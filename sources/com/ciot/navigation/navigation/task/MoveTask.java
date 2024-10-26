package com.ciot.navigation.navigation.task;

import android.os.SystemClock;
import android.text.TextUtils;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.navigation.NavigationHelper;
import com.ciot.navigation.navigation.task.MoveTaskBean;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;

public class MoveTask implements Runnable {
    private static final int DOUBLE = 2;
    private static final int ONE_HUNDRED_MILLISECONDS = 100;
    private static final int ONE_SECOND = 1000;
    String TAG = "NAVIGATION_TAG";
    private volatile boolean mCancelMove;
    private final Object mLock = new Object();
    private volatile List<MoveTaskBean> mMoveTaskList;
    private volatile boolean mMoving;
    private volatile boolean mNeedAddOutTurnstileMarker;
    private OnPauseMoveListener mOnPauseMoveListener;
    private volatile boolean mOutTurnstileMarker = false;
    private int mTaskStatus = 2;

    private void waitToGetOutFromTheElevator() {
    }

    public synchronized void setMove(MoveTaskBean moveTaskBean) {
        if (moveTaskBean != null) {
            String str = this.TAG;
            MyLogUtils.Logd(str, "MoveTask setMove MoveTaskBean:" + moveTaskBean.toString());
        }
        if (this.mMoveTaskList == null) {
            this.mMoveTaskList = new ArrayList();
        }
        setTaskStatus(1);
        this.mMoveTaskList.add(moveTaskBean);
        this.mCancelMove = false;
    }

    public synchronized void cancelMove() {
        MyLogUtils.Logd(this.TAG, "cancelMove");
        this.mCancelMove = true;
        NavigationHelper.Companion.getInstance().cancelNavigation();
        cancelPauseMove();
    }

    private synchronized int getTaskStatus() {
        return this.mTaskStatus;
    }

