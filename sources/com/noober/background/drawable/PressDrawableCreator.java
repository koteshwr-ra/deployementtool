package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import com.noober.background.R;

public class PressDrawableCreator implements ICreateDrawable {
    private GradientDrawable drawable;
    private TypedArray pressTa;
    private TypedArray typedArray;

    PressDrawableCreator(GradientDrawable gradientDrawable, TypedArray typedArray2, TypedArray typedArray3) {
        this.drawable = gradientDrawable;
        this.pressTa = typedArray3;
        this.typedArray = typedArray2;
    }

    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.pressTa.getIndexCount(); i++) {
            int index = this.pressTa.getIndex(i);
            if (index == R.styleable.background_press_bl_pressed_color) {
                int color = this.pressTa.getColor(index, 0);
                GradientDrawable drawable2 = DrawableFactory.getDrawable(this.typedArray);
                drawable2.setColor(color);
                stateListDrawable.addState(new int[]{16842919}, drawable2);
            } else if (index == R.styleable.background_press_bl_unpressed_color) {
                this.drawable.setColor(this.pressTa.getColor(index, 0));
                stateListDrawable.addState(new int[]{-16842919}, this.drawable);
            }
        }
        return stateListDrawable;
    }
}
