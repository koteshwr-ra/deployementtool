package mc.csst.com.selfchassislibrary.utils.navigation;

import android.content.Context;

public class GetPath {
    public static String getCompanyConfigPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/data/company.txt");
    }

    public static String getGuideConfigPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/data/guide.txt");
    }

    public static String getIntroducedConfigPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/data/Introduced.txt");
    }

    public static String getPointConfigPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/data/point.txt");
    }

    public static String getConfigZipPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot.zip");
    }

    public static String getUnpackPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/");
    }

    public static String getConfigImgPath(Context context) {
        return SDUtils.getFileRootPath(context, "servicerobot/img/");
    }
}
