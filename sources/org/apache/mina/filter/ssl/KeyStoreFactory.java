package org.apache.mina.filter.ssl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

public class KeyStoreFactory {
    private byte[] data = null;
    private char[] password = null;
    private String provider = null;
    private String type = "JKS";

    public KeyStore newInstance() throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore keyStore;
        if (this.data != null) {
            String str = this.provider;
            if (str == null) {
                keyStore = KeyStore.getInstance(this.type);
            } else {
                keyStore = KeyStore.getInstance(this.type, str);
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.data);
            try {
                keyStore.load(byteArrayInputStream, this.password);
                return keyStore;
            } finally {
                try {
                    byteArrayInputStream.close();
                } catch (IOException unused) {
                }
            }
        } else {
            throw new IllegalStateException("data property is not set.");
        }
    }

    public void setType(String str) {
        if (str != null) {
            this.type = str;
            return;
        }
        throw new IllegalArgumentException("type");
    }

    public void setPassword(String str) {
        if (str != null) {
            this.password = str.toCharArray();
        } else {
            this.password = null;
        }
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setData(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.data = bArr2;
    }

    private void setData(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read();
                if (read < 0) {
                    setData(byteArrayOutputStream.toByteArray());
                    try {
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                } else {
                    byteArrayOutputStream.write(read);
                }
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
        }
    }

    public void setDataFile(File file) throws IOException {
        setData((InputStream) new BufferedInputStream(new FileInputStream(file)));
    }

    public void setDataUrl(URL url) throws IOException {
        setData(url.openStream());
    }
}
