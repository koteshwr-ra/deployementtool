package io.realm.internal;

import io.realm.RealmChangeListener;
import io.realm.internal.ObservableCollection;
import io.realm.internal.core.DescriptorOrdering;
import io.realm.internal.sync.OsSubscription;
import io.realm.internal.sync.SubscriptionAction;

public class SubscriptionAwareOsResults extends OsResults {
    /* access modifiers changed from: private */
    public boolean collectionChanged = false;
    /* access modifiers changed from: private */
    public long delayedNotificationPtr = 0;
    private boolean firstCallback = true;
    private OsSubscription subscription = null;
    /* access modifiers changed from: private */
    public boolean subscriptionChanged;

    public static SubscriptionAwareOsResults createFromQuery(OsSharedRealm osSharedRealm, TableQuery tableQuery, DescriptorOrdering descriptorOrdering, SubscriptionAction subscriptionAction) {
        tableQuery.validateQuery();
        return new SubscriptionAwareOsResults(osSharedRealm, tableQuery.getTable(), nativeCreateResults(osSharedRealm.getNativePtr(), tableQuery.getNativePtr(), descriptorOrdering.getNativePtr()), subscriptionAction);
    }

    SubscriptionAwareOsResults(OsSharedRealm osSharedRealm, Table table, long j, SubscriptionAction subscriptionAction) {
        super(osSharedRealm, table, j);
        OsSubscription osSubscription = new OsSubscription(this, subscriptionAction);
        this.subscription = osSubscription;
        osSubscription.addChangeListener(new RealmChangeListener<OsSubscription>() {
            public void onChange(OsSubscription osSubscription) {
                boolean unused = SubscriptionAwareOsResults.this.subscriptionChanged = true;
            }
        });
        RealmNotifier realmNotifier = osSharedRealm.realmNotifier;
        realmNotifier.addBeginSendingNotificationsCallback(new Runnable() {
            public void run() {
                boolean unused = SubscriptionAwareOsResults.this.subscriptionChanged = false;
                boolean unused2 = SubscriptionAwareOsResults.this.collectionChanged = false;
                long unused3 = SubscriptionAwareOsResults.this.delayedNotificationPtr = 0;
            }
        });
        realmNotifier.addFinishedSendingNotificationsCallback(new Runnable() {
            public void run() {
                if (SubscriptionAwareOsResults.this.collectionChanged || SubscriptionAwareOsResults.this.subscriptionChanged) {
                    SubscriptionAwareOsResults.this.triggerDelayedChangeListener();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void triggerDelayedChangeListener() {
        OsCollectionChangeSet osCollectionChangeSet;
        OsSubscription osSubscription = this.subscriptionChanged ? this.subscription : null;
        if (this.delayedNotificationPtr != 0 || osSubscription == null || this.firstCallback || osSubscription.getState() == OsSubscription.SubscriptionState.ERROR || osSubscription.getState() == OsSubscription.SubscriptionState.COMPLETE) {
            if (this.delayedNotificationPtr == 0) {
                osCollectionChangeSet = new EmptyLoadChangeSet(osSubscription, this.firstCallback, true);
            } else {
                osCollectionChangeSet = new OsCollectionChangeSet(this.delayedNotificationPtr, this.firstCallback, osSubscription, true);
            }
            if (!osCollectionChangeSet.isEmpty() || !isLoaded()) {
                this.loaded = true;
                this.firstCallback = false;
                this.observerPairs.foreach(new ObservableCollection.Callback(osCollectionChangeSet));
            }
        }
    }

    public void notifyChangeListeners(long j) {
        this.collectionChanged = true;
        this.delayedNotificationPtr = j;
    }
}
