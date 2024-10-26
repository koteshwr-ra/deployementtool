package mc.csst.com.selfchassis.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.util.MyLogUtils;
import java.util.regex.Pattern;
import mc.csst.com.selfchassis.R;

public class LimitEditText extends LinearLayout {
    private static final int DEFAULT_LIMIT_NUM = 15;
    /* access modifiers changed from: private */
    public int MAX_COUNT;
    private String contentText;
    private int contentTextColor;
    private int contentTextSize;
    private float contentViewHeight;
    /* access modifiers changed from: private */
    public EditText et_input;
    private String hintText;
    private int hintTextColor;
    /* access modifiers changed from: private */
    public boolean ignoreCnOrEn;
    private boolean isInputAble;
    private boolean isSpecialFilter;
    private boolean isVisibleLimitNum;
    private Context mContext;
    private InputFilter mSpecialCharFilter;
    /* access modifiers changed from: private */
    public TextWatcher mTextWatcher;
    private boolean showPositive;
    private TextView tv_input;

    public void setInputAble(boolean z) {
        this.isInputAble = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isInputAble) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public LimitEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public LimitEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LimitEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isInputAble = true;
        this.isSpecialFilter = false;
        this.isVisibleLimitNum = true;
        this.mSpecialCharFilter = new InputFilter() {
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return LimitEditText.this.lambda$new$0$LimitEditText(charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.mTextWatcher = new TextWatcher() {
            private int editEnd;
            private int editStart;

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.editStart = LimitEditText.this.et_input.getSelectionStart();
                this.editEnd = LimitEditText.this.et_input.getSelectionEnd();
                LimitEditText.this.et_input.removeTextChangedListener(LimitEditText.this.mTextWatcher);
                try {
                    if (LimitEditText.this.ignoreCnOrEn) {
                        while (LimitEditText.this.calculateLengthIgnoreCnOrEn(editable.toString()) > LimitEditText.this.MAX_COUNT) {
                            editable.delete(this.editStart - 1, this.editEnd);
                            this.editStart--;
                            this.editEnd--;
                        }
                    } else {
                        while (LimitEditText.this.calculateLength(editable.toString()) > ((long) LimitEditText.this.MAX_COUNT)) {
                            editable.delete(this.editStart - 1, this.editEnd);
                            this.editStart--;
                            this.editEnd--;
                        }
                    }
                } catch (Exception unused) {
                }
                LimitEditText.this.et_input.setSelection(this.editStart);
                LimitEditText.this.et_input.addTextChangedListener(LimitEditText.this.mTextWatcher);
                LimitEditText.this.configCount();
            }
        };
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LimitEditText);
        this.MAX_COUNT = obtainStyledAttributes.getInteger(7, 15);
        this.ignoreCnOrEn = obtainStyledAttributes.getBoolean(6, true);
        this.showPositive = obtainStyledAttributes.getBoolean(8, true);
        this.isSpecialFilter = obtainStyledAttributes.getBoolean(9, false);
        this.isVisibleLimitNum = obtainStyledAttributes.getBoolean(10, true);
        this.hintText = obtainStyledAttributes.getString(4);
        this.hintTextColor = obtainStyledAttributes.getColor(5, this.mContext.getResources().getColor(com.ciot.sentrymove.R.color.clr_999999));
        this.contentText = obtainStyledAttributes.getString(0);
        this.contentTextColor = obtainStyledAttributes.getColor(1, this.mContext.getResources().getColor(com.ciot.sentrymove.R.color.clr_666666));
        this.contentTextSize = obtainStyledAttributes.getDimensionPixelSize(2, AdaptScreenUtils.pt2Px(24.0f));
        this.contentViewHeight = (float) obtainStyledAttributes.getDimensionPixelSize(3, -2);
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.mContext).inflate(com.ciot.sentrymove.R.layout.view_limit_edittext, this);
        this.et_input = (EditText) inflate.findViewById(com.ciot.sentrymove.R.id.et_input);
        this.tv_input = (TextView) inflate.findViewById(com.ciot.sentrymove.R.id.tv_input);
        if (getBackground() == null) {
            setBackgroundResource(com.ciot.sentrymove.R.drawable.line_et_bg);
        }
        if (this.isVisibleLimitNum) {
            this.et_input.addTextChangedListener(this.mTextWatcher);
            this.tv_input.requestFocus();
            configCount();
            EditText editText = this.et_input;
            editText.setSelection(editText.length());
        } else {
            this.tv_input.setVisibility(8);
        }
        this.et_input.setHint(this.hintText);
        this.et_input.setHintTextColor(this.hintTextColor);
        this.et_input.setText(this.contentText);
        this.et_input.setTextColor(this.contentTextColor);
        this.et_input.setTextSize(0, (float) this.contentTextSize);
        this.et_input.setHeight((int) this.contentViewHeight);
        this.et_input.setFilters(new InputFilter[]{this.mSpecialCharFilter});
        this.et_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                LimitEditText.this.setSelected(z);
            }
        });
    }

    public /* synthetic */ CharSequence lambda$new$0$LimitEditText(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.isSpecialFilter) {
            MyLogUtils.Logd("mSpecialCharFilter----->" + charSequence + "");
            if (Pattern.compile("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]").matcher(charSequence.toString()).find()) {
                return "";
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public long calculateLength(CharSequence charSequence) {
        double d = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            d += (charAt <= 0 || charAt >= 127) ? 1.0d : 0.5d;
        }
        return Math.round(d);
    }

    /* access modifiers changed from: private */
    public int calculateLengthIgnoreCnOrEn(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void configCount() {
        long j;
        if (this.ignoreCnOrEn) {
            j = (long) calculateLengthIgnoreCnOrEn(this.et_input.getText().toString());
        } else {
            j = calculateLength(this.et_input.getText().toString());
        }
        if (this.showPositive) {
            TextView textView = this.tv_input;
            textView.setText(String.valueOf(j) + "/" + this.MAX_COUNT);
            return;
        }
        TextView textView2 = this.tv_input;
        textView2.setText(String.valueOf(((long) this.MAX_COUNT) - j) + "/" + this.MAX_COUNT);
    }

    private static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setContentText(String str) {
        this.contentText = str;
        EditText editText = this.et_input;
        if (editText != null) {
            editText.setText(str);
        }
    }

    public String getContentText() {
        EditText editText = this.et_input;
        if (editText != null) {
            this.contentText = editText.getText() == null ? "" : this.et_input.getText().toString();
        }
        return this.contentText;
    }

    public void setHintText(String str) {
        this.hintText = str;
        EditText editText = this.et_input;
        if (editText != null) {
            editText.setHint(str);
        }
    }

    public void setContentTextSize(int i) {
        EditText editText = this.et_input;
        if (editText != null) {
            editText.setTextSize(0, (float) i);
        }
    }

    public void setContentTextColor(int i) {
        EditText editText = this.et_input;
        if (editText != null) {
            editText.setTextColor(i);
        }
    }

    public void setHintColor(int i) {
        EditText editText = this.et_input;
        if (editText != null) {
            editText.setHintTextColor(i);
        }
    }

    public String getHintText() {
        EditText editText = this.et_input;
        if (editText != null) {
            this.hintText = editText.getHint() == null ? "" : this.et_input.getHint().toString();
        }
        return this.hintText;
    }

    public void setMaxCount(int i) {
        this.MAX_COUNT = i;
        configCount();
    }

    public void setIgnoreCnOrEn(boolean z) {
        this.ignoreCnOrEn = z;
        configCount();
    }

    public void setError(CharSequence charSequence) {
        EditText editText = this.et_input;
        if (editText != null) {
            this.hintText = editText.getHint() == null ? "" : this.et_input.getHint().toString();
        }
        this.et_input.requestFocus();
        this.et_input.setError(charSequence);
    }
}
