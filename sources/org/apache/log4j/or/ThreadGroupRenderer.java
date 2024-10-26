package org.apache.log4j.or;

import org.apache.log4j.Layout;

public class ThreadGroupRenderer implements ObjectRenderer {
    public String doRender(Object obj) {
        if (!(obj instanceof ThreadGroup)) {
            return obj.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        ThreadGroup threadGroup = (ThreadGroup) obj;
        stringBuffer.append("java.lang.ThreadGroup[name=");
        stringBuffer.append(threadGroup.getName());
        stringBuffer.append(", maxpri=");
        stringBuffer.append(threadGroup.getMaxPriority());
        stringBuffer.append("]");
        int activeCount = threadGroup.activeCount();
        Thread[] threadArr = new Thread[activeCount];
        threadGroup.enumerate(threadArr);
        for (int i = 0; i < activeCount; i++) {
            stringBuffer.append(Layout.LINE_SEP);
            stringBuffer.append("   Thread=[");
            stringBuffer.append(threadArr[i].getName());
            stringBuffer.append(",");
            stringBuffer.append(threadArr[i].getPriority());
            stringBuffer.append(",");
            stringBuffer.append(threadArr[i].isDaemon());
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
}
