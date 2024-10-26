package com.ciot.navigation.navigation.task;

import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ciot.base.util.MyLogUtils;
import com.ciot.navigation.navigation.task.MoveTaskBean;
import com.ciot.navigation.navigation.task.turnstile.OpenTurnstileBean;
import com.ciot.navigation.navigation.task.turnstile.OpenTurnstileBeanR;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.content.VersionUpgradeContent;

public class TurnstileUtil {
    private static volatile TurnstileUtil sTurnstileUtil;
    private String mAccessToken;
    private RectF mBounds = new RectF();
    private Region mClipRegion = new Region();
    private OpenTurnstileBean mOpenTurnstileBean;
    /* access modifiers changed from: private */
    public Disposable mOpenTurnstileDisposable;
    private Path mPath = new Path();
    private String mPropertyUrl;
    private Region mRegion = new Region();
    private volatile boolean mTurnstileIsOn = false;

    private String getHost(int i) {
        return "192.168.1.232";
    }

    private String getId(int i) {
        return "10:D0:7A:00:09:65";
    }

    private int getPort(int i) {
        return 7733;
    }

    private TurnstileUtil() {
    }

    private synchronized void getOpenTurnstileBean() {
        if (this.mOpenTurnstileBean == null) {
            this.mOpenTurnstileBean = new OpenTurnstileBean();
        }
        int curFloor = SelfChassisState.getInstance().getCurFloor();
        this.mOpenTurnstileBean.setHost(getHost(curFloor));
        this.mOpenTurnstileBean.setPort(getPort(curFloor));
        this.mOpenTurnstileBean.setId(getId(curFloor));
    }

    public static TurnstileUtil getInstance() {
        if (sTurnstileUtil == null) {
            synchronized (TurnstileUtil.class) {
                if (sTurnstileUtil == null) {
                    sTurnstileUtil = new TurnstileUtil();
                }
            }
        }
        return sTurnstileUtil;
    }

    public synchronized boolean currentPositionInTurnstileRegion() {
        if (getPath() == null) {
            return false;
        }
        this.mPath.computeBounds(this.mBounds, true);
        this.mClipRegion.set((int) this.mBounds.left, (int) this.mBounds.top, (int) this.mBounds.right, (int) this.mBounds.bottom);
        this.mRegion.setPath(this.mPath, this.mClipRegion);
        return this.mRegion.contains((int) SelfChassisState.getInstance().getX(), (int) SelfChassisState.getInstance().getY());
    }

    private Path getPath() {
        if (SelfChassisState.getInstance().getCurFloor() != 1) {
            return null;
        }
        this.mPath.reset();
        this.mPath.moveTo(714.0f, 574.0f);
        this.mPath.lineTo(728.0f, 598.0f);
        this.mPath.lineTo(794.0f, 546.0f);
        this.mPath.lineTo(782.0f, 540.0f);
        this.mPath.close();
        return this.mPath;
    }

    public MoveTaskBean getOutTurnstileMarker() {
        int x = (int) SelfChassisState.getInstance().getX();
        int y = (int) SelfChassisState.getInstance().getY();
        int curFloor = SelfChassisState.getInstance().getCurFloor();
        if (curFloor != 1) {
            return null;
        }
        if (Math.sqrt(Math.pow((double) (715 - x), 2.0d) + Math.pow((double) (589 - y), 2.0d)) > Math.sqrt(Math.pow((double) (796 - x), 2.0d) + Math.pow((double) (546 - y), 2.0d))) {
            return new MoveTaskBean.Builder().x((float) VersionUpgradeContent.VersionResultContent.FILE_CHECK_FAILED).y((float) 589).angle(135.0f).floor(curFloor).outTurnstile(true).build();
        }
        return new MoveTaskBean.Builder().x((float) 796).y((float) BaseQuickAdapter.LOADING_VIEW).angle(-45.0f).floor(curFloor).outTurnstile(true).build();
    }

    public synchronized void openTurnstileWithTime(int i) {
        setTurnstileOn(false);
        getOpenTurnstileBean();
        this.mOpenTurnstileBean.setTime(i);
        if (this.mOpenTurnstileDisposable != null && !this.mOpenTurnstileDisposable.isDisposed()) {
            this.mOpenTurnstileDisposable.dispose();
        }
        MyLogUtils.Logw("NAVIGATION_TAG", "开闸间隔时间: " + i);
        Observable.interval(0, (long) (i + -1000), TimeUnit.MILLISECONDS).flatMap(new Function<Long, Observable<OpenTurnstileBeanR>>() {
            public Observable<OpenTurnstileBeanR> apply(Long l) {
                return TurnstileUtil.this.getOpenTurnstileBeanRObservable();
            }
        }).subscribeOn(Schedulers.io()).retry(30).subscribe(new Observer<OpenTurnstileBeanR>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
                Disposable unused = TurnstileUtil.this.mOpenTurnstileDisposable = disposable;
            }

            public void onNext(OpenTurnstileBeanR openTurnstileBeanR) {
                MyLogUtils.Logw("NAVIGATION_TAG", "onNext： " + openTurnstileBeanR.getId());
                TurnstileUtil.this.setTurnstileOn(true);
            }

            public void onError(Throwable th) {
                TurnstileUtil.this.setTurnstileOn(false);
                MyLogUtils.Loge("NAVIGATION_TAG", "onError： " + th);
                String th2 = th.toString();
                ISpeechManagerProvider iSpeechManagerProvider = (ISpeechManagerProvider) ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
                if (th2.contains("HttpException")) {
                    iSpeechManagerProvider.startSpeak("开闸失败，请检查服务器或者闸机网络");
                } else if (th2.contains("ConnectException")) {
                    iSpeechManagerProvider.startSpeak("开闸失败，请检查机器人网络");
                } else {
                    iSpeechManagerProvider.startSpeak("开闸失败，请检查网络");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<OpenTurnstileBeanR> getOpenTurnstileBeanRObservable() {
        String str = this.mPropertyUrl;
        String str2 = this.mAccessToken;
        MyLogUtils.Logw("NAVIGATION_TAG", "Turnstile: propertyUrl  " + str + "   sccessToken: " + str2);
        return null;
    }

    public String getmPropertyUrl() {
        return this.mPropertyUrl;
    }

    public void setmPropertyUrl(String str) {
        this.mPropertyUrl = str;
    }

    public String getmAccessToken() {
        return this.mAccessToken;
    }

    public void setmAccessToken(String str) {
        this.mAccessToken = str;
    }

    public synchronized boolean isTurnstileOff() {
        return !this.mTurnstileIsOn;
    }

    /* access modifiers changed from: private */
    public synchronized void setTurnstileOn(boolean z) {
        this.mTurnstileIsOn = z;
    }

    public synchronized void cancelOpenTurnstile() {
        MyLogUtils.Logw("NAVIGATION_TAG", "cancelOpenTurnstile");
        if (this.mOpenTurnstileDisposable != null && !this.mOpenTurnstileDisposable.isDisposed()) {
            this.mOpenTurnstileDisposable.dispose();
        }
        setTurnstileOn(false);
    }
}
