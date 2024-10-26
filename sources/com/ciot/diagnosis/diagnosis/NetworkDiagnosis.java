package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class NetworkDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NetworkDiagnosis() {
        /*
            r2 = this;
            int r0 = com.ciot.diagnosis.R.string.diagnosis_network_name
            java.lang.String r0 = getString(r0)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.NetworkDiagnosis$SubNetworkDiagnosis r1 = new com.ciot.diagnosis.diagnosis.NetworkDiagnosis$SubNetworkDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.NetworkDiagnosis.<init>():void");
    }

    public SubNetworkDiagnosis getSubNetworkDiagnosis() {
        return (SubNetworkDiagnosis) DiagnosisUtil.getDiagnosis(SubNetworkDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubNetworkDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_INIT_WELCOME_ROBOT = 7004;
        public static final int CODE_ERROR_LOGIN_GET_TOKEN = 7003;
        public static final int CODE_ERROR_ROBOT_ALLOW = 7002;
        public static final int CODE_INIT_FAIL = 7001;
        private static int CODE_UN_INIT = 7000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubNetworkDiagnosis() {
            /*
                r2 = this;
                com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
                android.content.Context r0 = r0.getContext()
                android.content.res.Resources r0 = r0.getResources()
                int r1 = com.ciot.diagnosis.R.string.diagnosis_sub_network_name
                java.lang.String r0 = r0.getString(r1)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.NetworkDiagnosis.SubNetworkDiagnosis.<init>():void");
        }
    }
}
