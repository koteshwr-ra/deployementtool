package mc.csst.com.selfchassis.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.sentrymove.R;
import java.util.List;
import javax.annotation.Nullable;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;

public class ChooseMarkerAdapter extends BaseQuickAdapter<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean, BaseViewHolder> {
    private int mPosition;

    public ChooseMarkerAdapter(@Nullable List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        super(R.layout.adapter_item_choose_marker, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        if (baseViewHolder.getAdapterPosition() == this.mPosition) {
            baseViewHolder.setBackgroundRes(R.id.point_img, R.drawable.rb_check);
        } else {
            baseViewHolder.setBackgroundRes(R.id.point_img, R.drawable.rb_uncheck_bg);
        }
        baseViewHolder.setText((int) R.id.point_tv, (CharSequence) waypointsBean.getName());
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
