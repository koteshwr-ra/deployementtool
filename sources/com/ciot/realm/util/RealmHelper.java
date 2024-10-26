package com.ciot.realm.util;

import android.util.Log;
import com.ciot.base.util.DateUtils;
import com.ciot.realm.db.EmployeeBean;
import com.ciot.realm.db.MarkerPoint;
import com.ciot.realm.db.Task;
import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.BeginBean;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.ad.EndBean;
import com.ciot.realm.db.ad.GetAdListsBeanR;
import com.ciot.realm.db.ad.HorseRaceLampsBean;
import com.ciot.realm.db.ad.ResourcesBean;
import com.ciot.realm.db.ad.TimesBean;
import com.ciot.realm.db.listener.OnTimerTaskListener;
import com.ciot.realm.db.patrol.FloorOriginBean;
import com.ciot.realm.db.patrol.PathBean;
import com.ciot.realm.db.patrol.PatrolTaskBean;
import com.ciot.realm.db.patrol.TurnstileBean;
import com.ciot.realm.db.patrol.WaterPathBean;
import com.ciot.realm.db.timer.TimerReceptionTaskBean;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.realm.ImportFlag;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;

public class RealmHelper {
    private static final String DATABASE_NAME = "RobotControlService.realm";
    private static final String EXECUTE_TIMER_TASK = "1";
    public static final int SCHEMA_VERSION = 7;
    private static volatile RealmHelper mRealmHelper;
    private Disposable mDisposable;
    private RealmConfiguration mRealmConfiguration;

    public static RealmHelper getInstance() {
        if (mRealmHelper == null) {
            synchronized (RealmHelper.class) {
                if (mRealmHelper == null) {
                    mRealmHelper = new RealmHelper();
                }
            }
        }
        return mRealmHelper;
    }

    public Realm newRealmInstance() {
        if (this.mRealmConfiguration == null) {
            synchronized (RealmHelper.class) {
                if (this.mRealmConfiguration == null) {
                    this.mRealmConfiguration = new RealmConfiguration.Builder().name("RobotControlService.realm").schemaVersion(7).deleteRealmIfMigrationNeeded().build();
                }
            }
        }
        return Realm.getInstance(this.mRealmConfiguration);
    }

