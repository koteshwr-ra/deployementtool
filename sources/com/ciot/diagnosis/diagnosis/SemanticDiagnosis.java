package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class SemanticDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SemanticDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_semantic_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.SemanticDiagnosis$SubSemanticRegisterDiagnosis r1 = new com.ciot.diagnosis.diagnosis.SemanticDiagnosis$SubSemanticRegisterDiagnosis
            r1.<init>()
            r0.add(r1)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.SemanticDiagnosis$SubSemanticApiDiagnosis r1 = new com.ciot.diagnosis.diagnosis.SemanticDiagnosis$SubSemanticApiDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.SemanticDiagnosis.<init>():void");
    }

    public SubSemanticRegisterDiagnosis getSubSemanticDiagnosis() {
        return (SubSemanticRegisterDiagnosis) DiagnosisUtil.getDiagnosis(SubSemanticRegisterDiagnosis.NAME, this.subDiagnosisList);
    }

    public SubSemanticApiDiagnosis getSubSemanticApiDiagnosis() {
        return (SubSemanticApiDiagnosis) DiagnosisUtil.getDiagnosis(SubSemanticApiDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubSemanticRegisterDiagnosis extends BaseDiagnosis {
        public static final int CODE_INIT_FAIL = 8001;
        private static int CODE_UN_INIT = 8000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubSemanticRegisterDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_semantic_register_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.SemanticDiagnosis.SubSemanticRegisterDiagnosis.<init>():void");
        }
    }

    public static class SubSemanticApiDiagnosis extends BaseDiagnosis {
        public static final int CODE_INIT_FAIL = 8102;
        private static int CODE_UN_INIT = 8100;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubSemanticApiDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_semantic_request
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.SemanticDiagnosis.SubSemanticApiDiagnosis.<init>():void");
        }
    }
}
