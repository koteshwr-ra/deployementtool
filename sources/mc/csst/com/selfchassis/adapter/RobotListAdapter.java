package mc.csst.com.selfchassis.adapter;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.utils.RandomColorUtil;
import mc.csst.com.selfchassislibrary.bean.msg.RobotListRespBean;

public class RobotListAdapter extends RecyclerView.Adapter<RobotListAdapterViewHolder> {
    private List<RobotListRespBean.MsgBean.ListBean> mList = new ArrayList();

    public void setRobotListRespBean(RobotListRespBean robotListRespBean) {
        if (isNeedToUpdate(robotListRespBean)) {
            this.mList.clear();
            if (robotListRespBean.getMsg().getList() != null) {
                this.mList.addAll(robotListRespBean.getMsg().getList());
            }
            notifyDataSetChanged();
        }
    }

    private boolean isNeedToUpdate(RobotListRespBean robotListRespBean) {
        if (isBeanNull(robotListRespBean) && this.mList.size() == 0) {
            return false;
        }
        if (robotListRespBean.getMsg().getList().size() == this.mList.size()) {
            return !compare(robotListRespBean.getMsg().getList(), this.mList);
        }
        return true;
    }

    private boolean compare(List<RobotListRespBean.MsgBean.ListBean> list, List<RobotListRespBean.MsgBean.ListBean> list2) {
        for (RobotListRespBean.MsgBean.ListBean contains : list) {
            if (!list2.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public RobotListAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RobotListAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_robot_list, viewGroup, false));
    }

    public void onBindViewHolder(RobotListAdapterViewHolder robotListAdapterViewHolder, int i) {
        String robot_id = this.mList.get(i).getRobot_id();
        robotListAdapterViewHolder.tv_robot_id.setText(robot_id);
        robotListAdapterViewHolder.riv_dot.setImageDrawable(new ColorDrawable(RandomColorUtil.getRandomColor(robot_id)));
    }

    public int getItemCount() {
        List<RobotListRespBean.MsgBean.ListBean> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private boolean isBeanNull(RobotListRespBean robotListRespBean) {
        return robotListRespBean == null || robotListRespBean.getMsg() == null || robotListRespBean.getMsg().getList() == null;
    }

    public static class RobotListAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView riv_dot;
        TextView tv_robot_id;

        public RobotListAdapterViewHolder(View view) {
            super(view);
            this.riv_dot = (ImageView) view.findViewById(R.id.riv_dot);
            this.tv_robot_id = (TextView) view.findViewById(R.id.tv_robot_id);
        }
    }
}
