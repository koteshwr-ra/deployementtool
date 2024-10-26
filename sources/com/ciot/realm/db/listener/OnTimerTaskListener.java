package com.ciot.realm.db.listener;

import com.ciot.realm.db.Task;

public interface OnTimerTaskListener {
    void onTimerTask(Task task);
}
