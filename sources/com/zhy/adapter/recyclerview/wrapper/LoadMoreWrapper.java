package com.zhy.adapter.recyclerview.wrapper;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.utils.WrapperUtils;

public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_LOAD_MORE = 2147483645;
    private RecyclerView.Adapter mInnerAdapter;
    private int mLoadMoreLayoutId;
    private View mLoadMoreView;
    private OnLoadMoreListener mOnLoadMoreListener;

    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    public LoadMoreWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    private boolean hasLoadMore() {
        return (this.mLoadMoreView == null && this.mLoadMoreLayoutId == 0) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean isShowLoadMore(int i) {
        return hasLoadMore() && i >= this.mInnerAdapter.getItemCount();
    }

    public int getItemViewType(int i) {
        if (isShowLoadMore(i)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return this.mInnerAdapter.getItemViewType(i);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 2147483645) {
            return this.mInnerAdapter.onCreateViewHolder(viewGroup, i);
        }
        if (this.mLoadMoreView != null) {
            return ViewHolder.createViewHolder(viewGroup.getContext(), this.mLoadMoreView);
        }
        return ViewHolder.createViewHolder(viewGroup.getContext(), viewGroup, this.mLoadMoreLayoutId);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (isShowLoadMore(i)) {
            OnLoadMoreListener onLoadMoreListener = this.mOnLoadMoreListener;
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMoreRequested();
                return;
            }
            return;
        }
        this.mInnerAdapter.onBindViewHolder(viewHolder, i);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(this.mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i) {
                if (LoadMoreWrapper.this.isShowLoadMore(i)) {
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
        if (isShowLoadMore(viewHolder.getLayoutPosition())) {
            setFullSpan(viewHolder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }

    public int getItemCount() {
        return this.mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        if (onLoadMoreListener != null) {
            this.mOnLoadMoreListener = onLoadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View view) {
        this.mLoadMoreView = view;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int i) {
        this.mLoadMoreLayoutId = i;
        return this;
    }
}
