package com.ciot.navigation.navigation.task;

import android.os.SystemClock;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ciot.base.bean.EventScreenClickMsg;
import com.ciot.base.bean.MsgEvent;
import com.ciot.base.constant.EventBusConstant;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.ThreadPoolUtils;
import com.ciot.navigation.R;
import com.ciot.navigation.navigation.NavigationHelper;
import com.ciot.navigation.navigation.OnNavigationListener;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import java.util.List;
import mc.csst.com.selfchassislibrary.navigation.PositionTag;
import org.greenrobot.eventbus.EventBus;

public class RobotTaskUtil implements Runnable {
    private static volatile RobotTaskUtil sRobotTaskUtil;
    String TAG = "NAVIGATION_TAG";
    /* access modifiers changed from: private */
    public volatile BaseTask mCurrentTask;
    private BaseTask mLastTask;
    private BaseTask mNextTask;
    private boolean mStartExecute = true;

    public void startExecute(boolean z) {
        this.mStartExecute = z;
        String str = this.TAG;
        MyLogUtils.Logd(str, "===设置mStartExecute==" + this.mStartExecute);
    }

    public static RobotTaskUtil getInstance() {
        if (sRobotTaskUtil == null) {
            synchronized (RobotTaskUtil.class) {
                if (sRobotTaskUtil == null) {
                    sRobotTaskUtil = new RobotTaskUtil();
                }
            }
        }
        return sRobotTaskUtil;
    }

