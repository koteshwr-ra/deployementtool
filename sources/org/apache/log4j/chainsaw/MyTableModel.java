package org.apache.log4j.chainsaw;

import com.alibaba.android.arouter.compiler.utils.Consts;
import io.realm.com_ciot_realm_db_patrol_MessageRealmProxy;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

class MyTableModel extends AbstractTableModel {
    private static final String[] COL_NAMES = {"Time", "Priority", "Trace", "Category", "NDC", com_ciot_realm_db_patrol_MessageRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME};
    private static final DateFormat DATE_FORMATTER = DateFormat.getDateTimeInstance(3, 2);
    private static final EventDetails[] EMPTY_LIST = new EventDetails[0];
    private static final Logger LOG;
    private static final Comparator MY_COMP = new Comparator() {
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            return (obj2 != null && ((EventDetails) obj).getTimeStamp() >= ((EventDetails) obj2).getTimeStamp()) ? -1 : 1;
        }
    };
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$MyTableModel;
    /* access modifiers changed from: private */
    public final SortedSet mAllEvents = new TreeSet(MY_COMP);
    private String mCategoryFilter = "";
    private EventDetails[] mFilteredEvents = EMPTY_LIST;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private String mMessageFilter = "";
    private String mNDCFilter = "";
    /* access modifiers changed from: private */
    public boolean mPaused = false;
    /* access modifiers changed from: private */
    public final List mPendingEvents = new ArrayList();
    private Priority mPriorityFilter = Priority.DEBUG;
    private String mThreadFilter = "";

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$MyTableModel;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.MyTableModel");
            class$org$apache$log4j$chainsaw$MyTableModel = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    private class Processor implements Runnable {
        private Processor() {
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException unused) {
                }
                synchronized (MyTableModel.this.mLock) {
                    if (!MyTableModel.this.mPaused) {
                        boolean z = false;
                        boolean z2 = true;
                        for (EventDetails eventDetails : MyTableModel.this.mPendingEvents) {
                            MyTableModel.this.mAllEvents.add(eventDetails);
                            z2 = z2 && eventDetails == MyTableModel.this.mAllEvents.first();
                            z = z || MyTableModel.this.matchFilter(eventDetails);
                        }
                        MyTableModel.this.mPendingEvents.clear();
                        if (z) {
                            MyTableModel.this.updateFilteredEvents(z2);
                        }
                    }
                }
            }
        }
    }

    MyTableModel() {
        Thread thread = new Thread(new Processor());
        thread.setDaemon(true);
        thread.start();
    }

    public int getRowCount() {
        int length;
        synchronized (this.mLock) {
            length = this.mFilteredEvents.length;
        }
        return length;
    }

    public int getColumnCount() {
        return COL_NAMES.length;
    }

    public String getColumnName(int i) {
        return COL_NAMES[i];
    }

    public Class getColumnClass(int i) {
        if (i == 2) {
            Class cls = class$java$lang$Boolean;
            if (cls != null) {
                return cls;
            }
            Class class$ = class$(Consts.BOOLEAN);
            class$java$lang$Boolean = class$;
            return class$;
        }
        Class cls2 = class$java$lang$Object;
        if (cls2 != null) {
            return cls2;
        }
        Class class$2 = class$("java.lang.Object");
        class$java$lang$Object = class$2;
        return class$2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getValueAt(int r5, int r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            org.apache.log4j.chainsaw.EventDetails[] r1 = r4.mFilteredEvents     // Catch:{ all -> 0x004b }
            r5 = r1[r5]     // Catch:{ all -> 0x004b }
            if (r6 != 0) goto L_0x001a
            java.text.DateFormat r6 = DATE_FORMATTER     // Catch:{ all -> 0x004b }
            java.util.Date r1 = new java.util.Date     // Catch:{ all -> 0x004b }
            long r2 = r5.getTimeStamp()     // Catch:{ all -> 0x004b }
            r1.<init>(r2)     // Catch:{ all -> 0x004b }
            java.lang.String r5 = r6.format(r1)     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x001a:
            r1 = 1
            if (r6 != r1) goto L_0x0023
            org.apache.log4j.Priority r5 = r5.getPriority()     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x0023:
            r1 = 2
            if (r6 != r1) goto L_0x0033
            java.lang.String[] r5 = r5.getThrowableStrRep()     // Catch:{ all -> 0x004b }
            if (r5 != 0) goto L_0x002f
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x004b }
            goto L_0x0031
        L_0x002f:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x004b }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x0033:
            r1 = 3
            if (r6 != r1) goto L_0x003c
            java.lang.String r5 = r5.getCategoryName()     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x003c:
            r1 = 4
            if (r6 != r1) goto L_0x0045
            java.lang.String r5 = r5.getNDC()     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x0045:
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return r5
        L_0x004b:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.chainsaw.MyTableModel.getValueAt(int, int):java.lang.Object");
    }

    public void setPriorityFilter(Priority priority) {
        synchronized (this.mLock) {
            this.mPriorityFilter = priority;
            updateFilteredEvents(false);
        }
    }

    public void setThreadFilter(String str) {
        synchronized (this.mLock) {
            this.mThreadFilter = str.trim();
            updateFilteredEvents(false);
        }
    }

    public void setMessageFilter(String str) {
        synchronized (this.mLock) {
            this.mMessageFilter = str.trim();
            updateFilteredEvents(false);
        }
    }

    public void setNDCFilter(String str) {
        synchronized (this.mLock) {
            this.mNDCFilter = str.trim();
            updateFilteredEvents(false);
        }
    }

    public void setCategoryFilter(String str) {
        synchronized (this.mLock) {
            this.mCategoryFilter = str.trim();
            updateFilteredEvents(false);
        }
    }

    public void addEvent(EventDetails eventDetails) {
        synchronized (this.mLock) {
            this.mPendingEvents.add(eventDetails);
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            this.mAllEvents.clear();
            this.mFilteredEvents = new EventDetails[0];
            this.mPendingEvents.clear();
            fireTableDataChanged();
        }
    }

    public void toggle() {
        synchronized (this.mLock) {
            this.mPaused = !this.mPaused;
        }
    }

    public boolean isPaused() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mPaused;
        }
        return z;
    }

    public EventDetails getEventDetails(int i) {
        EventDetails eventDetails;
        synchronized (this.mLock) {
            eventDetails = this.mFilteredEvents[i];
        }
        return eventDetails;
    }

    /* access modifiers changed from: private */
    public void updateFilteredEvents(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        int size = this.mAllEvents.size();
        for (EventDetails eventDetails : this.mAllEvents) {
            if (matchFilter(eventDetails)) {
                arrayList.add(eventDetails);
            }
        }
        EventDetails[] eventDetailsArr = this.mFilteredEvents;
        EventDetails eventDetails2 = eventDetailsArr.length == 0 ? null : eventDetailsArr[0];
        this.mFilteredEvents = (EventDetails[]) arrayList.toArray(EMPTY_LIST);
        if (!z || eventDetails2 == null) {
            fireTableDataChanged();
        } else {
            int indexOf = arrayList.indexOf(eventDetails2);
            if (indexOf < 1) {
                LOG.warn("In strange state");
                fireTableDataChanged();
            } else {
                fireTableRowsInserted(0, indexOf - 1);
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Logger logger = LOG;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Total time [ms]: ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" in update, size: ");
        stringBuffer.append(size);
        logger.debug(stringBuffer.toString());
    }

    /* access modifiers changed from: private */
    public boolean matchFilter(EventDetails eventDetails) {
        if (!eventDetails.getPriority().isGreaterOrEqual(this.mPriorityFilter) || eventDetails.getThreadName().indexOf(this.mThreadFilter) < 0 || eventDetails.getCategoryName().indexOf(this.mCategoryFilter) < 0) {
            return false;
        }
        if (this.mNDCFilter.length() != 0 && (eventDetails.getNDC() == null || eventDetails.getNDC().indexOf(this.mNDCFilter) < 0)) {
            return false;
        }
        String message = eventDetails.getMessage();
        if (message == null) {
            if (this.mMessageFilter.length() == 0) {
                return true;
            }
            return false;
        } else if (message.indexOf(this.mMessageFilter) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
