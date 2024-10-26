package mc.csst.com.selfchassis.utils.view.bubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import mc.csst.com.selfchassis.R;

public class ChatView extends ChatLayout {
    private String arrowDirection = "left";
    private int arrowHeight = 30;
    private int arrowUpDistance = 50;
    private int arrowWidth = 15;
    private ChatShape chatShape;
    private int connerRadius = 40;
    private int fillColor = Color.parseColor("#66CCFF");
    private boolean hasStroke = false;
    private boolean isArrowCenter = false;
    private int pressFillColor;
    private int pressStrokeColor;
    private StateListDrawable stalistDrawable;
    private int strokeColor = Color.parseColor("#CCCCCC");
    private int strokeWidth = 3;

    public ChatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i = this.strokeColor;
        this.pressStrokeColor = i;
        this.pressFillColor = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.chat);
        this.isArrowCenter = obtainStyledAttributes.getBoolean(7, false);
        this.hasStroke = obtainStyledAttributes.getBoolean(6, false);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(11, 3);
        this.arrowDirection = obtainStyledAttributes.getString(0);
        this.arrowUpDistance = obtainStyledAttributes.getDimensionPixelSize(2, 50);
        this.connerRadius = obtainStyledAttributes.getDimensionPixelSize(4, 40);
        this.strokeColor = obtainStyledAttributes.getColor(10, this.strokeColor);
        this.fillColor = obtainStyledAttributes.getColor(5, this.fillColor);
        this.pressStrokeColor = obtainStyledAttributes.getColor(9, this.pressStrokeColor);
        this.pressFillColor = obtainStyledAttributes.getColor(8, this.pressFillColor);
        this.arrowWidth = obtainStyledAttributes.getDimensionPixelSize(3, 15);
        this.arrowHeight = obtainStyledAttributes.getDimensionPixelSize(1, 30);
        setBackground(getSelectorBackground());
    }

    public StateListDrawable getSelectorBackground() {
        if (this.stalistDrawable == null) {
            this.stalistDrawable = new StateListDrawable();
        }
        this.chatShape = new ChatShape(this.arrowWidth, this.arrowHeight, this.isArrowCenter, this.strokeWidth, this.arrowDirection, this.arrowUpDistance, this.connerRadius, this.pressStrokeColor, this.pressFillColor);
        this.stalistDrawable.addState(new int[]{16842919}, new ShapeDrawable(this.chatShape));
        this.chatShape = new ChatShape(this.arrowWidth, this.arrowHeight, this.isArrowCenter, this.strokeWidth, this.arrowDirection, this.arrowUpDistance, this.connerRadius, this.strokeColor, this.fillColor);
        this.stalistDrawable.addState(new int[0], new ShapeDrawable(this.chatShape));
        return this.stalistDrawable;
    }
}
