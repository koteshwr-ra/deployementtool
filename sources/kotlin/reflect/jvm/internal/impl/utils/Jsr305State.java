package kotlin.reflect.jvm.internal.impl.utils;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: Jsr305State.kt */
public final class Jsr305State {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Jsr305State.class), "description", "getDescription()[Ljava/lang/String;"))};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Jsr305State DEFAULT = new Jsr305State(ReportLevel.WARN, (ReportLevel) null, MapsKt.emptyMap(), false, 8, (DefaultConstructorMarker) null);
    public static final Jsr305State DISABLED = new Jsr305State(ReportLevel.IGNORE, ReportLevel.IGNORE, MapsKt.emptyMap(), false, 8, (DefaultConstructorMarker) null);
    public static final Jsr305State STRICT = new Jsr305State(ReportLevel.STRICT, ReportLevel.STRICT, MapsKt.emptyMap(), false, 8, (DefaultConstructorMarker) null);
    private final Lazy description$delegate;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;
    private final ReportLevel global;
    private final ReportLevel migration;
    private final Map<String, ReportLevel> user;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Jsr305State) {
                Jsr305State jsr305State = (Jsr305State) obj;
                if (Intrinsics.areEqual((Object) this.global, (Object) jsr305State.global) && Intrinsics.areEqual((Object) this.migration, (Object) jsr305State.migration) && Intrinsics.areEqual((Object) this.user, (Object) jsr305State.user)) {
                    if (this.enableCompatqualCheckerFrameworkAnnotations == jsr305State.enableCompatqualCheckerFrameworkAnnotations) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        ReportLevel reportLevel = this.global;
        int i = 0;
        int hashCode = (reportLevel != null ? reportLevel.hashCode() : 0) * 31;
        ReportLevel reportLevel2 = this.migration;
        int hashCode2 = (hashCode + (reportLevel2 != null ? reportLevel2.hashCode() : 0)) * 31;
        Map<String, ReportLevel> map = this.user;
        if (map != null) {
            i = map.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.enableCompatqualCheckerFrameworkAnnotations;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public String toString() {
        return "Jsr305State(global=" + this.global + ", migration=" + this.migration + ", user=" + this.user + ", enableCompatqualCheckerFrameworkAnnotations=" + this.enableCompatqualCheckerFrameworkAnnotations + ")";
    }

    public Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map<String, ? extends ReportLevel> map, boolean z) {
        Intrinsics.checkParameterIsNotNull(reportLevel, "global");
        Intrinsics.checkParameterIsNotNull(map, "user");
        this.global = reportLevel;
        this.migration = reportLevel2;
        this.user = map;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        this.description$delegate = LazyKt.lazy(new Jsr305State$description$2(this));
    }

    public final ReportLevel getGlobal() {
        return this.global;
    }

    public final ReportLevel getMigration() {
        return this.migration;
    }

    public final Map<String, ReportLevel> getUser() {
        return this.user;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, reportLevel2, map, (i & 8) != 0 ? true : z);
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getDisabled() {
        return this == DISABLED;
    }

    /* compiled from: Jsr305State.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
