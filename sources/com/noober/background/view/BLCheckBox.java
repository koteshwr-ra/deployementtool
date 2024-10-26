package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.noober.background.BackgroundFactory;

public class BLCheckBox extends AppCompatCheckBox {
    public BLCheckBox(Context context) {
        super(context);
    }

    public BLCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public BLCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        BackgroundFactory.setViewBackground(context, attributeSet, this);
    }
}
