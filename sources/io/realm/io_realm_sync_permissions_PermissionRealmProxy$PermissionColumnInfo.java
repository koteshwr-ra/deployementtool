package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;

final class io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo extends ColumnInfo {
    long canCreateColKey;
    long canDeleteColKey;
    long canModifySchemaColKey;
    long canQueryColKey;
    long canReadColKey;
    long canSetPermissionsColKey;
    long canUpdateColKey;
    long roleColKey;

    io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo(OsSchemaInfo osSchemaInfo) {
        super(8);
        OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(io_realm_sync_permissions_PermissionRealmProxy$ClassNameHelper.INTERNAL_CLASS_NAME);
        this.roleColKey = addColumnDetails("role", "role", objectSchemaInfo);
        this.canReadColKey = addColumnDetails("canRead", "canRead", objectSchemaInfo);
        this.canUpdateColKey = addColumnDetails("canUpdate", "canUpdate", objectSchemaInfo);
        this.canDeleteColKey = addColumnDetails("canDelete", "canDelete", objectSchemaInfo);
        this.canSetPermissionsColKey = addColumnDetails("canSetPermissions", "canSetPermissions", objectSchemaInfo);
        this.canQueryColKey = addColumnDetails("canQuery", "canQuery", objectSchemaInfo);
        this.canCreateColKey = addColumnDetails("canCreate", "canCreate", objectSchemaInfo);
        this.canModifySchemaColKey = addColumnDetails("canModifySchema", "canModifySchema", objectSchemaInfo);
    }

    io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo(ColumnInfo columnInfo, boolean z) {
        super(columnInfo, z);
        copy(columnInfo, this);
    }

    /* access modifiers changed from: protected */
    public final ColumnInfo copy(boolean z) {
        return new io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo(this, z);
    }

    /* access modifiers changed from: protected */
    public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
        io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo = (io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo) columnInfo;
        io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2 = (io_realm_sync_permissions_PermissionRealmProxy$PermissionColumnInfo) columnInfo2;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.roleColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.roleColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canReadColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canReadColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canUpdateColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canUpdateColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canDeleteColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canDeleteColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canSetPermissionsColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canSetPermissionsColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canQueryColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canQueryColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canCreateColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canCreateColKey;
        io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo2.canModifySchemaColKey = io_realm_sync_permissions_permissionrealmproxy_permissioncolumninfo.canModifySchemaColKey;
    }
}
