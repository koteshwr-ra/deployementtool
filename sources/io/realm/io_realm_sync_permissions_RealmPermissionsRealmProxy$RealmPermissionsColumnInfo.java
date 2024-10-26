package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo extends ColumnInfo {
    long idColKey;
    long permissionsColKey;

    io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(2);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_permissions_RealmPermissionsRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        this.permissionsColKey = addColumnDetails("permissions", "permissions", objectSchemaInfo);
    }

    io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo = (io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo) columnInfo;
        io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo2 = (io_realm_sync_permissions_RealmPermissionsRealmProxy$RealmPermissionsColumnInfo) columnInfo2;
        io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo2.idColKey = io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo.idColKey;
        io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo2.permissionsColKey = io_realm_sync_permissions_realmpermissionsrealmproxy_realmpermissionscolumninfo.permissionsColKey;
    }
}
