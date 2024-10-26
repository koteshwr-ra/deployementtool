package io.realm.rx;

import android.os.Looper;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposables;
import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.ObjectChangeSet;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.IdentityHashMap;
import java.util.Map;

public class RealmObservableFactory implements RxObservableFactory {
    private static final BackpressureStrategy BACK_PRESSURE_STRATEGY = BackpressureStrategy.LATEST;
    /* access modifiers changed from: private */
    public ThreadLocal<StrongReferenceCounter<RealmList>> listRefs = new ThreadLocal<StrongReferenceCounter<RealmList>>() {
        /* access modifiers changed from: protected */
        public StrongReferenceCounter<RealmList> initialValue() {
            return new StrongReferenceCounter<>();
        }
    };
    /* access modifiers changed from: private */
    public ThreadLocal<StrongReferenceCounter<RealmModel>> objectRefs = new ThreadLocal<StrongReferenceCounter<RealmModel>>() {
        /* access modifiers changed from: protected */
        public StrongReferenceCounter<RealmModel> initialValue() {
            return new StrongReferenceCounter<>();
        }
    };
    /* access modifiers changed from: private */
    public ThreadLocal<StrongReferenceCounter<RealmResults>> resultsRefs = new ThreadLocal<StrongReferenceCounter<RealmResults>>() {
        /* access modifiers changed from: protected */
        public StrongReferenceCounter<RealmResults> initialValue() {
            return new StrongReferenceCounter<>();
        }
    };
    /* access modifiers changed from: private */
    public final boolean returnFrozenObjects;

    public int hashCode() {
        return 37;
    }

