package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.adapter.LayerSelectAdapter;
import mc.csst.com.selfchassis.utils.LayerDataUtils;
import mc.csst.com.selfchassis.utils.SoftTypeInfoManager;
import mc.csst.com.selfchassis.utils.bean.LayerBean;

public class LayerSelectView extends ConstraintLayout {
    private CardView mCardLayerChild;
    private LayerSelectAdapter mChildLayerSelectAdapter;
    private List<LayerBean> mLayerBeans = new ArrayList();
    private List<LayerBean> mLayerChildBeans = new ArrayList();
    private LayerSelectAdapter mLayerSelectAdapter;
    private OnSelectListener mOnSelectListener;
    private RecyclerView mRvLayer;
    private RecyclerView mRvLayerChild;
    private LayerBean mSelectLayerBean;
    private int mSoftType;

    public interface OnSelectListener {
        void onRefresh();
    }

    public LayerSelectView(Context context) {
        super(context);
        initView(context);
    }

    public LayerSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public LayerSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_layer_select, this, true);
        this.mCardLayerChild = (CardView) findViewById(R.id.card_layer_children);
        this.mRvLayer = (RecyclerView) findViewById(R.id.rv_layer);
        this.mRvLayerChild = (RecyclerView) findViewById(R.id.rv_layer_children);
        initData();
        initEvent();
    }

    private void initEvent() {
        this.mLayerSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LayerSelectView.this.lambda$initEvent$0$LayerSelectView(baseQuickAdapter, view, i);
            }
        });
        this.mLayerSelectAdapter.setOnSelectListener(new LayerSelectAdapter.OnSelectListener() {
            public final void onCheck(LayerBean layerBean, boolean z) {
                LayerSelectView.this.lambda$initEvent$1$LayerSelectView(layerBean, z);
            }
        });
        this.mChildLayerSelectAdapter.setOnSelectListener(new LayerSelectAdapter.OnSelectListener() {
            public final void onCheck(LayerBean layerBean, boolean z) {
                LayerSelectView.this.lambda$initEvent$2$LayerSelectView(layerBean, z);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$LayerSelectView(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        this.mLayerSelectAdapter.setSelectPosition(i);
        dealLayerSelect(this.mLayerBeans.get(i), false);
    }

    public /* synthetic */ void lambda$initEvent$1$LayerSelectView(LayerBean layerBean, boolean z) {
        dealLayerSelect(layerBean, true);
    }

    public /* synthetic */ void lambda$initEvent$2$LayerSelectView(LayerBean layerBean, boolean z) {
        String rootId = layerBean.getRootId();
        Iterator<LayerBean> it = this.mLayerBeans.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            LayerBean next = it.next();
            String id = next.getId();
            if (TextUtils.equals(rootId, id)) {
                next.setSelectStatus(z ^ true ? 1 : 0);
                LayerDataUtils.getInstance().setLayerStatus(id, z ^ true ? 1 : 0);
                break;
            }
        }
        this.mLayerSelectAdapter.refreshData(this.mLayerBeans);
        LayerDataUtils.getInstance().setLayerStatus(layerBean.getId(), layerBean.getSelectStatus());
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.onRefresh();
        }
    }

    private void dealLayerSelect(LayerBean layerBean, boolean z) {
        this.mSelectLayerBean = layerBean;
        List<LayerBean> layerChildData = LayerDataUtils.getInstance().getLayerChildData(layerBean.getId());
        this.mLayerChildBeans.clear();
        this.mLayerChildBeans = layerChildData;
        LayerDataUtils.getInstance().setLayerStatus(layerBean.getId(), layerBean.getSelectStatus());
        List<LayerBean> list = this.mLayerChildBeans;
        if (list == null || list.size() <= 0) {
            this.mCardLayerChild.setVisibility(8);
        } else if (z) {
            for (LayerBean next : this.mLayerChildBeans) {
                if (!LayerDataUtils.getInstance().isCanEditBySoftType() || next.getSelectStatus() != -1) {
                    LayerDataUtils.getInstance().setLayerStatus(next.getId(), layerBean.getSelectStatus());
                    next.setSelectStatus(layerBean.getSelectStatus());
                }
            }
            this.mCardLayerChild.setVisibility(0);
            this.mChildLayerSelectAdapter.setSelectPosition(-1);
            this.mChildLayerSelectAdapter.refreshData(this.mLayerChildBeans);
        } else if (this.mCardLayerChild.getVisibility() == 0) {
            this.mCardLayerChild.setVisibility(8);
        } else {
            this.mCardLayerChild.setVisibility(0);
            this.mChildLayerSelectAdapter.setSelectPosition(-1);
            this.mChildLayerSelectAdapter.refreshData(layerChildData);
        }
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.onRefresh();
        }
    }

    public void refreshData() {
        int softType = SoftTypeInfoManager.getInstance().getSoftType();
        LayerDataUtils.getInstance().setCanEditBySoftType();
        List<LayerBean> layerData = LayerDataUtils.getInstance().getLayerData();
        this.mLayerBeans = layerData;
        LayerSelectAdapter layerSelectAdapter = this.mLayerSelectAdapter;
        if (layerSelectAdapter != null) {
            layerSelectAdapter.refreshData(layerData);
        }
        if (this.mSelectLayerBean != null) {
            List<LayerBean> layerChildData = LayerDataUtils.getInstance().getLayerChildData(this.mSelectLayerBean.getId());
            LayerSelectAdapter layerSelectAdapter2 = this.mChildLayerSelectAdapter;
            if (layerSelectAdapter2 != null) {
                layerSelectAdapter2.refreshData(layerChildData);
            }
        }
        this.mSoftType = softType;
    }

    public void refreshByLeftToolType(String str) {
        String str2 = "";
        for (LayerBean next : LayerDataUtils.getInstance().getAllLayerChildData()) {
            String id = next.getId();
            if (TextUtils.equals(str, next.getId())) {
                str2 = next.getRootId();
                LayerDataUtils.getInstance().setLayerStatus(id, -1);
            } else if (next.getSelectStatus() == -1) {
                LayerDataUtils.getInstance().setLayerStatus(id, 1);
            }
        }
        List<LayerBean> layerData = LayerDataUtils.getInstance().getLayerData();
        for (LayerBean next2 : layerData) {
            String id2 = next2.getId();
            if (TextUtils.equals(str2, id2)) {
                next2.setSelectStatus(1);
                LayerDataUtils.getInstance().setLayerStatus(id2, 1);
            } else if (TextUtils.equals(str, id2)) {
                next2.setSelectStatus(-1);
                LayerDataUtils.getInstance().setLayerStatus(id2, -1);
            } else if (next2.getSelectStatus() == -1) {
                next2.setSelectStatus(1);
                LayerDataUtils.getInstance().setLayerStatus(id2, 1);
            }
        }
        this.mLayerSelectAdapter.refreshData(layerData);
        LayerBean layerBean = this.mSelectLayerBean;
        if (layerBean != null) {
            str2 = layerBean.getId();
        }
        List<LayerBean> layerChildData = LayerDataUtils.getInstance().getLayerChildData(str2);
        if (layerChildData.size() > 0) {
            for (LayerBean next3 : layerChildData) {
                String id3 = next3.getId();
                if (TextUtils.equals(str, id3)) {
                    next3.setSelectStatus(-1);
                    LayerDataUtils.getInstance().setLayerStatus(id3, -1);
                } else if (next3.getSelectStatus() == -1) {
                    next3.setSelectStatus(1);
                    LayerDataUtils.getInstance().setLayerStatus(id3, 1);
                }
            }
            this.mChildLayerSelectAdapter.refreshData(layerChildData);
        }
    }

    private void initData() {
        this.mCardLayerChild.setVisibility(8);
        List<LayerBean> layerData = LayerDataUtils.getInstance().getLayerData();
        this.mLayerBeans = layerData;
        this.mLayerSelectAdapter = new LayerSelectAdapter(layerData);
        this.mRvLayer.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mRvLayer.setAdapter(this.mLayerSelectAdapter);
        this.mChildLayerSelectAdapter = new LayerSelectAdapter(this.mLayerChildBeans);
        this.mRvLayerChild.setLayoutManager(new LinearLayoutManager(ContextUtil.getContext()));
        this.mRvLayerChild.setAdapter(this.mChildLayerSelectAdapter);
    }

    public void setVisibility(int i) {
        if (i == 0) {
            refreshData();
        }
        super.setVisibility(i);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }
}
