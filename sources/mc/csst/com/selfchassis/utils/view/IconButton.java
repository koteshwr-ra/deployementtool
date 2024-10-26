package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;

public class IconButton extends ConstraintLayout {
    private static final String TAG = IconButton.class.getSimpleName();
    private AppCompatImageView mImgIv = null;
    private ConstraintLayout mRootCl = null;
    private AppCompatTextView mTxtTv = null;

    static /* synthetic */ boolean lambda$setClickEnable$0(boolean z, View view, MotionEvent motionEvent) {
        return !z;
    }

    public IconButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_icon_button, this, true);
        this.mRootCl = (ConstraintLayout) findViewById(R.id.icon_button_root_cl);
        this.mTxtTv = (AppCompatTextView) findViewById(R.id.icon_button_txt_tv);
        this.mImgIv = (AppCompatImageView) findViewById(R.id.icon_button_img_iv);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, mc.csst.com.selfchassis.R.styleable.IconButton);
        int resourceId = obtainStyledAttributes.getResourceId(0, R.drawable.shape_rectangle_blue_5);
        int resourceId2 = obtainStyledAttributes.getResourceId(2, R.drawable.shape_rectangle_blue_5);
        String string = obtainStyledAttributes.getString(3);
        int color = obtainStyledAttributes.getColor(4, context.getColor(R.color.white));
        boolean z = obtainStyledAttributes.getBoolean(1, true);
        if (resourceId != -1) {
            this.mRootCl.setBackgroundResource(resourceId);
        }
        if (resourceId2 != -1) {
            this.mImgIv.setBackgroundResource(resourceId2);
        }
        if (!TextUtils.isEmpty(string)) {
            this.mTxtTv.setText(string);
        }
        this.mTxtTv.setTextColor(color);
        setClickEnable(z);
        obtainStyledAttributes.recycle();
    }

    public static void setEnableBg(IconButton iconButton, boolean z) {
        if (iconButton != null) {
            iconButton.setClickEnable(z);
        }
    }

    public void setBtnBg(int i) {
        this.mRootCl.setBackgroundResource(i);
    }

    public void setBtnIcon(int i) {
        this.mImgIv.setBackgroundResource(i);
    }

    public void setBtnText(String str) {
        this.mTxtTv.setText(str);
    }

    public void setBtnTextSize(float f) {
        this.mTxtTv.setTextSize(f);
    }

    public void setBtnTextColor(int i) {
        this.mTxtTv.setTextColor(ContextUtil.getContext().getResources().getColor(i));
    }

    public void setClickEnable(boolean z) {
        if (z) {
            this.mRootCl.setAlpha(1.0f);
        } else {
            this.mRootCl.setAlpha(0.5f);
        }
        this.mRootCl.setOnTouchListener(new View.OnTouchListener(z) {
            public final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return IconButton.lambda$setClickEnable$0(this.f$0, view, motionEvent);
            }
        });
    }
}