    public Flowable<Realm> from(Realm realm) {
        if (realm.isFrozen()) {
            return Flowable.just(realm);
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<Realm>() {
            public void subscribe(final FlowableEmitter<Realm> flowableEmitter) throws Exception {
                final Realm instance = Realm.getInstance(configuration);
                final AnonymousClass1 r1 = new RealmChangeListener<Realm>() {
                    public void onChange(Realm realm) {
                        if (!flowableEmitter.isCancelled()) {
                            FlowableEmitter flowableEmitter = flowableEmitter;
                            if (RealmObservableFactory.this.returnFrozenObjects) {
                                realm = realm.freeze();
                            }
                            flowableEmitter.onNext(realm);
                        }
                    }
                };
                instance.addChangeListener(r1);
                flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                    public void run() {
                        if (!instance.isClosed()) {
                            instance.removeChangeListener(r1);
                            instance.close();
                        }
                    }
                }));
                if (RealmObservableFactory.this.returnFrozenObjects) {
                    instance = instance.freeze();
                }
                flowableEmitter.onNext(instance);
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public RealmObservableFactory(boolean z) {
        this.returnFrozenObjects = z;
    }

    public Flowable<DynamicRealm> from(DynamicRealm dynamicRealm) {
        if (dynamicRealm.isFrozen()) {
            return Flowable.just(dynamicRealm);
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<DynamicRealm>() {
            public void subscribe(final FlowableEmitter<DynamicRealm> flowableEmitter) throws Exception {
                final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                final AnonymousClass1 r1 = new RealmChangeListener<DynamicRealm>() {
                    public void onChange(DynamicRealm dynamicRealm) {
                        if (!flowableEmitter.isCancelled()) {
                            FlowableEmitter flowableEmitter = flowableEmitter;
                            if (RealmObservableFactory.this.returnFrozenObjects) {
                                dynamicRealm = dynamicRealm.freeze();
                            }
                            flowableEmitter.onNext(dynamicRealm);
                        }
                    }
                };
                instance.addChangeListener(r1);
                flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                    public void run() {
                        if (!instance.isClosed()) {
                            instance.removeChangeListener(r1);
                            instance.close();
                        }
                    }
                }));
                if (RealmObservableFactory.this.returnFrozenObjects) {
                    instance = instance.freeze();
                }
                flowableEmitter.onNext(instance);
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Flowable<RealmResults<E>> from(Realm realm, final RealmResults<E> realmResults) {
        if (realm.isFrozen()) {
            return Flowable.just(realmResults);
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<RealmResults<E>>() {
            public void subscribe(final FlowableEmitter<RealmResults<E>> flowableEmitter) {
                if (realmResults.isValid()) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(realmResults);
                    final AnonymousClass1 r1 = new RealmChangeListener<RealmResults<E>>() {
                        public void onChange(RealmResults<E> realmResults) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmResults = realmResults.freeze();
                                }
                                flowableEmitter.onNext(realmResults);
                            }
                        }
                    };
                    realmResults.addChangeListener(r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmResults.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(realmResults);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? realmResults.freeze() : realmResults);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    private Scheduler getScheduler() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return AndroidSchedulers.from(myLooper);
        }
        throw new IllegalStateException("No looper found");
    }

    public <E> Observable<CollectionChange<RealmResults<E>>> changesetsFrom(Realm realm, final RealmResults<E> realmResults) {
        if (realm.isFrozen()) {
            return Observable.just(new CollectionChange(realmResults, (OrderedCollectionChangeSet) null));
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<CollectionChange<RealmResults<E>>>() {
            public void subscribe(final ObservableEmitter<CollectionChange<RealmResults<E>>> observableEmitter) {
                if (realmResults.isValid()) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(realmResults);
                    final AnonymousClass1 r1 = new OrderedRealmCollectionChangeListener<RealmResults<E>>() {
                        public void onChange(RealmResults<E> realmResults, OrderedCollectionChangeSet orderedCollectionChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                observableEmitter.onNext(new CollectionChange(RealmObservableFactory.this.returnFrozenObjects ? realmResults.freeze() : realmResults, orderedCollectionChangeSet));
                            }
                        }
                    };
                    realmResults.addChangeListener(r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmResults.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(realmResults);
                        }
                    }));
                    observableEmitter.onNext(new CollectionChange(RealmObservableFactory.this.returnFrozenObjects ? realmResults.freeze() : realmResults, (OrderedCollectionChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Flowable<RealmResults<E>> from(DynamicRealm dynamicRealm, final RealmResults<E> realmResults) {
        if (dynamicRealm.isFrozen()) {
            return Flowable.just(realmResults);
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<RealmResults<E>>() {
            public void subscribe(final FlowableEmitter<RealmResults<E>> flowableEmitter) {
                if (realmResults.isValid()) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(realmResults);
                    final AnonymousClass1 r1 = new RealmChangeListener<RealmResults<E>>() {
                        public void onChange(RealmResults<E> realmResults) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmResults = realmResults.freeze();
                                }
                                flowableEmitter.onNext(realmResults);
                            }
                        }
                    };
                    realmResults.addChangeListener(r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmResults.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(realmResults);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? realmResults.freeze() : realmResults);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Observable<CollectionChange<RealmResults<E>>> changesetsFrom(DynamicRealm dynamicRealm, final RealmResults<E> realmResults) {
        if (dynamicRealm.isFrozen()) {
            return Observable.just(new CollectionChange(realmResults, (OrderedCollectionChangeSet) null));
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<CollectionChange<RealmResults<E>>>() {
            public void subscribe(final ObservableEmitter<CollectionChange<RealmResults<E>>> observableEmitter) {
                if (realmResults.isValid()) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(realmResults);
                    final AnonymousClass1 r1 = new OrderedRealmCollectionChangeListener<RealmResults<E>>() {
                        public void onChange(RealmResults<E> realmResults, OrderedCollectionChangeSet orderedCollectionChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                ObservableEmitter observableEmitter = observableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmResults = realmResults.freeze();
                                }
                                observableEmitter.onNext(new CollectionChange(realmResults, orderedCollectionChangeSet));
                            }
                        }
                    };
                    realmResults.addChangeListener(r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmResults.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(realmResults);
                        }
                    }));
                    observableEmitter.onNext(new CollectionChange(RealmObservableFactory.this.returnFrozenObjects ? realmResults.freeze() : realmResults, (OrderedCollectionChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Flowable<RealmList<E>> from(Realm realm, final RealmList<E> realmList) {
        if (realm.isFrozen()) {
            return Flowable.just(realmList);
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<RealmList<E>>() {
            public void subscribe(final FlowableEmitter<RealmList<E>> flowableEmitter) {
                if (realmList.isValid()) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(realmList);
                    final AnonymousClass1 r1 = new RealmChangeListener<RealmList<E>>() {
                        public void onChange(RealmList<E> realmList) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmList = realmList.freeze();
                                }
                                flowableEmitter.onNext(realmList);
                            }
                        }
                    };
                    realmList.addChangeListener(r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmList.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(realmList);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? realmList.freeze() : realmList);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Observable<CollectionChange<RealmList<E>>> changesetsFrom(Realm realm, final RealmList<E> realmList) {
        if (realm.isFrozen()) {
            return Observable.just(new CollectionChange(realmList, (OrderedCollectionChangeSet) null));
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<CollectionChange<RealmList<E>>>() {
            public void subscribe(final ObservableEmitter<CollectionChange<RealmList<E>>> observableEmitter) {
                if (realmList.isValid()) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(realmList);
                    final AnonymousClass1 r1 = new OrderedRealmCollectionChangeListener<RealmList<E>>() {
                        public void onChange(RealmList<E> realmList, OrderedCollectionChangeSet orderedCollectionChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                ObservableEmitter observableEmitter = observableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmList = realmList.freeze();
                                }
                                observableEmitter.onNext(new CollectionChange(realmList, orderedCollectionChangeSet));
                            }
                        }
                    };
                    realmList.addChangeListener(r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmList.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(realmList);
                        }
                    }));
                    observableEmitter.onNext(new CollectionChange(RealmObservableFactory.this.returnFrozenObjects ? realmList.freeze() : realmList, (OrderedCollectionChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Flowable<RealmList<E>> from(DynamicRealm dynamicRealm, final RealmList<E> realmList) {
        if (dynamicRealm.isFrozen()) {
            return Flowable.just(realmList);
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<RealmList<E>>() {
            public void subscribe(final FlowableEmitter<RealmList<E>> flowableEmitter) {
                if (realmList.isValid()) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(realmList);
                    final AnonymousClass1 r1 = new RealmChangeListener<RealmList<E>>() {
                        public void onChange(RealmList<E> realmList) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmList = realmList.freeze();
                                }
                                flowableEmitter.onNext(realmList);
                            }
                        }
                    };
                    realmList.addChangeListener(r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmList.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(realmList);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? realmList.freeze() : realmList);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Observable<CollectionChange<RealmList<E>>> changesetsFrom(DynamicRealm dynamicRealm, final RealmList<E> realmList) {
        if (dynamicRealm.isFrozen()) {
            return Observable.just(new CollectionChange(realmList, (OrderedCollectionChangeSet) null));
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<CollectionChange<RealmList<E>>>() {
            public void subscribe(final ObservableEmitter<CollectionChange<RealmList<E>>> observableEmitter) {
                if (realmList.isValid()) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(realmList);
                    final AnonymousClass1 r1 = new OrderedRealmCollectionChangeListener<RealmList<E>>() {
                        public void onChange(RealmList<E> realmList, OrderedCollectionChangeSet orderedCollectionChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                ObservableEmitter observableEmitter = observableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    realmList = realmList.freeze();
                                }
                                observableEmitter.onNext(new CollectionChange(realmList, orderedCollectionChangeSet));
                            }
                        }
                    };
                    realmList.addChangeListener(r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                realmList.removeChangeListener(r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(realmList);
                        }
                    }));
                    observableEmitter.onNext(new CollectionChange(RealmObservableFactory.this.returnFrozenObjects ? realmList.freeze() : realmList, (OrderedCollectionChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E extends RealmModel> Flowable<E> from(final Realm realm, final E e) {
        if (realm.isFrozen()) {
            return Flowable.just(e);
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<E>() {
            public void subscribe(final FlowableEmitter<E> flowableEmitter) {
                if (!realm.isClosed()) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(e);
                    final AnonymousClass1 r1 = new RealmChangeListener<E>() {
                        public void onChange(E e) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    e = RealmObject.freeze(e);
                                }
                                flowableEmitter.onNext(e);
                            }
                        }
                    };
                    RealmObject.addChangeListener(e, r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                RealmObject.removeChangeListener(e, r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(e);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? RealmObject.freeze(e) : e);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E extends RealmModel> Observable<ObjectChange<E>> changesetsFrom(Realm realm, final E e) {
        if (realm.isFrozen()) {
            return Observable.just(new ObjectChange(e, (ObjectChangeSet) null));
        }
        final RealmConfiguration configuration = realm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<ObjectChange<E>>() {
            public void subscribe(final ObservableEmitter<ObjectChange<E>> observableEmitter) {
                if (RealmObject.isValid(e)) {
                    final Realm instance = Realm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(e);
                    final AnonymousClass1 r1 = new RealmObjectChangeListener<E>() {
                        public void onChange(E e, ObjectChangeSet objectChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                ObservableEmitter observableEmitter = observableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    e = RealmObject.freeze(e);
                                }
                                observableEmitter.onNext(new ObjectChange(e, objectChangeSet));
                            }
                        }
                    };
                    RealmObject.addChangeListener(e, r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                RealmObject.removeChangeListener(e, r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(e);
                        }
                    }));
                    observableEmitter.onNext(new ObjectChange(RealmObservableFactory.this.returnFrozenObjects ? RealmObject.freeze(e) : e, (ObjectChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public Flowable<DynamicRealmObject> from(final DynamicRealm dynamicRealm, final DynamicRealmObject dynamicRealmObject) {
        if (dynamicRealm.isFrozen()) {
            return Flowable.just(dynamicRealmObject);
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Flowable.create(new FlowableOnSubscribe<DynamicRealmObject>() {
            public void subscribe(final FlowableEmitter<DynamicRealmObject> flowableEmitter) {
                if (!dynamicRealm.isClosed()) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(dynamicRealmObject);
                    final AnonymousClass1 r1 = new RealmChangeListener<DynamicRealmObject>() {
                        public void onChange(DynamicRealmObject dynamicRealmObject) {
                            if (!flowableEmitter.isCancelled()) {
                                FlowableEmitter flowableEmitter = flowableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    dynamicRealmObject = (DynamicRealmObject) RealmObject.freeze(dynamicRealmObject);
                                }
                                flowableEmitter.onNext(dynamicRealmObject);
                            }
                        }
                    };
                    RealmObject.addChangeListener(dynamicRealmObject, r1);
                    flowableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                RealmObject.removeChangeListener(dynamicRealmObject, r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(dynamicRealmObject);
                        }
                    }));
                    flowableEmitter.onNext(RealmObservableFactory.this.returnFrozenObjects ? (DynamicRealmObject) RealmObject.freeze(dynamicRealmObject) : dynamicRealmObject);
                }
            }
        }, BACK_PRESSURE_STRATEGY).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public Observable<ObjectChange<DynamicRealmObject>> changesetsFrom(DynamicRealm dynamicRealm, final DynamicRealmObject dynamicRealmObject) {
        if (dynamicRealm.isFrozen()) {
            return Observable.just(new ObjectChange(dynamicRealmObject, (ObjectChangeSet) null));
        }
        final RealmConfiguration configuration = dynamicRealm.getConfiguration();
        Scheduler scheduler = getScheduler();
        return Observable.create(new ObservableOnSubscribe<ObjectChange<DynamicRealmObject>>() {
            public void subscribe(final ObservableEmitter<ObjectChange<DynamicRealmObject>> observableEmitter) {
                if (RealmObject.isValid(dynamicRealmObject)) {
                    final DynamicRealm instance = DynamicRealm.getInstance(configuration);
                    ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(dynamicRealmObject);
                    final AnonymousClass1 r1 = new RealmObjectChangeListener<DynamicRealmObject>() {
                        public void onChange(DynamicRealmObject dynamicRealmObject, ObjectChangeSet objectChangeSet) {
                            if (!observableEmitter.isDisposed()) {
                                ObservableEmitter observableEmitter = observableEmitter;
                                if (RealmObservableFactory.this.returnFrozenObjects) {
                                    dynamicRealmObject = (DynamicRealmObject) RealmObject.freeze(dynamicRealmObject);
                                }
                                observableEmitter.onNext(new ObjectChange(dynamicRealmObject, objectChangeSet));
                            }
                        }
                    };
                    dynamicRealmObject.addChangeListener(r1);
                    observableEmitter.setDisposable(Disposables.fromRunnable(new Runnable() {
                        public void run() {
                            if (!instance.isClosed()) {
                                RealmObject.removeChangeListener(dynamicRealmObject, r1);
                                instance.close();
                            }
                            ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(dynamicRealmObject);
                        }
                    }));
                    observableEmitter.onNext(new ObjectChange(RealmObservableFactory.this.returnFrozenObjects ? (DynamicRealmObject) RealmObject.freeze(dynamicRealmObject) : dynamicRealmObject, (ObjectChangeSet) null));
                }
            }
        }).subscribeOn(scheduler).unsubscribeOn(scheduler);
    }

    public <E> Single<RealmQuery<E>> from(Realm realm, RealmQuery<E> realmQuery) {
        throw new RuntimeException("RealmQuery not supported yet.");
    }

    public <E> Single<RealmQuery<E>> from(DynamicRealm dynamicRealm, RealmQuery<E> realmQuery) {
        throw new RuntimeException("RealmQuery not supported yet.");
    }

    public boolean equals(Object obj) {
        return obj instanceof RealmObservableFactory;
    }

    private static class StrongReferenceCounter<K> {
        private final Map<K, Integer> references;

        private StrongReferenceCounter() {
            this.references = new IdentityHashMap();
        }

        public void acquireReference(K k) {
            Integer num = this.references.get(k);
            if (num == null) {
                this.references.put(k, 1);
            } else {
                this.references.put(k, Integer.valueOf(num.intValue() + 1));
            }
        }

        public void releaseReference(K k) {
            Integer num = this.references.get(k);
            if (num == null) {
                throw new IllegalStateException("Object does not have any references: " + k);
            } else if (num.intValue() > 1) {
                this.references.put(k, Integer.valueOf(num.intValue() - 1));
            } else if (num.intValue() == 1) {
                this.references.remove(k);
            } else {
                throw new IllegalStateException("Invalid reference count: " + num);
            }
        }
    }
}
