package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.model.trajectory.TrajectoryModel;
import mc.csst.com.selfchassis.utils.MyToastUtils;

public class DrawingSwitchView extends FrameLayout {
    private ImageView ivIntellectDrawing;
    private ImageView ivTrackDrawing;
    private View llIntellectDrawing;
    private View llTrackDrawing;
    /* access modifiers changed from: private */
    public OnSelectListener onSelectListener;
    /* access modifiers changed from: private */
    public SelectionEnum selectionEnum;
    private TextView tvIntellectDrawing;
    private TextView tvTrackDrawing;

    public interface OnSelectListener {
        void onSelected(SelectionEnum selectionEnum);
    }

    public enum SelectionEnum {
        IntellectDrawing,
        TrackDrawing
    }

    public void setOnSelectListener(OnSelectListener onSelectListener2) {
        this.onSelectListener = onSelectListener2;
    }

    public DrawingSwitchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DrawingSwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawingSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectionEnum = SelectionEnum.IntellectDrawing;
        initView(LayoutInflater.from(context).inflate(R.layout.drawing_switch_view, this, true));
    }

    private void initView(View view) {
        this.llIntellectDrawing = view.findViewById(R.id.llIntellectDrawing);
        this.llTrackDrawing = view.findViewById(R.id.llTrackDrawing);
        this.ivIntellectDrawing = (ImageView) view.findViewById(R.id.ivIntellectDrawing);
        this.ivTrackDrawing = (ImageView) view.findViewById(R.id.ivTrackDrawing);
        this.tvIntellectDrawing = (TextView) view.findViewById(R.id.tvIntellectDrawing);
        this.tvTrackDrawing = (TextView) view.findViewById(R.id.tvTrackDrawing);
        this.llIntellectDrawing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TrajectoryModel.getInstance().getIsInDrawing().booleanValue()) {
                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.text_please_end_drawing));
                } else if (DrawingSwitchView.this.selectionEnum == SelectionEnum.TrackDrawing) {
                    DrawingSwitchView.this.updateView(SelectionEnum.IntellectDrawing);
                    SelectionEnum unused = DrawingSwitchView.this.selectionEnum = SelectionEnum.IntellectDrawing;
                    if (DrawingSwitchView.this.onSelectListener != null) {
                        DrawingSwitchView.this.onSelectListener.onSelected(DrawingSwitchView.this.selectionEnum);
                    }
                }
            }
        });
        this.llTrackDrawing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DrawingSwitchView.this.selectionEnum == SelectionEnum.IntellectDrawing) {
                    DrawingSwitchView.this.updateView(SelectionEnum.TrackDrawing);
                    SelectionEnum unused = DrawingSwitchView.this.selectionEnum = SelectionEnum.TrackDrawing;
                    if (DrawingSwitchView.this.onSelectListener != null) {
                        DrawingSwitchView.this.onSelectListener.onSelected(DrawingSwitchView.this.selectionEnum);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateView(SelectionEnum selectionEnum2) {
        this.ivIntellectDrawing.setImageResource(selectionEnum2 == SelectionEnum.IntellectDrawing ? R.drawable.icon_intelligence_draw_blue : R.drawable.icon_intelligence_draw_grey);
        TextView textView = this.tvIntellectDrawing;
        Resources resources = getContext().getResources();
        SelectionEnum selectionEnum3 = SelectionEnum.IntellectDrawing;
        int i = R.color._ff1b6efd;
        textView.setTextColor(resources.getColor(selectionEnum2 == selectionEnum3 ? R.color._ff1b6efd : R.color._ff333333));
        this.ivTrackDrawing.setImageResource(selectionEnum2 == SelectionEnum.TrackDrawing ? R.drawable.icon_trajectory_draw_blue : R.drawable.icon_trajectory_draw_grey);
        TextView textView2 = this.tvTrackDrawing;
        Resources resources2 = getContext().getResources();
        if (selectionEnum2 != SelectionEnum.TrackDrawing) {
            i = R.color._ff333333;
        }
        textView2.setTextColor(resources2.getColor(i));
    }
}
