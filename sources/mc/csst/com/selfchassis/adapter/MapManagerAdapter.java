package mc.csst.com.selfchassis.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.adapter.MapManagerAdapter;
import mc.csst.com.selfchassis.utils.BanSlidingLayoutManager;
import mc.csst.com.selfchassis.utils.Utils;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;

public class MapManagerAdapter extends RecyclerView.Adapter<ViewHolder> {
    OnItemClickListener clickListener;
    private List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> mBuildList;

    public interface OnItemClickListener {
        void onItemDeleteClick(String str, String str2);

        void onItemEditClick(String str, String str2);
    }

    public void refreshData(List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list) {
        this.mBuildList = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_map_manager, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean = this.mBuildList.get(i);
        viewHolder.tvBuildName.setText(listInfoBean.getBuilding_name());
        viewHolder.mapNameRv.setLayoutManager(new BanSlidingLayoutManager(viewHolder.itemView.getContext()));
        MapNameAdapter mapNameAdapter = new MapNameAdapter(listInfoBean);
        viewHolder.mapNameRv.setAdapter(mapNameAdapter);
        mapNameAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemDeleteClick(String str, String str2) {
                if (MapManagerAdapter.this.clickListener != null && Utils.isFastClick(1000)) {
                    MapManagerAdapter.this.clickListener.onItemDeleteClick(listInfoBean.getBuilding_name(), str2);
                }
            }

            public void onItemEditClick(String str, String str2) {
                if (MapManagerAdapter.this.clickListener != null && Utils.isFastClick(1000)) {
                    MapManagerAdapter.this.clickListener.onItemEditClick(listInfoBean.getBuilding_name(), str2);
                }
            }
        });
    }

    public int getItemCount() {
        List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list = this.mBuildList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void remove(String str, String str2) {
        Iterator<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> it = this.mBuildList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            LayeredMapCmdResponseBean.ValuesBean.ListInfoBean next = it.next();
            if (str.equals(next.getBuilding_name())) {
                for (LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean next2 : next.getFloor_info()) {
                    if (str2.equals(next2.getFloor_name())) {
                        next.getFloor_info().remove(next2);
                        if (next.getFloor_info().size() == 0) {
                            this.mBuildList.remove(next);
                        }
                    }
                }
                continue;
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mapNameRv;
        AppCompatTextView tvBuildName;

        public ViewHolder(View view) {
            super(view);
            this.tvBuildName = (AppCompatTextView) view.findViewById(R.id.tv_item_map_manage_name);
            this.mapNameRv = (RecyclerView) view.findViewById(R.id.rv_map_manager);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    static class MapNameAdapter extends RecyclerView.Adapter<ViewHolder> {
        OnItemClickListener clickListener;
        private final LayeredMapCmdResponseBean.ValuesBean.ListInfoBean mListInfoBean;
        private final List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean> mMapNameList;

        public MapNameAdapter(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean) {
            this.mListInfoBean = listInfoBean;
            this.mMapNameList = listInfoBean.getFloor_info();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_map_manager_item, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean floorInfoBean = this.mMapNameList.get(i);
            viewHolder.tvFloorName.setText(floorInfoBean.getFloor_name());
            String floor = ShowSelfChassisBean.getInstance().getFloor();
            String build = ShowSelfChassisBean.getInstance().getBuild();
            if (TextUtils.equals(floor, floorInfoBean.getFloor_name()) && TextUtils.equals(build, this.mListInfoBean.getBuilding_name())) {
                viewHolder.rlEdit.setVisibility(4);
                viewHolder.rlDelete.setVisibility(4);
            }
            viewHolder.rlDelete.setOnClickListener(new View.OnClickListener(floorInfoBean) {
                public final /* synthetic */ LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MapManagerAdapter.MapNameAdapter.this.lambda$onBindViewHolder$0$MapManagerAdapter$MapNameAdapter(this.f$1, view);
                }
            });
            viewHolder.rlEdit.setOnClickListener(new View.OnClickListener(floorInfoBean) {
                public final /* synthetic */ LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    MapManagerAdapter.MapNameAdapter.this.lambda$onBindViewHolder$1$MapManagerAdapter$MapNameAdapter(this.f$1, view);
                }
            });
        }

        public /* synthetic */ void lambda$onBindViewHolder$0$MapManagerAdapter$MapNameAdapter(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean floorInfoBean, View view) {
            OnItemClickListener onItemClickListener = this.clickListener;
            if (onItemClickListener != null) {
                onItemClickListener.onItemDeleteClick((String) null, floorInfoBean.getFloor_name());
            }
        }

        public /* synthetic */ void lambda$onBindViewHolder$1$MapManagerAdapter$MapNameAdapter(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean floorInfoBean, View view) {
            OnItemClickListener onItemClickListener = this.clickListener;
            if (onItemClickListener != null) {
                onItemClickListener.onItemEditClick((String) null, floorInfoBean.getFloor_name());
            }
        }

        public int getItemCount() {
            List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean> list = this.mMapNameList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout rlDelete;
            RelativeLayout rlEdit;
            AppCompatTextView tvFloorName;

            public ViewHolder(View view) {
                super(view);
                this.tvFloorName = (AppCompatTextView) view.findViewById(R.id.tv_item_map_name);
                this.rlDelete = (RelativeLayout) view.findViewById(R.id.rl_map_delete);
                this.rlEdit = (RelativeLayout) view.findViewById(R.id.rl_map_edit);
            }
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.clickListener = onItemClickListener;
        }
    }
}
