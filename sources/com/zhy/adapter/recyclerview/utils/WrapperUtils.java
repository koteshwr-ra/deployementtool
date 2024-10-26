package com.zhy.adapter.recyclerview.utils;

import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class WrapperUtils {

    public interface SpanSizeCallback {
        int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i);
    }

    public static void onAttachedToRecyclerView(RecyclerView.Adapter adapter, RecyclerView recyclerView, final SpanSizeCallback spanSizeCallback) {
        adapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    return spanSizeCallback.getSpanSize(gridLayoutManager, spanSizeLookup, i);
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    public static void setFullSpan(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }
}
