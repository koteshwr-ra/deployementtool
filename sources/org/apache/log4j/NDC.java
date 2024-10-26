package org.apache.log4j;

import java.util.Hashtable;
import java.util.Stack;

public class NDC {
    static final int REAP_THRESHOLD = 5;
    static Hashtable ht = new Hashtable();
    static int pushCounter = 0;

    private NDC() {
    }

    private static Stack getCurrentStack() {
        Hashtable hashtable = ht;
        if (hashtable != null) {
            return (Stack) hashtable.get(Thread.currentThread());
        }
        return null;
    }

    public static void clear() {
        Stack currentStack = getCurrentStack();
        if (currentStack != null) {
            currentStack.setSize(0);
        }
    }

    public static Stack cloneStack() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            return null;
        }
        return (Stack) currentStack.clone();
    }

    public static void inherit(Stack stack) {
        if (stack != null) {
            ht.put(Thread.currentThread(), stack);
        }
    }

    public static String get() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null || currentStack.isEmpty()) {
            return null;
        }
        return ((DiagnosticContext) currentStack.peek()).fullMessage;
    }

    public static int getDepth() {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            return 0;
        }
        return currentStack.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        r3 = r2.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r1 < r3) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        r0 = (java.lang.Thread) r2.elementAt(r1);
        r4 = new java.lang.StringBuffer();
        r4.append("Lazy NDC removal for thread [");
        r4.append(r0.getName());
        r4.append("] (");
        r4.append(ht.size());
        r4.append(").");
        org.apache.log4j.helpers.LogLog.debug(r4.toString());
        ht.remove(r0);
        r1 = r1 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void lazyRemove() {
        /*
            java.util.Hashtable r0 = ht
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r0)
            int r1 = pushCounter     // Catch:{ all -> 0x007e }
            int r1 = r1 + 1
            pushCounter = r1     // Catch:{ all -> 0x007e }
            r2 = 5
            if (r1 > r2) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            return
        L_0x0011:
            r1 = 0
            pushCounter = r1     // Catch:{ all -> 0x007e }
            java.util.Vector r2 = new java.util.Vector     // Catch:{ all -> 0x007e }
            r2.<init>()     // Catch:{ all -> 0x007e }
            java.util.Hashtable r3 = ht     // Catch:{ all -> 0x007e }
            java.util.Enumeration r3 = r3.keys()     // Catch:{ all -> 0x007e }
        L_0x001f:
            r4 = 0
        L_0x0020:
            boolean r5 = r3.hasMoreElements()     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x003d
            r5 = 4
            if (r4 <= r5) goto L_0x002a
            goto L_0x003d
        L_0x002a:
            java.lang.Object r5 = r3.nextElement()     // Catch:{ all -> 0x007e }
            java.lang.Thread r5 = (java.lang.Thread) r5     // Catch:{ all -> 0x007e }
            boolean r6 = r5.isAlive()     // Catch:{ all -> 0x007e }
            if (r6 == 0) goto L_0x0039
            int r4 = r4 + 1
            goto L_0x0020
        L_0x0039:
            r2.addElement(r5)     // Catch:{ all -> 0x007e }
            goto L_0x001f
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            int r3 = r2.size()
        L_0x0042:
            if (r1 < r3) goto L_0x0045
            return
        L_0x0045:
            java.lang.Object r0 = r2.elementAt(r1)
            java.lang.Thread r0 = (java.lang.Thread) r0
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.String r5 = "Lazy NDC removal for thread ["
            r4.append(r5)
            java.lang.String r5 = r0.getName()
            r4.append(r5)
            java.lang.String r5 = "] ("
            r4.append(r5)
            java.util.Hashtable r5 = ht
            int r5 = r5.size()
            r4.append(r5)
            java.lang.String r5 = ")."
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            org.apache.log4j.helpers.LogLog.debug(r4)
            java.util.Hashtable r4 = ht
            r4.remove(r0)
            int r1 = r1 + 1
            goto L_0x0042
        L_0x007e:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.NDC.lazyRemove():void");
    }

    public static String pop() {
        Stack currentStack = getCurrentStack();
        return (currentStack == null || currentStack.isEmpty()) ? "" : ((DiagnosticContext) currentStack.pop()).message;
    }

    public static String peek() {
        Stack currentStack = getCurrentStack();
        return (currentStack == null || currentStack.isEmpty()) ? "" : ((DiagnosticContext) currentStack.peek()).message;
    }

    public static void push(String str) {
        Stack currentStack = getCurrentStack();
        if (currentStack == null) {
            DiagnosticContext diagnosticContext = new DiagnosticContext(str, (DiagnosticContext) null);
            Stack stack = new Stack();
            ht.put(Thread.currentThread(), stack);
            stack.push(diagnosticContext);
        } else if (currentStack.isEmpty()) {
            currentStack.push(new DiagnosticContext(str, (DiagnosticContext) null));
        } else {
            currentStack.push(new DiagnosticContext(str, (DiagnosticContext) currentStack.peek()));
        }
    }

    public static void remove() {
        ht.remove(Thread.currentThread());
        lazyRemove();
    }

    public static void setMaxDepth(int i) {
        Stack currentStack = getCurrentStack();
        if (currentStack != null && i < currentStack.size()) {
            currentStack.setSize(i);
        }
    }

    private static class DiagnosticContext {
        String fullMessage;
        String message;

        DiagnosticContext(String str, DiagnosticContext diagnosticContext) {
            this.message = str;
            if (diagnosticContext != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(diagnosticContext.fullMessage);
                stringBuffer.append(' ');
                stringBuffer.append(str);
                this.fullMessage = stringBuffer.toString();
                return;
            }
            this.fullMessage = str;
        }
    }
}
