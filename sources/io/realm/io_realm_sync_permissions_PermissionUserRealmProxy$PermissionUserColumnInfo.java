package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo extends ColumnInfo {
    long idColKey;
    long roleColKey;

    io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(2);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_permissions_PermissionUserRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        this.roleColKey = addColumnDetails("role", "role", objectSchemaInfo);
        addBacklinkDetails(osSchemaInfo, "roles", io_realm_sync_permissions_RoleRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME, "members");
    }

    io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo = (io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo) columnInfo;
        io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo2 = (io_realm_sync_permissions_PermissionUserRealmProxy$PermissionUserColumnInfo) columnInfo2;
        io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo2.idColKey = io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo.idColKey;
        io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo2.roleColKey = io_realm_sync_permissions_permissionuserrealmproxy_permissionusercolumninfo.roleColKey;
    }
}
