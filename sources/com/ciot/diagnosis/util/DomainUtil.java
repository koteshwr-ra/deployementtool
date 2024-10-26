package com.ciot.diagnosis.util;

import android.net.Uri;
import android.text.TextUtils;

public class DomainUtil {
    public static String getDomain(String str) {
        return (TextUtils.isEmpty(str) || !str.startsWith("http")) ? str : Uri.parse(str).getHost();
    }

    public static void main(String[] strArr) {
        System.out.println(getDomain("https://mrad-reqtask-test.brandwisdom.cn/"));
    }
}
