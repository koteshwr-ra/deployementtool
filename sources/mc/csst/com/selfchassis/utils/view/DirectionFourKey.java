package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ciot.sentrymove.R;

public class DirectionFourKey extends ConstraintLayout {
    private static final String TAG = DirectionFourKey.class.getSimpleName();
    private AppCompatImageView mDownIv;
    private AppCompatImageView mLeftIv;
    private OnDirectionListener mOnDirectionListener;
    private AppCompatImageView mRightIv;
    private AppCompatImageView mUpIv;

    public interface OnDirectionListener {
        void downDown();

        void downUp();

        void leftDown();

        void leftUp();

        void rightDown();

        void rightUp();

        void upDown();

        void upUp();
    }

    public DirectionFourKey(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_direction_four_key, this, true);
        initView();
        initEvent();
    }

    private void initView() {
        this.mUpIv = (AppCompatImageView) findViewById(R.id.iv_direction_up);
        this.mDownIv = (AppCompatImageView) findViewById(R.id.iv_direction_down);
        this.mLeftIv = (AppCompatImageView) findViewById(R.id.iv_direction_left);
        this.mRightIv = (AppCompatImageView) findViewById(R.id.iv_direction_right);
    }

    private void initEvent() {
        this.mUpIv.setOnTouchListener(new MyOnTouchListener());
        this.mDownIv.setOnTouchListener(new MyOnTouchListener());
        this.mLeftIv.setOnTouchListener(new MyOnTouchListener());
        this.mRightIv.setOnTouchListener(new MyOnTouchListener());
    }

    public void setOnDirectionListener(OnDirectionListener onDirectionListener) {
        this.mOnDirectionListener = onDirectionListener;
    }

    class MyOnTouchListener implements View.OnTouchListener, View.OnClickListener {
        public void onClick(View view) {
        }

        MyOnTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            if (motionEvent.getAction() != 1) {
                if (motionEvent.getAction() == 2 || motionEvent.getAction() == 0) {
                    switch (id) {
                        case R.id.iv_direction_down:
                            DirectionFourKey.this.downDown();
                            break;
                        case R.id.iv_direction_left:
                            DirectionFourKey.this.leftDown();
                            break;
                        case R.id.iv_direction_right:
                            DirectionFourKey.this.rightDown();
                            break;
                        case R.id.iv_direction_up:
                            DirectionFourKey.this.upDown();
                            break;
                    }
                }
            } else {
                switch (id) {
                    case R.id.iv_direction_down:
                        DirectionFourKey.this.downUp();
                        break;
                    case R.id.iv_direction_left:
                        DirectionFourKey.this.leftUp();
                        break;
                    case R.id.iv_direction_right:
                        DirectionFourKey.this.rightUp();
                        break;
                    case R.id.iv_direction_up:
                        DirectionFourKey.this.upUp();
                        break;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void upUp() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.upUp();
        }
    }

    /* access modifiers changed from: private */
    public void downUp() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.downUp();
        }
    }

    /* access modifiers changed from: private */
    public void leftUp() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.leftUp();
        }
    }

    /* access modifiers changed from: private */
    public void rightUp() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.rightUp();
        }
    }

    /* access modifiers changed from: private */
    public void upDown() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.upDown();
        }
    }

    /* access modifiers changed from: private */
    public void downDown() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.downDown();
        }
    }

    /* access modifiers changed from: private */
    public void leftDown() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.leftDown();
        }
    }

    /* access modifiers changed from: private */
    public void rightDown() {
        OnDirectionListener onDirectionListener = this.mOnDirectionListener;
        if (onDirectionListener != null) {
            onDirectionListener.rightDown();
        }
    }
}
