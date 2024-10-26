package com.zhy.adapter.recyclerview.wrapper;

import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.utils.WrapperUtils;

public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    /* access modifiers changed from: private */
    public SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    /* access modifiers changed from: private */
    public SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mHeaderViews.get(i) != null) {
            return ViewHolder.createViewHolder(viewGroup.getContext(), this.mHeaderViews.get(i));
        }
        if (this.mFootViews.get(i) != null) {
            return ViewHolder.createViewHolder(viewGroup.getContext(), this.mFootViews.get(i));
        }
        return this.mInnerAdapter.onCreateViewHolder(viewGroup, i);
    }

    public int getItemViewType(int i) {
        if (isHeaderViewPos(i)) {
            return this.mHeaderViews.keyAt(i);
        }
        if (isFooterViewPos(i)) {
            return this.mFootViews.keyAt((i - getHeadersCount()) - getRealItemCount());
        }
        return this.mInnerAdapter.getItemViewType(i - getHeadersCount());
    }

    private int getRealItemCount() {
        return this.mInnerAdapter.getItemCount();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!isHeaderViewPos(i) && !isFooterViewPos(i)) {
            this.mInnerAdapter.onBindViewHolder(viewHolder, i - getHeadersCount());
        }
    }

    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(this.mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i) {
                int itemViewType = HeaderAndFooterWrapper.this.getItemViewType(i);
                if (HeaderAndFooterWrapper.this.mHeaderViews.get(itemViewType) != null) {
                    return gridLayoutManager.getSpanCount();
                }
                if (HeaderAndFooterWrapper.this.mFootViews.get(itemViewType) != null) {
                    return gridLayoutManager.getSpanCount();
                }
                if (spanSizeLookup != null) {
                    return spanSizeLookup.getSpanSize(i);
                }
                return 1;
            }
        });
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        this.mInnerAdapter.onViewAttachedToWindow(viewHolder);
        int layoutPosition = viewHolder.getLayoutPosition();
        if (isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition)) {
            WrapperUtils.setFullSpan(viewHolder);
        }
    }

    private boolean isHeaderViewPos(int i) {
        return i < getHeadersCount();
    }

    private boolean isFooterViewPos(int i) {
        return i >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        SparseArrayCompat<View> sparseArrayCompat = this.mHeaderViews;
        sparseArrayCompat.put(sparseArrayCompat.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        SparseArrayCompat<View> sparseArrayCompat = this.mFootViews;
        sparseArrayCompat.put(sparseArrayCompat.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return this.mHeaderViews.size();
    }

    public int getFootersCount() {
        return this.mFootViews.size();
    }
}
