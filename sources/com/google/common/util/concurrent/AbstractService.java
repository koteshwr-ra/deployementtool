package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public abstract class AbstractService implements Service {
    private static final ListenerCallQueue.Callback<Service.Listener> RUNNING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("running()") {
        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.running();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STARTING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("starting()") {
        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.starting();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(Service.State.STARTING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(Service.State.NEW);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(Service.State.STOPPING);
    private final Monitor.Guard hasReachedRunning = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    };
    private final Monitor.Guard isStartable;
    private final Monitor.Guard isStoppable = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    };
    private final Monitor.Guard isStopped = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    };
    private final List<ListenerCallQueue<Service.Listener>> listeners = Collections.synchronizedList(new ArrayList());
    private final Monitor monitor;
    private volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    /* access modifiers changed from: protected */
    public abstract void doStart();

    /* access modifiers changed from: protected */
    public abstract void doStop();

    private static ListenerCallQueue.Callback<Service.Listener> terminatedCallback(final Service.State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        StringBuilder sb = new StringBuilder(valueOf.length() + 21);
        sb.append("terminated({from = ");
        sb.append(valueOf);
        sb.append("})");
        return new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.terminated(state);
            }
        };
    }

    private static ListenerCallQueue.Callback<Service.Listener> stoppingCallback(final Service.State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        StringBuilder sb = new StringBuilder(valueOf.length() + 19);
        sb.append("stopping({from = ");
        sb.append(valueOf);
        sb.append("})");
        return new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.stopping(state);
            }
        };
    }

    protected AbstractService() {
        Monitor monitor2 = new Monitor();
        this.monitor = monitor2;
        this.isStartable = new Monitor.Guard(monitor2) {
            public boolean isSatisfied() {
                return AbstractService.this.state() == Service.State.NEW;
            }
        };
    }

    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                starting();
                doStart();
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
            return this;
        }
        String valueOf = String.valueOf(String.valueOf(this));
        StringBuilder sb = new StringBuilder(valueOf.length() + 33);
        sb.append("Service ");
        sb.append(valueOf);
        sb.append(" has already been started");
        throw new IllegalStateException(sb.toString());
    }

    public final Service stopAsync() {
        if (this.monitor.enterIf(this.isStoppable)) {
            try {
                Service.State state = state();
                switch (AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State[state.ordinal()]) {
                    case 1:
                        this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                        terminated(Service.State.NEW);
                        break;
                    case 2:
                        this.snapshot = new StateSnapshot(Service.State.STARTING, true, (Throwable) null);
                        stopping(Service.State.STARTING);
                        break;
                    case 3:
                        this.snapshot = new StateSnapshot(Service.State.STOPPING);
                        stopping(Service.State.RUNNING);
                        doStop();
                        break;
                    case 4:
                    case 5:
                    case 6:
                        String valueOf = String.valueOf(String.valueOf(state));
                        StringBuilder sb = new StringBuilder(valueOf.length() + 45);
                        sb.append("isStoppable is incorrectly implemented, saw: ");
                        sb.append(valueOf);
                        throw new AssertionError(sb.toString());
                    default:
                        String valueOf2 = String.valueOf(String.valueOf(state));
                        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 18);
                        sb2.append("Unexpected state: ");
                        sb2.append(valueOf2);
                        throw new AssertionError(sb2.toString());
                }
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
        }
        return this;
    }

    /* renamed from: com.google.common.util.concurrent.AbstractService$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$util$concurrent$Service$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.common.util.concurrent.Service$State[] r0 = com.google.common.util.concurrent.Service.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$common$util$concurrent$Service$State = r0
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.NEW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$Service$State     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.STARTING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$Service$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.RUNNING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$Service$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.STOPPING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$Service$State     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.TERMINATED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$Service$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.common.util.concurrent.Service$State r1 = com.google.common.util.concurrent.Service.State.FAILED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractService.AnonymousClass10.<clinit>():void");
        }
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            StringBuilder sb = new StringBuilder(valueOf.length() + 66 + valueOf2.length());
            sb.append("Timed out waiting for ");
            sb.append(valueOf);
            sb.append(" to reach the RUNNING state. ");
            sb.append("Current state: ");
            sb.append(valueOf2);
            throw new TimeoutException(sb.toString());
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            StringBuilder sb = new StringBuilder(valueOf.length() + 65 + valueOf2.length());
            sb.append("Timed out waiting for ");
            sb.append(valueOf);
            sb.append(" to reach a terminal state. ");
            sb.append("Current state: ");
            sb.append(valueOf2);
            throw new TimeoutException(sb.toString());
        }
    }

    private void checkCurrentState(Service.State state) {
        Service.State state2 = state();
        if (state2 == state) {
            return;
        }
        if (state2 == Service.State.FAILED) {
            String valueOf = String.valueOf(String.valueOf(state));
            StringBuilder sb = new StringBuilder(valueOf.length() + 55);
            sb.append("Expected the service to be ");
            sb.append(valueOf);
            sb.append(", but the service has FAILED");
            throw new IllegalStateException(sb.toString(), failureCause());
        }
        String valueOf2 = String.valueOf(String.valueOf(state));
        String valueOf3 = String.valueOf(String.valueOf(state2));
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 37 + valueOf3.length());
        sb2.append("Expected the service to be ");
        sb2.append(valueOf2);
        sb2.append(", but was ");
        sb2.append(valueOf3);
        throw new IllegalStateException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state == Service.State.STARTING) {
                if (this.snapshot.shutdownWhenStartupFinishes) {
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    doStop();
                } else {
                    this.snapshot = new StateSnapshot(Service.State.RUNNING);
                    running();
                }
                return;
            }
            String valueOf = String.valueOf(String.valueOf(this.snapshot.state));
            StringBuilder sb = new StringBuilder(valueOf.length() + 43);
            sb.append("Cannot notifyStarted() when the service is ");
            sb.append(valueOf);
            IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
            notifyFailed(illegalStateException);
            throw illegalStateException;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyStopped() {
        this.monitor.enter();
        try {
            Service.State state = this.snapshot.state;
            if (state != Service.State.STOPPING) {
                if (state != Service.State.RUNNING) {
                    String valueOf = String.valueOf(String.valueOf(state));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 43);
                    sb.append("Cannot notifyStopped() when the service is ");
                    sb.append(valueOf);
                    IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
                    notifyFailed(illegalStateException);
                    throw illegalStateException;
                }
            }
            this.snapshot = new StateSnapshot(Service.State.TERMINATED);
            terminated(state);
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailed(Throwable th) {
        Preconditions.checkNotNull(th);
        this.monitor.enter();
        try {
            Service.State state = state();
            switch (AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State[state.ordinal()]) {
                case 1:
                case 5:
                    String valueOf = String.valueOf(String.valueOf(state));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                    sb.append("Failed while in state:");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString(), th);
                case 2:
                case 3:
                case 4:
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, th);
                    failed(state, th);
                    break;
                case 6:
                    break;
                default:
                    String valueOf2 = String.valueOf(String.valueOf(state));
                    StringBuilder sb2 = new StringBuilder(valueOf2.length() + 18);
                    sb2.append("Unexpected state: ");
                    sb2.append(valueOf2);
                    throw new AssertionError(sb2.toString());
            }
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    public final Service.State state() {
        return this.snapshot.externalState();
    }

    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    public final void addListener(Service.Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().isTerminal()) {
                this.listeners.add(new ListenerCallQueue(listener, executor));
            }
        } finally {
            this.monitor.leave();
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getClass().getSimpleName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        StringBuilder sb = new StringBuilder(valueOf.length() + 3 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" [");
        sb.append(valueOf2);
        sb.append("]");
        return sb.toString();
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                this.listeners.get(i).execute();
            }
        }
    }

    private void starting() {
        STARTING_CALLBACK.enqueueOn(this.listeners);
    }

    private void running() {
        RUNNING_CALLBACK.enqueueOn(this.listeners);
    }

    private void stopping(Service.State state) {
        if (state == Service.State.STARTING) {
            STOPPING_FROM_STARTING_CALLBACK.enqueueOn(this.listeners);
        } else if (state == Service.State.RUNNING) {
            STOPPING_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    private void terminated(Service.State state) {
        int i = AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State[state.ordinal()];
        if (i == 1) {
            TERMINATED_FROM_NEW_CALLBACK.enqueueOn(this.listeners);
        } else if (i == 3) {
            TERMINATED_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else if (i == 4) {
            TERMINATED_FROM_STOPPING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    private void failed(final Service.State state, final Throwable th) {
        String valueOf = String.valueOf(String.valueOf(state));
        String valueOf2 = String.valueOf(String.valueOf(th));
        StringBuilder sb = new StringBuilder(valueOf.length() + 27 + valueOf2.length());
        sb.append("failed({from = ");
        sb.append(valueOf);
        sb.append(", cause = ");
        sb.append(valueOf2);
        sb.append("})");
        new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.failed(state, th);
            }
        }.enqueueOn(this.listeners);
    }

    private static final class StateSnapshot {
        @Nullable
        final Throwable failure;
        final boolean shutdownWhenStartupFinishes;
        final Service.State state;

        StateSnapshot(Service.State state2) {
            this(state2, false, (Throwable) null);
        }

        StateSnapshot(Service.State state2, boolean z, @Nullable Throwable th) {
            Preconditions.checkArgument(!z || state2 == Service.State.STARTING, "shudownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state2);
            Preconditions.checkArgument(!((th != null) ^ (state2 == Service.State.FAILED)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state2, th);
            this.state = state2;
            this.shutdownWhenStartupFinishes = z;
            this.failure = th;
        }

        /* access modifiers changed from: package-private */
        public Service.State externalState() {
            if (!this.shutdownWhenStartupFinishes || this.state != Service.State.STARTING) {
                return this.state;
            }
            return Service.State.STOPPING;
        }

        /* access modifiers changed from: package-private */
        public Throwable failureCause() {
            Preconditions.checkState(this.state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", this.state);
            return this.failure;
        }
    }
}
