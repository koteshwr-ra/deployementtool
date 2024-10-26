package mc.csst.com.selfchassis.ui.fragment.set.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.FragmentSetMapUploadBinding;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;

public class MapUploadFragment extends BaseFragment {
    private FragmentSetMapUploadBinding mMapUploadBinding;
    private MapUploadViewModel mViewModel;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    public static MapUploadFragment newInstance() {
        return new MapUploadFragment();
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mMapUploadBinding = (FragmentSetMapUploadBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_set_map_upload, viewGroup, false);
        this.mViewModel = (MapUploadViewModel) new ViewModelProvider(this).get(MapUploadViewModel.class);
        return this.mMapUploadBinding.getRoot();
    }
}
