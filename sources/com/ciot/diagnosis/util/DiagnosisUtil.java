package com.ciot.diagnosis.util;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import java.util.Iterator;
import java.util.List;

public class DiagnosisUtil {
    public static <T extends BaseDiagnosis> T getDiagnosis(String str, List<BaseDiagnosis> list) {
        try {
            Iterator<BaseDiagnosis> it = list.iterator();
            while (it.hasNext()) {
                T t = (BaseDiagnosis) it.next();
                if (str.equals(t.getDiagnosisName())) {
                    return t;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T extends BaseGroupDiagnosis> T getGroupDiagnosis(String str, List<BaseGroupDiagnosis> list) {
        try {
            Iterator<BaseGroupDiagnosis> it = list.iterator();
            while (it.hasNext()) {
                T t = (BaseGroupDiagnosis) it.next();
                if (str.equals(t.getDiagnosisName())) {
                    return t;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
