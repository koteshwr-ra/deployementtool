package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class MultiRowRadioGroup extends RadioGroup {
    public MultiRowRadioGroup(Context context) {
        super(context);
    }

    public MultiRowRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                childAt.measure(0, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
                int measuredHeight = childAt.getMeasuredHeight();
                i4 += measuredWidth;
                int i7 = (i3 * measuredHeight) + measuredHeight;
                if (i4 > size) {
                    i3++;
                    i5 = measuredHeight + (i3 * measuredHeight);
                    i4 = measuredWidth;
                } else {
                    i5 = i7;
                }
            }
        }
        if (i3 <= 0) {
            size = i4;
        }
        setMeasuredDimension(size, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
                int measuredHeight = childAt.getMeasuredHeight();
                i6 += measuredWidth;
                int i9 = (i7 * measuredHeight) + measuredHeight;
                if (i6 > i5) {
                    i7++;
                    i9 = (i7 * measuredHeight) + measuredHeight;
                    i6 = measuredWidth;
                }
                childAt.layout(i6 - measuredWidth, i9 - measuredHeight, i6, i9);
            }
        }
    }
}
