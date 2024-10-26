package com.ciot.navigation.navigation.task;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.navigation.task.MoveTaskBean;
import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.Task;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.Point;
import com.ciot.realm.db.patrol.Process;
import com.ciot.waterchassis.bean.WaterNavigationStatusBean;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import io.realm.RealmList;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import org.greenrobot.eventbus.EventBus;

public class PatrolTask extends BaseTask implements Serializable {
    public static final int SLEEP_TIME = 100;
    private static final long serialVersionUID = -6291581233173166236L;
    String TAG = "NAVIGATION_TAG";
    private final BroadcastTask mBroadcastTask = new BroadcastTask();
    private volatile boolean mIsNext = false;
    private final MoveTask mMoveTask = new MoveTask();
    private OnTaskStatusListener mOnTaskStatusListener;
    private final Object mPauseLock = new Object();
    private volatile boolean mPauseTask = false;
    private volatile boolean mStopThread = false;
    private Task mTask;
    private volatile long mTaskBeginTime = -1;
    private volatile int mTaskPriority = 0;
    private volatile int mTaskStatus = 1;
    private ExecutorService mThreadCachedPool;
    private WaterNavigationStatusBean mWaterNavigationStatus;

    public interface OnTaskStatusListener {
        void onPointWaitTime(int i);

        void onTaskStatus(WaterNavigationStatusBean waterNavigationStatusBean);
    }

    public void setTaskData(Task task) {
        this.mTask = task;
        this.mThreadCachedPool = Executors.newCachedThreadPool();
    }

    public Task getTaskData() {
        return this.mTask;
    }

    public void stopTask() {
        MyLogUtils.Logd(this.TAG, "RobotTask stopTask");
        this.mStopThread = true;
        if (this.mPauseTask) {
            this.mPauseTask = false;
            synchronized (this.mPauseLock) {
                this.mPauseLock.notify();
                MyLogUtils.Logd(this.TAG, "RobotTask continueTask2");
            }
        }
        this.mMoveTask.cancelMove();
        this.mBroadcastTask.stopBroadcast();
    }

    public void pauseTask() {
        MyLogUtils.Logd(this.TAG, "RobotTask pauseTask");
        this.mPauseTask = true;
        this.mMoveTask.cancelMove();
        this.mBroadcastTask.pauseSpeak();
    }

    public void nextPoint() {
        MyLogUtils.Logd(this.TAG, "RobotTask nextPoint");
        this.mMoveTask.cancelMove();
        this.mIsNext = true;
        this.mBroadcastTask.stopBroadcast();
    }

    public void continueTask() {
        MyLogUtils.Logd(this.TAG, "RobotTask continueTask");
        this.mPauseTask = false;
        synchronized (this.mPauseLock) {
            this.mPauseLock.notify();
            MyLogUtils.Logd(this.TAG, "RobotTask continueTask2");
        }
    }

    public void continueTts() {
        BroadcastTask broadcastTask = this.mBroadcastTask;
        if (broadcastTask != null) {
            broadcastTask.setIsPlaying(false);
        }
    }

    public boolean isRunning() {
        return this.mTaskStatus < 2;
    }

    public boolean isPause() {
        return this.mPauseTask;
    }

