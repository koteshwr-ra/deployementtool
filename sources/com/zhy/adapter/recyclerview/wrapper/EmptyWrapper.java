package com.zhy.adapter.recyclerview.wrapper;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.utils.WrapperUtils;

public class EmptyWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_EMPTY = 2147483646;
    private int mEmptyLayoutId;
    private View mEmptyView;
    private RecyclerView.Adapter mInnerAdapter;

    public EmptyWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public boolean isEmpty() {
        return !(this.mEmptyView == null && this.mEmptyLayoutId == 0) && this.mInnerAdapter.getItemCount() == 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (!isEmpty()) {
            return this.mInnerAdapter.onCreateViewHolder(viewGroup, i);
        }
        if (this.mEmptyView != null) {
            return ViewHolder.createViewHolder(viewGroup.getContext(), this.mEmptyView);
        }
        return ViewHolder.createViewHolder(viewGroup.getContext(), viewGroup, this.mEmptyLayoutId);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(this.mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i) {
                if (EmptyWrapper.this.isEmpty()) {
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
        if (isEmpty()) {
            WrapperUtils.setFullSpan(viewHolder);
        }
    }

    public int getItemViewType(int i) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        return this.mInnerAdapter.getItemViewType(i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!isEmpty()) {
            this.mInnerAdapter.onBindViewHolder(viewHolder, i);
        }
    }

    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        }
        return this.mInnerAdapter.getItemCount();
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
    }

    public void setEmptyView(int i) {
        this.mEmptyLayoutId = i;
    }
}
