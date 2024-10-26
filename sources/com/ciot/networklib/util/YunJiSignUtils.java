package com.ciot.networklib.util;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class YunJiSignUtils {
    public static String sign(Map<String, String> map, String str, String str2, long j) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            String trim = ((String) next.getKey()).trim();
            String trim2 = ((String) next.getValue()).trim();
            if (!trim.equalsIgnoreCase("appname") && !trim.equalsIgnoreCase("secret") && !trim.equalsIgnoreCase("ts") && !trim.equalsIgnoreCase("sign") && !StringUtils.isTrimEmpty(trim2)) {
                arrayList.add(trim + ":" + trim2);
            }
        }
        Collections.sort(arrayList);
        arrayList.add("appname:" + str);
        if (!StringUtils.isTrimEmpty(str2)) {
            arrayList.add("secret:" + str2);
        }
        arrayList.add("ts:" + j);
        return EncryptUtils.encryptMD5ToString(join(arrayList, "|").getBytes()).toLowerCase();
    }

    private static String join(List<String> list, String str) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append(str);
        }
        return sb.substring(0, sb.length() - str.length());
    }
}
