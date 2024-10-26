package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class RadioButtonSilent extends RadioGroup {
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener;

    public RadioButtonSilent(Context context) {
        super(context);
    }

    public RadioButtonSilent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener onCheckedChangeListener2) {
        super.setOnCheckedChangeListener(onCheckedChangeListener2);
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    public void setCheckedSilent(int i) {
        super.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
        super.check(i);
        super.setOnCheckedChangeListener(this.onCheckedChangeListener);
    }
}
