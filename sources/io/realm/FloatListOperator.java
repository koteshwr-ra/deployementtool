package io.realm;

import io.realm.internal.OsList;
import java.util.Locale;
import javax.annotation.Nullable;

/* compiled from: RealmList */
final class FloatListOperator extends ManagedListOperator<Float> {
    public boolean forRealmModel() {
        return false;
    }

    FloatListOperator(BaseRealm baseRealm, OsList osList, Class<Float> cls) {
        super(baseRealm, osList, cls);
    }

    @Nullable
    public Float get(int i) {
        return (Float) this.osList.getValue((long) i);
    }

    /* access modifiers changed from: protected */
    public void checkValidValue(@Nullable Object obj) {
        if (obj != null && !(obj instanceof Number)) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Unacceptable value type. Acceptable: %1$s, actual: %2$s .", new Object[]{"java.lang.Number", obj.getClass().getName()}));
        }
    }

    public void appendValue(Object obj) {
        this.osList.addFloat(((Number) obj).floatValue());
    }

    public void insertValue(int i, Object obj) {
        this.osList.insertFloat((long) i, ((Number) obj).floatValue());
    }

    /* access modifiers changed from: protected */
    public void setValue(int i, Object obj) {
        this.osList.setFloat((long) i, ((Number) obj).floatValue());
    }
}
