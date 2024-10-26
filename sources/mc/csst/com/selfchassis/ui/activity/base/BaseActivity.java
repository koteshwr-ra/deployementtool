package mc.csst.com.selfchassis.ui.activity.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.util.MultiLanguageUtil;
import com.ciot.base.util.MyLogUtils;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.ui.activity.base.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public final String TAG = getClass().getSimpleName();
    public Context mContext;
    private final List<String> mPermissionList = new ArrayList();
    private final String[] mPermissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    protected T mPresenter;

    /* access modifiers changed from: protected */
    public abstract View getView();

    /* access modifiers changed from: protected */
    public abstract void initData();

    /* access modifiers changed from: protected */
    public abstract void initEvent();

    /* access modifiers changed from: protected */
    public abstract void initView();

    private void initPermission() {
        for (String str : this.mPermissions) {
            if (ContextCompat.checkSelfPermission(getApplication(), str) != 0) {
                this.mPermissionList.add(str);
            }
        }
        if (this.mPermissionList.size() != 0) {
            List<String> list = this.mPermissionList;
            ActivityCompat.requestPermissions(this, (String[]) list.toArray(new String[list.size()]), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideNavigationBar();
        hideTitle();
        fullScreen();
        setContentView(getView());
        this.mContext = getApplicationContext();
        if (getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
        initView();
        initData();
        initEvent();
        initPermission();
    }

    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1920);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
        super.onDestroy();
    }

    private void hideNavigationBar() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = InputDeviceCompat.SOURCE_TOUCHSCREEN;
        window.setAttributes(attributes);
    }

    private void hideTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void fullScreen() {
        getWindow().setFlags(1024, 1024);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        MyLogUtils.Logd(this.TAG, "attachBaseContext setConfiguration");
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(context));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MyLogUtils.Logd(this.TAG, "onConfigurationChanged setConfiguration");
        MultiLanguageUtil.getInstance().setConfiguration();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MyLogUtils.Logd(this.TAG, "onResume setConfiguration");
        MultiLanguageUtil.getInstance().setConfiguration();
    }

    public void applyOverrideConfiguration(Configuration configuration) {
        if (configuration != null) {
            int i = configuration.uiMode;
            configuration.setTo(getBaseContext().getResources().getConfiguration());
            configuration.uiMode = i;
        }
        super.applyOverrideConfiguration(configuration);
    }
}
