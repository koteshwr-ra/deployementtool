package mc.csst.com.selfchassis.adapter;

import android.view.View;
import com.blankj.utilcode.util.ClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.TopMenuBean;

public class TopMenuAdapter extends BaseQuickAdapter<TopMenuBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public int mSelectPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(TopMenuBean topMenuBean);
    }

    public TopMenuAdapter() {
        super((int) R.layout.adapter_item_top_menu);
    }

    /* access modifiers changed from: protected */
    public void convert(final BaseViewHolder baseViewHolder, TopMenuBean topMenuBean) {
        if (topMenuBean != null) {
            baseViewHolder.setText((int) R.id.tv_menu, (CharSequence) topMenuBean.getLabelText());
            baseViewHolder.setBackgroundRes(R.id.img_menu, topMenuBean.getIconBg());
            baseViewHolder.setOnClickListener(R.id.cl_top_menu, new ClickUtils.OnDebouncingClickListener() {
                public void onDebouncingClick(View view) {
                    int unused = TopMenuAdapter.this.mSelectPosition = baseViewHolder.getAdapterPosition();
                    if (TopMenuAdapter.this.mOnItemClickListener != null) {
                        TopMenuAdapter.this.mOnItemClickListener.onItemClick((TopMenuBean) TopMenuAdapter.this.mData.get(TopMenuAdapter.this.mSelectPosition));
                    }
                    TopMenuAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void refreshData(List<TopMenuBean> list) {
        if (list != null) {
            Iterator<TopMenuBean> it = list.iterator();
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
