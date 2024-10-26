package mc.csst.com.selfchassis.adapter;

import android.text.TextUtils;
import android.view.View;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.OnlineUpdateUrlBean;

public class OnlineUpdateUrlAdapter extends BaseQuickAdapter<OnlineUpdateUrlBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public int mSelectPosition = -1;

    public interface OnItemClickListener {
        void onDelete(OnlineUpdateUrlBean onlineUpdateUrlBean);

        void onItemClick(OnlineUpdateUrlBean onlineUpdateUrlBean);
    }

    public OnlineUpdateUrlAdapter() {
        super((int) R.layout.adapter_item_update_url);
    }

    /* access modifiers changed from: protected */
    public void convert(final BaseViewHolder baseViewHolder, final OnlineUpdateUrlBean onlineUpdateUrlBean) {
        if (onlineUpdateUrlBean != null) {
            int urlDescType = onlineUpdateUrlBean.getUrlDescType();
            String urlAlias = onlineUpdateUrlBean.getUrlAlias();
            String url = onlineUpdateUrlBean.getUrl();
            boolean isDefault = onlineUpdateUrlBean.isDefault();
            baseViewHolder.setGone(R.id.tv_url_desc, true);
            if (urlDescType == 0) {
                baseViewHolder.setText((int) R.id.tv_url_desc, (CharSequence) StringUtils.getString(R.string.txt_li_ce));
                baseViewHolder.setGone(R.id.img_delete, false);
            } else if (urlDescType == 1) {
                baseViewHolder.setText((int) R.id.tv_url_desc, (CharSequence) StringUtils.getString(R.string.txt_lan_hai));
                baseViewHolder.setGone(R.id.img_delete, false);
            } else if (urlDescType == 3) {
                baseViewHolder.setText((int) R.id.tv_url_desc, (CharSequence) StringUtils.getString(R.string.txt_li_ce));
                baseViewHolder.setGone(R.id.img_delete, false);
            } else {
                baseViewHolder.setGone(R.id.tv_url_desc, false);
                baseViewHolder.setGone(R.id.img_delete, true);
            }
            if (this.mSelectPosition == baseViewHolder.getAdapterPosition()) {
                baseViewHolder.setBackgroundRes(R.id.img_check, R.drawable.rb_check_bg);
                baseViewHolder.setTextColor(R.id.tv_url, ColorUtils.getColor(R.color.clr_0560FD));
                baseViewHolder.setGone(R.id.img_delete, false);
                baseViewHolder.setTextColor(R.id.tv_url_desc, ColorUtils.getColor(R.color.clr_0560FD));
                baseViewHolder.setBackgroundRes(R.id.tv_url_desc, R.drawable.selector_rectangle_white_5);
            } else {
                baseViewHolder.setBackgroundRes(R.id.img_check, R.drawable.rb_uncheck_bg);
                baseViewHolder.setTextColor(R.id.tv_url, ColorUtils.getColor(R.color.clr_333333));
                baseViewHolder.setTextColor(R.id.tv_url_desc, ColorUtils.getColor(R.color.clr_333333));
                baseViewHolder.setBackgroundRes(R.id.tv_url_desc, R.drawable.selector_rectangle_black_5);
            }
            if (isDefault) {
                if (TextUtils.isEmpty(urlAlias)) {
                    urlAlias = url;
                }
                baseViewHolder.setText((int) R.id.tv_url, (CharSequence) urlAlias);
            } else {
                baseViewHolder.setText((int) R.id.tv_url, (CharSequence) url);
            }
            baseViewHolder.setOnClickListener(R.id.img_delete, new ClickUtils.OnDebouncingClickListener() {
                public void onDebouncingClick(View view) {
                    if (OnlineUpdateUrlAdapter.this.mOnItemClickListener != null) {
                        OnlineUpdateUrlAdapter.this.mOnItemClickListener.onDelete(onlineUpdateUrlBean);
                    }
                }
            });
            baseViewHolder.setOnClickListener(R.id.cl_root, new ClickUtils.OnDebouncingClickListener() {
                public void onDebouncingClick(View view) {
                    int unused = OnlineUpdateUrlAdapter.this.mSelectPosition = baseViewHolder.getAdapterPosition();
                    if (OnlineUpdateUrlAdapter.this.mOnItemClickListener != null) {
                        OnlineUpdateUrlAdapter.this.mOnItemClickListener.onItemClick((OnlineUpdateUrlBean) OnlineUpdateUrlAdapter.this.mData.get(OnlineUpdateUrlAdapter.this.mSelectPosition));
                    }
                    OnlineUpdateUrlAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void refreshData(List<OnlineUpdateUrlBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int i) {
        this.mSelectPosition = i;
        notifyDataSetChanged();
    }
}
