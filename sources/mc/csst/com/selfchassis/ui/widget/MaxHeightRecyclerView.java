package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import mc.csst.com.selfchassis.R;

public class MaxHeightRecyclerView extends RecyclerView {
    private int mMaxHeight;
    private int mMaxWidth;

    public MaxHeightRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getContext() != null && attributeSet != null) {
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.MaxLimitRecyclerView);
                if (typedArray.hasValue(0)) {
                    this.mMaxHeight = typedArray.getDimensionPixelOffset(0, -1);
                }
                if (typedArray.hasValue(1)) {
                    this.mMaxWidth = typedArray.getDimensionPixelOffset(1, -1);
                }
                if (typedArray == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (typedArray == null) {
                    return;
                }
            } catch (Throwable th) {
                if (typedArray != null) {
                    typedArray.recycle();
                }
                throw th;
            }
            typedArray.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMaxHeight >= 0 || this.mMaxWidth >= 0) {
            int measuredHeight = getMeasuredHeight();
            int measuredWidth = getMeasuredWidth();
            int measuredHeight2 = getMeasuredHeight();
            int i3 = this.mMaxHeight;
            if (measuredHeight2 > i3) {
                measuredHeight = i3;
            }
            int measuredWidth2 = getMeasuredWidth();
            int i4 = this.mMaxWidth;
            if (measuredWidth2 > i4) {
                measuredWidth = i4;
            }
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }
}
