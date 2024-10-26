package org.apache.log4j.lf5.viewer.configure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class MRUFileManager {
    private static final String CONFIG_FILE_NAME = "mru_file_manager";
    private static final int DEFAULT_MAX_SIZE = 3;
    private int _maxSize = 0;
    private LinkedList _mruFileList;

    public MRUFileManager() {
        load();
        setMaxSize(3);
    }

    public MRUFileManager(int i) {
        load();
        setMaxSize(i);
    }

    public void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(getFilename())));
            objectOutputStream.writeObject(this._mruFileList);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return this._mruFileList.size();
    }

    public Object getFile(int i) {
        if (i < size()) {
            return this._mruFileList.get(i);
        }
        return null;
    }

    public InputStream getInputStream(int i) throws IOException, FileNotFoundException {
        if (i >= size()) {
            return null;
        }
        Object file = getFile(i);
        if (file instanceof File) {
            return getInputStream((File) file);
        }
        return getInputStream((URL) file);
    }

    public void set(File file) {
        setMRU(file);
    }

    public void set(URL url) {
        setMRU(url);
    }

    public String[] getMRUFileList() {
        if (size() == 0) {
            return null;
        }
        String[] strArr = new String[size()];
        for (int i = 0; i < size(); i++) {
            Object file = getFile(i);
            if (file instanceof File) {
                strArr[i] = ((File) file).getAbsolutePath();
            } else {
                strArr[i] = file.toString();
            }
        }
        return strArr;
    }

    public void moveToTop(int i) {
        LinkedList linkedList = this._mruFileList;
        linkedList.add(0, linkedList.remove(i));
    }

    public static void createConfigurationDirectory() {
        String property = System.getProperty("user.home");
        String property2 = System.getProperty("file.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(property);
        stringBuffer.append(property2);
        stringBuffer.append("lf5");
        File file = new File(stringBuffer.toString());
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream(File file) throws IOException, FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream(URL url) throws IOException {
        return url.openStream();
    }

    /* access modifiers changed from: protected */
    public void setMRU(Object obj) {
        int indexOf = this._mruFileList.indexOf(obj);
        if (indexOf == -1) {
            this._mruFileList.add(0, obj);
            setMaxSize(this._maxSize);
            return;
        }
        moveToTop(indexOf);
    }

    /* access modifiers changed from: protected */
    public void load() {
        createConfigurationDirectory();
        File file = new File(getFilename());
        if (file.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                this._mruFileList = (LinkedList) objectInputStream.readObject();
                objectInputStream.close();
                Iterator it = this._mruFileList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!(next instanceof File) && !(next instanceof URL)) {
                        it.remove();
                    }
                }
            } catch (Exception unused) {
                this._mruFileList = new LinkedList();
            }
        } else {
            this._mruFileList = new LinkedList();
        }
    }

    /* access modifiers changed from: protected */
    public String getFilename() {
        String property = System.getProperty("user.home");
        String property2 = System.getProperty("file.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(property);
        stringBuffer.append(property2);
        stringBuffer.append("lf5");
        stringBuffer.append(property2);
        stringBuffer.append(CONFIG_FILE_NAME);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public void setMaxSize(int i) {
        if (i < this._mruFileList.size()) {
            for (int i2 = 0; i2 < this._mruFileList.size() - i; i2++) {
                this._mruFileList.removeLast();
            }
        }
        this._maxSize = i;
    }
}
