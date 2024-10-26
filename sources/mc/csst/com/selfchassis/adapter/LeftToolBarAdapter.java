package mc.csst.com.selfchassis.adapter;

import android.view.View;
import com.blankj.utilcode.util.ClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.LeftToolbarBean;

public class LeftToolBarAdapter extends BaseQuickAdapter<LeftToolbarBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public int mSelectPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(LeftToolbarBean leftToolbarBean);
    }

    public LeftToolBarAdapter() {
        super((int) R.layout.adapter_left_toolbar);
    }

    /* access modifiers changed from: protected */
    public void convert(final BaseViewHolder baseViewHolder, LeftToolbarBean leftToolbarBean) {
        if (leftToolbarBean != null) {
            baseViewHolder.setText((int) R.id.tv_left_toolbar, (CharSequence) leftToolbarBean.getLabelText());
            if (this.mSelectPosition == baseViewHolder.getAdapterPosition()) {
                baseViewHolder.setBackgroundRes(R.id.img_left_toolbar, leftToolbarBean.getIconSelectBg());
                baseViewHolder.setTextColor(R.id.tv_left_toolbar, ContextUtil.getContext().getResources().getColor(R.color.clr_0560FD));
            } else {
                baseViewHolder.setBackgroundRes(R.id.img_left_toolbar, leftToolbarBean.getIconBg());
                baseViewHolder.setTextColor(R.id.tv_left_toolbar, ContextUtil.getContext().getResources().getColor(R.color.clr_333333));
            }
            baseViewHolder.setOnClickListener(R.id.cl_left_toolbar, new ClickUtils.OnDebouncingClickListener() {
                public void onDebouncingClick(View view) {
                    int unused = LeftToolBarAdapter.this.mSelectPosition = baseViewHolder.getAdapterPosition();
                    if (LeftToolBarAdapter.this.mOnItemClickListener != null) {
                        LeftToolBarAdapter.this.mOnItemClickListener.onItemClick((LeftToolbarBean) LeftToolBarAdapter.this.mData.get(LeftToolBarAdapter.this.mSelectPosition));
                    }
                    LeftToolBarAdapter.this.notifyDataSetChanged();
                }
            });
            if (baseViewHolder.getAdapterPosition() == 0) {
                baseViewHolder.setBackgroundRes(R.id.cl_left_toolbar, R.drawable.selector_common_top_radius_10);
            } else if (this.mData == null || baseViewHolder.getAdapterPosition() != this.mData.size() - 1) {
                baseViewHolder.setBackgroundRes(R.id.cl_left_toolbar, R.drawable.selector_common);
            } else {
                baseViewHolder.setBackgroundRes(R.id.cl_left_toolbar, R.drawable.selector_common_bottom_radius_10);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void refreshData(List<LeftToolbarBean> list) {
        if (list != null) {
            Iterator<LeftToolbarBean> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().isHide()) {
                    it.remove();
                }
            }
        } else {
            list = new ArrayList<>();
        }
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int i) {
        this.mSelectPosition = i;
        notifyDataSetChanged();
    }
}