    public void saveWaterPath(WaterPathBean waterPathBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(waterPathBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<WaterPathBean> findWaterPath() {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        List<WaterPathBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(WaterPathBean.class).findAll().sort("id", Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void deleteWaterPath(final int i) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransaction(new Realm.Transaction() {
            public void execute(Realm realm) {
                WaterPathBean waterPathBean = (WaterPathBean) realm.where(WaterPathBean.class).equalTo("id", Integer.valueOf(i)).findFirst();
                if (waterPathBean != null) {
                    waterPathBean.deleteFromRealm();
                }
            }
        });
        newRealmInstance.close();
    }

    public boolean saveTask(Task task) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(task, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        return true;
    }

    public boolean deleteTask(Task task) {
        Realm newRealmInstance = newRealmInstance();
        final Task task2 = (Task) newRealmInstance.where(Task.class).equalTo("taskid", task.getTaskid()).findFirst();
        if (task2 == null) {
            return false;
        }
        newRealmInstance.executeTransaction(new Realm.Transaction() {
            public void execute(Realm realm) {
                task2.deleteFromRealm();
            }
        });
        newRealmInstance.close();
        return true;
    }

    public boolean deleteAllTask() {
        newRealmInstance().executeTransaction(new Realm.Transaction() {
            public void execute(Realm realm) {
                realm.delete(Task.class);
            }
        });
        return true;
    }

    public List<Task> findAllTasks() {
        Realm newRealmInstance = newRealmInstance();
        List<Task> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(Task.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<Task> findTimerTaskList() {
        Realm newRealmInstance = newRealmInstance();
        List<Task> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(Task.class).equalTo("taskStatus", (Boolean) true).findAll().sort("startTime", Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void findTimeTasks(String str, final int i, final OnTimerTaskListener onTimerTaskListener) {
        Disposable disposable = this.mDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.mDisposable.dispose();
        }
        final Realm newRealmInstance = newRealmInstance();
        this.mDisposable = newRealmInstance.where(Task.class).equalTo("taskStatus", (Boolean) true).equalTo("startTime", str).findAllAsync().asFlowable().filter(new Predicate<RealmResults<Task>>() {
            public boolean test(RealmResults<Task> realmResults) {
                return realmResults.isLoaded();
            }
        }).map(new Function<RealmResults<Task>, Task>() {
            public Task apply(RealmResults<Task> realmResults) {
                return RealmHelper.this.getPerformTask(newRealmInstance.copyFromRealm(realmResults), i);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Task>() {
            public void accept(Task task) {
                onTimerTaskListener.onTimerTask(task);
                newRealmInstance.close();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                newRealmInstance.close();
            }
        });
    }

    /* access modifiers changed from: private */
    public Task getPerformTask(List<Task> list, int i) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            Task task = list.get(size);
            if (task != null && "1".equals(task.getWeekly().substring(i - 1, i))) {
                return task;
            }
        }
        return null;
    }

    public Task findSingleTask(String str) {
        Realm newRealmInstance = newRealmInstance();
        Task task = (Task) newRealmInstance.where(Task.class).equalTo("taskid", str).findFirst();
        Task task2 = task != null ? (Task) newRealmInstance.copyFromRealm(task) : null;
        newRealmInstance.close();
        return task2;
    }

    public void saveMarkerPoint(MarkerPoint markerPoint) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(markerPoint, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<MarkerPoint> findMarkerPointByMapName(String str) {
        Realm newRealmInstance = newRealmInstance();
        List<MarkerPoint> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(MarkerPoint.class).equalTo("mapName", str).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public MarkerPoint findMarkerPointByPositionName(String str) {
        Realm newRealmInstance = newRealmInstance();
        MarkerPoint markerPoint = (MarkerPoint) newRealmInstance.where(MarkerPoint.class).equalTo("positionName", str).findFirst();
        MarkerPoint markerPoint2 = markerPoint != null ? (MarkerPoint) newRealmInstance.copyFromRealm(markerPoint) : null;
        newRealmInstance.close();
        return markerPoint2;
    }

    public boolean deleteMarkerPoint(String str, String str2) {
        Realm newRealmInstance = newRealmInstance();
        final MarkerPoint markerPoint = (MarkerPoint) newRealmInstance.where(MarkerPoint.class).equalTo("positionName", str).equalTo("mapName", str2).findFirst();
        if (markerPoint == null) {
            return false;
        }
        newRealmInstance.executeTransaction(new Realm.Transaction() {
            public void execute(Realm realm) {
                markerPoint.deleteFromRealm();
            }
        });
        newRealmInstance.close();
        return true;
    }

    public void addFloorOrigin(FloorOriginBean floorOriginBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(floorOriginBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<FloorOriginBean> findAllFloorOrigin() {
        Realm newRealmInstance = newRealmInstance();
        List<FloorOriginBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(FloorOriginBean.class).findAll().sort(ScheduleFragment.FLOOR, Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void deleteAllFloorOrigin() {
        Realm newRealmInstance = newRealmInstance();
        final RealmResults<E> findAll = newRealmInstance.where(FloorOriginBean.class).findAll();
        try {
            newRealmInstance.executeTransaction(new Realm.Transaction() {
                public void execute(Realm realm) {
                    findAll.deleteAllFromRealm();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        newRealmInstance.close();
    }

    public FloorOriginBean findFloorOrigin(String str) {
        Realm newRealmInstance = newRealmInstance();
        FloorOriginBean floorOriginBean = (FloorOriginBean) newRealmInstance.where(FloorOriginBean.class).equalTo(ScheduleFragment.FLOOR, str).findFirst();
        FloorOriginBean floorOriginBean2 = floorOriginBean != null ? (FloorOriginBean) newRealmInstance.copyFromRealm(floorOriginBean) : null;
        newRealmInstance.close();
        return floorOriginBean2;
    }

    public List<EmployeeBean> findAllEmployees() {
        Realm newRealmInstance = newRealmInstance();
        List<EmployeeBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(EmployeeBean.class).findAllAsync().sort("id", Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void addEmployee(EmployeeBean employeeBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(employeeBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deleteAllEmployees() {
        final Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm realm) {
                realm.where(EmployeeBean.class).findAllAsync().deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            public void onSuccess() {
                newRealmInstance.close();
            }
        }, new Realm.Transaction.OnError() {
            public void onError(Throwable th) {
                newRealmInstance.close();
            }
        });
    }

    public void deleteEmployee(final String str) {
        final Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm realm) {
                EmployeeBean employeeBean = (EmployeeBean) realm.where(EmployeeBean.class).equalTo("id", str).findFirst();
                if (employeeBean != null) {
                    employeeBean.deleteFromRealm();
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            public void onSuccess() {
                newRealmInstance.close();
            }
        }, new Realm.Transaction.OnError() {
            public void onError(Throwable th) {
                newRealmInstance.close();
            }
        });
    }

    public void updateEmployeeList(final List<EmployeeBean> list) {
        final Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm realm) {
                realm.where(EmployeeBean.class).findAll().deleteAllFromRealm();
                for (int i = 0; i < list.size(); i++) {
                    realm.copyToRealmOrUpdate((EmployeeBean) list.get(i), new ImportFlag[0]);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            public void onSuccess() {
                newRealmInstance.close();
            }
        }, new Realm.Transaction.OnError() {
            public void onError(Throwable th) {
                newRealmInstance.close();
            }
        });
    }

    public void saveTurnstileInfo(TurnstileBean turnstileBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.copyToRealmOrUpdate(turnstileBean, new ImportFlag[0]);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public List<TurnstileBean> findAllTurnstileInfo() {
        Realm newRealmInstance = newRealmInstance();
        List<TurnstileBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(TurnstileBean.class).findAllAsync().sort(ScheduleFragment.FLOOR, Sort.ASCENDING));
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<TurnstileBean> findTurnstileListByFloor(int i) {
        Realm newRealmInstance = newRealmInstance();
        RealmResults<E> findAll = newRealmInstance.where(TurnstileBean.class).equalTo(ScheduleFragment.FLOOR, Integer.valueOf(i)).findAll();
        List<TurnstileBean> copyFromRealm = (findAll == null || findAll.size() <= 0) ? null : newRealmInstance.copyFromRealm(findAll);
        newRealmInstance.close();
        return copyFromRealm;
    }

    public TurnstileBean findTurnstileInfoById(String str) {
        Realm newRealmInstance = newRealmInstance();
        TurnstileBean turnstileBean = (TurnstileBean) newRealmInstance.where(TurnstileBean.class).equalTo("id", str).findFirst();
        TurnstileBean turnstileBean2 = turnstileBean != null ? (TurnstileBean) newRealmInstance.copyFromRealm(turnstileBean) : null;
        newRealmInstance.close();
        return turnstileBean2;
    }

    public Realm deleteTurnstileInfo(final String str, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm realm) {
                TurnstileBean turnstileBean = (TurnstileBean) realm.where(TurnstileBean.class).equalTo("id", str).findFirst();
                if (turnstileBean != null) {
                    turnstileBean.deleteFromRealm();
                }
            }
        }, onSuccess, onError);
        return newRealmInstance;
    }

    public List<PathBean> findAllPath() {
        Realm newRealmInstance = newRealmInstance();
        List<PathBean> copyFromRealm = newRealmInstance.copyFromRealm(newRealmInstance.where(PathBean.class).findAll());
        newRealmInstance.close();
        return copyFromRealm;
    }

    public void insertPatrolTask(Realm realm, PatrolTaskBean patrolTaskBean) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(patrolTaskBean, new ImportFlag[0]);
        realm.commitTransaction();
    }

    public void deletePatrolTaskById(String str) {
        Realm newRealmInstance = newRealmInstance();
        PatrolTaskBean patrolTaskBean = (PatrolTaskBean) newRealmInstance.where(PatrolTaskBean.class).equalTo("id", str).findFirst();
        newRealmInstance.beginTransaction();
        if (patrolTaskBean != null) {
            patrolTaskBean.deleteFromRealm();
        }
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public void insertOrUpdatePath(PathBean pathBean) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.insertOrUpdate((RealmModel) pathBean);
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
    }

    public void deletePath(String str) {
        Realm newRealmInstance = newRealmInstance();
        newRealmInstance.beginTransaction();
        newRealmInstance.where(PathBean.class).equalTo("pathId", str).findAll().deleteAllFromRealm();
        newRealmInstance.commitTransaction();
        newRealmInstance.close();
        Realm.compactRealm(newRealmInstance.getConfiguration());
    }

    public List<Task> findAllTask() {
        Realm newRealmInstance = newRealmInstance();
        RealmResults<E> findAll = newRealmInstance.where(Task.class).findAll();
        List<Task> copyFromRealm = findAll != null ? newRealmInstance.copyFromRealm(findAll) : null;
        newRealmInstance.close();
        return copyFromRealm;
    }

    public List<Task> findAllNeedExecutePatrolTask(String str) {
        Realm newRealmInstance = newRealmInstance();
        Log.d("PATROL_TASK_TAG", "findAllNeedExecutePatrolTask nowTime=" + str);
        RealmResults<E> findAll = newRealmInstance.where(Task.class).findAll();
        List<E> copyFromRealm = findAll != null ? newRealmInstance.copyFromRealm(findAll) : null;
        newRealmInstance.close();
        if (copyFromRealm == null || copyFromRealm.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (E e : copyFromRealm) {
            e.getStartTime();
            Log.v("PATROL_TASK_TAG", "findAllNeedExecutePatrolTask task=" + e.toString());
            int weekOfDate = DateUtils.getWeekOfDate(new Date());
            if (e.getWeekly().length() == 7 && e.getWeekly().startsWith("1", weekOfDate - 1)) {
                e.setOneTaskStatus(1);
            }
            if (e.getOneTaskStatus() != 3) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public List<Task> findAllCompletePatrolTask(String str) {
        Realm newRealmInstance = newRealmInstance();
        Log.d("PATROL_TASK_TAG", "findAllCompletePatrolTask nowTime=" + str);
        RealmResults<E> findAll = newRealmInstance.where(Task.class).findAll();
        List<E> copyFromRealm = findAll != null ? newRealmInstance.copyFromRealm(findAll) : null;
        newRealmInstance.close();
        if (copyFromRealm == null || copyFromRealm.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (E e : copyFromRealm) {
            e.getStartTime();
            if (e.getOneTaskStatus() == 3) {
                e.setOneTaskStatus(3);
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public void insertOrUpdateTimerReceptionTask(Realm realm, TimerReceptionTaskBean timerReceptionTaskBean) {
        realm.beginTransaction();
        realm.insertOrUpdate((RealmModel) timerReceptionTaskBean);
        realm.commitTransaction();
    }

    public void deleteAllTimerReceptionTask(Realm realm) {
        realm.beginTransaction();
        realm.where(TimerReceptionTaskBean.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public List<GetAdListsBeanR> findAdList(Realm realm) {
        Calendar instance = Calendar.getInstance();
        long j = (long) (instance.get(12) + (instance.get(11) * 60));
        RealmResults<E> findAll = realm.where(GetAdListsBeanR.class).lessThanOrEqualTo("times.begin.minutes", j).findAll().where().greaterThan("times.end.minutes", j).findAll();
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - (60 * j);
        RealmResults<E> findAll2 = findAll.where().findAll();
        ArrayList arrayList = new ArrayList();
        for (E e : realm.copyFromRealm(findAll2)) {
            addDailyAd(arrayList, e);
            addTempAd(currentTimeMillis, arrayList, e);
            addWeeklyAd(instance, arrayList, e);
            addMonthlyAd(instance, arrayList, e);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            GetAdListsBeanR getAdListsBeanR = (GetAdListsBeanR) arrayList.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= getAdListsBeanR.getTimes().size()) {
                    break;
                }
                TimesBean timesBean = getAdListsBeanR.getTimes().get(i2);
                if (timesBean.getBegin().getMinutes() <= j && timesBean.getEnd().getMinutes() > j) {
                    arrayList2.add(getAdListsBeanR);
                    break;
                }
                i2++;
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            RealmList<AdvertisementsBean> advertisements = ((GetAdListsBeanR) it.next()).getAdvertisements();
            if (advertisements != null) {
                Iterator<AdvertisementsBean> it2 = advertisements.iterator();
                while (it2.hasNext()) {
                    AdvertisementsBean next = it2.next();
                    long end = (long) next.getEnd();
                    long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                    if (currentTimeMillis2 <= ((long) next.getBegin()) || currentTimeMillis2 >= end) {
                        it2.remove();
                    }
                }
                if (advertisements.size() <= 0) {
                    it.remove();
                }
            } else {
                it.remove();
            }
        }
        return arrayList2;
    }

    private void addDailyAd(List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.DAILY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            list.add(getAdListsBeanR);
        }
    }

    private void addMonthlyAd(Calendar calendar, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.MONTHLY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                if (it.next().longValue() == ((long) calendar.get(5))) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    private void addWeeklyAd(Calendar calendar, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.WEEKLY_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                Long next = it.next();
                int i = 7;
                int i2 = calendar.get(7);
                if (i2 > 1) {
                    i = i2 - 1;
                }
                if (next.longValue() == ((long) i)) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    private void addTempAd(long j, List<GetAdListsBeanR> list, GetAdListsBeanR getAdListsBeanR) {
        if (CycleBean.TIME_TYPE.equals(getAdListsBeanR.getCycle().getType())) {
            Iterator<Long> it = getAdListsBeanR.getCycle().getData().iterator();
            while (it.hasNext()) {
                Long valueOf = Long.valueOf(it.next().longValue() + 120);
                if (valueOf.longValue() >= j && valueOf.longValue() < TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC + j) {
                    list.add(getAdListsBeanR);
                    return;
                }
            }
        }
    }

    public void insertAd(Realm realm, GetAdListsBeanR getAdListsBeanR) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(getAdListsBeanR, new ImportFlag[0]);
        realm.commitTransaction();
    }

    public void deleteAllAdList(Realm realm) {
        realm.beginTransaction();
        RealmResults<E> findAll = realm.where(GetAdListsBeanR.class).findAll();
        RealmResults<E> findAll2 = realm.where(CycleBean.class).findAll();
        RealmResults<E> findAll3 = realm.where(TimesBean.class).findAll();
        RealmResults<E> findAll4 = realm.where(AdvertisementsBean.class).findAll();
        RealmResults<E> findAll5 = realm.where(BeginBean.class).findAll();
        RealmResults<E> findAll6 = realm.where(EndBean.class).findAll();
        RealmResults<E> findAll7 = realm.where(ResourcesBean.class).findAll();
        RealmResults<E> findAll8 = realm.where(HorseRaceLampsBean.class).findAll();
        findAll.deleteAllFromRealm();
        findAll2.deleteAllFromRealm();
        findAll3.deleteAllFromRealm();
        findAll4.deleteAllFromRealm();
        findAll5.deleteAllFromRealm();
        findAll6.deleteAllFromRealm();
        findAll7.deleteAllFromRealm();
        findAll8.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public boolean isAdFileExistByResourceId(Realm realm, String str) {
        return ((GetAdListsBeanR) realm.where(GetAdListsBeanR.class).equalTo("advertisements.resources.id", str).findFirst()) != null;
    }
}
