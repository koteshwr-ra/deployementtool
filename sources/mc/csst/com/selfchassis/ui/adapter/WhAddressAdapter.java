package mc.csst.com.selfchassis.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassis.ui.adapter.WhAddressAdapter;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.CommonAddressBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.transaction.GatewayTool;
import org.apache.commons.lang3.StringUtils;

public class WhAddressAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<CommonAddressBean> mDataList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void delUrl(CommonAddressBean commonAddressBean);

        void editUrl(CommonAddressBean commonAddressBean);

        void selectedUrl(CommonAddressBean commonAddressBean);
    }

    public void setDataList(List<CommonAddressBean> list) {
        this.mDataList = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(ContextUtil.getContext()).inflate(R.layout.gateway_address_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CommonAddressBean commonAddressBean = this.mDataList.get(i);
        showIcon(viewHolder, !commonAddressBean.isWuhan());
        selectedItem(viewHolder, commonAddressBean.getUrl(), i);
        viewHolder.urlTv.setText(getUrlDisplayName(commonAddressBean.getUrl()));
        viewHolder.rootCl.setOnClickListener(new View.OnClickListener(commonAddressBean, viewHolder) {
            public final /* synthetic */ CommonAddressBean f$1;
            public final /* synthetic */ WhAddressAdapter.ViewHolder f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                WhAddressAdapter.this.lambda$onBindViewHolder$0$WhAddressAdapter(this.f$1, this.f$2, view);
            }
        });
        viewHolder.editIv.setOnClickListener(new View.OnClickListener(commonAddressBean) {
            public final /* synthetic */ CommonAddressBean f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                WhAddressAdapter.this.lambda$onBindViewHolder$1$WhAddressAdapter(this.f$1, view);
            }
        });
        viewHolder.delIv.setOnClickListener(new View.OnClickListener(commonAddressBean) {
            public final /* synthetic */ CommonAddressBean f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                WhAddressAdapter.this.lambda$onBindViewHolder$2$WhAddressAdapter(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$WhAddressAdapter(CommonAddressBean commonAddressBean, ViewHolder viewHolder, View view) {
        this.mOnItemClickListener.selectedUrl(commonAddressBean);
        int i = 0;
        while (i < this.mDataList.size()) {
            this.mDataList.get(i).setSelected(i == viewHolder.getAdapterPosition());
            i++;
        }
        notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$WhAddressAdapter(CommonAddressBean commonAddressBean, View view) {
        this.mOnItemClickListener.editUrl(commonAddressBean);
    }

    public /* synthetic */ void lambda$onBindViewHolder$2$WhAddressAdapter(CommonAddressBean commonAddressBean, View view) {
        this.mOnItemClickListener.delUrl(commonAddressBean);
    }

    private String getUrlDisplayName(String str) {
        for (String next : GatewayTool.abbreviationUrlMap.keySet()) {
            if (next.equalsIgnoreCase(str)) {
                return GatewayTool.abbreviationUrlMap.get(next) + StringUtils.SPACE + str;
            }
        }
        return str;
    }

    private void selectedItem(ViewHolder viewHolder, String str, int i) {
        if (this.mDataList.get(i).isSelected()) {
            viewHolder.selectedIv.setImageResource(R.drawable.rb_check_bg);
        } else {
            viewHolder.selectedIv.setImageResource(R.drawable.rb_uncheck_bg);
        }
    }

    private void showIcon(ViewHolder viewHolder, boolean z) {
        if (z) {
            viewHolder.delIv.setVisibility(0);
            viewHolder.editIv.setVisibility(0);
            return;
        }
        viewHolder.delIv.setVisibility(8);
        viewHolder.editIv.setVisibility(8);
    }

    public int getItemCount() {
        List<CommonAddressBean> list = this.mDataList;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View delIv;
        View editIv;
        ConstraintLayout rootCl;
        AppCompatImageView selectedIv;
        AppCompatTextView urlTv;

        public ViewHolder(View view) {
            super(view);
            this.rootCl = (ConstraintLayout) view.findViewById(R.id.item_wh_address_root_cl);
            this.urlTv = (AppCompatTextView) view.findViewById(R.id.item_wh_address_url_tv);
            this.selectedIv = (AppCompatImageView) view.findViewById(R.id.item_wh_address_selected_iv);
            this.editIv = view.findViewById(R.id.item_wh_address_edit);
            this.delIv = view.findViewById(R.id.item_wh_address_del);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
