package mc.csst.com.selfchassis.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CenterLayoutManager extends LinearLayoutManager {
    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public CenterLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        CenterSmoothScroller centerSmoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        centerSmoothScroller.setTargetPosition(i);
        startSmoothScroll(centerSmoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {
        public CenterSmoothScroller(Context context) {
            super(context);
        }

        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2));
        }

        /* access modifiers changed from: protected */
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / ((float) displayMetrics.densityDpi);
        }
    }
}
