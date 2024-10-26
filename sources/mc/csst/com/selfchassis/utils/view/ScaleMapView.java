package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ciot.sentrymove.R;

public class ScaleMapView extends ConstraintLayout {
    private final View end;
    private boolean needChange;
    private final int progressBgColor;
    private final int progressColor;
    private final AppCompatSeekBar seekBar;
    private final View start;
    private final AppCompatTextView tvProgress;

    private int convertProgress(float f) {
        return (int) (f * 100.0f);
    }

    public ScaleMapView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScaleMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_scale_map, this, true);
        this.seekBar = (AppCompatSeekBar) findViewById(R.id.sb_scale);
        this.tvProgress = (AppCompatTextView) findViewById(R.id.tv_progress);
        this.start = findViewById(R.id.smv_start);
        this.end = findViewById(R.id.smv_end);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, mc.csst.com.selfchassis.R.styleable.ScaleMapView);
        int integer = obtainStyledAttributes.getInteger(1, 50);
        this.progressColor = obtainStyledAttributes.getResourceId(2, R.drawable.scale_thumb);
        this.progressBgColor = obtainStyledAttributes.getResourceId(0, R.drawable.scale_bg);
        int color = obtainStyledAttributes.getColor(3, context.getResources().getColor(R.color.black));
        float dimension = obtainStyledAttributes.getDimension(4, 18.0f);
        this.seekBar.setEnabled(false);
        this.seekBar.setProgressDrawable(context.getDrawable(this.progressBgColor));
        this.seekBar.setThumb(context.getDrawable(this.progressColor));
        setProgress(integer);
        this.tvProgress.setTextColor(color);
        this.tvProgress.setTextSize(3, dimension);
        obtainStyledAttributes.recycle();
    }

    public void setProgress(int i) {
        this.tvProgress.setText(String.format("%s", new Object[]{Integer.valueOf(i)}));
        changeUI(i);
    }

    public void setProgress(float f) {
        this.tvProgress.setText(String.format("%s", new Object[]{Float.valueOf(f)}));
        changeUI(convertProgress(f));
    }

    private void changeUI(int i) {
        this.seekBar.setProgress(i);
        if (i == 0) {
            this.start.setBackgroundResource(this.progressBgColor);
            this.seekBar.setThumb(getContext().getDrawable(this.progressBgColor));
            this.needChange = true;
        } else if (this.needChange) {
            this.start.setBackgroundResource(this.progressColor);
            this.seekBar.setThumb(getContext().getDrawable(this.progressColor));
            this.needChange = true ^ this.needChange;
        }
        if (i >= this.seekBar.getMax()) {
            this.end.setVisibility(4);
        } else if (this.end.getVisibility() == 4) {
            this.end.setVisibility(0);
        }
    }

    public void setMaxProgress(int i) {
        this.seekBar.setMax(i);
    }

    public void setMaxProgress(float f) {
        setMaxProgress(convertProgress(f));
    }
}
