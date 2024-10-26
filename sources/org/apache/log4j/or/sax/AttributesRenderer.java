package org.apache.log4j.or.sax;

import org.apache.log4j.or.ObjectRenderer;
import org.xml.sax.Attributes;

public class AttributesRenderer implements ObjectRenderer {
    public String doRender(Object obj) {
        if (!(obj instanceof Attributes)) {
            return obj.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        Attributes attributes = (Attributes) obj;
        int length = attributes.getLength();
        boolean z = true;
        for (int i = 0; i < length; i++) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(", ");
            }
            stringBuffer.append(attributes.getQName(i));
            stringBuffer.append('=');
            stringBuffer.append(attributes.getValue(i));
        }
        return stringBuffer.toString();
    }
}
