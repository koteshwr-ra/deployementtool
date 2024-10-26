package com.ciot.networklib;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.ZipUtils;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.constant.SpConstant;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.LogPlus;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.MyStringUtils;
import com.ciot.diagnosis.DiagnosisManager;
import com.ciot.diagnosis.diagnosis.MonitorDiagnosis;
import com.ciot.diagnosis.diagnosis.NetworkDiagnosis;
import com.ciot.networklib.api.Api;
import com.ciot.networklib.api.DeviceIdRegisterService;
import com.ciot.networklib.api.DiagnosisApiService;
import com.ciot.networklib.api.DownloadService;
import com.ciot.networklib.api.QiWuApiService;
import com.ciot.networklib.api.SemanticIntentApiService;
import com.ciot.networklib.api.UploadImgApiService;
import com.ciot.networklib.api.WuhanApiService;
import com.ciot.networklib.api.YunJiAudioService;
import com.ciot.networklib.api.YunJiOpenApiService;
import com.ciot.networklib.bean.AuthorizationCodeBean;
import com.ciot.networklib.bean.AuthorizationCodeTimerTaskBean;
import com.ciot.networklib.bean.Function;
import com.ciot.networklib.bean.HomeCard;
import com.ciot.networklib.bean.ProjectResponse;
import com.ciot.networklib.bean.hotel.FloorBean;
import com.ciot.networklib.bean.hotel.RoomBean;
import com.ciot.networklib.bean.log.CreateRobotLogBean;
import com.ciot.networklib.bean.log.CreateRobotLogResponse;
import com.ciot.networklib.bean.patrol.PatrolActionBean;
import com.ciot.networklib.bean.patrol.PatrolPathBean;
import com.ciot.networklib.bean.patrol.PatrolPointBean;
import com.ciot.networklib.bean.patrol.PatrolRecordBean;
import com.ciot.networklib.bean.patrol.PatrolReportBean;
import com.ciot.networklib.bean.patrol.PatrolRouteBean;
import com.ciot.networklib.bean.tactics.CustomTactics;
import com.ciot.networklib.bean.tactics.DefaultTactics;
import com.ciot.networklib.bean.version.VersionBean;
import com.ciot.networklib.bean.version.record.UpdateVersionBean;
import com.ciot.networklib.bean.version.record.UpdateVersionRecordResponse;
import com.ciot.networklib.callback.GetVideoCodeCallback;
import com.ciot.networklib.callback.OnGetTimerReceptionTaskCallback;
import com.ciot.networklib.function.BackgroundLiveData;
import com.ciot.networklib.function.DistantlyAttractsLiveData;
import com.ciot.networklib.function.HomeCardsLiveData;
import com.ciot.networklib.function.NearbyAttractsLiveData;
import com.ciot.networklib.function.RetryWithDelay;
import com.ciot.networklib.function.WelcomeLiveData;
import com.ciot.networklib.interceptor.HttpLoggingInterceptor;
import com.ciot.networklib.sim.SimHelper;
import com.ciot.networklib.util.YunJiSignUtils;
import com.ciot.realm.db.Tactics;
import com.ciot.realm.db.Task;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.RecordResponse;
import com.ciot.realm.db.patrol.PathBean;
import com.ciot.realm.db.patrol.PatrolTaskBean;
import com.ciot.realm.db.timer.TimerReceptionTaskBean;
import com.ciot.realm.util.RealmHelper;
import com.ciot.realm.util.SentryRealmHelper;
import com.example.sroslibrary.SrosManager;
import com.example.sroslibrary.TcpService;
import com.example.sroslibrary.bean.NotificationBean;
import com.example.sroslibrary.bean.RobotStatusResponse;
import com.example.sroslibrary.contents.WaterChassisConstants;
import com.example.sroslibrary.livedata.RobotStatusLiveData;
import com.example.sroslibrary.sros.SrosSendMsgUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import java.io.File;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import xcrash.TombstoneParser;

