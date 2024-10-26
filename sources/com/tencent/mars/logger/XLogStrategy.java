package com.tencent.mars.logger;

import com.tencent.mars.logger.XLogger;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/tencent/mars/logger/XLogStrategy;", "Lcom/tencent/mars/logger/LogStrategy;", "()V", "deleteFile", "", "logDir", "", "maxAliveTime", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "log", "priority", "", "tag", "message", "Companion", "xlog_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: XLogStrategy.kt */
public final class XLogStrategy implements LogStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DEFAULT_TAG = "NO_TAG";

    public XLogStrategy() {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
        final Xlog.XLogConfig build = XLogger.Builder.create().build();
        Xlog.setConsoleLogOpen(build.consoleLogOpen);
        Xlog.setMaxFileSize(build.maxFileSize);
        Xlog.setMaxAliveTime(build.maxAliveTime);
        Xlog.appenderOpen(build);
        Log.setLogImp(new Xlog());
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }

    public void log(int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "message");
        Utils.checkNotNull(str2);
        if (str == null) {
            str = DEFAULT_TAG;
        }
        if (i == 0) {
            Log.v(str, str2);
        } else if (i == 1) {
            Log.d(str, str2);
        } else if (i == 2) {
            Log.i(str, str2);
        } else if (i == 3) {
            Log.w(str, str2);
        } else if (i == 4) {
            Log.e(str, str2);
        } else if (i == 5) {
            Log.f(str, str2);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/tencent/mars/logger/XLogStrategy$Companion;", "", "()V", "DEFAULT_TAG", "", "xlog_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: XLogStrategy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.tencent.mars.logger.XLogStrategy$1", f = "XLogStrategy.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.tencent.mars.logger.XLogStrategy$1  reason: invalid class name */
    /* compiled from: XLogStrategy.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ XLogStrategy this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, build, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                XLogStrategy xLogStrategy = this.this$0;
                String str = build.logdir;
                Intrinsics.checkNotNullExpressionValue(str, "config.logdir");
                this.label = 1;
                if (xLogStrategy.deleteFile(str, build.maxAliveTime, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public final Object deleteFile(String str, long j, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new XLogStrategy$deleteFile$2(str, j, (Continuation<? super XLogStrategy$deleteFile$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
