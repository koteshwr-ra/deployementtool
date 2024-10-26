package com.noober.background.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import com.noober.background.R;
import com.noober.background.common.MultiSelector;
import com.noober.background.common.ResourceUtils;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import org.xmlpull.v1.XmlPullParserException;

public class MultiSelectorDrawableCreator implements ICreateDrawable {
    private Context context;
    private GradientDrawable gradientDrawable;
    private TypedArray selectorTa;
    TypedArray typedArray;

    public MultiSelectorDrawableCreator(Context context2, TypedArray typedArray2, TypedArray typedArray3) {
        this.selectorTa = typedArray2;
        this.context = context2;
        this.typedArray = typedArray3;
    }

    public StateListDrawable create() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.selectorTa.getIndexCount(); i++) {
            int index = this.selectorTa.getIndex(i);
            if (index == R.styleable.background_multi_selector_bl_multi_selector1) {
                addState(stateListDrawable, index);
            } else if (index == R.styleable.background_multi_selector_bl_multi_selector2) {
                addState(stateListDrawable, index);
            } else if (index == R.styleable.background_multi_selector_bl_multi_selector3) {
                addState(stateListDrawable, index);
            } else if (index == R.styleable.background_multi_selector_bl_multi_selector4) {
                addState(stateListDrawable, index);
            } else if (index == R.styleable.background_multi_selector_bl_multi_selector5) {
                addState(stateListDrawable, index);
            } else if (index == R.styleable.background_multi_selector_bl_multi_selector6) {
                addState(stateListDrawable, index);
            }
        }
        return stateListDrawable;
    }

    private void addState(StateListDrawable stateListDrawable, int i) {
        String string = this.selectorTa.getString(i);
        if (string != null) {
            String[] split = string.split(",");
            if (split.length >= 2) {
                Drawable drawable = null;
                int[] iArr = new int[(split.length - 1)];
                for (int i2 = 0; i2 < split.length; i2++) {
                    String str = split[i2];
                    if (i2 == split.length - 1) {
                        int color = ResourceUtils.getColor(this.context, str);
                        if (this.typedArray.getIndexCount() > 0) {
                            try {
                                this.gradientDrawable = DrawableFactory.getDrawable(this.typedArray);
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                        }
                        GradientDrawable gradientDrawable2 = this.gradientDrawable;
                        if (gradientDrawable2 == null || color == -1) {
                            drawable = ResourceUtils.getDrawable(this.context, str);
                        } else {
                            gradientDrawable2.setColor(color);
                            drawable = this.gradientDrawable;
                        }
                        if (drawable == null) {
                            throw new IllegalArgumentException("cannot find drawable from the last attribute");
                        }
                    } else {
                        MultiSelector multiAttr = MultiSelector.getMultiAttr(str.replace(MapFragment.SLASH, ""));
                        if (multiAttr == null) {
                            throw new IllegalArgumentException("the attribute of bl_multi_selector only support state_checkable, state_checked, state_enabled, state_selected, state_pressed, state_focused, state_hovered, state_activated");
                        } else if (str.contains(MapFragment.SLASH)) {
                            iArr[i2] = -multiAttr.id;
                        } else {
                            iArr[i2] = multiAttr.id;
                        }
                    }
                }
                stateListDrawable.addState(iArr, drawable);
                return;
            }
            throw new IllegalArgumentException("Attributes and drawable must be set at the same time");
        }
    }
}