@Metadata(d1 = {"\u0000ä\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 Á\u00022\u00020\u0001:\u0004Á\u0002Â\u0002B\u0005¢\u0006\u0002\u0010\u0002J\u0007\u0010\u0001\u001a\u00020DJ\u0012\u0010\u0001\u001a\u00020D2\t\u0010\u0001\u001a\u0004\u0018\u000103J\t\u0010\u0001\u001a\u00020DH\u0002J\t\u0010\u0001\u001a\u00020DH\u0002J\t\u0010\u0001\u001a\u00020DH\u0002J\u0013\u0010\u0001\u001a\u00020D2\b\u0010\u0001\u001a\u00030\u0001H\u0002J9\u0010\u0001\u001a\u00020D2\u0007\u0010\u0001\u001a\u00020\u00062'\u0010\u0001\u001a\"\u0012\u0017\u0012\u0015\u0018\u00010\u0001¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020D0\u0001J\u0017\u0010\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010|H\u0002J.\u0010\u0001\u001a\u00020D2\t\u0010\u0001\u001a\u0004\u0018\u00010\u00062\t\u0010\u0001\u001a\u0004\u0018\u00010\u00062\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010\u0001J\t\u0010\u0001\u001a\u0004\u0018\u00010bJ\t\u0010\u0001\u001a\u0004\u0018\u00010\u0006J\u001b\u0010\u0001\u001a\u00020D2\u0007\u0010\u0001\u001a\u00020\u00062\u0007\u0010\u0001\u001a\u00020\u0006H\u0002J\u0014\u0010\u0001\u001a\u0004\u0018\u00010\u00062\t\u0010\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010\u0001\u001a\u0004\u0018\u00010 J\t\u0010\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010\u0001\u001a\u00020\u0006J\u0007\u0010 \u0001\u001a\u00020%J\t\u0010¡\u0001\u001a\u0004\u0018\u00010'J\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010£\u0001\u001a\u00020DH\u0007J\u0007\u0010¤\u0001\u001a\u00020\u0006J\u0007\u0010¥\u0001\u001a\u00020*J\u0007\u0010¦\u0001\u001a\u00020DJ+\u0010§\u0001\u001a\u00020D2\u000f\u0010¨\u0001\u001a\n\u0012\u0005\u0012\u00030ª\u00010©\u00012\u000f\u0010«\u0001\u001a\n\u0012\u0005\u0012\u00030¬\u00010©\u0001H\u0007J\t\u0010­\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010®\u0001\u001a\u00020\u0017J\u0010\u0010¯\u0001\u001a\u00020D2\u0007\u0010°\u0001\u001a\u00020\u0004J\u0019\u0010¯\u0001\u001a\u00020D2\u0007\u0010±\u0001\u001a\u00020\u00062\u0007\u0010\u0001\u001a\u00020\u0006J\t\u0010²\u0001\u001a\u00020DH\u0007J\t\u0010³\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010´\u0001\u001a\u00020DJ\u0010\u0010µ\u0001\u001a\u00020D2\u0007\u0010\u0001\u001a\u00020\u0006J\n\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u0013\u0010¶\u0001\u001a\u00030·\u00012\u0007\u0010¸\u0001\u001a\u00020\u0006H\u0002J\t\u0010¹\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010º\u0001\u001a\u0004\u0018\u00010PJ\t\u0010»\u0001\u001a\u0004\u0018\u00010\u0006J(\u0010¼\u0001\u001a#\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010|j\u0011\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`½\u0001J\t\u0010¾\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010¿\u0001\u001a\u00020DH\u0002J\t\u0010À\u0001\u001a\u00020DH\u0003J\t\u0010Á\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010Â\u0001\u001a\u00020\bJ4\u0010Ã\u0001\u001a\u00020D2\u000f\u0010¨\u0001\u001a\n\u0012\u0005\u0012\u00030Ä\u00010©\u00012\u000f\u0010«\u0001\u001a\n\u0012\u0005\u0012\u00030¬\u00010©\u00012\u0007\u0010Å\u0001\u001a\u00020\u0006H\u0007J\t\u0010Æ\u0001\u001a\u00020\u0006H\u0002J\u0007\u0010Ç\u0001\u001a\u00020 J\t\u0010È\u0001\u001a\u00020\u0006H\u0002J\u0007\u0010É\u0001\u001a\u00020\bJ!\u0010Ê\u0001\u001a\u00020D2\u0007\u0010Ë\u0001\u001a\u00020\u00062\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030Ì\u00010\u0001J\u001a\u0010Í\u0001\u001a\u00020D2\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030Ì\u00010\u0001H\u0002J\u0007\u0010Î\u0001\u001a\u00020DJ\t\u0010Ï\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010Ð\u0001\u001a\u00020_J\u0010\u0010Ð\u0001\u001a\u00020_2\u0007\u0010Ñ\u0001\u001a\u00020\u0006J\u0013\u0010Ò\u0001\u001a\u00030Ó\u00012\u0007\u0010Ô\u0001\u001a\u00020\u0017H\u0002J\"\u0010Õ\u0001\u001a\u00020D2\t\u0010\u0001\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u0001J\t\u0010Ö\u0001\u001a\u0004\u0018\u00010qJ\t\u0010×\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010Ø\u0001\u001a\u0004\u0018\u00010\u0006J\t\u0010Ù\u0001\u001a\u0004\u0018\u00010\u0006J\u0007\u0010Ú\u0001\u001a\u00020uJ\u0007\u0010Û\u0001\u001a\u00020sJ\u0007\u0010Ü\u0001\u001a\u00020DJ\t\u0010Ý\u0001\u001a\u00020DH\u0002J\t\u0010Þ\u0001\u001a\u00020DH\u0002J\"\u0010ß\u0001\u001a\u00020D2\u0007\u0010à\u0001\u001a\u00020\u00062\u0007\u0010á\u0001\u001a\u00020\b2\u0007\u0010±\u0001\u001a\u00020\u0006J\u0007\u0010â\u0001\u001a\u00020\u0017J\u0007\u0010ã\u0001\u001a\u00020\u0017J\u0007\u0010ä\u0001\u001a\u00020\u0017J\t\u0010å\u0001\u001a\u00020\u0017H\u0002J\u0007\u0010æ\u0001\u001a\u00020\u0017J\u0007\u0010ç\u0001\u001a\u00020\u0017J\u0012\u0010è\u0001\u001a\u00020D2\u0007\u0010\u0001\u001a\u00020\u0006H\u0002J\u0007\u0010é\u0001\u001a\u00020DJ\"\u0010ê\u0001\u001a\u00020\u00062\u0007\u0010ë\u0001\u001a\u00020\u00062\u0007\u0010ì\u0001\u001a\u00020\u00062\u0007\u0010í\u0001\u001a\u00020\u0006J\u001a\u0010î\u0001\u001a\u00020D2\u000f\u0010ï\u0001\u001a\n\u0012\u0005\u0012\u00030ñ\u00010ð\u0001H\u0002J\u0013\u0010ò\u0001\u001a\u00020\u00172\b\u0010ó\u0001\u001a\u00030Ì\u0001H\u0002J\u0013\u0010ô\u0001\u001a\u00020D2\b\u0010õ\u0001\u001a\u00030Ì\u0001H\u0002J.\u0010ö\u0001\u001a\u00020D2\t\u0010à\u0001\u001a\u0004\u0018\u00010\u00062\t\u0010Ë\u0001\u001a\u0004\u0018\u00010\u00062\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030Ì\u00010\u0001JW\u0010÷\u0001\u001a\u00020D2\u0007\u0010ø\u0001\u001a\u00020\u00062\u0007\u0010ù\u0001\u001a\u00020\u00062\u0007\u0010ú\u0001\u001a\u00020\u00062\u0007\u0010û\u0001\u001a\u00020\u00062\u0007\u0010ü\u0001\u001a\u00020\u00062\u000e\u0010ý\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060©\u00012\u000f\u0010«\u0001\u001a\n\u0012\u0005\u0012\u00030¬\u00010©\u0001H\u0007JQ\u0010þ\u0001\u001a\u00020D2\u0007\u0010ø\u0001\u001a\u00020\u00062\t\u0010ÿ\u0001\u001a\u0004\u0018\u00010\u00062\u0007\u0010ù\u0001\u001a\u00020\u00062\u0007\u0010ú\u0001\u001a\u00020\u00062\u0007\u0010û\u0001\u001a\u00020\u00062\u0007\u0010\u0002\u001a\u00020\u00062\u0007\u0010ü\u0001\u001a\u00020\u00062\u0007\u0010\u0002\u001a\u00020\u0006J\u0007\u0010\u0002\u001a\u00020DJ\u001b\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u00172\u0007\u0010\u0002\u001a\u00020\u0006H\u0002J\u0007\u0010\u0002\u001a\u00020DJ!\u0010\u0002\u001a\u00020D2\u0007\u0010Ñ\u0001\u001a\u00020\u00062\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030Ì\u00010\u0002J\u001c\u0010\u0002\u001a\u00020D2\u0011\u0010\u0002\u001a\f\u0012\u0005\u0012\u00030\u0002\u0018\u00010ð\u0001H\u0002J\u0014\u0010\u0002\u001a\u00020D2\t\u0010\u0002\u001a\u0004\u0018\u00010\u0006H\u0002J\u0014\u0010\u0002\u001a\u00020D2\t\u0010\u0002\u001a\u0004\u0018\u00010\u0006H\u0002J\u001b\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\b2\u0007\u0010\u0002\u001a\u00020\u0006H\u0002J\u001b\u0010\u0002\u001a\u00020D2\u0010\u0010\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0002H\u0002J\u0007\u0010\u0002\u001a\u00020DJ\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u0006J\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u0006J\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u0006J\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u0006J\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010\u0002\u001a\u00020\u0017J\u0010\u0010\u0002\u001a\u00020D2\u0007\u0010æ\u0001\u001a\u00020\u0017J0\u0010 \u0002\u001a\u00020D2'\u0010¡\u0002\u001a\"\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020D\u0018\u00010=J\u0018\u0010¢\u0002\u001a\u00020D2\u000f\u0010¡\u0002\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010RJ\u0010\u0010£\u0002\u001a\u00020D2\u0007\u0010¤\u0002\u001a\u00020\u0006J\u0010\u0010¥\u0002\u001a\u00020D2\u0007\u0010Ñ\u0001\u001a\u00020\u0006J\u0010\u0010¦\u0002\u001a\u00020D2\u0007\u0010à\u0001\u001a\u00020\u0006J\u0010\u0010§\u0002\u001a\u00020D2\u0007\u0010±\u0001\u001a\u00020\u0006J\u0010\u0010¨\u0002\u001a\u00020D2\u0007\u0010©\u0002\u001a\u00020\bJ\u0010\u0010ª\u0002\u001a\u00020D2\u0007\u0010«\u0002\u001a\u00020\u0006J\u0010\u0010¬\u0002\u001a\u00020D2\u0007\u0010\u0001\u001a\u00020\u0006J\t\u0010­\u0002\u001a\u00020DH\u0002J\u0012\u0010®\u0002\u001a\u00020D2\u0007\u0010Ñ\u0001\u001a\u00020\u0006H\u0002J\u0012\u0010¯\u0002\u001a\u00020D2\t\u0010°\u0002\u001a\u0004\u0018\u00010\u0006J\u0012\u0010±\u0002\u001a\u00020D2\t\u0010²\u0002\u001a\u0004\u0018\u00010\u0006J\t\u0010³\u0002\u001a\u00020DH\u0002J\u0007\u0010´\u0002\u001a\u00020DJ\"\u0010µ\u0002\u001a\u00020D2\b\u0010¶\u0002\u001a\u00030·\u00022\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030¸\u00020\u0001J\t\u0010¹\u0002\u001a\u00020DH\u0002J\u0011\u0010º\u0002\u001a\u00020D2\b\u0010»\u0002\u001a\u00030¼\u0002J\t\u0010½\u0002\u001a\u00020DH\u0002J9\u0010¾\u0002\u001a\u00020D2\u0007\u0010¿\u0002\u001a\u00020\u00062'\u0010\u0001\u001a\"\u0012\u0017\u0012\u0015\u0018\u00010À\u0002¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020D0\u0001R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u00102\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000Rj\u0010<\u001a^\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(B\u0012\u0013\u0012\u00110\b¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020D\u0018\u00010=X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010E\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001b\"\u0004\bG\u0010\u001dR\u001c\u0010H\u001a\u0004\u0018\u00010IX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u0010\u0010N\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010Q\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010RX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u000103X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00060]X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u0004\u0018\u00010_X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010`\u001a\u0004\u0018\u00010_X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010a\u001a\u0004\u0018\u00010bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001c\u0010g\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u001b\"\u0004\bi\u0010\u001dR\u0010\u0010j\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010k\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u00105\"\u0004\bm\u00107R\u0010\u0010n\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010o\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010p\u001a\u0004\u0018\u00010qX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010r\u001a\u0004\u0018\u00010sX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010t\u001a\u0004\u0018\u00010uX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010v\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u001c\u0010{\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010|X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010}\u001a\u0004\u0018\u00010~X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006Ã\u0002"}, d2 = {"Lcom/ciot/networklib/RetrofitManager;", "", "()V", "SERVER_TIMEOUT", "", "TAG", "", "currentRegetVersionTimes", "", "getCurrentRegetVersionTimes", "()I", "setCurrentRegetVersionTimes", "(I)V", "getVideoCodeCallback", "Lcom/ciot/networklib/callback/GetVideoCodeCallback;", "getGetVideoCodeCallback", "()Lcom/ciot/networklib/callback/GetVideoCodeCallback;", "setGetVideoCodeCallback", "(Lcom/ciot/networklib/callback/GetVideoCodeCallback;)V", "initState", "getInitState", "setInitState", "isNeedRetry", "", "mArea", "mAuthorizationCode", "getMAuthorizationCode", "()Ljava/lang/String;", "setMAuthorizationCode", "(Ljava/lang/String;)V", "mCCID", "mCiotSemanticIntentApiService", "Lcom/ciot/networklib/api/SemanticIntentApiService;", "mCompositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "mCurTopicUrl", "mDeviceIdRegisterService", "Lcom/ciot/networklib/api/DeviceIdRegisterService;", "mDiagnosisApiService", "Lcom/ciot/networklib/api/DiagnosisApiService;", "mDownBaseUrl", "mDownloadService", "Lcom/ciot/networklib/api/DownloadService;", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "mIMSI", "mInterValDisposable", "Lio/reactivex/disposables/Disposable;", "getMInterValDisposable", "()Lio/reactivex/disposables/Disposable;", "setMInterValDisposable", "(Lio/reactivex/disposables/Disposable;)V", "mIsBusinessMode", "mIsGetRobotInfoSuccess", "mIsRealtimeUpload", "mIsTcpOnline", "mLoginListener", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "isLoginSuccess", "isGetRobotInfoSuccess", "isGetProjectInfoSuccess", "msgType", "", "mNewApkFilePath", "getMNewApkFilePath", "setMNewApkFilePath", "mOnGetTimerReceptionTaskCallback", "Lcom/ciot/networklib/callback/OnGetTimerReceptionTaskCallback;", "getMOnGetTimerReceptionTaskCallback", "()Lcom/ciot/networklib/callback/OnGetTimerReceptionTaskCallback;", "setMOnGetTimerReceptionTaskCallback", "(Lcom/ciot/networklib/callback/OnGetTimerReceptionTaskCallback;)V", "mProject", "mQiWuApiService", "Lcom/ciot/networklib/api/QiWuApiService;", "mRefreshAdListener", "Lkotlin/Function0;", "mRobotAllowDisable", "mRobotId", "mRobotNum", "mRobotRealType", "mSemanticIntentApiService", "mSemanticIntentBaseUrl", "mSemanticIntentType", "mStartTcpServiceIntent", "Landroid/content/Intent;", "mToken", "Ljava/util/concurrent/atomic/AtomicReference;", "mUploadImgApiService1", "Lcom/ciot/networklib/api/UploadImgApiService;", "mUploadImgApiService2", "mVersionBean", "Lcom/ciot/networklib/bean/version/VersionBean;", "getMVersionBean", "()Lcom/ciot/networklib/bean/version/VersionBean;", "setMVersionBean", "(Lcom/ciot/networklib/bean/version/VersionBean;)V", "mVideoCode", "getMVideoCode", "setMVideoCode", "mWuHanBaseUrl", "mWuHanInitDisposable", "getMWuHanInitDisposable", "setMWuHanInitDisposable", "mWuHanPassWord", "mWuHanUserName", "mWuhanApiService", "Lcom/ciot/networklib/api/WuhanApiService;", "mYunJiAudioService", "Lcom/ciot/networklib/api/YunJiAudioService;", "mYunJiOpenApiService", "Lcom/ciot/networklib/api/YunJiOpenApiService;", "openVoiceCallFunction", "getOpenVoiceCallFunction", "()Z", "setOpenVoiceCallFunction", "(Z)V", "requestMap", "Ljava/util/HashMap;", "retrofit", "Lretrofit2/Retrofit;", "retryCount", "addDefaultApkCallBack", "addSubscription", "disposable", "bindSimInfo", "deleteLocalVideoCode", "deleteLocalVoiceCode", "downloadYunJiMap", "mapData", "Lcom/example/sroslibrary/bean/NotificationBean$DescBean$MapDataBean;", "findCompanyById", "companyId", "callback", "Lkotlin/Function1;", "Lcom/ciot/realm/db/common/CompanyResponse;", "bean", "generateRequestMap", "getAdLists", "id", "token", "observer", "Lcom/ciot/networklib/BaseObserver;", "Lcom/google/gson/JsonArray;", "getApkNewVersion", "getArea", "getAuthorizationCode", "wuHanUserName", "getBaseUrl", "url", "getCCID", "getCiotSemanticIntentApiService", "getCiotSemanticIntentLogTransmissionUrl", "getDeviceIdRegisterBaseUrl", "getDeviceIdRegisterService", "getDiagnosisApiService", "getDiagnosisUploadUrl", "getDistantlyServices", "getDownBaseUrl", "getDownloadService", "getHotelActivity", "getHotelFloor", "success", "Lio/reactivex/functions/Consumer;", "Lcom/ciot/networklib/bean/hotel/FloorBean;", "err", "", "getIMSI", "getIsBusinessMode", "getLatestVersion", "delayTime", "robotNum", "getNearbyServices", "getNewApkFilePath", "getNewsList", "getNewsSpec", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "tag", "getProject", "getQiWuApiService", "getQiWuBaseUrl", "getRequestMap", "Lkotlin/collections/HashMap;", "getRobotId", "getRobotInfo", "getRobotIsLock", "getRobotNum", "getRobotRealType", "getRoomData", "Lcom/ciot/networklib/bean/hotel/RoomBean;", "floorId", "getSemanticBaseUrl", "getSemanticIntentApiService", "getSemanticIntentBaseUrl", "getSemanticIntentType", "getSimDetail", "iccid", "Lokhttp3/ResponseBody;", "getSimInfo", "getTactics", "getToken", "getUploadImgApiService", "baseUrl", "getUserRequestBody", "Lokhttp3/RequestBody;", "isGetUserAndPwm", "getVersionById", "getWuHanApiService", "getWuHanBaseUrl", "getWuHanPassWord", "getWuHanUserName", "getYunJiOpenApiService", "getYunjiAudioService", "init", "initGatekeeperRobot", "initRobot", "initRobotConfig", "robotId", "robotType", "isIpIllegal", "isNetInitSuccess", "isRealTimeUpload", "isShouldGetVideoCode", "isTcpOnline", "judgePproduction", "loadPatrolTaskFromServer", "onUnsubscribe", "openTurnstile", "propertyUrl", "accessToken", "openTurnstileBeanStr", "parseFunctionList", "functionList", "", "Lcom/ciot/networklib/bean/Function;", "parseLoginResponseBody", "loginResponseBody", "parseTcpIp", "getIpResponseBody", "postRobotInfo", "registerHotel", "registType", "roomId", "roomName", "phone", "remark", "sucess", "registerHotelActivity", "registTypeId", "peopleNum", "registerTypeName", "release", "replySrosNotification", "result", "errorMsg", "resetData", "robotAllow", "Lio/reactivex/Observer;", "saveTimerTask2Db", "timedTasks", "Lcom/ciot/networklib/bean/AuthorizationCodeTimerTaskBean;", "saveVideoCode2Local", "msgContent", "saveVoiceCode2Local", "sendNetErrorMsg", "errorType", "setAccountListener", "e", "Lio/reactivex/ObservableEmitter;", "setAdData", "setArea", "area", "setCCID", "ccid", "setDownBaseUrl", "downBaseUrl", "setIMSI", "imsi", "setIsBusinessMode", "IsBusinessMode", "setIsTcpOnline", "setLoginListener", "listener", "setOnRefreshAdListener", "setProject", "project", "setPropertyDomain", "setRobotId", "setRobotNum", "setRobotRealType", "robotRealType", "setSemanticIntentBaseUrl", "semanticUrl", "setToken", "setVideoCodeError", "setWuHanBaseUrl", "setWuHanPassWord", "passWord", "setWuHanUserName", "userName", "startTcpService", "stopTcpService", "updateVersionRecord", "updateVersionBean", "Lcom/ciot/networklib/bean/version/record/UpdateVersionBean;", "Lcom/ciot/networklib/bean/version/record/UpdateVersionRecordResponse;", "uploadLogFile", "uploadTaskLogcat", "mTask", "Lcom/ciot/realm/db/Task;", "uploadYuJiMap", "verifyInviteCode", "code", "Lcom/ciot/realm/db/common/RecordResponse;", "Companion", "RetrofitHelperHolder", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RetrofitManager.kt */
public final class RetrofitManager {
    public static final String CLOCK_IN_HELPER_TAG = "ClockInHelper JIN_YU";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int SEMANTIC_INTENT_TYPE_SELF_STUDY = 2;
    public static final int SEMANTIC_INTENT_TYPE_UNKNOWN = -1;
    public static final int SEMANTIC_INTENT_TYPE_YUNJI = 1;
    public static final int SEMANTIC_INTENT_TYPE_YUNJI_DEV = 1;
    public static final int SEMANTIC_INTENT_TYPE_YUNJI_PRODUCT = 0;
    public static final int SEMANTIC_INTENT_TYPE_YUNJI_TEST = 3;
    private final long SERVER_TIMEOUT = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    /* access modifiers changed from: private */
    public final String TAG = "NETWORK_TAG";
    private int currentRegetVersionTimes;
    private GetVideoCodeCallback getVideoCodeCallback;
    private int initState = -1;
    /* access modifiers changed from: private */
    public boolean isNeedRetry = true;
    private volatile String mArea;
    private String mAuthorizationCode;
    private volatile String mCCID;
    private SemanticIntentApiService mCiotSemanticIntentApiService;
    /* access modifiers changed from: private */
    public CompositeDisposable mCompositeDisposable;
    private String mCurTopicUrl;
    private DeviceIdRegisterService mDeviceIdRegisterService;
    private DiagnosisApiService mDiagnosisApiService;
    private String mDownBaseUrl;
    private DownloadService mDownloadService;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private volatile String mIMSI;
    private Disposable mInterValDisposable;
    private boolean mIsBusinessMode;
    private boolean mIsGetRobotInfoSuccess;
    private boolean mIsRealtimeUpload;
    private volatile boolean mIsTcpOnline;
    /* access modifiers changed from: private */
    public Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> mLoginListener;
    private String mNewApkFilePath;
    private OnGetTimerReceptionTaskCallback mOnGetTimerReceptionTaskCallback;
    private volatile String mProject;
    private QiWuApiService mQiWuApiService;
    private Function0<Unit> mRefreshAdListener;
    /* access modifiers changed from: private */
    public Disposable mRobotAllowDisable;
    private volatile String mRobotId;
    private String mRobotNum;
    /* access modifiers changed from: private */
    public int mRobotRealType = -1;
    private SemanticIntentApiService mSemanticIntentApiService;
    private String mSemanticIntentBaseUrl;
    /* access modifiers changed from: private */
    public volatile int mSemanticIntentType;
    private Intent mStartTcpServiceIntent;
    private AtomicReference<String> mToken = new AtomicReference<>();
    private UploadImgApiService mUploadImgApiService1;
    private UploadImgApiService mUploadImgApiService2;
    private VersionBean mVersionBean;
    private String mVideoCode;
    private String mWuHanBaseUrl;
    private Disposable mWuHanInitDisposable;
    private volatile String mWuHanPassWord;
    private volatile String mWuHanUserName;
    private WuhanApiService mWuhanApiService;
    private YunJiAudioService mYunJiAudioService;
    private YunJiOpenApiService mYunJiOpenApiService;
    private volatile boolean openVoiceCallFunction;
    private HashMap<String, String> requestMap;
    private Retrofit retrofit;
    /* access modifiers changed from: private */
    public int retryCount;

    public final String openTurnstile(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "propertyUrl");
        Intrinsics.checkNotNullParameter(str2, "accessToken");
        Intrinsics.checkNotNullParameter(str3, "openTurnstileBeanStr");
        return "TODO";
    }

    public final Disposable getMWuHanInitDisposable() {
        return this.mWuHanInitDisposable;
    }

    public final void setMWuHanInitDisposable(Disposable disposable) {
        this.mWuHanInitDisposable = disposable;
    }

    public final Disposable getMInterValDisposable() {
        return this.mInterValDisposable;
    }

    public final void setMInterValDisposable(Disposable disposable) {
        this.mInterValDisposable = disposable;
    }

    public final String getMAuthorizationCode() {
        return this.mAuthorizationCode;
    }

    public final void setMAuthorizationCode(String str) {
        this.mAuthorizationCode = str;
    }

    public final String getMVideoCode() {
        return this.mVideoCode;
    }

    public final void setMVideoCode(String str) {
        this.mVideoCode = str;
    }

    public final int getInitState() {
        return this.initState;
    }

    public final void setInitState(int i) {
        this.initState = i;
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final void setMHandler(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.mHandler = handler;
    }

    public final GetVideoCodeCallback getGetVideoCodeCallback() {
        return this.getVideoCodeCallback;
    }

    public final void setGetVideoCodeCallback(GetVideoCodeCallback getVideoCodeCallback2) {
        this.getVideoCodeCallback = getVideoCodeCallback2;
    }

    public final OnGetTimerReceptionTaskCallback getMOnGetTimerReceptionTaskCallback() {
        return this.mOnGetTimerReceptionTaskCallback;
    }

    public final void setMOnGetTimerReceptionTaskCallback(OnGetTimerReceptionTaskCallback onGetTimerReceptionTaskCallback) {
        this.mOnGetTimerReceptionTaskCallback = onGetTimerReceptionTaskCallback;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ciot/networklib/RetrofitManager$RetrofitHelperHolder;", "", "()V", "holder", "Lcom/ciot/networklib/RetrofitManager;", "getHolder", "()Lcom/ciot/networklib/RetrofitManager;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: RetrofitManager.kt */
    private static final class RetrofitHelperHolder {
        public static final RetrofitHelperHolder INSTANCE = new RetrofitHelperHolder();
        private static final RetrofitManager holder = new RetrofitManager();

        private RetrofitHelperHolder() {
        }

        public final RetrofitManager getHolder() {
            return holder;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/ciot/networklib/RetrofitManager$Companion;", "", "()V", "CLOCK_IN_HELPER_TAG", "", "SEMANTIC_INTENT_TYPE_SELF_STUDY", "", "SEMANTIC_INTENT_TYPE_UNKNOWN", "SEMANTIC_INTENT_TYPE_YUNJI", "SEMANTIC_INTENT_TYPE_YUNJI_DEV", "SEMANTIC_INTENT_TYPE_YUNJI_PRODUCT", "SEMANTIC_INTENT_TYPE_YUNJI_TEST", "instance", "Lcom/ciot/networklib/RetrofitManager;", "getInstance", "()Lcom/ciot/networklib/RetrofitManager;", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: RetrofitManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RetrofitManager getInstance() {
            return RetrofitHelperHolder.INSTANCE.getHolder();
        }
    }

    public final SemanticIntentApiService getCiotSemanticIntentApiService() {
        String ciotSemanticIntentLogTransmissionUrl = getCiotSemanticIntentLogTransmissionUrl();
        if (TextUtils.isEmpty(ciotSemanticIntentLogTransmissionUrl)) {
            return null;
        }
        if (this.mCiotSemanticIntentApiService == null) {
            this.mCiotSemanticIntentApiService = (SemanticIntentApiService) new Retrofit.Builder().baseUrl(ciotSemanticIntentLogTransmissionUrl).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(SemanticIntentApiService.class);
        }
        return this.mCiotSemanticIntentApiService;
    }

    public final DiagnosisApiService getDiagnosisApiService() {
        String diagnosisUploadUrl = getDiagnosisUploadUrl();
        if (TextUtils.isEmpty(diagnosisUploadUrl)) {
            return null;
        }
        if (this.mDiagnosisApiService == null) {
            this.mDiagnosisApiService = (DiagnosisApiService) new Retrofit.Builder().baseUrl(diagnosisUploadUrl).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(DiagnosisApiService.class);
            Log.d(this.TAG, "getDiagnosisApiService: ");
        }
        return this.mDiagnosisApiService;
    }

    public final String getCiotSemanticIntentLogTransmissionUrl() {
        String ziyanVoiceIp = AppSpUtil.getInstance().getZiyanVoiceIp();
        CharSequence charSequence = ziyanVoiceIp;
        return charSequence == null || charSequence.length() == 0 ? NetConstant.CIOT_DEFAULT_URL_PRODUCT : ziyanVoiceIp;
    }

    public final String getDiagnosisUploadUrl() {
        String wuHanBaseUrl = getWuHanBaseUrl();
        Boolean bool = null;
        if (wuHanBaseUrl != null) {
            bool = Boolean.valueOf(StringsKt.contains$default((CharSequence) wuHanBaseUrl, (CharSequence) "dev.csstrobot.com", false, 2, (Object) null));
        }
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue() ? NetConstant.INSTANCE.getDIAGNOSIS_IP_TEST() : NetConstant.INSTANCE.getDIAGNOSIS_IP_PRODUCTION();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getSemanticIntentType() {
        /*
            r6 = this;
            java.lang.String r0 = r6.getSemanticIntentBaseUrl()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            java.lang.String r3 = "https://jhai-zz.yunjiai.cn"
            if (r0 == 0) goto L_0x0017
            r0 = r3
            goto L_0x001b
        L_0x0017:
            java.lang.String r0 = r6.getSemanticIntentBaseUrl()
        L_0x001b:
            java.lang.String r4 = r6.TAG
            com.ciot.base.util.MyLogUtils.Logd(r4, r0)
            int r4 = r0.hashCode()
            r5 = -1578082858(0xffffffffa1f05dd6, float:-1.6287871E-18)
            if (r4 == r5) goto L_0x0048
            r2 = -836603341(0xffffffffce227233, float:-6.8134829E8)
            if (r4 == r2) goto L_0x003d
            r2 = 256048910(0xf42ff0e, float:9.61406E-30)
            if (r4 == r2) goto L_0x0034
            goto L_0x004e
        L_0x0034:
            java.lang.String r2 = "https://dev-jhai-zz.yunjiai.cn"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0051
            goto L_0x004e
        L_0x003d:
            java.lang.String r1 = "https://test-jhai-zz.yunjiai.cn"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0046
            goto L_0x004e
        L_0x0046:
            r1 = 3
            goto L_0x0051
        L_0x0048:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0050
        L_0x004e:
            r1 = -1
            goto L_0x0051
        L_0x0050:
            r1 = 0
        L_0x0051:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.RetrofitManager.getSemanticIntentType():int");
    }

    public final SemanticIntentApiService getSemanticIntentApiService() {
        String str;
        if (getSemanticIntentBaseUrl().length() == 0) {
            str = "https://jhai-zz.yunjiai.cn";
        } else {
            str = getSemanticIntentBaseUrl();
        }
        if (this.mSemanticIntentApiService == null) {
            String str2 = this.TAG;
            MyLogUtils.Logd(str2, "baseUrl = " + str);
            this.mSemanticIntentApiService = (SemanticIntentApiService) new Retrofit.Builder().baseUrl(str).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(SemanticIntentApiService.class);
            String str3 = this.TAG;
            MyLogUtils.Logd(str3, "robotAllow getSemanticApiService  mSemanticIntentType: " + this.mSemanticIntentType + " semanticIntentUrl: " + str);
        }
        SemanticIntentApiService semanticIntentApiService = this.mSemanticIntentApiService;
        Intrinsics.checkNotNull(semanticIntentApiService);
        return semanticIntentApiService;
    }

    public final WuhanApiService getWuHanApiService() {
        if (this.mWuhanApiService == null) {
            try {
                String wuHanBaseUrl = getWuHanBaseUrl();
                String str = this.TAG;
                MyLogUtils.Logd(str, "getWuHanBaseUrl=" + wuHanBaseUrl);
                this.mWuhanApiService = (WuhanApiService) new Retrofit.Builder().baseUrl(wuHanBaseUrl).client(getOkHttpClient("NETWORK_TAG")).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(WuhanApiService.class);
                MyLogUtils.Logd(this.TAG, "getWuHanApiService");
            } catch (Exception e) {
                String str2 = this.TAG;
                MyLogUtils.Loge(str2, "getWuHanApiService===:" + e);
            }
        }
        return this.mWuhanApiService;
    }

    public final QiWuApiService getQiWuApiService() {
        if (this.mQiWuApiService == null) {
            String qiWuBaseUrl = getQiWuBaseUrl();
            String str = this.TAG;
            MyLogUtils.Logd(str, "getQiWuApiService=" + qiWuBaseUrl);
            this.mQiWuApiService = (QiWuApiService) new Retrofit.Builder().baseUrl(qiWuBaseUrl).client(getOkHttpClient("NETWORK_TAG")).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(QiWuApiService.class);
        }
        return this.mQiWuApiService;
    }

    public final DownloadService getDownloadService() {
        if (this.mDownloadService == null) {
            String downBaseUrl = getDownBaseUrl();
            String str = this.TAG;
            MyLogUtils.Logd(str, "getDownBaseUrl=" + downBaseUrl);
            this.mDownloadService = (DownloadService) new Retrofit.Builder().baseUrl(downBaseUrl).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(DownloadService.class);
            MyLogUtils.Logd(this.TAG, "getDownloadService");
        }
        DownloadService downloadService = this.mDownloadService;
        Intrinsics.checkNotNull(downloadService);
        return downloadService;
    }

    public final UploadImgApiService getUploadImgApiService() {
        if (this.mUploadImgApiService1 == null) {
            this.mUploadImgApiService1 = (UploadImgApiService) new Retrofit.Builder().baseUrl(getWuHanBaseUrl()).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(UploadImgApiService.class);
            MyLogUtils.Logd(this.TAG, "getUploadImgApiService1");
        }
        UploadImgApiService uploadImgApiService = this.mUploadImgApiService1;
        Intrinsics.checkNotNull(uploadImgApiService);
        return uploadImgApiService;
    }

    public final UploadImgApiService getUploadImgApiService(String str) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        this.mUploadImgApiService2 = (UploadImgApiService) new Retrofit.Builder().baseUrl(str).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(UploadImgApiService.class);
        MyLogUtils.Logd(this.TAG, "getUploadImgApiService1");
        UploadImgApiService uploadImgApiService = this.mUploadImgApiService2;
        Intrinsics.checkNotNull(uploadImgApiService);
        return uploadImgApiService;
    }

    public final YunJiAudioService getYunjiAudioService() {
        if (this.mYunJiAudioService == null) {
            this.mYunJiAudioService = (YunJiAudioService) new Retrofit.Builder().baseUrl("https://mrad-reqtask-test.brandwisdom.cn/").client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(YunJiAudioService.class);
            MyLogUtils.Logd(this.TAG, "getYunjiAudioService");
        }
        YunJiAudioService yunJiAudioService = this.mYunJiAudioService;
        Intrinsics.checkNotNull(yunJiAudioService);
        return yunJiAudioService;
    }

    public final DeviceIdRegisterService getDeviceIdRegisterService() {
        String str;
        if (getDeviceIdRegisterBaseUrl().length() == 0) {
            str = "https://mrad-reqtask-test.brandwisdom.cn/";
        } else {
            str = getDeviceIdRegisterBaseUrl();
        }
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "baseUrl = " + str);
        if (this.mDeviceIdRegisterService == null) {
            this.mDeviceIdRegisterService = (DeviceIdRegisterService) new Retrofit.Builder().baseUrl(str).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(DeviceIdRegisterService.class);
            MyLogUtils.Logd(this.TAG, "getSemanticApiService");
        }
        DeviceIdRegisterService deviceIdRegisterService = this.mDeviceIdRegisterService;
        Intrinsics.checkNotNull(deviceIdRegisterService);
        return deviceIdRegisterService;
    }

    public final String getDeviceIdRegisterBaseUrl() {
        String semanticIntentBaseUrl = getSemanticIntentBaseUrl();
        return (Intrinsics.areEqual((Object) NetConstant.YUNJI_DEFAULT_URL_DEV, (Object) semanticIntentBaseUrl) || Intrinsics.areEqual((Object) NetConstant.CIOT_DEFAULT_URL_DEV, (Object) semanticIntentBaseUrl)) ? "https://mrad-reqtask-test.brandwisdom.cn/" : "https://mrad-reqtask.brandwisdom.cn/";
    }

    public final YunJiOpenApiService getYunJiOpenApiService() {
        if (this.mYunJiOpenApiService == null) {
            this.mYunJiOpenApiService = (YunJiOpenApiService) new Retrofit.Builder().baseUrl(NetConstant.YUN_JI_OPEN_API_BASE_URL).client(getOkHttpClient()).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(YunJiOpenApiService.class);
            MyLogUtils.Logd(this.TAG, "YunJiOpenApiService");
        }
        YunJiOpenApiService yunJiOpenApiService = this.mYunJiOpenApiService;
        Intrinsics.checkNotNull(yunJiOpenApiService);
        return yunJiOpenApiService;
    }

    private final String getSemanticBaseUrl() {
        return NetConstant.INSTANCE.getURL_DEFAULT_SEMANTIC();
    }

    public final boolean judgePproduction() {
        String wuHanAllowUrl = AppSpUtil.getInstance().getWuHanAllowUrl();
        if (wuHanAllowUrl == null) {
            return false;
        }
        LogPlus.w(wuHanAllowUrl);
        return StringsKt.startsWith$default(wuHanAllowUrl, "http://robot", false, 2, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized java.lang.String getSemanticIntentBaseUrl() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = r1.mSemanticIntentBaseUrl     // Catch:{ all -> 0x0022 }
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0010
            int r0 = r0.length()     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            if (r0 == 0) goto L_0x001b
            com.ciot.base.constant.NetConstant r0 = com.ciot.base.constant.NetConstant.INSTANCE     // Catch:{ all -> 0x0022 }
            java.lang.String r0 = r0.getURL_DEFAULT_SEMANTIC_INTENT()     // Catch:{ all -> 0x0022 }
            r1.mSemanticIntentBaseUrl = r0     // Catch:{ all -> 0x0022 }
        L_0x001b:
            java.lang.String r0 = r1.mSemanticIntentBaseUrl     // Catch:{ all -> 0x0022 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)
            return r0
        L_0x0022:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.RetrofitManager.getSemanticIntentBaseUrl():java.lang.String");
    }

    public final synchronized void setSemanticIntentBaseUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "semanticUrl");
        this.mSemanticIntentBaseUrl = str;
    }

    public final String getWuHanBaseUrl() {
        String wuHanAllowUrl = AppSpUtil.getInstance().getWuHanAllowUrl();
        if (this.initState >= 0) {
            wuHanAllowUrl = MySpUtils.getInstance().getString("SP_CONFIG_WUHAN_BASEURL", AppSpUtil.getInstance().getWuHanAllowUrl());
        }
        String str = this.TAG;
        MyLogUtils.Logd(str, "RetrofitManager getWuHanBaseUrl:" + wuHanAllowUrl);
        return wuHanAllowUrl;
    }

    public final String getQiWuBaseUrl() {
        String str = this.TAG;
        MyLogUtils.Logw(str, "RetrofitManager getQiWuBaseUrl:" + "https://robot-service.centaurstech.com/");
        return "https://robot-service.centaurstech.com/";
    }

    /* access modifiers changed from: private */
    public final void setWuHanBaseUrl(String str) {
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "RetrofitManager setWuHanBaseUrl:" + str);
        this.mWuHanBaseUrl = str;
        setPropertyDomain(str);
        MySpUtils.getInstance().putString("SP_CONFIG_WUHAN_BASEURL", str);
    }

    public final void setDownBaseUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "downBaseUrl");
        this.mDownBaseUrl = str;
    }

    public final String getDownBaseUrl() {
        String str = this.mDownBaseUrl;
        Intrinsics.checkNotNull(str);
        return str;
    }

    private final OkHttpClient getOkHttpClient() {
        return getOkHttpClient(this.TAG);
    }

    private final OkHttpClient getOkHttpClient(String str) {
        OkHttpClient.Builder with = RetrofitUrlManager.getInstance().with(new OkHttpClient().newBuilder());
        try {
            with.addInterceptor(new HttpLoggingInterceptor(str));
            with.connectTimeout(20, TimeUnit.SECONDS);
            with.readTimeout(20, TimeUnit.SECONDS);
            with.writeTimeout(20, TimeUnit.SECONDS);
            with.retryOnConnectionFailure(true);
            with.proxy(Proxy.NO_PROXY);
            MyLogUtils.Logd(this.TAG, "getOkHttpClient");
        } catch (Exception e) {
            String str2 = this.TAG;
            MyLogUtils.Loge(str2, "getOkHttpClient Exception：" + e);
        }
        return with.build();
    }

    public final void initRobotConfig(String str, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "robotId");
        Intrinsics.checkNotNullParameter(str2, "robotNum");
        String str3 = this.TAG;
        MyLogUtils.Loge(str3, "robotid: " + str + "   robotType: " + i + "  robotNum: " + str2);
        if (3 != i) {
            AppSpUtil.getInstance().setRobotNumber(str);
            AppSpUtil.getInstance().setRobotRealType(i);
            AppSpUtil.getInstance().setRobotModel(str2);
            SrosSendMsgUtil.getInstance().init();
        }
        setRobotId(str);
        setRobotRealType(i);
        setRobotNum(str2);
    }

    public final void init() {
        resetData();
        this.mAuthorizationCode = MySpUtils.getInstance().getString(NetConstant.AUTHORIZATION_CODE, "");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("RetrofitManager ROBOT_TYPE_WELCOM");
        boolean z = true;
        if (1 != NetConstant.INSTANCE.getROBOT_TYPE()) {
            z = false;
        }
        sb.append(z);
        Log.w(str, sb.toString());
        if (3 != NetConstant.INSTANCE.getROBOT_TYPE()) {
            Log.w(this.TAG, "RetrofitManager init");
            setIsBusinessMode(false);
            String robotNumber = AppSpUtil.getInstance().getRobotNumber();
            Intrinsics.checkNotNullExpressionValue(robotNumber, "getInstance().robotNumber");
            initRobotConfig(robotNumber, MySpUtils.getInstance().getInt(SpConstant.ROBOT_REAL_TYPE, NetConstant.INSTANCE.getROBOT_TYPE()), NetConstant.INSTANCE.getROBOT_NUM());
            String wuHanAllowUrl = AppSpUtil.getInstance().getWuHanAllowUrl();
            MyLogUtils.Logd("NETWORK_TAG", "RetrofitManager init -> robotAllow,serverUrl=" + wuHanAllowUrl);
            Intrinsics.checkNotNullExpressionValue(wuHanAllowUrl, "allowUrl");
            robotAllow(wuHanAllowUrl, new RetrofitManager$init$1(this));
            return;
        }
        initGatekeeperRobot();
    }

    private final void initGatekeeperRobot() {
        Disposable subscribe = Observable.interval(200, 200, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                RetrofitManager.m68initGatekeeperRobot$lambda3(RetrofitManager.this, (Long) obj);
            }
        });
        this.mInterValDisposable = subscribe;
        addSubscription(subscribe);
    }

    /* access modifiers changed from: private */
    /* renamed from: initGatekeeperRobot$lambda-3  reason: not valid java name */
    public static final void m68initGatekeeperRobot$lambda3(RetrofitManager retrofitManager, Long l) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        if (((int) l.longValue()) == 50) {
            Disposable disposable = retrofitManager.mInterValDisposable;
            if (disposable != null) {
                disposable.dispose();
                return;
            }
            return;
        }
        if (!NetConstant.INSTANCE.getIS_GET_CODE_FROM_NET() || ((!TextUtils.isEmpty(retrofitManager.mAuthorizationCode) && !retrofitManager.isShouldGetVideoCode()) || TextUtils.isEmpty(retrofitManager.getWuHanUserName()) || TextUtils.isEmpty(retrofitManager.getToken()))) {
            String str = retrofitManager.TAG;
            MyLogUtils.Logd(str, "NetConstant.IS_GET_CODE_FROM_NET=" + NetConstant.INSTANCE.getIS_GET_CODE_FROM_NET() + ",mAuthorizationCode=" + retrofitManager.mAuthorizationCode + ",getWuHanUserName=" + retrofitManager.getWuHanUserName() + ",getToken=" + retrofitManager.getToken());
        } else {
            String wuHanUserName = retrofitManager.getWuHanUserName();
            Intrinsics.checkNotNull(wuHanUserName);
            String token = retrofitManager.getToken();
            Intrinsics.checkNotNull(token);
            retrofitManager.getAuthorizationCode(wuHanUserName, token);
        }
        if (!TextUtils.isEmpty(retrofitManager.getToken()) && !TextUtils.isEmpty(retrofitManager.getRobotId()) && !TextUtils.isEmpty(retrofitManager.getWuHanUserName())) {
            Disposable disposable2 = retrofitManager.mInterValDisposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            retrofitManager.getRobotInfo();
        }
    }

    private final boolean isShouldGetVideoCode() {
        return NetConstant.INSTANCE.getROBOT_TYPE() == 5 && TextUtils.isEmpty(this.mVideoCode);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0022, code lost:
        r4 = (r4 = r0.getCodeForAccount(r4, r5)).subscribeOn(io.reactivex.schedulers.Schedulers.io());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void getAuthorizationCode(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = r3.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ready getAuthorizationCode,userName="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.ciot.base.util.MyLogUtils.Logi(r0, r1)
            com.ciot.networklib.api.WuhanApiService r0 = r3.getWuHanApiService()
            if (r0 == 0) goto L_0x003b
            io.reactivex.Observable r4 = r0.getCodeForAccount(r4, r5)
            if (r4 == 0) goto L_0x003b
            io.reactivex.Scheduler r5 = io.reactivex.schedulers.Schedulers.io()
            io.reactivex.Observable r4 = r4.subscribeOn(r5)
            if (r4 == 0) goto L_0x003b
            com.ciot.networklib.-$$Lambda$RetrofitManager$-mNdPwQ1hiY6F0yvVKJlcl5_pEw r5 = new com.ciot.networklib.-$$Lambda$RetrofitManager$-mNdPwQ1hiY6F0yvVKJlcl5_pEw
            r5.<init>()
            com.ciot.networklib.-$$Lambda$RetrofitManager$V43xbQbEmalR2jA-4S8eRslPqtI r0 = new com.ciot.networklib.-$$Lambda$RetrofitManager$V43xbQbEmalR2jA-4S8eRslPqtI
            r0.<init>()
            io.reactivex.disposables.Disposable r4 = r4.subscribe(r5, r0)
            goto L_0x003c
        L_0x003b:
            r4 = 0
        L_0x003c:
            r3.addSubscription(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.RetrofitManager.getAuthorizationCode(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: getAuthorizationCode$lambda-9  reason: not valid java name */
    public static final void m58getAuthorizationCode$lambda9(RetrofitManager retrofitManager, ResponseBody responseBody) {
        String guideWord;
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNull(responseBody);
        String str = new String(responseBody.bytes(), Charsets.UTF_8);
        String str2 = retrofitManager.TAG;
        MyLogUtils.Logi(str2, "get getAuthorizationCode result: " + str);
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!Intrinsics.areEqual((Object) "{}", (Object) str)) {
                    AuthorizationCodeBean authorizationCodeBean = (AuthorizationCodeBean) GsonUtils.fromJson(str, AuthorizationCodeBean.class);
                    retrofitManager.mAuthorizationCode = authorizationCodeBean.getAucdk();
                    retrofitManager.mVideoCode = authorizationCodeBean.getVideo();
                    LogPlus.w("NAVIGATION_TAG", str);
                    boolean z = false;
                    if (!(authorizationCodeBean == null || (guideWord = authorizationCodeBean.getGuideWord()) == null || !(!StringsKt.isBlank(guideWord)))) {
                        z = true;
                    }
                    if (z) {
                        WelcomeLiveData.Companion.get().postValue(authorizationCodeBean.getGuideWord());
                    }
                    String backgroundurl = authorizationCodeBean.getBackgroundurl();
                    if (backgroundurl != null) {
                        BackgroundLiveData.Companion.get().postValue(backgroundurl);
                        LogPlus.e("NAVIGATION_TAG-----" + backgroundurl);
                    }
                    List<HomeCard> homeCards = authorizationCodeBean.getHomeCards();
                    if (homeCards != null) {
                        Collection arrayList = new ArrayList();
                        for (Object next : homeCards) {
                            if (((HomeCard) next).function_available) {
                                arrayList.add(next);
                            }
                        }
                        HomeCardsLiveData.Companion.get().postValue((List) arrayList);
                    }
                    retrofitManager.mIsRealtimeUpload = authorizationCodeBean.isRealTimeUpload();
                    List<Function> funclist = authorizationCodeBean.getFunclist();
                    if (funclist != null) {
                        retrofitManager.parseFunctionList(funclist);
                    }
                    if (!TextUtils.isEmpty(retrofitManager.mAuthorizationCode)) {
                        retrofitManager.saveVoiceCode2Local(authorizationCodeBean.getAucdk());
                    } else {
                        String string = ContextUtil.getContext().getString(R.string.network_get_authorization_code_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…uthorization_code_failed)");
                        retrofitManager.sendNetErrorMsg(5, string);
                        retrofitManager.deleteLocalVoiceCode();
                        retrofitManager.setVideoCodeError();
                    }
                    if (!TextUtils.isEmpty(retrofitManager.mVideoCode)) {
                        retrofitManager.saveVideoCode2Local(authorizationCodeBean.getVideo());
                    } else {
                        MyLogUtils.Logw(retrofitManager.TAG, "get getAuthorization Video Code is empty");
                    }
                    GetVideoCodeCallback getVideoCodeCallback2 = retrofitManager.getVideoCodeCallback;
                    if (getVideoCodeCallback2 != null) {
                        getVideoCodeCallback2.onSuccess(retrofitManager.mVideoCode);
                    }
                    retrofitManager.saveTimerTask2Db(authorizationCodeBean.getTimedTasks());
                    return;
                }
            }
            String string2 = ContextUtil.getContext().getString(R.string.network_get_authorization_code_failed);
            Intrinsics.checkNotNullExpressionValue(string2, "getContext().getString(R…uthorization_code_failed)");
            retrofitManager.sendNetErrorMsg(5, string2);
            retrofitManager.deleteLocalVoiceCode();
        } catch (Exception e) {
            String str3 = retrofitManager.TAG;
            MyLogUtils.Logi(str3, "get getAuthorizationCode Exception: " + e);
            String string3 = ContextUtil.getContext().getString(R.string.network_get_authorization_code_exception);
            Intrinsics.checkNotNullExpressionValue(string3, "getContext().getString(R…orization_code_exception)");
            retrofitManager.sendNetErrorMsg(5, string3);
            retrofitManager.setVideoCodeError();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getAuthorizationCode$lambda-10  reason: not valid java name */
    public static final void m57getAuthorizationCode$lambda10(RetrofitManager retrofitManager, Throwable th) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        String str = retrofitManager.TAG;
        MyLogUtils.Logi(str, "get getAuthorizationCode throwAble: " + th);
        String string = ContextUtil.getContext().getString(R.string.network_get_authorization_code_exception);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…orization_code_exception)");
        retrofitManager.sendNetErrorMsg(5, string);
        retrofitManager.setVideoCodeError();
    }

    public final boolean getOpenVoiceCallFunction() {
        return this.openVoiceCallFunction;
    }

    public final void setOpenVoiceCallFunction(boolean z) {
        this.openVoiceCallFunction = z;
    }

    private final void parseFunctionList(List<? extends Function> list) {
        if (this.mRobotRealType == 5) {
            for (Function function : list) {
                Log.i("TAGA", "parseFunctionList: " + function);
                if (Intrinsics.areEqual((Object) "manualServicer", (Object) function.getFunction_key())) {
                    this.openVoiceCallFunction = function.isFunction_available();
                }
            }
        }
    }

    private final void saveTimerTask2Db(List<AuthorizationCodeTimerTaskBean> list) {
        String str = this.TAG;
        MyLogUtils.Logd(str, "findPropertyByAccount timedTasks: " + list);
        SentryRealmHelper instance = SentryRealmHelper.getInstance();
        Realm newRealmInstance = instance.newRealmInstance();
        instance.deleteAllTimerReceptionTask(newRealmInstance);
        Collection collection = list;
        if (!(collection == null || collection.isEmpty())) {
            for (AuthorizationCodeTimerTaskBean next : list) {
                if (Intrinsics.areEqual((Object) TimerReceptionTaskBean.RECEPTION_TASK_TYPE, (Object) next.getTasktype())) {
                    instance.insertOrUpdateTimerReceptionTask(newRealmInstance, new TimerReceptionTaskBean(GsonUtils.toJson(next), next.getTasktype(), next.getCycletype(), next.getTaskdatas(), next.getTaskstart(), next.getTaskend(), next.getEnable(), next.getDescription()));
                }
            }
        }
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
        OnGetTimerReceptionTaskCallback onGetTimerReceptionTaskCallback = this.mOnGetTimerReceptionTaskCallback;
        if (onGetTimerReceptionTaskCallback != null) {
            onGetTimerReceptionTaskCallback.onGetTimerReceptionTaskCallback();
        }
    }

    private final void setVideoCodeError() {
        if (this.mRobotRealType == 5) {
            DiagnosisManager.getInstance().getMonitorDiagnosis().getSubMonitorDiagnosis().setStatus((int) MonitorDiagnosis.SubMonitorDiagnosis.CODE_ERROR_GET_CODE_FAIL, R.string.diagnosis_error_get_video_code);
        }
    }

    private final void getRobotInfo() {
        Disposable disposable;
        Observable<ResponseBody> subscribeOn;
        MyLogUtils.Logd(this.TAG, "ready getRobotInfo");
        CharSequence robotId = getRobotId();
        if (robotId == null || robotId.length() == 0) {
            MyLogUtils.Logd(this.TAG, "ready getRobotInfo failed,robotId is null");
            return;
        }
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null) {
            String robotId2 = getRobotId();
            Intrinsics.checkNotNull(robotId2);
            Observable<ResponseBody> robots = wuHanApiService.robots(robotId2, getToken());
            if (!(robots == null || (subscribeOn = robots.subscribeOn(Schedulers.io())) == null)) {
                disposable = subscribeOn.subscribe(new Consumer() {
                    public final void accept(Object obj) {
                        RetrofitManager.m62getRobotInfo$lambda11(RetrofitManager.this, (ResponseBody) obj);
                    }
                }, new Consumer() {
                    public final void accept(Object obj) {
                        RetrofitManager.m63getRobotInfo$lambda12(RetrofitManager.this, (Throwable) obj);
                    }
                });
                addSubscription(disposable);
            }
        }
        disposable = null;
        addSubscription(disposable);
    }

    /* access modifiers changed from: private */
    /* renamed from: getRobotInfo$lambda-11  reason: not valid java name */
    public static final void m62getRobotInfo$lambda11(RetrofitManager retrofitManager, ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        if (responseBody == null) {
            MyLogUtils.Logi(retrofitManager.TAG, "get robotProperty failed,result is null");
            String string = ContextUtil.getContext().getString(R.string.network__get_robot_info_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…k__get_robot_info_failed)");
            retrofitManager.sendNetErrorMsg(3, string);
        }
        retrofitManager.initState = 3;
        Intrinsics.checkNotNull(responseBody);
        String str = new String(responseBody.bytes(), Charsets.UTF_8);
        String str2 = retrofitManager.TAG;
        MyLogUtils.Logi(str2, "get robotProperty result: " + str);
        JSONObject jSONObject = new JSONObject(str);
        String string2 = jSONObject.getString("projectid");
        String string3 = jSONObject.getString("area");
        Intrinsics.checkNotNullExpressionValue(string2, "projectid");
        retrofitManager.setProject(string2);
        Intrinsics.checkNotNullExpressionValue(string3, "area");
        retrofitManager.setArea(string3);
        retrofitManager.generateRequestMap();
        retrofitManager.mIsGetRobotInfoSuccess = true;
    }

    /* access modifiers changed from: private */
    /* renamed from: getRobotInfo$lambda-12  reason: not valid java name */
    public static final void m63getRobotInfo$lambda12(RetrofitManager retrofitManager, Throwable th) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        String str = retrofitManager.TAG;
        MyLogUtils.Loge(str, "getRobotInfo exception" + th);
        String string = ContextUtil.getContext().getString(R.string.network__init_failed);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…ing.network__init_failed)");
        retrofitManager.sendNetErrorMsg(4, string);
        Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> function4 = retrofitManager.mLoginListener;
        if (function4 != null) {
            function4.invoke(false, false, false, 4);
        }
        retrofitManager.mIsGetRobotInfoSuccess = false;
    }

    /* access modifiers changed from: private */
    public final void initRobot() {
        MyLogUtils.Logd(this.TAG, "Retrofit initWelComeRobot");
        Observable create = Observable.create(new ObservableOnSubscribe() {
            public final void subscribe(ObservableEmitter observableEmitter) {
                RetrofitManager.m69initRobot$lambda13(RetrofitManager.this, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(create, "create<Boolean> { e ->\n …artTcpService()\n        }");
        Disposable subscribe = create.map(new io.reactivex.functions.Function() {
            public final Object apply(Object obj) {
                return RetrofitManager.m70initRobot$lambda14(RetrofitManager.this, (Boolean) obj);
            }
        }).flatMap(new io.reactivex.functions.Function() {
            public final Object apply(Object obj) {
                return RetrofitManager.m71initRobot$lambda15(RetrofitManager.this, (RequestBody) obj);
            }
        }).map(new io.reactivex.functions.Function() {
            public final Object apply(Object obj) {
                return RetrofitManager.m72initRobot$lambda16(RetrofitManager.this, (ResponseBody) obj);
            }
        }).flatMap(new io.reactivex.functions.Function() {
            public final Object apply(Object obj) {
                return RetrofitManager.m73initRobot$lambda18(RetrofitManager.this, (Boolean) obj);
            }
        }).subscribeOn(Schedulers.io()).timeout(40000, TimeUnit.MILLISECONDS).subscribe(new Consumer() {
            public final void accept(Object obj) {
                RetrofitManager.m74initRobot$lambda19(RetrofitManager.this, (ResponseBody) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                RetrofitManager.m75initRobot$lambda20(RetrofitManager.this, (Throwable) obj);
            }
        });
        this.mWuHanInitDisposable = subscribe;
        addSubscription(subscribe);
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-13  reason: not valid java name */
    public static final void m69initRobot$lambda13(RetrofitManager retrofitManager, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(observableEmitter, "e");
        retrofitManager.setAccountListener(observableEmitter);
        retrofitManager.startTcpService();
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-14  reason: not valid java name */
    public static final RequestBody m70initRobot$lambda14(RetrofitManager retrofitManager, Boolean bool) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(bool, "isGetUserAndPwm");
        return retrofitManager.getUserRequestBody(bool.booleanValue());
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-15  reason: not valid java name */
    public static final ObservableSource m71initRobot$lambda15(RetrofitManager retrofitManager, RequestBody requestBody) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(requestBody, "logInRequestBody");
        WuhanApiService wuHanApiService = retrofitManager.getWuHanApiService();
        return wuHanApiService != null ? wuHanApiService.login(requestBody) : null;
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-16  reason: not valid java name */
    public static final Boolean m72initRobot$lambda16(RetrofitManager retrofitManager, ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(responseBody, "loginResponseBody");
        return Boolean.valueOf(retrofitManager.parseLoginResponseBody(responseBody));
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-18  reason: not valid java name */
    public static final ObservableSource m73initRobot$lambda18(RetrofitManager retrofitManager, Boolean bool) {
        Observable<ResponseBody> observable;
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(bool, "isGetToken");
        if (!NetConstant.INSTANCE.getIS_GET_CODE_FROM_NET() || ((!TextUtils.isEmpty(retrofitManager.mAuthorizationCode) && !retrofitManager.isShouldGetVideoCode()) || TextUtils.isEmpty(retrofitManager.getWuHanUserName()) || TextUtils.isEmpty(retrofitManager.getToken()))) {
            String str = retrofitManager.TAG;
            MyLogUtils.Logd(str, "NetConstant.IS_GET_CODE_FROM_NET=" + NetConstant.INSTANCE.getIS_GET_CODE_FROM_NET() + ",mVideoCode=" + retrofitManager.mVideoCode + ",mAuthorizationCode=" + retrofitManager.mAuthorizationCode + ",getWuHanUserName=" + retrofitManager.getWuHanUserName() + ",getToken=" + retrofitManager.getToken());
        } else {
            String wuHanUserName = retrofitManager.getWuHanUserName();
            Intrinsics.checkNotNull(wuHanUserName);
            String token = retrofitManager.getToken();
            Intrinsics.checkNotNull(token);
            retrofitManager.getAuthorizationCode(wuHanUserName, token);
        }
        String token2 = retrofitManager.getToken();
        if (token2 != null) {
            if (token2.length() > 0) {
                retrofitManager.setAdData();
                SimHelper simHelper = new SimHelper();
                String token3 = retrofitManager.getToken();
                Intrinsics.checkNotNull(token3);
                simHelper.postRobotInfo(token3);
                retrofitManager.getTactics();
                try {
                    SimHelper simHelper2 = new SimHelper();
                    String token4 = retrofitManager.getToken();
                    Intrinsics.checkNotNull(token4);
                    simHelper2.postRobotInfo(token4);
                    Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> function4 = retrofitManager.mLoginListener;
                    if (function4 != null) {
                        function4.invoke(true, false, false, -1);
                    }
                } catch (Exception e) {
                    String str2 = retrofitManager.TAG;
                    MyLogUtils.Loge(str2, "after getToken Exception:" + e + ' ');
                }
            }
        }
        WuhanApiService wuHanApiService = retrofitManager.getWuHanApiService();
        if (wuHanApiService != null) {
            String robotId = retrofitManager.getRobotId();
            Intrinsics.checkNotNull(robotId);
            observable = wuHanApiService.robots(robotId, retrofitManager.getToken());
        } else {
            observable = null;
        }
        return observable;
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-19  reason: not valid java name */
    public static final void m74initRobot$lambda19(RetrofitManager retrofitManager, ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        if (responseBody == null) {
            MyLogUtils.Logi(retrofitManager.TAG, "get robotProperty failed,result is null");
            String string = ContextUtil.getContext().getString(R.string.network__get_robot_info_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…k__get_robot_info_failed)");
            retrofitManager.sendNetErrorMsg(3, string);
        }
        retrofitManager.initState = 3;
        Intrinsics.checkNotNull(responseBody);
        String str = new String(responseBody.bytes(), Charsets.UTF_8);
        String str2 = retrofitManager.TAG;
        MyLogUtils.Logi(str2, "get robotProperty result: " + str);
        JSONObject jSONObject = new JSONObject(str);
        String string2 = jSONObject.getString("projectid");
        String string3 = jSONObject.getString("area");
        Intrinsics.checkNotNullExpressionValue(string2, "projectid");
        retrofitManager.setProject(string2);
        Intrinsics.checkNotNullExpressionValue(string3, "area");
        retrofitManager.setArea(string3);
        retrofitManager.getLatestVersion(0);
        retrofitManager.generateRequestMap();
        try {
            Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> function4 = retrofitManager.mLoginListener;
            if (function4 != null) {
                function4.invoke(true, true, true, -1);
            }
        } catch (Exception unused) {
            MyLogUtils.Logi(retrofitManager.TAG, "mLoginListener?.invoke exception");
        }
        Disposable disposable = retrofitManager.mWuHanInitDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = retrofitManager.mRobotAllowDisable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initRobot$lambda-20  reason: not valid java name */
    public static final void m75initRobot$lambda20(RetrofitManager retrofitManager, Throwable th) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> function4 = retrofitManager.mLoginListener;
        if (function4 != null) {
            function4.invoke(false, false, false, 4);
        }
        String str = retrofitManager.TAG;
        MyLogUtils.Logd(str, "initWelComeRobot exception:" + th);
        String string = ContextUtil.getContext().getString(R.string.network__init_failed);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…ing.network__init_failed)");
        retrofitManager.sendNetErrorMsg(4, string);
        DiagnosisManager.getInstance().getNetworkDiagnosis().getSubNetworkDiagnosis().setStatus((int) NetworkDiagnosis.SubNetworkDiagnosis.CODE_ERROR_INIT_WELCOME_ROBOT, ContextUtil.getContext().getString(R.string.network__init_failed));
    }

    public final void getDistantlyServices() {
        WuhanApiService wuHanApiService;
        Observable<ResponseBody> distantlyAttracts;
        Observable<ResponseBody> subscribeOn;
        ProjectResponse info = CurrentProjectInfo.Companion.getInstance().getInfo();
        String id = info != null ? info.getId() : null;
        String token = getToken();
        String robotId = getRobotId();
        CharSequence charSequence = id;
        boolean z = false;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            CharSequence charSequence2 = token;
            if (!(charSequence2 == null || StringsKt.isBlank(charSequence2))) {
                CharSequence charSequence3 = robotId;
                if (charSequence3 == null || StringsKt.isBlank(charSequence3)) {
                    z = true;
                }
                if (!z && (wuHanApiService = getWuHanApiService()) != null && (distantlyAttracts = wuHanApiService.getDistantlyAttracts(token, robotId, id)) != null && (subscribeOn = distantlyAttracts.subscribeOn(Schedulers.io())) != null) {
                    subscribeOn.subscribe((Consumer<? super ResponseBody>) $$Lambda$RetrofitManager$1_0B73iXnZUxGq58bgPs5XHL_Zg.INSTANCE);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getDistantlyServices$lambda-21  reason: not valid java name */
    public static final void m59getDistantlyServices$lambda21(ResponseBody responseBody) {
        try {
            String str = new String(responseBody.bytes(), Charsets.UTF_8);
            Object fromJson = GsonUtils.fromJson(str, new RetrofitManager$getDistantlyServices$1$bean$1().getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(json, object : …yAttractBean>>() {}.type)");
            MySpUtils.getInstance(AppSpUtil.DEFAULT_SP_NAME).put(SpConstant.SP_DISTANTLY_ATTRACTS, str);
            DistantlyAttractsLiveData.Companion.get().postValue((List) fromJson);
        } catch (Exception unused) {
        }
    }

    public final void getNearbyServices() {
        WuhanApiService wuHanApiService;
        Observable<ResponseBody> nearbyAttracts;
        Observable<ResponseBody> subscribeOn;
        ProjectResponse info = CurrentProjectInfo.Companion.getInstance().getInfo();
        String id = info != null ? info.getId() : null;
        String token = getToken();
        String robotId = getRobotId();
        CharSequence charSequence = id;
        boolean z = false;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            CharSequence charSequence2 = token;
            if (!(charSequence2 == null || StringsKt.isBlank(charSequence2))) {
                CharSequence charSequence3 = robotId;
                if (charSequence3 == null || StringsKt.isBlank(charSequence3)) {
                    z = true;
                }
                if (!z && (wuHanApiService = getWuHanApiService()) != null && (nearbyAttracts = wuHanApiService.getNearbyAttracts(token, robotId, id)) != null && (subscribeOn = nearbyAttracts.subscribeOn(Schedulers.io())) != null) {
                    subscribeOn.subscribe((Consumer<? super ResponseBody>) $$Lambda$RetrofitManager$fsKyIPoQaUwU2p46odb31oUWtfw.INSTANCE);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getNearbyServices$lambda-22  reason: not valid java name */
    public static final void m61getNearbyServices$lambda22(ResponseBody responseBody) {
        try {
            String str = new String(responseBody.bytes(), Charsets.UTF_8);
            Object fromJson = GsonUtils.fromJson(str, NearbyAttractList.class);
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(json, NearbyAttractList::class.java)");
            MySpUtils.getInstance(AppSpUtil.DEFAULT_SP_NAME).put(SpConstant.SP_NEARBY_ATTRACTS, str);
            NearbyAttractsLiveData.Companion.get().postValue((NearbyAttractList) fromJson);
        } catch (Exception unused) {
        }
    }

    private final boolean parseLoginResponseBody(ResponseBody responseBody) {
        String str;
        if (responseBody != null) {
            try {
                String str2 = new String(responseBody.bytes(), Charsets.UTF_8);
                String str3 = this.TAG;
                MyLogUtils.Logd(str3, "requestWuHanLogin result:" + str2);
                str = new JSONObject(str2).getString("token");
                Intrinsics.checkNotNullExpressionValue(str, "obj.getString(\"token\")");
            } catch (Exception e) {
                String str4 = this.TAG;
                MyLogUtils.Logd(str4, "parse WuHanLogin Exception:" + e);
                str = "";
            }
            this.initState = 2;
            if (TextUtils.isEmpty(str)) {
                MyLogUtils.Logd(this.TAG, "get Token is Empty");
                String string = ContextUtil.getContext().getString(R.string.network__get_robot_token_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(R…__get_robot_token_failed)");
                sendNetErrorMsg(2, string);
                DiagnosisManager.getInstance().getNetworkDiagnosis().getSubNetworkDiagnosis().setStatus((int) NetworkDiagnosis.SubNetworkDiagnosis.CODE_ERROR_LOGIN_GET_TOKEN, ContextUtil.getContext().getString(R.string.network__get_robot_token_failed));
                RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse("unActivite"));
                return false;
            }
            setToken(str);
            bindSimInfo();
            RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse("activite"));
            String str5 = this.TAG;
            LogPlus.w(str5, "requestWuHanLogin getToken:" + getToken());
            getRobotIsLock();
            DiagnosisManager.getInstance().getNetworkDiagnosis().getSubNetworkDiagnosis().setStatus(0, "网络模块初始化成功");
            return true;
        }
        MyLogUtils.Logi(this.TAG, "requestWuHanLogin loginResponseBody is null");
        String string2 = ContextUtil.getContext().getString(R.string.network__get_robot_token_failed);
        Intrinsics.checkNotNullExpressionValue(string2, "getContext().getString(R…__get_robot_token_failed)");
        sendNetErrorMsg(2, string2);
        DiagnosisManager.getInstance().getNetworkDiagnosis().getSubNetworkDiagnosis().setStatus((int) NetworkDiagnosis.SubNetworkDiagnosis.CODE_ERROR_LOGIN_GET_TOKEN, ContextUtil.getContext().getString(R.string.network__get_robot_token_failed));
        RobotStatusLiveData.Companion.get().postValue(new RobotStatusResponse("unActivite"));
        return false;
    }

    private final RequestBody getUserRequestBody(boolean z) {
        if (this.initState == 0 && z) {
            MyLogUtils.Logd(this.TAG, "login condition is get");
        }
        this.initState = 1;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", getWuHanUserName());
        jSONObject.put("password", getWuHanPassWord());
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "root.toString()");
        RequestBody create = companion.create(parse, jSONObject2);
        MyLogUtils.Logd(this.TAG, "ready login wuhan Server");
        return create;
    }

    private final void getRobotIsLock() {
        Observable<RobotStatusResponse> retry;
        Observable<RobotStatusResponse> subscribeOn;
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null) {
            String token = getToken();
            String robotId = getRobotId();
            Intrinsics.checkNotNull(robotId);
            Observable<RobotStatusResponse> robotIsLock = wuHanApiService.robotIsLock(token, robotId);
            if (robotIsLock != null && (retry = robotIsLock.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null) {
                subscribeOn.subscribe($$Lambda$RetrofitManager$ak6LgbIpprHARzRAzIGh8racZJs.INSTANCE, $$Lambda$RetrofitManager$iw6YlXvZbVTaaXAe3yNx8w4EVeg.INSTANCE);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getRobotIsLock$lambda-23  reason: not valid java name */
    public static final void m64getRobotIsLock$lambda23(RobotStatusResponse robotStatusResponse) {
        MyLogUtils.Loge("lala----" + robotStatusResponse);
        AppSpUtil.getInstance().setRobotLock(robotStatusResponse.lock);
        RobotStatusLiveData.Companion.get().postValue(robotStatusResponse);
    }

    /* access modifiers changed from: private */
    /* renamed from: getRobotIsLock$lambda-24  reason: not valid java name */
    public static final void m65getRobotIsLock$lambda24(Throwable th) {
        MyLogUtils.Logd(th.getMessage());
    }

    private final void parseTcpIp(ResponseBody responseBody) {
        String str = new String(responseBody.bytes(), Charsets.UTF_8);
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "requestWuHanSrosIp result: " + str);
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getBoolean("success")) {
            this.initState = 0;
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "obj.getJSONObject(\"data\")");
            String string = jSONObject2.getString("host");
            Intrinsics.checkNotNullExpressionValue(string, "dataObj.getString(\"host\")");
            AppSpUtil.getInstance().setTcpIp(string);
            return;
        }
        String string2 = jSONObject.getString("message");
        Intrinsics.checkNotNullExpressionValue(string2, "obj.getString(\"message\")");
        String str3 = this.TAG;
        MyLogUtils.Logw(str3, "requestWuHanSrosIp failed: " + string2);
        sendNetErrorMsg(0, ContextUtil.getContext().getString(R.string.network__get_ip_failed) + string2);
    }

    /* access modifiers changed from: private */
    public final void sendNetErrorMsg(int i, String str) {
        Disposable disposable;
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "sendNetErrorMsg errorType=" + i + ",errorMsg=" + str);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable(str) {
                public final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    RetrofitManager.m84sendNetErrorMsg$lambda25(this.f$0);
                }
            });
        }
        if ((4 == i || 2 == i || i == 0 || 3 == i) && (disposable = this.mWuHanInitDisposable) != null) {
            disposable.dispose();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: sendNetErrorMsg$lambda-25  reason: not valid java name */
    public static final void m84sendNetErrorMsg$lambda25(String str) {
        Intrinsics.checkNotNullParameter(str, "$errorMsg");
        ToastUtils.showShort(str, new Object[0]);
    }

    private final void setAccountListener(ObservableEmitter<Boolean> observableEmitter) {
        MyLogUtils.Logd(this.TAG, "setAccountListener");
        SrosManager.getInstance().setMsgListener(new RetrofitManager$setAccountListener$1(this, observableEmitter));
    }

    /* access modifiers changed from: private */
    public final void downloadYunJiMap(NotificationBean.DescBean.MapDataBean mapDataBean) {
        Observable<ResponseBody> observable;
        if (mapDataBean.getMaplists() == null || mapDataBean.getMaplists().size() <= 0) {
            replySrosNotification(false, "map data is null");
            return;
        }
        String string = MySpUtils.getInstance().getString(WaterChassisConstants.SP_PRODUCT_ID_KEY, "");
        if (!MyStringUtils.isEmpty(string)) {
            Map hashMap = new HashMap();
            Intrinsics.checkNotNullExpressionValue(string, "productId");
            hashMap.put("productId", string);
            List<NotificationBean.DescBean.MapDataBean.MaplistsBean> maplists = mapDataBean.getMaplists();
            Intrinsics.checkNotNullExpressionValue(maplists, "mapData.maplists");
            Iterator it = CollectionsKt.withIndex(maplists).iterator();
            loop0:
            while (true) {
                observable = null;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    IndexedValue indexedValue = (IndexedValue) it.next();
                    NotificationBean.DescBean.MapDataBean.MaplistsBean maplistsBean = (NotificationBean.DescBean.MapDataBean.MaplistsBean) indexedValue.getValue();
                    if ((maplistsBean != null ? maplistsBean.getMapName() : null) != null) {
                        long currentTimeMillis = System.currentTimeMillis();
                        NotificationBean.DescBean.MapDataBean.MaplistsBean maplistsBean2 = (NotificationBean.DescBean.MapDataBean.MaplistsBean) indexedValue.getValue();
                        String mapName = maplistsBean2 != null ? maplistsBean2.getMapName() : null;
                        Intrinsics.checkNotNull(mapName);
                        hashMap.put("mapName", mapName);
                        YunJiOpenApiService yunJiOpenApiService = Companion.getInstance().getYunJiOpenApiService();
                        String sign = YunJiSignUtils.sign(hashMap, "csst.com", "3012a144d66fac32541aa4ab6159f38b", currentTimeMillis);
                        Intrinsics.checkNotNullExpressionValue(sign, "sign(hashMap, appName, secret, ts)");
                        NotificationBean.DescBean.MapDataBean.MaplistsBean maplistsBean3 = (NotificationBean.DescBean.MapDataBean.MaplistsBean) indexedValue.getValue();
                        Observable<ResponseBody> observable2 = observable;
                        Observable<ResponseBody> downloadMap = yunJiOpenApiService.downloadMap("csst.com", "3012a144d66fac32541aa4ab6159f38b", currentTimeMillis, sign, string, maplistsBean3 != null ? maplistsBean3.getMapName() : null);
                        if (indexedValue.getIndex() > 0) {
                            if (observable2 != null) {
                                downloadMap = observable2.flatMap(new io.reactivex.functions.Function() {
                                    public final Object apply(Object obj) {
                                        return RetrofitManager.m56downloadYunJiMap$lambda26(Observable.this, (ResponseBody) obj);
                                    }
                                });
                            }
                        }
                        observable = downloadMap;
                    } else {
                        Observable<ResponseBody> observable3 = observable;
                    }
                }
            }
            Observable<ResponseBody> observable4 = observable;
            if (observable4 == null) {
                replySrosNotification(false, "地图下载失败 map name is null");
            } else {
                observable4.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new RetrofitManager$downloadYunJiMap$2(this));
            }
        } else {
            MyLogUtils.Logd("NETWORK_TAG", "地图下载失败 productId: " + string);
            replySrosNotification(false, "地图下载失败 productId: " + string);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: downloadYunJiMap$lambda-26  reason: not valid java name */
    public static final ObservableSource m56downloadYunJiMap$lambda26(Observable observable, ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(observable, "$temObservable");
        Intrinsics.checkNotNullParameter(responseBody, "it");
        return observable;
    }

    /* access modifiers changed from: private */
    public final void uploadYuJiMap() {
        String string = MySpUtils.getInstance().getString(WaterChassisConstants.SP_PRODUCT_ID_KEY, "");
        String string2 = MySpUtils.getInstance().getString(WaterChassisConstants.SP_MAP_NAME_KEY, "");
        if (MyStringUtils.isEmpty(string) || MyStringUtils.isEmpty(string2)) {
            MyLogUtils.Logd("NETWORK_TAG", "地图上传失败 mapName: " + string2 + " productId: " + string);
            replySrosNotification(false, "地图上传失败 mapName: " + string2 + " productId: " + string);
            return;
        }
        Map hashMap = new HashMap();
        Intrinsics.checkNotNullExpressionValue(string, "productId");
        hashMap.put("productId", string);
        Intrinsics.checkNotNullExpressionValue(string2, "mapName");
        hashMap.put("mapName", string2);
        long currentTimeMillis = System.currentTimeMillis();
        YunJiOpenApiService yunJiOpenApiService = Companion.getInstance().getYunJiOpenApiService();
        String sign = YunJiSignUtils.sign(hashMap, "csst.com", "3012a144d66fac32541aa4ab6159f38b", currentTimeMillis);
        Intrinsics.checkNotNullExpressionValue(sign, "sign(map, appName, secret, ts)");
        yunJiOpenApiService.uploadMap("csst.com", "3012a144d66fac32541aa4ab6159f38b", currentTimeMillis, sign, string, string2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new RetrofitManager$uploadYuJiMap$1(this));
    }

    /* access modifiers changed from: private */
    public final void replySrosNotification(boolean z, String str) {
        SrosSendMsgUtil.getInstance().replyNotification(z, str);
    }

    /* access modifiers changed from: private */
    public final void uploadLogFile() {
        AtomicReference atomicReference = new AtomicReference();
        Observable.create(new ObservableOnSubscribe(atomicReference) {
            public final /* synthetic */ AtomicReference f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                RetrofitManager.m85uploadLogFile$lambda27(RetrofitManager.this, this.f$1, observableEmitter);
            }
        }).flatMap(new io.reactivex.functions.Function() {
            public final Object apply(Object obj) {
                return RetrofitManager.m86uploadLogFile$lambda28(RetrofitManager.this, (CreateRobotLogBean) obj);
            }
        }).flatMap(new io.reactivex.functions.Function(atomicReference, this) {
            public final /* synthetic */ AtomicReference f$0;
            public final /* synthetic */ RetrofitManager f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return RetrofitManager.m87uploadLogFile$lambda29(this.f$0, this.f$1, (CreateRobotLogResponse) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new RetrofitManager$uploadLogFile$4(this, atomicReference));
    }

    /* access modifiers changed from: private */
    /* renamed from: uploadLogFile$lambda-27  reason: not valid java name */
    public static final void m85uploadLogFile$lambda27(RetrofitManager retrofitManager, AtomicReference atomicReference, ObservableEmitter observableEmitter) {
        RetrofitManager retrofitManager2 = retrofitManager;
        AtomicReference atomicReference2 = atomicReference;
        ObservableEmitter observableEmitter2 = observableEmitter;
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(atomicReference2, "$uploadFilePath");
        Intrinsics.checkNotNullParameter(observableEmitter2, "it");
        List<File> listFilesInDir = FileUtils.listFilesInDir(FileConstant.APP_PATH);
        MyLogUtils.Logd(retrofitManager2.TAG, "uploadLogFile: " + listFilesInDir);
        Intrinsics.checkNotNullExpressionValue(listFilesInDir, "logFiles");
        Collection collection = listFilesInDir;
        if (!collection.isEmpty()) {
            long j = -1;
            long j2 = -1;
            for (File lastModified : listFilesInDir) {
                long lastModified2 = lastModified.lastModified();
                if (j == -1) {
                    j = lastModified2;
                } else {
                    j = RangesKt.coerceAtMost(j, lastModified2);
                }
                j2 = RangesKt.coerceAtLeast(j2, lastModified2);
            }
            if (FileUtils.createOrExistsDir(FileConstant.APP_PATH)) {
                File file = new File(FileConstant.APP_PATH + File.separator + TimeUtils.getNowString() + ".zip");
                boolean zipFiles = ZipUtils.zipFiles((Collection<File>) collection, file);
                MyLogUtils.Logd(retrofitManager2.TAG, "uploadLogFile: " + file.getAbsolutePath() + ' ' + zipFiles);
                if (zipFiles) {
                    atomicReference2.set(file.getAbsolutePath());
                    CreateRobotLogBean createRobotLogBean = new CreateRobotLogBean();
                    long j3 = (long) 1000;
                    createRobotLogBean.setBegin(j / j3);
                    createRobotLogBean.setEnd(j2 / j3);
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = StringUtils.getString(R.string.network_log_file);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.network_log_file)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{TimeUtils.getNowString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    createRobotLogBean.setName(format);
                    createRobotLogBean.setRobot(AppSpUtil.getInstance().getRobotNumber());
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String string2 = StringUtils.getString(R.string.network_log_file_content);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.network_log_file_content)");
                    String format2 = String.format(string2, Arrays.copyOf(new Object[]{TimeUtils.millis2String(j), TimeUtils.millis2String(j2)}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    createRobotLogBean.setContent(format2);
                    createRobotLogBean.setDescription(StringUtils.getString(R.string.network_log_file_description));
                    observableEmitter2.onNext(createRobotLogBean);
                } else {
                    MyLogUtils.Loge(retrofitManager2.TAG, "uploadLogFile: 压缩失败");
                    retrofitManager.replySrosNotification(false, "uploadLogFile: 压缩失败");
                }
            } else {
                MyLogUtils.Loge(retrofitManager2.TAG, "uploadLogFile: 压缩文件夹创建失败!!!");
                observableEmitter2.onError(new Throwable("压缩文件夹创建失败!!!"));
            }
        } else {
            retrofitManager.replySrosNotification(false, "uploadLogFile: 没有日志");
            MyLogUtils.Logd(retrofitManager2.TAG, "uploadLogFile: 没有日志");
        }
        observableEmitter.onComplete();
    }

    /* access modifiers changed from: private */
    /* renamed from: uploadLogFile$lambda-28  reason: not valid java name */
    public static final ObservableSource m86uploadLogFile$lambda28(RetrofitManager retrofitManager, CreateRobotLogBean createRobotLogBean) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(createRobotLogBean, "createLog");
        MyLogUtils.Logd(retrofitManager.TAG, "uploadLogFile: 请求日志文件上传地址");
        String json = GsonUtils.toJson(createRobotLogBean);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(createLog)");
        RequestBody create = RequestBody.Companion.create(json, MediaType.Companion.get("application/json;charset=utf-8"));
        WuhanApiService wuHanApiService = Companion.getInstance().getWuHanApiService();
        return wuHanApiService != null ? wuHanApiService.createUploadRobotLog(retrofitManager.getToken(), create) : null;
    }

    /* access modifiers changed from: private */
    /* renamed from: uploadLogFile$lambda-29  reason: not valid java name */
    public static final ObservableSource m87uploadLogFile$lambda29(AtomicReference atomicReference, RetrofitManager retrofitManager, CreateRobotLogResponse createRobotLogResponse) {
        Observable<ResponseBody> observable;
        Intrinsics.checkNotNullParameter(atomicReference, "$uploadFilePath");
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        Intrinsics.checkNotNullParameter(createRobotLogResponse, "it");
        File file = new File((String) atomicReference.get());
        if (file.exists()) {
            MultipartBody.Part createFormData = MultipartBody.Part.Companion.createFormData("file", file.getName(), RequestBody.Companion.create(file, MediaType.Companion.get("application/x-zip-compressed")));
            if (createRobotLogResponse.getPath() != null) {
                MyLogUtils.Logd(retrofitManager.TAG, "uploadLogFile: 开始上传日志文件: " + createRobotLogResponse);
                observable = Companion.getInstance().getUploadImgApiService().uploadFile(createRobotLogResponse.getPath(), createFormData);
            } else {
                MyLogUtils.Logd(retrofitManager.TAG, "uploadLogFile: path == null");
                retrofitManager.replySrosNotification(false, "uploadLogFile: path == null");
                observable = null;
            }
        } else {
            MyLogUtils.Logd(retrofitManager.TAG, "uploadLogFile: 日志文件不存在");
            retrofitManager.replySrosNotification(false, "uploadLogFile: 日志文件不存在");
            observable = null;
        }
        return observable;
    }

    /* access modifiers changed from: private */
    public final void loadPatrolTaskFromServer(String str) {
        if (this.mRobotRealType == 5) {
            WuhanApiService wuhanApiService = null;
            try {
                wuhanApiService = Companion.getInstance().getWuHanApiService();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (wuhanApiService != null) {
                wuhanApiService.findPatrolTaskByTaskId(str, Companion.getInstance().getToken()).map($$Lambda$RetrofitManager$kvVXIUIJBibqQcKl5QRQIaHJ8aM.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new RetrofitManager$loadPatrolTaskFromServer$2());
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: loadPatrolTaskFromServer$lambda-30  reason: not valid java name */
    public static final Boolean m82loadPatrolTaskFromServer$lambda30(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "response");
        PatrolTaskBean patrolTaskBean = (PatrolTaskBean) new Gson().fromJson(new String(responseBody.bytes(), Charsets.UTF_8), PatrolTaskBean.class);
        MyLogUtils.Logd("SrosHandlerCallback: " + patrolTaskBean);
        RealmHelper instance = RealmHelper.getInstance();
        Realm newRealmInstance = instance.newRealmInstance();
        instance.insertPatrolTask(newRealmInstance, patrolTaskBean);
        newRealmInstance.close();
        return true;
    }

    /* access modifiers changed from: private */
    public final void saveVoiceCode2Local(String str) {
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0) && this.mRobotRealType == 5) {
            String str2 = this.TAG;
            MyLogUtils.Logd(str2, "saveVoiceCode2Local:" + str);
            MySpUtils.getInstance().putString(NetConstant.AUTHORIZATION_CODE, str);
        }
    }

    /* access modifiers changed from: private */
    public final void saveVideoCode2Local(String str) {
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0) && this.mRobotRealType == 5) {
            String str2 = this.TAG;
            MyLogUtils.Logd(str2, "saveVideoCode2Local:" + str);
            MySpUtils.getInstance().putString(NetConstant.AUTHORIZATION_VIDEO_CODE, str);
        }
    }

    /* access modifiers changed from: private */
    public final void deleteLocalVoiceCode() {
        MyLogUtils.Logd(this.TAG, "deleteLocalVoiceCode");
        MySpUtils.getInstance().putString(NetConstant.AUTHORIZATION_CODE, (String) null);
    }

    /* access modifiers changed from: private */
    public final void deleteLocalVideoCode() {
        MyLogUtils.Logd(this.TAG, "deleteLocalVideoCode");
        MySpUtils.getInstance().putString(NetConstant.AUTHORIZATION_VIDEO_CODE, (String) null);
    }

    public final void setToken(String str) {
        Intrinsics.checkNotNullParameter(str, "token");
        this.mToken.set(str);
    }

    public final String getToken() {
        return this.mToken.get();
    }

    public final boolean isTcpOnline() {
        return this.mIsTcpOnline;
    }

    public final void setIsTcpOnline(boolean z) {
        this.mIsTcpOnline = z;
    }

    public final void setIsBusinessMode(boolean z) {
        this.mIsBusinessMode = z;
    }

    public final boolean getIsBusinessMode() {
        return this.mIsBusinessMode;
    }

    public final void setArea(String str) {
        Intrinsics.checkNotNullParameter(str, "area");
        this.mArea = str;
    }

    public final String getArea() {
        return this.mArea;
    }

    public final String getProject() {
        return this.mProject;
    }

    public final void setProject(String str) {
        Intrinsics.checkNotNullParameter(str, "project");
        this.mProject = str;
    }

    public final void setWuHanUserName(String str) {
        this.mWuHanUserName = str;
    }

    public final String getWuHanUserName() {
        return this.mWuHanUserName;
    }

    public final void setWuHanPassWord(String str) {
        this.mWuHanPassWord = str;
    }

    public final String getWuHanPassWord() {
        return this.mWuHanPassWord;
    }

    public final String getCCID() {
        return this.mCCID;
    }

    public final void setCCID(String str) {
        Intrinsics.checkNotNullParameter(str, "ccid");
        this.mCCID = str;
        if (!TextUtils.isEmpty(this.mRobotId)) {
            postRobotInfo(this.mRobotId, this.mCCID, new RetrofitManager$setCCID$1());
        }
    }

    public final String getIMSI() {
        return this.mIMSI;
    }

    public final void setIMSI(String str) {
        Intrinsics.checkNotNullParameter(str, "imsi");
        this.mIMSI = str;
    }

    public final String getRobotId() {
        if (this.mRobotId == null) {
            return MySpUtils.getInstance().getString("SP_CONFIG_ROBOT_ID", this.mRobotId);
        }
        return this.mRobotId;
    }

    public final void setRobotId(String str) {
        Intrinsics.checkNotNullParameter(str, "robotId");
        String str2 = this.TAG;
        MyLogUtils.Logd(str2, "RetrofitManager setRobotId=" + str);
        if (TextUtils.isEmpty(str)) {
            MyLogUtils.Loge(this.TAG, "RetrofitManager setRobotId is empty");
            return;
        }
        this.mRobotId = str;
        MySpUtils.getInstance().putString("SP_CONFIG_ROBOT_ID", str);
    }

    public final int getRobotRealType() {
        return MySpUtils.getInstance().getInt("SP_CONFIG_ROBOT_TYPE", this.mRobotRealType);
    }

    public final void setRobotRealType(int i) {
        this.mRobotRealType = i;
        MySpUtils.getInstance().putInt("SP_CONFIG_ROBOT_TYPE", i);
    }

    public final String getRobotNum() {
        return MySpUtils.getInstance().getString("SP_CONFIG_ROBOT_NUM", this.mRobotNum);
    }

    public final void setRobotNum(String str) {
        Intrinsics.checkNotNullParameter(str, "robotNum");
        this.mRobotNum = str;
        MySpUtils.getInstance().putString("SP_CONFIG_ROBOT_NUM", str);
    }

    public final boolean isRealTimeUpload() {
        return this.mIsRealtimeUpload;
    }

    public final void onUnsubscribe() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            Intrinsics.checkNotNull(compositeDisposable);
            compositeDisposable.clear();
        }
    }

    public final void addSubscription(Disposable disposable) {
        CompositeDisposable compositeDisposable;
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        if (disposable != null && (compositeDisposable = this.mCompositeDisposable) != null) {
            compositeDisposable.add(disposable);
        }
    }

    public final void resetData() {
        setWuHanUserName((String) null);
        setWuHanPassWord((String) null);
        this.mToken.set((Object) null);
        this.mProject = null;
        this.mArea = null;
    }

    private final void startTcpService() {
        MyLogUtils.Logd(this.TAG, "read startTcpService");
        this.mStartTcpServiceIntent = new Intent(ContextUtil.getContext(), TcpService.class);
        ContextUtil.getContext().startService(this.mStartTcpServiceIntent);
    }

    public final void stopTcpService() {
        if (this.mStartTcpServiceIntent != null) {
            MyLogUtils.Logd(this.TAG, "read stopTcpService");
            ContextUtil.getContext().stopService(this.mStartTcpServiceIntent);
        }
    }

    public final void release() {
        MyLogUtils.Logd(this.TAG, "RetrofitManager release");
        this.retrofit = null;
        onUnsubscribe();
        stopTcpService();
    }

    public final VersionBean getMVersionBean() {
        return this.mVersionBean;
    }

    public final void setMVersionBean(VersionBean versionBean) {
        this.mVersionBean = versionBean;
    }

    public final String getMNewApkFilePath() {
        return this.mNewApkFilePath;
    }

    public final void setMNewApkFilePath(String str) {
        this.mNewApkFilePath = str;
    }

    public final int getCurrentRegetVersionTimes() {
        return this.currentRegetVersionTimes;
    }

    public final void setCurrentRegetVersionTimes(int i) {
        this.currentRegetVersionTimes = i;
    }

    public final void robotAllow(String str, Observer<ResponseBody> observer) {
        Observable<ResponseBody> robotAllow;
        Observable<ResponseBody> retryWhen;
        Observable<ResponseBody> timeout;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        Intrinsics.checkNotNullParameter(observer, "observer");
        setPropertyDomain(str);
        RequestBody create = RequestBody.Companion.create("{\"id\": \"" + getRobotId() + "\"}", MediaType.Companion.get("application/json;charset=utf-8"));
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (robotAllow = wuHanApiService.robotAllow(create)) != null && (retryWhen = robotAllow.retryWhen(new RetryWithDelay(40, 3000))) != null && (timeout = retryWhen.timeout(120000, TimeUnit.MILLISECONDS)) != null && (subscribeOn = timeout.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) observer);
        }
    }

    public final void getAdLists(String str, String str2, BaseObserver<JsonArray> baseObserver) {
        Observable<JsonArray> adLists;
        Observable<JsonArray> subscribeOn;
        Observable<JsonArray> observeOn;
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (adLists = wuHanApiService.getAdLists(str, str2)) != null && (subscribeOn = adLists.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super JsonArray>) baseObserver);
        }
    }

    public final void setPropertyDomain(String str) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        HttpUrl fetchDomain = RetrofitUrlManager.getInstance().fetchDomain(Api.DOMAIN_NAME_PROPERTY);
        if (fetchDomain == null || !Intrinsics.areEqual((Object) fetchDomain.toString(), (Object) str)) {
            try {
                RetrofitUrlManager.getInstance().putDomain(Api.DOMAIN_NAME_PROPERTY, str);
            } catch (Exception unused) {
                MyLogUtils.Loge("==不规范的网络地址===");
            }
        }
    }

    public final void getLatestVersion(long j) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                public final void run() {
                    RetrofitManager.m60getLatestVersion$lambda34(RetrofitManager.this);
                }
            }, j);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getLatestVersion$lambda-34  reason: not valid java name */
    public static final void m60getLatestVersion$lambda34(RetrofitManager retrofitManager) {
        Intrinsics.checkNotNullParameter(retrofitManager, "this$0");
        if (retrofitManager.getRobotId() != null && retrofitManager.getToken() != null) {
            String robotId = retrofitManager.getRobotId();
            Intrinsics.checkNotNull(robotId);
            String token = retrofitManager.getToken();
            Intrinsics.checkNotNull(token);
            retrofitManager.getLatestVersion(robotId, token);
        }
    }

    public final void getLatestVersion(String str, String str2) {
        Observable<ResponseBody> latestVersion;
        Observable<ResponseBody> timeout;
        Observable<ResponseBody> subscribeOn;
        Intrinsics.checkNotNullParameter(str, "robotNum");
        Intrinsics.checkNotNullParameter(str2, "token");
        this.currentRegetVersionTimes++;
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (latestVersion = wuHanApiService.getLatestVersion(str2, str)) != null && (timeout = latestVersion.timeout(this.SERVER_TIMEOUT, TimeUnit.MILLISECONDS)) != null && (subscribeOn = timeout.subscribeOn(Schedulers.io())) != null) {
            subscribeOn.subscribe((Observer<? super ResponseBody>) new RetrofitManager$getLatestVersion$2(this));
        }
    }

    public final void updateVersionRecord(UpdateVersionBean updateVersionBean, BaseObserver<UpdateVersionRecordResponse> baseObserver) {
        Observable<UpdateVersionRecordResponse> updateVersionRecord;
        Observable<UpdateVersionRecordResponse> retry;
        Observable<UpdateVersionRecordResponse> subscribeOn;
        Observable<UpdateVersionRecordResponse> observeOn;
        Intrinsics.checkNotNullParameter(updateVersionBean, "updateVersionBean");
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        String json = new Gson().toJson((Object) updateVersionBean);
        Intrinsics.checkNotNullExpressionValue(json, "gson.toJson(updateVersionBean)");
        RequestBody create = RequestBody.Companion.create(MediaType.Companion.parse("application/json; charset=utf-8"), json);
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (updateVersionRecord = wuHanApiService.updateVersionRecord(getToken(), create)) != null && (retry = updateVersionRecord.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super UpdateVersionRecordResponse>) baseObserver);
        }
    }

    public final void getVersionById(String str, BaseObserver<VersionBean> baseObserver) {
        Observable<VersionBean> versionById;
        Observable<VersionBean> retry;
        Observable<VersionBean> subscribeOn;
        Observable<VersionBean> observeOn;
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (versionById = wuHanApiService.getVersionById(str, getToken())) != null && (retry = versionById.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super VersionBean>) baseObserver);
        }
    }

    public final void addDefaultApkCallBack() {
        ApkDownloadInstallUtils.Companion.instance().setDownloadApkCallBack(new RetrofitManager$addDefaultApkCallBack$1(this));
    }

    public final String getNewApkFilePath() {
        return this.mNewApkFilePath;
    }

    public final VersionBean getApkNewVersion() {
        return this.mVersionBean;
    }

    public final void setLoginListener(Function4<? super Boolean, ? super Boolean, ? super Boolean, ? super Integer, Unit> function4) {
        this.mLoginListener = function4;
    }

    public final void setOnRefreshAdListener(Function0<Unit> function0) {
        this.mRefreshAdListener = function0;
    }

    public final void verifyInviteCode(String str, Function1<? super RecordResponse, Unit> function1) {
        WuhanApiService wuHanApiService;
        Observable<ResponseBody> retry;
        Observable<R> map;
        Observable<R> subscribeOn;
        Observable<R> observeOn;
        Intrinsics.checkNotNullParameter(str, TombstoneParser.keyCode);
        Intrinsics.checkNotNullParameter(function1, "callback");
        CharSequence token = getToken();
        if (!(token == null || token.length() == 0) && (wuHanApiService = getWuHanApiService()) != null) {
            String token2 = getToken();
            if (token2 == null) {
                token2 = "";
            }
            Observable<ResponseBody> verifyCode = wuHanApiService.verifyCode(str, token2);
            if (verifyCode != null && (retry = verifyCode.retry(2)) != null && (map = retry.map(new RetrofitManager$verifyInviteCode$1())) != null && (subscribeOn = map.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                observeOn.subscribe((Observer<? super R>) new RetrofitManager$verifyInviteCode$2(this, function1));
            }
        }
    }

    public final void setAdData() {
        Function0<Unit> function0 = this.mRefreshAdListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void findCompanyById(String str, Function1<? super CompanyResponse, Unit> function1) {
        WuhanApiService wuHanApiService;
        Observable<CompanyResponse> subscribeOn;
        Observable<CompanyResponse> observeOn;
        Intrinsics.checkNotNullParameter(str, "companyId");
        Intrinsics.checkNotNullParameter(function1, "callback");
        CharSequence token = getToken();
        if (!(token == null || token.length() == 0) && (wuHanApiService = getWuHanApiService()) != null) {
            String token2 = getToken();
            if (token2 == null) {
                token2 = "";
            }
            Observable<CompanyResponse> findCompanyById = wuHanApiService.findCompanyById(str, token2);
            if (findCompanyById != null && (subscribeOn = findCompanyById.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                observeOn.subscribe((Observer<? super CompanyResponse>) new RetrofitManager$findCompanyById$1(this, function1));
            }
        }
    }

    private final HashMap<String, String> generateRequestMap() {
        String robotId = getRobotId();
        String token = getToken();
        String project = getProject();
        String wuHanUserName = getWuHanUserName();
        String area = getArea();
        CharSequence charSequence = robotId;
        boolean z = false;
        if (!(charSequence == null || charSequence.length() == 0)) {
            CharSequence charSequence2 = token;
            if (!(charSequence2 == null || charSequence2.length() == 0)) {
                CharSequence charSequence3 = project;
                if (!(charSequence3 == null || charSequence3.length() == 0)) {
                    CharSequence charSequence4 = wuHanUserName;
                    if (charSequence4 == null || charSequence4.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        this.requestMap = hashMap;
                        Intrinsics.checkNotNull(hashMap);
                        hashMap.put("deviceid", robotId);
                        HashMap<String, String> hashMap2 = this.requestMap;
                        Intrinsics.checkNotNull(hashMap2);
                        hashMap2.put("token", token);
                        HashMap<String, String> hashMap3 = this.requestMap;
                        Intrinsics.checkNotNull(hashMap3);
                        hashMap3.put("user", wuHanUserName);
                        HashMap<String, String> hashMap4 = this.requestMap;
                        Intrinsics.checkNotNull(hashMap4);
                        hashMap4.put("project", project);
                        HashMap<String, String> hashMap5 = this.requestMap;
                        Intrinsics.checkNotNull(hashMap5);
                        hashMap5.put("robottype", String.valueOf(NetConstant.INSTANCE.getROBOT_TYPE()));
                        if (area != null) {
                            HashMap<String, String> hashMap6 = this.requestMap;
                            Intrinsics.checkNotNull(hashMap6);
                            hashMap6.put("area", area);
                        }
                        return this.requestMap;
                    }
                }
            }
        }
        String str = this.TAG;
        MyLogUtils.Logw(str, "generateRequestMap failed,token or projectId is null,token=" + token + ",projectId=" + project + ",user=" + wuHanUserName + ",area=" + area);
        return null;
    }

    public final HashMap<String, String> getRequestMap() {
        HashMap<String, String> hashMap = this.requestMap;
        return hashMap == null ? generateRequestMap() : hashMap;
    }

    public final String getBaseUrl(String str) {
        CharSequence charSequence = str;
        if (!TextUtils.isEmpty(charSequence)) {
            Matcher matcher = Pattern.compile(RegularExpressionConstants.REGEXP_DISCERN_DOMAIN_IP_PORT).matcher(charSequence);
            if (matcher.find()) {
                String group = matcher.group();
                Intrinsics.checkNotNullExpressionValue(group, "matcher.group()");
                return group;
            }
        }
        return "";
    }

    public final boolean isNetInitSuccess() {
        return !TextUtils.isEmpty(getWuHanUserName()) && !TextUtils.isEmpty(getToken()) && !TextUtils.isEmpty(getProject()) && !TextUtils.isEmpty(getRobotId());
    }

    public final boolean isIpIllegal() {
        return getSemanticIntentBaseUrl() == null;
    }

    private final void bindSimInfo() {
        getSimInfo(new RetrofitManager$bindSimInfo$1(this));
    }

    private final void getSimInfo(BaseObserver<ResponseBody> baseObserver) {
        Observable<ResponseBody> simInfo;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        String baseUrl = getBaseUrl(NetConstant.WATER_GET_SIM_INFO);
        HttpUrl fetchDomain = RetrofitUrlManager.getInstance().fetchDomain(Api.DOMAIN_NAME_OTHER);
        if (fetchDomain == null || !Intrinsics.areEqual((Object) fetchDomain.toString(), (Object) baseUrl)) {
            RetrofitUrlManager.getInstance().putDomain(Api.DOMAIN_NAME_OTHER, baseUrl);
        }
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (simInfo = wuHanApiService.getSimInfo(NetConstant.WATER_GET_SIM_INFO)) != null && (subscribeOn = simInfo.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) baseObserver);
        }
    }

    public final void postRobotInfo(String str, String str2, BaseObserver<ResponseBody> baseObserver) {
        Observable<ResponseBody> postRobotInfo;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("robot", str);
        jsonObject.addProperty("iccid", str2);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
        String jsonObject2 = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject2, "jsonObject.toString()");
        RequestBody create = companion.create(parse, jsonObject2);
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (postRobotInfo = wuHanApiService.postRobotInfo(create, getToken())) != null && (subscribeOn = postRobotInfo.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) baseObserver);
        }
    }

    public final void getSimDetail(String str, BaseObserver<ResponseBody> baseObserver) {
        Observable<ResponseBody> simsDetail;
        Observable<ResponseBody> retryWhen;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(str, "iccid");
        Intrinsics.checkNotNullParameter(baseObserver, "observer");
        WuhanApiService wuHanApiService = getWuHanApiService();
        if (wuHanApiService != null && (simsDetail = wuHanApiService.getSimsDetail(getToken(), str)) != null && (retryWhen = simsDetail.retryWhen(new RetryWithDelay(3, 3000))) != null && (subscribeOn = retryWhen.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observeOn.subscribe((Observer<? super ResponseBody>) baseObserver);
        }
    }

    public final void getNewsList() {
        Observable<ResponseBody> newsList;
        Observable<ResponseBody> retry;
        Observable<ResponseBody> subscribeOn;
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str = requestMap2.get("deviceid");
        if (str != null) {
            String str2 = str;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str3 = requestMap3.get("project");
            if (str3 != null) {
                String str4 = str3;
                HashMap<String, String> requestMap4 = getRequestMap();
                Intrinsics.checkNotNull(requestMap4);
                String str5 = requestMap4.get("token");
                if (str5 != null) {
                    String str6 = str5;
                    WuhanApiService wuHanApiService = getWuHanApiService();
                    if (wuHanApiService != null && (newsList = wuHanApiService.getNewsList(str6, str2, str4)) != null && (retry = newsList.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null) {
                        subscribeOn.subscribe((Observer<? super ResponseBody>) new RetrofitManager$getNewsList$1(this));
                        return;
                    }
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void getNewsSpec(String str) {
        Observable<ResponseBody> newsSpec;
        Observable<ResponseBody> retry;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Intrinsics.checkNotNullParameter(str, "id");
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str2 = requestMap2.get("project");
        if (str2 != null) {
            String str3 = str2;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str4 = requestMap3.get("token");
            if (str4 != null) {
                String str5 = str4;
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (newsSpec = wuHanApiService.getNewsSpec(str5, str, str3)) != null && (retry = newsSpec.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                    observeOn.subscribe((Observer<? super ResponseBody>) new RetrofitManager$getNewsSpec$1(this));
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void getHotelActivity() {
        Observable<ResponseBody> hotelActivitys;
        Observable<ResponseBody> retry;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str = requestMap2.get("project");
        if (str != null) {
            String str2 = str;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str3 = requestMap3.get("token");
            if (str3 != null) {
                String str4 = str3;
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (hotelActivitys = wuHanApiService.getHotelActivitys(str4, str2)) != null && (retry = hotelActivitys.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                    observeOn.subscribe((Observer<? super ResponseBody>) new RetrofitManager$getHotelActivity$1(this));
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void registerHotelActivity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Observable<ResponseBody> registHotelData;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        String str9 = str;
        String str10 = str2;
        String str11 = str3;
        String str12 = str4;
        String str13 = str5;
        String str14 = str6;
        String str15 = str7;
        String str16 = str8;
        Intrinsics.checkNotNullParameter(str9, "registType");
        Intrinsics.checkNotNullParameter(str11, "roomId");
        Intrinsics.checkNotNullParameter(str12, "roomName");
        Intrinsics.checkNotNullParameter(str13, "phone");
        Intrinsics.checkNotNullParameter(str14, "peopleNum");
        Intrinsics.checkNotNullParameter(str15, "remark");
        Intrinsics.checkNotNullParameter(str16, "registerTypeName");
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str17 = requestMap2.get("project");
        if (str17 != null) {
            String str18 = str17;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str19 = "null cannot be cast to non-null type kotlin.String";
            String str20 = requestMap3.get("token");
            if (str20 != null) {
                JsonObject jsonObject = new JsonObject();
                String str21 = str20;
                jsonObject.addProperty("projectId", str18);
                jsonObject.addProperty("registerType", str9);
                CharSequence charSequence = str10;
                boolean z = false;
                if (!(charSequence == null || charSequence.length() == 0)) {
                    jsonObject.addProperty("registerTypeId", str10);
                    jsonObject.addProperty("registerTypeName", str16);
                }
                jsonObject.addProperty("roomId", str11);
                jsonObject.addProperty("roomName", str12);
                jsonObject.addProperty("phone", str13);
                if (str14.length() == 0) {
                    z = true;
                }
                jsonObject.addProperty("number", (Number) Long.valueOf(!z ? Long.parseLong(str6) : 0));
                jsonObject.addProperty("description", str15);
                String str22 = this.TAG;
                MyLogUtils.Loge(str22, jsonObject.toString() + "...");
                RequestBody.Companion companion = RequestBody.Companion;
                MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
                String jsonObject2 = jsonObject.toString();
                Intrinsics.checkNotNullExpressionValue(jsonObject2, "jsonObject.toString()");
                RequestBody create = companion.create(parse, jsonObject2);
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (registHotelData = wuHanApiService.registHotelData(create, str21)) != null && (subscribeOn = registHotelData.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                    observeOn.subscribe((Observer<? super ResponseBody>) new RetrofitManager$registerHotelActivity$1(this));
                    return;
                }
                return;
            }
            throw new NullPointerException(str19);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void registerHotel(String str, String str2, String str3, String str4, String str5, Consumer<String> consumer, Consumer<Throwable> consumer2) {
        Observable<ResponseBody> registHotelData;
        Observable<ResponseBody> subscribeOn;
        Observable<ResponseBody> observeOn;
        Observable<R> map;
        Intrinsics.checkNotNullParameter(str, "registType");
        Intrinsics.checkNotNullParameter(str2, "roomId");
        Intrinsics.checkNotNullParameter(str3, "roomName");
        Intrinsics.checkNotNullParameter(str4, "phone");
        Intrinsics.checkNotNullParameter(str5, "remark");
        Intrinsics.checkNotNullParameter(consumer, "sucess");
        Intrinsics.checkNotNullParameter(consumer2, NotificationCompat.CATEGORY_ERROR);
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str6 = requestMap2.get("project");
        if (str6 != null) {
            String str7 = str6;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str8 = requestMap3.get("token");
            if (str8 != null) {
                String str9 = str8;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("projectId", str7);
                jsonObject.addProperty("registerType", str);
                jsonObject.addProperty("roomId", str2);
                jsonObject.addProperty("roomName", str3);
                jsonObject.addProperty("phone", str4);
                jsonObject.addProperty("number", (Number) 1);
                jsonObject.addProperty("description", str5);
                MyLogUtils.Loge(this.TAG, jsonObject.toString());
                RequestBody.Companion companion = RequestBody.Companion;
                MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
                String jsonObject2 = jsonObject.toString();
                Intrinsics.checkNotNullExpressionValue(jsonObject2, "jsonObject.toString()");
                RequestBody create = companion.create(parse, jsonObject2);
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (registHotelData = wuHanApiService.registHotelData(create, str9)) != null && (subscribeOn = registHotelData.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null && (map = observeOn.map($$Lambda$RetrofitManager$GJTegc_rNGhhVyb_33XH0oGiWA.INSTANCE)) != null) {
                    map.subscribe(consumer, consumer2);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    /* access modifiers changed from: private */
    /* renamed from: registerHotel$lambda-35  reason: not valid java name */
    public static final String m83registerHotel$lambda35(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "it");
        return new String(responseBody.bytes(), Charsets.UTF_8);
    }

    public final void getHotelFloor(Consumer<FloorBean> consumer, Consumer<Throwable> consumer2) {
        Observable<FloorBean> hotelFloor;
        Observable<FloorBean> retry;
        Observable<FloorBean> subscribeOn;
        Observable<FloorBean> observeOn;
        Intrinsics.checkNotNullParameter(consumer, "success");
        Intrinsics.checkNotNullParameter(consumer2, NotificationCompat.CATEGORY_ERROR);
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str = requestMap2.get("project");
        if (str != null) {
            String str2 = str;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str3 = requestMap3.get("token");
            if (str3 != null) {
                String str4 = str3;
                MyLogUtils.Loge("dan", "酒店场景 —— projectId : " + str2 + ",token : " + str4);
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (hotelFloor = wuHanApiService.getHotelFloor(str4, str2)) != null && (retry = hotelFloor.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                    observeOn.subscribe(consumer, consumer2);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void getRoomData(Consumer<RoomBean> consumer, Consumer<Throwable> consumer2, String str) {
        Observable<RoomBean> room;
        Observable<RoomBean> retry;
        Observable<RoomBean> subscribeOn;
        Observable<RoomBean> observeOn;
        Intrinsics.checkNotNullParameter(consumer, "success");
        Intrinsics.checkNotNullParameter(consumer2, NotificationCompat.CATEGORY_ERROR);
        Intrinsics.checkNotNullParameter(str, "floorId");
        HashMap<String, String> requestMap2 = getRequestMap();
        Intrinsics.checkNotNull(requestMap2);
        String str2 = requestMap2.get("project");
        if (str2 != null) {
            String str3 = str2;
            HashMap<String, String> requestMap3 = getRequestMap();
            Intrinsics.checkNotNull(requestMap3);
            String str4 = requestMap3.get("token");
            if (str4 != null) {
                String str5 = str4;
                WuhanApiService wuHanApiService = getWuHanApiService();
                if (wuHanApiService != null && (room = wuHanApiService.getRoom(str5, str3, str)) != null && (retry = room.retry(2)) != null && (subscribeOn = retry.subscribeOn(Schedulers.io())) != null && (observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                    observeOn.subscribe(consumer, consumer2);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final void getTactics() {
        if (!TextUtils.isEmpty(getToken()) && getWuHanApiService() != null) {
            WuhanApiService wuHanApiService = getWuHanApiService();
            Intrinsics.checkNotNull(wuHanApiService);
            Observable<R> map = wuHanApiService.getTactics("default", getToken()).map($$Lambda$RetrofitManager$jCm5H84Cw8uYSthDEHz7KcWtyvA.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(map, "getWuHanApiService()!!.g…result\n                })");
            WuhanApiService wuHanApiService2 = getWuHanApiService();
            Intrinsics.checkNotNull(wuHanApiService2);
            Observable<R> map2 = wuHanApiService2.getTactics(SchedulerSupport.CUSTOM, getToken()).map($$Lambda$RetrofitManager$cMn7Wy6QMXAwgL_9bTwXPZos3g.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(map2, "getWuHanApiService()!!.g…result\n                })");
            Observable.merge(map, map2).subscribeOn(Schedulers.io()).subscribe(new RetrofitManager$getTactics$1());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getTactics$lambda-36  reason: not valid java name */
    public static final List m66getTactics$lambda36(JsonArray jsonArray) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Log.d(CLOCK_IN_HELPER_TAG, "default" + jsonArray);
        List arrayList = new ArrayList();
        Object fromJson = GsonUtils.fromJson(jsonArray.toString(), new RetrofitManager$getTactics$defaultTactics$1$tactics$1().getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson<List<DefaultTac…faultTactics>>() {}.type)");
        List list = (List) fromJson;
        int size = list.size();
        int i = 0;
        while (i < size) {
            Tactics tactics = ((DefaultTactics) list.get(i)).toTactics();
            Intrinsics.checkNotNullExpressionValue(tactics, "tactics[index].toTactics()");
            long timeMinute = CustomTactics.getTimeMinute(tactics.getStart());
            long timeMinute2 = CustomTactics.getTimeMinute(tactics.getEnd());
            tactics.setStartTimeOpen(i == 0 ? 0 : timeMinute - ((long) 30));
            long j = (long) 30;
            tactics.setStartTimeClose(timeMinute + j);
            tactics.setEndTimeOpen(timeMinute2 - j);
            tactics.setEndTimeClose(i == list.size() + -1 ? 2147483647L : timeMinute2 + j);
            arrayList.add(tactics);
            i++;
        }
        Log.d(CLOCK_IN_HELPER_TAG, "default end" + GsonUtils.toJson(arrayList));
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: getTactics$lambda-37  reason: not valid java name */
    public static final List m67getTactics$lambda37(JsonArray jsonArray) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Log.d(CLOCK_IN_HELPER_TAG, SchedulerSupport.CUSTOM + jsonArray);
        Object fromJson = GsonUtils.fromJson(jsonArray.toString(), new RetrofitManager$getTactics$customTactics$1$tactics$1().getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson<List<CustomTact…ustomTactics>>() {}.type)");
        List arrayList = new ArrayList();
        for (CustomTactics tactics : (List) fromJson) {
            List<Tactics> tactics2 = tactics.getTactics();
            Intrinsics.checkNotNullExpressionValue(tactics2, "tactic.tactics");
            arrayList.addAll(tactics2);
        }
        Log.d(CLOCK_IN_HELPER_TAG, "custom end" + GsonUtils.toJson(arrayList));
        return arrayList;
    }

    public final void uploadTaskLogcat(Task task) {
        Intrinsics.checkNotNullParameter(task, "mTask");
        List<PatrolPathBean> arrayList = new ArrayList<>();
        PatrolPathBean patrolPathBean = new PatrolPathBean();
        List arrayList2 = new ArrayList();
        arrayList2.add(new PatrolActionBean(0, Long.valueOf(System.currentTimeMillis())));
        PatrolPointBean patrolPointBean = new PatrolPointBean();
        patrolPointBean.setName("aaa");
        patrolPointBean.setBegin(Long.valueOf(task.getStatisticRunStartTime()));
        patrolPointBean.setEnd(Long.valueOf(task.getStatisticRunEndTime()));
        patrolPointBean.setActions(arrayList2);
        List arrayList3 = new ArrayList();
        Double valueOf = Double.valueOf(0.53d);
        arrayList3.add(valueOf);
        Double valueOf2 = Double.valueOf(0.46d);
        arrayList3.add(valueOf2);
        patrolPointBean.setLoc(arrayList3);
        List arrayList4 = new ArrayList();
        PatrolReportBean patrolReportBean = new PatrolReportBean();
        patrolReportBean.setId("001");
        patrolReportBean.setName("001");
        patrolReportBean.setType("中途停止巡更");
        patrolReportBean.setTime(Long.valueOf(System.currentTimeMillis()));
        arrayList4.add(patrolReportBean);
        patrolPointBean.setAlarms(arrayList4);
        patrolPathBean.setStart(patrolPointBean);
        patrolPathBean.setEnd(patrolPointBean);
        arrayList.add(patrolPathBean);
        for (PatrolPathBean patrolPathBean2 : arrayList) {
            PatrolRouteBean path = patrolPathBean2.getPath();
            if (path == null) {
                path = new PatrolRouteBean();
            }
            List arrayList5 = new ArrayList();
            arrayList5.add(valueOf);
            arrayList5.add(valueOf2);
            path.setLoc_start(arrayList5);
            path.setLoc_end(arrayList5);
            path.setBegin(Long.valueOf(task.getStatisticRunStartTime()));
            path.setEnd(Long.valueOf(task.getStatisticRunEndTime()));
            path.setActions(new ArrayList());
            path.setAlarms(new ArrayList());
            path.setSpeed(0.5f);
            patrolPathBean2.setPath(path);
        }
        PatrolRecordBean patrolRecordBean = new PatrolRecordBean();
        patrolRecordBean.setRobot(AppSpUtil.getInstance().getRobotNumber());
        patrolRecordBean.setMap(task.getStatisticRunKm() + "");
        patrolRecordBean.setTask(task.getTaskName());
        patrolRecordBean.setBegin(Long.valueOf(task.getStatisticRunStartTime()));
        patrolRecordBean.setEnd(Long.valueOf(task.getStatisticRunEndTime()));
        PathBean pathBean = task.getPathBeanList().get(0);
        Intrinsics.checkNotNull(pathBean);
        patrolRecordBean.setLine(pathBean.getPathName());
        patrolRecordBean.setPaths(arrayList);
        List arrayList6 = new ArrayList();
        for (PatrolPathBean patrolPathBean3 : arrayList) {
            List<PatrolReportBean> alarms = patrolPathBean3.getStart().getAlarms();
            List<PatrolReportBean> alarms2 = patrolPathBean3.getEnd().getAlarms();
            if (!(alarms == null || alarms.size() == 0)) {
                arrayList6.addAll(alarms);
            }
            if (!(alarms2 == null || alarms2.size() == 0)) {
                arrayList6.addAll(alarms2);
            }
        }
        patrolRecordBean.setState("normal");
        if (!TextUtils.isEmpty(Companion.getInstance().getToken())) {
            String json = new Gson().toJson((Object) patrolRecordBean);
            RequestBody.Companion companion = RequestBody.Companion;
            MediaType parse = MediaType.Companion.parse("application/json; charset=utf-8");
            Intrinsics.checkNotNullExpressionValue(json, "json");
            RequestBody create = companion.create(parse, json);
            WuhanApiService wuHanApiService = Companion.getInstance().getWuHanApiService();
            Intrinsics.checkNotNull(wuHanApiService);
            String token = Companion.getInstance().getToken();
            Intrinsics.checkNotNull(token);
            wuHanApiService.uploadPatrolRecord(create, token).subscribeOn(Schedulers.io()).delay(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new RetrofitManager$uploadTaskLogcat$1());
        }
    }
}
