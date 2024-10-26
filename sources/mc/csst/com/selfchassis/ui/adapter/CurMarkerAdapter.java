package mc.csst.com.selfchassis.ui.adapter;

import android.view.View;
import com.blankj.utilcode.util.ClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerBean;

public class CurMarkerAdapter extends BaseQuickAdapter<MarkerBean, BaseViewHolder> {
    private int mPosition = -1;
    private OnNavClickListener onNavClickListener;

    public interface OnNavClickListener {
        void onNavClick(MarkerBean markerBean);
    }

    public CurMarkerAdapter(List<MarkerBean> list) {
        super(R.layout.adapter_item_cur_floor, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, MarkerBean markerBean) {
        baseViewHolder.setText((int) R.id.tv_item_floor_name, (CharSequence) markerBean.getName());
        if (baseViewHolder.getAdapterPosition() == this.mPosition) {
            baseViewHolder.setTextColor(R.id.tv_item_floor_name, ContextUtil.getContext().getResources().getColor(R.color.clr_0560FD));
            baseViewHolder.setBackgroundColor(R.id.cl_build_bg, ContextUtil.getContext().getResources().getColor(R.color.clr_1A0560FD));
        } else {
            baseViewHolder.setTextColor(R.id.tv_item_floor_name, ContextUtil.getContext().getResources().getColor(R.color.clr_2A2A2A));
            baseViewHolder.setBackgroundColor(R.id.cl_build_bg, ContextUtil.getContext().getResources().getColor(R.color.clr_FEFEFF));
        }
        ClickUtils.applyGlobalDebouncing(baseViewHolder.getView(R.id.nav_cl), (View.OnClickListener) new View.OnClickListener(markerBean) {
            public final /* synthetic */ MarkerBean f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                CurMarkerAdapter.this.lambda$convert$0$CurMarkerAdapter(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$convert$0$CurMarkerAdapter(MarkerBean markerBean, View view) {
        if (getOnNavClickListener() != null) {
            getOnNavClickListener().onNavClick(markerBean);
        }
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

    public OnNavClickListener getOnNavClickListener() {
        return this.onNavClickListener;
    }

    public void setOnNavClickListener(OnNavClickListener onNavClickListener2) {
        this.onNavClickListener = onNavClickListener2;
    }
}
