package mc.csst.com.selfchassis.adapter.multirobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;

public class MultiRobotFloorAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int MIN_CLICK_DELAY_TIME = 500;
    /* access modifiers changed from: private */
    public LayeredMapCmdResponseBean.ValuesBean.ListInfoBean buildInfo;
    OnItemClickListener clickListener;
    /* access modifiers changed from: private */
    public int clickPosition = -1;
    private final Context context;
    private long lastClickTime = 0;
    private List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean> mFloorList;

    public interface OnItemClickListener {
        void onItemClick(String str, String str2);
    }

    public MultiRobotFloorAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
        this.buildInfo = listInfoBean;
        this.mFloorList = listInfoBean.getFloor_info();
        this.clickPosition = -1;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_floor_multi_robot, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean floorInfoBean = this.mFloorList.get(i);
        viewHolder.tvFloorName.setText(floorInfoBean.getFloor_name());
        ClickUtils.applyGlobalDebouncing(viewHolder.itemView, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                int unused = MultiRobotFloorAdapter.this.clickPosition = i;
                viewHolder.iv_check.setImageResource(R.drawable.icon_multi_robot_floor_selected);
                MultiRobotFloorAdapter.this.notifyDataSetChanged();
                ThreadUtils.getMainHandler().postDelayed(new Runnable() {
                    public void run() {
                        if (MultiRobotFloorAdapter.this.clickListener != null) {
                            MultiRobotFloorAdapter.this.clickListener.onItemClick(MultiRobotFloorAdapter.this.buildInfo.getBuilding_name(), floorInfoBean.getFloor_name());
                        }
                    }
                }, 100);
            }
        });
        if (i == this.clickPosition) {
            viewHolder.itemView.setBackgroundColor(this.context.getResources().getColor(R.color.clr_1A0560FD));
            viewHolder.tvFloorName.setTextColor(this.context.getResources().getColor(R.color.clr_0560FD));
            return;
        }
        viewHolder.tvFloorName.setTextColor(this.context.getResources().getColor(R.color.clr_2A2A2A));
        viewHolder.itemView.setBackgroundColor(this.context.getResources().getColor(R.color.clr_FEFEFF));
    }

    public int getItemCount() {
        List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean> list = this.mFloorList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_check;
        TextView tvFloorName;

        public ViewHolder(View view) {
            super(view);
            this.tvFloorName = (TextView) view.findViewById(R.id.tv_item_floor_name);
            this.iv_check = (ImageView) view.findViewById(R.id.iv_check);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }
}
