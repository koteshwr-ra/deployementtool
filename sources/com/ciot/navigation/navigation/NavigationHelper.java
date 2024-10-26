package com.ciot.navigation.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.ciot.base.bean.MsgEvent;
import com.ciot.base.config.NavigationConfig;
import com.ciot.base.constant.EventBusConstant;
import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.CommonObserva;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.DisposeUtil;
import com.ciot.base.util.LogPlus;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.NavigationUtils;
import com.ciot.base.util.ToastUtil;
import com.ciot.navigation.R;
import com.ciot.navigation.navigation.INavigationHelper;
import com.ciot.navigation.navigation.self.listener.OnMarkerListListener;
import com.ciot.navigation.navigation.task.BaseTask;
import com.ciot.navigation.navigation.task.RobotTaskUtil;
import com.ciot.navigation.navigation.task.bean.EventPowerOnChargedBean;
import com.ciot.navigation.navigation.task.bean.TagNameBean;
import com.ciot.navigation.navigation.water.WaterChassisHandlerCallback;
import com.ciot.navigation.navigation.water.WaterNavigateState;
import com.ciot.navigation.navigation.water.WaterStatusLiveData;
import com.ciot.networklib.RetrofitManager;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.waterchassis.WaterChassisHelper;
import com.ciot.waterchassis.bean.WaterMoveBean;
import com.ciot.waterchassis.bean.WaterStatusBean;
import com.ciot.waterchassis.listener.OnConnectListener;
import com.ciot.waterchassis.status.WaterChassisStatus;
import com.example.sroslibrary.bean.QueryNavigationBeanR;
import com.example.sroslibrary.bean.SrosEventMsg;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.limpoxe.support.servicemanager.provider.ARouterConstants;
import com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.navigation.NavigationManager;
import mc.csst.com.selfchassislibrary.navigation.PositionTag;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 ê\u00012\u00020\u0001:\u0004ê\u0001ë\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020cJ\u0010\u0010r\u001a\u00020p2\b\u0010q\u001a\u0004\u0018\u00010hJ\u0010\u0010s\u001a\u00020p2\b\u0010q\u001a\u0004\u0018\u00010jJ\u0006\u0010t\u001a\u00020pJ\b\u0010u\u001a\u00020pH\u0002J\b\u0010v\u001a\u00020pH\u0002J\b\u0010w\u001a\u00020pH\u0002J\u0010\u0010x\u001a\u00020p2\u0006\u0010y\u001a\u00020%H\u0002J\u0006\u0010z\u001a\u00020\u001cJ\b\u0010{\u001a\u00020pH\u0002J\u0010\u0010|\u001a\u00020p2\u0006\u0010}\u001a\u00020~H\u0002J\u0006\u0010\u001a\u00020pJ\u0007\u0010\u0001\u001a\u00020pJ\u0015\u0010\u0001\u001a\u00020p2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0002J\u0010\u0010\u0001\u001a\u00020\u001c2\u0007\u0010\u0001\u001a\u00020%J\u0012\u0010\u0001\u001a\u0004\u0018\u00010\u000b2\u0007\u0010\u0001\u001a\u00020%J\u001c\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001J\u0007\u0010\u0001\u001a\u00020<J\t\u0010\u0001\u001a\u0004\u0018\u00010%J\t\u0010\u0001\u001a\u0004\u0018\u00010nJ\u0007\u0010\u0001\u001a\u00020pJ\u0015\u0010\u0001\u001a\u00020p2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0007J\t\u0010\u0001\u001a\u00020pH\u0002J\u0007\u0010\u0001\u001a\u00020pJ\t\u0010\u0001\u001a\u00020pH\u0002J\t\u0010\u0001\u001a\u00020pH\u0002J\u0019\u0010\u0001\u001a\u00020\u001c2\u0007\u0010\u0001\u001a\u00020%2\u0007\u0010\u0001\u001a\u00020\u000fJ@\u0010\u0001\u001a\u00020p2\u0007\u0010\u0001\u001a\u00020%2\u0007\u0010\u0001\u001a\u00020\u000f2\u0007\u0010\u0001\u001a\u00020\u000f2\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001J\u0007\u0010\u0001\u001a\u00020\u001cJ\u0007\u0010\u0001\u001a\u00020\u001cJ\u0007\u0010\u0001\u001a\u00020\u001cJ\u0007\u0010 \u0001\u001a\u00020\u001cJ\u0007\u0010¡\u0001\u001a\u00020\u001cJ\u0007\u0010¢\u0001\u001a\u00020\u001cJ\u0007\u0010£\u0001\u001a\u00020\u001cJ\u0007\u0010¤\u0001\u001a\u00020\u001cJ\u0010\u0010¥\u0001\u001a\u00020\u001c2\u0007\u0010\u0001\u001a\u00020%J\u001a\u0010¥\u0001\u001a\u00020\u001c2\u0007\u0010\u0001\u001a\u00020%2\b\u0010¦\u0001\u001a\u00030\u0001J\u0010\u0010§\u0001\u001a\u00020%2\u0007\u0010¨\u0001\u001a\u00020%JF\u0010©\u0001\u001a\u00020p2\u0007\u0010ª\u0001\u001a\u00020<2\u0007\u0010«\u0001\u001a\u00020<2\u0007\u0010¬\u0001\u001a\u00020\u000f2\u0007\u0010­\u0001\u001a\u00020<2\u0007\u0010®\u0001\u001a\u00020<2\u0007\u0010¯\u0001\u001a\u00020\u000f2\u0007\u0010°\u0001\u001a\u00020%J\u001d\u0010±\u0001\u001a\u00020p2\u0007\u0010²\u0001\u001a\u00020%2\t\b\u0002\u0010³\u0001\u001a\u00020\u001cH\u0002J\u0007\u0010´\u0001\u001a\u00020pJ\u0007\u0010µ\u0001\u001a\u00020pJ\u0007\u0010¶\u0001\u001a\u00020pJ\u0007\u0010·\u0001\u001a\u00020pJ\u000f\u0010¸\u0001\u001a\u00020p2\u0006\u0010q\u001a\u00020cJ\u0011\u0010¹\u0001\u001a\u00020p2\b\u0010q\u001a\u0004\u0018\u00010hJ\u000f\u0010º\u0001\u001a\u00020p2\u0006\u0010q\u001a\u00020jJ\u0007\u0010»\u0001\u001a\u00020pJ\u0010\u0010¼\u0001\u001a\u00020p2\u0007\u0010½\u0001\u001a\u00020\u001cJ\u0007\u0010¾\u0001\u001a\u00020pJ\u0007\u0010¿\u0001\u001a\u00020pJ\u0007\u0010À\u0001\u001a\u00020pJ\u0007\u0010Á\u0001\u001a\u00020pJ@\u0010Â\u0001\u001a\u00020p2\u0007\u0010\u0001\u001a\u00020%2\u0007\u0010\u0001\u001a\u00020\u000f2\u0007\u0010\u0001\u001a\u00020\u000f2\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001J\u0007\u0010Ã\u0001\u001a\u00020pJ\u0012\u0010Ä\u0001\u001a\u00020p2\u0007\u0010Å\u0001\u001a\u00020\u000fH\u0002J\u0010\u0010Æ\u0001\u001a\u00020p2\u0007\u0010Ç\u0001\u001a\u00020<J\u0017\u0010È\u0001\u001a\u00020p2\u000e\u0010É\u0001\u001a\t\u0012\u0005\u0012\u00030Ê\u00010\nJ\u0010\u0010Ë\u0001\u001a\u00020p2\u0007\u0010Ì\u0001\u001a\u00020\u000fJ\u0010\u0010Í\u0001\u001a\u00020p2\u0007\u0010Î\u0001\u001a\u00020\u001cJ\u0010\u0010Ï\u0001\u001a\u00020p2\u0007\u0010¡\u0001\u001a\u00020\u001cJ\u000f\u0010Ð\u0001\u001a\u00020p2\u0006\u0010q\u001a\u00020eJ\u0012\u0010Ñ\u0001\u001a\u00020p2\t\u0010\u0001\u001a\u0004\u0018\u00010%J\u0010\u0010Ò\u0001\u001a\u00020p2\u0007\u0010Ó\u0001\u001a\u00020\u001cJ\u0010\u0010Ô\u0001\u001a\u00020p2\u0007\u0010Õ\u0001\u001a\u00020\u001cJ-\u0010Ö\u0001\u001a\u00020p2\u0007\u0010\u0001\u001a\u00020<2\u0007\u0010\u0001\u001a\u00020<2\u0007\u0010×\u0001\u001a\u00020<2\t\b\u0002\u0010³\u0001\u001a\u00020\u001cJ\u001b\u0010Ö\u0001\u001a\u00020p2\u0007\u0010²\u0001\u001a\u00020%2\t\b\u0002\u0010³\u0001\u001a\u00020\u001cJ\u0011\u0010Ø\u0001\u001a\u00020p2\b\u0010Ù\u0001\u001a\u00030Ú\u0001J-\u0010Û\u0001\u001a\u00020p2\u0007\u0010Ü\u0001\u001a\u00020%2\t\u0010Ý\u0001\u001a\u0004\u0018\u00010%2\u0007\u0010Þ\u0001\u001a\u00020%2\u0007\u0010ß\u0001\u001a\u00020\u000fJ\t\u0010à\u0001\u001a\u00020pH\u0002J\t\u0010á\u0001\u001a\u00020pH\u0002J\t\u0010â\u0001\u001a\u00020pH\u0002J\u0007\u0010ã\u0001\u001a\u00020pJ\u0011\u0010ä\u0001\u001a\u00020p2\b\u0010å\u0001\u001a\u00030æ\u0001J\u001b\u0010ç\u0001\u001a\u00020p2\u0007\u0010¨\u0001\u001a\u00020%2\t\u0010è\u0001\u001a\u0004\u0018\u00010%J\u0019\u0010é\u0001\u001a\u00020p2\u0007\u0010²\u0001\u001a\u00020%2\u0007\u0010³\u0001\u001a\u00020\u001cR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158FX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0011\"\u0004\b,\u0010\u0013R\u000e\u0010-\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0011\"\u0004\b2\u0010\u0013R\u001a\u00103\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0011\"\u0004\b5\u0010\u0013R\u001a\u00106\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0011\"\u0004\b8\u0010\u0013R\u0012\u00109\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0004\n\u0002\u0010:R\u001e\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001e\u0010B\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\bC\u0010>\"\u0004\bD\u0010@R\u001c\u0010E\u001a\u0004\u0018\u00010FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0012\u0010K\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0004\n\u0002\u0010:R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010N\u001a\u0004\u0018\u00010OX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010Y\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010'\"\u0004\b[\u0010)R\u001c\u0010\\\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010'\"\u0004\b^\u0010)R\u0010\u0010_\u001a\u0004\u0018\u00010`X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010a\u001a\b\u0012\u0004\u0012\u00020c0bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u0004\u0018\u00010eX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010f\u001a\n\u0012\u0004\u0012\u00020h\u0018\u00010gX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010i\u001a\n\u0012\u0004\u0012\u00020j\u0018\u00010gX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u0004\u0018\u00010MX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010m\u001a\u0004\u0018\u00010nX\u000e¢\u0006\u0002\n\u0000¨\u0006ì\u0001"}, d2 = {"Lcom/ciot/navigation/navigation/NavigationHelper;", "", "()V", "LOW_POWER_THRESHOLD", "Landroidx/databinding/ObservableInt;", "getLOW_POWER_THRESHOLD", "()Landroidx/databinding/ObservableInt;", "setLOW_POWER_THRESHOLD", "(Landroidx/databinding/ObservableInt;)V", "allTags", "", "Lmc/csst/com/selfchassislibrary/navigation/PositionTag;", "getAllTags", "()Ljava/util/List;", "chassisType", "", "getChassisType", "()I", "setChassisType", "(I)V", "commonObserva", "Lcom/ciot/base/util/CommonObserva;", "Lcom/ciot/navigation/navigation/task/bean/TagNameBean;", "getCommonObserva", "()Lcom/ciot/base/util/CommonObserva;", "commonObserva$delegate", "Lkotlin/Lazy;", "isOpendCancleTask", "", "mAllTagList", "mChargeTagIndex", "getMChargeTagIndex", "()Ljava/lang/Integer;", "setMChargeTagIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "mChargeTagName", "", "getMChargeTagName", "()Ljava/lang/String;", "setMChargeTagName", "(Ljava/lang/String;)V", "mCurChassisType", "getMCurChassisType", "setMCurChassisType", "mCurWaterState", "mCurWaterStatusBean", "Lcom/ciot/waterchassis/bean/WaterStatusBean;", "mCurrentBattery", "getMCurrentBattery", "setMCurrentBattery", "mCurrentChargingState", "getMCurrentChargingState", "setMCurrentChargingState", "mCurrentFloor", "getMCurrentFloor", "setMCurrentFloor", "mCurrentHardStopState", "Ljava/lang/Boolean;", "mCurrentMaxLinearSpeed", "", "getMCurrentMaxLinearSpeed", "()Ljava/lang/Float;", "setMCurrentMaxLinearSpeed", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "mCurrentMiles", "getMCurrentMiles", "setMCurrentMiles", "mCurrentPosition", "Lcom/ciot/waterchassis/bean/WaterStatusBean$ResultsBean$CurrentPoseBean;", "getMCurrentPosition", "()Lcom/ciot/waterchassis/bean/WaterStatusBean$ResultsBean$CurrentPoseBean;", "setMCurrentPosition", "(Lcom/ciot/waterchassis/bean/WaterStatusBean$ResultsBean$CurrentPoseBean;)V", "mCurrentSoftStopState", "mDisable", "Lio/reactivex/disposables/Disposable;", "mINavigationHelper", "Lcom/ciot/navigation/navigation/INavigationHelper;", "getMINavigationHelper", "()Lcom/ciot/navigation/navigation/INavigationHelper;", "setMINavigationHelper", "(Lcom/ciot/navigation/navigation/INavigationHelper;)V", "mIsNavigateConnected", "mIsNavigateInitSuccess", "mIsNavigating", "mIsPaused", "mIsRobotIsLowPower", "mLastTagId", "getMLastTagId", "setMLastTagId", "mLastTagName", "getMLastTagName", "setMLastTagName", "mNavIntent", "Landroid/content/Intent;", "mOnAllStatusListeners", "", "Lcom/ciot/navigation/navigation/OnAllStatusListener;", "mOnMarkerListListener", "Lcom/ciot/navigation/navigation/self/listener/OnMarkerListListener;", "mOnNavigationListeners", "Ljava/util/LinkedList;", "Lcom/ciot/navigation/navigation/OnNavigationListener;", "mOnTaskListeners", "Lcom/ciot/navigation/navigation/OnTaskStateListener;", "mReSetTagetTask", "mTempWaterChargeState", "wHandler", "Landroid/os/Handler;", "addOnAllStatusListener", "", "listener", "addOnNavigationListener", "addTaskStateListener", "autoCharge", "batteryStateChanged", "callbackArrived", "callbackCanceled", "callbackNavigateFailed", "errorMessage", "cancelNavigation", "chargingStateChanged", "checkIsChargingInLowPower", "num", "", "clearOnNavigationListener", "clearTaskListeners", "dealChassisState", "selfChassisState", "Lmc/csst/com/selfchassislibrary/chassis/SelfChassisState;", "deleteMarkerByName", "markerName", "findTagByName", "name", "getAngle", "", "orientationW", "orientationZ", "getCurrentMiles", "getReceptionTag", "getThreadHandler", "gotoReception", "hanldEvent", "bean", "Lcom/example/sroslibrary/bean/SrosEventMsg;", "hardStopStateChanged", "initNav", "initSelfSdkListener", "initWaterSdkListener", "insertMarker", "type", "floor", "x", "y", "theta", "isCharging", "isConnected", "isInStopState", "isInitSuccess", "isNavigating", "isPaused", "isRobotIsLowPower", "isSurpportToReception", "isTagInPosition", "distane", "loadSpeakTxt", "key", "makePlanDistance", "start_x", "start_y", "start_floor", "goal_x", "goal_y", "goal_floor", "uuid", "navigatTag", "tagName", "isShouldAnswerResult", "pause", "queryAllMapList", "queryAllMarkers", "release", "removeOnAllStatusListener", "removeOnNavigationListener", "removeTaskStateListener", "resume", "sendEStop", "stop", "sendGetMarkerList", "sendGetMaxSpeedRatio", "sendGetRobotInfo", "sendGetRobotVersion", "sendInsertMarkerByPose", "sendLowPowerBackChargePile", "sendNavStateChangeBroadcast", "MsgType", "sendSetMaxSpeedRatio", "maxSpeedRatio", "setAllPoint", "navigationPoints", "Lcom/example/sroslibrary/bean/QueryNavigationBeanR$NavigationPoint;", "setChargingState", "chargingState", "setHardStopState", "isHardStopState", "setNavigating", "setOnMarkerListListener", "setRceptionTag", "setRobotIsLowPower", "boolean", "setSoftStopState", "isSoftStopState", "setTargetPosition", "radians", "setTaskOperation", "operation", "Lcom/ciot/realm/db/patrol/Operation;", "setTaskStatus", "taskId", "taskNodeId", "taskName", "taskStatus", "showLowPowerBackChargingFragment", "softStopStateChanged", "startlowPowerCharge", "updateChassisVersion", "updateMoveResponse", "waterMoveBean", "Lcom/ciot/waterchassis/bean/WaterMoveBean;", "updateSpeakTxt", "content", "waitReconnect", "Companion", "NevigationHelperHolder", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NavigationHelper.kt */
public final class NavigationHelper {
    public static final String ACTION_BROADCAST_NAV_STATE_CHANGED = "NAVAGATION_STATE_CHANGED_ACTION";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DEFAULT_MONITOR_URL = "ws://192.168.20.22:9090";
    public static final String DEFAULT_RECEPTION_TAG = "";
    private static final String NAVIGATION_TAG = "NAVIGATION_TAG";
    /* access modifiers changed from: private */
    public static final String TAG = "NAVIGATION_TAG";
    /* access modifiers changed from: private */
    public static volatile String mReceptionTagName;
    private ObservableInt LOW_POWER_THRESHOLD;
    private final Lazy commonObserva$delegate;
    private boolean isOpendCancleTask;
    /* access modifiers changed from: private */
    public volatile List<? extends PositionTag> mAllTagList;
    private Integer mChargeTagIndex;
    private String mChargeTagName;
    private int mCurChassisType;
    /* access modifiers changed from: private */
    public volatile String mCurWaterState;
    /* access modifiers changed from: private */
    public WaterStatusBean mCurWaterStatusBean;
    private volatile int mCurrentBattery;
    private int mCurrentChargingState;
    private int mCurrentFloor;
    /* access modifiers changed from: private */
    public volatile Boolean mCurrentHardStopState;
    private Float mCurrentMaxLinearSpeed;
    private Float mCurrentMiles;
    private WaterStatusBean.ResultsBean.CurrentPoseBean mCurrentPosition;
    /* access modifiers changed from: private */
    public volatile Boolean mCurrentSoftStopState;
    private Disposable mDisable;
    private INavigationHelper mINavigationHelper;
    /* access modifiers changed from: private */
    public volatile boolean mIsNavigateConnected;
    /* access modifiers changed from: private */
    public boolean mIsNavigateInitSuccess;
    private volatile boolean mIsNavigating;
    /* access modifiers changed from: private */
    public boolean mIsPaused;
    /* access modifiers changed from: private */
    public volatile boolean mIsRobotIsLowPower;
    private String mLastTagId;
    private String mLastTagName;
    private Intent mNavIntent;
    /* access modifiers changed from: private */
    public List<OnAllStatusListener> mOnAllStatusListeners;
    private OnMarkerListListener mOnMarkerListListener;
    /* access modifiers changed from: private */
    public LinkedList<OnNavigationListener> mOnNavigationListeners;
    private LinkedList<OnTaskStateListener> mOnTaskListeners;
    private Disposable mReSetTagetTask;
    /* access modifiers changed from: private */
    public int mTempWaterChargeState;
    private Handler wHandler;

    public /* synthetic */ NavigationHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void sendLowPowerBackChargePile() {
    }

    private NavigationHelper() {
        this.mCurWaterState = WaterNavigateState.IDLE;
        this.mCurChassisType = MySpUtils.getInstance().getInt(SpConstant.SP_CHASSIS_TYPE, 1);
        this.mCurrentFloor = 1;
        this.LOW_POWER_THRESHOLD = new ObservableInt(AppSpUtil.getInstance().getLowBatteryValue());
        this.mCurrentHardStopState = false;
        this.mCurrentSoftStopState = false;
        this.mChargeTagIndex = -1;
        this.mOnAllStatusListeners = new ArrayList();
        this.commonObserva$delegate = LazyKt.lazy(NavigationHelper$commonObserva$2.INSTANCE);
        Observable.create(getCommonObserva()).throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe(new Consumer() {
            public final void accept(Object obj) {
                NavigationHelper.m1_init_$lambda0(NavigationHelper.this, (TagNameBean) obj);
            }
        });
    }

    public final int getMCurChassisType() {
        return this.mCurChassisType;
    }

    public final void setMCurChassisType(int i) {
        this.mCurChassisType = i;
    }

    public final int getMCurrentFloor() {
        return this.mCurrentFloor;
    }

    public final void setMCurrentFloor(int i) {
        this.mCurrentFloor = i;
    }

    public final ObservableInt getLOW_POWER_THRESHOLD() {
        return this.LOW_POWER_THRESHOLD;
    }

    public final void setLOW_POWER_THRESHOLD(ObservableInt observableInt) {
        Intrinsics.checkNotNullParameter(observableInt, "<set-?>");
        this.LOW_POWER_THRESHOLD = observableInt;
    }

    public final int getMCurrentBattery() {
        return this.mCurrentBattery;
    }

    public final void setMCurrentBattery(int i) {
        this.mCurrentBattery = i;
    }

    public final WaterStatusBean.ResultsBean.CurrentPoseBean getMCurrentPosition() {
        return this.mCurrentPosition;
    }

    public final void setMCurrentPosition(WaterStatusBean.ResultsBean.CurrentPoseBean currentPoseBean) {
        this.mCurrentPosition = currentPoseBean;
    }

    public final int getMCurrentChargingState() {
        return this.mCurrentChargingState;
    }

    public final void setMCurrentChargingState(int i) {
        this.mCurrentChargingState = i;
    }

    public final String getMChargeTagName() {
        return this.mChargeTagName;
    }

    public final void setMChargeTagName(String str) {
        this.mChargeTagName = str;
    }

    public final Integer getMChargeTagIndex() {
        return this.mChargeTagIndex;
    }

    public final void setMChargeTagIndex(Integer num) {
        this.mChargeTagIndex = num;
    }

    public final String getMLastTagName() {
        return this.mLastTagName;
    }

    public final void setMLastTagName(String str) {
        this.mLastTagName = str;
    }

    public final String getMLastTagId() {
        return this.mLastTagId;
    }

    public final void setMLastTagId(String str) {
        this.mLastTagId = str;
    }

    public final Float getMCurrentMaxLinearSpeed() {
        return this.mCurrentMaxLinearSpeed;
    }

    public final void setMCurrentMaxLinearSpeed(Float f) {
        this.mCurrentMaxLinearSpeed = f;
    }

    public final List<PositionTag> getAllTags() {
        return this.mAllTagList;
    }

    public final INavigationHelper getMINavigationHelper() {
        return this.mINavigationHelper;
    }

    public final void setMINavigationHelper(INavigationHelper iNavigationHelper) {
        this.mINavigationHelper = iNavigationHelper;
    }

    public final CommonObserva<TagNameBean> getCommonObserva() {
        return (CommonObserva) this.commonObserva$delegate.getValue();
    }

    public final int getChassisType() {
        return this.mCurChassisType;
    }

    public final void setChassisType(int i) {
        if (i != this.mCurChassisType) {
            this.mCurChassisType = i;
            MySpUtils.getInstance().put(SpConstant.SP_CHASSIS_TYPE, i);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m1_init_$lambda0(NavigationHelper navigationHelper, TagNameBean tagNameBean) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        String str = tagNameBean.tagName;
        Intrinsics.checkNotNullExpressionValue(str, "it.tagName");
        navigationHelper.navigatTag(str, tagNameBean.isShouldAnswerResult);
    }

    public final void initNav() {
        if (this.mINavigationHelper != null) {
            MyLogUtils.Logw(TAG, "NavigationHelper init cancel,state is connected!");
            return;
        }
        setNavigating(false);
        mReceptionTagName = MySpUtils.getInstance().getString(NavigationConfig.NAVIGATION_RECEPTION_NAME, "");
        int i = this.mCurChassisType;
        if (i == 1) {
            this.mINavigationHelper = SelfNavigationHelper.Companion.getInstance();
            initSelfSdkListener();
            INavigationHelper iNavigationHelper = this.mINavigationHelper;
            if (iNavigationHelper != null) {
                iNavigationHelper.init();
            }
        } else if (i == 2) {
            this.mINavigationHelper = WaterNavigationHelper.Companion.getInstance();
            initWaterSdkListener();
            INavigationHelper iNavigationHelper2 = this.mINavigationHelper;
            if (iNavigationHelper2 != null) {
                iNavigationHelper2.init();
            }
        }
        Disposable disposable = this.mDisable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mDisable = Observable.interval(2000, (long) CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.single()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                NavigationHelper.m8initNav$lambda1(NavigationHelper.this, (Long) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: initNav$lambda-1  reason: not valid java name */
    public static final void m8initNav$lambda1(NavigationHelper navigationHelper, Long l) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullExpressionValue(l, "num");
        navigationHelper.checkIsChargingInLowPower(l.longValue());
    }

    public final boolean cancelNavigation() {
        MyLogUtils.Logd(TAG, "ready cancleNavigation");
        Disposable disposable = this.mReSetTagetTask;
        if (disposable != null) {
            disposable.dispose();
        }
        boolean z = false;
        setNavigating(false);
        this.mIsPaused = false;
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            z = iNavigationHelper.cancelNavigation();
        }
        MyLogUtils.Logd(TAG, "NavigationHelper cancleNavigation");
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m6cancelNavigation$lambda2(NavigationHelper.this);
                    }
                });
            }
        }
        EventBus.getDefault().post(new MsgEvent(EventBusConstant.EVENT_CANCEL_MARKER, ""));
        return z;
    }

    /* access modifiers changed from: private */
    /* renamed from: cancelNavigation$lambda-2  reason: not valid java name */
    public static final void m6cancelNavigation$lambda2(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).navigationCanceled();
        }
    }

    public final boolean isNavigating() {
        return this.mIsNavigating;
    }

    public final void pause() {
        this.mIsPaused = true;
        MyLogUtils.Logi(TAG, "Navigation paused");
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.pause();
        }
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m28pause$lambda3(NavigationHelper.this);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: pause$lambda-3  reason: not valid java name */
    public static final void m28pause$lambda3(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).navigationPaused();
        }
    }

    public final boolean isPaused() {
        return this.mIsPaused;
    }

    public final void resume() {
        if (this.mIsPaused && this.mLastTagName != null) {
            MyLogUtils.Logi(TAG, "Navigation resume");
            LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
            if (linkedList != null) {
                Intrinsics.checkNotNull(linkedList);
                if (linkedList.size() > 0) {
                    ThreadUtils.runOnUiThread(new Runnable() {
                        public final void run() {
                            NavigationHelper.m29resume$lambda5$lambda4(NavigationHelper.this);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: resume$lambda-5$lambda-4  reason: not valid java name */
    public static final void m29resume$lambda5$lambda4(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).navigationResumed();
        }
    }

    public final String getReceptionTag() {
        return mReceptionTagName;
    }

    public final void setRceptionTag(String str) {
        String str2 = TAG;
        MyLogUtils.Logd(str2, "setRceptionTag" + str);
        mReceptionTagName = str;
    }

    public final boolean isConnected() {
        return this.mIsNavigateConnected;
    }

    public final boolean isInitSuccess() {
        return this.mIsNavigateInitSuccess;
    }

    public final boolean isSurpportToReception() {
        return !isInStopState() && isConnected() && !isNavigating() && !isCharging();
    }

    public static /* synthetic */ void setTargetPosition$default(NavigationHelper navigationHelper, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        navigationHelper.setTargetPosition(str, z);
    }

    public final synchronized void setTargetPosition(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "tagName");
        MyLogUtils.Logd("NAVIGATION_TAG", "setTargetPosition tagName = " + str);
        getCommonObserva().setInfo(new TagNameBean(str, z));
    }

    public final void queryAllMarkers() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationHelper queryAllMarkers,mCurChassisType=");
        sb.append(this.mCurChassisType);
        sb.append(",mINavigationHelper is null:");
        sb.append(this.mINavigationHelper == null);
        MyLogUtils.Logd(str, sb.toString());
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.queryAllMarkers();
        }
    }

    public final boolean insertMarker(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "markerName");
        MyLogUtils.Logd("NAVIGATION_TAG", "NavigationHelper sendInsertMarker:markerName=" + str + ",type=" + i);
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            INavigationHelper.DefaultImpls.insertMarker$default(iNavigationHelper, str, i, 0, 0.0d, 0.0d, 0.0d, 60, (Object) null);
        }
        return this.mINavigationHelper != null;
    }

    public final void insertMarker(String str, int i, int i2, double d, double d2, double d3) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationHelper insertMarker,mCurChassisType=");
        sb.append(this.mCurChassisType);
        sb.append(",mINavigationHelper is null:");
        sb.append(this.mINavigationHelper == null);
        MyLogUtils.Logd(str3, sb.toString());
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.insertMarker(str, i, i2, d, d2, d3);
        }
    }

    public final void makePlanDistance(float f, float f2, int i, float f3, float f4, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, "uuid");
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationHelper makePlanDistance,mCurChassisType=");
        sb.append(this.mCurChassisType);
        sb.append(",mINavigationHelper is null:");
        sb.append(this.mINavigationHelper == null);
        MyLogUtils.Logd(str2, sb.toString());
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.getBetweenPath(f, f2, i, f3, f4, i2, str);
        }
    }

    public final void updateChassisVersion() {
        int i = this.mCurChassisType;
        if (i == 1) {
            INavigationHelper instance = SelfNavigationHelper.Companion.getInstance();
            this.mINavigationHelper = instance;
            if (instance != null) {
                instance.updateChassisVersion();
            }
        } else if (i == 2) {
            INavigationHelper instance2 = WaterNavigationHelper.Companion.getInstance();
            this.mINavigationHelper = instance2;
            if (instance2 != null) {
                instance2.updateChassisVersion();
            }
        }
    }

    public final boolean deleteMarkerByName(String str) {
        Intrinsics.checkNotNullParameter(str, "markerName");
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationHelper deleteMarkerByName,markerName=");
        sb.append(str);
        sb.append(",mCurChassisType=");
        sb.append(this.mCurChassisType);
        sb.append(",mINavigationHelper is null:");
        sb.append(this.mINavigationHelper == null);
        MyLogUtils.Logd(str2, sb.toString());
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.deleteMarkerByName(str);
        }
        if (this.mINavigationHelper != null) {
            return true;
        }
        return false;
    }

    public final void sendEStop(boolean z) {
        int i = MySpUtils.getInstance().getInt(SpConstant.SP_CHASSIS_TYPE, 1);
        if (i == 1) {
            SelfChassis.getInstance().sendEStop(z);
        } else if (i == 2) {
            WaterChassisHelper.getInstance().sendEStop(z);
        }
    }

    public final void sendSetMaxSpeedRatio(float f) {
        MyLogUtils.Logd("NAVIGATION_TAG", "NavigationHelper sendSetMaxSpeedRatio:" + f);
        if (this.mCurChassisType == 2) {
            WaterChassisHelper.getInstance().sendSetMaxSpeedRatio(f);
        }
    }

    public final void sendInsertMarkerByPose(String str, int i, int i2, double d, double d2, double d3) {
        String str2 = str;
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        Intrinsics.checkNotNullParameter(str, "markerName");
        StringBuilder sb = new StringBuilder();
        sb.append("NavigationHelper sendInsertMarkerByPose markerName=");
        sb.append(str);
        sb.append("?name=");
        sb.append(str);
        sb.append("&x=");
        sb.append(d4);
        sb.append("&y=");
        sb.append(d5);
        sb.append("&theta=");
        sb.append(d6);
        sb.append("&floor=");
        int i3 = i2;
        sb.append(i2);
        sb.append("&type=");
        int i4 = i;
        sb.append(i);
        MyLogUtils.Logd("NAVIGATION_TAG", sb.toString());
        int i5 = this.mCurChassisType;
        if (i5 == 1) {
            SelfChassis.getInstance().sendInsertMarkerByPose(str, i, (float) d4, (float) d5, (float) d6);
        } else if (i5 == 2) {
            WaterChassisHelper.getInstance().sendInsertMarkerByPose(str, i, i2, d, d2, d3);
        }
    }

    public final void updateMoveResponse(WaterMoveBean waterMoveBean) {
        Intrinsics.checkNotNullParameter(waterMoveBean, "waterMoveBean");
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper == null || !(iNavigationHelper instanceof WaterNavigationHelper)) {
            return;
        }
        if (Intrinsics.areEqual((Object) waterMoveBean.getStatus(), (Object) "OK")) {
            INavigationHelper iNavigationHelper2 = this.mINavigationHelper;
            if (iNavigationHelper2 != null) {
                ((WaterNavigationHelper) iNavigationHelper2).setMSendMoveSuccess(true);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.ciot.navigation.navigation.WaterNavigationHelper");
        }
        INavigationHelper iNavigationHelper3 = this.mINavigationHelper;
        if (iNavigationHelper3 != null) {
            ((WaterNavigationHelper) iNavigationHelper3).setMSendMoveSuccess(false);
            INavigationHelper iNavigationHelper4 = this.mINavigationHelper;
            if (iNavigationHelper4 != null) {
                WaterNavigationHelper waterNavigationHelper = (WaterNavigationHelper) iNavigationHelper4;
                if (iNavigationHelper4 != null) {
                    waterNavigationHelper.setMWaitTime(((WaterNavigationHelper) iNavigationHelper4).getMAX_TIMEOUT_TIMES());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.ciot.navigation.navigation.WaterNavigationHelper");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.ciot.navigation.navigation.WaterNavigationHelper");
        }
        throw new NullPointerException("null cannot be cast to non-null type com.ciot.navigation.navigation.WaterNavigationHelper");
    }

    public final void sendGetMarkerList() {
        MyLogUtils.Logd("NAVIGATION_TAG", "NavigationHelper sendGetMarkerList");
        int i = this.mCurChassisType;
        if (i == 1) {
            SelfChassis.getInstance().sendGetMarkerList();
        } else if (i == 2) {
            WaterChassisHelper.getInstance().sendGetMarkerList((Integer) null);
        }
    }

    public final void setTaskStatus(String str, String str2, String str3, int i) {
        Intrinsics.checkNotNullParameter(str, "taskId");
        Intrinsics.checkNotNullParameter(str3, "taskName");
        LinkedList<OnTaskStateListener> linkedList = this.mOnTaskListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ThreadUtils.runOnUiThread(new Runnable(str, str2, str3, i) {
                    public final /* synthetic */ String f$1;
                    public final /* synthetic */ String f$2;
                    public final /* synthetic */ String f$3;
                    public final /* synthetic */ int f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        NavigationHelper.m38setTaskStatus$lambda6(OnTaskStateListener.this, this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTaskStatus$lambda-6  reason: not valid java name */
    public static final void m38setTaskStatus$lambda6(OnTaskStateListener onTaskStateListener, String str, String str2, String str3, int i) {
        Intrinsics.checkNotNullParameter(onTaskStateListener, "$listener");
        Intrinsics.checkNotNullParameter(str, "$taskId");
        Intrinsics.checkNotNullParameter(str3, "$taskName");
        onTaskStateListener.setTastStatus(str, str2, str3, i);
    }

    public final void setTaskOperation(Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (this.mOnTaskListeners != null) {
            ThreadUtils.runOnUiThread(new Runnable(operation) {
                public final /* synthetic */ Operation f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    NavigationHelper.m37setTaskOperation$lambda7(NavigationHelper.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTaskOperation$lambda-7  reason: not valid java name */
    public static final void m37setTaskOperation$lambda7(NavigationHelper navigationHelper, Operation operation) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        LinkedList<OnTaskStateListener> linkedList = navigationHelper.mOnTaskListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnTaskStateListener) it.next()).setTastOperation(operation);
        }
    }

    public final void sendGetRobotVersion() {
        if (this.mCurChassisType == 2) {
            WaterChassisHelper.getInstance().sendGetRobotVersion();
        }
    }

    public final void sendGetRobotInfo() {
        if (this.mCurChassisType == 2) {
            WaterChassisHelper.getInstance().sendGetRobotInfo();
        }
    }

    public final void sendGetMaxSpeedRatio() {
        if (this.mCurChassisType == 2) {
            WaterChassisHelper.getInstance().sendGetMaxSpeedRatio();
        }
    }

    public final double getAngle(double d, double d2) {
        double atan2 = ((double) 2) * Math.atan2(d2, d);
        if (atan2 > 3.141592653589793d) {
            return atan2 - 6.283185307179586d;
        }
        return atan2 < -3.141592653589793d ? atan2 + 6.283185307179586d : atan2;
    }

    public final Float getMCurrentMiles() {
        return this.mCurrentMiles;
    }

    public final void setMCurrentMiles(Float f) {
        this.mCurrentMiles = f;
    }

    public final float getCurrentMiles() {
        Float f = this.mCurrentMiles;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    private final void initSelfSdkListener() {
        NavigationManager.getInstance().setNavigationListener(new NavigationHelper$initSelfSdkListener$1(this));
    }

    /* access modifiers changed from: private */
    public final void callbackNavigateFailed(String str) {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable(str) {
                    public final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NavigationHelper.m5callbackNavigateFailed$lambda8(NavigationHelper.this, this.f$1);
                    }
                });
            }
        }
        setNavigating(false);
        this.mIsPaused = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: callbackNavigateFailed$lambda-8  reason: not valid java name */
    public static final void m5callbackNavigateFailed$lambda8(NavigationHelper navigationHelper, String str) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(str, "$errorMessage");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).updateErrorMessage(20008, str);
        }
    }

    /* access modifiers changed from: private */
    public final void callbackArrived() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m3callbackArrived$lambda9(NavigationHelper.this);
                    }
                });
            }
        }
        setNavigating(false);
        this.mIsPaused = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: callbackArrived$lambda-9  reason: not valid java name */
    public static final void m3callbackArrived$lambda9(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).navigationarrived(navigationHelper.mLastTagName);
        }
    }

    /* access modifiers changed from: private */
    public final void callbackCanceled() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m4callbackCanceled$lambda10(NavigationHelper.this);
                    }
                });
                EventBus.getDefault().post(new MsgEvent(EventBusConstant.EVENT_CANCEL_MARKER, ""));
            }
        }
        setNavigating(false);
    }

    /* access modifiers changed from: private */
    /* renamed from: callbackCanceled$lambda-10  reason: not valid java name */
    public static final void m4callbackCanceled$lambda10(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).navigationCanceled();
        }
    }

    /* access modifiers changed from: private */
    public final void dealChassisState(SelfChassisState selfChassisState) {
        boolean z = true;
        Integer num = null;
        if (!(selfChassisState != null && this.mCurrentChargingState == selfChassisState.getCharging())) {
            this.mCurrentChargingState = selfChassisState != null ? selfChassisState.getCharging() : 0;
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("mCurrentChargingState=");
            sb.append(selfChassisState != null ? Integer.valueOf(selfChassisState.getCharging()) : null);
            MyLogUtils.Logd(str, sb.toString());
            chargingStateChanged();
        }
        if (!Intrinsics.areEqual((Object) this.mCurrentSoftStopState, (Object) selfChassisState != null ? Boolean.valueOf(selfChassisState.isSoftStop()) : null)) {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("mCurrentSoftStopState=");
            sb2.append(selfChassisState != null ? Boolean.valueOf(selfChassisState.isSoftStop()) : null);
            MyLogUtils.Logd(str2, sb2.toString());
            this.mCurrentSoftStopState = selfChassisState != null ? Boolean.valueOf(selfChassisState.isSoftStop()) : null;
            softStopStateChanged();
        }
        if (!Intrinsics.areEqual((Object) this.mCurrentHardStopState, (Object) selfChassisState != null ? Boolean.valueOf(selfChassisState.isHardStop()) : null)) {
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("mCurrentHardStopState=");
            sb3.append(selfChassisState != null ? Boolean.valueOf(selfChassisState.isHardStop()) : null);
            MyLogUtils.Logd(str3, sb3.toString());
            this.mCurrentHardStopState = selfChassisState != null ? Boolean.valueOf(selfChassisState.isHardStop()) : null;
            hardStopStateChanged();
        }
        if (!(selfChassisState != null && this.mCurrentBattery == selfChassisState.getBattery())) {
            String str4 = TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("mCurrentBattery=");
            sb4.append(selfChassisState != null ? Integer.valueOf(selfChassisState.getBattery()) : null);
            MyLogUtils.Logd(str4, sb4.toString());
            Integer valueOf = selfChassisState != null ? Integer.valueOf(selfChassisState.getBattery()) : null;
            Intrinsics.checkNotNull(valueOf);
            this.mCurrentBattery = valueOf.intValue();
            batteryStateChanged();
        }
        if (selfChassisState != null) {
            num = Integer.valueOf(selfChassisState.getBattery());
        }
        if (num.intValue() > MySpUtils.getInstance().getInt(SpConstant.SP_LOW_BATTERY_VALUE, 20)) {
            z = false;
        }
        this.mIsRobotIsLowPower = z;
    }

    /* access modifiers changed from: private */
    public final void chargingStateChanged() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m7chargingStateChanged$lambda11(NavigationHelper.this);
                    }
                });
            }
        }
        sendNavStateChangeBroadcast(20016);
    }

    /* access modifiers changed from: private */
    /* renamed from: chargingStateChanged$lambda-11  reason: not valid java name */
    public static final void m7chargingStateChanged$lambda11(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).senMsg(20016, String.valueOf(navigationHelper.mCurrentChargingState));
        }
    }

    /* access modifiers changed from: private */
    public final void sendNavStateChangeBroadcast(int i) {
        if (this.mNavIntent == null) {
            Intent intent = new Intent();
            this.mNavIntent = intent;
            if (intent != null) {
                intent.setAction(ACTION_BROADCAST_NAV_STATE_CHANGED);
            }
        }
        Intent intent2 = this.mNavIntent;
        if (intent2 != null) {
            intent2.putExtra("MSG_NAV_RECEIVER_EXTRA", i);
        }
        ContextUtil.getContext().sendBroadcast(this.mNavIntent);
    }

    private final void initWaterSdkListener() {
        WaterChassisHandlerCallback waterChassisHandlerCallback = new WaterChassisHandlerCallback();
        WaterNavigationHelper.Companion.getInstance().setWaterChassiHandlerCallback(waterChassisHandlerCallback);
        WaterChassisHelper.getInstance().setOnConnectListener(new OnConnectListener() {
            public final void onConnect(boolean z) {
                NavigationHelper.m9initWaterSdkListener$lambda13(NavigationHelper.this, z);
            }
        });
        waterChassisHandlerCallback.setNavigateListener(new NavigationHelper$initWaterSdkListener$2(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: initWaterSdkListener$lambda-13  reason: not valid java name */
    public static final void m9initWaterSdkListener$lambda13(NavigationHelper navigationHelper, boolean z) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        if (z && !navigationHelper.mIsNavigateConnected) {
            navigationHelper.queryAllMarkers();
            MyLogUtils.Logd(TAG, "WaterChassisHelper connected success");
        }
        if (!z && navigationHelper.mIsNavigateConnected) {
            LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
            if (linkedList != null) {
                Intrinsics.checkNotNull(linkedList);
                if (linkedList.size() > 0) {
                    LinkedList<OnNavigationListener> linkedList2 = navigationHelper.mOnNavigationListeners;
                    Intrinsics.checkNotNull(linkedList2);
                    Iterator it = linkedList2.iterator();
                    while (it.hasNext()) {
                        String string = ContextUtil.getContext().getString(R.string.navigation_navigation_disconnect2);
                        Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…n_navigation_disconnect2)");
                        ((OnNavigationListener) it.next()).updateErrorMessage(20012, string);
                    }
                }
            }
            MyLogUtils.Logd(TAG, "WaterChassisHelper disconnected");
        }
        navigationHelper.mIsNavigateConnected = z;
        LinkedList<OnNavigationListener> linkedList3 = navigationHelper.mOnNavigationListeners;
        if ((linkedList3 != null ? linkedList3.size() : 0) > 0) {
            LinkedList<OnNavigationListener> linkedList4 = navigationHelper.mOnNavigationListeners;
            Intrinsics.checkNotNull(linkedList4);
            Iterator it2 = linkedList4.iterator();
            while (it2.hasNext()) {
                ThreadUtils.runOnUiThread(new Runnable(z) {
                    public final /* synthetic */ boolean f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NavigationHelper.m10initWaterSdkListener$lambda13$lambda12(OnNavigationListener.this, this.f$1);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initWaterSdkListener$lambda-13$lambda-12  reason: not valid java name */
    public static final void m10initWaterSdkListener$lambda13$lambda12(OnNavigationListener onNavigationListener, boolean z) {
        Intrinsics.checkNotNullParameter(onNavigationListener, "$listener");
        onNavigationListener.connectResult(z);
    }

    /* access modifiers changed from: private */
    public final void hardStopStateChanged() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                LinkedList<OnNavigationListener> linkedList2 = this.mOnNavigationListeners;
                Intrinsics.checkNotNull(linkedList2);
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    ((OnNavigationListener) it.next()).senMsg(20018, String.valueOf(this.mCurrentHardStopState));
                }
            }
        }
        sendNavStateChangeBroadcast(20018);
    }

    /* access modifiers changed from: private */
    public final void softStopStateChanged() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m39softStopStateChanged$lambda14(NavigationHelper.this);
                    }
                });
            }
        }
        sendNavStateChangeBroadcast(20017);
    }

    /* access modifiers changed from: private */
    /* renamed from: softStopStateChanged$lambda-14  reason: not valid java name */
    public static final void m39softStopStateChanged$lambda14(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).senMsg(20017, String.valueOf(navigationHelper.mCurrentSoftStopState));
        }
    }

    /* access modifiers changed from: private */
    public final void batteryStateChanged() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    public final void run() {
                        NavigationHelper.m2batteryStateChanged$lambda15(NavigationHelper.this);
                    }
                });
            }
        }
        sendNavStateChangeBroadcast(20015);
    }

    /* access modifiers changed from: private */
    /* renamed from: batteryStateChanged$lambda-15  reason: not valid java name */
    public static final void m2batteryStateChanged$lambda15(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).senMsg(20015, String.valueOf(navigationHelper.mCurrentBattery));
        }
    }

    private final void checkIsChargingInLowPower(long j) {
        String str = TAG;
        Log.v(str, "checkIsChargingInLowPower:mIsRobotIsLowPower=" + this.mIsRobotIsLowPower + ",isNavigating=" + isNavigating() + ",mLastTagName=" + this.mLastTagName + ",mChargeTagName=" + this.mChargeTagName + ",WaterChassisStatus.getInstance().isCharging=" + WaterChassisStatus.getInstance().isCharging() + ",mCurrentHardStopState=" + this.mCurrentHardStopState + ",mCurrentSoftStopState=" + this.mCurrentSoftStopState + '}');
        if (!this.mIsNavigateConnected) {
            MyLogUtils.Loge(TAG, "Navigation is not connected ");
            return;
        }
        int i = this.mCurChassisType;
        if (i == 1) {
            if (isNavigating() && ((int) j) % 2 == 0) {
                String str2 = TAG;
                MyLogUtils.Logd(str2, "check SelfChassisState=" + SelfChassisState.getInstance());
            }
            if (SelfChassisState.getInstance().isRobotIsLowPower() && !isCharging() && !isInStopState()) {
                String str3 = TAG;
                MyLogUtils.Logd(str3, "checkIsChargingInLowPower is true:SelfChassisState=" + SelfChassisState.getInstance());
                autoCharge();
            }
        } else if (i == 2 && 5 != AppSpUtil.getInstance().getRobotRealType() && this.mIsRobotIsLowPower && !WaterChassisStatus.getInstance().isCharging() && !isInStopState()) {
            autoCharge();
        }
    }

    private final void startlowPowerCharge() {
        MyLogUtils.Logv(TAG, "startlowPowerCharge");
        Intent intent = new Intent();
        intent.setAction(ACTION_BROADCAST_NAV_STATE_CHANGED);
        intent.putExtra("MSG_NAV_RECEIVER_EXTRA", 20014);
        ContextUtil.getContext().sendBroadcast(intent);
    }

    public final void gotoReception() {
        if (TextUtils.isEmpty(mReceptionTagName)) {
            MyLogUtils.Loge(TAG, "gotoReception failed,mReceptionTagName is null");
            return;
        }
        String str = mReceptionTagName;
        Intrinsics.checkNotNull(str);
        setTargetPosition(str, false);
    }

    public final void autoCharge() {
        if (!AppSpUtil.getInstance().getIsOpenCharge()) {
            MyLogUtils.Loge(TAG, "gotoCharge failed,isOpenCharge is false");
        } else if (TextUtils.isEmpty(this.mChargeTagName)) {
            MyLogUtils.Loge(TAG, "gotoCharge failed,mChargeTagName is null");
        } else {
            String str = TAG;
            Log.v(str, "checkIsChargingInLowPower:mIsRobotIsLowPower=" + this.mIsRobotIsLowPower + ",isNavigating=" + isNavigating() + ",mLastTagName=" + this.mLastTagName + ",mChargeTagName=" + this.mChargeTagName + ",WaterChassisStatus.getInstance().isCharging=" + WaterChassisStatus.getInstance().isCharging());
            if (!this.mIsNavigateConnected) {
                MyLogUtils.Loge(TAG, "Navigation is not connected ");
                return;
            }
            String str2 = this.mChargeTagName;
            if (isCharging()) {
                MyLogUtils.Loge(TAG, "gotoCharge cancle,isCharging");
            } else if (!Intrinsics.areEqual((Object) this.mLastTagName, (Object) this.mChargeTagName) || !isNavigating()) {
                int i = this.mCurChassisType;
                if (i == 1) {
                    String str3 = TAG;
                    MyLogUtils.Logd(str3, "check SelfChassisState=" + SelfChassisState.getInstance());
                } else if (i == 2) {
                    String str4 = TAG;
                    MyLogUtils.Logd(str4, "check WaterChassisStatus=" + WaterChassisStatus.getInstance());
                }
                RobotTaskUtil.getInstance().release();
                BaseTask currentTask = RobotTaskUtil.getInstance().getCurrentTask();
                if (currentTask != null) {
                    currentTask.stopTask();
                }
                MyLogUtils.Logd(TAG, "===lowPowerGoCharge====");
                cancelNavigation();
                Object navigation = ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
                if (navigation != null) {
                    ((ISpeechManagerProvider) navigation).startSpeak(ContextUtil.getContext().getString(R.string.navigation_low_power_back_to_charging_pile));
                    showLowPowerBackChargingFragment();
                    Intrinsics.checkNotNull(str2);
                    setTargetPosition(str2, false);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider");
            } else {
                MyLogUtils.Loge(TAG, "gotoCharge cancle,is execute charging task");
            }
        }
    }

    private final void showLowPowerBackChargingFragment() {
        EventBus.getDefault().post(new MsgEvent(EventBusConstant.TAG_SHOW_LOW_POWER_BACK_CHARGING, ""));
    }

    public final void waitReconnect(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "tagName");
        String str2 = TAG;
        MyLogUtils.Logw(str2, "waitReconnect,tagName is " + str);
        this.mReSetTagetTask = Observable.interval(1000, 1000, TimeUnit.MILLISECONDS).subscribe(new Consumer(str, z) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void accept(Object obj) {
                NavigationHelper.m40waitReconnect$lambda16(NavigationHelper.this, this.f$1, this.f$2, (Long) obj);
            }
        });
        DisposeUtil.INSTANCE.addSubscription(this.mReSetTagetTask);
    }

    /* access modifiers changed from: private */
    /* renamed from: waitReconnect$lambda-16  reason: not valid java name */
    public static final void m40waitReconnect$lambda16(NavigationHelper navigationHelper, String str, boolean z, Long l) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(str, "$tagName");
        if (navigationHelper.isConnected()) {
            Disposable disposable = navigationHelper.mReSetTagetTask;
            if (disposable != null) {
                disposable.dispose();
            }
            String str2 = TAG;
            MyLogUtils.Logw(str2, "waitReconnect success,tagName is " + str);
            navigationHelper.navigatTag(str, z);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(l, "it");
        if (l.longValue() > 10) {
            String str3 = TAG;
            MyLogUtils.Logw(str3, "waitReconnect tasg canceld,tagName is " + str);
            Disposable disposable2 = navigationHelper.mReSetTagetTask;
            if (disposable2 != null) {
                disposable2.dispose();
            }
        }
    }

    static /* synthetic */ void navigatTag$default(NavigationHelper navigationHelper, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        navigationHelper.navigatTag(str, z);
    }

    private final void navigatTag(String str, boolean z) {
        int i;
        if (!isConnected()) {
            waitReconnect(str, z);
            return;
        }
        this.mIsPaused = false;
        if (RetrofitManager.Companion.getInstance().getRobotRealType() != 6 || MySpUtils.getInstance().getBoolean(SpConstant.SP_IS_POWER_ON_CHARGED, false)) {
            String str2 = TAG;
            MyLogUtils.Logd(str2, "setTargetPosition tagName=" + str + ",mChargeTagName=" + this.mChargeTagName + ",mCurrentChargingState=" + this.mCurrentChargingState);
            if (str.equals(this.mChargeTagName) && ((i = this.mCurrentChargingState) == 1 || i == 2)) {
                LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
                if (linkedList != null) {
                    Intrinsics.checkNotNull(linkedList);
                    if (linkedList.size() > 0) {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            public final void run() {
                                NavigationHelper.m20navigatTag$lambda18(NavigationHelper.this);
                            }
                        });
                    }
                }
                MyLogUtils.Logd(TAG, "setTargetPosition failed,is Charging");
            } else if (Intrinsics.areEqual((Object) this.mCurrentHardStopState, (Object) true) || Intrinsics.areEqual((Object) this.mCurrentSoftStopState, (Object) true)) {
                String str3 = TAG;
                MyLogUtils.Logd(str3, "setTargetPosition failed,mCurrentHardStopState=" + this.mCurrentHardStopState + ",mCurrentSoftStopState=" + this.mCurrentSoftStopState);
                LinkedList<OnNavigationListener> linkedList2 = this.mOnNavigationListeners;
                if (linkedList2 != null) {
                    Intrinsics.checkNotNull(linkedList2);
                    if (linkedList2.size() > 0) {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            public final void run() {
                                NavigationHelper.m21navigatTag$lambda19(NavigationHelper.this);
                            }
                        });
                    }
                }
            } else {
                if (TextUtils.isEmpty(str)) {
                    Context context = ContextUtil.getContext();
                    Intrinsics.checkNotNull(context);
                    String string = context.getString(R.string.navigation_navigation_failed);
                    Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…gation_navigation_failed)");
                    MyLogUtils.Logw(TAG, "NavigationHelper setTargetPosition,tagName is null");
                    LinkedList<OnNavigationListener> linkedList3 = this.mOnNavigationListeners;
                    if (linkedList3 != null) {
                        Intrinsics.checkNotNull(linkedList3);
                        if (linkedList3.size() > 0) {
                            ThreadUtils.runOnUiThread(new Runnable(string) {
                                public final /* synthetic */ String f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    NavigationHelper.m22navigatTag$lambda20(NavigationHelper.this, this.f$1);
                                }
                            });
                        }
                    }
                } else {
                    if (this.mIsNavigating) {
                        if (!Intrinsics.areEqual((Object) this.mLastTagName, (Object) str)) {
                            cancelNavigation();
                        } else {
                            return;
                        }
                    }
                    this.mLastTagName = str;
                    setNavigating(true);
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = str + System.currentTimeMillis();
                    this.mLastTagId = (String) objectRef.element;
                    INavigationHelper iNavigationHelper = this.mINavigationHelper;
                    if (iNavigationHelper != null) {
                        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                        booleanRef.element = iNavigationHelper.setTargetPosition((String) objectRef.element, str);
                        MyLogUtils.Logd("setTargetPosition： " + ((String) objectRef.element) + ' ' + str);
                        if (!booleanRef.element) {
                            String str4 = TAG;
                            MyLogUtils.Logd(str4, "setTargetPosition failed,tagName=" + str + ",sendMoveSuccess=" + booleanRef.element);
                            Disposable disposable = this.mReSetTagetTask;
                            if (disposable != null) {
                                disposable.dispose();
                            }
                            this.mReSetTagetTask = Observable.interval(1000, 1000, TimeUnit.MILLISECONDS).subscribe(new Consumer(objectRef, str, booleanRef, iNavigationHelper) {
                                public final /* synthetic */ Ref.ObjectRef f$1;
                                public final /* synthetic */ String f$2;
                                public final /* synthetic */ Ref.BooleanRef f$3;
                                public final /* synthetic */ INavigationHelper f$4;

                                {
                                    this.f$1 = r2;
                                    this.f$2 = r3;
                                    this.f$3 = r4;
                                    this.f$4 = r5;
                                }

                                public final void accept(Object obj) {
                                    NavigationHelper.m23navigatTag$lambda23$lambda22(NavigationHelper.this, this.f$1, this.f$2, this.f$3, this.f$4, (Long) obj);
                                }
                            });
                            DisposeUtil.INSTANCE.addSubscription(this.mReSetTagetTask);
                        }
                    }
                    if (z) {
                        LinkedList<OnNavigationListener> linkedList4 = this.mOnNavigationListeners;
                        if (linkedList4 != null) {
                            Intrinsics.checkNotNull(linkedList4);
                            if (linkedList4.size() > 0) {
                                ThreadUtils.runOnUiThread(new Runnable() {
                                    public final void run() {
                                        NavigationHelper.m25navigatTag$lambda24(NavigationHelper.this);
                                    }
                                });
                            }
                        }
                    } else if (this.mIsRobotIsLowPower) {
                        LinkedList<OnNavigationListener> linkedList5 = this.mOnNavigationListeners;
                        if (linkedList5 != null) {
                            Intrinsics.checkNotNull(linkedList5);
                            if (linkedList5.size() > 0) {
                                ThreadUtils.runOnUiThread(new Runnable() {
                                    public final void run() {
                                        NavigationHelper.m26navigatTag$lambda25(NavigationHelper.this);
                                    }
                                });
                            }
                        }
                    } else {
                        LinkedList<OnNavigationListener> linkedList6 = this.mOnNavigationListeners;
                        if (linkedList6 != null) {
                            Intrinsics.checkNotNull(linkedList6);
                            if (linkedList6.size() > 0) {
                                ThreadUtils.runOnUiThread(new Runnable() {
                                    public final void run() {
                                        NavigationHelper.m27navigatTag$lambda26(NavigationHelper.this);
                                    }
                                });
                            }
                        }
                    }
                    String str5 = TAG;
                    MyLogUtils.Logi(str5, "NavigationHelper setTargetPosition:" + ((String) objectRef.element));
                }
                this.mLastTagName = str;
            }
        } else {
            Context context2 = ContextUtil.getContext();
            Intrinsics.checkNotNull(context2);
            String string2 = context2.getString(R.string.navigation_power_on_not_charged);
            Intrinsics.checkNotNullExpressionValue(string2, "getContext()!!.getString…ion_power_on_not_charged)");
            MyLogUtils.Logw(TAG, "NavigationHelper navigatTag power_on_not_charged");
            EventPowerOnChargedBean eventPowerOnChargedBean = new EventPowerOnChargedBean();
            eventPowerOnChargedBean.setPowerOnCharged(false);
            EventBus.getDefault().post(eventPowerOnChargedBean);
            LinkedList<OnNavigationListener> linkedList7 = this.mOnNavigationListeners;
            if (linkedList7 != null) {
                Intrinsics.checkNotNull(linkedList7);
                if (linkedList7.size() > 0) {
                    ThreadUtils.runOnUiThread(new Runnable(string2) {
                        public final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            NavigationHelper.m19navigatTag$lambda17(NavigationHelper.this, this.f$1);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-17  reason: not valid java name */
    public static final void m19navigatTag$lambda17(NavigationHelper navigationHelper, String str) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(str, "$errorMessage");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).updateErrorMessage(20013, str);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-18  reason: not valid java name */
    public static final void m20navigatTag$lambda18(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Context context = ContextUtil.getContext();
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.navigation_move_error_charge);
            Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…gation_move_error_charge)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(false, 20009, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-19  reason: not valid java name */
    public static final void m21navigatTag$lambda19(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Context context = ContextUtil.getContext();
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.navigation_move_error_for_stop);
            Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…tion_move_error_for_stop)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(false, 20010, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-20  reason: not valid java name */
    public static final void m22navigatTag$lambda20(NavigationHelper navigationHelper, String str) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(str, "$errorMessage");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).updateErrorMessage(20008, str);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-23$lambda-22  reason: not valid java name */
    public static final void m23navigatTag$lambda23$lambda22(NavigationHelper navigationHelper, Ref.ObjectRef objectRef, String str, Ref.BooleanRef booleanRef, INavigationHelper iNavigationHelper, Long l) {
        Disposable disposable;
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        Intrinsics.checkNotNullParameter(objectRef, "$setTagetId");
        Intrinsics.checkNotNullParameter(str, "$tagName");
        Intrinsics.checkNotNullParameter(booleanRef, "$sendMoveSuccess");
        Intrinsics.checkNotNullParameter(iNavigationHelper, "$it");
        if (navigationHelper.isConnected()) {
            Intrinsics.checkNotNullExpressionValue(l, "count");
            if (l.longValue() < 10) {
                objectRef.element = str + System.currentTimeMillis();
                navigationHelper.mLastTagId = (String) objectRef.element;
                booleanRef.element = iNavigationHelper.setTargetPosition((String) objectRef.element, str);
                String str2 = TAG;
                MyLogUtils.Logw(str2, "ResetTargetPosition success,tagName is " + str + ",==sendMoveSuccess=" + booleanRef.element);
                if (booleanRef.element && (disposable = navigationHelper.mReSetTagetTask) != null) {
                    disposable.dispose();
                    return;
                }
                return;
            }
        }
        Intrinsics.checkNotNullExpressionValue(l, "count");
        if (l.longValue() > 10) {
            ViewUtils.runOnUiThread($$Lambda$NavigationHelper$0DIgPGjNrejRg1LpoZjK4hEwbRQ.INSTANCE);
            navigationHelper.setNavigating(false);
            String str3 = TAG;
            MyLogUtils.Logw(str3, "ResetTargetPosition tag canceld,tagName is " + str);
            Disposable disposable2 = navigationHelper.mReSetTagetTask;
            if (disposable2 != null) {
                disposable2.dispose();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-23$lambda-22$lambda-21  reason: not valid java name */
    public static final void m24navigatTag$lambda23$lambda22$lambda21() {
        ToastUtil.showText2(ContextUtil.getContext(), StringUtils.getString(R.string.navigation_navigation_failed));
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-24  reason: not valid java name */
    public static final void m25navigatTag$lambda24(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 200, "设置目标位置成功");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-25  reason: not valid java name */
    public static final void m26navigatTag$lambda25(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            String string = ContextUtil.getContext().getString(R.string.navigation_goto_charge_for_lower_power);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…o_charge_for_lower_power)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 20014, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: navigatTag$lambda-26  reason: not valid java name */
    public static final void m27navigatTag$lambda26(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 20006, "设置目标位置成功");
        }
    }

    public static /* synthetic */ void setTargetPosition$default(NavigationHelper navigationHelper, float f, float f2, float f3, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        navigationHelper.setTargetPosition(f, f2, f3, z);
    }

    public final void setTargetPosition(float f, float f2, float f3, boolean z) {
        boolean z2 = false;
        this.mIsPaused = false;
        String str = TAG;
        MyLogUtils.Logd(str, "setTargetPosition x=" + f + ",y=" + f2 + ",radians=" + f3 + ",mChargeTagName=" + this.mChargeTagName + ",mCurrentChargingState=" + this.mCurrentChargingState);
        int i = this.mCurrentChargingState;
        if (i == 1 || i == 2) {
            LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
            if (linkedList != null) {
                Intrinsics.checkNotNull(linkedList);
                if (linkedList.size() > 0) {
                    ThreadUtils.runOnUiThread(new Runnable() {
                        public final void run() {
                            NavigationHelper.m31setTargetPosition$lambda27(NavigationHelper.this);
                        }
                    });
                }
            }
            MyLogUtils.Logd(TAG, "setTargetPosition failed,is Charging");
        } else if (Intrinsics.areEqual((Object) this.mCurrentHardStopState, (Object) true) || Intrinsics.areEqual((Object) this.mCurrentSoftStopState, (Object) true)) {
            String str2 = TAG;
            MyLogUtils.Logd(str2, "setTargetPosition failed,mCurrentHardStopState=" + this.mCurrentHardStopState + ",mCurrentSoftStopState=" + this.mCurrentSoftStopState);
            LinkedList<OnNavigationListener> linkedList2 = this.mOnNavigationListeners;
            if (linkedList2 != null) {
                Intrinsics.checkNotNull(linkedList2);
                if (linkedList2.size() > 0) {
                    ThreadUtils.runOnUiThread(new Runnable() {
                        public final void run() {
                            NavigationHelper.m32setTargetPosition$lambda28(NavigationHelper.this);
                        }
                    });
                }
            }
        } else {
            INavigationHelper iNavigationHelper = this.mINavigationHelper;
            if (iNavigationHelper != null) {
                z2 = iNavigationHelper.setTargetPosition(f, f2, f3);
            }
            setNavigating(z2);
            if (!z2) {
                ViewUtils.runOnUiThread($$Lambda$NavigationHelper$8YIXVN0qmSugYXX4NH92DLH9nVA.INSTANCE);
            }
            if (z) {
                LinkedList<OnNavigationListener> linkedList3 = this.mOnNavigationListeners;
                if (linkedList3 != null) {
                    Intrinsics.checkNotNull(linkedList3);
                    if (linkedList3.size() > 0) {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            public final void run() {
                                NavigationHelper.m34setTargetPosition$lambda30(NavigationHelper.this);
                            }
                        });
                    }
                }
            } else if (this.mIsRobotIsLowPower) {
                LinkedList<OnNavigationListener> linkedList4 = this.mOnNavigationListeners;
                if (linkedList4 != null) {
                    Intrinsics.checkNotNull(linkedList4);
                    if (linkedList4.size() > 0) {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            public final void run() {
                                NavigationHelper.m35setTargetPosition$lambda31(NavigationHelper.this);
                            }
                        });
                    }
                }
            } else {
                LinkedList<OnNavigationListener> linkedList5 = this.mOnNavigationListeners;
                if (linkedList5 != null) {
                    Intrinsics.checkNotNull(linkedList5);
                    if (linkedList5.size() > 0) {
                        ThreadUtils.runOnUiThread(new Runnable() {
                            public final void run() {
                                NavigationHelper.m36setTargetPosition$lambda32(NavigationHelper.this);
                            }
                        });
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-27  reason: not valid java name */
    public static final void m31setTargetPosition$lambda27(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Context context = ContextUtil.getContext();
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.navigation_move_error_charge);
            Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…gation_move_error_charge)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(false, 20009, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-28  reason: not valid java name */
    public static final void m32setTargetPosition$lambda28(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Context context = ContextUtil.getContext();
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.navigation_move_error_for_stop);
            Intrinsics.checkNotNullExpressionValue(string, "getContext()!!.getString…tion_move_error_for_stop)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(false, 20010, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-29  reason: not valid java name */
    public static final void m33setTargetPosition$lambda29() {
        ToastUtil.showText2(ContextUtil.getContext(), StringUtils.getString(R.string.navigation_navigation_failed));
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-30  reason: not valid java name */
    public static final void m34setTargetPosition$lambda30(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 200, "设置目标位置成功");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-31  reason: not valid java name */
    public static final void m35setTargetPosition$lambda31(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            String string = ContextUtil.getContext().getString(R.string.navigation_goto_charge_for_lower_power);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…o_charge_for_lower_power)");
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 20014, string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setTargetPosition$lambda-32  reason: not valid java name */
    public static final void m36setTargetPosition$lambda32(NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        LinkedList<OnNavigationListener> linkedList = navigationHelper.mOnNavigationListeners;
        Intrinsics.checkNotNull(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((OnNavigationListener) it.next()).setTargetPositionResult(true, 20006, "设置目标位置成功");
        }
    }

    public final void setRobotIsLowPower(boolean z) {
        this.mIsRobotIsLowPower = z;
    }

    public final boolean isRobotIsLowPower() {
        return this.mIsRobotIsLowPower;
    }

    public final void setSoftStopState(boolean z) {
        this.mCurrentSoftStopState = Boolean.valueOf(z);
    }

    public final void setHardStopState(boolean z) {
        this.mCurrentHardStopState = Boolean.valueOf(z);
    }

    public final boolean isInStopState() {
        return Intrinsics.areEqual((Object) this.mCurrentHardStopState, (Object) true) || Intrinsics.areEqual((Object) this.mCurrentSoftStopState, (Object) true);
    }

    public final void setChargingState(int i) {
        this.mCurrentChargingState = i;
    }

    public final boolean isCharging() {
        int i = this.mCurrentChargingState;
        return i == 1 || i == 2;
    }

    public final void setNavigating(boolean z) {
        String str = TAG;
        MyLogUtils.Logd(str, "setIsNavigating=" + z);
        this.mIsNavigating = z;
    }

    public final String loadSpeakTxt(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        String loadSpeakTxt = NavigationManager.getInstance().loadSpeakTxt(str);
        Intrinsics.checkNotNullExpressionValue(loadSpeakTxt, "getInstance().loadSpeakTxt(key)");
        return loadSpeakTxt;
    }

    public final void updateSpeakTxt(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        NavigationManager.getInstance().updateSpeakTxt(str, str2);
    }

    public final void addOnNavigationListener(OnNavigationListener onNavigationListener) {
        LinkedList<OnNavigationListener> linkedList;
        if (this.mOnNavigationListeners == null) {
            this.mOnNavigationListeners = new LinkedList<>();
        }
        if (onNavigationListener != null && (linkedList = this.mOnNavigationListeners) != null) {
            linkedList.add(onNavigationListener);
        }
    }

    public final void removeOnNavigationListener(OnNavigationListener onNavigationListener) {
        LinkedList<OnNavigationListener> linkedList;
        if (this.mOnNavigationListeners == null) {
            this.mOnNavigationListeners = new LinkedList<>();
        }
        if (onNavigationListener != null && (linkedList = this.mOnNavigationListeners) != null) {
            linkedList.remove(onNavigationListener);
        }
    }

    public final void clearOnNavigationListener() {
        LinkedList<OnNavigationListener> linkedList = this.mOnNavigationListeners;
        if (linkedList != null) {
            linkedList.clear();
        }
    }

    public final void addTaskStateListener(OnTaskStateListener onTaskStateListener) {
        LinkedList<OnTaskStateListener> linkedList;
        if (this.mOnTaskListeners == null) {
            this.mOnTaskListeners = new LinkedList<>();
        }
        if (onTaskStateListener != null && (linkedList = this.mOnTaskListeners) != null) {
            linkedList.add(onTaskStateListener);
        }
    }

    public final void removeTaskStateListener(OnTaskStateListener onTaskStateListener) {
        Intrinsics.checkNotNullParameter(onTaskStateListener, "listener");
        if (this.mOnTaskListeners == null) {
            this.mOnTaskListeners = new LinkedList<>();
        }
        LinkedList<OnTaskStateListener> linkedList = this.mOnTaskListeners;
        if (linkedList != null) {
            linkedList.remove(onTaskStateListener);
        }
    }

    public final void clearTaskListeners() {
        LinkedList<OnTaskStateListener> linkedList = this.mOnTaskListeners;
        if (linkedList != null) {
            linkedList.clear();
        }
    }

    @Subscribe
    public final void hanldEvent(SrosEventMsg srosEventMsg) {
        INavigationHelper iNavigationHelper;
        if (srosEventMsg != null && Intrinsics.areEqual((Object) srosEventMsg.getMsgType(), (Object) "/api/map/list") && (iNavigationHelper = this.mINavigationHelper) != null) {
            iNavigationHelper.sendGetMapList();
        }
    }

    public final void setAllPoint(List<? extends QueryNavigationBeanR.NavigationPoint> list) {
        Intrinsics.checkNotNullParameter(list, "navigationPoints");
        String str = TAG;
        MyLogUtils.Logd(str, "NavigationHelper setAllWaterPoint:" + list);
        Handler threadHandler = getThreadHandler();
        if (threadHandler != null) {
            threadHandler.post(new Runnable(list, this) {
                public final /* synthetic */ List f$0;
                public final /* synthetic */ NavigationHelper f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    NavigationHelper.m30setAllPoint$lambda33(this.f$0, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setAllPoint$lambda-33  reason: not valid java name */
    public static final void m30setAllPoint$lambda33(List list, NavigationHelper navigationHelper) {
        Intrinsics.checkNotNullParameter(list, "$navigationPoints");
        Intrinsics.checkNotNullParameter(navigationHelper, "this$0");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            QueryNavigationBeanR.NavigationPoint navigationPoint = (QueryNavigationBeanR.NavigationPoint) it.next();
            PositionTag positionTag = new PositionTag();
            positionTag.setX((float) navigationPoint.getX1());
            positionTag.setY((float) navigationPoint.getY1());
            positionTag.setZ((int) navigationPoint.getZ1());
            positionTag.setType(navigationPoint.getType());
            positionTag.setName(navigationPoint.getPositionname());
            arrayList.add(positionTag.getName());
            arrayList2.add(positionTag);
            if (navigationPoint.getType() == 11) {
                navigationHelper.mChargeTagName = navigationPoint.getPositionname();
                navigationHelper.mChargeTagIndex = Integer.valueOf(i);
                String str = TAG;
                MyLogUtils.Logd(str, "NavigationHelper waterChassis getChargeName:" + navigationHelper.mChargeTagName);
            }
            i = i2;
        }
        navigationHelper.mAllTagList = arrayList2;
        if (!arrayList.isEmpty()) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("NavigationHelper waterChassis getAllPositionList:");
            List<? extends PositionTag> list2 = navigationHelper.mAllTagList;
            sb.append(list2 != null ? list2.toString() : null);
            MyLogUtils.Logd(str2, sb.toString());
            Object navigation = ARouter.getInstance().build(ARouterConstants.LIB_SPEECH).navigation();
            if (navigation != null) {
                ((ISpeechManagerProvider) navigation).addNavigateKeyWord("allTagName", arrayList);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.limpoxe.support.servicemanager.provider.speech.ISpeechManagerProvider");
        }
    }

    public final void queryAllMapList() {
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.sendGetMapList();
        }
    }

    public final PositionTag findTagByName(String str) {
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        if (this.mAllTagList == null) {
            return null;
        }
        List<? extends PositionTag> list = this.mAllTagList;
        Intrinsics.checkNotNull(list);
        for (PositionTag positionTag : list) {
            if (Intrinsics.areEqual((Object) positionTag.getName(), (Object) str)) {
                return positionTag;
            }
        }
        return null;
    }

    public final boolean isTagInPosition(String str, double d) {
        WaterStatusBean.ResultsBean results;
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        PositionTag findTagByName = findTagByName(str);
        WaterStatusBean.ResultsBean.CurrentPoseBean currentPoseBean = null;
        Double valueOf = findTagByName != null ? Double.valueOf((double) findTagByName.getX()) : null;
        Double valueOf2 = findTagByName != null ? Double.valueOf((double) findTagByName.getY()) : null;
        WaterStatusBean waterStatusBean = (WaterStatusBean) WaterStatusLiveData.Companion.get().getValue();
        if (!(waterStatusBean == null || (results = waterStatusBean.getResults()) == null)) {
            currentPoseBean = results.getCurrent_pose();
        }
        if (currentPoseBean == null) {
            return false;
        }
        double x = currentPoseBean.getX();
        double y = currentPoseBean.getY();
        if (valueOf == null || valueOf2 == null) {
            return false;
        }
        double distanceBetweenTwoPoints = NavigationUtils.distanceBetweenTwoPoints(valueOf.doubleValue(), valueOf2.doubleValue(), x, y);
        LogPlus.w("底盘信息", "激励----" + distanceBetweenTwoPoints + "----------" + findTagByName + "======" + currentPoseBean);
        if (distanceBetweenTwoPoints < d) {
            return true;
        }
        return false;
    }

    public final boolean isTagInPosition(String str) {
        Intrinsics.checkNotNullParameter(str, ServiceProvider.NAME);
        return isTagInPosition(str, 0.2d);
    }

    public final void addOnAllStatusListener(OnAllStatusListener onAllStatusListener) {
        Intrinsics.checkNotNullParameter(onAllStatusListener, "listener");
        this.mOnAllStatusListeners.add(onAllStatusListener);
    }

    public final void removeOnAllStatusListener(OnAllStatusListener onAllStatusListener) {
        Intrinsics.checkNotNullParameter(onAllStatusListener, "listener");
        this.mOnAllStatusListeners.remove(onAllStatusListener);
    }

    public final void setOnMarkerListListener(OnMarkerListListener onMarkerListListener) {
        Intrinsics.checkNotNullParameter(onMarkerListListener, "listener");
        this.mOnMarkerListListener = onMarkerListListener;
    }

    public final Handler getThreadHandler() {
        if (this.wHandler == null) {
            HandlerThread handlerThread = new HandlerThread("navigate-load");
            handlerThread.start();
            this.wHandler = new Handler(handlerThread.getLooper());
        }
        return this.wHandler;
    }

    public final void release() {
        cancelNavigation();
        DisposeUtil.INSTANCE.onUnsubscribe();
        RobotTaskUtil.getInstance().release();
        INavigationHelper iNavigationHelper = this.mINavigationHelper;
        if (iNavigationHelper != null) {
            iNavigationHelper.release();
        }
        this.mIsNavigateConnected = false;
        MyLogUtils.Logd(TAG, "NavigationHelper release");
        clearOnNavigationListener();
        clearTaskListeners();
        Disposable disposable = this.mDisable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mINavigationHelper = null;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ciot/navigation/navigation/NavigationHelper$NevigationHelperHolder;", "", "()V", "holder", "Lcom/ciot/navigation/navigation/NavigationHelper;", "getHolder", "()Lcom/ciot/navigation/navigation/NavigationHelper;", "setHolder", "(Lcom/ciot/navigation/navigation/NavigationHelper;)V", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NavigationHelper.kt */
    private static final class NevigationHelperHolder {
        public static final NevigationHelperHolder INSTANCE = new NevigationHelperHolder();
        private static NavigationHelper holder = new NavigationHelper((DefaultConstructorMarker) null);

        private NevigationHelperHolder() {
        }

        public final NavigationHelper getHolder() {
            return holder;
        }

        public final void setHolder(NavigationHelper navigationHelper) {
            Intrinsics.checkNotNullParameter(navigationHelper, "<set-?>");
            holder = navigationHelper;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/ciot/navigation/navigation/NavigationHelper$Companion;", "", "()V", "ACTION_BROADCAST_NAV_STATE_CHANGED", "", "DEFAULT_MONITOR_URL", "DEFAULT_RECEPTION_TAG", "NAVIGATION_TAG", "TAG", "instance", "Lcom/ciot/navigation/navigation/NavigationHelper;", "getInstance", "()Lcom/ciot/navigation/navigation/NavigationHelper;", "mReceptionTagName", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: NavigationHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavigationHelper getInstance() {
            return NevigationHelperHolder.INSTANCE.getHolder();
        }
    }
}
