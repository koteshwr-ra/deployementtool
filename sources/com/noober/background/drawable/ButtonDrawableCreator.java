package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import com.noober.background.R;

public class ButtonDrawableCreator implements ICreateDrawable {
    private TypedArray buttonTa;
    private TypedArray typedArray;

    public ButtonDrawableCreator(TypedArray typedArray2, TypedArray typedArray3) {
        this.typedArray = typedArray2;
        this.buttonTa = typedArray3;
    }

    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.buttonTa.getIndexCount(); i++) {
            int index = this.buttonTa.getIndex(i);
            if (index == R.styleable.background_button_drawable_bl_checked_button_drawable) {
                setSelectorDrawable(this.typedArray, this.buttonTa, stateListDrawable, index, 16842912);
            } else if (index == R.styleable.background_button_drawable_bl_unChecked_button_drawable) {
                setSelectorDrawable(this.typedArray, this.buttonTa, stateListDrawable, index, -16842912);
            }
        }
        return stateListDrawable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0016 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setSelectorDrawable(android.content.res.TypedArray r3, android.content.res.TypedArray r4, android.graphics.drawable.StateListDrawable r5, int r6, int r7) throws java.lang.Exception {
        /*
            r2 = this;
            r0 = 0
            int r1 = r4.getColor(r6, r0)     // Catch:{ Exception -> 0x000e }
            if (r1 != 0) goto L_0x000c
            android.graphics.drawable.Drawable r4 = r4.getDrawable(r6)     // Catch:{ Exception -> 0x000f }
            goto L_0x0013
        L_0x000c:
            r4 = 0
            goto L_0x0013
        L_0x000e:
            r1 = 0
        L_0x000f:
            android.graphics.drawable.Drawable r4 = r4.getDrawable(r6)
        L_0x0013:
            r6 = 1
            if (r4 != 0) goto L_0x0027
            if (r1 == 0) goto L_0x0027
            android.graphics.drawable.GradientDrawable r3 = com.noober.background.drawable.DrawableFactory.getDrawable(r3)
            r3.setColor(r1)
            int[] r4 = new int[r6]
            r4[r0] = r7
            r5.addState(r4, r3)
            goto L_0x002e
        L_0x0027:
            int[] r3 = new int[r6]
            r3[r0] = r7
            r5.addState(r3, r4)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.drawable.ButtonDrawableCreator.setSelectorDrawable(android.content.res.TypedArray, android.content.res.TypedArray, android.graphics.drawable.StateListDrawable, int, int):void");
    }
}
