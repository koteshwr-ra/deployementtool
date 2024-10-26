package mc.csst.com.selfchassis.ui.fragment.set.version;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.FragmentSetVersionControlBinding;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;

public class VersionControlFragment extends BaseFragment {
    FragmentSetVersionControlBinding versionControlBinding;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    public static VersionControlFragment newInstance() {
        return new VersionControlFragment();
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSetVersionControlBinding fragmentSetVersionControlBinding = (FragmentSetVersionControlBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_version_control, viewGroup, false);
        this.versionControlBinding = fragmentSetVersionControlBinding;
        return fragmentSetVersionControlBinding.getRoot();
    }
}
