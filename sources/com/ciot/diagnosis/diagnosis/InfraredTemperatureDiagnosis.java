package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class InfraredTemperatureDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InfraredTemperatureDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_infrared_temperature_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.InfraredTemperatureDiagnosis$SubInfraredTemperatureDiagnosis r1 = new com.ciot.diagnosis.diagnosis.InfraredTemperatureDiagnosis$SubInfraredTemperatureDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.InfraredTemperatureDiagnosis.<init>():void");
    }

    public SubInfraredTemperatureDiagnosis getSubInfraredTemperatureDiagnosis() {
        return (SubInfraredTemperatureDiagnosis) DiagnosisUtil.getDiagnosis(SubInfraredTemperatureDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubInfraredTemperatureDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_GET_DEV_FD_FAILED = 1002;
        public static final int CODE_ERROR_START_PREVIEW_FAILED = 1003;
        public static final int CODE_INIT_FAIL = 1001;
        private static int CODE_UN_INIT = 1000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubInfraredTemperatureDiagnosis() {
            /*
                r2 = this;
                com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
                android.content.Context r0 = r0.getContext()
                android.content.res.Resources r0 = r0.getResources()
                int r1 = com.ciot.diagnosis.R.string.diagnosis_infrared_temperature_name
                java.lang.String r0 = r0.getString(r1)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.InfraredTemperatureDiagnosis.SubInfraredTemperatureDiagnosis.<init>():void");
        }
    }
}
