package io.realm.internal.modules;

import android.util.JsonReader;
import io.realm.ImportFlag;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.Util;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class FilterableMediator extends RealmProxyMediator {
    private final Set<Class<? extends RealmModel>> allowedClasses;
    private final RealmProxyMediator originalMediator;

    public FilterableMediator(RealmProxyMediator realmProxyMediator, Collection<Class<? extends RealmModel>> collection) {
        this.originalMediator = realmProxyMediator;
        HashSet hashSet = new HashSet();
        if (realmProxyMediator != null) {
            Set<Class<? extends RealmModel>> modelClasses = realmProxyMediator.getModelClasses();
            for (Class next : collection) {
                if (modelClasses.contains(next)) {
                    hashSet.add(next);
                }
            }
        }
        this.allowedClasses = Collections.unmodifiableSet(hashSet);
    }

    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.originalMediator.getExpectedObjectSchemaInfoMap().entrySet()) {
            if (this.allowedClasses.contains(next.getKey())) {
                hashMap.put((Class) next.getKey(), (OsObjectSchemaInfo) next.getValue());
            }
        }
        return hashMap;
    }

    public ColumnInfo createColumnInfo(Class<? extends RealmModel> cls, OsSchemaInfo osSchemaInfo) {
        checkSchemaHasClass(cls);
        return this.originalMediator.createColumnInfo(cls, osSchemaInfo);
    }

    /* access modifiers changed from: protected */
    public String getSimpleClassNameImpl(Class<? extends RealmModel> cls) {
        checkSchemaHasClass(cls);
        return this.originalMediator.getSimpleClassName(cls);
    }

    public <E extends RealmModel> E newInstance(Class<E> cls, Object obj, Row row, ColumnInfo columnInfo, boolean z, List<String> list) {
        checkSchemaHasClass(cls);
        return this.originalMediator.newInstance(cls, obj, row, columnInfo, z, list);
    }

    public Set<Class<? extends RealmModel>> getModelClasses() {
        return this.allowedClasses;
    }

    public <E extends RealmModel> E copyOrUpdate(Realm realm, E e, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        checkSchemaHasClass(Util.getOriginalModelClass(e.getClass()));
        return this.originalMediator.copyOrUpdate(realm, e, z, map, set);
    }

    public void insert(Realm realm, RealmModel realmModel, Map<RealmModel, Long> map) {
        checkSchemaHasClass(Util.getOriginalModelClass(realmModel.getClass()));
        this.originalMediator.insert(realm, realmModel, map);
    }

    public void insert(Realm realm, Collection<? extends RealmModel> collection) {
        checkSchemaHasClass(Util.getOriginalModelClass(((RealmModel) collection.iterator().next()).getClass()));
        this.originalMediator.insert(realm, collection);
    }

    public void insertOrUpdate(Realm realm, RealmModel realmModel, Map<RealmModel, Long> map) {
        checkSchemaHasClass(Util.getOriginalModelClass(realmModel.getClass()));
        this.originalMediator.insertOrUpdate(realm, realmModel, map);
    }

    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> collection) {
        checkSchemaHasClass(Util.getOriginalModelClass(((RealmModel) collection.iterator().next()).getClass()));
        this.originalMediator.insertOrUpdate(realm, collection);
    }

    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> cls, Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        checkSchemaHasClass(cls);
        return this.originalMediator.createOrUpdateUsingJsonObject(cls, realm, jSONObject, z);
    }

    public <E extends RealmModel> E createUsingJsonStream(Class<E> cls, Realm realm, JsonReader jsonReader) throws IOException {
        checkSchemaHasClass(cls);
        return this.originalMediator.createUsingJsonStream(cls, realm, jsonReader);
    }

    public <E extends RealmModel> E createDetachedCopy(E e, int i, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        checkSchemaHasClass(Util.getOriginalModelClass(e.getClass()));
        return this.originalMediator.createDetachedCopy(e, i, map);
    }

    public boolean transformerApplied() {
        RealmProxyMediator realmProxyMediator = this.originalMediator;
        if (realmProxyMediator == null) {
            return true;
        }
        return realmProxyMediator.transformerApplied();
    }

    private void checkSchemaHasClass(Class<? extends RealmModel> cls) {
        if (!this.allowedClasses.contains(cls)) {
            throw new IllegalArgumentException(cls.getSimpleName() + " is not part of the schema for this Realm");
        }
    }
}
