package com.ciot.diagnosis.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\"\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00028\u00000\u000bH&J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00028\u00008DX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/ciot/diagnosis/base/BaseBindingFragment;", "T", "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Landroidx/databinding/ViewDataBinding;", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "bindingInflate", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "onCreateView", "Landroid/view/View;", "inflater", "container", "savedInstanceState", "Landroid/os/Bundle;", "library-diagnosis_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: BaseBindingFragment.kt */
public abstract class BaseBindingFragment<T extends ViewDataBinding> extends Fragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private T _binding;

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

    public abstract Function3<LayoutInflater, ViewGroup, Boolean, T> bindingInflate();

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: protected */
    public final T getBinding() {
        T t = this._binding;
        Intrinsics.checkNotNull(t);
        return t;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this._binding = (ViewDataBinding) bindingInflate().invoke(layoutInflater, viewGroup, false);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());
        return getBinding().getRoot();
    }
}
