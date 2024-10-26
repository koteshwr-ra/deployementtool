package io.realm.internal.sync;

import kotlin.jvm.internal.LongCompanionObject;

public class SubscriptionAction {
    public static final SubscriptionAction ANONYMOUS_SUBSCRIPTION = new SubscriptionAction("", LongCompanionObject.MAX_VALUE, false);
    public static final SubscriptionAction NO_SUBSCRIPTION = new SubscriptionAction((String) null, 0, false);
    private final String subscriptionName;
    private final long timeToLiveMs;
    private final boolean update;

    public static SubscriptionAction create(String str, long j) {
        return new SubscriptionAction(str, j, false);
    }

    public static SubscriptionAction update(String str, long j) {
        return new SubscriptionAction(str, j, true);
    }

    public SubscriptionAction(String str, long j, boolean z) {
        this.subscriptionName = str;
        this.timeToLiveMs = j;
        this.update = z;
    }

    public boolean shouldCreateSubscriptions() {
        return this.subscriptionName != null;
    }

    public String getName() {
        return this.subscriptionName;
    }

    public long getTimeToLiveMs() {
        return this.timeToLiveMs;
    }

    public boolean isUpdate() {
        return this.update;
    }
}
