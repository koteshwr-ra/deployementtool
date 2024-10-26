package mc.csst.com.selfchassis.utils.view;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SplitLayoutManager extends RecyclerView.LayoutManager {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int mFirstVisiblePos = 0;
    private int mLastVisiblePos = 0;
    private int mOrientation = 0;
    private OrientationHelper mOrientationHelper;
    private int mScrollingOffset;
    private int mSpanCount = 1;

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public SplitLayoutManager(int i, int i2) {
        setOrientation(i);
        setSpan(i2);
    }

    private void setOrientation(int i) {
        if (this.mOrientation != i || this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
            this.mOrientation = i;
            requestLayout();
        }
    }

    private void setSpan(int i) {
        if (this.mSpanCount != i) {
            this.mSpanCount = i;
            requestLayout();
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
        } else if (getChildCount() != 0 || !state.isPreLayout()) {
            detachAndScrapAttachedViews(recycler);
            this.mFirstVisiblePos = 0;
            this.mLastVisiblePos = 0;
            this.mScrollingOffset = 0;
            fill(recycler, state);
        }
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        addViewToFillStart(recycler, state);
        addViewToFillEnd(recycler, state);
    }

    private void addViewToFillStart(RecyclerView.Recycler recycler, RecyclerView.State state) {
        for (int i = this.mFirstVisiblePos - 1; i >= 0; i--) {
            if (isItemVisibleInParent(i)) {
                View viewForPosition = recycler.getViewForPosition(i);
                addView(viewForPosition);
                this.mFirstVisiblePos--;
                if (this.mOrientation == 1) {
                    measureChildWithSpan(viewForPosition);
                    int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                    int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                    int paddingLeft = getPaddingLeft();
                    int spanSpace = ((getSpanSpace() * i) + ((getSpanSpace() - decoratedMeasuredHeight) / 2)) - this.mScrollingOffset;
                    layoutDecoratedWithMargins(viewForPosition, paddingLeft, spanSpace, paddingLeft + decoratedMeasuredWidth, spanSpace + decoratedMeasuredHeight);
                } else {
                    measureChildWithSpan(viewForPosition);
                    int decoratedMeasuredWidth2 = getDecoratedMeasuredWidth(viewForPosition);
                    int decoratedMeasuredHeight2 = getDecoratedMeasuredHeight(viewForPosition);
                    int spanSpace2 = ((getSpanSpace() * i) + ((getSpanSpace() - decoratedMeasuredWidth2) / 2)) - this.mScrollingOffset;
                    int paddingTop = getPaddingTop();
                    layoutDecoratedWithMargins(viewForPosition, spanSpace2, paddingTop, spanSpace2 + decoratedMeasuredWidth2, paddingTop + decoratedMeasuredHeight2);
                }
            }
        }
    }

    private void addViewToFillEnd(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i = this.mLastVisiblePos;
        while (i < getItemCount() && isItemVisibleInParent(i)) {
            View viewForPosition = recycler.getViewForPosition(i);
            addView(viewForPosition);
            this.mLastVisiblePos++;
            if (this.mOrientation == 1) {
                measureChildWithSpan(viewForPosition);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                int paddingLeft = getPaddingLeft();
                int spanSpace = ((getSpanSpace() * i) + ((getSpanSpace() - decoratedMeasuredHeight) / 2)) - this.mScrollingOffset;
                layoutDecoratedWithMargins(viewForPosition, paddingLeft, spanSpace, paddingLeft + decoratedMeasuredWidth, spanSpace + decoratedMeasuredHeight);
            } else {
                measureChildWithSpan(viewForPosition);
                int decoratedMeasuredWidth2 = getDecoratedMeasuredWidth(viewForPosition);
                int decoratedMeasuredHeight2 = getDecoratedMeasuredHeight(viewForPosition);
                int spanSpace2 = ((getSpanSpace() * i) + ((getSpanSpace() - decoratedMeasuredWidth2) / 2)) - this.mScrollingOffset;
                int paddingTop = getPaddingTop();
                layoutDecoratedWithMargins(viewForPosition, spanSpace2, paddingTop, spanSpace2 + decoratedMeasuredWidth2, paddingTop + decoratedMeasuredHeight2);
            }
            i++;
        }
    }

    private boolean isItemVisibleInParent(int i) {
        int spanSpace = i * getSpanSpace();
        return spanSpace >= this.mScrollingOffset - getSpanSpace() && spanSpace < this.mScrollingOffset + this.mOrientationHelper.getTotalSpace();
    }

    private void measureChildWithSpan(View view) {
        int i;
        int i2;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
        int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
        if (this.mOrientation == 1) {
            i = getChildMeasureSpec(getWidth(), getWidthMode(), leftDecorationWidth, layoutParams.width, canScrollHorizontally());
            i2 = getChildMeasureSpec(getSpanSpace(), 1073741824, topDecorationHeight, layoutParams.height, canScrollVertically());
        } else {
            i = getChildMeasureSpec(getSpanSpace(), 1073741824, leftDecorationWidth, layoutParams.width, canScrollHorizontally());
            i2 = getChildMeasureSpec(getHeight(), getHeightMode(), topDecorationHeight, layoutParams.height, canScrollVertically());
        }
        if (shouldMeasureChild(view, i, i2, layoutParams)) {
            view.measure(i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return view.isLayoutRequested() || !isMeasurementCacheEnabled() || !isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height);
    }

    private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getSpanSpace() {
        OrientationHelper orientationHelper = this.mOrientationHelper;
        if (orientationHelper == null) {
            return 0;
        }
        return orientationHelper.getTotalSpace() / this.mSpanCount;
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (this.mOrientation == 0) {
            return 0;
        }
        int totalSpace = (this.mOrientationHelper.getTotalSpace() / this.mSpanCount) * getItemCount();
        if (this.mScrollingOffset + i > totalSpace - this.mOrientationHelper.getTotalSpace()) {
            i2 = (totalSpace - this.mOrientationHelper.getTotalSpace()) - this.mScrollingOffset;
        } else {
            int i3 = this.mScrollingOffset;
            i2 = i + i3 < 0 ? -i3 : i;
        }
        this.mScrollingOffset += i2;
        recycleOutsideChild(recycler, i);
        offsetChildrenVertical(-i2);
        fill(recycler, state);
        return i2;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (this.mOrientation == 1) {
            return 0;
        }
        int totalSpace = (this.mOrientationHelper.getTotalSpace() / this.mSpanCount) * getItemCount();
        if (this.mScrollingOffset + i > totalSpace - this.mOrientationHelper.getTotalSpace()) {
            i2 = (totalSpace - this.mOrientationHelper.getTotalSpace()) - this.mScrollingOffset;
        } else {
            int i3 = this.mScrollingOffset;
            i2 = i + i3 < 0 ? -i3 : i;
        }
        this.mScrollingOffset += i2;
        recycleOutsideChild(recycler, i);
        offsetChildrenHorizontal(-i2);
        fill(recycler, state);
        return i2;
    }

    private void recycleOutsideChild(RecyclerView.Recycler recycler, int i) {
        int childCount = getChildCount();
        if (childCount != 0 && i != 0) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                if (!isItemVisibleInParent(getPosition(childAt))) {
                    removeAndRecycleView(childAt, recycler);
                    if (i > 0) {
                        this.mFirstVisiblePos++;
                    } else {
                        this.mLastVisiblePos--;
                    }
                }
            }
        }
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new RecyclerView.LayoutParams(-2, -1);
        }
        return new RecyclerView.LayoutParams(-1, -2);
    }
}
