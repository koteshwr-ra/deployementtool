package mc.csst.com.selfchassis.ui.fragment.set.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.ciot.base.livedatabus.LiveDatabus;
import com.ciot.networklib.bean.DataUpdateEvent;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import mc.csst.com.selfchassis.databinding.FragmentMapBinding;
import mc.csst.com.selfchassis.ui.fragment.base.BaseFragment;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.bean.LoadingLiveDataEvent;
import mc.csst.com.selfchassis.utils.constant.DTLiveDataConstant;
import mc.csst.com.selfchassislibrary.bean.msg.RequestBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MapFragment extends BaseFragment {
    public static final String ANNEX_TAR_GZ = "-annex.tar.gz";
    public static final String CIOT = "ciot-";
    public static final String FLOOR = "floor_";
    public static final String MAP = "map-";
    public static final String MAP_PNG = "-map.png";
    public static final String SLASH = "-";
    FragmentMapBinding mapBinding;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentMapBinding fragmentMapBinding = (FragmentMapBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_map, viewGroup, false);
        this.mapBinding = fragmentMapBinding;
        return fragmentMapBinding.getRoot();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mapBinding.btnSureUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MapFragment mapFragment = MapFragment.this;
                mapFragment.showLoadingDialog(mapFragment.getString(R.string.txt_multi_robot_map_uploading));
                ArrayList arrayList = new ArrayList();
                RequestBean.ArgsBean.FloorBean floorBean = new RequestBean.ArgsBean.FloorBean();
                floorBean.setNum(MapFragment.this.mapBinding.mrmpwUpload.getSelectedFloorName());
                arrayList.add(floorBean);
                SelfChassis.getInstance().serviceUploadMaps(MapFragment.this.mapBinding.mrmpwUpload.getSelectedBuildName(), arrayList);
            }
        });
        this.mapBinding.btnSureDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MapFragment mapFragment = MapFragment.this;
                mapFragment.showLoadingDialog(mapFragment.getString(R.string.txt_multi_robot_map_downloading));
                ArrayList arrayList = new ArrayList();
                RequestBean.ArgsBean.FloorBean floorBean = new RequestBean.ArgsBean.FloorBean();
                MapFragment mapFragment2 = MapFragment.this;
                floorBean.setAnnex(mapFragment2.getAnnex(mapFragment2.mapBinding.mrmpwDownload.getSelectedBuildName(), MapFragment.this.mapBinding.mrmpwDownload.getSelectedFloorName()));
                floorBean.setNum(MapFragment.this.mapBinding.mrmpwDownload.getSelectedFloorName());
                MapFragment mapFragment3 = MapFragment.this;
                floorBean.setPath(mapFragment3.getPath(mapFragment3.mapBinding.mrmpwDownload.getSelectedBuildName(), MapFragment.this.mapBinding.mrmpwDownload.getSelectedFloorName()));
                arrayList.add(floorBean);
                SelfChassis.getInstance().serviceDownloadMaps(MapFragment.this.mapBinding.mrmpwDownload.getSelectedBuildName(), arrayList);
            }
        });
    }

    /* access modifiers changed from: private */
    public String getPath(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MAP);
        stringBuffer.append(str);
        stringBuffer.append(SLASH);
        stringBuffer.append(FLOOR);
        stringBuffer.append(str2);
        stringBuffer.append(MAP_PNG);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public String getAnnex(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MAP);
        stringBuffer.append(str);
        stringBuffer.append(SLASH);
        stringBuffer.append(FLOOR);
        stringBuffer.append(str2);
        stringBuffer.append(ANNEX_TAR_GZ);
        return stringBuffer.toString();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    @org.greenrobot.eventbus.Subscribe
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleSuccessEvent(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getCode()
            int r1 = r0.hashCode()
            r2 = -1458264442(0xffffffffa914a686, float:-3.3007038E-14)
            r3 = 1
            if (r1 == r2) goto L_0x001e
            r2 = 2040205343(0x799b101f, float:1.0064162E35)
            if (r1 == r2) goto L_0x0014
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "/download_maps"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 1
            goto L_0x0029
        L_0x001e:
            java.lang.String r1 = "/upload_maps"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            r0 = 0
            goto L_0x0029
        L_0x0028:
            r0 = -1
        L_0x0029:
            if (r0 == 0) goto L_0x0050
            if (r0 == r3) goto L_0x002e
            goto L_0x0071
        L_0x002e:
            r4.closeLoadingDialog()
            java.lang.Object r5 = r5.getData()
            mc.csst.com.selfchassislibrary.bean.msg.DownloadMapsResponseBean r5 = (mc.csst.com.selfchassislibrary.bean.msg.DownloadMapsResponseBean) r5
            android.content.Context r0 = r4.getContext()
            boolean r5 = r5.isResult()
            if (r5 == 0) goto L_0x0045
            r5 = 2131755655(0x7f100287, float:1.9142195E38)
            goto L_0x0048
        L_0x0045:
            r5 = 2131755252(0x7f1000f4, float:1.9141378E38)
        L_0x0048:
            java.lang.String r5 = r4.getString(r5)
            mc.csst.com.selfchassis.utils.MyToastUtils.showShort(r0, r5)
            goto L_0x0071
        L_0x0050:
            r4.closeLoadingDialog()
            java.lang.Object r5 = r5.getData()
            mc.csst.com.selfchassislibrary.bean.msg.UploadMapsResponseBean r5 = (mc.csst.com.selfchassislibrary.bean.msg.UploadMapsResponseBean) r5
            android.content.Context r0 = r4.getContext()
            boolean r5 = r5.isResult()
            if (r5 == 0) goto L_0x0067
            r5 = 2131755803(0x7f10031b, float:1.9142496E38)
            goto L_0x006a
        L_0x0067:
            r5 = 2131755802(0x7f10031a, float:1.9142494E38)
        L_0x006a:
            java.lang.String r5 = r4.getString(r5)
            mc.csst.com.selfchassis.utils.MyToastUtils.showShort(r0, r5)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment.handleSuccessEvent(mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg):void");
    }

    @Subscribe
    public void handleErrorEvent(DataUpdateEvent dataUpdateEvent) {
        int type = dataUpdateEvent.getType();
        if (type == 12 || type == 13) {
            closeLoadingDialog();
            MyToastUtils.showShort(getContext(), dataUpdateEvent.getId());
        }
    }

    public void closeLoadingDialog() {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(true));
    }

    public void showLoadingDialog(String str) {
        LiveDatabus.getInstance().with(DTLiveDataConstant.SET_LOADING_DIALOG_EVENT).postValue(new LoadingLiveDataEvent(str));
    }
}
