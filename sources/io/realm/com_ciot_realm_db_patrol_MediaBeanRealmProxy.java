package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.base.constant.FileConstant;
import com.ciot.realm.db.patrol.MediaBean;
import com.ciot.realm.db.patrol.Resource;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy;
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
import org.apache.log4j.spi.Configurator;

public class com_ciot_realm_db_patrol_MediaBeanRealmProxy extends MediaBean implements RealmObjectProxy, com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<Resource> audioRealmList;
    private MediaBeanColumnInfo columnInfo;
    private RealmList<Resource> pictureRealmList;
    private ProxyState<MediaBean> proxyState;
    private RealmList<Resource> videoCoverRealmList;
    private RealmList<Resource> videoRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "MediaBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class MediaBeanColumnInfo extends ColumnInfo {
        long actionColKey;
        long audioColKey;
        long broadcastColKey;
        long countColKey;
        long idColKey;
        long kindColKey;
        long pictureColKey;
        long placeidColKey;
        long textColKey;
        long titleColKey;
        long typeColKey;
        long videoColKey;
        long videoCoverColKey;

        MediaBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.placeidColKey = addColumnDetails("placeid", "placeid", objectSchemaInfo);
            this.kindColKey = addColumnDetails("kind", "kind", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.titleColKey = addColumnDetails("title", "title", objectSchemaInfo);
            this.textColKey = addColumnDetails("text", "text", objectSchemaInfo);
            this.broadcastColKey = addColumnDetails("broadcast", "broadcast", objectSchemaInfo);
            this.actionColKey = addColumnDetails("action", "action", objectSchemaInfo);
            this.countColKey = addColumnDetails("count", "count", objectSchemaInfo);
            this.pictureColKey = addColumnDetails("picture", "picture", objectSchemaInfo);
            this.audioColKey = addColumnDetails("audio", "audio", objectSchemaInfo);
            this.videoColKey = addColumnDetails(FileConstant.VIDEO_FILE_NAME, FileConstant.VIDEO_FILE_NAME, objectSchemaInfo);
            this.videoCoverColKey = addColumnDetails("videoCover", "videoCover", objectSchemaInfo);
        }

        MediaBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new MediaBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            MediaBeanColumnInfo mediaBeanColumnInfo = (MediaBeanColumnInfo) columnInfo;
            MediaBeanColumnInfo mediaBeanColumnInfo2 = (MediaBeanColumnInfo) columnInfo2;
            mediaBeanColumnInfo2.idColKey = mediaBeanColumnInfo.idColKey;
            mediaBeanColumnInfo2.placeidColKey = mediaBeanColumnInfo.placeidColKey;
            mediaBeanColumnInfo2.kindColKey = mediaBeanColumnInfo.kindColKey;
            mediaBeanColumnInfo2.typeColKey = mediaBeanColumnInfo.typeColKey;
            mediaBeanColumnInfo2.titleColKey = mediaBeanColumnInfo.titleColKey;
            mediaBeanColumnInfo2.textColKey = mediaBeanColumnInfo.textColKey;
            mediaBeanColumnInfo2.broadcastColKey = mediaBeanColumnInfo.broadcastColKey;
            mediaBeanColumnInfo2.actionColKey = mediaBeanColumnInfo.actionColKey;
            mediaBeanColumnInfo2.countColKey = mediaBeanColumnInfo.countColKey;
            mediaBeanColumnInfo2.pictureColKey = mediaBeanColumnInfo.pictureColKey;
            mediaBeanColumnInfo2.audioColKey = mediaBeanColumnInfo.audioColKey;
            mediaBeanColumnInfo2.videoColKey = mediaBeanColumnInfo.videoColKey;
            mediaBeanColumnInfo2.videoCoverColKey = mediaBeanColumnInfo.videoCoverColKey;
        }
    }

    com_ciot_realm_db_patrol_MediaBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MediaBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<MediaBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    public String realmGet$placeid() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.placeidColKey);
    }

    public void realmSet$placeid(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.placeidColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.placeidColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.placeidColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.placeidColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$kind() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.kindColKey);
    }

    public void realmSet$kind(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.kindColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.kindColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.kindColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.kindColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeColKey);
    }

    public void realmSet$type(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.typeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.typeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$title() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.titleColKey);
    }

    public void realmSet$title(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.titleColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.titleColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.titleColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.titleColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$text() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.textColKey);
    }

    public void realmSet$text(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.textColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.textColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.textColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.textColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$broadcast() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.broadcastColKey);
    }

    public void realmSet$broadcast(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.broadcastColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.broadcastColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.broadcastColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.broadcastColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$action() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.actionColKey);
    }

    public void realmSet$action(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.actionColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.actionColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$count() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.countColKey);
    }

    public void realmSet$count(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.countColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.countColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public RealmList<Resource> realmGet$picture() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Resource> realmList = this.pictureRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Resource> realmList2 = new RealmList<>(Resource.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.pictureColKey), this.proxyState.getRealm$realm());
        this.pictureRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$picture(RealmList<Resource> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("picture")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Resource> realmList2 = new RealmList<>();
                Iterator<Resource> it = realmList.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Resource) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.pictureColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Resource resource = realmList.get(i);
                    this.proxyState.checkValidObject(resource);
                    modelList.addRow(((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Resource resource2 = realmList.get(i);
            this.proxyState.checkValidObject(resource2);
            modelList.setRow((long) i, ((RealmObjectProxy) resource2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public RealmList<Resource> realmGet$audio() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Resource> realmList = this.audioRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Resource> realmList2 = new RealmList<>(Resource.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.audioColKey), this.proxyState.getRealm$realm());
        this.audioRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$audio(RealmList<Resource> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("audio")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Resource> realmList2 = new RealmList<>();
                Iterator<Resource> it = realmList.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Resource) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.audioColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Resource resource = realmList.get(i);
                    this.proxyState.checkValidObject(resource);
                    modelList.addRow(((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Resource resource2 = realmList.get(i);
            this.proxyState.checkValidObject(resource2);
            modelList.setRow((long) i, ((RealmObjectProxy) resource2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public RealmList<Resource> realmGet$video() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Resource> realmList = this.videoRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Resource> realmList2 = new RealmList<>(Resource.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.videoColKey), this.proxyState.getRealm$realm());
        this.videoRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$video(RealmList<Resource> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains(FileConstant.VIDEO_FILE_NAME)) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Resource> realmList2 = new RealmList<>();
                Iterator<Resource> it = realmList.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Resource) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.videoColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Resource resource = realmList.get(i);
                    this.proxyState.checkValidObject(resource);
                    modelList.addRow(((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Resource resource2 = realmList.get(i);
            this.proxyState.checkValidObject(resource2);
            modelList.setRow((long) i, ((RealmObjectProxy) resource2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public RealmList<Resource> realmGet$videoCover() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Resource> realmList = this.videoCoverRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Resource> realmList2 = new RealmList<>(Resource.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.videoCoverColKey), this.proxyState.getRealm$realm());
        this.videoCoverRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$videoCover(RealmList<Resource> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("videoCover")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Resource> realmList2 = new RealmList<>();
                Iterator<Resource> it = realmList.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Resource) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.videoCoverColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Resource resource = realmList.get(i);
                    this.proxyState.checkValidObject(resource);
                    modelList.addRow(((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Resource resource2 = realmList.get(i);
            this.proxyState.checkValidObject(resource2);
            modelList.setRow((long) i, ((RealmObjectProxy) resource2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 13, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("placeid", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("kind", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("title", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("text", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("broadcast", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("action", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("count", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("picture", RealmFieldType.LIST, com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("audio", RealmFieldType.LIST, com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty(FileConstant.VIDEO_FILE_NAME, RealmFieldType.LIST, com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("videoCover", RealmFieldType.LIST, com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MediaBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new MediaBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.MediaBean createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 4
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r3 = com.ciot.realm.db.patrol.MediaBean.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r5 = com.ciot.realm.db.patrol.MediaBean.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy$MediaBeanColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy.MediaBeanColumnInfo) r4
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
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r4 = com.ciot.realm.db.patrol.MediaBean.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy r3 = new io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy     // Catch:{ all -> 0x0061 }
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
            java.lang.String r4 = "videoCover"
            java.lang.String r5 = "video"
            java.lang.String r6 = "audio"
            java.lang.String r7 = "picture"
            if (r3 != 0) goto L_0x00c2
            boolean r3 = r14.has(r7)
            if (r3 == 0) goto L_0x007a
            r0.add(r7)
        L_0x007a:
            boolean r3 = r14.has(r6)
            if (r3 == 0) goto L_0x0083
            r0.add(r6)
        L_0x0083:
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x008c
            r0.add(r5)
        L_0x008c:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x0095
            r0.add(r4)
        L_0x0095:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00ba
            boolean r3 = r14.isNull(r1)
            r8 = 1
            if (r3 == 0) goto L_0x00ac
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r1 = com.ciot.realm.db.patrol.MediaBean.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r8, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy) r3
            goto L_0x00c2
        L_0x00ac:
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r3 = com.ciot.realm.db.patrol.MediaBean.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r8, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy) r3
            goto L_0x00c2
        L_0x00ba:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00c2:
            r0 = r3
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface) r0
            java.lang.String r1 = "placeid"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x00de
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x00d7
            r0.realmSet$placeid(r2)
            goto L_0x00de
        L_0x00d7:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$placeid(r1)
        L_0x00de:
            java.lang.String r1 = "kind"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x00f7
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x00f0
            r0.realmSet$kind(r2)
            goto L_0x00f7
        L_0x00f0:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$kind(r1)
        L_0x00f7:
            java.lang.String r1 = "type"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0110
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x0109
            r0.realmSet$type(r2)
            goto L_0x0110
        L_0x0109:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$type(r1)
        L_0x0110:
            java.lang.String r1 = "title"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0129
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x0122
            r0.realmSet$title(r2)
            goto L_0x0129
        L_0x0122:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$title(r1)
        L_0x0129:
            java.lang.String r1 = "text"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0142
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x013b
            r0.realmSet$text(r2)
            goto L_0x0142
        L_0x013b:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$text(r1)
        L_0x0142:
            java.lang.String r1 = "broadcast"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x015b
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x0154
            r0.realmSet$broadcast(r2)
            goto L_0x015b
        L_0x0154:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$broadcast(r1)
        L_0x015b:
            java.lang.String r1 = "action"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0179
            boolean r8 = r14.isNull(r1)
            if (r8 != 0) goto L_0x0171
            int r1 = r14.getInt(r1)
            r0.realmSet$action(r1)
            goto L_0x0179
        L_0x0171:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'action' to null."
            r13.<init>(r14)
            throw r13
        L_0x0179:
            java.lang.String r1 = "count"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0197
            boolean r8 = r14.isNull(r1)
            if (r8 != 0) goto L_0x018f
            int r1 = r14.getInt(r1)
            r0.realmSet$count(r1)
            goto L_0x0197
        L_0x018f:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'count' to null."
            r13.<init>(r14)
            throw r13
        L_0x0197:
            boolean r1 = r14.has(r7)
            r8 = 0
            if (r1 == 0) goto L_0x01cc
            boolean r1 = r14.isNull(r7)
            if (r1 == 0) goto L_0x01a8
            r0.realmSet$picture(r2)
            goto L_0x01cc
        L_0x01a8:
            io.realm.RealmList r1 = r0.realmGet$picture()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r7)
            r7 = 0
        L_0x01b4:
            int r9 = r1.length()
            if (r7 >= r9) goto L_0x01cc
            org.json.JSONObject r9 = r1.getJSONObject(r7)
            com.ciot.realm.db.patrol.Resource r9 = io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(r13, r9, r15)
            io.realm.RealmList r10 = r0.realmGet$picture()
            r10.add(r9)
            int r7 = r7 + 1
            goto L_0x01b4
        L_0x01cc:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x0200
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x01dc
            r0.realmSet$audio(r2)
            goto L_0x0200
        L_0x01dc:
            io.realm.RealmList r1 = r0.realmGet$audio()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r6)
            r6 = 0
        L_0x01e8:
            int r7 = r1.length()
            if (r6 >= r7) goto L_0x0200
            org.json.JSONObject r7 = r1.getJSONObject(r6)
            com.ciot.realm.db.patrol.Resource r7 = io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(r13, r7, r15)
            io.realm.RealmList r9 = r0.realmGet$audio()
            r9.add(r7)
            int r6 = r6 + 1
            goto L_0x01e8
        L_0x0200:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x0234
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x0210
            r0.realmSet$video(r2)
            goto L_0x0234
        L_0x0210:
            io.realm.RealmList r1 = r0.realmGet$video()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r5)
            r5 = 0
        L_0x021c:
            int r6 = r1.length()
            if (r5 >= r6) goto L_0x0234
            org.json.JSONObject r6 = r1.getJSONObject(r5)
            com.ciot.realm.db.patrol.Resource r6 = io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(r13, r6, r15)
            io.realm.RealmList r7 = r0.realmGet$video()
            r7.add(r6)
            int r5 = r5 + 1
            goto L_0x021c
        L_0x0234:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x0267
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x0244
            r0.realmSet$videoCover(r2)
            goto L_0x0267
        L_0x0244:
            io.realm.RealmList r1 = r0.realmGet$videoCover()
            r1.clear()
            org.json.JSONArray r14 = r14.getJSONArray(r4)
        L_0x024f:
            int r1 = r14.length()
            if (r8 >= r1) goto L_0x0267
            org.json.JSONObject r1 = r14.getJSONObject(r8)
            com.ciot.realm.db.patrol.Resource r1 = io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            io.realm.RealmList r2 = r0.realmGet$videoCover()
            r2.add(r1)
            int r8 = r8 + 1
            goto L_0x024f
        L_0x0267:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.MediaBean");
    }

    public static MediaBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        MediaBean mediaBean = new MediaBean();
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("placeid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$placeid(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$placeid((String) null);
                }
            } else if (nextName.equals("kind")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$kind(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$kind((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$type((String) null);
                }
            } else if (nextName.equals("title")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$title(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$title((String) null);
                }
            } else if (nextName.equals("text")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$text(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$text((String) null);
                }
            } else if (nextName.equals("broadcast")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$broadcast(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$broadcast((String) null);
                }
            } else if (nextName.equals("action")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$action(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'action' to null.");
                }
            } else if (nextName.equals("count")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$count(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'count' to null.");
                }
            } else if (nextName.equals("picture")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$picture((RealmList<Resource>) null);
                } else {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$picture(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture().add(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals("audio")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$audio((RealmList<Resource>) null);
                } else {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$audio(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio().add(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals(FileConstant.VIDEO_FILE_NAME)) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$video((RealmList<Resource>) null);
                } else {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$video(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video().add(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("videoCover")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$videoCover((RealmList<Resource>) null);
            } else {
                com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$videoCover(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover().add(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (MediaBean) realm.copyToRealm(mediaBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_MediaBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_MediaBeanRealmProxy com_ciot_realm_db_patrol_mediabeanrealmproxy = new com_ciot_realm_db_patrol_MediaBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_mediabeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.MediaBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy.MediaBeanColumnInfo r9, com.ciot.realm.db.patrol.MediaBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.MediaBean r1 = (com.ciot.realm.db.patrol.MediaBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.MediaBean> r2 = com.ciot.realm.db.patrol.MediaBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.MediaBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.MediaBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy$MediaBeanColumnInfo, com.ciot.realm.db.patrol.MediaBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.MediaBean");
    }

    public static MediaBean copy(Realm realm, MediaBeanColumnInfo mediaBeanColumnInfo, MediaBean mediaBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Realm realm2 = realm;
        MediaBeanColumnInfo mediaBeanColumnInfo2 = mediaBeanColumnInfo;
        MediaBean mediaBean2 = mediaBean;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        RealmObjectProxy realmObjectProxy = map2.get(mediaBean2);
        if (realmObjectProxy != null) {
            return (MediaBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MediaBean.class), set);
        osObjectBuilder.addString(mediaBeanColumnInfo2.idColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(mediaBeanColumnInfo2.placeidColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$placeid());
        osObjectBuilder.addString(mediaBeanColumnInfo2.kindColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$kind());
        osObjectBuilder.addString(mediaBeanColumnInfo2.typeColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(mediaBeanColumnInfo2.titleColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$title());
        osObjectBuilder.addString(mediaBeanColumnInfo2.textColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$text());
        osObjectBuilder.addString(mediaBeanColumnInfo2.broadcastColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$broadcast());
        osObjectBuilder.addInteger(mediaBeanColumnInfo2.actionColKey, Integer.valueOf(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$action()));
        osObjectBuilder.addInteger(mediaBeanColumnInfo2.countColKey, Integer.valueOf(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$count()));
        com_ciot_realm_db_patrol_MediaBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map2.put(mediaBean2, newProxyInstance);
        RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture();
        if (realmGet$picture != null) {
            RealmList<Resource> realmGet$picture2 = newProxyInstance.realmGet$picture();
            realmGet$picture2.clear();
            for (int i = 0; i < realmGet$picture.size(); i++) {
                Resource resource = realmGet$picture.get(i);
                Resource resource2 = (Resource) map2.get(resource);
                if (resource2 != null) {
                    realmGet$picture2.add(resource2);
                } else {
                    realmGet$picture2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource, z, map, set));
                }
            }
        }
        RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio();
        if (realmGet$audio != null) {
            RealmList<Resource> realmGet$audio2 = newProxyInstance.realmGet$audio();
            realmGet$audio2.clear();
            for (int i2 = 0; i2 < realmGet$audio.size(); i2++) {
                Resource resource3 = realmGet$audio.get(i2);
                Resource resource4 = (Resource) map2.get(resource3);
                if (resource4 != null) {
                    realmGet$audio2.add(resource4);
                } else {
                    realmGet$audio2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource3, z, map, set));
                }
            }
        }
        RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video();
        if (realmGet$video != null) {
            RealmList<Resource> realmGet$video2 = newProxyInstance.realmGet$video();
            realmGet$video2.clear();
            for (int i3 = 0; i3 < realmGet$video.size(); i3++) {
                Resource resource5 = realmGet$video.get(i3);
                Resource resource6 = (Resource) map2.get(resource5);
                if (resource6 != null) {
                    realmGet$video2.add(resource6);
                } else {
                    realmGet$video2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource5, z, map, set));
                }
            }
        }
        RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover();
        if (realmGet$videoCover != null) {
            RealmList<Resource> realmGet$videoCover2 = newProxyInstance.realmGet$videoCover();
            realmGet$videoCover2.clear();
            for (int i4 = 0; i4 < realmGet$videoCover.size(); i4++) {
                Resource resource7 = realmGet$videoCover.get(i4);
                Resource resource8 = (Resource) map2.get(resource7);
                if (resource8 != null) {
                    realmGet$videoCover2.add(resource8);
                } else {
                    realmGet$videoCover2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource7, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, MediaBean mediaBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        MediaBean mediaBean2 = mediaBean;
        Map<RealmModel, Long> map2 = map;
        if ((mediaBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(MediaBean.class);
        long nativePtr = table.getNativePtr();
        MediaBeanColumnInfo mediaBeanColumnInfo = (MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class);
        long j4 = mediaBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean2;
        String realmGet$id = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$id();
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
        map2.put(mediaBean2, Long.valueOf(j5));
        String realmGet$placeid = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$placeid();
        if (realmGet$placeid != null) {
            j2 = j5;
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.placeidColKey, j5, realmGet$placeid, false);
        } else {
            j2 = j5;
        }
        String realmGet$kind = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.kindColKey, j2, realmGet$kind, false);
        }
        String realmGet$type = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.typeColKey, j2, realmGet$type, false);
        }
        String realmGet$title = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.titleColKey, j2, realmGet$title, false);
        }
        String realmGet$text = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.textColKey, j2, realmGet$text, false);
        }
        String realmGet$broadcast = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$broadcast();
        if (realmGet$broadcast != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.broadcastColKey, j2, realmGet$broadcast, false);
        }
        long j6 = nativePtr;
        long j7 = j2;
        Table.nativeSetLong(j6, mediaBeanColumnInfo.actionColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$action(), false);
        Table.nativeSetLong(j6, mediaBeanColumnInfo.countColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$count(), false);
        RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture();
        if (realmGet$picture != null) {
            j3 = j2;
            OsList osList = new OsList(table.getUncheckedRow(j3), mediaBeanColumnInfo.pictureColKey);
            Iterator<Resource> it = realmGet$picture.iterator();
            while (it.hasNext()) {
                Resource next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        } else {
            j3 = j2;
        }
        RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio();
        if (realmGet$audio != null) {
            OsList osList2 = new OsList(table.getUncheckedRow(j3), mediaBeanColumnInfo.audioColKey);
            Iterator<Resource> it2 = realmGet$audio.iterator();
            while (it2.hasNext()) {
                Resource next2 = it2.next();
                Long l2 = map2.get(next2);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next2, map2));
                }
                osList2.addRow(l2.longValue());
            }
        }
        RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video();
        if (realmGet$video != null) {
            OsList osList3 = new OsList(table.getUncheckedRow(j3), mediaBeanColumnInfo.videoColKey);
            Iterator<Resource> it3 = realmGet$video.iterator();
            while (it3.hasNext()) {
                Resource next3 = it3.next();
                Long l3 = map2.get(next3);
                if (l3 == null) {
                    l3 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next3, map2));
                }
                osList3.addRow(l3.longValue());
            }
        }
        RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover();
        if (realmGet$videoCover != null) {
            OsList osList4 = new OsList(table.getUncheckedRow(j3), mediaBeanColumnInfo.videoCoverColKey);
            Iterator<Resource> it4 = realmGet$videoCover.iterator();
            while (it4.hasNext()) {
                Resource next4 = it4.next();
                Long l4 = map2.get(next4);
                if (l4 == null) {
                    l4 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next4, map2));
                }
                osList4.addRow(l4.longValue());
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
        Table table = realm2.getTable(MediaBean.class);
        long nativePtr = table.getNativePtr();
        MediaBeanColumnInfo mediaBeanColumnInfo = (MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class);
        long j5 = mediaBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            MediaBean mediaBean = (MediaBean) it.next();
            if (!map2.containsKey(mediaBean)) {
                if ((mediaBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(mediaBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean;
                String realmGet$id = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$id();
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
                map2.put(mediaBean, Long.valueOf(j6));
                String realmGet$placeid = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$placeid();
                if (realmGet$placeid != null) {
                    j3 = j6;
                    j2 = j5;
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.placeidColKey, j6, realmGet$placeid, false);
                } else {
                    j3 = j6;
                    j2 = j5;
                }
                String realmGet$kind = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.kindColKey, j3, realmGet$kind, false);
                }
                String realmGet$type = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.typeColKey, j3, realmGet$type, false);
                }
                String realmGet$title = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.titleColKey, j3, realmGet$title, false);
                }
                String realmGet$text = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.textColKey, j3, realmGet$text, false);
                }
                String realmGet$broadcast = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$broadcast();
                if (realmGet$broadcast != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.broadcastColKey, j3, realmGet$broadcast, false);
                }
                long j7 = j3;
                Table.nativeSetLong(nativePtr, mediaBeanColumnInfo.actionColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$action(), false);
                Table.nativeSetLong(nativePtr, mediaBeanColumnInfo.countColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$count(), false);
                RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture();
                if (realmGet$picture != null) {
                    j4 = j3;
                    OsList osList = new OsList(table.getUncheckedRow(j4), mediaBeanColumnInfo.pictureColKey);
                    Iterator<Resource> it2 = realmGet$picture.iterator();
                    while (it2.hasNext()) {
                        Resource next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j4 = j3;
                }
                RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio();
                if (realmGet$audio != null) {
                    OsList osList2 = new OsList(table.getUncheckedRow(j4), mediaBeanColumnInfo.audioColKey);
                    Iterator<Resource> it3 = realmGet$audio.iterator();
                    while (it3.hasNext()) {
                        Resource next2 = it3.next();
                        Long l2 = map2.get(next2);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next2, map2));
                        }
                        osList2.addRow(l2.longValue());
                    }
                }
                RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video();
                if (realmGet$video != null) {
                    OsList osList3 = new OsList(table.getUncheckedRow(j4), mediaBeanColumnInfo.videoColKey);
                    Iterator<Resource> it4 = realmGet$video.iterator();
                    while (it4.hasNext()) {
                        Resource next3 = it4.next();
                        Long l3 = map2.get(next3);
                        if (l3 == null) {
                            l3 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next3, map2));
                        }
                        osList3.addRow(l3.longValue());
                    }
                }
                RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover();
                if (realmGet$videoCover != null) {
                    OsList osList4 = new OsList(table.getUncheckedRow(j4), mediaBeanColumnInfo.videoCoverColKey);
                    Iterator<Resource> it5 = realmGet$videoCover.iterator();
                    while (it5.hasNext()) {
                        Resource next4 = it5.next();
                        Long l4 = map2.get(next4);
                        if (l4 == null) {
                            l4 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, next4, map2));
                        }
                        osList4.addRow(l4.longValue());
                    }
                }
                j5 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MediaBean mediaBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        MediaBean mediaBean2 = mediaBean;
        Map<RealmModel, Long> map2 = map;
        if ((mediaBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(MediaBean.class);
        long nativePtr = table.getNativePtr();
        MediaBeanColumnInfo mediaBeanColumnInfo = (MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class);
        long j3 = mediaBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean2;
        String realmGet$id = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j3);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
        }
        long j4 = j;
        map2.put(mediaBean2, Long.valueOf(j4));
        String realmGet$placeid = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$placeid();
        if (realmGet$placeid != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.placeidColKey, j4, realmGet$placeid, false);
        } else {
            j2 = j4;
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.placeidColKey, j2, false);
        }
        String realmGet$kind = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.kindColKey, j2, realmGet$kind, false);
        } else {
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.kindColKey, j2, false);
        }
        String realmGet$type = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.typeColKey, j2, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.typeColKey, j2, false);
        }
        String realmGet$title = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.titleColKey, j2, realmGet$title, false);
        } else {
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.titleColKey, j2, false);
        }
        String realmGet$text = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.textColKey, j2, realmGet$text, false);
        } else {
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.textColKey, j2, false);
        }
        String realmGet$broadcast = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$broadcast();
        if (realmGet$broadcast != null) {
            Table.nativeSetString(nativePtr, mediaBeanColumnInfo.broadcastColKey, j2, realmGet$broadcast, false);
        } else {
            Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.broadcastColKey, j2, false);
        }
        long j5 = nativePtr;
        long j6 = j2;
        Table.nativeSetLong(j5, mediaBeanColumnInfo.actionColKey, j6, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$action(), false);
        Table.nativeSetLong(j5, mediaBeanColumnInfo.countColKey, j6, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$count(), false);
        long j7 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j7), mediaBeanColumnInfo.pictureColKey);
        RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture();
        if (realmGet$picture == null || ((long) realmGet$picture.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$picture != null) {
                Iterator<Resource> it = realmGet$picture.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    Long l = map2.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$picture.size();
            for (int i = 0; i < size; i++) {
                Resource resource = realmGet$picture.get(i);
                Long l2 = map2.get(resource);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource, map2));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        OsList osList2 = new OsList(table.getUncheckedRow(j7), mediaBeanColumnInfo.audioColKey);
        RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio();
        if (realmGet$audio == null || ((long) realmGet$audio.size()) != osList2.size()) {
            osList2.removeAll();
            if (realmGet$audio != null) {
                Iterator<Resource> it2 = realmGet$audio.iterator();
                while (it2.hasNext()) {
                    Resource next2 = it2.next();
                    Long l3 = map2.get(next2);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next2, map2));
                    }
                    osList2.addRow(l3.longValue());
                }
            }
        } else {
            int size2 = realmGet$audio.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Resource resource2 = realmGet$audio.get(i2);
                Long l4 = map2.get(resource2);
                if (l4 == null) {
                    l4 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource2, map2));
                }
                osList2.setRow((long) i2, l4.longValue());
            }
        }
        OsList osList3 = new OsList(table.getUncheckedRow(j7), mediaBeanColumnInfo.videoColKey);
        RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video();
        if (realmGet$video == null || ((long) realmGet$video.size()) != osList3.size()) {
            osList3.removeAll();
            if (realmGet$video != null) {
                Iterator<Resource> it3 = realmGet$video.iterator();
                while (it3.hasNext()) {
                    Resource next3 = it3.next();
                    Long l5 = map2.get(next3);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next3, map2));
                    }
                    osList3.addRow(l5.longValue());
                }
            }
        } else {
            int size3 = realmGet$video.size();
            for (int i3 = 0; i3 < size3; i3++) {
                Resource resource3 = realmGet$video.get(i3);
                Long l6 = map2.get(resource3);
                if (l6 == null) {
                    l6 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource3, map2));
                }
                osList3.setRow((long) i3, l6.longValue());
            }
        }
        OsList osList4 = new OsList(table.getUncheckedRow(j7), mediaBeanColumnInfo.videoCoverColKey);
        RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover();
        if (realmGet$videoCover == null || ((long) realmGet$videoCover.size()) != osList4.size()) {
            osList4.removeAll();
            if (realmGet$videoCover != null) {
                Iterator<Resource> it4 = realmGet$videoCover.iterator();
                while (it4.hasNext()) {
                    Resource next4 = it4.next();
                    Long l7 = map2.get(next4);
                    if (l7 == null) {
                        l7 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next4, map2));
                    }
                    osList4.addRow(l7.longValue());
                }
            }
        } else {
            int size4 = realmGet$videoCover.size();
            for (int i4 = 0; i4 < size4; i4++) {
                Resource resource4 = realmGet$videoCover.get(i4);
                Long l8 = map2.get(resource4);
                if (l8 == null) {
                    l8 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource4, map2));
                }
                osList4.setRow((long) i4, l8.longValue());
            }
        }
        return j7;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(MediaBean.class);
        long nativePtr = table.getNativePtr();
        MediaBeanColumnInfo mediaBeanColumnInfo = (MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class);
        long j5 = mediaBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            MediaBean mediaBean = (MediaBean) it.next();
            if (!map2.containsKey(mediaBean)) {
                if ((mediaBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(mediaBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean;
                String realmGet$id = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j5);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j5, realmGet$id);
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j5, realmGet$id);
                }
                long j6 = j;
                map2.put(mediaBean, Long.valueOf(j6));
                String realmGet$placeid = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$placeid();
                if (realmGet$placeid != null) {
                    j3 = j6;
                    j2 = j5;
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.placeidColKey, j6, realmGet$placeid, false);
                } else {
                    j3 = j6;
                    j2 = j5;
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.placeidColKey, j6, false);
                }
                String realmGet$kind = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.kindColKey, j3, realmGet$kind, false);
                } else {
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.kindColKey, j3, false);
                }
                String realmGet$type = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.typeColKey, j3, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.typeColKey, j3, false);
                }
                String realmGet$title = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.titleColKey, j3, realmGet$title, false);
                } else {
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.titleColKey, j3, false);
                }
                String realmGet$text = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.textColKey, j3, realmGet$text, false);
                } else {
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.textColKey, j3, false);
                }
                String realmGet$broadcast = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$broadcast();
                if (realmGet$broadcast != null) {
                    Table.nativeSetString(nativePtr, mediaBeanColumnInfo.broadcastColKey, j3, realmGet$broadcast, false);
                } else {
                    Table.nativeSetNull(nativePtr, mediaBeanColumnInfo.broadcastColKey, j3, false);
                }
                long j7 = j3;
                Table.nativeSetLong(nativePtr, mediaBeanColumnInfo.actionColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$action(), false);
                Table.nativeSetLong(nativePtr, mediaBeanColumnInfo.countColKey, j7, (long) com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$count(), false);
                long j8 = j3;
                OsList osList = new OsList(table.getUncheckedRow(j8), mediaBeanColumnInfo.pictureColKey);
                RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$picture();
                if (realmGet$picture == null || ((long) realmGet$picture.size()) != osList.size()) {
                    osList.removeAll();
                    if (realmGet$picture != null) {
                        Iterator<Resource> it2 = realmGet$picture.iterator();
                        while (it2.hasNext()) {
                            Resource next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int i = 0;
                    for (int size = realmGet$picture.size(); i < size; size = size) {
                        Resource resource = realmGet$picture.get(i);
                        Long l2 = map2.get(resource);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                    }
                }
                OsList osList2 = new OsList(table.getUncheckedRow(j8), mediaBeanColumnInfo.audioColKey);
                RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$audio();
                if (realmGet$audio == null || ((long) realmGet$audio.size()) != osList2.size()) {
                    j4 = nativePtr;
                    osList2.removeAll();
                    if (realmGet$audio != null) {
                        Iterator<Resource> it3 = realmGet$audio.iterator();
                        while (it3.hasNext()) {
                            Resource next2 = it3.next();
                            Long l3 = map2.get(next2);
                            if (l3 == null) {
                                l3 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next2, map2));
                            }
                            osList2.addRow(l3.longValue());
                        }
                    }
                } else {
                    int size2 = realmGet$audio.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        Resource resource2 = realmGet$audio.get(i2);
                        Long l4 = map2.get(resource2);
                        if (l4 == null) {
                            l4 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource2, map2));
                        }
                        osList2.setRow((long) i2, l4.longValue());
                        i2++;
                        nativePtr = nativePtr;
                    }
                    j4 = nativePtr;
                }
                OsList osList3 = new OsList(table.getUncheckedRow(j8), mediaBeanColumnInfo.videoColKey);
                RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$video();
                if (realmGet$video == null || ((long) realmGet$video.size()) != osList3.size()) {
                    osList3.removeAll();
                    if (realmGet$video != null) {
                        Iterator<Resource> it4 = realmGet$video.iterator();
                        while (it4.hasNext()) {
                            Resource next3 = it4.next();
                            Long l5 = map2.get(next3);
                            if (l5 == null) {
                                l5 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next3, map2));
                            }
                            osList3.addRow(l5.longValue());
                        }
                    }
                } else {
                    int size3 = realmGet$video.size();
                    for (int i3 = 0; i3 < size3; i3++) {
                        Resource resource3 = realmGet$video.get(i3);
                        Long l6 = map2.get(resource3);
                        if (l6 == null) {
                            l6 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource3, map2));
                        }
                        osList3.setRow((long) i3, l6.longValue());
                    }
                }
                OsList osList4 = new OsList(table.getUncheckedRow(j8), mediaBeanColumnInfo.videoCoverColKey);
                RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmGet$videoCover();
                if (realmGet$videoCover == null || ((long) realmGet$videoCover.size()) != osList4.size()) {
                    osList4.removeAll();
                    if (realmGet$videoCover != null) {
                        Iterator<Resource> it5 = realmGet$videoCover.iterator();
                        while (it5.hasNext()) {
                            Resource next4 = it5.next();
                            Long l7 = map2.get(next4);
                            if (l7 == null) {
                                l7 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, next4, map2));
                            }
                            osList4.addRow(l7.longValue());
                        }
                    }
                } else {
                    int size4 = realmGet$videoCover.size();
                    for (int i4 = 0; i4 < size4; i4++) {
                        Resource resource4 = realmGet$videoCover.get(i4);
                        Long l8 = map2.get(resource4);
                        if (l8 == null) {
                            l8 = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, resource4, map2));
                        }
                        osList4.setRow((long) i4, l8.longValue());
                    }
                }
                j5 = j2;
                nativePtr = j4;
            }
        }
    }

    public static MediaBean createDetachedCopy(MediaBean mediaBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        MediaBean mediaBean2;
        if (i > i2 || mediaBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(mediaBean);
        if (cacheData == null) {
            mediaBean2 = new MediaBean();
            map.put(mediaBean, new RealmObjectProxy.CacheData(i, mediaBean2));
        } else if (i >= cacheData.minDepth) {
            return (MediaBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            mediaBean2 = (MediaBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean2;
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2 = mediaBean;
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$placeid(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$placeid());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$kind(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$kind());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$type(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$title(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$title());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$text(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$text());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$broadcast(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$broadcast());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$action(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$action());
        com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$count(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$count());
        if (i == i2) {
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$picture((RealmList<Resource>) null);
        } else {
            RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$picture();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$picture(realmList);
            int i3 = i + 1;
            int size = realmGet$picture.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy(realmGet$picture.get(i4), i3, i2, map));
            }
        }
        if (i == i2) {
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$audio((RealmList<Resource>) null);
        } else {
            RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$audio();
            RealmList realmList2 = new RealmList();
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$audio(realmList2);
            int i5 = i + 1;
            int size2 = realmGet$audio.size();
            for (int i6 = 0; i6 < size2; i6++) {
                realmList2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy(realmGet$audio.get(i6), i5, i2, map));
            }
        }
        if (i == i2) {
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$video((RealmList<Resource>) null);
        } else {
            RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$video();
            RealmList realmList3 = new RealmList();
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$video(realmList3);
            int i7 = i + 1;
            int size3 = realmGet$video.size();
            for (int i8 = 0; i8 < size3; i8++) {
                realmList3.add(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy(realmGet$video.get(i8), i7, i2, map));
            }
        }
        if (i == i2) {
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$videoCover((RealmList<Resource>) null);
        } else {
            RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$videoCover();
            RealmList realmList4 = new RealmList();
            com_ciot_realm_db_patrol_mediabeanrealmproxyinterface.realmSet$videoCover(realmList4);
            int i9 = i + 1;
            int size4 = realmGet$videoCover.size();
            for (int i10 = 0; i10 < size4; i10++) {
                realmList4.add(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy(realmGet$videoCover.get(i10), i9, i2, map));
            }
        }
        return mediaBean2;
    }

    static MediaBean update(Realm realm, MediaBeanColumnInfo mediaBeanColumnInfo, MediaBean mediaBean, MediaBean mediaBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        MediaBeanColumnInfo mediaBeanColumnInfo2 = mediaBeanColumnInfo;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface = mediaBean;
        com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2 = mediaBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MediaBean.class), set);
        osObjectBuilder.addString(mediaBeanColumnInfo2.idColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(mediaBeanColumnInfo2.placeidColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$placeid());
        osObjectBuilder.addString(mediaBeanColumnInfo2.kindColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$kind());
        osObjectBuilder.addString(mediaBeanColumnInfo2.typeColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$type());
        osObjectBuilder.addString(mediaBeanColumnInfo2.titleColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$title());
        osObjectBuilder.addString(mediaBeanColumnInfo2.textColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$text());
        osObjectBuilder.addString(mediaBeanColumnInfo2.broadcastColKey, com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$broadcast());
        osObjectBuilder.addInteger(mediaBeanColumnInfo2.actionColKey, Integer.valueOf(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$action()));
        osObjectBuilder.addInteger(mediaBeanColumnInfo2.countColKey, Integer.valueOf(com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$count()));
        RealmList<Resource> realmGet$picture = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$picture();
        if (realmGet$picture != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$picture.size(); i++) {
                Resource resource = realmGet$picture.get(i);
                Resource resource2 = (Resource) map2.get(resource);
                if (resource2 != null) {
                    realmList.add(resource2);
                } else {
                    realmList.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.pictureColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.pictureColKey, new RealmList());
        }
        RealmList<Resource> realmGet$audio = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$audio();
        if (realmGet$audio != null) {
            RealmList realmList2 = new RealmList();
            for (int i2 = 0; i2 < realmGet$audio.size(); i2++) {
                Resource resource3 = realmGet$audio.get(i2);
                Resource resource4 = (Resource) map2.get(resource3);
                if (resource4 != null) {
                    realmList2.add(resource4);
                } else {
                    realmList2.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource3, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.audioColKey, realmList2);
        } else {
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.audioColKey, new RealmList());
        }
        RealmList<Resource> realmGet$video = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$video();
        if (realmGet$video != null) {
            RealmList realmList3 = new RealmList();
            for (int i3 = 0; i3 < realmGet$video.size(); i3++) {
                Resource resource5 = realmGet$video.get(i3);
                Resource resource6 = (Resource) map2.get(resource5);
                if (resource6 != null) {
                    realmList3.add(resource6);
                } else {
                    realmList3.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource5, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.videoColKey, realmList3);
        } else {
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.videoColKey, new RealmList());
        }
        RealmList<Resource> realmGet$videoCover = com_ciot_realm_db_patrol_mediabeanrealmproxyinterface2.realmGet$videoCover();
        if (realmGet$videoCover != null) {
            RealmList realmList4 = new RealmList();
            for (int i4 = 0; i4 < realmGet$videoCover.size(); i4++) {
                Resource resource7 = realmGet$videoCover.get(i4);
                Resource resource8 = (Resource) map2.get(resource7);
                if (resource8 != null) {
                    realmList4.add(resource8);
                } else {
                    realmList4.add(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), resource7, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.videoCoverColKey, realmList4);
        } else {
            osObjectBuilder.addObjectList(mediaBeanColumnInfo2.videoCoverColKey, new RealmList());
        }
        osObjectBuilder.updateExistingObject();
        return mediaBean;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("MediaBean = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{placeid:");
        sb.append(realmGet$placeid() != null ? realmGet$placeid() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{kind:");
        sb.append(realmGet$kind() != null ? realmGet$kind() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type() != null ? realmGet$type() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{title:");
        sb.append(realmGet$title() != null ? realmGet$title() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{text:");
        sb.append(realmGet$text() != null ? realmGet$text() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{broadcast:");
        if (realmGet$broadcast() != null) {
            str = realmGet$broadcast();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{action:");
        sb.append(realmGet$action());
        sb.append("}");
        sb.append(",");
        sb.append("{count:");
        sb.append(realmGet$count());
        sb.append("}");
        sb.append(",");
        sb.append("{picture:");
        sb.append("RealmList<Resource>[");
        sb.append(realmGet$picture().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{audio:");
        sb.append("RealmList<Resource>[");
        sb.append(realmGet$audio().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{video:");
        sb.append("RealmList<Resource>[");
        sb.append(realmGet$video().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{videoCover:");
        sb.append("RealmList<Resource>[");
        sb.append(realmGet$videoCover().size());
        sb.append("]");
        sb.append("}");
        sb.append("]");
        return sb.toString();
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
        com_ciot_realm_db_patrol_MediaBeanRealmProxy com_ciot_realm_db_patrol_mediabeanrealmproxy = (com_ciot_realm_db_patrol_MediaBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_mediabeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_mediabeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_mediabeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
