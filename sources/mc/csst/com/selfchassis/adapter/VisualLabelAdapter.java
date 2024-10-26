package mc.csst.com.selfchassis.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mc.csst.com.selfchassislibrary.bean.msg.ApriltagsBufferBean;

public class VisualLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "VisualLabelAdapter";
    OnItemClickListener clickListener;
    private final Context context;
    private List<String> lastCurrentTagPointList = new ArrayList();
    private List<PointInfo> mCurrentTagPointList = new ArrayList();
    private List<PointInfo> mPointList = new ArrayList();
    private List<PointInfo> mSavedTagPointList = new ArrayList();

    public interface OnItemClickListener {
        void onItemDeleteClick(String str);
    }

    public VisualLabelAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list) {
        this.mPointList.clear();
        this.mSavedTagPointList = loadData(list, true);
        this.mPointList.addAll(this.mCurrentTagPointList);
        this.mPointList.addAll(this.mSavedTagPointList);
        notifyDataSetChanged();
    }

    public void refreshCurrentTagPoints(List<String> list) {
        int i = 0;
        if (list.size() == this.lastCurrentTagPointList.size()) {
            while (i < list.size() && list.get(i).equals(this.lastCurrentTagPointList.get(i))) {
                i++;
            }
            return;
        }
        this.mPointList.clear();
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String name : list) {
                ApriltagsBufferBean.MsgBean.WaypointsBean waypointsBean = new ApriltagsBufferBean.MsgBean.WaypointsBean();
                waypointsBean.setName(name);
                waypointsBean.setBehavior_code(20);
                arrayList.add(waypointsBean);
            }
        }
        this.lastCurrentTagPointList.clear();
        this.lastCurrentTagPointList.addAll(list);
        List<PointInfo> loadData = loadData(arrayList, false);
        this.mCurrentTagPointList = loadData;
        this.mPointList.addAll(loadData);
        this.mPointList.addAll(this.mSavedTagPointList);
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                VisualLabelAdapter.this.notifyDataSetChanged();
            }
        });
        Log.d(TAG, "refreshCurrentTagPoints: " + this.mPointList.size());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_visual_label_point, viewGroup, false));
        }
        return new ViewHolderContent(LayoutInflater.from(this.context).inflate(R.layout.item_visual_label_point_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PointInfo pointInfo = this.mPointList.get(i);
        if (viewHolder instanceof ViewHolder) {
            AppCompatTextView appCompatTextView = ((ViewHolder) viewHolder).tvPointName;
            StringBuilder sb = new StringBuilder();
            sb.append(getPointName(pointInfo.getPointType()));
            sb.append(pointInfo.isSaved ? ContextUtil.getContext().getString(R.string.text_tag_point_cache) : "");
            appCompatTextView.setText(sb.toString());
        } else if (viewHolder instanceof ViewHolderContent) {
            ViewHolderContent viewHolderContent = (ViewHolderContent) viewHolder;
            final String name = pointInfo.getWaypointsBean().getName();
            viewHolderContent.tvPointName.setText(name);
            if (pointInfo.isSaved) {
                viewHolderContent.rlDelete.setVisibility(0);
                viewHolderContent.tvPointName.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.clr_2A2A2A));
                ClickUtils.applyGlobalDebouncing((View) viewHolderContent.rlDelete, (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        if (VisualLabelAdapter.this.clickListener != null) {
                            VisualLabelAdapter.this.clickListener.onItemDeleteClick(name);
                        }
                    }
                });
                return;
            }
            viewHolderContent.rlDelete.setVisibility(4);
            viewHolderContent.tvPointName.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.clr_838486));
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
            this.tvPointName = (AppCompatTextView) view.findViewById(R.id.tv_item_point_name);
            this.rlDelete = (RelativeLayout) view.findViewById(R.id.rl_point_delete);
            this.rlEdit = (RelativeLayout) view.findViewById(R.id.rl_point_edit);
            this.rlNavigation = (RelativeLayout) view.findViewById(R.id.rl_point_navigation);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    private List<PointInfo> loadData(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list, boolean z) {
        return getPointInfoOfGroup(list, z);
    }

    private List<PointInfo> getPointInfoOfGroup(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list, boolean z) {
        HashMap hashMap = new HashMap();
        for (ApriltagsBufferBean.MsgBean.WaypointsBean next : list) {
            int behavior_code = next.getBehavior_code();
            if (hashMap.containsKey(behavior_code + "")) {
                ((List) hashMap.get(behavior_code + "")).add(next);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                hashMap.put(behavior_code + "", arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            int parseInt = Integer.parseInt((String) entry.getKey());
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(0, new PointInfo(parseInt));
            for (ApriltagsBufferBean.MsgBean.WaypointsBean pointInfo : (List) entry.getValue()) {
                arrayList3.add(new PointInfo(parseInt, pointInfo, z));
            }
            arrayList2.addAll(arrayList3);
        }
        return arrayList2;
    }

    private String getPointName(int i) {
        if (i == 20) {
            return this.context.getString(R.string.txt_visual_label_point);
        }
        return "" + i;
    }

    private static class PointInfo {
        /* access modifiers changed from: private */
        public boolean isHead;
        /* access modifiers changed from: private */
        public boolean isSaved;
        private int pointType;
        private ApriltagsBufferBean.MsgBean.WaypointsBean waypointsBean;

        public PointInfo() {
        }

        public PointInfo(int i) {
            this.pointType = i;
            this.isHead = true;
        }

        public PointInfo(int i, ApriltagsBufferBean.MsgBean.WaypointsBean waypointsBean2, boolean z) {
            this.pointType = i;
            this.isSaved = z;
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

        public boolean isSaved() {
            return this.isSaved;
        }

        public ApriltagsBufferBean.MsgBean.WaypointsBean getWaypointsBean() {
            return this.waypointsBean;
        }

        public void setWaypointsBean(ApriltagsBufferBean.MsgBean.WaypointsBean waypointsBean2) {
            this.waypointsBean = waypointsBean2;
        }
    }
}