    public void run() {
        if (this.mTaskBeginTime < 0) {
            this.mTaskBeginTime = System.currentTimeMillis();
        }
        MyLogUtils.Logw("NAVIGATION_TAG", "robotTask: " + this.mTask);
        int flag = this.mTask.getFlag();
        String taskid = this.mTask.getTaskid();
        setTaskStatus(taskid, "", 1);
        if (!TextUtils.isEmpty(this.mTask.getPrologue())) {
            broadcastWelcomeWord();
        }
        while (true) {
            if (flag != -1 && flag <= 0) {
                break;
            }
            RealmList<ChildTask> taskdata = this.mTask.getTaskdata();
            if (taskdata == null) {
                MyLogUtils.Logd("NAVIGATION_TAG", "robotTask 开始执行任务,tasks is null");
                break;
            }
            MyLogUtils.Logd("NAVIGATION_TAG", "robotTask 开始执行任务,tasks size=:" + taskdata.size());
            int i = 0;
            while (true) {
                if (i >= taskdata.size()) {
                    break;
                }
                MyLogUtils.Logd("NAVIGATION_TAG", "pauseIndex i=" + i);
                ChildTask executeChildLineTask = executeChildLineTask(taskid, taskdata, i);
                if (executeChildLineTask != null) {
                    if (this.mPauseTask && !this.mStopThread) {
                        pauseAndContinueExecuteLineTask(taskid, taskdata, i);
                    }
                    executeChildPointTask(executeChildLineTask, i == taskdata.size() - 1);
                    if (this.mPauseTask && !this.mStopThread) {
                        pauseAndContinueExecutePointTask(executeChildLineTask, i == taskdata.size() - 1);
                    }
                    if (this.mStopThread) {
                        setTaskStatus(taskid, executeChildLineTask.getTaskNodeID(), 3);
                        break;
                    }
                    this.mIsNext = false;
                }
                i++;
            }
            if (this.mStopThread) {
                break;
            } else if (flag > 0) {
                flag--;
            }
        }
        long currentTimeMillis = (System.currentTimeMillis() - this.mTaskBeginTime) / 1000;
        Log.d(this.TAG, "任务执行总时长: " + (currentTimeMillis / 60) + "分" + (currentTimeMillis % 60) + "秒");
        if (this.mStopThread) {
            broadcastSpecificVoice("好的，结束了");
        } else {
            broadcastSpecificVoice("当前巡更路线已经完成了");
        }
        while (!this.mBroadcastTask.isComplete()) {
            SystemClock.sleep(100);
        }
        if (!this.mStopThread) {
            setTaskStatus(taskid, "", 2);
            sendWaterNavigationTaskStatus(this.mTask.getTaskdata().size() - 1, true, true);
            MyLogUtils.Logw("NAVIGATION_TAG", "任务完成");
            return;
        }
        setTaskStatus(taskid, "", 3);
        MyLogUtils.Logw("NAVIGATION_TAG", "执行完毕");
        sendWaterNavigationTaskStatus(this.mTask.getTaskdata().size() - 1, false, true);
    }

