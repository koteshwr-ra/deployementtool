package mc.csst.com.selfchassis.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.ciot.base.storage.MySpUtils;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.OnlineUpdateUrlBean;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolSpConstant;
import okhttp3.HttpUrl;

public class ChassisSpUtils {
    public static boolean editOnlineUpdateUrl(OnlineUpdateUrlBean onlineUpdateUrlBean, OnlineUpdateUrlBean onlineUpdateUrlBean2) {
        try {
            List list = (List) GsonUtils.fromJson(MySpUtils.getInstance().getString(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI), new TypeToken<List<OnlineUpdateUrlBean>>() {
            }.getType());
            if (list != null) {
                if (!list.contains(onlineUpdateUrlBean2)) {
                    int indexOf = list.indexOf(onlineUpdateUrlBean);
                    if (indexOf == -1) {
                        return false;
                    }
                    list.add(indexOf, onlineUpdateUrlBean2);
                    MySpUtils.getInstance().put(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, GsonUtils.toJson(list));
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addOnlineUpdateUrl(OnlineUpdateUrlBean onlineUpdateUrlBean) {
        try {
            List<OnlineUpdateUrlBean> list = (List) GsonUtils.fromJson(MySpUtils.getInstance().getString(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI), new TypeToken<List<OnlineUpdateUrlBean>>() {
            }.getType());
            if (list == null) {
                list = new ArrayList<>();
            }
            String url = onlineUpdateUrlBean.getUrl();
            for (OnlineUpdateUrlBean url2 : list) {
                if (TextUtils.equals(url2.getUrl(), url)) {
                    return false;
                }
            }
            list.add(onlineUpdateUrlBean);
            MySpUtils.getInstance().put(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, GsonUtils.toJson(list));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeOnlineUpdateUrl(OnlineUpdateUrlBean onlineUpdateUrlBean) {
        boolean z = false;
        try {
            List list = (List) GsonUtils.fromJson(MySpUtils.getInstance().getString(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI), new TypeToken<List<OnlineUpdateUrlBean>>() {
            }.getType());
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                OnlineUpdateUrlBean onlineUpdateUrlBean2 = (OnlineUpdateUrlBean) list.get(i);
                if (TextUtils.equals(onlineUpdateUrlBean.getUrl(), onlineUpdateUrlBean2.getUrl()) && onlineUpdateUrlBean.getUrlDescType() == onlineUpdateUrlBean2.getUrlDescType()) {
                    z = list.remove(onlineUpdateUrlBean2);
                    break;
                }
                i++;
            }
            MySpUtils.getInstance().put(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, GsonUtils.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static List<OnlineUpdateUrlBean> getOnlineUpdateUrls() {
        List<OnlineUpdateUrlBean> list;
        try {
            list = (List) GsonUtils.fromJson(MySpUtils.getInstance().getString(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI), new TypeToken<List<OnlineUpdateUrlBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list == null ? new ArrayList() : list;
    }

    public static void setOnlineUpdateUrls(List<OnlineUpdateUrlBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        MySpUtils.getInstance().put(DeploymentToolSpConstant.SP_ONLINE_UPDATE_URL_LIST, GsonUtils.toJson(list));
    }

    public static OnlineUpdateUrlBean getCurOnlineUpdateUrl() {
        String string = MySpUtils.getInstance().getString(DeploymentToolSpConstant.SP_CUR_ONLINE_UPDATE_URL, "");
        if (string == null) {
            return null;
        }
        try {
            return (OnlineUpdateUrlBean) GsonUtils.fromJson(string, new TypeToken<OnlineUpdateUrlBean>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setCurOnlineUpdateUrl(OnlineUpdateUrlBean onlineUpdateUrlBean) {
        if (onlineUpdateUrlBean != null) {
            try {
                MySpUtils.getInstance().put(DeploymentToolSpConstant.SP_CUR_ONLINE_UPDATE_URL, GsonUtils.toJson(onlineUpdateUrlBean));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
