package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class JsonWriter implements Closeable, Flushable {
    int flattenStackSize = -1;
    String indent;
    boolean lenient;
    int[] pathIndices = new int[32];
    String[] pathNames = new String[32];
    boolean promoteValueToName;
    int[] scopes = new int[32];
    boolean serializeNulls;
    int stackSize = 0;

    public abstract JsonWriter beginArray() throws IOException;

    public abstract JsonWriter beginObject() throws IOException;

    public abstract JsonWriter endArray() throws IOException;

    public abstract JsonWriter endObject() throws IOException;

    public abstract JsonWriter name(String str) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    public abstract JsonWriter value(double d) throws IOException;

    public abstract JsonWriter value(long j) throws IOException;

    public abstract JsonWriter value(@Nullable Boolean bool) throws IOException;

    public abstract JsonWriter value(@Nullable Number number) throws IOException;

    public abstract JsonWriter value(@Nullable String str) throws IOException;

    public abstract JsonWriter value(BufferedSource bufferedSource) throws IOException;

    public abstract JsonWriter value(boolean z) throws IOException;

    @CheckReturnValue
    public static JsonWriter of(BufferedSink bufferedSink) {
        return new JsonUtf8Writer(bufferedSink);
    }

    JsonWriter() {
    }

    /* access modifiers changed from: package-private */
    public final int peekScope() {
        int i = this.stackSize;
        if (i != 0) {
            return this.scopes[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* access modifiers changed from: package-private */
    public final boolean checkStack() {
        int i = this.stackSize;
        int[] iArr = this.scopes;
        if (i != iArr.length) {
            return false;
        }
        if (i != 256) {
            this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.pathNames;
            this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.pathIndices;
            this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
            if (!(this instanceof JsonValueWriter)) {
                return true;
            }
            JsonValueWriter jsonValueWriter = (JsonValueWriter) this;
            jsonValueWriter.stack = Arrays.copyOf(jsonValueWriter.stack, jsonValueWriter.stack.length * 2);
            return true;
        }
        throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
    }

    /* access modifiers changed from: package-private */
    public final void pushScope(int i) {
        int[] iArr = this.scopes;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        iArr[i2] = i;
    }

    /* access modifiers changed from: package-private */
    public final void replaceTop(int i) {
        this.scopes[this.stackSize - 1] = i;
    }

    public void setIndent(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.indent = str;
    }

    @CheckReturnValue
    public final String getIndent() {
        String str = this.indent;
        return str != null ? str : "";
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    @CheckReturnValue
    public final boolean isLenient() {
        return this.lenient;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    @CheckReturnValue
    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    /* access modifiers changed from: package-private */
    public final void promoteValueToName() throws IOException {
        int peekScope = peekScope();
        if (peekScope == 5 || peekScope == 3) {
            this.promoteValueToName = true;
            return;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    @CheckReturnValue
    public final int beginFlatten() {
        int peekScope = peekScope();
        if (peekScope == 5 || peekScope == 3 || peekScope == 2 || peekScope == 1) {
            int i = this.flattenStackSize;
            this.flattenStackSize = this.stackSize;
            return i;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public final void endFlatten(int i) {
        this.flattenStackSize = i;
    }

    @CheckReturnValue
    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }
}
