package com.ciot.diagnosis.fragment;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.ciot.diagnosis.R;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/ciot/diagnosis/fragment/WebViewFragment$onViewCreated$1", "Landroid/webkit/WebChromeClient;", "onProgressChanged", "", "view", "Landroid/webkit/WebView;", "newProgress", "", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WebViewFragment.kt */
public final class WebViewFragment$onViewCreated$1 extends WebChromeClient {
    final /* synthetic */ WebViewFragment this$0;

    WebViewFragment$onViewCreated$1(WebViewFragment webViewFragment) {
        this.this$0 = webViewFragment;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        ProgressBar progressBar = (ProgressBar) this.this$0._$_findCachedViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.setProgress(i);
        }
    }
}
