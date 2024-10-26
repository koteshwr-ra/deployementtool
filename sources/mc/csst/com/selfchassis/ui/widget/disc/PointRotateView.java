package mc.csst.com.selfchassis.ui.widget.disc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.widget.disc.TempControlView;

public class PointRotateView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "PointRotateView";
    /* access modifiers changed from: private */
    public PointRockerView pointRockerView;
    /* access modifiers changed from: private */
    public PointRotateViewListener pointRotateViewListener;
    private TempControlView tempControl;

    public interface PointRotateViewListener {
        void onBottom();

        void onLeft();

        void onRight();

        void onRotate(float f);

        void onTop();
    }

    public PointRotateView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PointRotateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PointRotateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_point_dial_rotate, this, true);
        this.pointRockerView = (PointRockerView) inflate.findViewById(R.id.point_rocker_view);
        TempControlView tempControlView = (TempControlView) inflate.findViewById(R.id.temp_control);
        this.tempControl = tempControlView;
        tempControlView.setTemp(0, 359, 0);
        this.tempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            public void change(int i) {
                PointRotateView.this.pointRockerView.setRotateAngle(i);
                if (PointRotateView.this.pointRotateViewListener != null) {
                    PointRotateView.this.pointRotateViewListener.onRotate((float) PointRotateView.this.degreeToRadian(i));
                }
            }
        });
        inflate.findViewById(R.id.btnTop).setOnClickListener(this);
        inflate.findViewById(R.id.btnBottom).setOnClickListener(this);
        inflate.findViewById(R.id.btnLeft).setOnClickListener(this);
        inflate.findViewById(R.id.btnRight).setOnClickListener(this);
    }

    public void setTemp(float f) {
        this.tempControl.setTemp((int) radianToDegree(f));
    }

    private double radianToDegree(float f) {
        if (f < 0.0f) {
            f = (float) (((double) f) + 6.283185307179586d);
        }
        return Math.toDegrees((double) f);
    }

    /* access modifiers changed from: private */
    public double degreeToRadian(int i) {
        if (i > 180) {
            i -= 360;
        }
        return Math.toRadians((double) i);
    }

    public void onClick(View view) {
        if (this.pointRotateViewListener != null) {
            switch (view.getId()) {
                case R.id.btnBottom:
                    this.pointRotateViewListener.onBottom();
                    return;
                case R.id.btnLeft:
                    this.pointRotateViewListener.onLeft();
                    return;
                case R.id.btnRight:
                    this.pointRotateViewListener.onRight();
                    return;
                case R.id.btnTop:
                    this.pointRotateViewListener.onTop();
                    return;
                default:
                    return;
            }
        }
    }

    public void setPointRotateViewListener(PointRotateViewListener pointRotateViewListener2) {
        this.pointRotateViewListener = pointRotateViewListener2;
    }
}
