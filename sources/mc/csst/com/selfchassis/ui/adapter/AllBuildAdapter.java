package mc.csst.com.selfchassis.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.MarkersDetailsBean;

public class AllBuildAdapter extends BaseQuickAdapter<MarkersDetailsBean.ValuesBean.FloorsBean, BaseViewHolder> {
    private int mPosition = -1;

    public AllBuildAdapter(List<MarkersDetailsBean.ValuesBean.FloorsBean> list) {
        super(R.layout.adapter_item_all_build, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, MarkersDetailsBean.ValuesBean.FloorsBean floorsBean) {
        baseViewHolder.setText((int) R.id.tv_item_build_name, (CharSequence) floorsBean.getFloor_name());
        if (baseViewHolder.getAdapterPosition() == this.mPosition) {
            baseViewHolder.setTextColor(R.id.tv_item_build_name, ContextUtil.getContext().getResources().getColor(R.color.clr_0560FD));
            baseViewHolder.setBackgroundColor(R.id.cl_build_bg, ContextUtil.getContext().getResources().getColor(R.color.clr_1A0560FD));
            return;
        }
        baseViewHolder.setTextColor(R.id.tv_item_build_name, ContextUtil.getContext().getResources().getColor(R.color.clr_2A2A2A));
        baseViewHolder.setBackgroundColor(R.id.cl_build_bg, ContextUtil.getContext().getResources().getColor(R.color.clr_FEFEFF));
    }

    public void setCheckedItem(int i) {
        if (i != this.mPosition) {
            this.mPosition = i;
            notifyDataSetChanged();
        }
    }

    public int getCheckedItem() {
        return this.mPosition;
    }
}
