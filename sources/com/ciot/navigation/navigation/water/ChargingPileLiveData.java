package com.ciot.navigation.navigation.water;

import androidx.lifecycle.MutableLiveData;
import com.ciot.waterchassis.bean.WaterMarkerBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00052\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/ciot/navigation/navigation/water/ChargingPileLiveData;", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/ciot/waterchassis/bean/WaterMarkerBean;", "()V", "Companion", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ChargingPileLiveData.kt */
public final class ChargingPileLiveData extends MutableLiveData<List<WaterMarkerBean>> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ChargingPileLiveData sInstance;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/navigation/navigation/water/ChargingPileLiveData$Companion;", "", "()V", "sInstance", "Lcom/ciot/navigation/navigation/water/ChargingPileLiveData;", "get", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ChargingPileLiveData.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChargingPileLiveData get() {
            ChargingPileLiveData chargingPileLiveData;
            if (ChargingPileLiveData.sInstance != null) {
                chargingPileLiveData = ChargingPileLiveData.sInstance;
                if (chargingPileLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    chargingPileLiveData = null;
                }
            } else {
                chargingPileLiveData = new ChargingPileLiveData();
            }
            ChargingPileLiveData.sInstance = chargingPileLiveData;
            ChargingPileLiveData access$getSInstance$cp = ChargingPileLiveData.sInstance;
            if (access$getSInstance$cp != null) {
                return access$getSInstance$cp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }
}
