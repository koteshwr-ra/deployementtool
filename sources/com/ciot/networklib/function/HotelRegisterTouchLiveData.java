package com.ciot.networklib.function;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/ciot/networklib/function/HotelRegisterTouchLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: HotelRegisterTouchLiveData.kt */
public final class HotelRegisterTouchLiveData extends MutableLiveData<Boolean> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static HotelRegisterTouchLiveData sInstance;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/function/HotelRegisterTouchLiveData$Companion;", "", "()V", "sInstance", "Lcom/ciot/networklib/function/HotelRegisterTouchLiveData;", "get", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: HotelRegisterTouchLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HotelRegisterTouchLiveData get() {
            HotelRegisterTouchLiveData hotelRegisterTouchLiveData;
            if (HotelRegisterTouchLiveData.sInstance != null) {
                hotelRegisterTouchLiveData = HotelRegisterTouchLiveData.sInstance;
                if (hotelRegisterTouchLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    hotelRegisterTouchLiveData = null;
                }
            } else {
                hotelRegisterTouchLiveData = new HotelRegisterTouchLiveData();
            }
            HotelRegisterTouchLiveData.sInstance = hotelRegisterTouchLiveData;
            HotelRegisterTouchLiveData access$getSInstance$cp = HotelRegisterTouchLiveData.sInstance;
            if (access$getSInstance$cp != null) {
                return access$getSInstance$cp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }
}
