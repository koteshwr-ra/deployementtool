package com.google.common.hash;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.annotation.Nullable;

public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    /* access modifiers changed from: package-private */
    public abstract boolean equalsSameBits(HashCode hashCode);

    public abstract long padToLong();

    /* access modifiers changed from: package-private */
    public abstract void writeBytesToImpl(byte[] bArr, int i, int i2);

    HashCode() {
    }

    public int writeBytesTo(byte[] bArr, int i, int i2) {
        int min = Ints.min(i2, bits() / 8);
        Preconditions.checkPositionIndexes(i, i + min, bArr.length);
        writeBytesToImpl(bArr, i, min);
        return min;
    }

    /* access modifiers changed from: package-private */
    public byte[] getBytesInternal() {
        return asBytes();
    }

    public static HashCode fromInt(int i) {
        return new IntHashCode(i);
    }

    private static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final int hash;

        public int bits() {
            return 32;
        }

        IntHashCode(int i) {
            this.hash = i;
        }

        public byte[] asBytes() {
            int i = this.hash;
            return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
        }

        public int asInt() {
            return this.hash;
        }

        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        /* access modifiers changed from: package-private */
        public void writeBytesToImpl(byte[] bArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i + i3] = (byte) (this.hash >> (i3 * 8));
            }
        }

        /* access modifiers changed from: package-private */
        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asInt();
        }
    }

    public static HashCode fromLong(long j) {
        return new LongHashCode(j);
    }

    private static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final long hash;

        public int bits() {
            return 64;
        }

        LongHashCode(long j) {
            this.hash = j;
        }

        public byte[] asBytes() {
            long j = this.hash;
            return new byte[]{(byte) ((int) j), (byte) ((int) (j >> 8)), (byte) ((int) (j >> 16)), (byte) ((int) (j >> 24)), (byte) ((int) (j >> 32)), (byte) ((int) (j >> 40)), (byte) ((int) (j >> 48)), (byte) ((int) (j >> 56))};
        }

        public int asInt() {
            return (int) this.hash;
        }

        public long asLong() {
            return this.hash;
        }

        public long padToLong() {
            return this.hash;
        }

        /* access modifiers changed from: package-private */
        public void writeBytesToImpl(byte[] bArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i + i3] = (byte) ((int) (this.hash >> (i3 * 8)));
            }
        }

        /* access modifiers changed from: package-private */
        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asLong();
        }
    }

    public static HashCode fromBytes(byte[] bArr) {
        boolean z = true;
        if (bArr.length < 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "A HashCode must contain at least 1 byte.");
        return fromBytesNoCopy((byte[]) bArr.clone());
    }

    static HashCode fromBytesNoCopy(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    private static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        BytesHashCode(byte[] bArr) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        public int bits() {
            return this.bytes.length * 8;
        }

        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        public int asInt() {
            Preconditions.checkState(this.bytes.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(this.bytes.length));
            byte[] bArr = this.bytes;
            return ((bArr[3] & 255) << Ascii.CAN) | ((bArr[1] & 255) << 8) | (bArr[0] & 255) | ((bArr[2] & 255) << Ascii.DLE);
        }

        public long asLong() {
            Preconditions.checkState(this.bytes.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", Integer.valueOf(this.bytes.length));
            return padToLong();
        }

        public long padToLong() {
            long j = (long) (this.bytes[0] & 255);
            for (int i = 1; i < Math.min(this.bytes.length, 8); i++) {
                j |= (((long) this.bytes[i]) & 255) << (i * 8);
            }
            return j;
        }

        /* access modifiers changed from: package-private */
        public void writeBytesToImpl(byte[] bArr, int i, int i2) {
            System.arraycopy(this.bytes, 0, bArr, i, i2);
        }

        /* access modifiers changed from: package-private */
        public byte[] getBytesInternal() {
            return this.bytes;
        }

        /* access modifiers changed from: package-private */
        public boolean equalsSameBits(HashCode hashCode) {
            return MessageDigest.isEqual(this.bytes, hashCode.getBytesInternal());
        }
    }

    public static HashCode fromString(String str) {
        Preconditions.checkArgument(str.length() >= 2, "input string (%s) must have at least 2 characters", str);
        Preconditions.checkArgument(str.length() % 2 == 0, "input string (%s) must have an even number of characters", str);
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((decode(str.charAt(i)) << 4) + decode(str.charAt(i + 1)));
        }
        return fromBytesNoCopy(bArr);
    }

    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("Illegal hexadecimal character: ");
        sb.append(c);
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof HashCode)) {
            return false;
        }
        HashCode hashCode = (HashCode) obj;
        if (bits() != hashCode.bits() || !equalsSameBits(hashCode)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] asBytes = asBytes();
        byte b = asBytes[0] & 255;
        for (int i = 1; i < asBytes.length; i++) {
            b |= (asBytes[i] & 255) << (i * 8);
        }
        return b;
    }

    public final String toString() {
        byte[] asBytes = asBytes();
        StringBuilder sb = new StringBuilder(asBytes.length * 2);
        for (byte b : asBytes) {
            sb.append(hexDigits[(b >> 4) & 15]);
            sb.append(hexDigits[b & Ascii.SI]);
        }
        return sb.toString();
    }
}
