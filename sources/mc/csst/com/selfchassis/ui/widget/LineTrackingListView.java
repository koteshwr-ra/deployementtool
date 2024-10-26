package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.PathGetResponseBean;

public class LineTrackingListView extends FrameLayout {
    private View cl_line_tracking_list_title;
    /* access modifiers changed from: private */
    public ImageView iv_line_tracking_arrow;
    private ListAdapter listAdapter;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public RecyclerView rv_line_tracking_list;

    public interface OnItemClickListener {
        void onDeleteClick(String str);

        void onSelectedIndex(int i);
    }

    public void setPathGetResponseBean(PathGetResponseBean pathGetResponseBean) {
        this.listAdapter.setNodes((pathGetResponseBean == null || pathGetResponseBean.getValues() == null || pathGetResponseBean.getValues().getAdjacent_list() == null || pathGetResponseBean.getValues().getAdjacent_list().getNodes() == null) ? null : pathGetResponseBean.getValues().getAdjacent_list().getNodes());
    }

    public LineTrackingListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LineTrackingListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LineTrackingListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(LayoutInflater.from(context).inflate(R.layout.line_tracking_list, this, true));
    }

    private void initView(View view) {
        this.cl_line_tracking_list_title = view.findViewById(R.id.cl_line_tracking_list_title);
        this.iv_line_tracking_arrow = (ImageView) view.findViewById(R.id.iv_line_tracking_arrow);
        this.rv_line_tracking_list = (RecyclerView) view.findViewById(R.id.rv_line_tracking_list);
        ListAdapter listAdapter2 = new ListAdapter();
        this.listAdapter = listAdapter2;
        this.rv_line_tracking_list.setAdapter(listAdapter2);
        this.cl_line_tracking_list_title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LineTrackingListView.this.rv_line_tracking_list.getVisibility() == 0) {
                    LineTrackingListView.this.rv_line_tracking_list.setVisibility(8);
                    LineTrackingListView.this.iv_line_tracking_arrow.setBackground(LineTrackingListView.this.getContext().getDrawable(R.mipmap.right_arrow));
                    return;
                }
                LineTrackingListView.this.rv_line_tracking_list.setVisibility(0);
                LineTrackingListView.this.iv_line_tracking_arrow.setBackground(LineTrackingListView.this.getContext().getDrawable(R.mipmap.down_arrow));
            }
        });
    }

    class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
        /* access modifiers changed from: private */
        public List<PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean> nodes;
        private String textFormat = "%s - %s ";

        ListAdapter() {
        }

        public void setNodes(List<PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean> list) {
            this.nodes = list;
            notifyDataSetChanged();
        }

        public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_link_tracking, viewGroup, false));
        }

        public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
            listViewHolder.tv_line_tracking_path_name.setText(String.format(this.textFormat, new Object[]{this.nodes.get(i).getStart_node_name(), this.nodes.get(i).getGoal_node_name()}));
        }

        public int getItemCount() {
            List<PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean> list = this.nodes;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        class ListViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_line_tracking_path_name;

            public ListViewHolder(View view) {
                super(view);
                this.tv_line_tracking_path_name = (TextView) view.findViewById(R.id.tv_line_tracking_path_name);
                view.setOnClickListener(new View.OnClickListener(ListAdapter.this) {
                    public void onClick(View view) {
                        if (LineTrackingListView.this.onItemClickListener != null) {
                            LineTrackingListView.this.onItemClickListener.onSelectedIndex(ListViewHolder.this.getAdapterPosition());
                        }
                    }
                });
                view.findViewById(R.id.rl_line_tracking_delete).setOnClickListener(new View.OnClickListener(ListAdapter.this) {
                    public void onClick(View view) {
                        if (LineTrackingListView.this.onItemClickListener != null) {
                            LineTrackingListView.this.onItemClickListener.onSelectedIndex(ListViewHolder.this.getAdapterPosition());
                            LineTrackingListView.this.onItemClickListener.onDeleteClick(((PathGetResponseBean.ValuesBean.AdjacentListBean.NodesBean) ListAdapter.this.nodes.get(ListViewHolder.this.getAdapterPosition())).getPath_name());
                        }
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}
