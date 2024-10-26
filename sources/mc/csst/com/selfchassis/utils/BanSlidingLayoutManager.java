package mc.csst.com.selfchassis.utils;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

public class BanSlidingLayoutManager extends LinearLayoutManager {
    public boolean canScrollVertically() {
        return false;
    }

    public BanSlidingLayoutManager(Context context) {
        super(context);
    }
}
