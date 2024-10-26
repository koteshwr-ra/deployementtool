package mc.csst.com.selfchassis.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected Context context;

    /* access modifiers changed from: protected */
    public abstract View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void initData();

    /* access modifiers changed from: protected */
    public abstract void initEvent();

    public void onAttach(Context context2) {
        super.onAttach(context2);
        this.context = context2.getApplicationContext();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = getContentView(layoutInflater, viewGroup, bundle);
        initData();
        initEvent();
        return contentView;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
