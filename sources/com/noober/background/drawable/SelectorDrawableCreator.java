package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import com.noober.background.R;

public class SelectorDrawableCreator implements ICreateDrawable {
    private TypedArray selectorTa;
    private TypedArray typedArray;

    public SelectorDrawableCreator(TypedArray typedArray2, TypedArray typedArray3) {
        this.typedArray = typedArray2;
        this.selectorTa = typedArray3;
    }

    public StateListDrawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.selectorTa.getIndexCount(); i++) {
            int index = this.selectorTa.getIndex(i);
            if (index == R.styleable.background_selector_bl_checkable_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842911);
            } else if (index == R.styleable.background_selector_bl_unCheckable_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842911);
            } else if (index == R.styleable.background_selector_bl_checked_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842912);
            } else if (index == R.styleable.background_selector_bl_unChecked_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842912);
            } else if (index == R.styleable.background_selector_bl_enabled_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842910);
            } else if (index == R.styleable.background_selector_bl_unEnabled_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842910);
            } else if (index == R.styleable.background_selector_bl_selected_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842913);
            } else if (index == R.styleable.background_selector_bl_unSelected_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842913);
            } else if (index == R.styleable.background_selector_bl_pressed_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842919);
            } else if (index == R.styleable.background_selector_bl_unPressed_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842919);
            } else if (index == R.styleable.background_selector_bl_focused_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16842908);
            } else if (index == R.styleable.background_selector_bl_unFocused_drawable) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16842908);
            } else if (index == R.styleable.background_selector_bl_focused_hovered) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16843623);
            } else if (index == R.styleable.background_selector_bl_unFocused_hovered) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16843623);
            } else if (index == R.styleable.background_selector_bl_focused_activated) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, 16843518);
            } else if (index == R.styleable.background_selector_bl_unFocused_activated) {
                setSelectorDrawable(this.typedArray, this.selectorTa, stateListDrawable, index, -16843518);
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
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.drawable.SelectorDrawableCreator.setSelectorDrawable(android.content.res.TypedArray, android.content.res.TypedArray, android.graphics.drawable.StateListDrawable, int, int):void");
    }
}
