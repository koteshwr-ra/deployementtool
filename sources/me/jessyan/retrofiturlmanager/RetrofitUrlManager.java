package me.jessyan.retrofiturlmanager;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.jessyan.retrofiturlmanager.parser.DefaultUrlParser;
import me.jessyan.retrofiturlmanager.parser.UrlParser;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitUrlManager {
    private static final boolean DEPENDENCY_OKHTTP;
    private static final String DOMAIN_NAME = "Domain-Name";
    public static final String DOMAIN_NAME_HEADER = "Domain-Name: ";
    private static final String GLOBAL_DOMAIN_NAME = "me.jessyan.retrofiturlmanager.globalDomainName";
    public static final String IDENTIFICATION_IGNORE = "#url_ignore";
    public static final String IDENTIFICATION_PATH_SIZE = "#baseurl_path_size=";
    private static final String TAG = "RetrofitUrlManager";
    private HttpUrl baseUrl;
    private boolean debug;
    private boolean isRun;
    private final Map<String, HttpUrl> mDomainNameHub;
    private final Interceptor mInterceptor;
    private final List<onUrlChangeListener> mListeners;
    private UrlParser mUrlParser;
    private int pathSize;

    static {
        boolean z;
        try {
            Class.forName("okhttp3.OkHttpClient");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        DEPENDENCY_OKHTTP = z;
    }

    private RetrofitUrlManager() {
        this.isRun = true;
        this.debug = false;
        this.mDomainNameHub = new HashMap();
        this.mListeners = new ArrayList();
        if (DEPENDENCY_OKHTTP) {
            DefaultUrlParser defaultUrlParser = new DefaultUrlParser();
            defaultUrlParser.init(this);
            setUrlParser(defaultUrlParser);
            this.mInterceptor = new Interceptor() {
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    if (!RetrofitUrlManager.this.isRun()) {
                        return chain.proceed(chain.request());
                    }
                    return chain.proceed(RetrofitUrlManager.this.processRequest(chain.request()));
                }
            };
            return;
        }
        throw new IllegalStateException("Must be dependency Okhttp");
    }

    private static class RetrofitUrlManagerHolder {
        /* access modifiers changed from: private */
        public static final RetrofitUrlManager INSTANCE = new RetrofitUrlManager();

        private RetrofitUrlManagerHolder() {
        }
    }

    public static final RetrofitUrlManager getInstance() {
        return RetrofitUrlManagerHolder.INSTANCE;
    }

    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        Utils.checkNotNull(builder, "builder cannot be null");
        return builder.addInterceptor(this.mInterceptor);
    }

    public Request processRequest(Request request) {
        HttpUrl httpUrl;
        if (request == null) {
            return request;
        }
        Request.Builder newBuilder = request.newBuilder();
        String httpUrl2 = request.url().toString();
        if (httpUrl2.contains(IDENTIFICATION_IGNORE)) {
            return pruneIdentification(newBuilder, httpUrl2);
        }
        String obtainDomainNameFromHeaders = obtainDomainNameFromHeaders(request);
        Object[] listenersToArray = listenersToArray();
        if (!TextUtils.isEmpty(obtainDomainNameFromHeaders)) {
            notifyListener(request, obtainDomainNameFromHeaders, listenersToArray);
            httpUrl = fetchDomain(obtainDomainNameFromHeaders);
            newBuilder.removeHeader(DOMAIN_NAME);
        } else {
            notifyListener(request, GLOBAL_DOMAIN_NAME, listenersToArray);
            httpUrl = getGlobalDomain();
        }
        if (httpUrl == null) {
            return newBuilder.build();
        }
        HttpUrl parseUrl = this.mUrlParser.parseUrl(httpUrl, request.url());
        if (this.debug) {
            Log.d(TAG, "The new url is { " + parseUrl.toString() + " }, old url is { " + request.url().toString() + " }");
        }
        if (listenersToArray != null) {
            for (Object obj : listenersToArray) {
                ((onUrlChangeListener) obj).onUrlChanged(parseUrl, request.url());
            }
        }
        return newBuilder.url(parseUrl).build();
    }

    private Request pruneIdentification(Request.Builder builder, String str) {
        String[] split = str.split(IDENTIFICATION_IGNORE);
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : split) {
            stringBuffer.append(append);
        }
        return builder.url(stringBuffer.toString()).build();
    }

    private void notifyListener(Request request, String str, Object[] objArr) {
        if (objArr != null) {
            for (onUrlChangeListener onUrlChangeBefore : objArr) {
                onUrlChangeBefore.onUrlChangeBefore(request.url(), str);
            }
        }
    }

    public boolean isRun() {
        return this.isRun;
    }

    public void setRun(boolean z) {
        this.isRun = z;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void startAdvancedModel(String str) {
        Utils.checkNotNull(str, "baseUrl cannot be null");
        startAdvancedModel(Utils.checkUrl(str));
    }

    public synchronized void startAdvancedModel(HttpUrl httpUrl) {
        Utils.checkNotNull(httpUrl, "baseUrl cannot be null");
        this.baseUrl = httpUrl;
        this.pathSize = httpUrl.pathSize();
        List<String> pathSegments = httpUrl.pathSegments();
        if ("".equals(pathSegments.get(pathSegments.size() - 1))) {
            this.pathSize--;
        }
    }

    public int getPathSize() {
        return this.pathSize;
    }

    public boolean isAdvancedModel() {
        return this.baseUrl != null;
    }

    public HttpUrl getBaseUrl() {
        return this.baseUrl;
    }

    public String setUrlNotChange(String str) {
        Utils.checkNotNull(str, "url cannot be null");
        return str + IDENTIFICATION_IGNORE;
    }

    public String setPathSizeOfUrl(String str, int i) {
        Utils.checkNotNull(str, "url cannot be null");
        if (i >= 0) {
            return str + IDENTIFICATION_PATH_SIZE + i;
        }
        throw new IllegalArgumentException("pathSize must be >= 0");
    }

    public void setGlobalDomain(String str) {
        Utils.checkNotNull(str, "globalDomain cannot be null");
        synchronized (this.mDomainNameHub) {
            this.mDomainNameHub.put(GLOBAL_DOMAIN_NAME, Utils.checkUrl(str));
        }
    }

    public synchronized HttpUrl getGlobalDomain() {
        return this.mDomainNameHub.get(GLOBAL_DOMAIN_NAME);
    }

    public void removeGlobalDomain() {
        synchronized (this.mDomainNameHub) {
            this.mDomainNameHub.remove(GLOBAL_DOMAIN_NAME);
        }
    }

    public void putDomain(String str, String str2) {
        Utils.checkNotNull(str, "domainName cannot be null");
        Utils.checkNotNull(str2, "domainUrl cannot be null");
        synchronized (this.mDomainNameHub) {
            this.mDomainNameHub.put(str, Utils.checkUrl(str2));
        }
    }

    public synchronized HttpUrl fetchDomain(String str) {
        Utils.checkNotNull(str, "domainName cannot be null");
        return this.mDomainNameHub.get(str);
    }

    public void removeDomain(String str) {
        Utils.checkNotNull(str, "domainName cannot be null");
        synchronized (this.mDomainNameHub) {
            this.mDomainNameHub.remove(str);
        }
    }

    public void clearAllDomain() {
        this.mDomainNameHub.clear();
    }

    public synchronized boolean haveDomain(String str) {
        return this.mDomainNameHub.containsKey(str);
    }

    public synchronized int domainSize() {
        return this.mDomainNameHub.size();
    }

    public void setUrlParser(UrlParser urlParser) {
        Utils.checkNotNull(urlParser, "parser cannot be null");
        this.mUrlParser = urlParser;
    }

    public void registerUrlChangeListener(onUrlChangeListener onurlchangelistener) {
        Utils.checkNotNull(onurlchangelistener, "listener cannot be null");
        synchronized (this.mListeners) {
            this.mListeners.add(onurlchangelistener);
        }
    }

    public void unregisterUrlChangeListener(onUrlChangeListener onurlchangelistener) {
        Utils.checkNotNull(onurlchangelistener, "listener cannot be null");
        synchronized (this.mListeners) {
            this.mListeners.remove(onurlchangelistener);
        }
    }

    private Object[] listenersToArray() {
        Object[] array;
        synchronized (this.mListeners) {
            array = this.mListeners.size() > 0 ? this.mListeners.toArray() : null;
        }
        return array;
    }

    private String obtainDomainNameFromHeaders(Request request) {
        List<String> headers = request.headers(DOMAIN_NAME);
        if (headers == null || headers.size() == 0) {
            return null;
        }
        if (headers.size() <= 1) {
            return request.header(DOMAIN_NAME);
        }
        throw new IllegalArgumentException("Only one Domain-Name in the headers");
    }
}
