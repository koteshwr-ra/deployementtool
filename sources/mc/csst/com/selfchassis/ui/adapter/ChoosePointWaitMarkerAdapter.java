package mc.csst.com.selfchassis.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.base.util.ToastUtil;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;

public class ChoosePointWaitMarkerAdapter extends BaseQuickAdapter<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean, BaseViewHolder> {
    private ArrayList<String> selected = new ArrayList<>();

    public ChoosePointWaitMarkerAdapter(@Nullable List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        super(R.layout.adapter_item_choose_marker, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        ArrayList<String> arrayList = this.selected;
        if (arrayList.contains(baseViewHolder.getAdapterPosition() + "")) {
            baseViewHolder.setBackgroundRes(R.id.point_img, R.drawable.rb_check);
        } else {
            baseViewHolder.setBackgroundRes(R.id.point_img, R.drawable.rb_uncheck_bg);
        }
        baseViewHolder.setText((int) R.id.point_tv, (CharSequence) waypointsBean.getName());
    }

    public void setCheckedItem(int i) {
        if (this.selected.size() >= 2) {
            ArrayList<String> arrayList = this.selected;
            if (arrayList.contains(i + "")) {
                ArrayList<String> arrayList2 = this.selected;
                arrayList2.remove(i + "");
            } else {
                ToastUtil.showShort(this.mContext, this.mContext.getString(R.string.txt_almost_two_bind_wait_point));
            }
            notifyDataSetChanged();
            return;
        }
        ArrayList<String> arrayList3 = this.selected;
        if (!arrayList3.contains(i + "")) {
            ArrayList<String> arrayList4 = this.selected;
            arrayList4.add(i + "");
            notifyDataSetChanged();
        }
    }

    public ArrayList<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> getCheckedItems() {
        ArrayList<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mData.size(); i++) {
            for (int i2 = 0; i2 < this.selected.size(); i2++) {
                if (Integer.parseInt(this.selected.get(i2)) == i) {
                    arrayList.add((MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean) this.mData.get(i));
                }
            }
        }
        return arrayList;
    }
}
