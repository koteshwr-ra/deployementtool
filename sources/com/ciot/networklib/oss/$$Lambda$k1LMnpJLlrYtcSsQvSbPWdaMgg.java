package com.ciot.networklib.oss;

import java.io.File;
import java.io.FileFilter;

/* renamed from: com.ciot.networklib.oss.-$$Lambda$k1LMnpJLlrYtcSsQvSbPW-daMgg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$k1LMnpJLlrYtcSsQvSbPWdaMgg implements FileFilter {
    public static final /* synthetic */ $$Lambda$k1LMnpJLlrYtcSsQvSbPWdaMgg INSTANCE = new $$Lambda$k1LMnpJLlrYtcSsQvSbPWdaMgg();

    private /* synthetic */ $$Lambda$k1LMnpJLlrYtcSsQvSbPWdaMgg() {
    }

    public final boolean accept(File file) {
        return file.isDirectory();
    }
}
