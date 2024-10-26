package com.ciot.base.util;

import android.text.TextUtils;
import com.ciot.base.constant.HttpConstant;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;

public class StringUtils {
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
    protected static final String TAG = StringUtils.class.getSimpleName();

    public static String addBlank(int i) {
        return String.format("%" + i + "s", new Object[]{""});
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isIPAddress(String str) {
        String[] split;
        if (str == null || (split = str.split("\\.")) == null) {
            return false;
        }
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str2 = split[i];
            if (isEmpty(str2.trim())) {
                return false;
            }
            try {
                int parseInt = Integer.parseInt(str2.trim());
                if (parseInt >= 0 && parseInt <= 255) {
                    i++;
                }
            } catch (NumberFormatException unused) {
            }
            return false;
        }
        return true;
    }

    public static boolean isEmailAddress(String str) {
        return isMatch("\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}", str);
    }

    public static boolean isDigit(String str) {
        if (!isEmpty(str)) {
            return isMatch("[0-9]*", str);
        }
        return false;
    }

    public static boolean isMatch(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }

    public static boolean isUrl(String str) {
        return isMatch("^((https?)|(ftp))://(?:(\\s+?)(?::(\\s+?))?@)?([a-zA-Z0-9\\-.]+)(?::(\\d+))?((?:/[a-zA-Z0-9\\-._?,'+\\&%$=~*!():@\\\\]*)+)?$", str);
    }

    public static String addSemanticMarks(String str) {
        StringBuilder sb = new StringBuilder("");
        if (TextUtils.isEmpty(str)) {
            return sb.toString();
        }
        sb.append("“");
        sb.append(str);
        sb.append("”");
        return sb.toString();
    }

    public static List<String> addSemanticListMarks(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (!(list == null || list.size() == 0)) {
            for (String addSemanticMarks : list) {
                arrayList.add(addSemanticMarks(addSemanticMarks));
            }
        }
        return arrayList;
    }

    public static String removeSemanticMarks(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return (!str.startsWith("“") || !str.endsWith("”")) ? str : str.substring(1, str.length() - 1);
    }

    public static String string2Unicode(String str) {
        if (isEmpty(str)) {
            return null;
        }
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char append : charArray) {
            stringBuffer.append(append);
        }
        return stringBuffer.toString();
    }

    public static String unicode2String(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        int length = trim.length() / 5;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += 5;
            stringBuffer.append((char) Integer.valueOf(trim.substring(i2 * 5, i)).intValue());
        }
        return stringBuffer.toString();
    }

    public static String getParamValueOfUrl(String str, String str2) {
        try {
            String[] split = str.split("[?]");
            if (split.length > 1) {
                for (String split2 : split[1].split("[&]")) {
                    String[] split3 = split2.split("[=]");
                    if (split3.length > 1) {
                        String str3 = split3[0];
                        String str4 = split3[1];
                        if (str3.equalsIgnoreCase(str2)) {
                            return str4;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String ToDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (charArray[i] > 65280 && charArray[i] < 65375) {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    public static String stringFilter(String str) {
        return Pattern.compile("[『』]").matcher(str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":")).replaceAll("").trim();
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public static boolean isChinese(String str) {
        char[] charArray = str.toCharArray();
        for (char isChinese : charArray) {
            if (isChinese(isChinese)) {
                return true;
            }
        }
        return false;
    }

    public static String getStringFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                byteArrayOutputStream.close();
                return byteArrayOutputStream2;
            }
        }
    }

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIP(String str) {
        return isMatch("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)", str);
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll(MapFragment.SLASH, "");
    }

    public static String getStringFirstAndEnd(String str, int i, int i2) {
        char[] charArray = str.toCharArray();
        if (charArray.length <= i + i2) {
            return str;
        }
        int length = (charArray.length - i2) - 1;
        for (int i3 = 0; i3 < str.length() - 1; i3++) {
            if (i3 >= i && i3 <= length) {
                charArray[i3] = '*';
            }
        }
        return String.valueOf(charArray);
    }

    public static boolean isVoiceIp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("https://") || str.startsWith(HttpConstant.HTTP_URL)) {
            return true;
        }
        return false;
    }

    public static boolean isFloat(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean isInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
