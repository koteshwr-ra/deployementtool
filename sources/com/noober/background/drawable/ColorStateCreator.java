package com.noober.background.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import com.noober.background.R;

public class ColorStateCreator implements ICreateColorState {
    private int[] colors = new int[0];
    private int index;
    private int[][] states = new int[0][];
    private TypedArray textTa;

    ColorStateCreator(TypedArray typedArray) {
        this.textTa = typedArray;
    }

    public ColorStateList create() {
        this.states = new int[this.textTa.getIndexCount()][];
        this.colors = new int[this.textTa.getIndexCount()];
        for (int i = 0; i < this.textTa.getIndexCount(); i++) {
            int index2 = this.textTa.getIndex(i);
            if (index2 == R.styleable.text_selector_bl_checkable_textColor) {
                setStateColor(index2, 16842911);
            } else if (index2 == R.styleable.text_selector_bl_unCheckable_textColor) {
                setStateColor(index2, -16842911);
            } else if (index2 == R.styleable.text_selector_bl_checked_textColor) {
                setStateColor(index2, 16842912);
            } else if (index2 == R.styleable.text_selector_bl_unChecked_textColor) {
                setStateColor(index2, -16842912);
            } else if (index2 == R.styleable.text_selector_bl_enabled_textColor) {
                setStateColor(index2, 16842910);
            } else if (index2 == R.styleable.text_selector_bl_unEnabled_textColor) {
                setStateColor(index2, -16842910);
            } else if (index2 == R.styleable.text_selector_bl_selected_textColor) {
                setStateColor(index2, 16842913);
            } else if (index2 == R.styleable.text_selector_bl_unSelected_textColor) {
                setStateColor(index2, -16842913);
            } else if (index2 == R.styleable.text_selector_bl_pressed_textColor) {
                setStateColor(index2, 16842919);
            } else if (index2 == R.styleable.text_selector_bl_unPressed_textColor) {
                setStateColor(index2, -16842919);
            } else if (index2 == R.styleable.text_selector_bl_focused_textColor) {
                setStateColor(index2, 16842908);
            } else if (index2 == R.styleable.text_selector_bl_unFocused_textColor) {
                setStateColor(index2, -16842908);
            } else if (index2 == R.styleable.text_selector_bl_activated_textColor) {
                setStateColor(index2, 16843518);
            } else if (index2 == R.styleable.text_selector_bl_unActivated_textColor) {
                setStateColor(index2, -16842914);
            } else if (index2 == R.styleable.text_selector_bl_active_textColor) {
                setStateColor(index2, 16842914);
            } else if (index2 == R.styleable.text_selector_bl_unActive_textColor) {
                setStateColor(index2, -16843518);
            } else if (index2 == R.styleable.text_selector_bl_expanded_textColor) {
                setStateColor(index2, 16842920);
            } else if (index2 == R.styleable.text_selector_bl_unExpanded_textColor) {
                setStateColor(index2, -16842920);
            }
        }
        return new ColorStateList(this.states, this.colors);
    }

    private void setStateColor(int i, int i2) {
        int[][] iArr = this.states;
        int i3 = this.index;
        iArr[i3] = new int[]{i2};
        this.colors[i3] = this.textTa.getColor(i, 0);
        this.index++;
    }
}
