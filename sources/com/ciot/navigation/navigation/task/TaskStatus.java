package com.ciot.navigation.navigation.task;

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
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ciot/navigation/navigation/task/TaskStatus;", "", "Companion", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* compiled from: TaskStatus.kt */
public @interface TaskStatus {
    public static final int COMPLETE = 2;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int PAUSE = 4;
    public static final int RUNNING = 0;
    public static final int START = 1;
    public static final int STOP = 3;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ciot/navigation/navigation/task/TaskStatus$Companion;", "", "()V", "COMPLETE", "", "PAUSE", "RUNNING", "START", "STOP", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: TaskStatus.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int COMPLETE = 2;
        public static final int PAUSE = 4;
        public static final int RUNNING = 0;
        public static final int START = 1;
        public static final int STOP = 3;

        private Companion() {
        }
    }
}
