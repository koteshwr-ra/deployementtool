package com.tencent.smtt.sdk;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import com.tencent.smtt.export.external.interfaces.DownloadListener;
import com.tencent.smtt.sdk.stat.MttLoader;
import java.util.HashMap;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;

class b implements DownloadListener {
    private DownloadListener a;
    private WebView b;

    public b(WebView webView, DownloadListener downloadListener, boolean z) {
        this.a = downloadListener;
        this.b = webView;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        onDownloadStart(str, (String) null, (byte[]) null, str2, str3, str4, j, (String) null, (String) null);
    }

    public void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j, String str6, String str7) {
        String str8 = str;
        DownloadListener downloadListener = this.a;
        if (downloadListener == null) {
            String str9 = str5;
            if (QbSdk.canOpenMimeFileType(this.b.getContext(), str5)) {
                Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
                intent.setFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
                intent.putExtra("key_reader_sdk_url", str);
                intent.putExtra("key_reader_sdk_type", 1);
                intent.setData(Uri.parse(str));
                this.b.getContext().startActivity(intent);
                return;
            }
            ApplicationInfo applicationInfo = this.b.getContext().getApplicationInfo();
            if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                MttLoader.loadUrl(this.b.getContext(), str, (HashMap<String, String>) null, (WebView) null);
                return;
            }
            return;
        }
        downloadListener.onDownloadStart(str, str3, str4, str5, j);
    }

    public void onDownloadVideo(String str, long j, int i) {
    }
}
