package io.realm;

import io.realm.RealmObjectSchema;
import io.realm.internal.CheckedRow;
import io.realm.internal.OsObjectStore;
import io.realm.internal.OsResults;
import io.realm.internal.Table;
import io.realm.internal.core.DescriptorOrdering;
import io.realm.internal.fields.FieldDescriptor;
import java.util.Locale;

class MutableRealmObjectSchema extends RealmObjectSchema {
    MutableRealmObjectSchema(BaseRealm baseRealm, RealmSchema realmSchema, Table table) {
        super(baseRealm, realmSchema, table, new RealmObjectSchema.DynamicColumnIndices(table));
    }

    public RealmObjectSchema setClassName(String str) {
        this.realm.checkNotInSync();
        checkEmpty(str);
        String tableNameForClass = Table.getTableNameForClass(str);
        if (str.length() > Table.CLASS_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(Locale.US, "Class name is too long. Limit is %1$d characters: '%2$s' (%3$d)", new Object[]{Integer.valueOf(Table.CLASS_NAME_MAX_LENGTH), str, Integer.valueOf(str.length())}));
        } else if (!this.realm.sharedRealm.hasTable(tableNameForClass)) {
            String name = this.table.getName();
            String className = this.table.getClassName();
            String primaryKeyForObject = OsObjectStore.getPrimaryKeyForObject(this.realm.sharedRealm, className);
            if (primaryKeyForObject != null) {
                OsObjectStore.setPrimaryKeyForObject(this.realm.sharedRealm, className, (String) null);
            }
            this.realm.sharedRealm.renameTable(name, tableNameForClass);
            if (primaryKeyForObject != null) {
                try {
                    OsObjectStore.setPrimaryKeyForObject(this.realm.sharedRealm, str, primaryKeyForObject);
                } catch (Exception e) {
                    this.realm.sharedRealm.renameTable(this.table.getName(), name);
                    throw e;
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException("Class already exists: " + str);
        }
    }

    private void checkEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Null or empty class names are not allowed");
        }
    }

    public RealmObjectSchema addField(String str, Class<?> cls, FieldAttribute... fieldAttributeArr) {
        RealmObjectSchema.FieldMetaData fieldMetaData = (RealmObjectSchema.FieldMetaData) SUPPORTED_SIMPLE_FIELDS.get(cls);
        boolean z = false;
        if (fieldMetaData != null) {
            if (containsAttribute(fieldAttributeArr, FieldAttribute.PRIMARY_KEY)) {
                checkAddPrimaryKeyForSync();
            }
            checkNewFieldName(str);
            boolean z2 = fieldMetaData.defaultNullable;
            if (!containsAttribute(fieldAttributeArr, FieldAttribute.REQUIRED)) {
                z = z2;
            }
            long addColumn = this.table.addColumn(fieldMetaData.fieldType, str, z);
            try {
                addModifiers(str, fieldAttributeArr);
                return this;
            } catch (Exception e) {
                this.table.removeColumn(addColumn);
                throw e;
            }
        } else if (SUPPORTED_LINKED_FIELDS.containsKey(cls)) {
            throw new IllegalArgumentException("Use addRealmObjectField() instead to add fields that link to other RealmObjects: " + str);
        } else if (RealmModel.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Use 'addRealmObjectField()' instead to add fields that link to other RealmObjects: %s(%s)", new Object[]{str, cls}));
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Realm doesn't support this field type: %s(%s)", new Object[]{str, cls}));
        }
    }

    public RealmObjectSchema addRealmObjectField(String str, RealmObjectSchema realmObjectSchema) {
        checkLegalName(str);
        checkFieldNameIsAvailable(str);
        this.table.addColumnLink(RealmFieldType.OBJECT, str, this.realm.sharedRealm.getTable(Table.getTableNameForClass(realmObjectSchema.getClassName())));
        return this;
    }

    public RealmObjectSchema addRealmListField(String str, RealmObjectSchema realmObjectSchema) {
        checkLegalName(str);
        checkFieldNameIsAvailable(str);
        this.table.addColumnLink(RealmFieldType.LIST, str, this.realm.sharedRealm.getTable(Table.getTableNameForClass(realmObjectSchema.getClassName())));
        return this;
    }

    public RealmObjectSchema addRealmListField(String str, Class<?> cls) {
        checkLegalName(str);
        checkFieldNameIsAvailable(str);
        RealmObjectSchema.FieldMetaData fieldMetaData = (RealmObjectSchema.FieldMetaData) SUPPORTED_SIMPLE_FIELDS.get(cls);
        if (fieldMetaData != null) {
            this.table.addColumn(fieldMetaData.listType, str, fieldMetaData.defaultNullable);
            return this;
        } else if (cls.equals(RealmObjectSchema.class) || RealmModel.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Use 'addRealmListField(String name, RealmObjectSchema schema)' instead to add lists that link to other RealmObjects: " + str);
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "RealmList does not support lists with this type: %s(%s)", new Object[]{str, cls}));
        }
    }

    public RealmObjectSchema removeField(String str) {
        this.realm.checkNotInSync();
        checkLegalName(str);
        if (hasField(str)) {
            long columnKey = getColumnKey(str);
            String className = getClassName();
            if (str.equals(OsObjectStore.getPrimaryKeyForObject(this.realm.sharedRealm, className))) {
                OsObjectStore.setPrimaryKeyForObject(this.realm.sharedRealm, className, str);
            }
            this.table.removeColumn(columnKey);
            return this;
        }
        throw new IllegalStateException(str + " does not exist.");
    }

    public RealmObjectSchema renameField(String str, String str2) {
        this.realm.checkNotInSync();
        checkLegalName(str);
        checkFieldExists(str);
        checkLegalName(str2);
        checkFieldNameIsAvailable(str2);
        this.table.renameColumn(getColumnKey(str), str2);
        return this;
    }

    public RealmObjectSchema addIndex(String str) {
        checkLegalName(str);
        checkFieldExists(str);
        long columnKey = getColumnKey(str);
        if (!this.table.hasSearchIndex(columnKey)) {
            this.table.addSearchIndex(columnKey);
            return this;
        }
        throw new IllegalStateException(str + " already has an index.");
    }

    public RealmObjectSchema removeIndex(String str) {
        this.realm.checkNotInSync();
        checkLegalName(str);
        checkFieldExists(str);
        long columnKey = getColumnKey(str);
        if (this.table.hasSearchIndex(columnKey)) {
            this.table.removeSearchIndex(columnKey);
            return this;
        }
        throw new IllegalStateException("Field is not indexed: " + str);
    }

    public RealmObjectSchema addPrimaryKey(String str) {
        checkAddPrimaryKeyForSync();
        checkLegalName(str);
        checkFieldExists(str);
        String primaryKeyForObject = OsObjectStore.getPrimaryKeyForObject(this.realm.sharedRealm, getClassName());
        if (primaryKeyForObject == null) {
            long columnKey = getColumnKey(str);
            if (getFieldType(str) != RealmFieldType.STRING && !this.table.hasSearchIndex(columnKey)) {
                this.table.addSearchIndex(columnKey);
            }
            OsObjectStore.setPrimaryKeyForObject(this.realm.sharedRealm, getClassName(), str);
            return this;
        }
        throw new IllegalStateException(String.format(Locale.ENGLISH, "Field '%s' has been already defined as primary key.", new Object[]{primaryKeyForObject}));
    }

    public RealmObjectSchema removePrimaryKey() {
        this.realm.checkNotInSync();
        String primaryKeyForObject = OsObjectStore.getPrimaryKeyForObject(this.realm.sharedRealm, getClassName());
        if (primaryKeyForObject != null) {
            long columnKey = this.table.getColumnKey(primaryKeyForObject);
            if (this.table.hasSearchIndex(columnKey)) {
                this.table.removeSearchIndex(columnKey);
            }
            OsObjectStore.setPrimaryKeyForObject(this.realm.sharedRealm, getClassName(), (String) null);
            return this;
        }
        throw new IllegalStateException(getClassName() + " doesn't have a primary key.");
    }

    public RealmObjectSchema setRequired(String str, boolean z) {
        long columnKey = this.table.getColumnKey(str);
        boolean isRequired = isRequired(str);
        RealmFieldType columnType = this.table.getColumnType(columnKey);
        if (columnType == RealmFieldType.OBJECT) {
            throw new IllegalArgumentException("Cannot modify the required state for RealmObject references: " + str);
        } else if (columnType == RealmFieldType.LIST) {
            throw new IllegalArgumentException("Cannot modify the required state for RealmList references: " + str);
        } else if (z && isRequired) {
            throw new IllegalStateException("Field is already required: " + str);
        } else if (z || isRequired) {
            if (z) {
                try {
                    this.table.convertColumnToNotNullable(columnKey);
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().contains("Attempted to insert null into non-nullable column")) {
                        throw new IllegalStateException(String.format("The primary key field '%s' has 'null' values stored.", new Object[]{str}));
                    }
                    throw e;
                }
            } else {
                this.table.convertColumnToNullable(columnKey);
            }
            return this;
        } else {
            throw new IllegalStateException("Field is already nullable: " + str);
        }
    }

    public RealmObjectSchema setNullable(String str, boolean z) {
        setRequired(str, !z);
        return this;
    }

    public RealmObjectSchema transform(RealmObjectSchema.Function function) {
        if (function != null) {
            OsResults createSnapshot = OsResults.createFromQuery(this.realm.sharedRealm, this.table.where(), new DescriptorOrdering()).createSnapshot();
            long size = createSnapshot.size();
            if (size <= 2147483647L) {
                int size2 = (int) createSnapshot.size();
                for (int i = 0; i < size2; i++) {
                    DynamicRealmObject dynamicRealmObject = new DynamicRealmObject(this.realm, new CheckedRow(createSnapshot.getUncheckedRow(i)));
                    if (dynamicRealmObject.isValid()) {
                        function.apply(dynamicRealmObject);
                    }
                }
            } else {
                throw new UnsupportedOperationException("Too many results to iterate: " + size);
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public FieldDescriptor getFieldDescriptors(String str, RealmFieldType... realmFieldTypeArr) {
        return FieldDescriptor.createStandardFieldDescriptor(getSchemaConnector(), getTable(), str, realmFieldTypeArr);
    }

    private void addModifiers(String str, FieldAttribute[] fieldAttributeArr) {
        if (fieldAttributeArr != null) {
            try {
                if (fieldAttributeArr.length > 0) {
                    if (containsAttribute(fieldAttributeArr, FieldAttribute.INDEXED)) {
                        addIndex(str);
                    }
                    if (containsAttribute(fieldAttributeArr, FieldAttribute.PRIMARY_KEY)) {
                        addPrimaryKey(str);
                    }
                }
            } catch (Exception e) {
                long columnKey = getColumnKey(str);
                if (0 != 0) {
                    this.table.removeSearchIndex(columnKey);
                }
                throw ((RuntimeException) e);
            }
        }
    }

    static boolean containsAttribute(FieldAttribute[] fieldAttributeArr, FieldAttribute fieldAttribute) {
        if (!(fieldAttributeArr == null || fieldAttributeArr.length == 0)) {
            for (FieldAttribute fieldAttribute2 : fieldAttributeArr) {
                if (fieldAttribute2 == fieldAttribute) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkNewFieldName(String str) {
        checkLegalName(str);
        checkFieldNameIsAvailable(str);
    }

    private void checkFieldNameIsAvailable(String str) {
        if (this.table.getColumnKey(str) != -1) {
            throw new IllegalArgumentException("Field already exists in '" + getClassName() + "': " + str);
        }
    }

    private void checkAddPrimaryKeyForSync() {
        if (this.realm.configuration.isSyncConfiguration()) {
            throw new UnsupportedOperationException("'addPrimaryKey' is not supported by synced Realms.");
        }
    }
}
