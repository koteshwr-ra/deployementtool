package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;

public class WaterChassisDiagnosis extends BaseGroupDiagnosis {
    public static String CAN_MODULE = "CAN模块";
    public static String CHARGING_SIGNAL = "充电桩红外发射信号";
    public static String DEPTH_CAMERA = "深度摄像头";
    public static String INERTIAL_MEASUREMENT_UNIT = "惯性测量单元";
    public static String INTERNET = "互联网";
    public static String LASER_SENSOR = "激光传感器";
    public static String MOTOR_BOARD = "电机板";
    public static String NAME = null;
    public static String ODOMETER = "里程记";
    public static String PERIPHERAL_BOARD = "外设板";
    public static String POWER_BOARD = "电源板";
    public static String ROUTER = "路由器";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WaterChassisDiagnosis() {
        /*
            r5 = this;
            int r0 = com.ciot.diagnosis.R.string.diagnosis_chassis_name
            java.lang.String r0 = getString(r0)
            NAME = r0
            r5.<init>(r0)
            java.util.List r0 = r5.subDiagnosisList
            com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis r1 = new com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis
            int r2 = com.ciot.diagnosis.R.string.diagnosis_sub_chassis_name
            java.lang.String r2 = getString(r2)
            int r3 = com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisDiagnosis.CODE_UN_INIT_CHASSIS
            r1.<init>(r2, r3)
            r0.add(r1)
            java.util.List r0 = r5.subDiagnosisList
            com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis r1 = new com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis
            java.lang.String r2 = ROUTER
            int r3 = com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisDiagnosis.CODE_UN_INIT_ROUTER
            r4 = 0
            r1.<init>(r2, r4, r3)
            r0.add(r1)
            java.util.List r0 = r5.subDiagnosisList
            com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis r1 = new com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis
            java.lang.String r2 = LASER_SENSOR
            int r3 = com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisDiagnosis.CODE_UN_INIT_LASER
            r1.<init>(r2, r4, r3)
            r0.add(r1)
            java.util.List r0 = r5.subDiagnosisList
            com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis r1 = new com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisDiagnosis
            java.lang.String r2 = CHARGING_SIGNAL
            int r3 = com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisDiagnosis.CODE_UN_INIT_INFRARED
            r4 = 1
            r1.<init>(r2, r4, r3)
            r0.add(r1)
            com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisMapDiagnosis r0 = new com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis$SubChassisMapDiagnosis
            int r1 = com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisDiagnosis.CODE_UN_INIT_MAP
            r0.<init>(r1)
            r0.setDefaultStatus(r4)
            java.util.List r1 = r5.subDiagnosisList
            r1.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.<init>():void");
    }

    public SubChassisDiagnosis getSubChassisDiagnosis() {
        return (SubChassisDiagnosis) DiagnosisUtil.getDiagnosis(SubChassisDiagnosis.NAME, this.subDiagnosisList);
    }

    public SubChassisMapDiagnosis getSubChassisMapDiagnosis() {
        return (SubChassisMapDiagnosis) DiagnosisUtil.getDiagnosis(SubChassisMapDiagnosis.NAME, this.subDiagnosisList);
    }

    public SubChassisDiagnosis getSubChassisDiagnosis(String str) {
        return (SubChassisDiagnosis) DiagnosisUtil.getDiagnosis(str, this.subDiagnosisList);
    }

    public static class SubChassisDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_CONNECT_FAIL = 3002;
        public static final int CODE_INIT_FAIL = 3001;
        /* access modifiers changed from: private */
        public static int CODE_UN_INIT_CHASSIS = 3901;
        /* access modifiers changed from: private */
        public static int CODE_UN_INIT_INFRARED = 3904;
        /* access modifiers changed from: private */
        public static int CODE_UN_INIT_LASER = 3903;
        /* access modifiers changed from: private */
        public static int CODE_UN_INIT_MAP = 3905;
        /* access modifiers changed from: private */
        public static int CODE_UN_INIT_ROUTER = 3902;
        public static String NAME;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SubChassisDiagnosis(String str, int i) {
            super(str, i);
            NAME = str;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SubChassisDiagnosis(String str, boolean z, int i) {
            super(str, i);
            NAME = str;
            setDefaultStatus(z);
        }
    }

    public static class SubChassisMapDiagnosis extends BaseDiagnosis {
        public static final int CODE_MAP_MAYBE_LOST = 3003;
        public static String NAME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SubChassisMapDiagnosis(int r2) {
            /*
                r1 = this;
                int r0 = com.ciot.diagnosis.R.string.diagnosis_sub_chassis_map_name
                java.lang.String r0 = getString(r0)
                NAME = r0
                r1.<init>(r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis.SubChassisMapDiagnosis.<init>(int):void");
        }
    }
}
