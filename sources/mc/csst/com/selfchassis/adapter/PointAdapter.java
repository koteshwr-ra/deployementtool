package mc.csst.com.selfchassis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ClickUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.GsonUtils;
import com.ciot.sentrymove.R;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;

public class PointAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnItemClickListener clickListener;
    private final Context context;
    private List<PointInfo> mPointList;

    public interface OnItemClickListener {
        void onItemDeleteClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);

        void onItemEditClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);

        void onItemNavigationClick(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean, boolean z);
    }

    public PointAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        List list2 = (List) GsonUtils.fromLocalJson(GsonUtils.toJson(list), new TypeToken<List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean>>() {
        }.getType());
        screenOutTrajectoryPoint(list2);
        this.mPointList = loadData(list2);
        notifyDataSetChanged();
    }

    private void screenOutTrajectoryPoint(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        if (list != null) {
            for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
                if (next != null && next.getTime_out() == 1.0f) {
                    next.setBehavior_code(DeploymentToolConstant.POINT_TRAJECTORY);
                }
            }
        }
    }

    public void appendData(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        this.mPointList = loadData(list);
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_point, viewGroup, false));
        }
        return new ViewHolderContent(LayoutInflater.from(this.context).inflate(R.layout.item_point_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PointInfo pointInfo = this.mPointList.get(i);
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).tvPointName.setText(getPointName(pointInfo.getPointType()));
        } else if (viewHolder instanceof ViewHolderContent) {
            ViewHolderContent viewHolderContent = (ViewHolderContent) viewHolder;
            final MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean = pointInfo.getWaypointsBean();
            viewHolderContent.tvPointName.setText(waypointsBean.getName());
            int behavior_code = waypointsBean.getBehavior_code();
            int i2 = 0;
            if (behavior_code == 20) {
                viewHolderContent.rlDelete.setVisibility(4);
                viewHolderContent.rlEdit.setVisibility(4);
            } else {
                viewHolderContent.rlDelete.setVisibility(0);
                viewHolderContent.rlEdit.setVisibility(0);
            }
            if (behavior_code == 5 || behavior_code == 3 || behavior_code == 4) {
                viewHolderContent.rlNavigation.setVisibility(4);
            } else if (MySpUtils.getInstance().getInt(SpConstant.NAV_MODE, 0) == 0) {
                viewHolderContent.rlNavigation.setVisibility(0);
            } else {
                RelativeLayout relativeLayout = viewHolderContent.rlNavigation;
                if (waypointsBean.getTime_out() != 1.0f) {
                    i2 = 4;
                }
                relativeLayout.setVisibility(i2);
            }
            if (pointInfo.getPointType() == -65535) {
                viewHolderContent.rlEdit.setVisibility(4);
                viewHolderContent.rlDelete.setVisibility(4);
            }
            ClickUtils.applyGlobalDebouncing((View) viewHolderContent.rlDelete, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (PointAdapter.this.clickListener != null) {
                        PointAdapter.this.clickListener.onItemDeleteClick(waypointsBean);
                    }
                }
            });
            ClickUtils.applyGlobalDebouncing((View) viewHolderContent.rlEdit, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (PointAdapter.this.clickListener != null) {
                        PointAdapter.this.clickListener.onItemEditClick(waypointsBean);
                    }
                }
            });
            ClickUtils.applyGlobalDebouncing((View) viewHolderContent.rlNavigation, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (PointAdapter.this.clickListener != null) {
                        OnItemClickListener onItemClickListener = PointAdapter.this.clickListener;
                        MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean = waypointsBean;
                        onItemClickListener.onItemNavigationClick(waypointsBean, waypointsBean.getTime_out() == 1.0f);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        List<PointInfo> list = this.mPointList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getItemViewType(int i) {
        return this.mPointList.get(i).isHead() ^ true ? 1 : 0;
    }

    public void remove(String str) {
        Iterator<PointInfo> it = this.mPointList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PointInfo next = it.next();
            if (!next.isHead && str.equals(next.getWaypointsBean().getName())) {
                this.mPointList.remove(next);
                PointInfo pointInfoDelHead = getPointInfoDelHead(next.getPointType());
                if (pointInfoDelHead != null) {
                    this.mPointList.remove(pointInfoDelHead);
                }
            }
        }
        notifyDataSetChanged();
    }

    private PointInfo getPointInfoDelHead(int i) {
        ArrayList arrayList = new ArrayList();
        for (PointInfo next : this.mPointList) {
            if (next.getPointType() == i) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() == 1) {
            return (PointInfo) arrayList.get(0);
        }
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPointName;

        public ViewHolder(View view) {
            super(view);
            this.tvPointName = (AppCompatTextView) view.findViewById(R.id.tv_item_point_name);
        }
    }

    static class ViewHolderContent extends RecyclerView.ViewHolder {
        RelativeLayout rlDelete;
        RelativeLayout rlEdit;
        RelativeLayout rlNavigation;
        AppCompatTextView tvPointName;

        public ViewHolderContent(View view) {
            super(view);
            setIsRecyclable(false);
            this.tvPointName = (AppCompatTextView) view.findViewById(R.id.tv_item_point_name);
            this.rlDelete = (RelativeLayout) view.findViewById(R.id.rl_point_delete);
            this.rlEdit = (RelativeLayout) view.findViewById(R.id.rl_point_edit);
            this.rlNavigation = (RelativeLayout) view.findViewById(R.id.rl_point_navigation);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    private List<PointInfo> loadData(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getPointInfoByType(list, 11));
        arrayList.addAll(getPointInfoByType(list, 0));
        arrayList.addAll(getPointInfoByType(list, 3));
        arrayList.addAll(getPointInfoByType(list, 4));
        arrayList.addAll(getPointInfoByType(list, 7));
        arrayList.addAll(getPointInfoByType(list, 8));
        arrayList.addAll(getPointInfoByType(list, 50));
        arrayList.addAll(getPointInfoByType(list, 5));
        arrayList.addAll(getPointInfoByType(list, 20));
        arrayList.addAll(getPointInfoByType(list, 10));
        arrayList.addAll(getPointInfoByType(list, DeploymentToolConstant.POINT_TRAJECTORY));
        return arrayList;
    }

    private List<PointInfo> getPointInfoByType(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
            if (next.getBehavior_code() == i) {
                arrayList.add(new PointInfo(i, next));
            }
        }
        if (arrayList.size() > 0) {
            arrayList.add(0, new PointInfo(i));
        }
        return arrayList;
    }

    private String getPointName(int i) {
        if (i == -65535) {
            return ContextUtil.getContext().getString(R.string.text_point_trajectory);
        }
        if (i == 0) {
            return this.context.getString(R.string.point_common);
        }
        if (i == 20) {
            return this.context.getString(R.string.txt_visual_label_point);
        }
        if (i == 50) {
            return this.context.getString(R.string.point_stop);
        }
        if (i == 3) {
            return this.context.getString(R.string.elevator_out);
        }
        if (i == 4) {
            return this.context.getString(R.string.elevator_in);
        }
        if (i == 5) {
            return this.context.getString(R.string.point_wait);
        }
        if (i == 7) {
            return this.context.getString(R.string.point_gate_machine);
        }
        if (i == 8) {
            return this.context.getString(R.string.point_door_guard);
        }
        if (i != 10) {
            return i != 11 ? "" : this.context.getString(R.string.point_charge);
        }
        return this.context.getString(R.string.txt_outward_of_charging_pile);
    }

    private static class PointInfo {
        /* access modifiers changed from: private */
        public boolean isHead;
        private int pointType;
        private MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean;

        public PointInfo() {
        }

        public PointInfo(int i) {
            this.pointType = i;
            this.isHead = true;
        }

        public PointInfo(int i, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean2) {
            this.pointType = i;
            this.waypointsBean = waypointsBean2;
        }

        public int getPointType() {
            return this.pointType;
        }

        public void setPointType(int i) {
            this.pointType = i;
        }

        public boolean isHead() {
            return this.isHead;
        }

        public void setHead(boolean z) {
            this.isHead = z;
        }

        public MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean getWaypointsBean() {
            return this.waypointsBean;
        }

        public void setWaypointsBean(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean2) {
            this.waypointsBean = waypointsBean2;
        }
    }
}
