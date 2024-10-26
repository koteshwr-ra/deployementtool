package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class VoiceRecognitionDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VoiceRecognitionDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_voice_recognition_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis$RecordDiagnosis r1 = new com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis$RecordDiagnosis
            r1.<init>()
            r0.add(r1)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis$AuthDiagnosis r1 = new com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis$AuthDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis.<init>():void");
    }

    public RecordDiagnosis getRecordDiagnosis() {
        return (RecordDiagnosis) DiagnosisUtil.getDiagnosis(RecordDiagnosis.NAME, this.subDiagnosisList);
    }

    public AuthDiagnosis getAuthDiagnosis() {
        return (AuthDiagnosis) DiagnosisUtil.getDiagnosis(AuthDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class RecordDiagnosis extends BaseDiagnosis {
        public static final int CODE_INIT_FAIL = 9001;
        private static int CODE_UN_INIT = 9000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public RecordDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_sub_voice_record_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis.RecordDiagnosis.<init>():void");
        }
    }

    public static class AuthDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_GET_CODE_TIMEOUT = 9003;
        public static final int CODE_INIT_FAIL = 9002;
        private static int CODE_UN_INIT = 9100;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public AuthDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_sub_voice_auth_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis.AuthDiagnosis.<init>():void");
        }
    }
}
