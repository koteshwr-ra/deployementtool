package mc.csst.com.selfchassis.ui.adapter;

import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;

public class CheckAdapter extends BaseQuickAdapter<SelfDiagnosisResponseBean.ValuesBean.StatusBean, BaseViewHolder> {
    public CheckAdapter(List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> list) {
        super(R.layout.adapter_item_self_check, list);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder baseViewHolder, SelfDiagnosisResponseBean.ValuesBean.StatusBean statusBean) {
        String hardware_id = statusBean.getHardware_id();
        int level = statusBean.getLevel();
        String name = statusBean.getName();
        String message = statusBean.getMessage();
        baseViewHolder.setText((int) R.id.hardware_tv, (CharSequence) hardware_id);
        if (level == 0) {
            baseViewHolder.setText((int) R.id.addtion_info_tv, (int) R.string.normal);
            baseViewHolder.setBackgroundRes(R.id.status_iv, R.mipmap.status_ok);
            baseViewHolder.setTextColor(R.id.addtion_info_tv, ContextUtil.getContext().getResources().getColor(R.color.clr_09DAA8));
            name = "";
        } else if (level == 1 || level == 3) {
            if (TextUtils.isEmpty(message)) {
                baseViewHolder.setText((int) R.id.addtion_info_tv, (int) R.string.warn);
            } else {
                baseViewHolder.setText((int) R.id.addtion_info_tv, (CharSequence) message);
            }
            baseViewHolder.setBackgroundRes(R.id.status_iv, R.mipmap.status_warning);
            baseViewHolder.setTextColor(R.id.addtion_info_tv, ContextUtil.getContext().getResources().getColor(R.color.clr_FF8329));
        } else if (level == 2) {
            if (TextUtils.isEmpty(message)) {
                baseViewHolder.setText((int) R.id.addtion_info_tv, (int) R.string.abnormal);
            } else {
                baseViewHolder.setText((int) R.id.addtion_info_tv, (CharSequence) message);
            }
            baseViewHolder.setBackgroundRes(R.id.status_iv, R.mipmap.status_error);
            baseViewHolder.setTextColor(R.id.addtion_info_tv, ContextUtil.getContext().getResources().getColor(R.color.clr_FF3E3E));
        }
        baseViewHolder.setText((int) R.id.error_code_tv, (CharSequence) name);
        if (baseViewHolder.getLayoutPosition() % 2 == 0) {
            baseViewHolder.setBackgroundRes(R.id.root_ln, R.drawable.adapter_item2);
        } else {
            baseViewHolder.setBackgroundRes(R.id.root_ln, R.drawable.adapter_item);
        }
    }
}
