package io.realm;

import androidx.core.app.NotificationCompat;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo extends ColumnInfo {
    long createdAtColKey;
    long errorMessageColKey;
    long expiresAtColKey;
    long matchesPropertyColKey;
    long nameColKey;
    long queryColKey;
    long queryParseCounterColKey;
    long statusColKey;
    long timeToLiveColKey;
    long updatedAtColKey;

    io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(10);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_SubscriptionRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
        this.statusColKey = addColumnDetails(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, objectSchemaInfo);
        this.errorMessageColKey = addColumnDetails("errorMessage", "error_message", objectSchemaInfo);
        this.matchesPropertyColKey = addColumnDetails("matchesProperty", "matches_property", objectSchemaInfo);
        this.queryColKey = addColumnDetails("query", "query", objectSchemaInfo);
        this.queryParseCounterColKey = addColumnDetails("queryParseCounter", "query_parse_counter", objectSchemaInfo);
        this.createdAtColKey = addColumnDetails("createdAt", "created_at", objectSchemaInfo);
        this.updatedAtColKey = addColumnDetails("updatedAt", "updated_at", objectSchemaInfo);
        this.expiresAtColKey = addColumnDetails("expiresAt", "expires_at", objectSchemaInfo);
        this.timeToLiveColKey = addColumnDetails("timeToLive", "time_to_live", objectSchemaInfo);
    }

    io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo = (io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo) columnInfo;
        io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2 = (io_realm_sync_SubscriptionRealmProxy$SubscriptionColumnInfo) columnInfo2;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.nameColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.nameColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.statusColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.statusColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.errorMessageColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.errorMessageColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.matchesPropertyColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.matchesPropertyColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.queryColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.queryColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.queryParseCounterColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.queryParseCounterColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.createdAtColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.createdAtColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.updatedAtColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.updatedAtColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.expiresAtColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.expiresAtColKey;
        io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo2.timeToLiveColKey = io_realm_sync_subscriptionrealmproxy_subscriptioncolumninfo.timeToLiveColKey;
    }
}
