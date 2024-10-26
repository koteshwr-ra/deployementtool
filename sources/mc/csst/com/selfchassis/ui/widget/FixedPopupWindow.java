package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

public class FixedPopupWindow extends PopupWindow {
    public FixedPopupWindow(Context context) {
        super(context);
    }

    public FixedPopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FixedPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FixedPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public FixedPopupWindow() {
    }

    public FixedPopupWindow(View view) {
        super(view);
    }

    public FixedPopupWindow(int i, int i2) {
        super(i, i2);
    }

    public FixedPopupWindow(View view, int i, int i2) {
        super(view, i, i2);
    }

    public FixedPopupWindow(View view, int i, int i2, boolean z) {
        super(view, i, i2, z);
    }

    public void showAsDropDown(View view, int i, int i2) {
        setFocusable(false);
        super.showAsDropDown(view, i, i2);
        setFocusable(true);
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        setFocusable(false);
        super.showAsDropDown(view, i, i2, i3);
        setFocusable(true);
    }

    public void showAsDropDown(View view) {
        setFocusable(false);
        super.showAsDropDown(view);
        setFocusable(true);
    }
}
