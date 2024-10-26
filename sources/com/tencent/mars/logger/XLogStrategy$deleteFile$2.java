package com.tencent.mars.logger;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.blankj.utilcode.util.FileUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.tencent.mars.logger.XLogStrategy$deleteFile$2", f = "XLogStrategy.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: XLogStrategy.kt */
final class XLogStrategy$deleteFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $logDir;
    final /* synthetic */ long $maxAliveTime;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    XLogStrategy$deleteFile$2(String str, long j, Continuation<? super XLogStrategy$deleteFile$2> continuation) {
        super(2, continuation);
        this.$logDir = str;
        this.$maxAliveTime = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new XLogStrategy$deleteFile$2(this.$logDir, this.$maxAliveTime, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((XLogStrategy$deleteFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(CoroutineLiveDataKt.DEFAULT_TIMEOUT, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List<File> listFilesInDirWithFilter = FileUtils.listFilesInDirWithFilter(this.$logDir, (FileFilter) $$Lambda$XLogStrategy$deleteFile$2$yD1lEYBCaWoUmh8QLnlSCTdbQtY.INSTANCE);
        XLogger.i("deleteFile", "deleteFile  findFiles " + listFilesInDirWithFilter.size(), new Object[0]);
        Intrinsics.checkNotNullExpressionValue(listFilesInDirWithFilter, "files");
        long j = this.$maxAliveTime;
        for (File file : listFilesInDirWithFilter) {
            long j2 = (long) 1000;
            long lastModified = file.lastModified() / j2;
            long currentTimeMillis = (System.currentTimeMillis() / j2) - j;
            XLogger.e("deleteFile", "deleteFile lastModified =" + lastModified + "  deleteTime =" + currentTimeMillis, new Object[0]);
            if (lastModified <= currentTimeMillis) {
                FileUtils.delete(file);
                XLogger.e("deleteFile", "deleteFile delete file ->" + file.getAbsolutePath(), new Object[0]);
            }
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* renamed from: invokeSuspend$lambda-0  reason: not valid java name */
    public static final boolean m93invokeSuspend$lambda0(File file) {
        return Intrinsics.areEqual((Object) LoggerConfig.LOG_FILE_NAME, (Object) FileUtils.getFileExtension(file.getAbsolutePath()));
    }
}
