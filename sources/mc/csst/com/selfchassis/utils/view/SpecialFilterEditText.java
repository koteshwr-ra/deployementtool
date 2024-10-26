package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import java.util.regex.Pattern;

public class SpecialFilterEditText extends AppCompatEditText {
    private InputFilter mSpecialCharFilter;

    public SpecialFilterEditText(Context context) {
        super(context);
        $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y r3 = $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y.INSTANCE;
        this.mSpecialCharFilter = r3;
        setFilters(new InputFilter[]{r3});
    }

    public SpecialFilterEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y r2 = $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y.INSTANCE;
        this.mSpecialCharFilter = r2;
        setFilters(new InputFilter[]{r2});
    }

    public SpecialFilterEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y r1 = $$Lambda$SpecialFilterEditText$VkUmWk3khOn413_L3BubcAHS48Y.INSTANCE;
        this.mSpecialCharFilter = r1;
        setFilters(new InputFilter[]{r1});
    }

    static /* synthetic */ CharSequence lambda$new$0(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        try {
            int languageType = MultiLanguageUtil.getInstance().getLanguageType();
            if (languageType == 0 || languageType == 1 || languageType == 2) {
                MyLogUtils.Logd("mSpecialCharFilter----->source：" + charSequence + " start：" + i + " end:" + i2 + " dest:" + spanned + " dstart:" + i3 + " dend:" + i4);
                String charSequence2 = charSequence.toString();
                if (Pattern.compile("[^a-zA-Z0-9\\u4e00-\\u9fa5\\s]").matcher(charSequence.toString()).find()) {
                    return "";
                }
                if (i3 != 0 || !TextUtils.isEmpty(charSequence2.trim())) {
                    return null;
                }
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.Logw(e.getMessage());
        }
        return null;
    }
}
