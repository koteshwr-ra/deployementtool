package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ciot.base.util.GsonUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import mc.csst.com.selfchassis.ui.adapter.WhAddressAdapter;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.CommonAddressBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.GetGatewayRespBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.transaction.GatewayTool;
import mc.csst.com.selfchassis.utils.MyToastUtils;

public class GatewayPopupWindow extends FrameLayout {
    private View container;
    private FragmentManager fragmentManager;
    private Context mContext;
    private EditDialog mEditDialog;
    private TextView tv_gateway_selected;

    public void setFragmentManager(FragmentManager fragmentManager2) {
        this.fragmentManager = fragmentManager2;
    }

    public GatewayPopupWindow(Context context) {
        this(context, (AttributeSet) null);
    }

    public GatewayPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GatewayPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initView(LayoutInflater.from(context).inflate(R.layout.gateway_popup_window, this, true));
    }

    private void initView(View view) {
        this.container = view.findViewById(R.id.container);
        this.tv_gateway_selected = (TextView) view.findViewById(R.id.tv_gateway_selected);
        updateSelectedMapText();
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GatewayPopupWindow.this.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSelectedMapText() {
        this.tv_gateway_selected.setText(getUrlDisplayName(GatewayTool.getInstance().getCurrentGateway()));
    }

    private String getUrlDisplayName(String str) {
        for (String next : GatewayTool.abbreviationUrlMap.keySet()) {
            if (next.equalsIgnoreCase(str)) {
                return GatewayTool.abbreviationUrlMap.get(next);
            }
        }
        return str;
    }

    public void show() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.recycler_view_gateway_popup_window, (ViewGroup) null, false);
        View findViewById = inflate.findViewById(R.id.about_wh_address_add_ll);
        final FixedPopupWindow fixedPopupWindow = new FixedPopupWindow(inflate, -2, -2, true);
        fixedPopupWindow.setAnimationStyle(-1);
        fixedPopupWindow.setOutsideTouchable(true);
        WhAddressAdapter whAddressAdapter = new WhAddressAdapter();
        ((RecyclerView) inflate.findViewById(R.id.rv_gateway)).setAdapter(whAddressAdapter);
        whAddressAdapter.setDataList(getAllGateway());
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GatewayPopupWindow.this.showUltrasoundDistanceEditDialog("");
                fixedPopupWindow.dismiss();
            }
        });
        whAddressAdapter.setOnItemClickListener(new WhAddressAdapter.OnItemClickListener() {
            public void selectedUrl(CommonAddressBean commonAddressBean) {
                GatewayTool.currentGateway = new GetGatewayRespBean(commonAddressBean.getUrl());
                GatewayPopupWindow.this.updateSelectedMapText();
                fixedPopupWindow.dismiss();
            }

            public void editUrl(CommonAddressBean commonAddressBean) {
                GatewayPopupWindow.this.showUltrasoundDistanceEditDialog(commonAddressBean.getUrl());
                fixedPopupWindow.dismiss();
            }

            public void delUrl(CommonAddressBean commonAddressBean) {
                if (GatewayTool.currentGateway.getDefaultAddress().equalsIgnoreCase(commonAddressBean.getUrl())) {
                    if (GatewayTool.wuHanAddressList.size() > 0) {
                        GatewayTool.currentGateway = new GetGatewayRespBean(GatewayTool.wuHanAddressList.get(0).getUrl());
                    } else if (GatewayTool.localAddressList.size() > 0) {
                        GatewayTool.currentGateway = new GetGatewayRespBean(GatewayTool.localAddressList.get(0).getUrl());
                    } else {
                        GatewayTool.currentGateway = new GetGatewayRespBean("");
                    }
                    GatewayPopupWindow.this.updateSelectedMapText();
                    GatewayTool.getInstance().initWuhanGateway();
                }
                GatewayPopupWindow.this.deleteGateway(commonAddressBean.getUrl());
                GatewayPopupWindow.this.saveGateway();
                fixedPopupWindow.dismiss();
            }
        });
        inflate.measure(0, 0);
        fixedPopupWindow.showAsDropDown(this.container, 0, 20);
    }

    /* access modifiers changed from: private */
    public void showUltrasoundDistanceEditDialog(String str) {
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(StringUtils.getString(R.string.txt_add_gateway));
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_add_gateway));
        this.mEditDialog.setHintTxt("");
        this.mEditDialog.setContent(str);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener() {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                if (GatewayPopupWindow.this.isUrl(str)) {
                    GatewayTool.localAddressList.add(new CommonAddressBean(str, false));
                    GatewayPopupWindow.this.saveGateway();
                    return false;
                }
                MyToastUtils.showShort(GatewayPopupWindow.this.getContext(), StringUtils.getString(R.string.txt_please_input_the_right_gateway));
                return true;
            }
        });
        this.mEditDialog.showAllowingStateLoss(this.fragmentManager);
    }

    public boolean isUrl(String str) {
        if (StringUtils.isEmpty(str)) {
            return true;
        }
        return Pattern.compile("(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?").matcher(str.trim()).matches();
    }

    private void closeEditDialog() {
        EditDialog editDialog = this.mEditDialog;
        if (editDialog != null && editDialog.isAdded()) {
            this.mEditDialog.dismissAllowingStateLoss();
            this.mEditDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public void saveGateway() {
        SPUtils.getInstance().put("gateway", GsonUtils.toJson(GatewayTool.localAddressList));
    }

    /* access modifiers changed from: private */
    public void deleteGateway(String str) {
        int i = -1;
        for (int i2 = 0; i2 < GatewayTool.localAddressList.size(); i2++) {
            if (GatewayTool.localAddressList.get(i2).getUrl().equalsIgnoreCase(str)) {
                i = i2;
            }
        }
        if (i != -1) {
            GatewayTool.localAddressList.remove(i);
        }
    }

    private List<CommonAddressBean> getAllGateway() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(GatewayTool.wuHanAddressList);
        arrayList.addAll(GatewayTool.localAddressList);
        return arrayList;
    }
}
