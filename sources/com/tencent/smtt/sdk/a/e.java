package com.tencent.smtt.sdk.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.tencent.smtt.utils.TbsLog;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class e {
    /* access modifiers changed from: private */
    public static String a = "EmergencyManager";
    private static final Object f = new Object();
    private static HandlerThread g;
    private static Handler h;
    /* access modifiers changed from: private */
    public String b;
    private String c;
    private String d;
    /* access modifiers changed from: private */
    public Handler e;

    public interface a {
        void a(String str);
    }

    public e(Context context, String str, String str2) {
        this(context, str, str2, "POST");
    }

    public e(Context context, String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = new Handler(context.getMainLooper());
    }

    private static Handler b() {
        Handler handler;
        synchronized (f) {
            if (h == null) {
                HandlerThread handlerThread = new HandlerThread("HttpThread");
                g = handlerThread;
                handlerThread.start();
                h = new Handler(g.getLooper());
            }
            handler = h;
        }
        return handler;
    }

    public String a(String str) {
        String str2 = a;
        TbsLog.e(str2, "Request url: " + this.b + ",params: " + this.c);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str.trim()).openConnection();
            httpURLConnection.setRequestMethod(this.d);
            httpURLConnection.setRequestProperty("Content-Type", FastJsonJsonView.DEFAULT_CONTENT_TYPE);
            httpURLConnection.setRequestProperty("Content-Length", this.c.length() + "");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(this.c.getBytes());
            int responseCode = httpURLConnection.getResponseCode();
            if (200 == responseCode) {
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (-1 == read) {
                        return byteArrayOutputStream.toString("utf-8");
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                }
            } else {
                String str3 = a;
                TbsLog.e(str3, "Bad http request, code: " + responseCode);
                return null;
            }
        } catch (Exception e2) {
            String str4 = a;
            TbsLog.e(str4, "Http exception: " + e2.getMessage());
            return null;
        }
    }

    public void a(final a aVar) {
        b().post(new Runnable() {
            public void run() {
                e eVar = e.this;
                final String a2 = eVar.a(eVar.b);
                if (a2 != null) {
                    e.this.e.post(new Runnable() {
                        public void run() {
                            if (aVar != null) {
                                aVar.a(a2);
                            }
                        }
                    });
                    return;
                }
                String a3 = e.a;
                TbsLog.e(a3, "Unexpected result for an empty http response: " + e.this.b);
            }
        });
    }
}
