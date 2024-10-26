package com.ciot.navigation.navigation.task;

public abstract class BaseTask implements Runnable {
    public abstract void continueTask();

    public abstract void continueTts();

    public abstract int getTaskPriority();

    public abstract boolean isPause();

    public abstract boolean isRunning();

    public abstract void nextPoint();

    public abstract void pauseTask();

    public abstract void release();

    public abstract void setMoving(boolean z);

    public abstract void setTaskPriority(int i);

    public abstract void stopTask();

    public abstract String toString();
}