    private void broadcastWelcomeWord() {
        broadcastSpecificVoice(this.mTask.getPrologue());
        while (!this.mBroadcastTask.isComplete()) {
            SystemClock.sleep(100);
            if (this.mPauseTask || this.mStopThread) {
                this.mBroadcastTask.stopBroadcast();
            }
            if (this.mPauseTask) {
                synchronized (this.mPauseLock) {
                    try {
                        MyLogUtils.Logd(this.TAG, "RobotTask pauseAndContinueExecuteTask: 暂停任务");
                        this.mPauseLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    broadcastWelcomeWord();
                }
            }
        }
    }

    private void broadcastSpecificVoice(String str) {
        Operation operation = new Operation();
        operation.setOperType(1);
        operation.setOperText(str);
        operation.setOperRepeat(1);
        this.mBroadcastTask.setBroadcast(operation);
        this.mThreadCachedPool.execute(this.mBroadcastTask);
    }

    private void executeChildPointTask(ChildTask childTask, boolean z) {
        Point point = childTask.getPoint();
        if (point != null) {
            RealmList<Operation> oper = point.getOper();
            int time = point.getTime();
            if (oper != null && !this.mStopThread) {
                executeOper(oper);
            }
            waitChildTaskEnd();
            if (!this.mStopThread) {
                pointDuration(time);
            }
        }
    }

    private void pauseAndContinueExecutePointTask(ChildTask childTask, boolean z) {
        synchronized (this.mPauseLock) {
            try {
                MyLogUtils.Logd(this.TAG, "RobotTask pauseAndContinueExecuteTask: 暂停任务");
                this.mPauseLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.mStopThread) {
                executeChildPointTask(childTask, z);
            }
            if (this.mPauseTask && !this.mStopThread) {
                pauseAndContinueExecutePointTask(childTask, z);
            }
        }
    }

    private ChildTask executeChildLineTask(String str, RealmList<ChildTask> realmList, int i) {
        int i2;
        String str2;
        sendWaterNavigationTaskStatus(i, false, false);
        ChildTask childTask = realmList.get(i);
        if (childTask == null) {
            return null;
        }
        String firstSentence = childTask.getFirstSentence();
        if (!TextUtils.isEmpty(firstSentence)) {
            ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).startSpeak(firstSentence);
        }
        setTaskStatus(str, childTask.getTaskNodeID(), 0);
        float x = childTask.getX();
        float y = childTask.getY();
        float angle = childTask.getAngle();
        int p_type = childTask.getP_type();
        try {
            i2 = childTask.getZ();
        } catch (NumberFormatException unused) {
            i2 = SelfChassisState.getInstance().getCurFloor();
        }
        executeLineTask(childTask.getProcess());
        if (!this.mStopThread) {
            if (this.mTask.getTaskType() == 34) {
                str2 = childTask.getPointName();
            } else {
                str2 = this.mTask.getTaskName();
            }
            MoveTaskBean build = new MoveTaskBean.Builder().pointType(p_type).angle(angle).floor(i2).x(x).y(y).markerName(str2).build();
            this.mMoveTask.setMove(build);
            this.mThreadCachedPool.execute(this.mMoveTask);
            MyLogUtils.Logi("NAVIGATION_TAG", "moveTask isStarted,wait maveTask complete,moveTaskBean=" + build);
            while (!this.mMoveTask.isComplete()) {
                MyLogUtils.Logv(this.TAG, "RobotTask pauseAndContinueExecuteTask: 等待移动任务完成");
                SystemClock.sleep(100);
            }
            MyLogUtils.Logi("NAVIGATION_TAG", "moveTask is complete,moveTaskBean=" + build);
        }
        if (!this.mBroadcastTask.isComplete()) {
            this.mBroadcastTask.stopBroadcast();
        }
        waitLineChildTaskEnd();
        if (!this.mPauseTask) {
            sendWaterNavigationTaskStatus(i, true, false);
        }
        return childTask;
    }

