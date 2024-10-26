package mc.csst.com.selfchassis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ClickUtils;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;

public class BuildAdapter extends RecyclerView.Adapter<ViewHolder> {
    OnItemClickListener clickListener;
    /* access modifiers changed from: private */
    public int clickPosition = -1;
    private final Context context;
    private List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> mBuildList;

    public interface OnItemClickListener {
        void onItemClick(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean);
    }

    public BuildAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list) {
        this.mBuildList = list;
        this.clickPosition = -1;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_build, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean = this.mBuildList.get(i);
        viewHolder.tvBuildName.setText(listInfoBean.getBuilding_name());
        ClickUtils.applyGlobalDebouncing(viewHolder.itemView, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                if (BuildAdapter.this.clickListener != null) {
                    BuildAdapter.this.clickListener.onItemClick(listInfoBean);
                }
                int unused = BuildAdapter.this.clickPosition = i;
                BuildAdapter.this.notifyDataSetChanged();
            }
        });
        if (i == this.clickPosition) {
            viewHolder.itemView.setBackgroundColor(this.context.getResources().getColor(R.color.clr_1A0560FD));
            viewHolder.tvBuildName.setTextColor(this.context.getResources().getColor(R.color.clr_0560FD));
            return;
        }
        viewHolder.tvBuildName.setTextColor(this.context.getResources().getColor(R.color.clr_2A2A2A));
        viewHolder.itemView.setBackgroundColor(this.context.getResources().getColor(R.color.clr_FEFEFF));
    }

    public int getItemCount() {
        List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list = this.mBuildList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBuildName;

        public ViewHolder(View view) {
            super(view);
            this.tvBuildName = (TextView) view.findViewById(R.id.tv_item_build_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }
}
