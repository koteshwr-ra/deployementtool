package mc.csst.com.selfchassis.adapter;

import android.view.View;
import com.blankj.utilcode.util.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.bean.LayerBean;

public class LayerSelectAdapter extends BaseQuickAdapter<LayerBean, BaseViewHolder> {
    private OnSelectListener mOnSelectListener;
    private int mSelectPosition = -1;

    public interface OnSelectListener {
        void onCheck(LayerBean layerBean, boolean z);
    }

    public LayerSelectAdapter(List<LayerBean> list) {
        super(R.layout.adapter_item_layer_select, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, LayerBean layerBean) {
        if (this.mSelectPosition == baseViewHolder.getAdapterPosition()) {
            baseViewHolder.setBackgroundColor(R.id.cl_layer_root, ColorUtils.getColor(R.color.clr_1A0560FD));
        } else {
            baseViewHolder.setBackgroundRes(R.id.cl_layer_root, R.drawable.selector_common);
        }
        int selectStatus = layerBean.getSelectStatus();
        boolean isCanEditBySoftType = LayerDataUtils.getInstance().isCanEditBySoftType();
        baseViewHolder.setImageResource(R.id.img_layer, (selectStatus != -1 || !isCanEditBySoftType) ? selectStatus == 0 ? R.mipmap.check_box_normal : R.mipmap.check_box_select : R.drawable.check_box_enable);
        baseViewHolder.setText((int) R.id.tv_layer, (CharSequence) layerBean.getName());
        baseViewHolder.setOnClickListener(R.id.img_layer, new View.OnClickListener(baseViewHolder, selectStatus, isCanEditBySoftType, layerBean) {
            public final /* synthetic */ BaseViewHolder f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ boolean f$3;
            public final /* synthetic */ LayerBean f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onClick(View view) {
                LayerSelectAdapter.this.lambda$convert$0$LayerSelectAdapter(this.f$1, this.f$2, this.f$3, this.f$4, view);
            }
        });
    }

    public /* synthetic */ void lambda$convert$0$LayerSelectAdapter(BaseViewHolder baseViewHolder, int i, boolean z, LayerBean layerBean, View view) {
        this.mSelectPosition = baseViewHolder.getAdapterPosition();
        if (i != -1 || !z) {
            if (i != 0) {
                layerBean.setSelectStatus(0);
            } else {
                layerBean.setSelectStatus(1);
            }
        }
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.onCheck(layerBean, checkAllUnSelect());
        }
        notifyDataSetChanged();
    }

    public void setSelectPosition(int i) {
        this.mSelectPosition = i;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return this.mSelectPosition;
    }

    public void refreshData(List<LayerBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mData = list;
        notifyDataSetChanged();
    }

    private boolean checkAllUnSelect() {
        for (int i = 0; i < this.mData.size(); i++) {
            if (((LayerBean) this.mData.get(i)).getSelectStatus() != 0) {
                return false;
            }
        }
        return true;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }
}
