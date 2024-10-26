package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.adapter.multirobot.MultiRobotBuildAdapter;
import mc.csst.com.selfchassis.adapter.multirobot.MultiRobotFloorAdapter;
import mc.csst.com.selfchassis.model.trajectory.MultiScheduleModel;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;

public class MultiRobotMapPopupWindow extends FrameLayout {
    private CommonPopupWindowListener commonPopupWindowListener;
    private View container;
    private Context mContext;
    /* access modifiers changed from: private */
    public String selectedBuildName;
    /* access modifiers changed from: private */
    public String selectedFloorName;
    private TextView tv_upload_map_list;

    public interface CommonPopupWindowListener {
        void onItemSelected(String str, int i);
    }

    public String getSelectedBuildName() {
        return this.selectedBuildName;
    }

    public String getSelectedFloorName() {
        return this.selectedFloorName;
    }

    public MultiRobotMapPopupWindow(Context context) {
        this(context, (AttributeSet) null);
    }

    public MultiRobotMapPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiRobotMapPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedBuildName = "";
        this.selectedFloorName = "";
        this.mContext = context;
        initView(LayoutInflater.from(context).inflate(R.layout.multi_robot_map_popup_window, this, true));
    }

    private void initView(View view) {
        this.container = view.findViewById(R.id.container);
        this.tv_upload_map_list = (TextView) view.findViewById(R.id.tv_upload_map_list);
        this.selectedBuildName = ShowSelfChassisBean.getInstance().getBuild();
        this.selectedFloorName = ShowSelfChassisBean.getInstance().getFloor();
        updateSelectedMapText();
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiRobotMapPopupWindow.this.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSelectedMapText() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.selectedBuildName;
        if (!(str == null || this.selectedFloorName == null)) {
            stringBuffer.append(str);
            stringBuffer.append("/");
            stringBuffer.append(this.selectedFloorName);
        }
        this.tv_upload_map_list.setText(stringBuffer.toString());
    }

    public void show() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.recycler_view_multi_robot_map_popup_window, (ViewGroup) null, false);
        final FixedPopupWindow fixedPopupWindow = new FixedPopupWindow(inflate, -2, -2, true);
        fixedPopupWindow.setAnimationStyle(-1);
        fixedPopupWindow.setOutsideTouchable(true);
        MultiRobotBuildAdapter multiRobotBuildAdapter = new MultiRobotBuildAdapter(getContext());
        final MultiRobotFloorAdapter multiRobotFloorAdapter = new MultiRobotFloorAdapter(getContext());
        ((RecyclerView) inflate.findViewById(R.id.main_build_name_rv)).setAdapter(multiRobotBuildAdapter);
        ((RecyclerView) inflate.findViewById(R.id.main_floor_rv)).setAdapter(multiRobotFloorAdapter);
        multiRobotBuildAdapter.setOnItemClickListener(new MultiRobotBuildAdapter.OnItemClickListener() {
            public void onItemClick(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
                multiRobotFloorAdapter.refreshData(listInfoBean);
            }
        });
        multiRobotFloorAdapter.setOnItemClickListener(new MultiRobotFloorAdapter.OnItemClickListener() {
            public void onItemClick(String str, String str2) {
                String unused = MultiRobotMapPopupWindow.this.selectedBuildName = str;
                String unused2 = MultiRobotMapPopupWindow.this.selectedFloorName = str2;
                MultiRobotMapPopupWindow.this.updateSelectedMapText();
                fixedPopupWindow.dismiss();
            }
        });
        multiRobotBuildAdapter.refreshData(MultiScheduleModel.buildList);
        if (MultiScheduleModel.buildList != null) {
            multiRobotFloorAdapter.refreshData(MultiScheduleModel.buildList.get(0));
        }
        inflate.measure(0, 0);
        fixedPopupWindow.showAsDropDown(this.container, 0, 20);
    }

    public void setCommonPopupWindowListener(CommonPopupWindowListener commonPopupWindowListener2) {
        this.commonPopupWindowListener = commonPopupWindowListener2;
    }
}
