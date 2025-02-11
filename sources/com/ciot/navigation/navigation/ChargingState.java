package com.ciot.navigation.navigation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
@Retention(AnnotationRetention.SOURCE)
@Documented
@java.lang.annotation.Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ciot/navigation/navigation/ChargingState;", "", "Companion", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* compiled from: ChargingState.kt */
public @interface ChargingState {
    public static final int BACK_CHARGING_PILE = 2;
    public static final int BACK_CHARGING_PILE_FAIL = -1;
    public static final int CHARGING = 1;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int NOT_CONNECTED = 0;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/ciot/navigation/navigation/ChargingState$Companion;", "", "()V", "BACK_CHARGING_PILE", "", "BACK_CHARGING_PILE_FAIL", "CHARGING", "NOT_CONNECTED", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ChargingState.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int BACK_CHARGING_PILE = 2;
        public static final int BACK_CHARGING_PILE_FAIL = -1;
        public static final int CHARGING = 1;
        public static final int NOT_CONNECTED = 0;

        private Companion() {
        }
    }
}
