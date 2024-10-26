package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.dialog.EditDialog;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;

public class LineTrackingSettingView extends FrameLayout {
    /* access modifiers changed from: private */
    public View clLineTrackingContent;
    private View clLineTrackingTitle;
    private CommonPopupWindow cpw_lane;
    private CommonPopupWindow cpw_max_speed;
    /* access modifiers changed from: private */
    public TextView etPathWidth;
    private FragmentManager fragmentManager;
    /* access modifiers changed from: private */
    public ImageView ivLineTrackingArrow;
    /* access modifiers changed from: private */
    public LineTrackingSettingViewListener lineTrackingSettingViewListener;
    private EditDialog mEditDialog;
    private Switch switch_label_camera_window;

    public interface LineTrackingSettingViewListener {
        void onChecked(boolean z);
    }

    public void setLineTrackingSettingViewListener(LineTrackingSettingViewListener lineTrackingSettingViewListener2) {
        this.lineTrackingSettingViewListener = lineTrackingSettingViewListener2;
    }

    public LineTrackingSettingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LineTrackingSettingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LineTrackingSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(LayoutInflater.from(context).inflate(R.layout.line_tracking_setting, this, true));
    }

    private void initView(View view) {
        Switch switchR = (Switch) view.findViewById(R.id.witch_label_camera_window);
        this.switch_label_camera_window = switchR;
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (LineTrackingSettingView.this.lineTrackingSettingViewListener != null) {
                    LineTrackingSettingView.this.lineTrackingSettingViewListener.onChecked(z);
                }
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.et_path_width);
        this.etPathWidth = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LineTrackingSettingView.this.showPathWidthEditDialog();
            }
        });
        this.cpw_lane = (CommonPopupWindow) view.findViewById(R.id.cpw_lane);
        this.cpw_max_speed = (CommonPopupWindow) view.findViewById(R.id.cpw_max_speed);
        this.ivLineTrackingArrow = (ImageView) view.findViewById(R.id.iv_line_tracking_arrow);
        this.clLineTrackingTitle = view.findViewById(R.id.cl_line_tracking_title);
        this.clLineTrackingContent = view.findViewById(R.id.cl_line_tracking_content);
        this.clLineTrackingTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LineTrackingSettingView.this.clLineTrackingContent.getVisibility() == 0) {
                    LineTrackingSettingView.this.clLineTrackingContent.setVisibility(8);
                    LineTrackingSettingView.this.ivLineTrackingArrow.setBackground(LineTrackingSettingView.this.getContext().getDrawable(R.mipmap.right_arrow));
                    return;
                }
                LineTrackingSettingView.this.clLineTrackingContent.setVisibility(0);
                LineTrackingSettingView.this.ivLineTrackingArrow.setBackground(LineTrackingSettingView.this.getContext().getDrawable(R.mipmap.down_arrow));
            }
        });
    }

    public void setChecked(boolean z) {
        this.switch_label_camera_window.setChecked(z);
    }

    public boolean isChecked() {
        return this.switch_label_camera_window.isChecked();
    }

    public float getPathWidth() {
        try {
            return Float.parseFloat(this.etPathWidth.getText().toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getPassCapacity() {
        int selectedPos = this.cpw_lane.getSelectedPos();
        if (selectedPos != 0) {
            return selectedPos != 1 ? 0 : 2;
        }
        return 1;
    }

    public float getVelocity() {
        int selectedPos = this.cpw_max_speed.getSelectedPos();
        if (selectedPos == 0) {
            return 0.8f;
        }
        if (selectedPos != 1) {
            return selectedPos != 2 ? 0.0f : 0.3f;
        }
        return 0.5f;
    }

    public void setFragmentManager(FragmentManager fragmentManager2) {
        this.fragmentManager = fragmentManager2;
    }

    /* access modifiers changed from: private */
    public void showPathWidthEditDialog() {
        String string = StringUtils.getString(R.string.text_path_width);
        closeEditDialog();
        EditDialog newInstance = EditDialog.newInstance();
        this.mEditDialog = newInstance;
        newInstance.setSize(AdaptScreenUtils.pt2Px(1064.0f), AdaptScreenUtils.pt2Px(480.0f));
        this.mEditDialog.setTitle(string);
        String string2 = StringUtils.getString(R.string.unit_m);
        final String string3 = StringUtils.getString(R.string.txt_please_enter_value_range_hint, 0.0f + "", 10.0f + "");
        this.mEditDialog.setSubTitle(StringUtils.getString(R.string.txt_please_enter_value_tips, string2));
        this.mEditDialog.setHintTxt(string3);
        this.mEditDialog.setLimitLength(10);
        this.mEditDialog.setLimitDecimal(2);
        this.mEditDialog.setInputType(8194);
        this.mEditDialog.setOnDialogButtonClickListener(new EditDialog.OnDialogButtonClickListener(0.0f, 10.0f) {
            public void onCanCel() {
            }

            public boolean onConfirm(String str, float f) {
                try {
                    float parseFloat = Float.parseFloat(str);
                    if (parseFloat < 0.0f || parseFloat > 10.0f) {
                        MyToastUtils.showShort(LineTrackingSettingView.this.getContext(), string3);
                        return true;
                    }
                    SelfChassis.getInstance().configSafeStopDistance(parseFloat);
                    LineTrackingSettingView.this.etPathWidth.setText(str);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        FragmentManager fragmentManager2 = this.fragmentManager;
        if (fragmentManager2 != null) {
            this.mEditDialog.showAllowingStateLoss(fragmentManager2);
        }
    }

    private void closeEditDialog() {
        EditDialog editDialog = this.mEditDialog;
        if (editDialog != null && editDialog.isAdded()) {
            this.mEditDialog.dismissAllowingStateLoss();
            this.mEditDialog = null;
        }
    }
}
