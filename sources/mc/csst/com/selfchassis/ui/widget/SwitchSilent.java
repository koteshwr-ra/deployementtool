package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SwitchSilent extends Switch {
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public SwitchSilent(Context context) {
        super(context);
    }

    public SwitchSilent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener2) {
        super.setOnCheckedChangeListener(onCheckedChangeListener2);
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    public void setCheckedSilent(boolean z) {
        super.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        super.setChecked(z);
        super.setOnCheckedChangeListener(this.onCheckedChangeListener);
    }
}
