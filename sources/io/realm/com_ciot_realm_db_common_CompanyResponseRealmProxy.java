package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.Contact;
import com.ciot.realm.db.common.LocationBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_common_ContactRealmProxy;
import io.realm.com_ciot_realm_db_common_LocationBeanRealmProxy;
import io.realm.exceptions.RealmException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class com_ciot_realm_db_common_CompanyResponseRealmProxy extends CompanyResponse implements RealmObjectProxy, com_ciot_realm_db_common_CompanyResponseRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CompanyResponseColumnInfo columnInfo;
    private ProxyState<CompanyResponse> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "CompanyResponse";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class CompanyResponseColumnInfo extends ColumnInfo {
        long addressColKey;
        long areaColKey;
        long contactColKey;
        long descriptionColKey;
        long idColKey;
        long locationColKey;
        long nameColKey;
        long pinyinColKey;
        long projectColKey;
        long qrcodeColKey;
        long xColKey;
        long yColKey;

        CompanyResponseColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(12);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.pinyinColKey = addColumnDetails("pinyin", "pinyin", objectSchemaInfo);
            this.contactColKey = addColumnDetails("contact", "contact", objectSchemaInfo);
            this.areaColKey = addColumnDetails("area", "area", objectSchemaInfo);
            this.addressColKey = addColumnDetails("address", "address", objectSchemaInfo);
            this.qrcodeColKey = addColumnDetails("qrcode", "qrcode", objectSchemaInfo);
            this.projectColKey = addColumnDetails("project", "project", objectSchemaInfo);
            this.xColKey = addColumnDetails("x", "x", objectSchemaInfo);
            this.yColKey = addColumnDetails("y", "y", objectSchemaInfo);
            this.locationColKey = addColumnDetails(RequestParameters.SUBRESOURCE_LOCATION, RequestParameters.SUBRESOURCE_LOCATION, objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        CompanyResponseColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new CompanyResponseColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            CompanyResponseColumnInfo companyResponseColumnInfo = (CompanyResponseColumnInfo) columnInfo;
            CompanyResponseColumnInfo companyResponseColumnInfo2 = (CompanyResponseColumnInfo) columnInfo2;
            companyResponseColumnInfo2.nameColKey = companyResponseColumnInfo.nameColKey;
            companyResponseColumnInfo2.pinyinColKey = companyResponseColumnInfo.pinyinColKey;
            companyResponseColumnInfo2.contactColKey = companyResponseColumnInfo.contactColKey;
            companyResponseColumnInfo2.areaColKey = companyResponseColumnInfo.areaColKey;
            companyResponseColumnInfo2.addressColKey = companyResponseColumnInfo.addressColKey;
            companyResponseColumnInfo2.qrcodeColKey = companyResponseColumnInfo.qrcodeColKey;
            companyResponseColumnInfo2.projectColKey = companyResponseColumnInfo.projectColKey;
            companyResponseColumnInfo2.xColKey = companyResponseColumnInfo.xColKey;
            companyResponseColumnInfo2.yColKey = companyResponseColumnInfo.yColKey;
            companyResponseColumnInfo2.locationColKey = companyResponseColumnInfo.locationColKey;
            companyResponseColumnInfo2.descriptionColKey = companyResponseColumnInfo.descriptionColKey;
            companyResponseColumnInfo2.idColKey = companyResponseColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_common_CompanyResponseRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CompanyResponseColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<CompanyResponse> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameColKey);
    }

    public void realmSet$name(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.nameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.nameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$pinyin() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pinyinColKey);
    }

    public void realmSet$pinyin(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pinyinColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pinyinColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pinyinColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pinyinColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Contact realmGet$contact() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.contactColKey)) {
            return null;
        }
        return (Contact) this.proxyState.getRealm$realm().get(Contact.class, this.proxyState.getRow$realm().getLink(this.columnInfo.contactColKey), false, Collections.emptyList());
    }

    public void realmSet$contact(Contact contact) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (contact == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.contactColKey);
                return;
            }
            this.proxyState.checkValidObject(contact);
            this.proxyState.getRow$realm().setLink(this.columnInfo.contactColKey, ((RealmObjectProxy) contact).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("contact")) {
            if (contact != null && !RealmObject.isManaged(contact)) {
                contact = (Contact) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(contact, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (contact == null) {
                row$realm.nullifyLink(this.columnInfo.contactColKey);
                return;
            }
            this.proxyState.checkValidObject(contact);
            row$realm.getTable().setLink(this.columnInfo.contactColKey, row$realm.getObjectKey(), ((RealmObjectProxy) contact).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$area() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.areaColKey);
    }

    public void realmSet$area(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.areaColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.areaColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.areaColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.areaColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$address() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.addressColKey);
    }

    public void realmSet$address(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.addressColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.addressColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.addressColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.addressColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$qrcode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.qrcodeColKey);
    }

    public void realmSet$qrcode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.qrcodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.qrcodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.qrcodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.qrcodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$project() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.projectColKey);
    }

    public void realmSet$project(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.projectColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.projectColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.projectColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.projectColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public double realmGet$x() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.xColKey);
    }

    public void realmSet$x(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.xColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.xColKey, row$realm.getObjectKey(), d, true);
        }
    }

    public double realmGet$y() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.yColKey);
    }

    public void realmSet$y(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.yColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.yColKey, row$realm.getObjectKey(), d, true);
        }
    }

    public LocationBean realmGet$location() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.locationColKey)) {
            return null;
        }
        return (LocationBean) this.proxyState.getRealm$realm().get(LocationBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.locationColKey), false, Collections.emptyList());
    }

    public void realmSet$location(LocationBean locationBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (locationBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.locationColKey);
                return;
            }
            this.proxyState.checkValidObject(locationBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.locationColKey, ((RealmObjectProxy) locationBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains(RequestParameters.SUBRESOURCE_LOCATION)) {
            if (locationBean != null && !RealmObject.isManaged(locationBean)) {
                locationBean = (LocationBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(locationBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (locationBean == null) {
                row$realm.nullifyLink(this.columnInfo.locationColKey);
                return;
            }
            this.proxyState.checkValidObject(locationBean);
            row$realm.getTable().setLink(this.columnInfo.locationColKey, row$realm.getObjectKey(), ((RealmObjectProxy) locationBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$description() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.descriptionColKey);
    }

    public void realmSet$description(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.descriptionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.descriptionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.descriptionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.descriptionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idColKey);
    }

    public void realmSet$id(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 12, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("pinyin", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("contact", RealmFieldType.OBJECT, com_ciot_realm_db_common_ContactRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("area", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("address", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("qrcode", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("project", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("x", RealmFieldType.DOUBLE, false, false, true);
        builder3.addPersistedProperty("y", RealmFieldType.DOUBLE, false, false, true);
        builder.addPersistedLinkProperty(RequestParameters.SUBRESOURCE_LOCATION, RealmFieldType.OBJECT, com_ciot_realm_db_common_LocationBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder4 = builder;
        builder4.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CompanyResponseColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new CompanyResponseColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.CompanyResponse createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 2
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r3 = com.ciot.realm.db.common.CompanyResponse.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r5 = com.ciot.realm.db.common.CompanyResponse.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy$CompanyResponseColumnInfo r4 = (io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy.CompanyResponseColumnInfo) r4
            long r4 = r4.idColKey
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x002a
            long r4 = r3.findFirstNull(r4)
            goto L_0x0032
        L_0x002a:
            java.lang.String r6 = r14.getString(r1)
            long r4 = r3.findFirstString(r4, r6)
        L_0x0032:
            r6 = -1
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0066
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r6 = io.realm.BaseRealm.objectContext
            java.lang.Object r6 = r6.get()
            io.realm.BaseRealm$RealmObjectContext r6 = (io.realm.BaseRealm.RealmObjectContext) r6
            io.realm.internal.UncheckedRow r9 = r3.getUncheckedRow(r4)     // Catch:{ all -> 0x0061 }
            io.realm.RealmSchema r3 = r13.getSchema()     // Catch:{ all -> 0x0061 }
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r4 = com.ciot.realm.db.common.CompanyResponse.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy r3 = new io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy     // Catch:{ all -> 0x0061 }
            r3.<init>()     // Catch:{ all -> 0x0061 }
            r6.clear()
            goto L_0x0067
        L_0x0061:
            r13 = move-exception
            r6.clear()
            throw r13
        L_0x0066:
            r3 = r2
        L_0x0067:
            java.lang.String r4 = "location"
            java.lang.String r5 = "contact"
            if (r3 != 0) goto L_0x00ac
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x0076
            r0.add(r5)
        L_0x0076:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x007f
            r0.add(r4)
        L_0x007f:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00a4
            boolean r3 = r14.isNull(r1)
            r6 = 1
            if (r3 == 0) goto L_0x0096
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r1 = com.ciot.realm.db.common.CompanyResponse.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r6, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy r3 = (io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy) r3
            goto L_0x00ac
        L_0x0096:
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r3 = com.ciot.realm.db.common.CompanyResponse.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r6, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy r3 = (io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy) r3
            goto L_0x00ac
        L_0x00a4:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00ac:
            r0 = r3
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxyInterface) r0
            java.lang.String r1 = "name"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x00c8
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x00c1
            r0.realmSet$name(r2)
            goto L_0x00c8
        L_0x00c1:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$name(r1)
        L_0x00c8:
            java.lang.String r1 = "pinyin"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x00e1
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x00da
            r0.realmSet$pinyin(r2)
            goto L_0x00e1
        L_0x00da:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$pinyin(r1)
        L_0x00e1:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x00fc
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x00f1
            r0.realmSet$contact(r2)
            goto L_0x00fc
        L_0x00f1:
            org.json.JSONObject r1 = r14.getJSONObject(r5)
            com.ciot.realm.db.common.Contact r1 = io.realm.com_ciot_realm_db_common_ContactRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$contact(r1)
        L_0x00fc:
            java.lang.String r1 = "area"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0115
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x010e
            r0.realmSet$area(r2)
            goto L_0x0115
        L_0x010e:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$area(r1)
        L_0x0115:
            java.lang.String r1 = "address"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x012e
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x0127
            r0.realmSet$address(r2)
            goto L_0x012e
        L_0x0127:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$address(r1)
        L_0x012e:
            java.lang.String r1 = "qrcode"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0147
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x0140
            r0.realmSet$qrcode(r2)
            goto L_0x0147
        L_0x0140:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$qrcode(r1)
        L_0x0147:
            java.lang.String r1 = "project"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0160
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x0159
            r0.realmSet$project(r2)
            goto L_0x0160
        L_0x0159:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$project(r1)
        L_0x0160:
            java.lang.String r1 = "x"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x017e
            boolean r5 = r14.isNull(r1)
            if (r5 != 0) goto L_0x0176
            double r5 = r14.getDouble(r1)
            r0.realmSet$x(r5)
            goto L_0x017e
        L_0x0176:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'x' to null."
            r13.<init>(r14)
            throw r13
        L_0x017e:
            java.lang.String r1 = "y"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x019c
            boolean r5 = r14.isNull(r1)
            if (r5 != 0) goto L_0x0194
            double r5 = r14.getDouble(r1)
            r0.realmSet$y(r5)
            goto L_0x019c
        L_0x0194:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'y' to null."
            r13.<init>(r14)
            throw r13
        L_0x019c:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x01b7
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x01ac
            r0.realmSet$location(r2)
            goto L_0x01b7
        L_0x01ac:
            org.json.JSONObject r1 = r14.getJSONObject(r4)
            com.ciot.realm.db.common.LocationBean r13 = io.realm.com_ciot_realm_db_common_LocationBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$location(r13)
        L_0x01b7:
            java.lang.String r13 = "description"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x01d0
            boolean r15 = r14.isNull(r13)
            if (r15 == 0) goto L_0x01c9
            r0.realmSet$description(r2)
            goto L_0x01d0
        L_0x01c9:
            java.lang.String r13 = r14.getString(r13)
            r0.realmSet$description(r13)
        L_0x01d0:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.common.CompanyResponse");
    }

    public static CompanyResponse createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        CompanyResponse companyResponse = new CompanyResponse();
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("pinyin")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$pinyin(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$pinyin((String) null);
                }
            } else if (nextName.equals("contact")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$contact((Contact) null);
                } else {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$contact(com_ciot_realm_db_common_ContactRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("area")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$area(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$area((String) null);
                }
            } else if (nextName.equals("address")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$address(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$address((String) null);
                }
            } else if (nextName.equals("qrcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$qrcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$qrcode((String) null);
                }
            } else if (nextName.equals("project")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$project(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$project((String) null);
                }
            } else if (nextName.equals("x")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$x(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
                }
            } else if (nextName.equals("y")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$y(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
                }
            } else if (nextName.equals(RequestParameters.SUBRESOURCE_LOCATION)) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$location((LocationBean) null);
                } else {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$location(com_ciot_realm_db_common_LocationBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (CompanyResponse) realm.copyToRealm(companyResponse, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_common_CompanyResponseRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class), false, Collections.emptyList());
        com_ciot_realm_db_common_CompanyResponseRealmProxy com_ciot_realm_db_common_companyresponserealmproxy = new com_ciot_realm_db_common_CompanyResponseRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_companyresponserealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.CompanyResponse copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy.CompanyResponseColumnInfo r9, com.ciot.realm.db.common.CompanyResponse r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
        /*
            boolean r0 = r10 instanceof io.realm.internal.RealmObjectProxy
            if (r0 == 0) goto L_0x003e
            boolean r0 = io.realm.RealmObject.isFrozen(r10)
            if (r0 != 0) goto L_0x003e
            r0 = r10
            io.realm.internal.RealmObjectProxy r0 = (io.realm.internal.RealmObjectProxy) r0
            io.realm.ProxyState r1 = r0.realmGet$proxyState()
            io.realm.BaseRealm r1 = r1.getRealm$realm()
            if (r1 == 0) goto L_0x003e
            io.realm.ProxyState r0 = r0.realmGet$proxyState()
            io.realm.BaseRealm r0 = r0.getRealm$realm()
            long r1 = r0.threadId
            long r3 = r8.threadId
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0036
            java.lang.String r0 = r0.getPath()
            java.lang.String r1 = r8.getPath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003e
            return r10
        L_0x0036:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Objects which belong to Realm instances in other threads cannot be copied into this Realm instance."
            r8.<init>(r9)
            throw r8
        L_0x003e:
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r0 = io.realm.BaseRealm.objectContext
            java.lang.Object r0 = r0.get()
            io.realm.BaseRealm$RealmObjectContext r0 = (io.realm.BaseRealm.RealmObjectContext) r0
            java.lang.Object r1 = r12.get(r10)
            io.realm.internal.RealmObjectProxy r1 = (io.realm.internal.RealmObjectProxy) r1
            if (r1 == 0) goto L_0x0051
            com.ciot.realm.db.common.CompanyResponse r1 = (com.ciot.realm.db.common.CompanyResponse) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.common.CompanyResponse> r2 = com.ciot.realm.db.common.CompanyResponse.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$id()
            if (r5 != 0) goto L_0x006a
            long r3 = r2.findFirstNull(r3)
            goto L_0x006e
        L_0x006a:
            long r3 = r2.findFirstString(r3, r5)
        L_0x006e:
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0076
            r0 = 0
            goto L_0x009a
        L_0x0076:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x0094 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0094 }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0094 }
            io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy r1 = new io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy     // Catch:{ all -> 0x0094 }
            r1.<init>()     // Catch:{ all -> 0x0094 }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x0094 }
            r12.put(r10, r2)     // Catch:{ all -> 0x0094 }
            r0.clear()
            goto L_0x0099
        L_0x0094:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0099:
            r0 = r11
        L_0x009a:
            r3 = r1
            if (r0 == 0) goto L_0x00a7
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.common.CompanyResponse r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.common.CompanyResponse r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy$CompanyResponseColumnInfo, com.ciot.realm.db.common.CompanyResponse, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.common.CompanyResponse");
    }

    public static CompanyResponse copy(Realm realm, CompanyResponseColumnInfo companyResponseColumnInfo, CompanyResponse companyResponse, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(companyResponse);
        if (realmObjectProxy != null) {
            return (CompanyResponse) realmObjectProxy;
        }
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(CompanyResponse.class), set);
        osObjectBuilder.addString(companyResponseColumnInfo.nameColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(companyResponseColumnInfo.pinyinColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$pinyin());
        osObjectBuilder.addString(companyResponseColumnInfo.areaColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$area());
        osObjectBuilder.addString(companyResponseColumnInfo.addressColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$address());
        osObjectBuilder.addString(companyResponseColumnInfo.qrcodeColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$qrcode());
        osObjectBuilder.addString(companyResponseColumnInfo.projectColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$project());
        osObjectBuilder.addDouble(companyResponseColumnInfo.xColKey, Double.valueOf(com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$x()));
        osObjectBuilder.addDouble(companyResponseColumnInfo.yColKey, Double.valueOf(com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$y()));
        osObjectBuilder.addString(companyResponseColumnInfo.descriptionColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(companyResponseColumnInfo.idColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$id());
        com_ciot_realm_db_common_CompanyResponseRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(companyResponse, newProxyInstance);
        Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$contact();
        if (realmGet$contact == null) {
            newProxyInstance.realmSet$contact((Contact) null);
        } else {
            Contact contact = (Contact) map.get(realmGet$contact);
            if (contact != null) {
                newProxyInstance.realmSet$contact(contact);
            } else {
                newProxyInstance.realmSet$contact(com_ciot_realm_db_common_ContactRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ContactRealmProxy.ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class), realmGet$contact, z, map, set));
            }
        }
        LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$location();
        if (realmGet$location == null) {
            newProxyInstance.realmSet$location((LocationBean) null);
        } else {
            LocationBean locationBean = (LocationBean) map.get(realmGet$location);
            if (locationBean != null) {
                newProxyInstance.realmSet$location(locationBean);
            } else {
                newProxyInstance.realmSet$location(com_ciot_realm_db_common_LocationBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_LocationBeanRealmProxy.LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class), realmGet$location, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, CompanyResponse companyResponse, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        CompanyResponse companyResponse2 = companyResponse;
        Map<RealmModel, Long> map2 = map;
        if ((companyResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(companyResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) companyResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(CompanyResponse.class);
        long nativePtr = table.getNativePtr();
        CompanyResponseColumnInfo companyResponseColumnInfo = (CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class);
        long j2 = companyResponseColumnInfo.idColKey;
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse2;
        String realmGet$id = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j3 = j;
        map2.put(companyResponse2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$pinyin = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$pinyin();
        if (realmGet$pinyin != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.pinyinColKey, j3, realmGet$pinyin, false);
        }
        Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$contact();
        if (realmGet$contact != null) {
            Long l = map2.get(realmGet$contact);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_common_ContactRealmProxy.insert(realm2, realmGet$contact, map2));
            }
            Table.nativeSetLink(nativePtr, companyResponseColumnInfo.contactColKey, j3, l.longValue(), false);
        }
        String realmGet$area = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.areaColKey, j3, realmGet$area, false);
        }
        String realmGet$address = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.addressColKey, j3, realmGet$address, false);
        }
        String realmGet$qrcode = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$qrcode();
        if (realmGet$qrcode != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.qrcodeColKey, j3, realmGet$qrcode, false);
        }
        String realmGet$project = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$project();
        if (realmGet$project != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.projectColKey, j3, realmGet$project, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, companyResponseColumnInfo.xColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$x(), false);
        Table.nativeSetDouble(j4, companyResponseColumnInfo.yColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$y(), false);
        LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$location();
        if (realmGet$location != null) {
            Long l2 = map2.get(realmGet$location);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_common_LocationBeanRealmProxy.insert(realm2, realmGet$location, map2));
            }
            Table.nativeSetLink(nativePtr, companyResponseColumnInfo.locationColKey, j3, l2.longValue(), false);
        }
        String realmGet$description = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(CompanyResponse.class);
        long nativePtr = table.getNativePtr();
        CompanyResponseColumnInfo companyResponseColumnInfo = (CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class);
        long j4 = companyResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            CompanyResponse companyResponse = (CompanyResponse) it.next();
            if (!map2.containsKey(companyResponse)) {
                if ((companyResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(companyResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) companyResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(companyResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse;
                String realmGet$id = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(companyResponse, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.nameColKey, j2, realmGet$name, false);
                } else {
                    j3 = j4;
                }
                String realmGet$pinyin = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$pinyin();
                if (realmGet$pinyin != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.pinyinColKey, j2, realmGet$pinyin, false);
                }
                Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$contact();
                if (realmGet$contact != null) {
                    Long l = map2.get(realmGet$contact);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_common_ContactRealmProxy.insert(realm2, realmGet$contact, map2));
                    }
                    table.setLink(companyResponseColumnInfo.contactColKey, j2, l.longValue(), false);
                }
                String realmGet$area = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$area();
                if (realmGet$area != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.areaColKey, j2, realmGet$area, false);
                }
                String realmGet$address = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.addressColKey, j2, realmGet$address, false);
                }
                String realmGet$qrcode = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$qrcode();
                if (realmGet$qrcode != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.qrcodeColKey, j2, realmGet$qrcode, false);
                }
                String realmGet$project = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$project();
                if (realmGet$project != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.projectColKey, j2, realmGet$project, false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetDouble(j5, companyResponseColumnInfo.xColKey, j6, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$x(), false);
                Table.nativeSetDouble(j5, companyResponseColumnInfo.yColKey, j6, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$y(), false);
                LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$location();
                if (realmGet$location != null) {
                    Long l2 = map2.get(realmGet$location);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_common_LocationBeanRealmProxy.insert(realm2, realmGet$location, map2));
                    }
                    table.setLink(companyResponseColumnInfo.locationColKey, j2, l2.longValue(), false);
                }
                String realmGet$description = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CompanyResponse companyResponse, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        CompanyResponse companyResponse2 = companyResponse;
        Map<RealmModel, Long> map2 = map;
        if ((companyResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(companyResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) companyResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(CompanyResponse.class);
        long nativePtr = table.getNativePtr();
        CompanyResponseColumnInfo companyResponseColumnInfo = (CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class);
        long j2 = companyResponseColumnInfo.idColKey;
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse2;
        String realmGet$id = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map2.put(companyResponse2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.nameColKey, j3, false);
        }
        String realmGet$pinyin = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$pinyin();
        if (realmGet$pinyin != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.pinyinColKey, j3, realmGet$pinyin, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.pinyinColKey, j3, false);
        }
        Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$contact();
        if (realmGet$contact != null) {
            Long l = map2.get(realmGet$contact);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_common_ContactRealmProxy.insertOrUpdate(realm2, realmGet$contact, map2));
            }
            Table.nativeSetLink(nativePtr, companyResponseColumnInfo.contactColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, companyResponseColumnInfo.contactColKey, j3);
        }
        String realmGet$area = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.areaColKey, j3, realmGet$area, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.areaColKey, j3, false);
        }
        String realmGet$address = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.addressColKey, j3, realmGet$address, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.addressColKey, j3, false);
        }
        String realmGet$qrcode = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$qrcode();
        if (realmGet$qrcode != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.qrcodeColKey, j3, realmGet$qrcode, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.qrcodeColKey, j3, false);
        }
        String realmGet$project = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$project();
        if (realmGet$project != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.projectColKey, j3, realmGet$project, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.projectColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, companyResponseColumnInfo.xColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$x(), false);
        Table.nativeSetDouble(j4, companyResponseColumnInfo.yColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$y(), false);
        LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$location();
        if (realmGet$location != null) {
            Long l2 = map2.get(realmGet$location);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_common_LocationBeanRealmProxy.insertOrUpdate(realm2, realmGet$location, map2));
            }
            Table.nativeSetLink(nativePtr, companyResponseColumnInfo.locationColKey, j3, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, companyResponseColumnInfo.locationColKey, j3);
        }
        String realmGet$description = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, companyResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, companyResponseColumnInfo.descriptionColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(CompanyResponse.class);
        long nativePtr = table.getNativePtr();
        CompanyResponseColumnInfo companyResponseColumnInfo = (CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class);
        long j3 = companyResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            CompanyResponse companyResponse = (CompanyResponse) it.next();
            if (!map2.containsKey(companyResponse)) {
                if ((companyResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(companyResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) companyResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(companyResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse;
                String realmGet$id = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(companyResponse, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$pinyin = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$pinyin();
                if (realmGet$pinyin != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.pinyinColKey, createRowWithPrimaryKey, realmGet$pinyin, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.pinyinColKey, createRowWithPrimaryKey, false);
                }
                Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$contact();
                if (realmGet$contact != null) {
                    Long l = map2.get(realmGet$contact);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_common_ContactRealmProxy.insertOrUpdate(realm2, realmGet$contact, map2));
                    }
                    Table.nativeSetLink(nativePtr, companyResponseColumnInfo.contactColKey, createRowWithPrimaryKey, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, companyResponseColumnInfo.contactColKey, createRowWithPrimaryKey);
                }
                String realmGet$area = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$area();
                if (realmGet$area != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.areaColKey, createRowWithPrimaryKey, realmGet$area, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.areaColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$address = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.addressColKey, createRowWithPrimaryKey, realmGet$address, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.addressColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$qrcode = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$qrcode();
                if (realmGet$qrcode != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.qrcodeColKey, createRowWithPrimaryKey, realmGet$qrcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.qrcodeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$project = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$project();
                if (realmGet$project != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.projectColKey, createRowWithPrimaryKey, realmGet$project, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.projectColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetDouble(j4, companyResponseColumnInfo.xColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$x(), false);
                Table.nativeSetDouble(j4, companyResponseColumnInfo.yColKey, j5, com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$y(), false);
                LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$location();
                if (realmGet$location != null) {
                    Long l2 = map2.get(realmGet$location);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_common_LocationBeanRealmProxy.insertOrUpdate(realm2, realmGet$location, map2));
                    }
                    Table.nativeSetLink(nativePtr, companyResponseColumnInfo.locationColKey, createRowWithPrimaryKey, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, companyResponseColumnInfo.locationColKey, createRowWithPrimaryKey);
                }
                String realmGet$description = com_ciot_realm_db_common_companyresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, companyResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, companyResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                j3 = j2;
            }
        }
    }

    public static CompanyResponse createDetachedCopy(CompanyResponse companyResponse, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        CompanyResponse companyResponse2;
        if (i > i2 || companyResponse == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(companyResponse);
        if (cacheData == null) {
            companyResponse2 = new CompanyResponse();
            map.put(companyResponse, new RealmObjectProxy.CacheData(i, companyResponse2));
        } else if (i >= cacheData.minDepth) {
            return (CompanyResponse) cacheData.object;
        } else {
            cacheData.minDepth = i;
            companyResponse2 = (CompanyResponse) cacheData.object;
        }
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse2;
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface2 = companyResponse;
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$name(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$pinyin(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$pinyin());
        int i3 = i + 1;
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$contact(com_ciot_realm_db_common_ContactRealmProxy.createDetachedCopy(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$contact(), i3, i2, map));
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$area(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$area());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$address(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$address());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$qrcode(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$qrcode());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$project(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$project());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$x(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$x());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$y(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$y());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$location(com_ciot_realm_db_common_LocationBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$location(), i3, i2, map));
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$description(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_common_companyresponserealmproxyinterface.realmSet$id(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$id());
        return companyResponse2;
    }

    static CompanyResponse update(Realm realm, CompanyResponseColumnInfo companyResponseColumnInfo, CompanyResponse companyResponse, CompanyResponse companyResponse2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface = companyResponse;
        com_ciot_realm_db_common_CompanyResponseRealmProxyInterface com_ciot_realm_db_common_companyresponserealmproxyinterface2 = companyResponse2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(CompanyResponse.class), set);
        osObjectBuilder.addString(companyResponseColumnInfo.nameColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(companyResponseColumnInfo.pinyinColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$pinyin());
        Contact realmGet$contact = com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$contact();
        if (realmGet$contact == null) {
            osObjectBuilder.addNull(companyResponseColumnInfo.contactColKey);
        } else {
            Contact contact = (Contact) map.get(realmGet$contact);
            if (contact != null) {
                osObjectBuilder.addObject(companyResponseColumnInfo.contactColKey, contact);
            } else {
                osObjectBuilder.addObject(companyResponseColumnInfo.contactColKey, com_ciot_realm_db_common_ContactRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ContactRealmProxy.ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class), realmGet$contact, true, map, set));
            }
        }
        osObjectBuilder.addString(companyResponseColumnInfo.areaColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$area());
        osObjectBuilder.addString(companyResponseColumnInfo.addressColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$address());
        osObjectBuilder.addString(companyResponseColumnInfo.qrcodeColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$qrcode());
        osObjectBuilder.addString(companyResponseColumnInfo.projectColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$project());
        osObjectBuilder.addDouble(companyResponseColumnInfo.xColKey, Double.valueOf(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$x()));
        osObjectBuilder.addDouble(companyResponseColumnInfo.yColKey, Double.valueOf(com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$y()));
        LocationBean realmGet$location = com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$location();
        if (realmGet$location == null) {
            osObjectBuilder.addNull(companyResponseColumnInfo.locationColKey);
        } else {
            LocationBean locationBean = (LocationBean) map.get(realmGet$location);
            if (locationBean != null) {
                osObjectBuilder.addObject(companyResponseColumnInfo.locationColKey, locationBean);
            } else {
                osObjectBuilder.addObject(companyResponseColumnInfo.locationColKey, com_ciot_realm_db_common_LocationBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_LocationBeanRealmProxy.LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class), realmGet$location, true, map, set));
            }
        }
        osObjectBuilder.addString(companyResponseColumnInfo.descriptionColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(companyResponseColumnInfo.idColKey, com_ciot_realm_db_common_companyresponserealmproxyinterface2.realmGet$id());
        osObjectBuilder.updateExistingObject();
        return companyResponse;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public int hashCode() {
        String path = this.proxyState.getRealm$realm().getPath();
        String name = this.proxyState.getRow$realm().getTable().getName();
        long objectKey = this.proxyState.getRow$realm().getObjectKey();
        int i = 0;
        int hashCode = (527 + (path != null ? path.hashCode() : 0)) * 31;
        if (name != null) {
            i = name.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((objectKey >>> 32) ^ objectKey));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        com_ciot_realm_db_common_CompanyResponseRealmProxy com_ciot_realm_db_common_companyresponserealmproxy = (com_ciot_realm_db_common_CompanyResponseRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_companyresponserealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_companyresponserealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_companyresponserealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
