package org.apache.mina.filter.errorgenerating;

import com.alibaba.android.arouter.utils.Consts;
import java.util.Random;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorGeneratingFilter extends IoFilterAdapter {
    private int changeByteProbability = 0;
    private int duplicatePduProbability = 0;
    private int insertByteProbability = 0;
    private final Logger logger = LoggerFactory.getLogger(ErrorGeneratingFilter.class);
    private boolean manipulateReads = false;
    private boolean manipulateWrites = false;
    private int maxInsertByte = 10;
    private int removeByteProbability = 0;
    private int removePduProbability = 0;
    private int resendPduLasterProbability = 0;
    private Random rng = new Random();

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (this.manipulateWrites) {
            if (writeRequest.getMessage() instanceof IoBuffer) {
                manipulateIoBuffer(ioSession, (IoBuffer) writeRequest.getMessage());
                IoBuffer insertBytesToNewIoBuffer = insertBytesToNewIoBuffer(ioSession, (IoBuffer) writeRequest.getMessage());
                if (insertBytesToNewIoBuffer != null) {
                    writeRequest = new DefaultWriteRequest(insertBytesToNewIoBuffer, writeRequest.getFuture(), writeRequest.getDestination());
                }
            } else {
                if (this.duplicatePduProbability > this.rng.nextInt()) {
                    nextFilter.filterWrite(ioSession, writeRequest);
                }
                this.rng.nextInt();
                if (this.removePduProbability > this.rng.nextInt()) {
                    return;
                }
            }
        }
        nextFilter.filterWrite(ioSession, writeRequest);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        if (this.manipulateReads && (obj instanceof IoBuffer)) {
            IoBuffer ioBuffer = (IoBuffer) obj;
            manipulateIoBuffer(ioSession, ioBuffer);
            IoBuffer insertBytesToNewIoBuffer = insertBytesToNewIoBuffer(ioSession, ioBuffer);
            if (insertBytesToNewIoBuffer != null) {
                obj = insertBytesToNewIoBuffer;
            }
        }
        nextFilter.messageReceived(ioSession, obj);
    }

    private IoBuffer insertBytesToNewIoBuffer(IoSession ioSession, IoBuffer ioBuffer) {
        if (this.insertByteProbability <= this.rng.nextInt(1000)) {
            return null;
        }
        this.logger.info(ioBuffer.getHexDump());
        int nextInt = this.rng.nextInt(ioBuffer.remaining()) - 1;
        int nextInt2 = this.rng.nextInt(this.maxInsertByte - 1) + 1;
        IoBuffer allocate = IoBuffer.allocate(ioBuffer.remaining() + nextInt2);
        for (int i = 0; i < nextInt; i++) {
            allocate.put(ioBuffer.get());
        }
        for (int i2 = 0; i2 < nextInt2; i2++) {
            allocate.put((byte) this.rng.nextInt(256));
        }
        while (ioBuffer.remaining() > 0) {
            allocate.put(ioBuffer.get());
        }
        allocate.flip();
        this.logger.info("Inserted " + nextInt2 + " bytes.");
        this.logger.info(allocate.getHexDump());
        return allocate;
    }

    private void manipulateIoBuffer(IoSession ioSession, IoBuffer ioBuffer) {
        if (ioBuffer.remaining() > 0 && this.removeByteProbability > this.rng.nextInt(1000)) {
            this.logger.info(ioBuffer.getHexDump());
            int nextInt = this.rng.nextInt(ioBuffer.remaining());
            int nextInt2 = this.rng.nextInt(ioBuffer.remaining() - nextInt) + 1;
            if (nextInt2 == ioBuffer.remaining()) {
                nextInt2 = ioBuffer.remaining() - 1;
            }
            IoBuffer allocate = IoBuffer.allocate(ioBuffer.remaining() - nextInt2);
            for (int i = 0; i < nextInt; i++) {
                allocate.put(ioBuffer.get());
            }
            ioBuffer.skip(nextInt2);
            while (allocate.remaining() > 0) {
                allocate.put(ioBuffer.get());
            }
            allocate.flip();
            ioBuffer.rewind();
            ioBuffer.put(allocate);
            ioBuffer.flip();
            this.logger.info("Removed " + nextInt2 + " bytes at position " + nextInt + Consts.DOT);
            this.logger.info(ioBuffer.getHexDump());
        }
        if (ioBuffer.remaining() > 0 && this.changeByteProbability > this.rng.nextInt(1000)) {
            this.logger.info(ioBuffer.getHexDump());
            int nextInt3 = this.rng.nextInt(ioBuffer.remaining() - 1) + 1;
            byte[] bArr = new byte[nextInt3];
            this.rng.nextBytes(bArr);
            for (int i2 = 0; i2 < nextInt3; i2++) {
                ioBuffer.put(this.rng.nextInt(ioBuffer.remaining()), bArr[i2]);
            }
            this.logger.info("Modified " + nextInt3 + " bytes.");
            this.logger.info(ioBuffer.getHexDump());
        }
    }

    public int getChangeByteProbability() {
        return this.changeByteProbability;
    }

    public void setChangeByteProbability(int i) {
        this.changeByteProbability = i;
    }

    public int getDuplicatePduProbability() {
        return this.duplicatePduProbability;
    }

    public void setDuplicatePduProbability(int i) {
        this.duplicatePduProbability = i;
    }

    public int getInsertByteProbability() {
        return this.insertByteProbability;
    }

    public void setInsertByteProbability(int i) {
        this.insertByteProbability = i;
    }

    public boolean isManipulateReads() {
        return this.manipulateReads;
    }

    public void setManipulateReads(boolean z) {
        this.manipulateReads = z;
    }

    public boolean isManipulateWrites() {
        return this.manipulateWrites;
    }

    public void setManipulateWrites(boolean z) {
        this.manipulateWrites = z;
    }

    public int getRemoveByteProbability() {
        return this.removeByteProbability;
    }

    public void setRemoveByteProbability(int i) {
        this.removeByteProbability = i;
    }

    public int getRemovePduProbability() {
        return this.removePduProbability;
    }

    public void setRemovePduProbability(int i) {
        this.removePduProbability = i;
    }

    public int getResendPduLasterProbability() {
        return this.resendPduLasterProbability;
    }

    public void setResendPduLasterProbability(int i) {
        this.resendPduLasterProbability = i;
    }

    public int getMaxInsertByte() {
        return this.maxInsertByte;
    }

    public void setMaxInsertByte(int i) {
        this.maxInsertByte = i;
    }
}
