package com.ciot.networklib.oss;

import com.blankj.utilcode.util.FileUtils;
import com.ciot.base.constant.FileConstant;
import io.reactivex.functions.Predicate;

/* renamed from: com.ciot.networklib.oss.-$$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX-5JZw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX5JZw implements Predicate {
    public static final /* synthetic */ $$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX5JZw INSTANCE = new $$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX5JZw();

    private /* synthetic */ $$Lambda$OssHelper$hZdvfukraedeKNJh_hcscX5JZw() {
    }

    public final boolean test(Object obj) {
        return FileUtils.isFileExists(FileConstant.APP_PATH);
    }
}
