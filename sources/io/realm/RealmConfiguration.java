package io.realm;

import android.content.Context;
import com.alibaba.android.arouter.utils.Consts;
import io.realm.Realm;
import io.realm.annotations.RealmModule;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmFileException;
import io.realm.internal.OsRealmConfig;
import io.realm.internal.RealmCore;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Util;
import io.realm.internal.modules.CompositeMediator;
import io.realm.internal.modules.FilterableMediator;
import io.realm.rx.RealmObservableFactory;
import io.realm.rx.RxObservableFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.commons.lang3.StringUtils;

public class RealmConfiguration {
    /* access modifiers changed from: private */
    public static final Object DEFAULT_MODULE;
    protected static final RealmProxyMediator DEFAULT_MODULE_MEDIATOR;
    public static final String DEFAULT_REALM_NAME = "default.realm";
    public static final int KEY_LENGTH = 64;
    private static Boolean rxJavaAvailable;
    private final String assetFilePath;
    private final String canonicalPath;
    private final CompactOnLaunchCallback compactOnLaunch;
    private final boolean deleteRealmIfMigrationNeeded;
    private final OsRealmConfig.Durability durability;
    private final Realm.Transaction initialDataTransaction;
    private final boolean isRecoveryConfiguration;
    private final byte[] key;
    private final long maxNumberOfActiveVersions;
    private final RealmMigration migration;
    private final boolean readOnly;
    private final File realmDirectory;
    private final String realmFileName;
    private final RxObservableFactory rxObservableFactory;
    private final RealmProxyMediator schemaMediator;
    private final long schemaVersion;

    /* access modifiers changed from: package-private */
    public boolean isSyncConfiguration() {
        return false;
    }

    static {
        Object defaultModule = Realm.getDefaultModule();
        DEFAULT_MODULE = defaultModule;
        if (defaultModule != null) {
            RealmProxyMediator moduleMediator = getModuleMediator(defaultModule.getClass().getCanonicalName());
            if (moduleMediator.transformerApplied()) {
                DEFAULT_MODULE_MEDIATOR = moduleMediator;
                return;
            }
            throw new ExceptionInInitializerError("RealmTransformer doesn't seem to be applied. Please update the project configuration to use the Realm Gradle plugin. See https://realm.io/news/android-installation-change/");
        }
        DEFAULT_MODULE_MEDIATOR = null;
    }

    protected RealmConfiguration(@Nullable File file, @Nullable String str, String str2, @Nullable String str3, @Nullable byte[] bArr, long j, @Nullable RealmMigration realmMigration, boolean z, OsRealmConfig.Durability durability2, RealmProxyMediator realmProxyMediator, @Nullable RxObservableFactory rxObservableFactory2, @Nullable Realm.Transaction transaction, boolean z2, @Nullable CompactOnLaunchCallback compactOnLaunchCallback, boolean z3, long j2) {
        this.realmDirectory = file;
        this.realmFileName = str;
        this.canonicalPath = str2;
        this.assetFilePath = str3;
        this.key = bArr;
        this.schemaVersion = j;
        this.migration = realmMigration;
        this.deleteRealmIfMigrationNeeded = z;
        this.durability = durability2;
        this.schemaMediator = realmProxyMediator;
        this.rxObservableFactory = rxObservableFactory2;
        this.initialDataTransaction = transaction;
        this.readOnly = z2;
        this.compactOnLaunch = compactOnLaunchCallback;
        this.isRecoveryConfiguration = z3;
        this.maxNumberOfActiveVersions = j2;
    }

    public File getRealmDirectory() {
        return this.realmDirectory;
    }

    public String getRealmFileName() {
        return this.realmFileName;
    }

