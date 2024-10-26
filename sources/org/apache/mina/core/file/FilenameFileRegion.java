package org.apache.mina.core.file;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilenameFileRegion extends DefaultFileRegion {
    private final File file;

    public FilenameFileRegion(File file2, FileChannel fileChannel) throws IOException {
        this(file2, fileChannel, 0, file2.length());
    }

    public FilenameFileRegion(File file2, FileChannel fileChannel, long j, long j2) {
        super(fileChannel, j, j2);
        if (file2 != null) {
            this.file = file2;
            return;
        }
        throw new IllegalArgumentException("file can not be null");
    }

    public String getFilename() {
        return this.file.getAbsolutePath();
    }
}
