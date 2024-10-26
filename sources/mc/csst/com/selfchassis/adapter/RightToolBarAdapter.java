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
import mc.csst.com.selfchassis.utils.bean.ToolbarBean;

public class RightToolBarAdapter extends BaseQuickAdapter<ToolbarBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public int mSelectPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(ToolbarBean toolbarBean);
    }

    public RightToolBarAdapter() {
        super((int) R.layout.adapter_right_toolbar);
    }

    /* access modifiers changed from: protected */
    public void convert(final BaseViewHolder baseViewHolder, ToolbarBean toolbarBean) {
        if (toolbarBean != null) {
            baseViewHolder.setText((int) R.id.right_toolbar_tv, (CharSequence) toolbarBean.getLabelText());
            baseViewHolder.setBackgroundRes(R.id.right_toolbar_iv, toolbarBean.getIconBg());
            baseViewHolder.setTextColor(R.id.right_toolbar_tv, ContextUtil.getContext().getResources().getColor(R.color.clr_333333));
            baseViewHolder.setOnClickListener(R.id.right_toolbar_cl, new ClickUtils.OnDebouncingClickListener() {
                public void onDebouncingClick(View view) {
                    int unused = RightToolBarAdapter.this.mSelectPosition = baseViewHolder.getAdapterPosition();
                    if (RightToolBarAdapter.this.mSelectPosition != -1) {
                        if (RightToolBarAdapter.this.mOnItemClickListener != null) {
                            RightToolBarAdapter.this.mOnItemClickListener.onItemClick((ToolbarBean) RightToolBarAdapter.this.mData.get(RightToolBarAdapter.this.mSelectPosition));
                        }
                        RightToolBarAdapter.this.notifyDataSetChanged();
                    }
                }
            });
            if (baseViewHolder.getAdapterPosition() == 0) {
                baseViewHolder.setBackgroundRes(R.id.right_toolbar_cl, R.drawable.selector_common_top_left_radius_40);
            } else if (this.mData == null || baseViewHolder.getAdapterPosition() != this.mData.size() - 1) {
                baseViewHolder.setBackgroundRes(R.id.right_toolbar_cl, R.drawable.selector_common);
            } else {
                baseViewHolder.setBackgroundRes(R.id.right_toolbar_cl, R.drawable.selector_common_bottom_left_radius_40);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setSelectPosition(int i) {
        this.mSelectPosition = i;
        notifyDataSetChanged();
    }

    public void refreshData(List<ToolbarBean> list) {
        if (list != null) {
            Iterator<ToolbarBean> it = list.iterator();
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
}