    private synchronized void setTaskStatus(int i) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "MoveTask setTaskStatus:" + i);
        this.mTaskStatus = i;
    }

    public boolean isComplete() {
        return getTaskStatus() >= 2;
    }

    public boolean isRunning() {
        return getTaskStatus() < 2;
    }

    private synchronized List<MoveTaskBean> getMoveTaskList() {
        return this.mMoveTaskList;
    }

    private synchronized void removeFirstMoveTask() {
        MyLogUtils.Logd(this.TAG, "removeFirstMoveTask");
        if (this.mMoveTaskList.size() > 0) {
            this.mMoveTaskList.remove(0);
        }
    }

    private synchronized void addMoveTaskFirst(MoveTaskBean moveTaskBean) {
        if (this.mMoveTaskList != null) {
            this.mMoveTaskList.add(0, moveTaskBean);
        }
    }

    public void run() {
        setTaskStatus(0);
        while (getMoveTaskList().size() > 0) {
            MoveTaskBean moveTaskBean = null;
            try {
                moveTaskBean = getMoveTaskList().get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (moveTaskBean != null) {
                setOutTurnstileMarker(moveTaskBean.isOutTurnstile());
                startMove(moveTaskBean);
                if (this.mCancelMove) {
                    break;
                }
            }
        }
        TurnstileUtil.getInstance().cancelOpenTurnstile();
        if (this.mCancelMove) {
            setTaskStatus(3);
        } else {
            setTaskStatus(2);
        }
    }

    private void startMove(MoveTaskBean moveTaskBean) {
        if (moveTaskBean == null) {
            MyLogUtils.Logw(this.TAG, "MoveTask startMove cancled:task is nulll");
            return;
        }
        String str = this.TAG;
        MyLogUtils.Logd(str, "MoveTask startMove:" + moveTaskBean.toString());
        String markerName = moveTaskBean.getMarkerName();
        if (!TextUtils.isEmpty(markerName)) {
            moveByName(markerName);
        } else if (moveTaskBean.getPointType() == 11) {
            moveByName("充电桩");
        } else if (SelfChassisState.getInstance().getCurFloor() == moveTaskBean.getFloor()) {
            currentFloorMoveByLocation(moveTaskBean.getX(), moveTaskBean.getY(), moveTaskBean.getAngle());
        } else {
            addMoveTaskFirst(new MoveTaskBean.Builder().markerName(getCrossFloorMarkerName(moveTaskBean.getFloor())).build());
        }
    }

    private void moveByName(String str) {
        if (!this.mCancelMove) {
            NavigationHelper.Companion.getInstance().setTargetPosition(str, true);
            waitForMoveEnd();
        }
    }

    private void currentFloorMoveByLocation(float f, float f2, float f3) {
        if (!this.mCancelMove) {
            moveByLocation(f, f2, f3);
            waitForMoveEnd();
        }
    }

    private String getCrossFloorMarkerName(int i) {
        return String.format("get_in_" + i, new Object[0]);
    }

    private void waitForMoveEnd() {
        SystemClock.sleep(1000);
        for (int i = 0; i < 20; i++) {
            SystemClock.sleep(100);
            if (this.mMoving) {
                break;
            }
        }
        String str = this.TAG;
        MyLogUtils.Logd(str, "MoveTask waitForMoveEnd start,mMoving=:" + this.mMoving);
        while (this.mMoving) {
            SystemClock.sleep(1000);
            if (this.mCancelMove) {
                waitToGetOutFromTheElevator();
            }
        }
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "MoveTask waitForMoveEnd complete,mMoving=:" + this.mMoving);
        removeFirstMoveTask();
        pauseMoveAfterArriveOutTurnstileMarker();
        if (isOutTurnstileMarker()) {
            TurnstileUtil.getInstance().cancelOpenTurnstile();
        }
    }

    private boolean cancelMoveAndOpenTurnstileInArea() {
        TurnstileUtil instance = TurnstileUtil.getInstance();
        boolean z = true;
        if (instance.isTurnstileOff()) {
            z = cancelMoveAndOpenTurnstile(true, instance);
        }
        return (!isNeedAddOutTurnstileMarker() || isOutTurnstileMarker()) ? z : cancelMoveAndOpenTurnstile(z, instance);
    }

    private boolean cancelMoveAndOpenTurnstile(boolean z, TurnstileUtil turnstileUtil) {
        if (this.mMoving) {
            NavigationHelper.Companion.getInstance().cancelNavigation();
            z = false;
        }
        if (turnstileUtil.isTurnstileOff()) {
            turnstileUtil.openTurnstileWithTime(5000);
        }
        while (true) {
            if ((this.mCancelMove || !turnstileUtil.isTurnstileOff()) && !this.mMoving) {
                break;
            }
            SystemClock.sleep(100);
        }
        if (!this.mCancelMove) {
            ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).startSpeak("我要过闸机,请让一让");
        }
        return z;
    }

    private void pauseMoveAfterArriveOutTurnstileMarker() {
        if (isOutTurnstileMarker()) {
            OnPauseMoveListener onPauseMoveListener = this.mOnPauseMoveListener;
            if (onPauseMoveListener != null) {
                onPauseMoveListener.onPauseMove();
            }
            synchronized (this.mLock) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addOutTurnstileMarker() {
        if (isNeedAddOutTurnstileMarker() && !isOutTurnstileMarker()) {
            addMoveTaskFirst(TurnstileUtil.getInstance().getOutTurnstileMarker());
        }
    }

    private void moveByLocation(float f, float f2, float f3) {
        NavigationHelper.Companion.getInstance().setTargetPosition(f, f2, f3, true);
    }

    public synchronized void setMoving(boolean z) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "MoveTask setMoving:" + z);
        if (this.mMoving != z) {
            this.mMoving = z;
        }
    }

    private synchronized boolean isOutTurnstileMarker() {
        return this.mOutTurnstileMarker;
    }

    private synchronized void setOutTurnstileMarker(boolean z) {
        this.mOutTurnstileMarker = z;
    }

    public synchronized void setNeedOutNeedTurnstileMarker(boolean z) {
        this.mNeedAddOutTurnstileMarker = z;
    }

    private synchronized boolean isNeedAddOutTurnstileMarker() {
        return this.mNeedAddOutTurnstileMarker;
    }

    public void cancelPauseMove() {
        synchronized (this.mLock) {
            this.mLock.notify();
        }
    }

    public void setOnPauseMoveListener(OnPauseMoveListener onPauseMoveListener) {
        this.mOnPauseMoveListener = onPauseMoveListener;
    }

    public synchronized void removeAllTask() {
        if (this.mMoveTaskList != null) {
            String str = this.TAG;
            MyLogUtils.Logd(str, "MoveTask removeAllTask size=:" + this.mMoveTaskList.size());
            while (this.mMoveTaskList.size() > 0) {
                this.mMoveTaskList.remove(0);
            }
        }
    }
}