    public synchronized boolean setTask(BaseTask baseTask) {
        boolean z = false;
        if (isForbidSendTask()) {
            return false;
        }
        if (baseTask == null) {
            this.mNextTask = null;
            MyLogUtils.Logw(this.TAG, "RobotTaskUtis setTask is null");
        } else if (this.mNextTask == null || this.mNextTask.getTaskPriority() <= baseTask.getTaskPriority()) {
            this.mNextTask = baseTask;
            z = true;
        }
        if (!z && baseTask != null) {
            ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).startSpeak("当前已有更高级别的任务");
        }
        String str = this.TAG;
        MyLogUtils.Logi(str, "setTask is success：" + z + ",mNextTask=" + this.mNextTask);
        return z;
    }

    public void run() {
        MyLogUtils.Logd(this.TAG, "RobotTaskUtil run method execute");
        while (this.mStartExecute) {
            try {
                if (this.mNextTask == null) {
                    SystemClock.sleep(100);
                } else {
                    if (this.mCurrentTask == null || !this.mCurrentTask.isRunning()) {
                        this.mCurrentTask = this.mNextTask;
                        setTask((BaseTask) null);
                    } else {
                        MyLogUtils.Logv(this.TAG, "RobotTaskUtil mCurrentTask isRunning ");
                        if (this.mNextTask.getTaskPriority() < this.mCurrentTask.getTaskPriority()) {
                            MyLogUtils.Logw(this.TAG, "优先级较低，继续当前任务");
                            SystemClock.sleep(100);
                        } else {
                            if (this.mCurrentTask.isRunning()) {
                                MyLogUtils.Logw(this.TAG, "关闭之前任务");
                            }
                            String str = this.TAG;
                            MyLogUtils.Logd(str, this.mCurrentTask.toString() + ",任务等待执行完成");
                            while (this.mCurrentTask.isRunning()) {
                                Log.v(this.TAG, "等待任务执行完成");
                                SystemClock.sleep(100);
                            }
                            String str2 = this.TAG;
                            MyLogUtils.Logd(str2, this.mCurrentTask.toString() + ",任务已经执行完成");
                            this.mLastTask = this.mCurrentTask;
                            this.mCurrentTask = this.mNextTask;
                            setTask((BaseTask) null);
                            addCurrentTask2QueueByAlarmTask();
                        }
                    }
                    executeTask();
                }
            } catch (Exception e) {
                e.printStackTrace();
                String str3 = this.TAG;
                MyLogUtils.Logd(str3, "RobotTaskUtil run Exception:" + e.getLocalizedMessage());
            }
        }
    }

    private void addCurrentTask2QueueByAlarmTask() {
        if (this.mCurrentTask != null && this.mCurrentTask.getTaskPriority() == 4 && this.mCurrentTask.getTaskPriority() > this.mLastTask.getTaskPriority()) {
            MyLogUtils.Logw(this.TAG, "收到报警任务，将当前正在执行的任务加入队列中");
            setTask(this.mLastTask);
        }
    }

    private void executeTask() {
        MyLogUtils.Logd(this.TAG, "RobotTaskUtil executeTask:");
        ThreadPoolUtils.getInstance().execute(this.mCurrentTask);
        while (!this.mCurrentTask.isRunning()) {
            SystemClock.sleep(100);
        }
    }

    public boolean isTaskCurrentlyExecuting() {
        return this.mCurrentTask != null && this.mCurrentTask.isRunning();
    }

    public BaseTask getCurrentTask() {
        return this.mCurrentTask;
    }

    public void clearTask() {
        MyLogUtils.Logd(this.TAG, "clearTask");
        if (this.mCurrentTask != null) {
            this.mCurrentTask.stopTask();
        }
        BaseTask baseTask = this.mNextTask;
        if (baseTask != null) {
            baseTask.stopTask();
        }
    }

    public boolean sendRobotTask(BaseTask baseTask) {
        if (isForbidSendTask()) {
            return false;
        }
        return getInstance().setTask(baseTask);
    }

    public boolean isForbidSendTask() {
        ISpeechManagerProvider iSpeechManagerProvider = (ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
        if (NavigationHelper.Companion.getInstance().isRobotIsLowPower()) {
            iSpeechManagerProvider.startSpeak(ContextUtil.getContext().getString(R.string.navigation_low_power_can_not_execute_current_task));
            return true;
        } else if (NavigationHelper.Companion.getInstance().isInStopState()) {
            iSpeechManagerProvider.startSpeak(ContextUtil.getContext().getString(R.string.navigation_move_error_for_stop));
            return true;
        } else if (NavigationHelper.Companion.getInstance().isConnected()) {
            return false;
        } else {
            iSpeechManagerProvider.startSpeak(ContextUtil.getContext().getString(R.string.navigation_navigation_disconnect2));
            return true;
        }
    }

    public void init() {
        initListener();
    }

    private void initListener() {
        NavigationHelper.Companion.getInstance().addOnNavigationListener(new OnNavigationListener() {
            public void connectResult(boolean z) {
            }

            public void getNavigationTags(boolean z, List<? extends PositionTag> list) {
            }

            public void setInitPositionResult(boolean z, int i, String str) {
            }

            public void updateErrorMessage(int i, String str) {
                ((ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation()).startSpeak(str);
                if (20008 == i) {
                    MyLogUtils.Logw(RobotTaskUtil.this.TAG, "navigate failed:stopTask");
                    EventScreenClickMsg eventScreenClickMsg = new EventScreenClickMsg();
                    eventScreenClickMsg.setCurState(3);
                    EventBus.getDefault().postSticky(eventScreenClickMsg);
                    RobotTaskUtil.this.setTaskMoveStatus(false);
                }
            }

            public void navigationarrived(String str) {
                String str2 = RobotTaskUtil.this.TAG;
                MyLogUtils.Logi(str2, "arriveTargetPosition:" + str);
                EventBus.getDefault().post(new MsgEvent(EventBusConstant.EVENT_ARRIVED_MARKER, str));
                RobotTaskUtil.this.setTaskMoveStatus(false);
            }

            public void setTargetPositionResult(boolean z, int i, String str) {
                if (z) {
                    String str2 = RobotTaskUtil.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("setTargetPositionResult success,currentTask is null:");
                    sb.append(RobotTaskUtil.this.mCurrentTask == null);
                    MyLogUtils.Logd(str2, sb.toString());
                    RobotTaskUtil.this.setTaskMoveStatus(true);
                }
            }

            public void navigationCanceled() {
                if (RobotTaskUtil.this.getCurrentTask() != null) {
                    MyLogUtils.Logw(RobotTaskUtil.this.TAG, "cancleNavigation:cancle Task");
                    RobotTaskUtil.this.setTaskMoveStatus(false);
                    EventScreenClickMsg eventScreenClickMsg = new EventScreenClickMsg();
                    eventScreenClickMsg.setCurState(3);
                    EventBus.getDefault().postSticky(eventScreenClickMsg);
                }
            }

            public void navigationResumed() {
                MyLogUtils.Logd(RobotTaskUtil.this.TAG, "navigationResumed:");
                BaseTask currentTask = RobotTaskUtil.getInstance().getCurrentTask();
                if (currentTask != null) {
                    currentTask.continueTask();
                }
                EventScreenClickMsg eventScreenClickMsg = new EventScreenClickMsg();
                eventScreenClickMsg.setCurState(2);
                EventBus.getDefault().postSticky(eventScreenClickMsg);
            }

            public void navigationPaused() {
                MyLogUtils.Logd(RobotTaskUtil.this.TAG, "navigationPaused:");
                BaseTask currentTask = RobotTaskUtil.getInstance().getCurrentTask();
                if (currentTask != null) {
                    currentTask.pauseTask();
                }
                EventScreenClickMsg eventScreenClickMsg = new EventScreenClickMsg();
                eventScreenClickMsg.setCurState(1);
                EventBus.getDefault().postSticky(eventScreenClickMsg);
            }

            public void senMsg(int i, String str) {
                if (i == 20018) {
                    if (Boolean.parseBoolean(str)) {
                        navigationPaused();
                    } else {
                        navigationResumed();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setTaskMoveStatus(boolean z) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "RbotTaskUtil setTaskMoveStatus :" + z);
        BaseTask currentTask = getCurrentTask();
        if (currentTask != null) {
            currentTask.setMoving(z);
        }
    }

    public void release() {
        MyLogUtils.Logd(this.TAG, "RobotTaskUtil release");
        if (this.mCurrentTask != null) {
            this.mCurrentTask.release();
        }
        BaseTask baseTask = this.mNextTask;
        if (baseTask != null) {
            baseTask.release();
        }
        clearTask();
    }
}
