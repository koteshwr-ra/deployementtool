package com.limpoxe.support.servicemanager.util;

import android.os.Bundle;
import com.limpoxe.support.servicemanager.compat.BundleCompat;

public class ParamUtil {
    public static final String method_args_count = "method_args_count";
    public static final String method_name = "method_name";
    public static final String result = "result";
    public static final String service_name = "service_name";

    public static Bundle wrapperParams(String str, String str2, Object[] objArr) {
        Bundle bundle = new Bundle();
        bundle.putString(service_name, str);
        bundle.putString(method_name, str2);
        if (objArr != null && objArr.length > 0) {
            bundle.putInt(method_args_count, objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                BundleCompat.putObject(bundle, String.valueOf(i), objArr[i]);
            }
        }
        return bundle;
    }

    public static Object[] unwrapperParams(Bundle bundle) {
        int i = bundle.getInt(method_args_count);
        if (i <= 0) {
            return null;
        }
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = bundle.get(String.valueOf(i2));
        }
        return objArr;
    }

    public static Object getResult(Bundle bundle) {
        if (bundle != null) {
            return bundle.get(result);
        }
        return null;
    }
}
