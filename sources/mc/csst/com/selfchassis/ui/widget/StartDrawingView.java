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

public class StartDrawingView extends FrameLayout {
    private String endDrawingText;
    private ImageView iv_state;
    private View ll_cancel_drawing;
    /* access modifiers changed from: private */
    public String startDrawingText;
    /* access modifiers changed from: private */
    public StartDrawingViewListener startDrawingViewListener;
    /* access modifiers changed from: private */
    public TextView tv_state;

    public interface StartDrawingViewListener {
        void onCancel();

        void onChecked(boolean z);
    }

    public void setStartDrawingViewListener(StartDrawingViewListener startDrawingViewListener2) {
        this.startDrawingViewListener = startDrawingViewListener2;
    }

    public StartDrawingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StartDrawingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StartDrawingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.startDrawingText = getContext().getString(R.string.text_start_drawing);
        this.endDrawingText = getContext().getString(R.string.text_stop_drawing);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, mc.csst.com.selfchassis.R.styleable.SwitchStateViewText);
        this.startDrawingText = obtainStyledAttributes.getString(1);
        this.endDrawingText = obtainStyledAttributes.getString(0);
        obtainStyledAttributes.recycle();
        initView(LayoutInflater.from(context).inflate(R.layout.view_start_drawing, this, true));
    }

    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_state);
        this.tv_state = textView;
        textView.setText(this.startDrawingText);
        this.iv_state = (ImageView) view.findViewById(R.id.iv_state);
        View findViewById = view.findViewById(R.id.ll_cancel_drawing);
        this.ll_cancel_drawing = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartDrawingView.this.startDrawingViewListener != null) {
                    StartDrawingView.this.startDrawingViewListener.onCancel();
                }
            }
        });
        view.findViewById(R.id.ll_start_drawing).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartDrawingView.this.tv_state.getText().toString().trim().equals(StartDrawingView.this.startDrawingText)) {
                    if (StartDrawingView.this.startDrawingViewListener != null) {
                        StartDrawingView.this.startDrawingViewListener.onChecked(true);
                    }
                } else if (StartDrawingView.this.startDrawingViewListener != null) {
                    StartDrawingView.this.startDrawingViewListener.onChecked(false);
                }
            }
        });
    }

    public void endDrawing() {
        this.tv_state.setText(this.startDrawingText);
        this.iv_state.setImageResource(R.drawable.icon_start_drawing);
        this.ll_cancel_drawing.setVisibility(8);
    }

    public void startDrawing() {
        this.tv_state.setText(this.endDrawingText);
        this.iv_state.setImageResource(R.drawable.icon_stop_drawing);
        this.ll_cancel_drawing.setVisibility(0);
    }

    public boolean isDrawing() {
        return this.tv_state.getText().toString().trim().equals(this.endDrawingText);
    }
}
