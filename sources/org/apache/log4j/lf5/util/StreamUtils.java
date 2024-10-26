package org.apache.log4j.lf5.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamUtils {
    public static final int DEFAULT_BUFFER_SIZE = 2048;

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, 2048);
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int read = inputStream.read(bArr);
        while (read != -1) {
            outputStream.write(bArr, 0, read);
            read = inputStream.read(bArr);
        }
        outputStream.flush();
    }

    public static void copyThenClose(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