    private void pauseAndContinueExecuteLineTask(String str, RealmList<ChildTask> realmList, int i) {
        synchronized (this.mPauseLock) {
            try {
                MyLogUtils.Logd(this.TAG, "RobotTask pauseAndContinueExecuteTask: 暂停任务");
                this.mPauseLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyLogUtils.Logd(this.TAG, "RobotTask resumeSpeak");
            if (!this.mStopThread) {
                executeChildLineTask(str, realmList, i);
            }
            if (this.mPauseTask && !this.mStopThread) {
                pauseAndContinueExecuteLineTask(str, realmList, i);
            }
        }
    }

    private void executeLineTask(Process process) {
        RealmList<Operation> oper;
        if (process != null && (oper = process.getOper()) != null && !this.mStopThread) {
            executeOper(oper);
        }
    }

    private void waitChildTaskEnd() {
        while (!this.mBroadcastTask.isComplete()) {
            if (this.mPauseTask || this.mIsNext || this.mStopThread) {
                this.mBroadcastTask.stopBroadcast();
                return;
            }
            SystemClock.sleep(1000);
        }
    }

    private void waitLineChildTaskEnd() {
        while (true) {
            if (this.mBroadcastTask.isComplete()) {
                break;
            }
            MyLogUtils.Logd(this.TAG, "waitLineChildTaskEnd1");
            if (this.mPauseTask || this.mIsNext || this.mStopThread) {
                MyLogUtils.Logd(this.TAG, "waitLineChildTaskEnd2");
                this.mBroadcastTask.stopBroadcast();
            } else {
                SystemClock.sleep(1000);
            }
        }
        MyLogUtils.Logd(this.TAG, "waitLineChildTaskEnd2");
        this.mBroadcastTask.stopBroadcast();
        MyLogUtils.Logd(this.TAG, "waitLineChildTaskEnd3");
    }

    public void setTaskStatus(String str, String str2, int i) {
        String str3 = this.TAG;
        MyLogUtils.Logd(str3, "RobotTask setTaskStatus=" + i);
        if (this.mTaskStatus != i) {
            this.mTaskStatus = i;
        }
    }

    public int getTaskStatus() {
        return this.mTaskStatus;
    }

    private void pointDuration(int i) {
        OnTaskStatusListener onTaskStatusListener;
        int i2 = 0;
        int i3 = i < 1 ? 0 : i * 1000;
        int i4 = i3 / 100;
        SystemClock.sleep((long) (i3 % 100));
        while (i2 < i4) {
            if (i2 % 10 == 0 && (onTaskStatusListener = this.mOnTaskStatusListener) != null) {
                onTaskStatusListener.onPointWaitTime((i3 - (i2 * 100)) / 1000);
            }
            if (!this.mStopThread && !this.mIsNext) {
                pausePointWait();
                SystemClock.sleep(100);
                i2++;
            } else {
                return;
            }
        }
    }

    private void pausePointWait() {
        if (this.mPauseTask) {
            synchronized (this.mPauseLock) {
                try {
                    MyLogUtils.Logd(this.TAG, "RobotTask pauseAndContinueExecuteTask: 暂停任务");
                    this.mPauseLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void executeOper(RealmList<Operation> realmList) {
        for (int i = 0; i < realmList.size(); i++) {
            Operation operation = realmList.get(i);
            if (operation != null && operation.getOperType() == 1) {
                MyLogUtils.Logd(this.TAG, "RobotTask pauseAndContinueExecuteTask: 开启播报任务");
                this.mBroadcastTask.setBroadcast(operation);
                this.mThreadCachedPool.execute(this.mBroadcastTask);
            }
        }
    }

    public void setMoving(boolean z) {
        this.mMoveTask.setMoving(z);
    }

    private void sendWaterNavigationTaskStatus(int i, boolean z, boolean z2) {
        if (this.mWaterNavigationStatus == null) {
            this.mWaterNavigationStatus = new WaterNavigationStatusBean();
        }
        this.mWaterNavigationStatus.setMarkerIndex(i);
        this.mWaterNavigationStatus.setArriveMarker(z);
        this.mWaterNavigationStatus.setComplete(z2);
        MyLogUtils.Logd("NAVIGATION_TAG", "sendWaterNavigationStatusBean:" + this.mWaterNavigationStatus.toString());
        EventBus.getDefault().post(this.mWaterNavigationStatus);
        OnTaskStatusListener onTaskStatusListener = this.mOnTaskStatusListener;
        if (onTaskStatusListener != null) {
            onTaskStatusListener.onTaskStatus(this.mWaterNavigationStatus);
        }
    }

    public int getTaskPriority() {
        return this.mTaskPriority;
    }

    public void setTaskPriority(int i) {
        this.mTaskPriority = i;
    }

    public String toString() {
        Task task = this.mTask;
        if (task == null) {
            return "RobotTask is null";
        }
        return task.getTaskName();
    }

    public void release() {
        MoveTask moveTask = this.mMoveTask;
        if (moveTask != null) {
            moveTask.removeAllTask();
        }
    }

    public void setOnTaskStatusListener(OnTaskStatusListener onTaskStatusListener) {
        this.mOnTaskStatusListener = onTaskStatusListener;
    }

    public long getTaskBeginTime() {
        return this.mTaskBeginTime;
    }
}
