package io.realm;

import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo extends ColumnInfo {
    long membersColKey;
    long nameColKey;

    io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(2);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_permissions_RoleRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
        this.membersColKey = addColumnDetails("members", "members", objectSchemaInfo);
    }

    io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo io_realm_sync_permissions_rolerealmproxy_rolecolumninfo = (io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo) columnInfo;
        io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo io_realm_sync_permissions_rolerealmproxy_rolecolumninfo2 = (io_realm_sync_permissions_RoleRealmProxy$RoleColumnInfo) columnInfo2;
        io_realm_sync_permissions_rolerealmproxy_rolecolumninfo2.nameColKey = io_realm_sync_permissions_rolerealmproxy_rolecolumninfo.nameColKey;
        io_realm_sync_permissions_rolerealmproxy_rolecolumninfo2.membersColKey = io_realm_sync_permissions_rolerealmproxy_rolecolumninfo.membersColKey;
    }
}
