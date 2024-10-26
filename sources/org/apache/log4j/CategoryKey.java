package org.apache.log4j;

class CategoryKey {
    static /* synthetic */ Class class$org$apache$log4j$CategoryKey;
    int hashCache;
    String name;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    CategoryKey(String str) {
        this.name = str;
        this.hashCache = str.hashCode();
    }

    public final int hashCode() {
        return this.hashCache;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Class<?> cls = class$org$apache$log4j$CategoryKey;
        if (cls == null) {
            cls = class$("org.apache.log4j.CategoryKey");
            class$org$apache$log4j$CategoryKey = cls;
        }
        if (cls == obj.getClass()) {
            return this.name.equals(((CategoryKey) obj).name);
        }
        return false;
    }
}
