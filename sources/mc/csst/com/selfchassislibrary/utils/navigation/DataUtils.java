package mc.csst.com.selfchassislibrary.utils.navigation;

import com.blankj.utilcode.util.FileIOUtils;
import com.ciot.base.util.ContextUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.utils.navigation.GuideBean;
import mc.csst.com.selfchassislibrary.utils.navigation.IntroducedBean;
import mc.csst.com.selfchassislibrary.utils.navigation.PointBean;

public class DataUtils {
    public static String getCompanyName() {
        return FileIOUtils.readFile2String(GetPath.getCompanyConfigPath(ContextUtil.getContext()));
    }

    public static List<PointBean.DataBean> getPointBean() {
        ArrayList arrayList;
        JsonSyntaxException e;
        try {
            PointBean pointBean = (PointBean) new Gson().fromJson(FileIOUtils.readFile2String(GetPath.getPointConfigPath(ContextUtil.getContext())), PointBean.class);
            arrayList = new ArrayList();
            if (pointBean == null) {
                return arrayList;
            }
            try {
                return pointBean.getData();
            } catch (JsonSyntaxException e2) {
                e = e2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (JsonSyntaxException e3) {
            arrayList = null;
            e = e3;
            e.printStackTrace();
            return arrayList;
        }
    }

    public static List<GuideBean.DataBean> getGuideBean() {
        ArrayList arrayList;
        JsonSyntaxException e;
        try {
            GuideBean guideBean = (GuideBean) new Gson().fromJson(FileIOUtils.readFile2String(GetPath.getGuideConfigPath(ContextUtil.getContext())), GuideBean.class);
            arrayList = new ArrayList();
            if (guideBean == null) {
                return arrayList;
            }
            try {
                for (GuideBean.DataBean next : guideBean.getData()) {
                    if (next.getIsuser() == 1) {
                        arrayList.add(next);
                    }
                }
            } catch (JsonSyntaxException e2) {
                e = e2;
                e.printStackTrace();
                return arrayList;
            }
            return arrayList;
        } catch (JsonSyntaxException e3) {
            arrayList = null;
            e = e3;
            e.printStackTrace();
            return arrayList;
        }
    }

    public static List<IntroducedBean.DataBean> getIntroducedBean() {
        ArrayList arrayList;
        JsonSyntaxException e;
        try {
            arrayList = new ArrayList();
            try {
                String readFile2String = FileIOUtils.readFile2String(GetPath.getIntroducedConfigPath(ContextUtil.getContext()));
                Gson gson = new Gson();
                gson.fromJson(readFile2String, IntroducedBean.class);
                IntroducedBean introducedBean = (IntroducedBean) gson.fromJson(readFile2String, IntroducedBean.class);
                if (introducedBean != null) {
                    List<IntroducedBean.DataBean> data = introducedBean.getData();
                    for (int i = 0; i < data.size(); i++) {
                        IntroducedBean.DataBean dataBean = data.get(i);
                        if (dataBean != null && dataBean.getIsshow() == 1) {
                            arrayList.add(dataBean);
                        }
                    }
                }
            } catch (JsonSyntaxException e2) {
                e = e2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (JsonSyntaxException e3) {
            JsonSyntaxException jsonSyntaxException = e3;
            arrayList = null;
            e = jsonSyntaxException;
            e.printStackTrace();
            return arrayList;
        }
        return arrayList;
    }
}
