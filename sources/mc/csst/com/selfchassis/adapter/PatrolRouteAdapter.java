package mc.csst.com.selfchassis.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.blankj.utilcode.util.ClickUtils;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;

public class PatrolRouteAdapter extends BaseItemDraggableAdapter<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean, BaseViewHolder> {
    private int mAdapterOperateMode = 0;
    /* access modifiers changed from: private */
    public int mDeletePosition = -1;
    private boolean mIsRight = false;
    private AnimationDrawable mLineAnimationDrawable;
    /* access modifiers changed from: private */
    public OnPatrolRouteItemListener mOnPatrolRouteItemListener;
    private int mPatrolMode = 2;
    private int mSelectPosition = -1;

    public interface AdapterOperateMode {
        public static final int TYPE_DELETE = 0;
        public static final int TYPE_NO_CLICK = -1;
        public static final int TYPE_SELECT = 1;
    }

    public interface OnPatrolRouteItemListener {
        void onItemDeleteClick();
    }

    public PatrolRouteAdapter(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        super(R.layout.adapte_item_patrol_route, list);
    }

    /* access modifiers changed from: protected */
    public void convert(final BaseViewHolder baseViewHolder, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        int i;
        if (this.mData != null && !this.mData.isEmpty()) {
            String name = waypointsBean.getName();
            AppCompatTextView appCompatTextView = (AppCompatTextView) baseViewHolder.getView(R.id.tv_point_name);
            AppCompatImageView appCompatImageView = (AppCompatImageView) baseViewHolder.getView(R.id.img_delete);
            appCompatTextView.setText(name);
            if (baseViewHolder.getAdapterPosition() == this.mData.size() - 1) {
                baseViewHolder.setVisible(R.id.img_line, false);
            } else {
                baseViewHolder.setVisible(R.id.img_line, true);
            }
            int i2 = this.mDeletePosition;
            if (i2 == -1 || i2 != baseViewHolder.getAdapterPosition()) {
                appCompatImageView.setVisibility(8);
                selectPointStyle(baseViewHolder, false);
            } else {
                appCompatImageView.setVisibility(0);
                selectPointStyle(baseViewHolder, true);
            }
            int i3 = this.mAdapterOperateMode;
            if (i3 == 0) {
                baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.line_black);
                appCompatTextView.setOnClickListener(new ClickUtils.OnDebouncingClickListener() {
                    public void onDebouncingClick(View view) {
                        if (PatrolRouteAdapter.this.mDeletePosition == -1 || PatrolRouteAdapter.this.mDeletePosition != baseViewHolder.getAdapterPosition()) {
                            int unused = PatrolRouteAdapter.this.mDeletePosition = baseViewHolder.getAdapterPosition();
                        } else {
                            int unused2 = PatrolRouteAdapter.this.mDeletePosition = -1;
                        }
                        PatrolRouteAdapter.this.notifyDataSetChanged();
                    }
                });
                appCompatImageView.setOnClickListener(new ClickUtils.OnDebouncingClickListener() {
                    public void onDebouncingClick(View view) {
                        int unused = PatrolRouteAdapter.this.mDeletePosition = -1;
                        PatrolRouteAdapter.this.remove(baseViewHolder.getLayoutPosition());
                        if (PatrolRouteAdapter.this.mOnPatrolRouteItemListener != null) {
                            PatrolRouteAdapter.this.mOnPatrolRouteItemListener.onItemDeleteClick();
                        }
                        PatrolRouteAdapter.this.notifyDataSetChanged();
                    }
                });
            } else if (i3 == 1) {
                int i4 = this.mSelectPosition;
                if (i4 == 0) {
                    this.mIsRight = true;
                } else if (i4 == this.mData.size() - 1 && this.mPatrolMode == 3) {
                    this.mIsRight = false;
                }
                int i5 = this.mSelectPosition;
                if (i5 == -1 || i5 != baseViewHolder.getAdapterPosition()) {
                    selectPointStyle(baseViewHolder, false);
                    baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.line_black);
                } else {
                    selectPointStyle(baseViewHolder, true);
                    stopLineAnimation();
                    if (this.mIsRight) {
                        baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.frame_right);
                        AnimationDrawable animationDrawable = (AnimationDrawable) baseViewHolder.getView(R.id.img_line).getBackground();
                        this.mLineAnimationDrawable = animationDrawable;
                        if (!animationDrawable.isRunning()) {
                            this.mLineAnimationDrawable.start();
                        }
                    } else {
                        baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.line_black);
                    }
                }
                if (!this.mIsRight && (i = this.mSelectPosition) != -1 && i - 1 == baseViewHolder.getAdapterPosition()) {
                    stopLineAnimation();
                    baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.frame_left);
                    AnimationDrawable animationDrawable2 = (AnimationDrawable) baseViewHolder.getView(R.id.img_line).getBackground();
                    this.mLineAnimationDrawable = animationDrawable2;
                    if (!animationDrawable2.isRunning()) {
                        this.mLineAnimationDrawable.start();
                    }
                }
            } else {
                baseViewHolder.setBackgroundRes(R.id.img_line, R.drawable.line_black);
                appCompatTextView.setOnClickListener((View.OnClickListener) null);
            }
        }
    }

    private void stopLineAnimation() {
        AnimationDrawable animationDrawable = this.mLineAnimationDrawable;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.mLineAnimationDrawable.stop();
            this.mLineAnimationDrawable.selectDrawable(0);
        }
    }

    private void selectPointStyle(BaseViewHolder baseViewHolder, boolean z) {
        if (z) {
            baseViewHolder.setBackgroundRes(R.id.tv_point_name, R.drawable.shape_rectangle_blue_10);
            baseViewHolder.setTextColor(R.id.tv_point_name, this.mContext.getResources().getColor(R.color.clr_0560FD));
            return;
        }
        baseViewHolder.setBackgroundRes(R.id.tv_point_name, R.drawable.shape_rectangle_gray_10);
        baseViewHolder.setTextColor(R.id.tv_point_name, this.mContext.getResources().getColor(R.color.clr_333333));
    }

    public void setDeletePosition(int i) {
        this.mDeletePosition = i;
        this.mAdapterOperateMode = 0;
        notifyDataSetChanged();
    }

    public void setAdapterCanClick(boolean z) {
        this.mDeletePosition = -1;
        this.mSelectPosition = -1;
        if (z) {
            this.mAdapterOperateMode = 0;
        } else {
            this.mAdapterOperateMode = -1;
        }
        notifyDataSetChanged();
    }

    public void setSelectPosition(int i, boolean z, int i2) {
        this.mSelectPosition = i;
        this.mIsRight = z;
        this.mPatrolMode = i2;
        this.mAdapterOperateMode = 1;
        notifyDataSetChanged();
    }

    public OnPatrolRouteItemListener getOnPatrolRouteItemListener() {
        return this.mOnPatrolRouteItemListener;
    }

    public void setOnPatrolRouteItemListener(OnPatrolRouteItemListener onPatrolRouteItemListener) {
        this.mOnPatrolRouteItemListener = onPatrolRouteItemListener;
    }
}
