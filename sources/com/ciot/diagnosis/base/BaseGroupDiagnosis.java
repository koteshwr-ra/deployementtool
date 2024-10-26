package com.ciot.diagnosis.base;

import com.ciot.diagnosis.util.MyContextUtil;
import java.util.ArrayList;
import java.util.List;

public class BaseGroupDiagnosis {
    protected String diagnosisName;
    protected List<BaseDiagnosis> subDiagnosisList = new ArrayList();

    public BaseGroupDiagnosis(String str) {
        this.diagnosisName = str;
    }

    public List<BaseDiagnosis> getSubDiagnosisList() {
        return this.subDiagnosisList;
    }

    public String getDiagnosisName() {
        return this.diagnosisName;
    }

    public static String getString(int i) {
        return MyContextUtil.getInstance().getContext().getResources().getString(i);
    }
}
