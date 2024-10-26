package com.ciot.networklib;

import com.ciot.networklib.bean.ProjectResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ciot/networklib/CurrentProjectInfo;", "", "()V", "info", "Lcom/ciot/networklib/bean/ProjectResponse;", "getInfo", "setInfo", "", "Companion", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CurrentProjectInfo.kt */
public final class CurrentProjectInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static CurrentProjectInfo currentProjectInfo;
    private ProjectResponse info;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ciot/networklib/CurrentProjectInfo$Companion;", "", "()V", "currentProjectInfo", "Lcom/ciot/networklib/CurrentProjectInfo;", "getInstance", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: CurrentProjectInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public CurrentProjectInfo getInstance() {
            if (CurrentProjectInfo.currentProjectInfo == null) {
                CurrentProjectInfo.currentProjectInfo = new CurrentProjectInfo();
            }
            CurrentProjectInfo access$getCurrentProjectInfo$cp = CurrentProjectInfo.currentProjectInfo;
            Intrinsics.checkNotNull(access$getCurrentProjectInfo$cp);
            return access$getCurrentProjectInfo$cp;
        }
    }

    public final void setInfo(ProjectResponse projectResponse) {
        Intrinsics.checkNotNullParameter(projectResponse, "info");
        this.info = projectResponse;
    }

    public final ProjectResponse getInfo() {
        return this.info;
    }
}
