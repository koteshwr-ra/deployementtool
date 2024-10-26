package com.github.moduth.blockcanary;

import android.content.Context;
import com.ciot.base.constant.NetConstant;
import com.github.moduth.blockcanary.internal.BlockInfo;
import java.io.File;
import java.util.List;

public class BlockCanaryContext {
    private static Context sApplicationContext;
    private static BlockCanaryContext sInstance;

    public List<String> concernPackages() {
        return null;
    }

    public boolean deleteFilesInWhiteList() {
        return false;
    }

    public boolean displayNotification() {
        return false;
    }

    public boolean filterNonConcernStack() {
        return false;
    }

    public void onBlock(Context context, BlockInfo blockInfo) {
    }

    public int provideBlockThreshold() {
        return 1000;
    }

    public int provideMonitorDuration() {
        return 99999;
    }

    public String provideNetworkType() {
        return "UNKNOWN";
    }

    public String providePath() {
        return "/blockcanary/";
    }

    public String provideQualifier() {
        return "Unspecified";
    }

    public String provideUid() {
        return NetConstant.PAGE_ID_HOME;
    }

    public List<String> provideWhiteList() {
        return null;
    }

    public boolean stopWhenDebugging() {
        return true;
    }

    public boolean zip(File[] fileArr, File file) {
        return false;
    }

    static void init(Context context, BlockCanaryContext blockCanaryContext) {
        sApplicationContext = context;
        sInstance = blockCanaryContext;
    }

    public static BlockCanaryContext get() {
        BlockCanaryContext blockCanaryContext = sInstance;
        if (blockCanaryContext != null) {
            return blockCanaryContext;
        }
        throw new RuntimeException("BlockCanaryContext not init");
    }

    public Context provideContext() {
        return sApplicationContext;
    }

    public int provideDumpInterval() {
        return provideBlockThreshold();
    }

    public void upload(File file) {
        throw new UnsupportedOperationException();
    }
}
