package mc.csst.com.selfchassislibrary.utils;

import mc.csst.com.selfchassislibrary.bean.EularBean;
import mc.csst.com.selfchassislibrary.bean.QuaternionBean;

public class ConvertorUtils {
    public static QuaternionBean eular2quaternion(float f, float f2, float f3) {
        QuaternionBean quaternionBean = new QuaternionBean();
        double d = (double) (f / 2.0f);
        double d2 = (double) (f2 / 2.0f);
        double d3 = (double) (f3 / 2.0f);
        float cos = (float) ((Math.cos(d) * Math.cos(d2) * Math.cos(d3)) + (Math.sin(d) * Math.sin(d2) * Math.sin(d3)));
        float sin = (float) (((Math.sin(d) * Math.cos(d2)) * Math.cos(d3)) - ((Math.cos(d) * Math.sin(d2)) * Math.sin(d3)));
        quaternionBean.setW(cos);
        quaternionBean.setX(sin);
        quaternionBean.setY((float) ((Math.cos(d) * Math.sin(d2) * Math.cos(d3)) + (Math.sin(d) * Math.cos(d2) * Math.sin(d3))));
        quaternionBean.setZ((float) (((Math.cos(d) * Math.cos(d2)) * Math.sin(d3)) - ((Math.sin(d) * Math.sin(d2)) * Math.cos(d3))));
        return quaternionBean;
    }

    public static QuaternionBean eular2quaternion(EularBean eularBean) {
        return eular2quaternion(eularBean.getR(), eularBean.getP(), eularBean.getY());
    }

    public static EularBean quaternion2eular(float f, float f2, float f3, float f4) {
        EularBean eularBean = new EularBean();
        float f5 = f3 * f3;
        eularBean.setR((float) Math.atan2((double) (((f * f2) + (f3 * f4)) * 2.0f), (double) (1.0f - (((f2 * f2) + f5) * 2.0f))));
        eularBean.setP((float) Math.asin((double) (((f * f3) - (f4 * f2)) * 2.0f)));
        eularBean.setY((float) Math.atan2((double) (((f * f4) + (f2 * f3)) * 2.0f), (double) (1.0f - ((f5 + (f4 * f4)) * 2.0f))));
        return eularBean;
    }

    public static EularBean quaternion2eular(QuaternionBean quaternionBean) {
        return quaternion2eular(quaternionBean.getW(), quaternionBean.getX(), quaternionBean.getY(), quaternionBean.getZ());
    }

    public static float getDistanceBetween2Points(float f, float f2, float f3, float f4) {
        return (float) Math.sqrt(Math.pow((double) (f2 - f4), 2.0d) + Math.pow((double) (f - f3), 2.0d));
    }
}
