package mc.csst.com.selfchassis.model.trajectory;

import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;

public class MultiScheduleModel {
    public static List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> buildList = new ArrayList();
    private static final MultiScheduleModel instance = new MultiScheduleModel();

    private MultiScheduleModel() {
    }

    public static MultiScheduleModel getInstance() {
        return instance;
    }
}
