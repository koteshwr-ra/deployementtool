package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class FaceRecognizeDiagnosis extends BaseGroupDiagnosis {
    public static String NAME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FaceRecognizeDiagnosis() {
        /*
            r2 = this;
            com.ciot.diagnosis.util.MyContextUtil r0 = com.ciot.diagnosis.util.MyContextUtil.getInstance()
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.ciot.diagnosis.R.string.diagnosis_face_recognize_name
            java.lang.String r0 = r0.getString(r1)
            NAME = r0
            r2.<init>(r0)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis$SubFaceShangTangDiagnosis r1 = new com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis$SubFaceShangTangDiagnosis
            r1.<init>()
            r0.add(r1)
            java.util.List r0 = r2.subDiagnosisList
            com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis$SubFaceCameraDiagnosis r1 = new com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis$SubFaceCameraDiagnosis
            r1.<init>()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis.<init>():void");
    }

    public SubFaceShangTangDiagnosis getShangTangDiagnosis() {
        return (SubFaceShangTangDiagnosis) DiagnosisUtil.getDiagnosis(SubFaceShangTangDiagnosis.NAME, this.subDiagnosisList);
    }

    public SubFaceCameraDiagnosis getSubFaceCameraDiagnosis() {
        return (SubFaceCameraDiagnosis) DiagnosisUtil.getDiagnosis(SubFaceCameraDiagnosis.NAME, this.subDiagnosisList);
    }

    public static class SubFaceShangTangDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_ARC_SOFT_90118 = 4006;
        public static final int CODE_ERROR_ARC_SOFT_90137 = 4007;
        public static final int CODE_ERROR_ARC_SOFT_98309 = 4008;
        public static final int CODE_ERROR_ARC_SOFT_OTHER = 4009;
        public static final int CODE_ERROR_SENSE_TIME_ENCRYPTION_CHIP = 4002;
        public static final int CODE_ERROR_SENSE_TIME_OTHER = 4003;
        public static final int CODE_INIT_FAIL = 4001;
        private static int CODE_UN_INIT = 4000;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubFaceShangTangDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_sub_face_shang_tang_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis.SubFaceShangTangDiagnosis.<init>():void");
        }
    }

    public static class SubFaceCameraDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_CAMERA_NOT_FOUND = 4005;
        public static final int CODE_INIT_FAIL = 4004;
        private static int CODE_UN_INIT = 4100;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubFaceCameraDiagnosis() {
            /*
                r2 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_sub_face_camera_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                int r1 = CODE_UN_INIT
                r2.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis.SubFaceCameraDiagnosis.<init>():void");
        }
    }
}
