package com.ciot.diagnosis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.diagnosis.R;
import com.ciot.diagnosis.util.DomainUtil;
import com.ciot.diagnosis.util.PingViewModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ciot/diagnosis/fragment/PingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewModel", "Lcom/ciot/diagnosis/util/PingViewModel;", "isVisBottom", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onViewCreated", "view", "Companion", "PingAdapter", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: PingFragment.kt */
public final class PingFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HOST = "HOST";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private PingViewModel viewModel;

    @JvmStatic
    public static final PingFragment newInstance(String str) {
        return Companion.newInstance(str);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/ciot/diagnosis/fragment/PingFragment$Companion;", "", "()V", "HOST", "", "newInstance", "Lcom/ciot/diagnosis/fragment/PingFragment;", "host", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: PingFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PingFragment newInstance(String str) {
            Intrinsics.checkNotNullParameter(str, "host");
            PingFragment pingFragment = new PingFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PingFragment.HOST, str);
            pingFragment.setArguments(bundle);
            return pingFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewModel viewModel2 = ViewModelProviders.of((Fragment) this).get(PingViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(this).get(PingViewModel::class.java)");
        this.viewModel = (PingViewModel) viewModel2;
    }

    public void onDestroy() {
        super.onDestroy();
        PingViewModel pingViewModel = this.viewModel;
        if (pingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pingViewModel = null;
        }
        pingViewModel.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.diagnosis_fragment_ping, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new PingAdapter(this, (List) objectRef.element);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setAdapter((RecyclerView.Adapter) objectRef2.element);
        PingViewModel pingViewModel = this.viewModel;
        String str = null;
        if (pingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pingViewModel = null;
        }
        LifecycleOwner lifecycleOwner = this;
        pingViewModel.data.observe(lifecycleOwner, new Observer(objectRef, objectRef2) {
            public final /* synthetic */ Ref.ObjectRef f$1;
            public final /* synthetic */ Ref.ObjectRef f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onChanged(Object obj) {
                PingFragment.m0onViewCreated$lambda0(PingFragment.this, this.f$1, this.f$2, (String) obj);
            }
        });
        PingViewModel pingViewModel2 = this.viewModel;
        if (pingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pingViewModel2 = null;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString(HOST);
        }
        pingViewModel2.ping(lifecycleOwner, DomainUtil.getDomain(str));
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-0  reason: not valid java name */
    public static final void m0onViewCreated$lambda0(PingFragment pingFragment, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, String str) {
        Intrinsics.checkNotNullParameter(pingFragment, "this$0");
        Intrinsics.checkNotNullParameter(objectRef, "$stringList");
        Intrinsics.checkNotNullParameter(objectRef2, "$adapter");
        RecyclerView recyclerView = (RecyclerView) pingFragment._$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        boolean isVisBottom = pingFragment.isVisBottom(recyclerView);
        Intrinsics.checkNotNullExpressionValue(str, "it");
        ((List) objectRef.element).add(str);
        ((PingAdapter) objectRef2.element).notifyItemInserted(((List) objectRef.element).size() - 1);
        if (isVisBottom) {
            ((RecyclerView) pingFragment._$_findCachedViewById(R.id.recyclerView)).scrollToPosition(((PingAdapter) objectRef2.element).getItemCount() - 1);
        }
    }

    private final boolean isVisBottom(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            int childCount = linearLayoutManager.getChildCount();
            int itemCount = linearLayoutManager.getItemCount();
            if (childCount > 0 && findLastVisibleItemPosition == itemCount - 1 && recyclerView.getScrollState() == 0) {
                return true;
            }
            return false;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J \u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ciot/diagnosis/fragment/PingFragment$PingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ciot/diagnosis/fragment/PingFragment$PingAdapter$ViewHolder;", "Lcom/ciot/diagnosis/fragment/PingFragment;", "stringList", "", "", "(Lcom/ciot/diagnosis/fragment/PingFragment;Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: PingFragment.kt */
    public final class PingAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<String> stringList;
        final /* synthetic */ PingFragment this$0;

        public PingAdapter(PingFragment pingFragment, List<String> list) {
            Intrinsics.checkNotNullParameter(list, "stringList");
            this.this$0 = pingFragment;
            this.stringList = list;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diagnosis_item_ping, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…item_ping, parent, false)");
            return new ViewHolder(this, inflate);
        }

        public int getItemCount() {
            List<String> list = this.stringList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Intrinsics.checkNotNullParameter(viewHolder, "holder");
            ((TextView) viewHolder.itemView.findViewById(R.id.tvMessage)).setText(this.stringList.get(i));
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/ciot/diagnosis/fragment/PingFragment$PingAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ciot/diagnosis/fragment/PingFragment$PingAdapter;Landroid/view/View;)V", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* compiled from: PingFragment.kt */
        public final class ViewHolder extends RecyclerView.ViewHolder {
            final /* synthetic */ PingAdapter this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ViewHolder(PingAdapter pingAdapter, View view) {
                super(view);
                Intrinsics.checkNotNullParameter(view, "itemView");
                this.this$0 = pingAdapter;
            }
        }
    }
}
