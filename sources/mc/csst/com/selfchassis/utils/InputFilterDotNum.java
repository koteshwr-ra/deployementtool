package mc.csst.com.selfchassis.utils;

import android.text.InputFilter;
import android.text.Spanned;
import com.alibaba.android.arouter.utils.Consts;

public class InputFilterDotNum implements InputFilter {
    private int dot_num;

    public InputFilterDotNum(int i) {
        this.dot_num = i;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        try {
            if (charSequence.equals(Consts.DOT) && spanned.toString().length() == 0) {
                return "0.";
            }
            if (!spanned.toString().contains(Consts.DOT)) {
                return null;
            }
            if (spanned.toString().substring(spanned.toString().indexOf(Consts.DOT)).length() == this.dot_num + 1) {
                return "";
            }
            return null;
        } catch (Exception unused) {
            return "";
        }
    }
}
