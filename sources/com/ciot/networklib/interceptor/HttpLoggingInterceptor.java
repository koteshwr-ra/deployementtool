package com.ciot.networklib.interceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;

public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private boolean isLogEnable = false;
    private volatile Level level = Level.BODY;
    private Logger logger;
    private String tag;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public void log(String str) {
        this.logger.log(java.util.logging.Level.INFO, str);
    }

    public HttpLoggingInterceptor(String str) {
        this.tag = str;
        this.logger = Logger.getLogger(str);
    }

    public HttpLoggingInterceptor(String str, boolean z) {
        this.tag = str;
        this.isLogEnable = z;
        this.logger = Logger.getLogger(str);
    }

    public HttpLoggingInterceptor setLevel(Level level2) {
        if (level2 != null) {
            this.level = level2;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    public Level getLevel() {
        return this.level;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.level == Level.NONE) {
            return chain.proceed(request);
        }
        logForRequest(request, chain.connection());
        long nanoTime = System.nanoTime();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            log("<-- HTTP FAILED: " + e);
        }
        return logForResponse(response, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
    }

    private void logForRequest(Request request, Connection connection) throws IOException {
        StringBuilder sb;
        log("-------------------------------request-------------------------------");
        boolean z = true;
        boolean z2 = this.level == Level.BODY;
        boolean z3 = this.level == Level.BODY || this.level == Level.HEADERS;
        RequestBody body = request.body();
        if (body == null) {
            z = false;
        }
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        try {
            log("--> " + request.method() + ' ' + URLDecoder.decode(request.url().url().toString(), UTF8.name()) + ' ' + protocol);
            if (z3) {
                Headers headers = request.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    log("\t" + headers.name(i) + ": " + headers.value(i));
                }
                if (z2 && z) {
                    if (isPlaintext(body.contentType())) {
                        bodyToString(request);
                    } else {
                        log("\tbody: maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            sb = new StringBuilder();
        } catch (Exception e) {
            log("-->HttpLoggingInterceptor Exception " + e.getLocalizedMessage());
            e(e);
            sb = new StringBuilder();
        } catch (Throwable th) {
            log("--> END " + request.method());
            throw th;
        }
        sb.append("--> END ");
        sb.append(request.method());
        log(sb.toString());
    }

    private Response logForResponse(Response response, long j) {
        log("-------------------------------response-------------------------------");
        try {
            Response build = response.newBuilder().build();
            ResponseBody body = build.body();
            boolean z = true;
            boolean z2 = this.level == Level.BODY;
            if (this.level != Level.BODY) {
                if (this.level != Level.HEADERS) {
                    z = false;
                }
            }
            log("<-- " + build.code() + ' ' + build.message() + ' ' + URLDecoder.decode(build.request().url().url().toString(), UTF8.name()) + " (" + j + "ms）");
            if (z) {
                log(StringUtils.SPACE);
                Headers headers = build.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    log("\t" + headers.name(i) + ": " + headers.value(i));
                }
                log(StringUtils.SPACE);
                if (z2 && HttpHeaders.hasBody(build)) {
                    if (isPlaintext(body.contentType())) {
                        String string = body.string();
                        log("\tbody:" + string);
                        Response build2 = response.newBuilder().body(ResponseBody.create(body.contentType(), string)).build();
                        log("<-- END HTTP");
                        return build2;
                    }
                    log("\tbody: maybe [file part] , too large too print , ignored!");
                }
                log(StringUtils.SPACE);
            }
        } catch (Exception e) {
            e(e);
        } catch (Throwable th) {
            log("<-- END HTTP");
            throw th;
        }
        log("<-- END HTTP");
        return response;
    }

    static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        String subtype = mediaType.subtype();
        if (subtype != null) {
            String lowerCase = subtype.toLowerCase();
            if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains("html")) {
                return true;
            }
        }
        return false;
    }

    private void bodyToString(Request request) {
        try {
            Request build = request.newBuilder().build();
            Buffer buffer = new Buffer();
            build.body().writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = build.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String readString = buffer.readString(charset);
            log("\tbody:" + URLDecoder.decode(replacer(readString), UTF8.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String replacer(String str) {
        try {
            return URLDecoder.decode(str.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void e(Throwable th) {
        if (this.isLogEnable) {
            th.printStackTrace();
        }
    }
}
