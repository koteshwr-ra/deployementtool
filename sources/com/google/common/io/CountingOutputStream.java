package com.google.common.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

public final class CountingOutputStream extends FilterOutputStream {
    private long count;

    public CountingOutputStream(@Nullable OutputStream outputStream) {
        super(outputStream);
    }

    public long getCount() {
        return this.count;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.count += (long) i2;
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        this.count++;
    }

    public void close() throws IOException {
        this.out.close();
    }
}
