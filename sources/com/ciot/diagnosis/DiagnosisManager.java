package com.ciot.diagnosis;

import android.content.Context;
import com.ciot.diagnosis.base.BaseManager;
import com.ciot.diagnosis.util.MyContextUtil;

public class DiagnosisManager extends BaseManager {
    private static DiagnosisManager instance;

    public static DiagnosisManager getInstance() {
        if (instance == null) {
            synchronized (DiagnosisManager.class) {
                if (instance == null) {
                    instance = new DiagnosisManager();
                }
            }
        }
        return instance;
    }

    private DiagnosisManager() {
    }

    public void init(Context context, RobotType robotType, int i) {
        MyContextUtil.getInstance().setContext(context);
        super.init(robotType, i);
    }
}
