package com.squareup.moshi;

import com.tencent.bugly.Bugly;
import java.io.IOException;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.BufferedSource;
import org.apache.log4j.spi.Configurator;

final class JsonUtf8Writer extends JsonWriter {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private String deferredName;
    private String separator = ":";
    private final BufferedSink sink;

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    JsonUtf8Writer(BufferedSink bufferedSink) {
        if (bufferedSink != null) {
            this.sink = bufferedSink;
            pushScope(6);
            return;
        }
        throw new NullPointerException("sink == null");
    }

    public void setIndent(String str) {
        super.setIndent(str);
        this.separator = !str.isEmpty() ? ": " : ":";
    }

    public JsonWriter beginArray() throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            return open(1, 2, "[");
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter endArray() throws IOException {
        return close(1, 2, "]");
    }

    public JsonWriter beginObject() throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            return open(3, 5, "{");
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter endObject() throws IOException {
        this.promoteValueToName = false;
        return close(3, 5, "}");
    }

    private JsonWriter open(int i, int i2, String str) throws IOException {
        if (this.stackSize == this.flattenStackSize && (this.scopes[this.stackSize - 1] == i || this.scopes[this.stackSize - 1] == i2)) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        beforeValue();
        checkStack();
        pushScope(i);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeUtf8(str);
        return this;
    }

    private JsonWriter close(int i, int i2, String str) throws IOException {
        int peekScope = peekScope();
        if (peekScope != i2 && peekScope != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else if (this.stackSize == (~this.flattenStackSize)) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        } else {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            if (peekScope == i2) {
                newline();
            }
            this.sink.writeUtf8(str);
            return this;
        }
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.stackSize != 0) {
            int peekScope = peekScope();
            if ((peekScope == 3 || peekScope == 5) && this.deferredName == null) {
                this.deferredName = str;
                this.pathNames[this.stackSize - 1] = str;
                this.promoteValueToName = false;
                return this;
            }
            throw new IllegalStateException("Nesting problem.");
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.sink, this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        if (this.promoteValueToName) {
            return name(str);
        }
        writeDeferredName();
        beforeValue();
        string(this.sink, str);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (!this.promoteValueToName) {
            if (this.deferredName != null) {
                if (this.serializeNulls) {
                    writeDeferredName();
                } else {
                    this.deferredName = null;
                    return this;
                }
            }
            beforeValue();
            this.sink.writeUtf8(Configurator.NULL);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(boolean z) throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(z ? "true" : Bugly.SDK_IS_DEV);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(Boolean bool) throws IOException {
        if (bool == null) {
            return nullValue();
        }
        return value(bool.booleanValue());
    }

    public JsonWriter value(double d) throws IOException {
        if (!this.lenient && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        } else if (this.promoteValueToName) {
            return name(Double.toString(d));
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(Double.toString(d));
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public JsonWriter value(long j) throws IOException {
        if (this.promoteValueToName) {
            return name(Long.toString(j));
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(Long.toString(j));
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter value(@Nullable Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        String obj = number.toString();
        if (!this.lenient && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        } else if (this.promoteValueToName) {
            return name(obj);
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(obj);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public JsonWriter value(BufferedSource bufferedSource) throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            beforeValue();
            this.sink.writeAll(bufferedSource);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
        throw new IllegalStateException("BufferedSource cannot be used as a map key in JSON at path " + getPath());
    }

    public void flush() throws IOException {
        if (this.stackSize != 0) {
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public void close() throws IOException {
        this.sink.close();
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.scopes[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    static void string(BufferedSink bufferedSink, String str) throws IOException {
        String str2;
        String[] strArr = REPLACEMENT_CHARS;
        bufferedSink.writeByte(34);
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i < i2) {
                bufferedSink.writeUtf8(str, i, i2);
            }
            bufferedSink.writeUtf8(str2);
            i = i2 + 1;
        }
        if (i < length) {
            bufferedSink.writeUtf8(str, i, length);
        }
        bufferedSink.writeByte(34);
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.sink.writeByte(10);
            int i = this.stackSize;
            for (int i2 = 1; i2 < i; i2++) {
                this.sink.writeUtf8(this.indent);
            }
        }
    }

    private void beforeName() throws IOException {
        int peekScope = peekScope();
        if (peekScope == 5) {
            this.sink.writeByte(44);
        } else if (peekScope != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        int peekScope = peekScope();
        if (peekScope == 1) {
            replaceTop(2);
            newline();
        } else if (peekScope == 2) {
            this.sink.writeByte(44);
            newline();
        } else if (peekScope != 4) {
            if (peekScope != 6) {
                if (peekScope != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            replaceTop(7);
        } else {
            this.sink.writeUtf8(this.separator);
            replaceTop(5);
        }
    }
}
