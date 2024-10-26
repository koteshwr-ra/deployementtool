package io.realm;

import com.alibaba.android.arouter.compiler.utils.Consts;
import io.realm.internal.OsList;
import io.realm.internal.OsObjectStore;
import io.realm.internal.RealmObjectProxy;
import java.util.Locale;
import javax.annotation.Nullable;

/* compiled from: RealmList */
final class RealmModelListOperator<T> extends ManagedListOperator<T> {
    @Nullable
    private final String className;

    public boolean forRealmModel() {
        return true;
    }

    RealmModelListOperator(BaseRealm baseRealm, OsList osList, @Nullable Class<T> cls, @Nullable String str) {
        super(baseRealm, osList, cls);
        this.className = str;
    }

    public T get(int i) {
        return this.realm.get(this.clazz, this.className, this.osList.getUncheckedRow((long) i));
    }

    /* access modifiers changed from: protected */
    public void checkValidValue(@Nullable Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("RealmList does not accept null values.");
        } else if (!(obj instanceof RealmModel)) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Unacceptable value type. Acceptable: %1$s, actual: %2$s .", new Object[]{Consts.STRING, obj.getClass().getName()}));
        }
    }

    private void checkInsertIndex(int i) {
        int size = size();
        if (i < 0 || size < i) {
            throw new IndexOutOfBoundsException("Invalid index " + i + ", size is " + this.osList.size());
        }
    }

    public void appendValue(Object obj) {
        this.osList.addRow(((RealmObjectProxy) copyToRealmIfNeeded((RealmModel) obj)).realmGet$proxyState().getRow$realm().getObjectKey());
    }

    /* access modifiers changed from: protected */
    public void insertNull(int i) {
        throw new RuntimeException("Should not reach here.");
    }

    public void insertValue(int i, Object obj) {
        checkInsertIndex(i);
        this.osList.insertRow((long) i, ((RealmObjectProxy) copyToRealmIfNeeded((RealmModel) obj)).realmGet$proxyState().getRow$realm().getObjectKey());
    }

    /* access modifiers changed from: protected */
    public void setNull(int i) {
        throw new RuntimeException("Should not reach here.");
    }

    /* access modifiers changed from: protected */
    public void setValue(int i, Object obj) {
        this.osList.setRow((long) i, ((RealmObjectProxy) copyToRealmIfNeeded((RealmModel) obj)).realmGet$proxyState().getRow$realm().getObjectKey());
    }

    private <E extends RealmModel> E copyToRealmIfNeeded(E e) {
        if (e instanceof RealmObjectProxy) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) e;
            if (realmObjectProxy instanceof DynamicRealmObject) {
                String str = this.className;
                if (realmObjectProxy.realmGet$proxyState().getRealm$realm() == this.realm) {
                    String type = ((DynamicRealmObject) e).getType();
                    if (str.equals(type)) {
                        return e;
                    }
                    throw new IllegalArgumentException(String.format(Locale.US, "The object has a different type from list's. Type of the list is '%s', type of object is '%s'.", new Object[]{str, type}));
                } else if (this.realm.threadId == realmObjectProxy.realmGet$proxyState().getRealm$realm().threadId) {
                    throw new IllegalArgumentException("Cannot copy DynamicRealmObject between Realm instances.");
                } else {
                    throw new IllegalStateException("Cannot copy an object to a Realm instance created in another thread.");
                }
            } else if (realmObjectProxy.realmGet$proxyState().getRow$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(this.realm.getPath())) {
                if (this.realm == realmObjectProxy.realmGet$proxyState().getRealm$realm()) {
                    return e;
                }
                throw new IllegalArgumentException("Cannot copy an object from another Realm instance.");
            }
        }
        Realm realm = (Realm) this.realm;
        if (OsObjectStore.getPrimaryKeyForObject(realm.getSharedRealm(), realm.getConfiguration().getSchemaMediator().getSimpleClassName(e.getClass())) != null) {
            return realm.copyToRealmOrUpdate(e, new ImportFlag[0]);
        }
        return realm.copyToRealm(e, new ImportFlag[0]);
    }
}
