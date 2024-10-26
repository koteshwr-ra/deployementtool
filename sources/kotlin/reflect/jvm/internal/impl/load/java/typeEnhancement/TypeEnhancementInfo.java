package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;

/* compiled from: predefinedEnhancementInfo.kt */
public final class TypeEnhancementInfo {
    private final Map<Integer, JavaTypeQualifiers> map;

    public TypeEnhancementInfo(Map<Integer, JavaTypeQualifiers> map2) {
        Intrinsics.checkParameterIsNotNull(map2, ScheduleFragment.MAP);
        this.map = map2;
    }

    public final Map<Integer, JavaTypeQualifiers> getMap() {
        return this.map;
    }
}
