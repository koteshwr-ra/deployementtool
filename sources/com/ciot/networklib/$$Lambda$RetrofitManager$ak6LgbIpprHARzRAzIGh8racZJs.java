package com.ciot.networklib;

import com.example.sroslibrary.bean.RobotStatusResponse;
import io.reactivex.functions.Consumer;

/* renamed from: com.ciot.networklib.-$$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs implements Consumer {
    public static final /* synthetic */ $$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs INSTANCE = new $$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs();

    private /* synthetic */ $$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs() {
    }

    public final void accept(Object obj) {
        RetrofitManager.m64getRobotIsLock$lambda23((RobotStatusResponse) obj);
    }
}
