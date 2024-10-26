package org.apache.log4j.or;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.RendererSupport;

public class RendererMap {
    static /* synthetic */ Class class$org$apache$log4j$or$ObjectRenderer;
    static ObjectRenderer defaultRenderer = new DefaultRenderer();
    Hashtable map = new Hashtable();

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void addRenderer(RendererSupport rendererSupport, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Rendering class: [");
        stringBuffer.append(str2);
        stringBuffer.append("], Rendered class: [");
        stringBuffer.append(str);
        stringBuffer.append("].");
        LogLog.debug(stringBuffer.toString());
        Class cls = class$org$apache$log4j$or$ObjectRenderer;
        if (cls == null) {
            cls = class$("org.apache.log4j.or.ObjectRenderer");
            class$org$apache$log4j$or$ObjectRenderer = cls;
        }
        ObjectRenderer objectRenderer = (ObjectRenderer) OptionConverter.instantiateByClassName(str2, cls, (Object) null);
        if (objectRenderer == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not instantiate renderer [");
            stringBuffer2.append(str2);
            stringBuffer2.append("].");
            LogLog.error(stringBuffer2.toString());
            return;
        }
        try {
            rendererSupport.setRenderer(Loader.loadClass(str), objectRenderer);
        } catch (ClassNotFoundException e) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Could not find class [");
            stringBuffer3.append(str);
            stringBuffer3.append("].");
            LogLog.error(stringBuffer3.toString(), e);
        }
    }

    public String findAndRender(Object obj) {
        if (obj == null) {
            return null;
        }
        return get((Class) obj.getClass()).doRender(obj);
    }

    public ObjectRenderer get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get((Class) obj.getClass());
    }

    public ObjectRenderer get(Class cls) {
        while (cls != null) {
            ObjectRenderer objectRenderer = (ObjectRenderer) this.map.get(cls);
            if (objectRenderer != null) {
                return objectRenderer;
            }
            ObjectRenderer searchInterfaces = searchInterfaces(cls);
            if (searchInterfaces != null) {
                return searchInterfaces;
            }
            cls = cls.getSuperclass();
        }
        return defaultRenderer;
    }

    /* access modifiers changed from: package-private */
    public ObjectRenderer searchInterfaces(Class cls) {
        ObjectRenderer objectRenderer = (ObjectRenderer) this.map.get(cls);
        if (objectRenderer != null) {
            return objectRenderer;
        }
        Class[] interfaces = cls.getInterfaces();
        for (Class searchInterfaces : interfaces) {
            ObjectRenderer searchInterfaces2 = searchInterfaces(searchInterfaces);
            if (searchInterfaces2 != null) {
                return searchInterfaces2;
            }
        }
        return null;
    }

    public ObjectRenderer getDefaultRenderer() {
        return defaultRenderer;
    }

    public void clear() {
        this.map.clear();
    }

    public void put(Class cls, ObjectRenderer objectRenderer) {
        this.map.put(cls, objectRenderer);
    }
}
