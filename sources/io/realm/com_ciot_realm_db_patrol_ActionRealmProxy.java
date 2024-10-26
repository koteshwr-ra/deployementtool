package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.ciot.realm.db.patrol.Action;
import com.ciot.realm.db.patrol.MediaBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxy;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_patrol_ActionRealmProxy extends Action implements RealmObjectProxy, com_ciot_realm_db_patrol_ActionRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActionColumnInfo columnInfo;
    private RealmList<String> dataRealmList;
    private RealmList<MediaBean> mediaRealmList;
    private ProxyState<Action> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Action";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ActionColumnInfo extends ColumnInfo {
        long dataColKey;
        long enableColKey;
        long mediaColKey;

        ActionColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.enableColKey = addColumnDetails(Consts.VALUE_ENABLE, Consts.VALUE_ENABLE, objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
            this.mediaColKey = addColumnDetails("media", "media", objectSchemaInfo);
        }

        ActionColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ActionColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ActionColumnInfo actionColumnInfo = (ActionColumnInfo) columnInfo;
            ActionColumnInfo actionColumnInfo2 = (ActionColumnInfo) columnInfo2;
            actionColumnInfo2.enableColKey = actionColumnInfo.enableColKey;
            actionColumnInfo2.dataColKey = actionColumnInfo.dataColKey;
            actionColumnInfo2.mediaColKey = actionColumnInfo.mediaColKey;
        }
    }

    com_ciot_realm_db_patrol_ActionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActionColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Action> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public boolean realmGet$enable() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.enableColKey);
    }

    public void realmSet$enable(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.enableColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.enableColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public RealmList<String> realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.dataRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.dataColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.dataRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$data(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("data"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.dataColKey, RealmFieldType.STRING_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<String> it = realmList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addString(next);
                    }
                }
            }
        }
    }

    public RealmList<MediaBean> realmGet$media() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<MediaBean> realmList = this.mediaRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<MediaBean> realmList2 = new RealmList<>(MediaBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.mediaColKey), this.proxyState.getRealm$realm());
        this.mediaRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$media(RealmList<MediaBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("media")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<MediaBean> realmList2 = new RealmList<>();
                Iterator<MediaBean> it = realmList.iterator();
                while (it.hasNext()) {
                    MediaBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((MediaBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.mediaColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    MediaBean mediaBean = realmList.get(i);
                    this.proxyState.checkValidObject(mediaBean);
                    modelList.addRow(((RealmObjectProxy) mediaBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            MediaBean mediaBean2 = realmList.get(i);
            this.proxyState.checkValidObject(mediaBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) mediaBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 3, 0);
        builder.addPersistedProperty(Consts.VALUE_ENABLE, RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedValueListProperty("data", RealmFieldType.STRING_LIST, false);
        builder.addPersistedLinkProperty("media", RealmFieldType.LIST, com_ciot_realm_db_patrol_MediaBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActionColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ActionColumnInfo(osSchemaInfo);
    }

    public static Action createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(2);
        if (jSONObject.has("data")) {
            arrayList.add("data");
        }
        if (jSONObject.has("media")) {
            arrayList.add("media");
        }
        Action action = (Action) realm.createObjectInternal(Action.class, true, arrayList);
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
        if (jSONObject.has(Consts.VALUE_ENABLE)) {
            if (!jSONObject.isNull(Consts.VALUE_ENABLE)) {
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$enable(jSONObject.getBoolean(Consts.VALUE_ENABLE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
            }
        }
        ProxyUtils.setRealmListWithJsonObject(com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data(), jSONObject, "data");
        if (jSONObject.has("media")) {
            if (jSONObject.isNull("media")) {
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$media((RealmList<MediaBean>) null);
            } else {
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media().clear();
                JSONArray jSONArray = jSONObject.getJSONArray("media");
                for (int i = 0; i < jSONArray.length(); i++) {
                    com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media().add(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray.getJSONObject(i), z));
                }
            }
        }
        return action;
    }

    public static Action createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Action action = new Action();
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(Consts.VALUE_ENABLE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$enable(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
                }
            } else if (nextName.equals("data")) {
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$data(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else if (!nextName.equals("media")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$media((RealmList<MediaBean>) null);
            } else {
                com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$media(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media().add(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return (Action) realm.copyToRealm(action, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_ActionRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_ActionRealmProxy com_ciot_realm_db_patrol_actionrealmproxy = new com_ciot_realm_db_patrol_ActionRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_actionrealmproxy;
    }

    public static Action copyOrUpdate(Realm realm, ActionColumnInfo actionColumnInfo, Action action, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((action instanceof RealmObjectProxy) && !RealmObject.isFrozen(action)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) action;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return action;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(action);
        if (realmObjectProxy2 != null) {
            return (Action) realmObjectProxy2;
        }
        return copy(realm, actionColumnInfo, action, z, map, set);
    }

    public static Action copy(Realm realm, ActionColumnInfo actionColumnInfo, Action action, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(action);
        if (realmObjectProxy != null) {
            return (Action) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Action.class), set);
        osObjectBuilder.addBoolean(actionColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$enable()));
        osObjectBuilder.addStringList(actionColumnInfo.dataColKey, com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data());
        com_ciot_realm_db_patrol_ActionRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(action, newProxyInstance);
        RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media();
        if (realmGet$media != null) {
            RealmList<MediaBean> realmGet$media2 = newProxyInstance.realmGet$media();
            realmGet$media2.clear();
            for (int i = 0; i < realmGet$media.size(); i++) {
                MediaBean mediaBean = realmGet$media.get(i);
                MediaBean mediaBean2 = (MediaBean) map.get(mediaBean);
                if (mediaBean2 != null) {
                    realmGet$media2.add(mediaBean2);
                } else {
                    realmGet$media2.add(com_ciot_realm_db_patrol_MediaBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_MediaBeanRealmProxy.MediaBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaBean.class), mediaBean, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, Action action, Map<RealmModel, Long> map) {
        if ((action instanceof RealmObjectProxy) && !RealmObject.isFrozen(action)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) action;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Action.class);
        long nativePtr = table.getNativePtr();
        ActionColumnInfo actionColumnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class);
        long createRow = OsObject.createRow(table);
        map.put(action, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
        Table.nativeSetBoolean(nativePtr, actionColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$enable(), false);
        RealmList<String> realmGet$data = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            OsList osList = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.dataColKey);
            Iterator<String> it = realmGet$data.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        }
        RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media();
        if (realmGet$media != null) {
            OsList osList2 = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.mediaColKey);
            Iterator<MediaBean> it2 = realmGet$media.iterator();
            while (it2.hasNext()) {
                MediaBean next2 = it2.next();
                Long l = map.get(next2);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insert(realm, next2, map));
                }
                osList2.addRow(l.longValue());
            }
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Action.class);
        long nativePtr = table.getNativePtr();
        ActionColumnInfo actionColumnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class);
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (!map2.containsKey(action)) {
                if ((action instanceof RealmObjectProxy) && !RealmObject.isFrozen(action)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) action;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(action, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(action, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
                Table.nativeSetBoolean(nativePtr, actionColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$enable(), false);
                RealmList<String> realmGet$data = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    OsList osList = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.dataColKey);
                    Iterator<String> it2 = realmGet$data.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                }
                RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media();
                if (realmGet$media != null) {
                    OsList osList2 = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.mediaColKey);
                    Iterator<MediaBean> it3 = realmGet$media.iterator();
                    while (it3.hasNext()) {
                        MediaBean next2 = it3.next();
                        Long l = map2.get(next2);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insert(realm2, next2, map2));
                        }
                        osList2.addRow(l.longValue());
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Action action, Map<RealmModel, Long> map) {
        if ((action instanceof RealmObjectProxy) && !RealmObject.isFrozen(action)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) action;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Action.class);
        long nativePtr = table.getNativePtr();
        ActionColumnInfo actionColumnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class);
        long createRow = OsObject.createRow(table);
        map.put(action, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
        Table.nativeSetBoolean(nativePtr, actionColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$enable(), false);
        OsList osList = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.dataColKey);
        osList.removeAll();
        RealmList<String> realmGet$data = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Iterator<String> it = realmGet$data.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        }
        OsList osList2 = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.mediaColKey);
        RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media();
        if (realmGet$media == null || ((long) realmGet$media.size()) != osList2.size()) {
            osList2.removeAll();
            if (realmGet$media != null) {
                Iterator<MediaBean> it2 = realmGet$media.iterator();
                while (it2.hasNext()) {
                    MediaBean next2 = it2.next();
                    Long l = map.get(next2);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm, next2, map));
                    }
                    osList2.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$media.size();
            for (int i = 0; i < size; i++) {
                MediaBean mediaBean = realmGet$media.get(i);
                Long l2 = map.get(mediaBean);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm, mediaBean, map));
                }
                osList2.setRow((long) i, l2.longValue());
            }
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Action.class);
        long nativePtr = table.getNativePtr();
        ActionColumnInfo actionColumnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class);
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (!map2.containsKey(action)) {
                if ((action instanceof RealmObjectProxy) && !RealmObject.isFrozen(action)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) action;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(action, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(action, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action;
                Table.nativeSetBoolean(nativePtr, actionColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$enable(), false);
                OsList osList = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.dataColKey);
                osList.removeAll();
                RealmList<String> realmGet$data = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Iterator<String> it2 = realmGet$data.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                }
                OsList osList2 = new OsList(table.getUncheckedRow(createRow), actionColumnInfo.mediaColKey);
                RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$media();
                if (realmGet$media == null || ((long) realmGet$media.size()) != osList2.size()) {
                    osList2.removeAll();
                    if (realmGet$media != null) {
                        Iterator<MediaBean> it3 = realmGet$media.iterator();
                        while (it3.hasNext()) {
                            MediaBean next2 = it3.next();
                            Long l = map2.get(next2);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                            }
                            osList2.addRow(l.longValue());
                        }
                    }
                } else {
                    int size = realmGet$media.size();
                    for (int i = 0; i < size; i++) {
                        MediaBean mediaBean = realmGet$media.get(i);
                        Long l2 = map2.get(mediaBean);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_patrol_MediaBeanRealmProxy.insertOrUpdate(realm2, mediaBean, map2));
                        }
                        osList2.setRow((long) i, l2.longValue());
                    }
                }
            }
        }
    }

    public static Action createDetachedCopy(Action action, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Action action2;
        if (i > i2 || action == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(action);
        if (cacheData == null) {
            action2 = new Action();
            map.put(action, new RealmObjectProxy.CacheData(i, action2));
        } else if (i >= cacheData.minDepth) {
            return (Action) cacheData.object;
        } else {
            cacheData.minDepth = i;
            action2 = (Action) cacheData.object;
        }
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface = action2;
        com_ciot_realm_db_patrol_ActionRealmProxyInterface com_ciot_realm_db_patrol_actionrealmproxyinterface2 = action;
        com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$enable(com_ciot_realm_db_patrol_actionrealmproxyinterface2.realmGet$enable());
        com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$data(new RealmList());
        com_ciot_realm_db_patrol_actionrealmproxyinterface.realmGet$data().addAll(com_ciot_realm_db_patrol_actionrealmproxyinterface2.realmGet$data());
        if (i == i2) {
            com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$media((RealmList<MediaBean>) null);
        } else {
            RealmList<MediaBean> realmGet$media = com_ciot_realm_db_patrol_actionrealmproxyinterface2.realmGet$media();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_actionrealmproxyinterface.realmSet$media(realmList);
            int i3 = i + 1;
            int size = realmGet$media.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_MediaBeanRealmProxy.createDetachedCopy(realmGet$media.get(i4), i3, i2, map));
            }
        }
        return action2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        return "Action = proxy[" + "{enable:" + realmGet$enable() + "}" + "," + "{data:" + "RealmList<String>[" + realmGet$data().size() + "]" + "}" + "," + "{media:" + "RealmList<MediaBean>[" + realmGet$media().size() + "]" + "}" + "]";
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
        com_ciot_realm_db_patrol_ActionRealmProxy com_ciot_realm_db_patrol_actionrealmproxy = (com_ciot_realm_db_patrol_ActionRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_actionrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_actionrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_actionrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
