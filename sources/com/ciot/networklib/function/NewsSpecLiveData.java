package com.ciot.networklib.function;

import androidx.lifecycle.MutableLiveData;
import com.ciot.networklib.bean.NewsSpecBean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/ciot/networklib/function/NewsSpecLiveData;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ciot/networklib/bean/NewsSpecBean;", "()V", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NewsSpecLiveData.kt */
public final class NewsSpecLiveData extends MutableLiveData<NewsSpecBean> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static NewsSpecLiveData sInstance;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/function/NewsSpecLiveData$Companion;", "", "()V", "sInstance", "Lcom/ciot/networklib/function/NewsSpecLiveData;", "get", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NewsSpecLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NewsSpecLiveData get() {
            NewsSpecLiveData newsSpecLiveData;
            if (NewsSpecLiveData.sInstance != null) {
                newsSpecLiveData = NewsSpecLiveData.sInstance;
                if (newsSpecLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    newsSpecLiveData = null;
                }
            } else {
                newsSpecLiveData = new NewsSpecLiveData();
            }
            NewsSpecLiveData.sInstance = newsSpecLiveData;
            NewsSpecLiveData access$getSInstance$cp = NewsSpecLiveData.sInstance;
            if (access$getSInstance$cp != null) {
                return access$getSInstance$cp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }
}
