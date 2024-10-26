package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import com.tencent.smtt.sdk.WebView;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class d {
    private static DexClassLoader b;
    /* access modifiers changed from: private */
    public static Looper c;
    private static d d;
    public String a = "";

    public interface a {
        void a();

        void a(int i);

        void a(Throwable th);
    }

    private d(Context context) {
        this.a = context.getDir("debugtbs", 0).getAbsolutePath() + File.separator + "plugin";
    }

    public static d a(Context context) {
        if (d == null) {
            d = new d(context);
        }
        return d;
    }

    public static void a(final String str, final a aVar) {
        new Thread() {
            /* JADX WARNING: Unknown top exception splitter block from list: {B:29:0x0072=Splitter:B:29:0x0072, B:13:0x004f=Splitter:B:13:0x004f} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r9 = this;
                    r0 = 0
                    java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    java.lang.String r2 = "https://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs"
                    r1.<init>(r2)     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    int r2 = r1.getContentLength()     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    r3 = 5000(0x1388, float:7.006E-42)
                    r1.setConnectTimeout(r3)     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    r1.connect()     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Exception -> 0x0060, all -> 0x005d }
                    java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    java.lang.String r4 = r1     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r3.<init>(r4)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    java.io.FileOutputStream r0 = com.tencent.smtt.utils.FileUtil.d((java.io.File) r3)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r3 = 8192(0x2000, float:1.14794E-41)
                    byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r4 = 0
                    r5 = 0
                L_0x002f:
                    int r6 = r1.read(r3)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    if (r6 <= 0) goto L_0x0042
                    int r5 = r5 + r6
                    r0.write(r3, r4, r6)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    int r6 = r5 * 100
                    int r6 = r6 / r2
                    com.tencent.smtt.utils.d$a r7 = r2     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r7.a((int) r6)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    goto L_0x002f
                L_0x0042:
                    com.tencent.smtt.utils.d$a r2 = r2     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r2.a()     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
                    r1.close()     // Catch:{ Exception -> 0x004b }
                    goto L_0x004f
                L_0x004b:
                    r1 = move-exception
                    r1.printStackTrace()
                L_0x004f:
                    r0.close()     // Catch:{ Exception -> 0x0076 }
                    goto L_0x007a
                L_0x0053:
                    r2 = move-exception
                    r8 = r1
                    r1 = r0
                    r0 = r8
                    goto L_0x007c
                L_0x0058:
                    r2 = move-exception
                    r8 = r1
                    r1 = r0
                    r0 = r8
                    goto L_0x0062
                L_0x005d:
                    r2 = move-exception
                    r1 = r0
                    goto L_0x007c
                L_0x0060:
                    r2 = move-exception
                    r1 = r0
                L_0x0062:
                    r2.printStackTrace()     // Catch:{ all -> 0x007b }
                    com.tencent.smtt.utils.d$a r3 = r2     // Catch:{ all -> 0x007b }
                    r3.a((java.lang.Throwable) r2)     // Catch:{ all -> 0x007b }
                    r0.close()     // Catch:{ Exception -> 0x006e }
                    goto L_0x0072
                L_0x006e:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0072:
                    r1.close()     // Catch:{ Exception -> 0x0076 }
                    goto L_0x007a
                L_0x0076:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x007a:
                    return
                L_0x007b:
                    r2 = move-exception
                L_0x007c:
                    r0.close()     // Catch:{ Exception -> 0x0080 }
                    goto L_0x0084
                L_0x0080:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0084:
                    r1.close()     // Catch:{ Exception -> 0x0088 }
                    goto L_0x008c
                L_0x0088:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x008c:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.d.AnonymousClass2.run():void");
            }
        }.start();
    }

    public void a(String str, WebView webView, Context context) {
        final RelativeLayout relativeLayout = new RelativeLayout(context);
        final TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setText("加载中，请稍后...");
        relativeLayout.addView(textView, layoutParams);
        webView.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
        String str2 = this.a + File.separator + "DebugPlugin.tbs";
        FileUtil.b(new File(str2));
        final WebView webView2 = webView;
        final Context context2 = context;
        final String str3 = str;
        a(str2, new a() {
            public void a() {
                webView2.post(new Runnable() {
                    public void run() {
                        Toast.makeText(context2, "下载成功", 0).show();
                        relativeLayout.setVisibility(4);
                        d.this.a(str3, webView2, context2, d.c);
                    }
                });
            }

            public void a(final int i) {
                webView2.post(new Runnable() {
                    public void run() {
                        TextView textView = textView;
                        textView.setText("已下载" + i + "%");
                    }
                });
            }

            public void a(Throwable th) {
                webView2.post(new Runnable() {
                    public void run() {
                        Toast.makeText(context2, "下载失败，请检查网络", 0).show();
                    }
                });
            }
        });
    }

    public void a(String str, WebView webView, Context context, Looper looper) {
        TbsLog.i("debugtbs", "showPluginView -- url: " + str + "; webview: " + webView + "; context: " + context);
        String str2 = this.a + File.separator + "DebugPlugin.apk";
        File file = new File(this.a + File.separator + "DebugPlugin.tbs");
        File file2 = new File(str2);
        c = looper;
        if (file.exists()) {
            file2.delete();
            file.renameTo(file2);
        }
        if (!file2.exists()) {
            TbsLog.i("debugtbs", "showPluginView - going to download plugin...");
            a(str, webView, context);
            return;
        }
        try {
            String a2 = b.a(context, true, new File(str2));
            if (!"308203773082025fa003020102020448bb959d300d06092a864886f70d01010b0500306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e673020170d3136303532313039353730335a180f32303731303232323039353730335a306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e6730820122300d06092a864886f70d01010105000382010f003082010a02820101008c58deabefe95f699c6322f9a75620873b490d26520c7267eb8382a91da625a5876b2bd617116eb40b371c4f88c988c1ba73052caaa9964873c94b7755c3429fca47a6677229fb2e72908d3b17df82f1ebe70447b94c1e5b0a763dad8948312180322657325306f01e423e0409ef3a59e5c0e0b9c765a2322699a2dec2d4dbe58ec15f41752516192169d9596169f5bf08eaf8aab9893240ad679e82fc92b97d2ae98b28021dc5a752f0a69437ea603c541e6753cea52dbc8e8043fe21fd5da46066c92e0714905dfad3116f35aca52b13871c57481459aa4ca255a6482ba972bd17af90d0d2c21a57ef65376bbd4ce7078e6047060640669f3867fdc69fbb750203010001a321301f301d0603551d0e0416041450fb9b6362e829797b1b29ca55e6d5e082e93ff3300d06092a864886f70d01010b050003820101004952ffbfba7c00ee9b84f44b05ec62bc2400dc769fb2e83f80395e3fbb54e44d56e16527413d144f42bf8f21fa443bc42a7a732de9d5124df906c6d728e75ca94eefc918080876bd3ce6cb5f7f2d9cc8d8e708033afc1295c7f347fb2d2098be2e4a79220e9552171d5b5f8f59cff4c6478cc41dce24cbe942305757488d37659d3265838ee54ebe44fccbd1bec93d809f950034f5ef292f20179554d22f5856c03b4d44997fcb9b3579e16a49218fce0e2e6bfe1fd4aa0ab39f548344c244c171c203baff1a730883aaf4506b6865a45c3c9aba40c6326d4152b6ce09cc058864bec1d6422e83dad9496b83fb252b4bfb30d3a6badf996099793e11f9af618d".equals(a2)) {
                TbsLog.e("debugtbs", "verifyPlugin apk: " + str2 + " signature failed - sig: " + a2);
                Toast.makeText(context, "插件校验失败，请重试", 0).show();
                file.delete();
                file2.delete();
                return;
            }
            String str3 = this.a + File.separator + "opt";
            File file3 = new File(str3);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            if (b == null) {
                b = new DexClassLoader(str2, str3, (String) null, context.getClassLoader());
            }
            HashMap hashMap = new HashMap();
            hashMap.put(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, str);
            hashMap.put("tbs_version", "" + WebView.getTbsSDKVersion(context));
            hashMap.put("tbs_core_version", "" + WebView.getTbsCoreVersion(context));
            if (c != null) {
                hashMap.put("looper", looper);
            }
            Object newInstance = b.loadClass("com.tencent.tbs.debug.plugin.DebugView").getConstructor(new Class[]{Context.class, Map.class}).newInstance(new Object[]{context, hashMap});
            if (newInstance instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) newInstance;
                webView.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
                TbsLog.i("debugtbs", "show " + frameLayout + " successful in " + webView);
                return;
            }
            TbsLog.e("debugtbs", "get debugview failure: " + newInstance);
        } catch (Exception e) {
            FileUtil.b(file2);
            e.printStackTrace();
        }
    }
}