    public byte[] getEncryptionKey() {
        byte[] bArr = this.key;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public long getSchemaVersion() {
        return this.schemaVersion;
    }

    public RealmMigration getMigration() {
        return this.migration;
    }

    public boolean shouldDeleteRealmIfMigrationNeeded() {
        return this.deleteRealmIfMigrationNeeded;
    }

    public OsRealmConfig.Durability getDurability() {
        return this.durability;
    }

    /* access modifiers changed from: protected */
    public RealmProxyMediator getSchemaMediator() {
        return this.schemaMediator;
    }

    /* access modifiers changed from: package-private */
    public Realm.Transaction getInitialDataTransaction() {
        return this.initialDataTransaction;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAssetFile() {
        return !Util.isEmptyString(this.assetFilePath);
    }

    /* access modifiers changed from: package-private */
    public String getAssetFilePath() {
        return this.assetFilePath;
    }

    public CompactOnLaunchCallback getCompactOnLaunchCallback() {
        return this.compactOnLaunch;
    }

    public Set<Class<? extends RealmModel>> getRealmObjectClasses() {
        return this.schemaMediator.getModelClasses();
    }

    public String getPath() {
        return this.canonicalPath;
    }

    /* access modifiers changed from: package-private */
    public boolean realmExists() {
        return new File(this.canonicalPath).exists();
    }

    public RxObservableFactory getRxFactory() {
        RxObservableFactory rxObservableFactory2 = this.rxObservableFactory;
        if (rxObservableFactory2 != null) {
            return rxObservableFactory2;
        }
        throw new UnsupportedOperationException("RxJava seems to be missing from the classpath. Remember to add it as a compile dependency. See https://realm.io/docs/java/latest/#rxjava for more details.");
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public boolean isRecoveryConfiguration() {
        return this.isRecoveryConfiguration;
    }

    public long getMaxNumberOfActiveVersions() {
        return this.maxNumberOfActiveVersions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RealmConfiguration realmConfiguration = (RealmConfiguration) obj;
        if (this.schemaVersion != realmConfiguration.schemaVersion || this.deleteRealmIfMigrationNeeded != realmConfiguration.deleteRealmIfMigrationNeeded || this.readOnly != realmConfiguration.readOnly || this.isRecoveryConfiguration != realmConfiguration.isRecoveryConfiguration) {
            return false;
        }
        File file = this.realmDirectory;
        if (file == null ? realmConfiguration.realmDirectory != null : !file.equals(realmConfiguration.realmDirectory)) {
            return false;
        }
        String str = this.realmFileName;
        if (str == null ? realmConfiguration.realmFileName != null : !str.equals(realmConfiguration.realmFileName)) {
            return false;
        }
        if (!this.canonicalPath.equals(realmConfiguration.canonicalPath)) {
            return false;
        }
        String str2 = this.assetFilePath;
        if (str2 == null ? realmConfiguration.assetFilePath != null : !str2.equals(realmConfiguration.assetFilePath)) {
            return false;
        }
        if (!Arrays.equals(this.key, realmConfiguration.key)) {
            return false;
        }
        RealmMigration realmMigration = this.migration;
        if (realmMigration == null ? realmConfiguration.migration != null : !realmMigration.equals(realmConfiguration.migration)) {
            return false;
        }
        if (this.durability != realmConfiguration.durability || !this.schemaMediator.equals(realmConfiguration.schemaMediator)) {
            return false;
        }
        RxObservableFactory rxObservableFactory2 = this.rxObservableFactory;
        if (rxObservableFactory2 == null ? realmConfiguration.rxObservableFactory != null : !rxObservableFactory2.equals(realmConfiguration.rxObservableFactory)) {
            return false;
        }
        Realm.Transaction transaction = this.initialDataTransaction;
        if (transaction == null ? realmConfiguration.initialDataTransaction != null : !transaction.equals(realmConfiguration.initialDataTransaction)) {
            return false;
        }
        CompactOnLaunchCallback compactOnLaunchCallback = this.compactOnLaunch;
        if (compactOnLaunchCallback == null ? realmConfiguration.compactOnLaunch != null : !compactOnLaunchCallback.equals(realmConfiguration.compactOnLaunch)) {
            return false;
        }
        if (this.maxNumberOfActiveVersions == realmConfiguration.maxNumberOfActiveVersions) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        File file = this.realmDirectory;
        int i = 0;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        String str = this.realmFileName;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.canonicalPath.hashCode()) * 31;
        String str2 = this.assetFilePath;
        int hashCode3 = str2 != null ? str2.hashCode() : 0;
        long j = this.schemaVersion;
        int hashCode4 = (((((hashCode2 + hashCode3) * 31) + Arrays.hashCode(this.key)) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        RealmMigration realmMigration = this.migration;
        int hashCode5 = (((((((hashCode4 + (realmMigration != null ? realmMigration.hashCode() : 0)) * 31) + (this.deleteRealmIfMigrationNeeded ? 1 : 0)) * 31) + this.durability.hashCode()) * 31) + this.schemaMediator.hashCode()) * 31;
        RxObservableFactory rxObservableFactory2 = this.rxObservableFactory;
        int hashCode6 = (hashCode5 + (rxObservableFactory2 != null ? rxObservableFactory2.hashCode() : 0)) * 31;
        Realm.Transaction transaction = this.initialDataTransaction;
        int hashCode7 = (((hashCode6 + (transaction != null ? transaction.hashCode() : 0)) * 31) + (this.readOnly ? 1 : 0)) * 31;
        CompactOnLaunchCallback compactOnLaunchCallback = this.compactOnLaunch;
        if (compactOnLaunchCallback != null) {
            i = compactOnLaunchCallback.hashCode();
        }
        long j2 = this.maxNumberOfActiveVersions;
        return ((((hashCode7 + i) * 31) + (this.isRecoveryConfiguration ? 1 : 0)) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    protected static RealmProxyMediator createSchemaMediator(Set<Object> set, Set<Class<? extends RealmModel>> set2) {
        if (set2.size() > 0) {
            return new FilterableMediator(DEFAULT_MODULE_MEDIATOR, set2);
        }
        if (set.size() == 1) {
            return getModuleMediator(set.iterator().next().getClass().getCanonicalName());
        }
        RealmProxyMediator[] realmProxyMediatorArr = new RealmProxyMediator[set.size()];
        int i = 0;
        for (Object obj : set) {
            realmProxyMediatorArr[i] = getModuleMediator(obj.getClass().getCanonicalName());
            i++;
        }
        return new CompositeMediator(realmProxyMediatorArr);
    }

    private static RealmProxyMediator getModuleMediator(String str) {
        String[] split = str.split("\\.");
        String str2 = split[split.length - 1];
        String format = String.format(Locale.US, "io.realm.%s%s", new Object[]{str2, "Mediator"});
        try {
            Constructor constructor = Class.forName(format).getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return (RealmProxyMediator) constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            throw new RealmException("Could not find " + format, e);
        } catch (InvocationTargetException e2) {
            throw new RealmException("Could not create an instance of " + format, e2);
        } catch (InstantiationException e3) {
            throw new RealmException("Could not create an instance of " + format, e3);
        } catch (IllegalAccessException e4) {
            throw new RealmException("Could not create an instance of " + format, e4);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("realmDirectory: ");
        File file = this.realmDirectory;
        sb.append(file != null ? file.toString() : "");
        sb.append(StringUtils.LF);
        sb.append("realmFileName : ");
        sb.append(this.realmFileName);
        sb.append(StringUtils.LF);
        sb.append("canonicalPath: ");
        sb.append(this.canonicalPath);
        sb.append(StringUtils.LF);
        sb.append("key: ");
        sb.append("[length: ");
        sb.append(this.key == null ? 0 : 64);
        sb.append("]");
        sb.append(StringUtils.LF);
        sb.append("schemaVersion: ");
        sb.append(Long.toString(this.schemaVersion));
        sb.append(StringUtils.LF);
        sb.append("migration: ");
        sb.append(this.migration);
        sb.append(StringUtils.LF);
        sb.append("deleteRealmIfMigrationNeeded: ");
        sb.append(this.deleteRealmIfMigrationNeeded);
        sb.append(StringUtils.LF);
        sb.append("durability: ");
        sb.append(this.durability);
        sb.append(StringUtils.LF);
        sb.append("schemaMediator: ");
        sb.append(this.schemaMediator);
        sb.append(StringUtils.LF);
        sb.append("readOnly: ");
        sb.append(this.readOnly);
        sb.append(StringUtils.LF);
        sb.append("compactOnLaunch: ");
        sb.append(this.compactOnLaunch);
        sb.append(StringUtils.LF);
        sb.append("maxNumberOfActiveVersions: ");
        sb.append(this.maxNumberOfActiveVersions);
        return sb.toString();
    }

    static synchronized boolean isRxJavaAvailable() {
        boolean booleanValue;
        synchronized (RealmConfiguration.class) {
            if (rxJavaAvailable == null) {
                try {
                    Class.forName("io.reactivex.Flowable");
                    rxJavaAvailable = true;
                } catch (ClassNotFoundException unused) {
                    rxJavaAvailable = false;
                }
            }
            booleanValue = rxJavaAvailable.booleanValue();
        }
        return booleanValue;
    }

    protected static String getCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            RealmFileException.Kind kind = RealmFileException.Kind.ACCESS_ERROR;
            throw new RealmFileException(kind, "Could not resolve the canonical path to the Realm file: " + file.getAbsolutePath(), e);
        }
    }

    public static class Builder {
        private String assetFilePath;
        private CompactOnLaunchCallback compactOnLaunch;
        private HashSet<Class<? extends RealmModel>> debugSchema;
        private boolean deleteRealmIfMigrationNeeded;
        private File directory;
        private OsRealmConfig.Durability durability;
        private String fileName;
        private Realm.Transaction initialDataTransaction;
        private byte[] key;
        private long maxNumberOfActiveVersions;
        private RealmMigration migration;
        private HashSet<Object> modules;
        private boolean readOnly;
        private RxObservableFactory rxFactory;
        private long schemaVersion;

        public Builder() {
            this(BaseRealm.applicationContext);
        }

        Builder(Context context) {
            this.modules = new HashSet<>();
            this.debugSchema = new HashSet<>();
            this.maxNumberOfActiveVersions = LongCompanionObject.MAX_VALUE;
            if (context != null) {
                RealmCore.loadLibrary(context);
                initializeBuilder(context);
                return;
            }
            throw new IllegalStateException("Call `Realm.init(Context)` before creating a RealmConfiguration");
        }

        private void initializeBuilder(Context context) {
            this.directory = context.getFilesDir();
            this.fileName = "default.realm";
            this.key = null;
            this.schemaVersion = 0;
            this.migration = null;
            this.deleteRealmIfMigrationNeeded = false;
            this.durability = OsRealmConfig.Durability.FULL;
            this.readOnly = false;
            this.compactOnLaunch = null;
            if (RealmConfiguration.DEFAULT_MODULE != null) {
                this.modules.add(RealmConfiguration.DEFAULT_MODULE);
            }
        }

        public Builder name(String str) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("A non-empty filename must be provided");
            }
            this.fileName = str;
            return this;
        }

        public Builder directory(File file) {
            if (file == null) {
                throw new IllegalArgumentException("Non-null 'dir' required.");
            } else if (file.isFile()) {
                throw new IllegalArgumentException("'dir' is a file, not a directory: " + file.getAbsolutePath() + Consts.DOT);
            } else if (!file.exists() && !file.mkdirs()) {
                throw new IllegalArgumentException("Could not create the specified directory: " + file.getAbsolutePath() + Consts.DOT);
            } else if (file.canWrite()) {
                this.directory = file;
                return this;
            } else {
                throw new IllegalArgumentException("Realm directory is not writable: " + file.getAbsolutePath() + Consts.DOT);
            }
        }

        public Builder encryptionKey(byte[] bArr) {
            if (bArr == null) {
                throw new IllegalArgumentException("A non-null key must be provided");
            } else if (bArr.length == 64) {
                this.key = Arrays.copyOf(bArr, bArr.length);
                return this;
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "The provided key must be %s bytes. Yours was: %s", new Object[]{64, Integer.valueOf(bArr.length)}));
            }
        }

        public Builder schemaVersion(long j) {
            if (j >= 0) {
                this.schemaVersion = j;
                return this;
            }
            throw new IllegalArgumentException("Realm schema version numbers must be 0 (zero) or higher. Yours was: " + j);
        }

        public Builder migration(RealmMigration realmMigration) {
            if (realmMigration != null) {
                this.migration = realmMigration;
                return this;
            }
            throw new IllegalArgumentException("A non-null migration must be provided");
        }

        public Builder deleteRealmIfMigrationNeeded() {
            String str = this.assetFilePath;
            if (str == null || str.length() == 0) {
                this.deleteRealmIfMigrationNeeded = true;
                return this;
            }
            throw new IllegalStateException("Realm cannot clear its schema when previously configured to use an asset file by calling assetFile().");
        }

        public Builder inMemory() {
            if (Util.isEmptyString(this.assetFilePath)) {
                this.durability = OsRealmConfig.Durability.MEM_ONLY;
                return this;
            }
            throw new RealmException("Realm can not use in-memory configuration if asset file is present.");
        }

        public Builder modules(Object obj, Object... objArr) {
            this.modules.clear();
            addModule(obj);
            if (objArr != null) {
                for (Object addModule : objArr) {
                    addModule(addModule);
                }
            }
            return this;
        }

        public final Builder addModule(Object obj) {
            if (obj != null) {
                checkModule(obj);
                this.modules.add(obj);
            }
            return this;
        }

        public Builder rxFactory(RxObservableFactory rxObservableFactory) {
            this.rxFactory = rxObservableFactory;
            return this;
        }

        public Builder initialData(Realm.Transaction transaction) {
            this.initialDataTransaction = transaction;
            return this;
        }

        public Builder assetFile(String str) {
            if (Util.isEmptyString(str)) {
                throw new IllegalArgumentException("A non-empty asset file path must be provided");
            } else if (this.durability == OsRealmConfig.Durability.MEM_ONLY) {
                throw new RealmException("Realm can not use in-memory configuration if asset file is present.");
            } else if (!this.deleteRealmIfMigrationNeeded) {
                this.assetFilePath = str;
                return this;
            } else {
                throw new IllegalStateException("Realm cannot use an asset file when previously configured to clear its schema in migration by calling deleteRealmIfMigrationNeeded().");
            }
        }

        public Builder readOnly() {
            this.readOnly = true;
            return this;
        }

        public Builder compactOnLaunch() {
            return compactOnLaunch(new DefaultCompactOnLaunchCallback());
        }

        public Builder compactOnLaunch(CompactOnLaunchCallback compactOnLaunchCallback) {
            if (compactOnLaunchCallback != null) {
                this.compactOnLaunch = compactOnLaunchCallback;
                return this;
            }
            throw new IllegalArgumentException("A non-null compactOnLaunch must be provided");
        }

        public Builder maxNumberOfActiveVersions(long j) {
            if (j >= 1) {
                this.maxNumberOfActiveVersions = j;
                return this;
            }
            throw new IllegalArgumentException("Only positive numbers above 0 are allowed. Yours was: " + j);
        }

        /* access modifiers changed from: package-private */
        public final Builder schema(Class<? extends RealmModel> cls, Class<? extends RealmModel>... clsArr) {
            if (cls != null) {
                this.modules.clear();
                this.modules.add(RealmConfiguration.DEFAULT_MODULE_MEDIATOR);
                this.debugSchema.add(cls);
                if (clsArr != null) {
                    Collections.addAll(this.debugSchema, clsArr);
                }
                return this;
            }
            throw new IllegalArgumentException("A non-null class must be provided");
        }

        public RealmConfiguration build() {
            if (this.readOnly) {
                if (this.initialDataTransaction != null) {
                    throw new IllegalStateException("This Realm is marked as read-only. Read-only Realms cannot use initialData(Realm.Transaction).");
                } else if (this.assetFilePath == null) {
                    throw new IllegalStateException("Only Realms provided using 'assetFile(path)' can be marked read-only. No such Realm was provided.");
                } else if (this.deleteRealmIfMigrationNeeded) {
                    throw new IllegalStateException("'deleteRealmIfMigrationNeeded()' and read-only Realms cannot be combined");
                } else if (this.compactOnLaunch != null) {
                    throw new IllegalStateException("'compactOnLaunch()' and read-only Realms cannot be combined");
                }
            }
            if (this.rxFactory == null && RealmConfiguration.isRxJavaAvailable()) {
                this.rxFactory = new RealmObservableFactory(true);
            }
            RealmConfiguration realmConfiguration = r3;
            RealmConfiguration realmConfiguration2 = new RealmConfiguration(this.directory, this.fileName, RealmConfiguration.getCanonicalPath(new File(this.directory, this.fileName)), this.assetFilePath, this.key, this.schemaVersion, this.migration, this.deleteRealmIfMigrationNeeded, this.durability, RealmConfiguration.createSchemaMediator(this.modules, this.debugSchema), this.rxFactory, this.initialDataTransaction, this.readOnly, this.compactOnLaunch, false, this.maxNumberOfActiveVersions);
            return realmConfiguration;
        }

        private void checkModule(Object obj) {
            if (!obj.getClass().isAnnotationPresent(RealmModule.class)) {
                throw new IllegalArgumentException(obj.getClass().getCanonicalName() + " is not a RealmModule. Add @RealmModule to the class definition.");
            }
        }
    }
}
