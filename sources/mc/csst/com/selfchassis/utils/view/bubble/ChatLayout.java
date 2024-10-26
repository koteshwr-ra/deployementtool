package mc.csst.com.selfchassis.utils.view.bubble;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ChatLayout extends ConstraintLayout {
    public ChatLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(Color.parseColor("#00000000"));
    }
}
