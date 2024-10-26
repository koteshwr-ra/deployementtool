package com.ciot.navigation.navigation;

import com.ciot.realm.db.patrol.Operation;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J*\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/ciot/navigation/navigation/OnTaskStateListener;", "", "setTastOperation", "", "operation", "Lcom/ciot/realm/db/patrol/Operation;", "setTastStatus", "taskId", "", "taskNodeId", "taskName", "taskStatus", "", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: OnTaskStateListener.kt */
public interface OnTaskStateListener {
    void setTastOperation(Operation operation);

    void setTastStatus(String str, String str2, String str3, int i);
}
