package mc.csst.com.selfchassis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ClickUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnItemClickListener clickListener;
    private final Context context;
    private List<PointInfo> mPointList;

    public interface OnItemClickListener {
        void onItemAdd2Line(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);
    }

    public MultiAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        this.mPointList = loadData(list);
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_point, viewGroup, false));
        }
        return new ViewHolderContent(LayoutInflater.from(this.context).inflate(R.layout.item_multi_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PointInfo pointInfo = this.mPointList.get(i);
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).tvPointName.setText(getPointName(pointInfo.getPointType()));
        } else if (viewHolder instanceof ViewHolderContent) {
            ViewHolderContent viewHolderContent = (ViewHolderContent) viewHolder;
            final MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean = pointInfo.getWaypointsBean();
            viewHolderContent.tvMultiName.setText(waypointsBean.getName());
            ClickUtils.applyGlobalDebouncing((View) viewHolderContent.clMultiRoot, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (MultiAdapter.this.clickListener != null) {
                        MultiAdapter.this.clickListener.onItemAdd2Line(waypointsBean);
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
        ConstraintLayout clMultiAdd;
        ConstraintLayout clMultiRoot;
        AppCompatTextView tvMultiName;

        public ViewHolderContent(View view) {
            super(view);
            this.tvMultiName = (AppCompatTextView) view.findViewById(R.id.tv_multi_name);
            this.clMultiAdd = (ConstraintLayout) view.findViewById(R.id.cl_multi_add);
            this.clMultiRoot = (ConstraintLayout) view.findViewById(R.id.cl_multi_bg);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    private List<PointInfo> loadData(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getPointInfoByType(list, 11));
        arrayList.addAll(getPointInfoByType(list, 0));
        arrayList.addAll(getPointInfoByType(list, 50));
        return arrayList;
    }

    private List<PointInfo> getPointInfoByType(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
            if (next.getBehavior_code() == i && next.getTime_out() != 1.0f) {
                arrayList.add(new PointInfo(i, next));
            }
        }
        if (arrayList.size() > 0) {
            arrayList.add(0, new PointInfo(i));
        }
        return arrayList;
    }

    private String getPointName(int i) {
        if (i == 0) {
            return this.context.getString(R.string.point_common);
        }
        if (i == 11) {
            return this.context.getString(R.string.point_charge);
        }
        if (i == 50) {
            return this.context.getString(R.string.point_stop);
        }
        if (i != 7) {
            return i != 8 ? "" : this.context.getString(R.string.point_door_guard);
        }
        return this.context.getString(R.string.point_gate_machine);
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
