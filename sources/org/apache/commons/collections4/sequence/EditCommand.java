package org.apache.commons.collections4.sequence;

public abstract class EditCommand<T> {
    private final T object;

    public abstract void accept(CommandVisitor<T> commandVisitor);

    protected EditCommand(T t) {
        this.object = t;
    }

    /* access modifiers changed from: protected */
    public T getObject() {
        return this.object;
    }
}
