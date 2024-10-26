package org.apache.mina.util.byteaccess;

import java.nio.ByteOrder;
import org.apache.mina.util.byteaccess.ByteArray;
import org.apache.mina.util.byteaccess.CompositeByteArray;

abstract class CompositeByteArrayRelativeBase {
    protected final CompositeByteArray cba;
    protected final ByteArray.Cursor cursor;

    /* access modifiers changed from: protected */
    public abstract void cursorPassedFirstComponent();

    public CompositeByteArrayRelativeBase(CompositeByteArray compositeByteArray) {
        this.cba = compositeByteArray;
        this.cursor = compositeByteArray.cursor(compositeByteArray.first(), new CompositeByteArray.CursorListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            public void enteredFirstComponent(int i, ByteArray byteArray) {
            }

            public void enteredLastComponent(int i, ByteArray byteArray) {
            }

            public void enteredPreviousComponent(int i, ByteArray byteArray) {
            }

            static {
                Class<CompositeByteArrayRelativeBase> cls = CompositeByteArrayRelativeBase.class;
            }

            public void enteredNextComponent(int i, ByteArray byteArray) {
                CompositeByteArrayRelativeBase.this.cursorPassedFirstComponent();
            }
        });
    }

    public final int getRemaining() {
        return this.cursor.getRemaining();
    }

    public final boolean hasRemaining() {
        return this.cursor.hasRemaining();
    }

    public ByteOrder order() {
        return this.cba.order();
    }

    public final void append(ByteArray byteArray) {
        this.cba.addLast(byteArray);
    }

    public final void free() {
        this.cba.free();
    }

    public final int getIndex() {
        return this.cursor.getIndex();
    }

    public final int last() {
        return this.cba.last();
    }
}
