package com.ciot.base.constant;

import android.os.Environment;
import com.ciot.base.util.AppSpUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\n\n\u0002\b_\n\u0002\u0010\u000b\n\u0002\bi\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\tR\u000e\u0010!\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\tR\u0014\u0010$\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\tR\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u0011\u0010n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\tR\u001a\u0010p\u001a\u00020qX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u001a\u0010v\u001a\u00020qX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010s\"\u0004\bx\u0010uR\u000e\u0010y\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010 \u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010¡\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010£\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u0013\u0010¤\u0001\u001a\u00020\u00048F¢\u0006\u0007\u001a\u0005\b¥\u0001\u0010\tR\u000f\u0010¦\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010§\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010¨\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0013\u0010©\u0001\u001a\u00020\u00048F¢\u0006\u0007\u001a\u0005\bª\u0001\u0010\tR\u000f\u0010«\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010¬\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010­\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010®\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010¯\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010°\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010±\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010²\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001f\u0010³\u0001\u001a\u00020\u000bX\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b´\u0001\u0010µ\u0001\"\u0006\b¶\u0001\u0010·\u0001R\u000f\u0010¸\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010¹\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010º\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010»\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010¼\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010½\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010¾\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010¿\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010À\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010Á\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010Â\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010Ã\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ä\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Å\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Æ\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ç\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010È\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010É\u0001\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000f\u0010Ê\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001e\u0010Ë\u0001\u001a\u00020\u0004X\u000e¢\u0006\u0011\n\u0000\u001a\u0005\bÌ\u0001\u0010\t\"\u0006\bÍ\u0001\u0010Î\u0001R\u0013\u0010Ï\u0001\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\bÐ\u0001\u0010\tR\u0013\u0010Ñ\u0001\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\bÒ\u0001\u0010\tR\u000f\u0010Ó\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ô\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Õ\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ö\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010×\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ø\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000f\u0010Ù\u0001\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006Ú\u0001"}, d2 = {"Lcom/ciot/base/constant/NetConstant;", "", "()V", "ACTION_BROADCAST_START_TCP_SERVICE", "", "AUDIO_BASE_URL", "AUTHORIZATION_CODE", "AUTHORIZATION_CODE_PATH", "getAUTHORIZATION_CODE_PATH", "()Ljava/lang/String;", "AUTHORIZATION_ERROR_CODE", "", "AUTHORIZATION_VIDEO_CODE", "CIOT_DEFAULT_URL_DEV", "CIOT_DEFAULT_URL_PRODUCT", "CIOT_DEFAULT_URL_TEST", "CONTROL_ACTIVATE", "", "CONTROL_DEVICE_MANAGEMENT_REGISTER", "CONTROL_LOCAL_START_PHONE_CALL", "CONTROL_LOCAL_STOP_PHONE_CALL", "CONTROL_NOTIFICATION", "CONTROL_QUERY_PLAY_BACK", "CONTROL_START_PHONE_CALL", "CONTROL_START_VIDEO_LIVE", "CONTROL_START_VIDEO_PLAY_BACK", "CONTROL_STATUS_HEART_BEAT", "CONTROL_STOP_PHONE_CALL", "CONTROL_STOP_VIDEO_LIVE", "CONTROL_STOP_VIDEO_PLAY_BACK", "CONTROL_VIDEO_PLAY_BACK_STATUS", "DEFAULT_SERVICE_URL", "getDEFAULT_SERVICE_URL", "DEFAULT_URL", "DIAGNOSIS_IP_PRODUCTION", "getDIAGNOSIS_IP_PRODUCTION", "DIAGNOSIS_IP_TEST", "getDIAGNOSIS_IP_TEST", "EVENT_CLICK_LELE_APP_BIG_PICTURE", "EVENT_CLICK_LELE_APP_CHAT", "EVENT_CLICK_LELE_APP_COMBINATION_SWITCH", "EVENT_CLICK_LELE_APP_COMPANY", "EVENT_CLICK_LELE_APP_CURRENT_SWITCH", "EVENT_CLICK_LELE_APP_DETAILS_COMPANY", "EVENT_CLICK_LELE_APP_DETAILS_FACILITIES", "EVENT_CLICK_LELE_APP_DETAILS_INTERACTIVE_STORY", "EVENT_CLICK_LELE_APP_DETAILS_NEWS", "EVENT_CLICK_LELE_APP_DETAILS_PRODUCT_INTRODUCTION", "EVENT_CLICK_LELE_APP_DETAILS_QA", "EVENT_CLICK_LELE_APP_DETAILS_REGISTER", "EVENT_CLICK_LELE_APP_DIAGNOSIS", "EVENT_CLICK_LELE_APP_FACE_SWITCH", "EVENT_CLICK_LELE_APP_FACILITIES", "EVENT_CLICK_LELE_APP_FREE_TIME_AUDIO", "EVENT_CLICK_LELE_APP_FREE_TIME_CONTENT", "EVENT_CLICK_LELE_APP_FREE_TIME_INTERVAL", "EVENT_CLICK_LELE_APP_FREE_TIME_SWITCH", "EVENT_CLICK_LELE_APP_ID_CARD_SWITCH", "EVENT_CLICK_LELE_APP_MEETING_ATTEDDANCE", "EVENT_CLICK_LELE_APP_NAVIGATION_BAR", "EVENT_CLICK_LELE_APP_NEWS", "EVENT_CLICK_LELE_APP_ON_SITE_REGISTARTION", "EVENT_CLICK_LELE_APP_PRODUCT_INTRODUCTION", "EVENT_CLICK_LELE_APP_QA_CONSULT", "EVENT_CLICK_LELE_APP_QR_SWITCH", "EVENT_CLICK_LELE_APP_SILENCE_SWITCH_1", "EVENT_CLICK_LELE_APP_SILENCE_SWITCH_2", "EVENT_CLICK_LELE_APP_TEMPARATURE_ZONE", "EVENT_CLICK_LELE_APP_TEMPERATURE_SWITCH", "EVENT_CLICK_LELE_APP_TEMPERATURE_THRESHOLD", "EVENT_CLICK_LELE_APP_VISIT", "EVENT_CLICK_LELE_APP_VISTOR_SWITCH", "EVENT_CLICK_LELE_APP_WELCOME", "EVENT_LOAD_LELE_APP", "EVENT_LOAD_LELE_APP_ABNORMAL_TEMPERATURE", "EVENT_LOAD_LELE_APP_ATTENDANCE_TIPS", "EVENT_LOAD_LELE_APP_CAMERA_INITIALIZATION_FAILED", "EVENT_LOAD_LELE_APP_CONTENT_NOT_CONFIGURED", "EVENT_LOAD_LELE_APP_DETAILS_REGISTER_MDI", "EVENT_LOAD_LELE_APP_DETAILS_VISIT", "EVENT_LOAD_LELE_APP_HOME", "EVENT_LOAD_LELE_APP_INITIALIZATION_FAILED", "EVENT_LOAD_LELE_APP_MASK_TIPS_TIMES", "EVENT_LOAD_LELE_APP_MEETING_ATTENDANCE", "EVENT_LOAD_LELE_APP_NETWORK_NOT_CONNECTED", "EVENT_LOAD_LELE_APP_RECORDING_FAILED", "EVENT_LOAD_LELE_APP_REGISTRATION_ID_CARD", "EVENT_LOAD_LELE_APP_STANDBY", "EVENT_LOAD_LELE_APP_STOP_RUNNING", "EVENT_LOAD_LELE_APP_TEMPERATURE_PIC_SWITCH", "EVENT_LOAD_LELE_APP_TEMPERATURE_TIMES", "EVENT_LOAD_LELE_APP_VOICE_AUTHORIZATION_FAILED", "EVENT_QR_LELLE_APP", "EVENT_VOICE_LELE_APP_COMPANY", "EVENT_VOICE_LELE_APP_DETAILS_COMPANY", "EVENT_VOICE_LELE_APP_DETAILS_FACILITIES", "EVENT_VOICE_LELE_APP_DETAILS_INTERACTIVE_STORY", "EVENT_VOICE_LELE_APP_DETAILS_PRODUCT_INTRODUCTION", "EVENT_VOICE_LELE_APP_DETAILS_QA", "EVENT_VOICE_LELE_APP_FACILITIES", "EVENT_VOICE_LELE_APP_NEWS", "EVENT_VOICE_LELE_APP_PRODUCT_INTRODUCTION", "EVENT_VOICE_LELE_APP_QA_CONSULT", "EVENT_VOICE_LELE_APP_VISIT", "INIT_STATE_GET_IP", "INIT_STATE_GET_PROPERTITY", "INIT_STATE_GET_USER", "INIT_STATE_IDLE", "INIT_STATE_INIT_EXCEPTION", "INIT_STATE_LONGIN_GET_TOKEN", "IP", "getIP", "IS_GET_CODE_FROM_NET", "", "getIS_GET_CODE_FROM_NET", "()Z", "setIS_GET_CODE_FROM_NET", "(Z)V", "IS_OPEN_FULL_DUPLEX", "getIS_OPEN_FULL_DUPLEX", "setIS_OPEN_FULL_DUPLEX", "MSG_SROS_AD_REFRESH", "MSG_SROS_ARCCODE_UPDATE", "MSG_SROS_CHANGE_GREETINGS_DISTANTLY", "MSG_SROS_CHANGE_GREETINGS_NEARBY", "MSG_SROS_CHANGE_HOME_NEWS", "MSG_SROS_CHANGE_HOTEL_ACTIVITY", "MSG_SROS_CHANGE_TACTICS", "MSG_SROS_DELETE_EMPLOEE", "MSG_SROS_DELETE_VERIFY", "MSG_SROS_DELETE_VISITOR", "MSG_SROS_DOWNLOAD_MAP", "MSG_SROS_GET_TOKEN", "MSG_SROS_IMAGE_BASE_URL", "MSG_SROS_PATROL_TASK_REFRESH", "MSG_SROS_SELF_MAP_UPDATE", "MSG_SROS_SET_PASSWORD", "MSG_SROS_SET_PLATFORM_BASE_URL", "MSG_SROS_SET_TOKEN", "MSG_SROS_SET_USER_NAME", "MSG_SROS_TCP_ONLINE", "MSG_SROS_UPDATE_EMPLOEE", "MSG_SROS_UPDATE_PROPERTY", "MSG_SROS_UPDATE_VERIFY", "MSG_SROS_UPDATE_VISITOR", "MSG_SROS_UPLOAD_LOG", "MSG_SROS_UPLOAD_MAP", "MSG_SROS_VERSION_UPDATE", "MSG_SROS_VIDEO_ACTIVATE", "MSG_SROS_VIDEO_CANCEL", "MSG_SROS_VOICE_CODE_ACTIVATE", "MSG_SROS_VOICE_CODE_CANCLE", "PAGE_ID_COMPANY", "PAGE_ID_CONSULTATION", "PAGE_ID_DIALOG_SHOW", "PAGE_ID_HOME", "PAGE_ID_IDLE", "PAGE_ID_NAV_DETAIL_INTRODUCE", "PAGE_ID_NAV_GUIDE_INTRODUCE", "PAGE_ID_PRODUCT_INTRODUCE", "PAGE_ID_PUBLIC_UTILITIES", "PAGE_ID_RCMD", "PAGE_ID_VISITOR", "PORT", "ROBOT_ID_INPUT", "getROBOT_ID_INPUT", "ROBOT_ID_INPUT_LELE", "ROBOT_ID_INPUT_WELCOM", "ROBOT_ID_INPUT_WELCOMPATROL", "ROBOT_NUM", "getROBOT_NUM", "ROBOT_NUM_DISINFECT", "ROBOT_NUM_GATEKEEPER", "ROBOT_NUM_LELE", "ROBOT_NUM_MEAL_DELIVERY", "ROBOT_NUM_MINI_OH_DISINFECT", "ROBOT_NUM_OH_DISINFECT", "ROBOT_NUM_WELCOM", "ROBOT_NUM_WELCOME_PATROL", "ROBOT_TYPE", "getROBOT_TYPE", "()I", "setROBOT_TYPE", "(I)V", "ROBOT_TYPE_GATEKEEPER", "ROBOT_TYPE_IDLE", "ROBOT_TYPE_LELE", "ROBOT_TYPE_MEAL_DELIVERY", "ROBOT_TYPE_MINI_OH_SPRAY", "ROBOT_TYPE_OH_SPRAY", "ROBOT_TYPE_PATROL", "ROBOT_TYPE_SPRAY", "ROBOT_TYPE_WELCOM", "ROBOT_TYPE_WELCOME_PATROL", "ROBOT_TYPE_XUNGENG", "SP_CONFIG_ROBOT_ID", "SP_CONFIG_ROBOT_NUM", "SP_CONFIG_ROBOT_TYPE", "SP_CONFIG_WUHAN_BASEURL", "SROS_SEND_STATUS_INTERVAL_TIME", "TAG", "TCP_SERVER_PORT", "TEST_AUDIO_BASE_URL", "URL_DEFAULT_SEMANTIC", "getURL_DEFAULT_SEMANTIC", "setURL_DEFAULT_SEMANTIC", "(Ljava/lang/String;)V", "URL_DEFAULT_SEMANTIC_INTENT", "getURL_DEFAULT_SEMANTIC_INTENT", "URL_DEFAULT_TOPIC", "getURL_DEFAULT_TOPIC", "WATER_GET_SIM_INFO", "YUNJI_DEFAULT_URL_DEV", "YUNJI_DEFAULT_URL_PRODUCT", "YUNJI_DEFAULT_URL_TEST", "YUNJI_DEVICE_REGIESTER_URL_DEV", "YUNJI_DEVICE_REGIESTER_URL_PRODUCT", "YUN_JI_OPEN_API_BASE_URL", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NetConstant.kt */
public final class NetConstant {
    public static final String ACTION_BROADCAST_START_TCP_SERVICE = "start TcpService";
    public static final String AUDIO_BASE_URL = "https://mrad-reqtask.brandwisdom.cn/";
    public static final String AUTHORIZATION_CODE = "AUTHORIZATION_CODE";
    private static final String AUTHORIZATION_CODE_PATH = (Environment.getExternalStorageDirectory().toString() + File.separator + "Authorization");
    public static final int AUTHORIZATION_ERROR_CODE = 5;
    public static final String AUTHORIZATION_VIDEO_CODE = "AUTHORIZATION_VIDEO_CODE";
    public static final String CIOT_DEFAULT_URL_DEV = "http://220.231.188.150:8000";
    public static final String CIOT_DEFAULT_URL_PRODUCT = "http://ai.csstrobot.com";
    public static final String CIOT_DEFAULT_URL_TEST = " http://39.99.134.198:8000";
    public static final short CONTROL_ACTIVATE = -30968;
    public static final short CONTROL_DEVICE_MANAGEMENT_REGISTER = -30975;
    public static final short CONTROL_LOCAL_START_PHONE_CALL = 2064;
    public static final short CONTROL_LOCAL_STOP_PHONE_CALL = 2065;
    public static final short CONTROL_NOTIFICATION = 17;
    public static final short CONTROL_QUERY_PLAY_BACK = -30205;
    public static final short CONTROL_START_PHONE_CALL = -30200;
    public static final short CONTROL_START_VIDEO_LIVE = -30207;
    public static final short CONTROL_START_VIDEO_PLAY_BACK = -30204;
    public static final short CONTROL_STATUS_HEART_BEAT = -32544;
    public static final short CONTROL_STOP_PHONE_CALL = -30199;
    public static final short CONTROL_STOP_VIDEO_LIVE = -30206;
    public static final short CONTROL_STOP_VIDEO_PLAY_BACK = -30203;
    public static final short CONTROL_VIDEO_PLAY_BACK_STATUS = -30201;
    private static final String DEFAULT_SERVICE_URL = (HttpConstant.HTTP_URL + IP + ":9899/");
    public static final String DEFAULT_URL = "https://jhai-zz.yunjiai.cn";
    private static final String DIAGNOSIS_IP_PRODUCTION = "https://dopt.szkwrobot.com:10000";
    private static final String DIAGNOSIS_IP_TEST = "https://office.yunbiaokj.com:3105";
    public static final String EVENT_CLICK_LELE_APP_BIG_PICTURE = "click_lele_app_Big_picture";
    public static final String EVENT_CLICK_LELE_APP_CHAT = "click_lele_app_chat";
    public static final String EVENT_CLICK_LELE_APP_COMBINATION_SWITCH = "click_lele_app_Combination_switch";
    public static final String EVENT_CLICK_LELE_APP_COMPANY = "click_lele_app_company";
    public static final String EVENT_CLICK_LELE_APP_CURRENT_SWITCH = "click_lele_app_current_switch";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_COMPANY = "click_lele_app_details_company";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_FACILITIES = "click_lele_app_details_facilities";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_INTERACTIVE_STORY = "click_lele_app_details_Interactive_story";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_NEWS = "click_lele_app_details_news";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_PRODUCT_INTRODUCTION = "click_lele_app_details_Product_introduction";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_QA = "click_lele_app_details_qa";
    public static final String EVENT_CLICK_LELE_APP_DETAILS_REGISTER = "click_lele_app_details_register";
    public static final String EVENT_CLICK_LELE_APP_DIAGNOSIS = "click_lele_app_diagnosis";
    public static final String EVENT_CLICK_LELE_APP_FACE_SWITCH = "click_lele_app_face_switch";
    public static final String EVENT_CLICK_LELE_APP_FACILITIES = "click_lele_app_facilities";
    public static final String EVENT_CLICK_LELE_APP_FREE_TIME_AUDIO = "click_lele_app_free_time_audio";
    public static final String EVENT_CLICK_LELE_APP_FREE_TIME_CONTENT = "click_lele_app_free_time_content";
    public static final String EVENT_CLICK_LELE_APP_FREE_TIME_INTERVAL = "click_lele_app_free_time_interval";
    public static final String EVENT_CLICK_LELE_APP_FREE_TIME_SWITCH = "click_lele_app_free_time_switch";
    public static final String EVENT_CLICK_LELE_APP_ID_CARD_SWITCH = "click_lele_app_ID_card_switch";
    public static final String EVENT_CLICK_LELE_APP_MEETING_ATTEDDANCE = "click_lele_app_Meeting_attendance";
    public static final String EVENT_CLICK_LELE_APP_NAVIGATION_BAR = "click_lele_app_navigation_bar";
    public static final String EVENT_CLICK_LELE_APP_NEWS = "click_lele_app_news";
    public static final String EVENT_CLICK_LELE_APP_ON_SITE_REGISTARTION = "click_lele_app_On_site_registration";
    public static final String EVENT_CLICK_LELE_APP_PRODUCT_INTRODUCTION = "click_lele_app_Product_introduction";
    public static final String EVENT_CLICK_LELE_APP_QA_CONSULT = "click_lele_app_qa_consult";
    public static final String EVENT_CLICK_LELE_APP_QR_SWITCH = "click_lele_app_QR_switch";
    public static final String EVENT_CLICK_LELE_APP_SILENCE_SWITCH_1 = "click_lele_app_silence_switch_1";
    public static final String EVENT_CLICK_LELE_APP_SILENCE_SWITCH_2 = "click_lele_app_silence_switch_2";
    public static final String EVENT_CLICK_LELE_APP_TEMPARATURE_ZONE = "click_lele_app_Temperature_zone";
    public static final String EVENT_CLICK_LELE_APP_TEMPERATURE_SWITCH = "click_lele_app_Temperature_switch";
    public static final String EVENT_CLICK_LELE_APP_TEMPERATURE_THRESHOLD = "click_lele_app_Temperature_threshold";
    public static final String EVENT_CLICK_LELE_APP_VISIT = "click_lele_app_visit";
    public static final String EVENT_CLICK_LELE_APP_VISTOR_SWITCH = "click_lele_app_visitor_switch";
    public static final String EVENT_CLICK_LELE_APP_WELCOME = "click_lele_app_Welcome";
    public static final String EVENT_LOAD_LELE_APP = "load_lelle_app";
    public static final String EVENT_LOAD_LELE_APP_ABNORMAL_TEMPERATURE = "load_lele_app_Abnormal_temperature";
    public static final String EVENT_LOAD_LELE_APP_ATTENDANCE_TIPS = "load_lele_app_Attendance_tips";
    public static final String EVENT_LOAD_LELE_APP_CAMERA_INITIALIZATION_FAILED = "load_lele_app_Camera_initialization_failed";
    public static final String EVENT_LOAD_LELE_APP_CONTENT_NOT_CONFIGURED = "load_lele_app_Content_not_configured";
    public static final String EVENT_LOAD_LELE_APP_DETAILS_REGISTER_MDI = "load_lele_app_details_register_MDI";
    public static final String EVENT_LOAD_LELE_APP_DETAILS_VISIT = "load_lele_app_details_visit";
    public static final String EVENT_LOAD_LELE_APP_HOME = "load_lele_app_home";
    public static final String EVENT_LOAD_LELE_APP_INITIALIZATION_FAILED = "load_lele_app_initialization_failed";
    public static final String EVENT_LOAD_LELE_APP_MASK_TIPS_TIMES = "load_lele_app_Mask_tips_times";
    public static final String EVENT_LOAD_LELE_APP_MEETING_ATTENDANCE = "load_lele_app_Meeting_attendance";
    public static final String EVENT_LOAD_LELE_APP_NETWORK_NOT_CONNECTED = "load_lele_app_Network_not_connected";
    public static final String EVENT_LOAD_LELE_APP_RECORDING_FAILED = "load_lele_app_Recording_on_failed";
    public static final String EVENT_LOAD_LELE_APP_REGISTRATION_ID_CARD = "load_lele_app_Registration_ID_card";
    public static final String EVENT_LOAD_LELE_APP_STANDBY = "load_lele_app_Standby";
    public static final String EVENT_LOAD_LELE_APP_STOP_RUNNING = "load_lele_app_Stop_running";
    public static final String EVENT_LOAD_LELE_APP_TEMPERATURE_PIC_SWITCH = "click_lele_app_Temperature_pic_switch";
    public static final String EVENT_LOAD_LELE_APP_TEMPERATURE_TIMES = "load_lele_app_temperature_Times";
    public static final String EVENT_LOAD_LELE_APP_VOICE_AUTHORIZATION_FAILED = "load_lele_app_Voice_authorization_failed";
    public static final String EVENT_QR_LELLE_APP = "QR_lele_app";
    public static final String EVENT_VOICE_LELE_APP_COMPANY = "voice_lele_app_company";
    public static final String EVENT_VOICE_LELE_APP_DETAILS_COMPANY = "voice_lele_app_details_company";
    public static final String EVENT_VOICE_LELE_APP_DETAILS_FACILITIES = "voice_lele_app_details_facilities";
    public static final String EVENT_VOICE_LELE_APP_DETAILS_INTERACTIVE_STORY = "voice_lele_app_details_Interactive_story";
    public static final String EVENT_VOICE_LELE_APP_DETAILS_PRODUCT_INTRODUCTION = "voice_lele_app_details_Product_introduction";
    public static final String EVENT_VOICE_LELE_APP_DETAILS_QA = "voice_lele_app_details_qa";
    public static final String EVENT_VOICE_LELE_APP_FACILITIES = "voice_lele_app_facilities";
    public static final String EVENT_VOICE_LELE_APP_NEWS = "voice_lele_app_news";
    public static final String EVENT_VOICE_LELE_APP_PRODUCT_INTRODUCTION = "voice_lele_app_Product_introduction";
    public static final String EVENT_VOICE_LELE_APP_QA_CONSULT = "voice_lele_app_qa_consult";
    public static final String EVENT_VOICE_LELE_APP_VISIT = "voice_lele_app_visit";
    public static final int INIT_STATE_GET_IP = 0;
    public static final int INIT_STATE_GET_PROPERTITY = 3;
    public static final int INIT_STATE_GET_USER = 1;
    public static final int INIT_STATE_IDLE = -1;
    public static final int INIT_STATE_INIT_EXCEPTION = 4;
    public static final int INIT_STATE_LONGIN_GET_TOKEN = 2;
    public static final NetConstant INSTANCE = new NetConstant();
    private static final String IP = "robot.csstrobot.com";
    private static boolean IS_GET_CODE_FROM_NET = false;
    private static boolean IS_OPEN_FULL_DUPLEX = true;
    public static final String MSG_SROS_AD_REFRESH = "MSG_SROS_AD_REFRESH";
    public static final String MSG_SROS_ARCCODE_UPDATE = "MSG_SROS_ARCCODE_UPDATE";
    public static final String MSG_SROS_CHANGE_GREETINGS_DISTANTLY = "MSG_SROS_CHANGE_GREETINGS_DISTANTLY";
    public static final String MSG_SROS_CHANGE_GREETINGS_NEARBY = "MSG_SROS_CHANGE_GREETINGS_NEARBY";
    public static final String MSG_SROS_CHANGE_HOME_NEWS = "MSG_SROS_CHANGE_HOME_NEWS";
    public static final String MSG_SROS_CHANGE_HOTEL_ACTIVITY = "MSG_SROS_CHANGE_HOTEL_ACTIVITY";
    public static final String MSG_SROS_CHANGE_TACTICS = "MSG_SROS_CHANGE_TACTICS";
    public static final String MSG_SROS_DELETE_EMPLOEE = "MSG_SROS_DELETE_EMPLOEE";
    public static final String MSG_SROS_DELETE_VERIFY = "MSG_SROS_DELETE_VERIFY";
    public static final String MSG_SROS_DELETE_VISITOR = "MSG_SROS_DELETE_VISITOR";
    public static final String MSG_SROS_DOWNLOAD_MAP = "MSG_SROS_DOWNLOAD_MAP";
    public static final String MSG_SROS_GET_TOKEN = "MSG_SROS_GET_TOKEN";
    public static final String MSG_SROS_IMAGE_BASE_URL = "MSG_SROS_IMAGE_BASE_URL";
    public static final String MSG_SROS_PATROL_TASK_REFRESH = "MSG_SROS_PATROL_TASK_REFRESH";
    public static final String MSG_SROS_SELF_MAP_UPDATE = "MSG_SROS_SELF_MAP_UPDATE";
    public static final String MSG_SROS_SET_PASSWORD = "MSG_SROS_SET_PASSWORD";
    public static final String MSG_SROS_SET_PLATFORM_BASE_URL = "MSG_SROS_SET_PLATFORM_BASE_URL";
    public static final String MSG_SROS_SET_TOKEN = "MSG_SROS_SET_TOKEN";
    public static final String MSG_SROS_SET_USER_NAME = "MSG_SROS_SET_USER_NAME";
    public static final String MSG_SROS_TCP_ONLINE = "MSG_SROS_TCP_ONLINE";
    public static final String MSG_SROS_UPDATE_EMPLOEE = "MSG_SROS_UPDATE_EMPLOEE";
    public static final String MSG_SROS_UPDATE_PROPERTY = "MSG_SROS_UPDATE_PROPERTY";
    public static final String MSG_SROS_UPDATE_VERIFY = "MSG_SROS_UPDATE_VERIFY";
    public static final String MSG_SROS_UPDATE_VISITOR = "MSG_SROS_UPDATE_VISITOR";
    public static final String MSG_SROS_UPLOAD_LOG = "MSG_SROS_UPLOAD_LOG";
    public static final String MSG_SROS_UPLOAD_MAP = "MSG_SROS_UPLOAD_MAP";
    public static final String MSG_SROS_VERSION_UPDATE = "MSG_SROS_VERSION_UPDATE";
    public static final String MSG_SROS_VIDEO_ACTIVATE = "MSG_SROS_VIDEO_ACTIVATE";
    public static final String MSG_SROS_VIDEO_CANCEL = "MSG_SROS_VIDEO_CANCEL";
    public static final String MSG_SROS_VOICE_CODE_ACTIVATE = "MSG_SROS_VOICE_CODE_ACTIVATE";
    public static final String MSG_SROS_VOICE_CODE_CANCLE = "MSG_SROS_VOICE_CODE_CANCLE";
    public static final String PAGE_ID_COMPANY = "2";
    public static final String PAGE_ID_CONSULTATION = "4";
    public static final String PAGE_ID_DIALOG_SHOW = "7";
    public static final String PAGE_ID_HOME = "0";
    public static final String PAGE_ID_IDLE = "-1";
    public static final String PAGE_ID_NAV_DETAIL_INTRODUCE = "6";
    public static final String PAGE_ID_NAV_GUIDE_INTRODUCE = "5";
    public static final String PAGE_ID_PRODUCT_INTRODUCE = "8";
    public static final String PAGE_ID_PUBLIC_UTILITIES = "3";
    public static final String PAGE_ID_RCMD = "9";
    public static final String PAGE_ID_VISITOR = "1";
    public static final int PORT = 28888;
    public static final String ROBOT_ID_INPUT_LELE = "YHPR1151A001SZGM0111202XXXXX";
    public static final String ROBOT_ID_INPUT_WELCOM = "YHPR1150B002SZGM0208202XXXXX";
    public static final String ROBOT_ID_INPUT_WELCOMPATROL = "YHPR1151C001SZGM0111202XXXXX";
    private static final String ROBOT_NUM_DISINFECT = "ME1252C";
    private static final String ROBOT_NUM_GATEKEEPER = "ZZPR1120B";
    private static final String ROBOT_NUM_LELE = "PR1151A001";
    private static final String ROBOT_NUM_MEAL_DELIVERY = "DE1230B";
    private static final String ROBOT_NUM_MINI_OH_DISINFECT = "ME1253B";
    private static final String ROBOT_NUM_OH_DISINFECT = "ME1253A";
    private static final String ROBOT_NUM_WELCOM = "PR1150B002";
    private static final String ROBOT_NUM_WELCOME_PATROL = "PR1151C001";
    private static int ROBOT_TYPE = AppSpUtil.getInstance().getRobotRealType();
    public static final int ROBOT_TYPE_GATEKEEPER = 3;
    public static final int ROBOT_TYPE_IDLE = -1;
    public static final int ROBOT_TYPE_LELE = 4;
    public static final int ROBOT_TYPE_MEAL_DELIVERY = 8;
    public static final int ROBOT_TYPE_MINI_OH_SPRAY = 23;
    public static final int ROBOT_TYPE_OH_SPRAY = 22;
    public static final int ROBOT_TYPE_PATROL = 0;
    public static final int ROBOT_TYPE_SPRAY = 6;
    public static final int ROBOT_TYPE_WELCOM = 1;
    public static final int ROBOT_TYPE_WELCOME_PATROL = 5;
    public static final int ROBOT_TYPE_XUNGENG = 2;
    public static final String SP_CONFIG_ROBOT_ID = "SP_CONFIG_ROBOT_ID";
    public static final String SP_CONFIG_ROBOT_NUM = "SP_CONFIG_ROBOT_NUM";
    public static final String SP_CONFIG_ROBOT_TYPE = "SP_CONFIG_ROBOT_TYPE";
    public static final String SP_CONFIG_WUHAN_BASEURL = "SP_CONFIG_WUHAN_BASEURL";
    public static final int SROS_SEND_STATUS_INTERVAL_TIME = 3000;
    public static final String TAG = "NETWORK_TAG";
    public static final int TCP_SERVER_PORT = 28889;
    public static final String TEST_AUDIO_BASE_URL = "https://mrad-reqtask-test.brandwisdom.cn/";
    private static String URL_DEFAULT_SEMANTIC = (HttpConstant.HTTP_URL + AppSpUtil.getInstance().getVoiceIp() + ":20164");
    private static final String URL_DEFAULT_SEMANTIC_INTENT;
    private static final String URL_DEFAULT_TOPIC = (HttpConstant.HTTP_URL + AppSpUtil.getInstance().getVoiceIp() + ":20166");
    public static final String WATER_GET_SIM_INFO = "http://192.168.10.10:9001/api/get_router_sim_info";
    public static final String YUNJI_DEFAULT_URL_DEV = "https://dev-jhai-zz.yunjiai.cn";
    public static final String YUNJI_DEFAULT_URL_PRODUCT = "https://jhai-zz.yunjiai.cn";
    public static final String YUNJI_DEFAULT_URL_TEST = "https://test-jhai-zz.yunjiai.cn";
    public static final String YUNJI_DEVICE_REGIESTER_URL_DEV = "https://mrad-reqtask-test.brandwisdom.cn/";
    public static final String YUNJI_DEVICE_REGIESTER_URL_PRODUCT = "https://mrad-reqtask.brandwisdom.cn/";
    public static final String YUN_JI_OPEN_API_BASE_URL = "https://openapi.yunjichina.com.cn/";

    private NetConstant() {
    }

    static {
        String voiceIp = AppSpUtil.getInstance().getVoiceIp();
        if (voiceIp == null) {
            voiceIp = "https://jhai-zz.yunjiai.cn";
        }
        URL_DEFAULT_SEMANTIC_INTENT = voiceIp;
    }

    public final String getURL_DEFAULT_TOPIC() {
        return URL_DEFAULT_TOPIC;
    }

    public final String getURL_DEFAULT_SEMANTIC() {
        return URL_DEFAULT_SEMANTIC;
    }

    public final void setURL_DEFAULT_SEMANTIC(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        URL_DEFAULT_SEMANTIC = str;
    }

    public final String getURL_DEFAULT_SEMANTIC_INTENT() {
        return URL_DEFAULT_SEMANTIC_INTENT;
    }

    public final String getROBOT_ID_INPUT() {
        int i = ROBOT_TYPE;
        if (i == 1) {
            return ROBOT_ID_INPUT_WELCOM;
        }
        return i == 4 ? ROBOT_ID_INPUT_LELE : ROBOT_NUM_LELE;
    }

    public final int getROBOT_TYPE() {
        return ROBOT_TYPE;
    }

    public final void setROBOT_TYPE(int i) {
        ROBOT_TYPE = i;
    }

    public final String getROBOT_NUM() {
        int i = ROBOT_TYPE;
        if (i == 1) {
            return ROBOT_NUM_WELCOM;
        }
        if (i == 3) {
            return "ZZPR1120B";
        }
        if (i == 5) {
            return ROBOT_NUM_WELCOME_PATROL;
        }
        if (i == 4) {
            return ROBOT_NUM_LELE;
        }
        if (i == 6) {
            return "ME1252C";
        }
        if (i == 8) {
            return "DE1230B";
        }
        if (i == 22) {
            return "ME1253A";
        }
        return i == 23 ? "ME1253B" : ROBOT_NUM_LELE;
    }

    public final String getAUTHORIZATION_CODE_PATH() {
        return AUTHORIZATION_CODE_PATH;
    }

    public final boolean getIS_GET_CODE_FROM_NET() {
        return IS_GET_CODE_FROM_NET;
    }

    public final void setIS_GET_CODE_FROM_NET(boolean z) {
        IS_GET_CODE_FROM_NET = z;
    }

    public final boolean getIS_OPEN_FULL_DUPLEX() {
        return IS_OPEN_FULL_DUPLEX;
    }

    public final void setIS_OPEN_FULL_DUPLEX(boolean z) {
        IS_OPEN_FULL_DUPLEX = z;
    }

    public final String getIP() {
        return IP;
    }

    public final String getDEFAULT_SERVICE_URL() {
        return DEFAULT_SERVICE_URL;
    }

    public final String getDIAGNOSIS_IP_TEST() {
        return DIAGNOSIS_IP_TEST;
    }

    public final String getDIAGNOSIS_IP_PRODUCTION() {
        return DIAGNOSIS_IP_PRODUCTION;
    }
}
