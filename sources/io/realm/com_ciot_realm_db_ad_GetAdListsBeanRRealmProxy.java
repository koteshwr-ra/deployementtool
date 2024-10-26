package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.ad.GetAdListsBeanR;
import com.ciot.realm.db.ad.TimesBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_CycleBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_TimesBeanRealmProxy;
import io.realm.exceptions.RealmException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
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

public class com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy extends GetAdListsBeanR implements RealmObjectProxy, com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<AdvertisementsBean> advertisementsRealmList;
    private GetAdListsBeanRColumnInfo columnInfo;
    private ProxyState<GetAdListsBeanR> proxyState;
    private RealmList<TimesBean> timesRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "GetAdListsBeanR";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class GetAdListsBeanRColumnInfo extends ColumnInfo {
        long advertisementsColKey;
        long createtimeColKey;
        long cycleColKey;
        long descriptionColKey;
        long idColKey;
        long nameColKey;
        long playingModeColKey;
        long screenColKey;
        long timesColKey;

        GetAdListsBeanRColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.cycleColKey = addColumnDetails("cycle", "cycle", objectSchemaInfo);
            this.playingModeColKey = addColumnDetails("playingMode", "playingMode", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.screenColKey = addColumnDetails("screen", "screen", objectSchemaInfo);
            this.timesColKey = addColumnDetails("times", "times", objectSchemaInfo);
            this.advertisementsColKey = addColumnDetails("advertisements", "advertisements", objectSchemaInfo);
        }

        GetAdListsBeanRColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new GetAdListsBeanRColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo = (GetAdListsBeanRColumnInfo) columnInfo;
            GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo2 = (GetAdListsBeanRColumnInfo) columnInfo2;
            getAdListsBeanRColumnInfo2.nameColKey = getAdListsBeanRColumnInfo.nameColKey;
            getAdListsBeanRColumnInfo2.cycleColKey = getAdListsBeanRColumnInfo.cycleColKey;
            getAdListsBeanRColumnInfo2.playingModeColKey = getAdListsBeanRColumnInfo.playingModeColKey;
            getAdListsBeanRColumnInfo2.createtimeColKey = getAdListsBeanRColumnInfo.createtimeColKey;
            getAdListsBeanRColumnInfo2.descriptionColKey = getAdListsBeanRColumnInfo.descriptionColKey;
            getAdListsBeanRColumnInfo2.idColKey = getAdListsBeanRColumnInfo.idColKey;
            getAdListsBeanRColumnInfo2.screenColKey = getAdListsBeanRColumnInfo.screenColKey;
            getAdListsBeanRColumnInfo2.timesColKey = getAdListsBeanRColumnInfo.timesColKey;
            getAdListsBeanRColumnInfo2.advertisementsColKey = getAdListsBeanRColumnInfo.advertisementsColKey;
        }
    }

    com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (GetAdListsBeanRColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<GetAdListsBeanR> proxyState2 = new ProxyState<>(this);
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

    public CycleBean realmGet$cycle() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.cycleColKey)) {
            return null;
        }
        return (CycleBean) this.proxyState.getRealm$realm().get(CycleBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.cycleColKey), false, Collections.emptyList());
    }

    public void realmSet$cycle(CycleBean cycleBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (cycleBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.cycleColKey);
                return;
            }
            this.proxyState.checkValidObject(cycleBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.cycleColKey, ((RealmObjectProxy) cycleBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("cycle")) {
            if (cycleBean != null && !RealmObject.isManaged(cycleBean)) {
                cycleBean = (CycleBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(cycleBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (cycleBean == null) {
                row$realm.nullifyLink(this.columnInfo.cycleColKey);
                return;
            }
            this.proxyState.checkValidObject(cycleBean);
            row$realm.getTable().setLink(this.columnInfo.cycleColKey, row$realm.getObjectKey(), ((RealmObjectProxy) cycleBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$playingMode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.playingModeColKey);
    }

    public void realmSet$playingMode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.playingModeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.playingModeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.playingModeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.playingModeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$createtime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createtimeColKey);
    }

    public void realmSet$createtime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.createtimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.createtimeColKey, row$realm.getObjectKey(), j, true);
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

    public int realmGet$screen() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.screenColKey);
    }

    public void realmSet$screen(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.screenColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.screenColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public RealmList<TimesBean> realmGet$times() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<TimesBean> realmList = this.timesRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<TimesBean> realmList2 = new RealmList<>(TimesBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.timesColKey), this.proxyState.getRealm$realm());
        this.timesRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$times(RealmList<TimesBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("times")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<TimesBean> realmList2 = new RealmList<>();
                Iterator<TimesBean> it = realmList.iterator();
                while (it.hasNext()) {
                    TimesBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((TimesBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.timesColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    TimesBean timesBean = realmList.get(i);
                    this.proxyState.checkValidObject(timesBean);
                    modelList.addRow(((RealmObjectProxy) timesBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            TimesBean timesBean2 = realmList.get(i);
            this.proxyState.checkValidObject(timesBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) timesBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public RealmList<AdvertisementsBean> realmGet$advertisements() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<AdvertisementsBean> realmList = this.advertisementsRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<AdvertisementsBean> realmList2 = new RealmList<>(AdvertisementsBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.advertisementsColKey), this.proxyState.getRealm$realm());
        this.advertisementsRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$advertisements(RealmList<AdvertisementsBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("advertisements")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<AdvertisementsBean> realmList2 = new RealmList<>();
                Iterator<AdvertisementsBean> it = realmList.iterator();
                while (it.hasNext()) {
                    AdvertisementsBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((AdvertisementsBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.advertisementsColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    AdvertisementsBean advertisementsBean = realmList.get(i);
                    this.proxyState.checkValidObject(advertisementsBean);
                    modelList.addRow(((RealmObjectProxy) advertisementsBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            AdvertisementsBean advertisementsBean2 = realmList.get(i);
            this.proxyState.checkValidObject(advertisementsBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) advertisementsBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 9, 0);
        builder.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("cycle", RealmFieldType.OBJECT, com_ciot_realm_db_ad_CycleBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("playingMode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("screen", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("times", RealmFieldType.LIST, com_ciot_realm_db_ad_TimesBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("advertisements", RealmFieldType.LIST, com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static GetAdListsBeanRColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new GetAdListsBeanRColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0197  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.ad.GetAdListsBeanR createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 3
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r3 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r5 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy$GetAdListsBeanRColumnInfo r4 = (io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.GetAdListsBeanRColumnInfo) r4
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
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r4 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy r3 = new io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy     // Catch:{ all -> 0x0061 }
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
            java.lang.String r4 = "advertisements"
            java.lang.String r5 = "times"
            java.lang.String r6 = "cycle"
            if (r3 != 0) goto L_0x00b7
            boolean r3 = r14.has(r6)
            if (r3 == 0) goto L_0x0078
            r0.add(r6)
        L_0x0078:
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x0081
            r0.add(r5)
        L_0x0081:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x008a
            r0.add(r4)
        L_0x008a:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00af
            boolean r3 = r14.isNull(r1)
            r7 = 1
            if (r3 == 0) goto L_0x00a1
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r1 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy r3 = (io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy) r3
            goto L_0x00b7
        L_0x00a1:
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r3 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy r3 = (io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy) r3
            goto L_0x00b7
        L_0x00af:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00b7:
            r0 = r3
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface) r0
            java.lang.String r1 = "name"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x00d3
            boolean r7 = r14.isNull(r1)
            if (r7 == 0) goto L_0x00cc
            r0.realmSet$name(r2)
            goto L_0x00d3
        L_0x00cc:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$name(r1)
        L_0x00d3:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x00ee
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x00e3
            r0.realmSet$cycle(r2)
            goto L_0x00ee
        L_0x00e3:
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.ad.CycleBean r1 = io.realm.com_ciot_realm_db_ad_CycleBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$cycle(r1)
        L_0x00ee:
            java.lang.String r1 = "playingMode"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x0107
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x0100
            r0.realmSet$playingMode(r2)
            goto L_0x0107
        L_0x0100:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$playingMode(r1)
        L_0x0107:
            java.lang.String r1 = "createtime"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x0125
            boolean r6 = r14.isNull(r1)
            if (r6 != 0) goto L_0x011d
            long r6 = r14.getLong(r1)
            r0.realmSet$createtime(r6)
            goto L_0x0125
        L_0x011d:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'createtime' to null."
            r13.<init>(r14)
            throw r13
        L_0x0125:
            java.lang.String r1 = "description"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x013e
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x0137
            r0.realmSet$description(r2)
            goto L_0x013e
        L_0x0137:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$description(r1)
        L_0x013e:
            java.lang.String r1 = "screen"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x015c
            boolean r6 = r14.isNull(r1)
            if (r6 != 0) goto L_0x0154
            int r1 = r14.getInt(r1)
            r0.realmSet$screen(r1)
            goto L_0x015c
        L_0x0154:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'screen' to null."
            r13.<init>(r14)
            throw r13
        L_0x015c:
            boolean r1 = r14.has(r5)
            r6 = 0
            if (r1 == 0) goto L_0x0191
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x016d
            r0.realmSet$times(r2)
            goto L_0x0191
        L_0x016d:
            io.realm.RealmList r1 = r0.realmGet$times()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r5)
            r5 = 0
        L_0x0179:
            int r7 = r1.length()
            if (r5 >= r7) goto L_0x0191
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            com.ciot.realm.db.ad.TimesBean r7 = io.realm.com_ciot_realm_db_ad_TimesBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r7, r15)
            io.realm.RealmList r8 = r0.realmGet$times()
            r8.add(r7)
            int r5 = r5 + 1
            goto L_0x0179
        L_0x0191:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x01c4
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x01a1
            r0.realmSet$advertisements(r2)
            goto L_0x01c4
        L_0x01a1:
            io.realm.RealmList r1 = r0.realmGet$advertisements()
            r1.clear()
            org.json.JSONArray r14 = r14.getJSONArray(r4)
        L_0x01ac:
            int r1 = r14.length()
            if (r6 >= r1) goto L_0x01c4
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.ad.AdvertisementsBean r1 = io.realm.com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            io.realm.RealmList r2 = r0.realmGet$advertisements()
            r2.add(r1)
            int r6 = r6 + 1
            goto L_0x01ac
        L_0x01c4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.ad.GetAdListsBeanR");
    }

    public static GetAdListsBeanR createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        GetAdListsBeanR getAdListsBeanR = new GetAdListsBeanR();
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("cycle")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$cycle((CycleBean) null);
                } else {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$cycle(com_ciot_realm_db_ad_CycleBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("playingMode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$playingMode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$playingMode((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$createtime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("screen")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$screen(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'screen' to null.");
                }
            } else if (nextName.equals("times")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$times((RealmList<TimesBean>) null);
                } else {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$times(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times().add(com_ciot_realm_db_ad_TimesBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("advertisements")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$advertisements((RealmList<AdvertisementsBean>) null);
            } else {
                com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$advertisements(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements().add(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (GetAdListsBeanR) realm.copyToRealm(getAdListsBeanR, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy com_ciot_realm_db_ad_getadlistsbeanrrealmproxy = new com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_getadlistsbeanrrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.ad.GetAdListsBeanR copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.GetAdListsBeanRColumnInfo r9, com.ciot.realm.db.ad.GetAdListsBeanR r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.ad.GetAdListsBeanR r1 = (com.ciot.realm.db.ad.GetAdListsBeanR) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.ad.GetAdListsBeanR> r2 = com.ciot.realm.db.ad.GetAdListsBeanR.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy r1 = new io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.ad.GetAdListsBeanR r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.ad.GetAdListsBeanR r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy$GetAdListsBeanRColumnInfo, com.ciot.realm.db.ad.GetAdListsBeanR, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.ad.GetAdListsBeanR");
    }

    public static GetAdListsBeanR copy(Realm realm, GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo, GetAdListsBeanR getAdListsBeanR, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Realm realm2 = realm;
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo2 = getAdListsBeanRColumnInfo;
        GetAdListsBeanR getAdListsBeanR2 = getAdListsBeanR;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        RealmObjectProxy realmObjectProxy = map2.get(getAdListsBeanR2);
        if (realmObjectProxy != null) {
            return (GetAdListsBeanR) realmObjectProxy;
        }
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(GetAdListsBeanR.class), set);
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.nameColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.playingModeColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$playingMode());
        osObjectBuilder.addInteger(getAdListsBeanRColumnInfo2.createtimeColKey, Long.valueOf(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.descriptionColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.idColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$id());
        osObjectBuilder.addInteger(getAdListsBeanRColumnInfo2.screenColKey, Integer.valueOf(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$screen()));
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map2.put(getAdListsBeanR2, newProxyInstance);
        CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$cycle();
        if (realmGet$cycle == null) {
            newProxyInstance.realmSet$cycle((CycleBean) null);
        } else {
            CycleBean cycleBean = (CycleBean) map2.get(realmGet$cycle);
            if (cycleBean != null) {
                newProxyInstance.realmSet$cycle(cycleBean);
            } else {
                newProxyInstance.realmSet$cycle(com_ciot_realm_db_ad_CycleBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_CycleBeanRealmProxy.CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class), realmGet$cycle, z, map, set));
            }
        }
        RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times();
        if (realmGet$times != null) {
            RealmList<TimesBean> realmGet$times2 = newProxyInstance.realmGet$times();
            realmGet$times2.clear();
            for (int i = 0; i < realmGet$times.size(); i++) {
                TimesBean timesBean = realmGet$times.get(i);
                TimesBean timesBean2 = (TimesBean) map2.get(timesBean);
                if (timesBean2 != null) {
                    realmGet$times2.add(timesBean2);
                } else {
                    realmGet$times2.add(com_ciot_realm_db_ad_TimesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_TimesBeanRealmProxy.TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class), timesBean, z, map, set));
                }
            }
        }
        RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements();
        if (realmGet$advertisements != null) {
            RealmList<AdvertisementsBean> realmGet$advertisements2 = newProxyInstance.realmGet$advertisements();
            realmGet$advertisements2.clear();
            for (int i2 = 0; i2 < realmGet$advertisements.size(); i2++) {
                AdvertisementsBean advertisementsBean = realmGet$advertisements.get(i2);
                AdvertisementsBean advertisementsBean2 = (AdvertisementsBean) map2.get(advertisementsBean);
                if (advertisementsBean2 != null) {
                    realmGet$advertisements2.add(advertisementsBean2);
                } else {
                    realmGet$advertisements2.add(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class), advertisementsBean, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, GetAdListsBeanR getAdListsBeanR, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        GetAdListsBeanR getAdListsBeanR2 = getAdListsBeanR;
        Map<RealmModel, Long> map2 = map;
        if ((getAdListsBeanR2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(getAdListsBeanR)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) getAdListsBeanR2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(GetAdListsBeanR.class);
        long nativePtr = table.getNativePtr();
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo = (GetAdListsBeanRColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class);
        long j4 = getAdListsBeanRColumnInfo.idColKey;
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR2;
        String realmGet$id = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j4);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j5 = j;
        map2.put(getAdListsBeanR2, Long.valueOf(j5));
        String realmGet$name = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            j2 = j5;
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j5, realmGet$name, false);
        } else {
            j2 = j5;
        }
        CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$cycle();
        if (realmGet$cycle != null) {
            Long l = map2.get(realmGet$cycle);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_ad_CycleBeanRealmProxy.insert(realm2, realmGet$cycle, map2));
            }
            Table.nativeSetLink(nativePtr, getAdListsBeanRColumnInfo.cycleColKey, j2, l.longValue(), false);
        }
        String realmGet$playingMode = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$playingMode();
        if (realmGet$playingMode != null) {
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j2, realmGet$playingMode, false);
        }
        Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.createtimeColKey, j2, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$description = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j2, realmGet$description, false);
        }
        Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.screenColKey, j2, (long) com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$screen(), false);
        RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times();
        if (realmGet$times != null) {
            j3 = j2;
            OsList osList = new OsList(table.getUncheckedRow(j3), getAdListsBeanRColumnInfo.timesColKey);
            Iterator<TimesBean> it = realmGet$times.iterator();
            while (it.hasNext()) {
                TimesBean next = it.next();
                Long l2 = map2.get(next);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l2.longValue());
            }
        } else {
            j3 = j2;
        }
        RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements();
        if (realmGet$advertisements != null) {
            OsList osList2 = new OsList(table.getUncheckedRow(j3), getAdListsBeanRColumnInfo.advertisementsColKey);
            Iterator<AdvertisementsBean> it2 = realmGet$advertisements.iterator();
            while (it2.hasNext()) {
                AdvertisementsBean next2 = it2.next();
                Long l3 = map2.get(next2);
                if (l3 == null) {
                    l3 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insert(realm2, next2, map2));
                }
                osList2.addRow(l3.longValue());
            }
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(GetAdListsBeanR.class);
        long nativePtr = table.getNativePtr();
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo = (GetAdListsBeanRColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class);
        long j5 = getAdListsBeanRColumnInfo.idColKey;
        while (it.hasNext()) {
            GetAdListsBeanR getAdListsBeanR = (GetAdListsBeanR) it.next();
            if (!map2.containsKey(getAdListsBeanR)) {
                if ((getAdListsBeanR instanceof RealmObjectProxy) && !RealmObject.isFrozen(getAdListsBeanR)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) getAdListsBeanR;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(getAdListsBeanR, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR;
                String realmGet$id = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j5);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j5, realmGet$id);
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j5, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                }
                long j6 = j;
                map2.put(getAdListsBeanR, Long.valueOf(j6));
                String realmGet$name = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j6;
                    j2 = j5;
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j6, realmGet$name, false);
                } else {
                    j3 = j6;
                    j2 = j5;
                }
                CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$cycle();
                if (realmGet$cycle != null) {
                    Long l = map2.get(realmGet$cycle);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_ad_CycleBeanRealmProxy.insert(realm2, realmGet$cycle, map2));
                    }
                    table.setLink(getAdListsBeanRColumnInfo.cycleColKey, j3, l.longValue(), false);
                }
                String realmGet$playingMode = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$playingMode();
                if (realmGet$playingMode != null) {
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j3, realmGet$playingMode, false);
                }
                Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.createtimeColKey, j3, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$description = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j3, realmGet$description, false);
                }
                Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.screenColKey, j3, (long) com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$screen(), false);
                RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times();
                if (realmGet$times != null) {
                    j4 = j3;
                    OsList osList = new OsList(table.getUncheckedRow(j4), getAdListsBeanRColumnInfo.timesColKey);
                    Iterator<TimesBean> it2 = realmGet$times.iterator();
                    while (it2.hasNext()) {
                        TimesBean next = it2.next();
                        Long l2 = map2.get(next);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l2.longValue());
                    }
                } else {
                    j4 = j3;
                }
                RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements();
                if (realmGet$advertisements != null) {
                    OsList osList2 = new OsList(table.getUncheckedRow(j4), getAdListsBeanRColumnInfo.advertisementsColKey);
                    Iterator<AdvertisementsBean> it3 = realmGet$advertisements.iterator();
                    while (it3.hasNext()) {
                        AdvertisementsBean next2 = it3.next();
                        Long l3 = map2.get(next2);
                        if (l3 == null) {
                            l3 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insert(realm2, next2, map2));
                        }
                        osList2.addRow(l3.longValue());
                    }
                }
                j5 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, GetAdListsBeanR getAdListsBeanR, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        GetAdListsBeanR getAdListsBeanR2 = getAdListsBeanR;
        Map<RealmModel, Long> map2 = map;
        if ((getAdListsBeanR2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(getAdListsBeanR)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) getAdListsBeanR2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(GetAdListsBeanR.class);
        long nativePtr = table.getNativePtr();
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo = (GetAdListsBeanRColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class);
        long j3 = getAdListsBeanRColumnInfo.idColKey;
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR2;
        String realmGet$id = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j3);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
        }
        long j4 = j;
        map2.put(getAdListsBeanR2, Long.valueOf(j4));
        String realmGet$name = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j4, realmGet$name, false);
        } else {
            j2 = j4;
            Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j2, false);
        }
        CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$cycle();
        if (realmGet$cycle != null) {
            Long l = map2.get(realmGet$cycle);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_ad_CycleBeanRealmProxy.insertOrUpdate(realm2, realmGet$cycle, map2));
            }
            Table.nativeSetLink(nativePtr, getAdListsBeanRColumnInfo.cycleColKey, j2, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, getAdListsBeanRColumnInfo.cycleColKey, j2);
        }
        String realmGet$playingMode = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$playingMode();
        if (realmGet$playingMode != null) {
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j2, realmGet$playingMode, false);
        } else {
            Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j2, false);
        }
        Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.createtimeColKey, j2, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$description = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j2, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j2, false);
        }
        Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.screenColKey, j2, (long) com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$screen(), false);
        long j5 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j5), getAdListsBeanRColumnInfo.timesColKey);
        RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times();
        if (realmGet$times == null || ((long) realmGet$times.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$times != null) {
                Iterator<TimesBean> it = realmGet$times.iterator();
                while (it.hasNext()) {
                    TimesBean next = it.next();
                    Long l2 = map2.get(next);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l2.longValue());
                }
            }
        } else {
            int size = realmGet$times.size();
            for (int i = 0; i < size; i++) {
                TimesBean timesBean = realmGet$times.get(i);
                Long l3 = map2.get(timesBean);
                if (l3 == null) {
                    l3 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm2, timesBean, map2));
                }
                osList.setRow((long) i, l3.longValue());
            }
        }
        OsList osList2 = new OsList(table.getUncheckedRow(j5), getAdListsBeanRColumnInfo.advertisementsColKey);
        RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements();
        if (realmGet$advertisements == null || ((long) realmGet$advertisements.size()) != osList2.size()) {
            osList2.removeAll();
            if (realmGet$advertisements != null) {
                Iterator<AdvertisementsBean> it2 = realmGet$advertisements.iterator();
                while (it2.hasNext()) {
                    AdvertisementsBean next2 = it2.next();
                    Long l4 = map2.get(next2);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                    }
                    osList2.addRow(l4.longValue());
                }
            }
        } else {
            int size2 = realmGet$advertisements.size();
            for (int i2 = 0; i2 < size2; i2++) {
                AdvertisementsBean advertisementsBean = realmGet$advertisements.get(i2);
                Long l5 = map2.get(advertisementsBean);
                if (l5 == null) {
                    l5 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm2, advertisementsBean, map2));
                }
                osList2.setRow((long) i2, l5.longValue());
            }
        }
        return j5;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(GetAdListsBeanR.class);
        long nativePtr = table.getNativePtr();
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo = (GetAdListsBeanRColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) GetAdListsBeanR.class);
        long j4 = getAdListsBeanRColumnInfo.idColKey;
        while (it.hasNext()) {
            GetAdListsBeanR getAdListsBeanR = (GetAdListsBeanR) it.next();
            if (!map2.containsKey(getAdListsBeanR)) {
                if ((getAdListsBeanR instanceof RealmObjectProxy) && !RealmObject.isFrozen(getAdListsBeanR)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) getAdListsBeanR;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(getAdListsBeanR, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR;
                String realmGet$id = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                }
                long j5 = j;
                map2.put(getAdListsBeanR, Long.valueOf(j5));
                String realmGet$name = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j5;
                    j2 = j4;
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j5, realmGet$name, false);
                } else {
                    j3 = j5;
                    j2 = j4;
                    Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.nameColKey, j5, false);
                }
                CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$cycle();
                if (realmGet$cycle != null) {
                    Long l = map2.get(realmGet$cycle);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_ad_CycleBeanRealmProxy.insertOrUpdate(realm2, realmGet$cycle, map2));
                    }
                    Table.nativeSetLink(nativePtr, getAdListsBeanRColumnInfo.cycleColKey, j3, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, getAdListsBeanRColumnInfo.cycleColKey, j3);
                }
                String realmGet$playingMode = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$playingMode();
                if (realmGet$playingMode != null) {
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j3, realmGet$playingMode, false);
                } else {
                    Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.playingModeColKey, j3, false);
                }
                Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.createtimeColKey, j3, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$description = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j3, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, getAdListsBeanRColumnInfo.descriptionColKey, j3, false);
                }
                Table.nativeSetLong(nativePtr, getAdListsBeanRColumnInfo.screenColKey, j3, (long) com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$screen(), false);
                long j6 = j3;
                OsList osList = new OsList(table.getUncheckedRow(j6), getAdListsBeanRColumnInfo.timesColKey);
                RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$times();
                if (realmGet$times == null || ((long) realmGet$times.size()) != osList.size()) {
                    osList.removeAll();
                    if (realmGet$times != null) {
                        Iterator<TimesBean> it2 = realmGet$times.iterator();
                        while (it2.hasNext()) {
                            TimesBean next = it2.next();
                            Long l2 = map2.get(next);
                            if (l2 == null) {
                                l2 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l2.longValue());
                        }
                    }
                } else {
                    int i = 0;
                    for (int size = realmGet$times.size(); i < size; size = size) {
                        TimesBean timesBean = realmGet$times.get(i);
                        Long l3 = map2.get(timesBean);
                        if (l3 == null) {
                            l3 = Long.valueOf(com_ciot_realm_db_ad_TimesBeanRealmProxy.insertOrUpdate(realm2, timesBean, map2));
                        }
                        osList.setRow((long) i, l3.longValue());
                        i++;
                    }
                }
                OsList osList2 = new OsList(table.getUncheckedRow(j6), getAdListsBeanRColumnInfo.advertisementsColKey);
                RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmGet$advertisements();
                if (realmGet$advertisements == null || ((long) realmGet$advertisements.size()) != osList2.size()) {
                    osList2.removeAll();
                    if (realmGet$advertisements != null) {
                        Iterator<AdvertisementsBean> it3 = realmGet$advertisements.iterator();
                        while (it3.hasNext()) {
                            AdvertisementsBean next2 = it3.next();
                            Long l4 = map2.get(next2);
                            if (l4 == null) {
                                l4 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                            }
                            osList2.addRow(l4.longValue());
                        }
                    }
                } else {
                    int i2 = 0;
                    for (int size2 = realmGet$advertisements.size(); i2 < size2; size2 = size2) {
                        AdvertisementsBean advertisementsBean = realmGet$advertisements.get(i2);
                        Long l5 = map2.get(advertisementsBean);
                        if (l5 == null) {
                            l5 = Long.valueOf(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.insertOrUpdate(realm2, advertisementsBean, map2));
                        }
                        osList2.setRow((long) i2, l5.longValue());
                        i2++;
                    }
                }
                j4 = j2;
            }
        }
    }

    public static GetAdListsBeanR createDetachedCopy(GetAdListsBeanR getAdListsBeanR, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        GetAdListsBeanR getAdListsBeanR2;
        if (i > i2 || getAdListsBeanR == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(getAdListsBeanR);
        if (cacheData == null) {
            getAdListsBeanR2 = new GetAdListsBeanR();
            map.put(getAdListsBeanR, new RealmObjectProxy.CacheData(i, getAdListsBeanR2));
        } else if (i >= cacheData.minDepth) {
            return (GetAdListsBeanR) cacheData.object;
        } else {
            cacheData.minDepth = i;
            getAdListsBeanR2 = (GetAdListsBeanR) cacheData.object;
        }
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR2;
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2 = getAdListsBeanR;
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$name(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$name());
        int i3 = i + 1;
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$cycle(com_ciot_realm_db_ad_CycleBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$cycle(), i3, i2, map));
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$playingMode(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$playingMode());
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$createtime(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$description(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$id(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$screen(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$screen());
        if (i == i2) {
            com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$times((RealmList<TimesBean>) null);
        } else {
            RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$times();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$times(realmList);
            int size = realmGet$times.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_ad_TimesBeanRealmProxy.createDetachedCopy(realmGet$times.get(i4), i3, i2, map));
            }
        }
        if (i == i2) {
            com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$advertisements((RealmList<AdvertisementsBean>) null);
        } else {
            RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$advertisements();
            RealmList realmList2 = new RealmList();
            com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface.realmSet$advertisements(realmList2);
            int size2 = realmGet$advertisements.size();
            for (int i5 = 0; i5 < size2; i5++) {
                realmList2.add(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.createDetachedCopy(realmGet$advertisements.get(i5), i3, i2, map));
            }
        }
        return getAdListsBeanR2;
    }

    static GetAdListsBeanR update(Realm realm, GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo, GetAdListsBeanR getAdListsBeanR, GetAdListsBeanR getAdListsBeanR2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        GetAdListsBeanRColumnInfo getAdListsBeanRColumnInfo2 = getAdListsBeanRColumnInfo;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface = getAdListsBeanR;
        com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2 = getAdListsBeanR2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(GetAdListsBeanR.class), set);
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.nameColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$name());
        CycleBean realmGet$cycle = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$cycle();
        if (realmGet$cycle == null) {
            osObjectBuilder.addNull(getAdListsBeanRColumnInfo2.cycleColKey);
        } else {
            CycleBean cycleBean = (CycleBean) map2.get(realmGet$cycle);
            if (cycleBean != null) {
                osObjectBuilder.addObject(getAdListsBeanRColumnInfo2.cycleColKey, cycleBean);
            } else {
                osObjectBuilder.addObject(getAdListsBeanRColumnInfo2.cycleColKey, com_ciot_realm_db_ad_CycleBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_CycleBeanRealmProxy.CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class), realmGet$cycle, true, map, set));
            }
        }
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.playingModeColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$playingMode());
        osObjectBuilder.addInteger(getAdListsBeanRColumnInfo2.createtimeColKey, Long.valueOf(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$createtime()));
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.descriptionColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(getAdListsBeanRColumnInfo2.idColKey, com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addInteger(getAdListsBeanRColumnInfo2.screenColKey, Integer.valueOf(com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$screen()));
        RealmList<TimesBean> realmGet$times = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$times();
        if (realmGet$times != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$times.size(); i++) {
                TimesBean timesBean = realmGet$times.get(i);
                TimesBean timesBean2 = (TimesBean) map2.get(timesBean);
                if (timesBean2 != null) {
                    realmList.add(timesBean2);
                } else {
                    realmList.add(com_ciot_realm_db_ad_TimesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_TimesBeanRealmProxy.TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class), timesBean, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(getAdListsBeanRColumnInfo2.timesColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(getAdListsBeanRColumnInfo2.timesColKey, new RealmList());
        }
        RealmList<AdvertisementsBean> realmGet$advertisements = com_ciot_realm_db_ad_getadlistsbeanrrealmproxyinterface2.realmGet$advertisements();
        if (realmGet$advertisements != null) {
            RealmList realmList2 = new RealmList();
            for (int i2 = 0; i2 < realmGet$advertisements.size(); i2++) {
                AdvertisementsBean advertisementsBean = realmGet$advertisements.get(i2);
                AdvertisementsBean advertisementsBean2 = (AdvertisementsBean) map2.get(advertisementsBean);
                if (advertisementsBean2 != null) {
                    realmList2.add(advertisementsBean2);
                } else {
                    realmList2.add(com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy.AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class), advertisementsBean, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(getAdListsBeanRColumnInfo2.advertisementsColKey, realmList2);
        } else {
            osObjectBuilder.addObjectList(getAdListsBeanRColumnInfo2.advertisementsColKey, new RealmList());
        }
        osObjectBuilder.updateExistingObject();
        return getAdListsBeanR;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }
}
