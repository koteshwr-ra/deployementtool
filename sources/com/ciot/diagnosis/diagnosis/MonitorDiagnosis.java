package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class MonitorDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MonitorDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_monitor_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.MonitorDiagnosis$SubMonitorDiagnosis r1 = new com.ciot.diagnosis.diagnosis.MonitorDiagnosis$SubMonitorDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.MonitorDiagnosis.<init>():void");
    }

    public SubMonitorDiagnosis getSubMonitorDiagnosis() {
        return (SubMonitorDiagnosis) DiagnosisUtil.getDiagnosis(SubMonitorDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubMonitorDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_GET_CODE_FAIL = 6003;
        public static final int CODE_ERROR_GET_CODE_TIMEOUT = 6004;
        public static final int CODE_ERROR_INNER_INIT_FAIL = 6002;
        public static final int CODE_INIT_FAIL = 6001;
        private static int CODE_UN_INIT = 6000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubMonitorDiagnosis() {
            /*
                r2 = this;
                com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
                android.content.Context r0 = r0.getContext()
                android.content.res.Resources r0 = r0.getResources()
                int r1 = com.ciot.diagnosis.R.string.diagnosis_monitor_name
                java.lang.String r0 = r0.getString(r1)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.MonitorDiagnosis.SubMonitorDiagnosis.<init>():void");
        }
    }
}
