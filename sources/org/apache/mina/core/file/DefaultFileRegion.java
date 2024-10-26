package org.apache.mina.core.file;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class DefaultFileRegion implements FileRegion {
    private final FileChannel channel;
    private final long originalPosition;
    private long position;
    private long remainingBytes;

    public String getFilename() {
        return null;
    }

    public DefaultFileRegion(FileChannel fileChannel) throws IOException {
        this(fileChannel, 0, fileChannel.size());
    }

    public DefaultFileRegion(FileChannel fileChannel, long j, long j2) {
        if (fileChannel == null) {
            throw new IllegalArgumentException("channel can not be null");
        } else if (j < 0) {
            throw new IllegalArgumentException("position may not be less than 0");
        } else if (j2 >= 0) {
            this.channel = fileChannel;
            this.originalPosition = j;
            this.position = j;
            this.remainingBytes = j2;
        } else {
            throw new IllegalArgumentException("remainingBytes may not be less than 0");
        }
    }

    public long getWrittenBytes() {
        return this.position - this.originalPosition;
    }

    public long getRemainingBytes() {
        return this.remainingBytes;
    }

    public FileChannel getFileChannel() {
        return this.channel;
    }

    public long getPosition() {
        return this.position;
    }

    public void update(long j) {
        this.position += j;
        this.remainingBytes -= j;
    }
}
