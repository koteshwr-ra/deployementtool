package io.realm;

import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo extends ColumnInfo {
    long nameColKey;
    long permissionsColKey;

    io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(2);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
        this.permissionsColKey = addColumnDetails("permissions", "permissions", objectSchemaInfo);
    }

    io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo = (io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo) columnInfo;
        io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo2 = (io_realm_sync_permissions_ClassPermissionsRealmProxy$ClassPermissionsColumnInfo) columnInfo2;
        io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo2.nameColKey = io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo.nameColKey;
        io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo2.permissionsColKey = io_realm_sync_permissions_classpermissionsrealmproxy_classpermissionscolumninfo.permissionsColKey;
    }
}
