package com.google.common.hash;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.Nullable;

public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private static final Strategy DEFAULT_STRATEGY = BloomFilterStrategies.MURMUR128_MITZ_64;
    /* access modifiers changed from: private */
    public final BloomFilterStrategies.BitArray bits;
    /* access modifiers changed from: private */
    public final Funnel<? super T> funnel;
    /* access modifiers changed from: private */
    public final int numHashFunctions;
    /* access modifiers changed from: private */
    public final Strategy strategy;

    interface Strategy extends Serializable {
        <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.BitArray bitArray);

        int ordinal();

        <T> boolean put(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.BitArray bitArray);
    }

    private BloomFilter(BloomFilterStrategies.BitArray bitArray, int i, Funnel<? super T> funnel2, Strategy strategy2) {
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", Integer.valueOf(i));
        Preconditions.checkArgument(i <= 255, "numHashFunctions (%s) must be <= 255", Integer.valueOf(i));
        this.bits = (BloomFilterStrategies.BitArray) Preconditions.checkNotNull(bitArray);
        this.numHashFunctions = i;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel2);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy2);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    /* access modifiers changed from: package-private */
    public long bitSize() {
        return this.bits.bitSize();
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        Preconditions.checkArgument(this.numHashFunctions == bloomFilter.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", Integer.valueOf(this.numHashFunctions), Integer.valueOf(bloomFilter.numHashFunctions));
        Preconditions.checkArgument(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", Long.valueOf(bitSize()), Long.valueOf(bloomFilter.bitSize()));
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, bloomFilter.funnel);
        this.bits.putAll(bloomFilter.bits);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        if (this.numHashFunctions != bloomFilter.numHashFunctions || !this.funnel.equals(bloomFilter.funnel) || !this.bits.equals(bloomFilter.bits) || !this.strategy.equals(bloomFilter.strategy)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i, double d) {
        return create(funnel2, i, d, DEFAULT_STRATEGY);
    }

    static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i, double d, Strategy strategy2) {
        Preconditions.checkNotNull(funnel2);
        Preconditions.checkArgument(i >= 0, "Expected insertions (%s) must be >= 0", Integer.valueOf(i));
        Preconditions.checkArgument(d > 0.0d, "False positive probability (%s) must be > 0.0", Double.valueOf(d));
        Preconditions.checkArgument(d < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(d));
        Preconditions.checkNotNull(strategy2);
        if (i == 0) {
            i = 1;
        }
        long j = (long) i;
        long optimalNumOfBits = optimalNumOfBits(j, d);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.BitArray(optimalNumOfBits), optimalNumOfHashFunctions(j, optimalNumOfBits), funnel2, strategy2);
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder(57);
            sb.append("Could not create BloomFilter of ");
            sb.append(optimalNumOfBits);
            sb.append(" bits");
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i) {
        return create(funnel2, i, 0.03d);
    }

    static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    static long optimalNumOfBits(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        SerialForm(BloomFilter<T> bloomFilter) {
            this.data = bloomFilter.bits.data;
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.BitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length);
        for (long writeLong : this.bits.data) {
            dataOutputStream.writeLong(writeLong);
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<T> funnel2) throws IOException {
        int i;
        int i2;
        int readInt;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel2, "Funnel");
        byte b = -1;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte readByte = dataInputStream.readByte();
            try {
                i2 = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e) {
                e = e;
                i2 = -1;
                b = readByte;
                i = -1;
                StringBuilder sb = new StringBuilder("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ".length() + 65);
                sb.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                sb.append(b);
                sb.append(" numHashFunctions: ");
                sb.append(i2);
                sb.append(" dataLength: ");
                sb.append(i);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e);
                throw iOException;
            }
            try {
                readInt = dataInputStream.readInt();
            } catch (RuntimeException e2) {
                e = e2;
                b = readByte;
                i = -1;
                StringBuilder sb2 = new StringBuilder("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ".length() + 65);
                sb2.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                sb2.append(b);
                sb2.append(" numHashFunctions: ");
                sb2.append(i2);
                sb2.append(" dataLength: ");
                sb2.append(i);
                IOException iOException2 = new IOException(sb2.toString());
                iOException2.initCause(e);
                throw iOException2;
            }
            try {
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[readByte];
                long[] jArr = new long[readInt];
                for (int i3 = 0; i3 < readInt; i3++) {
                    jArr[i3] = dataInputStream.readLong();
                }
                return new BloomFilter<>(new BloomFilterStrategies.BitArray(jArr), i2, funnel2, bloomFilterStrategies);
            } catch (RuntimeException e3) {
                e = e3;
                int i4 = readInt;
                b = readByte;
                i = i4;
                StringBuilder sb22 = new StringBuilder("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ".length() + 65);
                sb22.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                sb22.append(b);
                sb22.append(" numHashFunctions: ");
                sb22.append(i2);
                sb22.append(" dataLength: ");
                sb22.append(i);
                IOException iOException22 = new IOException(sb22.toString());
                iOException22.initCause(e);
                throw iOException22;
            }
        } catch (RuntimeException e4) {
            e = e4;
            i = -1;
            i2 = -1;
            StringBuilder sb222 = new StringBuilder("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ".length() + 65);
            sb222.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
            sb222.append(b);
            sb222.append(" numHashFunctions: ");
            sb222.append(i2);
            sb222.append(" dataLength: ");
            sb222.append(i);
            IOException iOException222 = new IOException(sb222.toString());
            iOException222.initCause(e);
            throw iOException222;
        }
    }
}
