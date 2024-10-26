package mc.csst.com.selfchassis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassis.utils.enumbs.ConvertChassisAreaType;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;

public class AreaAdapter extends RecyclerView.Adapter<ViewHolder> {
    int mAreaType = 0;
    List<AreaItemBean> mData;
    /* access modifiers changed from: private */
    public ItemOnClickListener mItemOnClickListener;
    int mSelectedIndex = -1;

    public interface ItemOnClickListener {
        void bindItem(AreaItemBean areaItemBean);

        void delItem(AreaItemBean areaItemBean);

        void selectedItem(AreaItemBean areaItemBean);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(ContextUtil.getContext()).inflate(R.layout.item_area, viewGroup, false));
    }

    public AreaAdapter(List<AreaItemBean> list, String str) {
        this.mAreaType = ConvertChassisAreaType.getChassisAreaTypeByToolType(str);
        this.mData = list;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        AreaItemBean areaItemBean = this.mData.get(i);
        String name = areaItemBean.getName();
        int type = areaItemBean.getType();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
        if (this.mAreaType != type) {
            viewHolder.mClAreaBg.setVisibility(8);
            layoutParams.width = 0;
            layoutParams.height = 0;
        } else {
            viewHolder.mClAreaBg.setVisibility(0);
            layoutParams.width = AdaptScreenUtils.pt2Px(444.0f);
            layoutParams.height = AdaptScreenUtils.pt2Px(64.0f);
            if (this.mAreaType == 7) {
                viewHolder.mClBindPoint.setVisibility(0);
            } else {
                viewHolder.mClBindPoint.setVisibility(8);
            }
        }
        viewHolder.itemView.setLayoutParams(layoutParams);
        ClickUtils.applyGlobalDebouncing((View) viewHolder.mClAreaBg, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                if (AreaAdapter.this.mItemOnClickListener != null) {
                    AreaAdapter.this.mSelectedIndex = viewHolder.getAdapterPosition();
                    AreaAdapter.this.mItemOnClickListener.selectedItem(AreaAdapter.this.mData.get(viewHolder.getAdapterPosition()));
                    AreaAdapter.this.notifyDataSetChanged();
                }
            }
        });
        ClickUtils.applyGlobalDebouncing((View) viewHolder.mClAreaDel, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                if (AreaAdapter.this.mItemOnClickListener != null) {
                    AreaAdapter.this.mItemOnClickListener.delItem(AreaAdapter.this.mData.get(viewHolder.getAdapterPosition()));
                }
            }
        });
        ClickUtils.applyGlobalDebouncing((View) viewHolder.mClBindPoint, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                if (AreaAdapter.this.mItemOnClickListener != null) {
                    AreaAdapter.this.mItemOnClickListener.bindItem(AreaAdapter.this.mData.get(viewHolder.getAdapterPosition()));
                }
            }
        });
        viewHolder.mTvAreaName.setText(name);
        if (i == this.mSelectedIndex) {
            viewHolder.mClAreaBg.setBackgroundColor(ContextUtil.getContext().getResources().getColor(R.color.clr_E6EEFF));
            viewHolder.mTvAreaName.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.clr_146AFD));
            viewHolder.mIvAreaDel.setBackgroundResource(R.mipmap.icon_delete_press);
            viewHolder.mIvBindPoint.setBackgroundResource(R.mipmap.icon_bind_press);
            return;
        }
        viewHolder.mClAreaBg.setBackgroundColor(ContextUtil.getContext().getResources().getColor(R.color.clr_FEFEFF));
        viewHolder.mTvAreaName.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.clr_333333));
        viewHolder.mIvAreaDel.setBackgroundResource(R.mipmap.icon_delete);
        viewHolder.mIvBindPoint.setBackgroundResource(R.mipmap.icon_bind_normal);
    }

    public int getItemCount() {
        List<AreaItemBean> list = this.mData;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void refreshData(List<AreaItemBean> list, String str) {
        this.mAreaType = ConvertChassisAreaType.getChassisAreaTypeByToolType(str);
        this.mData = list;
        this.mSelectedIndex = -1;
        notifyDataSetChanged();
    }

    public void setSelectedIndex(int i) {
        this.mSelectedIndex = i;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mClAreaBg;
        ConstraintLayout mClAreaDel;
        ConstraintLayout mClBindPoint;
        AppCompatImageView mIvAreaDel;
        AppCompatImageView mIvBindPoint;
        AppCompatTextView mTvAreaName;

        public ViewHolder(View view) {
            super(view);
            this.mClAreaBg = (ConstraintLayout) view.findViewById(R.id.cl_area_bg);
            this.mClAreaDel = (ConstraintLayout) view.findViewById(R.id.cl_area_del);
            this.mClBindPoint = (ConstraintLayout) view.findViewById(R.id.cl_bind_point);
            this.mTvAreaName = (AppCompatTextView) view.findViewById(R.id.tv_area_name);
            this.mIvAreaDel = (AppCompatImageView) view.findViewById(R.id.iv_area_del);
            this.mIvBindPoint = (AppCompatImageView) view.findViewById(R.id.iv_bind_point);
        }
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.mItemOnClickListener = itemOnClickListener;
    }
}
