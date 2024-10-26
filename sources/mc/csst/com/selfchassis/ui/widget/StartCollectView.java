package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ciot.sentrymove.R;

public class StartCollectView extends FrameLayout {
    private String endCollectingText;
    private ImageView iv_state;
    /* access modifiers changed from: private */
    public String startCollectingText;
    /* access modifiers changed from: private */
    public StartDrawingViewListener startCollectingViewListener;
    /* access modifiers changed from: private */
    public TextView tv_state;

    public interface StartDrawingViewListener {
        void onChecked(boolean z);
    }

    public void setStartCollectingViewListener(StartDrawingViewListener startDrawingViewListener) {
        this.startCollectingViewListener = startDrawingViewListener;
    }

    public StartCollectView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StartCollectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StartCollectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.startCollectingText = getContext().getString(R.string.text_start_collecting);
        this.endCollectingText = getContext().getString(R.string.text_stop_collecting);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, mc.csst.com.selfchassis.R.styleable.SwitchStateViewText);
        this.startCollectingText = obtainStyledAttributes.getString(1);
        this.endCollectingText = obtainStyledAttributes.getString(0);
        obtainStyledAttributes.recycle();
        initView(LayoutInflater.from(context).inflate(R.layout.view_start_collecting, this, true));
    }

    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_state);
        this.tv_state = textView;
        textView.setText(this.startCollectingText);
        this.iv_state = (ImageView) view.findViewById(R.id.iv_state);
        view.findViewById(R.id.ll_start_collecting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartCollectView.this.tv_state.getText().toString().trim().equals(StartCollectView.this.startCollectingText)) {
                    if (StartCollectView.this.startCollectingViewListener != null) {
                        StartCollectView.this.startCollectingViewListener.onChecked(true);
                    }
                } else if (StartCollectView.this.startCollectingViewListener != null) {
                    StartCollectView.this.startCollectingViewListener.onChecked(false);
                }
            }
        });
    }

    public void endDrawing() {
        this.tv_state.setText(this.startCollectingText);
        this.iv_state.setImageResource(R.drawable.icon_start_drawing);
    }

    public void startDrawing() {
        this.tv_state.setText(this.endCollectingText);
        this.iv_state.setImageResource(R.drawable.icon_stop_drawing);
    }

    public boolean isDrawing() {
        return this.tv_state.getText().toString().trim().equals(this.endCollectingText);
    }
}
