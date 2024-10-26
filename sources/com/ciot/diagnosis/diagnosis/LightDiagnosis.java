package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class LightDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LightDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_light_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.LightDiagnosis$SubLightDiagnosis r1 = new com.ciot.diagnosis.diagnosis.LightDiagnosis$SubLightDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.LightDiagnosis.<init>():void");
    }

    public SubLightDiagnosis getSubLightDiagnosis() {
        return (SubLightDiagnosis) DiagnosisUtil.getDiagnosis(SubLightDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubLightDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_GET_MESSAGE = 5004;
        public static final int CODE_ERROR_GET_ROBOT_ID = 5003;
        public static final int CODE_ERROR_VERIFY_MESSAGE = 5005;
        public static final int CODE_INITED_AND_DID_NOT_READ_DATA = 5002;
        public static final int CODE_INIT_FAIL = 5001;
        private static int CODE_UN_INIT = 5000;
        public static String NAME;
        public boolean isSuccess;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubLightDiagnosis() {
            /*
                r2 = this;
                com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
                android.content.Context r0 = r0.getContext()
                android.content.res.Resources r0 = r0.getResources()
                int r1 = com.ciot.diagnosis.R.string.diagnosis_light_name
                java.lang.String r0 = r0.getString(r1)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.LightDiagnosis.SubLightDiagnosis.<init>():void");
        }

        public void setStatus(int i, String str) {
            if (!checkAlreadySucceedInErrorCode(i)) {
                super.setStatus(i, str);
            }
        }

        public void setStatus(int i, int i2) {
            if (!checkAlreadySucceedInErrorCode(i)) {
                super.setStatus(i, i2);
            }
        }

        private boolean checkAlreadySucceedInErrorCode(int i) {
            if (i == 0) {
                this.isSuccess = true;
            }
            if (!this.isSuccess || i == 0) {
                return false;
            }
            return true;
        }
    }
}
