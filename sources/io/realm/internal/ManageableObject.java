package io.realm.internal;

public interface ManageableObject {
    boolean isFrozen();

    boolean isManaged();

    boolean isValid();
}
