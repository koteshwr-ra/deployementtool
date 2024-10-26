package com.ciot.diagnosis.diagnosis;

import com.ciot.diagnosis.R;
import com.ciot.diagnosis.base.BaseDiagnosis;
import com.ciot.diagnosis.base.BaseGroupDiagnosis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelfChassisDiagnosis extends BaseGroupDiagnosis {
    public static String BATTERY = getString(R.string.diagnosis_buttery);
    public static String CHASSIS_CONNECT = getString(R.string.diagnosis_connect_chassis);
    public static String DEPTH_CAMERA = getString(R.string.diagnosis_deep_camera);
    public static String GYRO = getString(R.string.diagnosis_gyroscope);
    public static String INTERNET = getString(R.string.diagnosis_network_connect);
    public static String LASER_SENSOR = getString(R.string.diagnosis_laser_radar);
    public static String MAP = getString(R.string.diagnosis_sub_chassis_map_name);
    public static String MOTOR_LEFT = getString(R.string.diagnosis_tv_left);
    public static String MOTOR_RIGHT = getString(R.string.diagnosis_tv_right);
    public static String NAME;
    public static String OTHER_ERROR = getString(R.string.diagnosis_other_system_error);
    public static String PERIPHERALS = getString(R.string.diagnosis_peripheral);
    public static String SYSTEM_CURRENT = getString(R.string.diagnosis_system_current);
    public static String TAG_CAMERA = getString(R.string.diagnosis_tagged_camera);
    public static String ULTRASOUND = getString(R.string.diagnosis_ultrasonic);
    private HashMap<String, BaseDiagnosis> diagnosisHashMap = new HashMap<>();
    private List<String> subModuleNameList = new ArrayList();
    private HashMap<String, Integer> unInitCodeMap = new HashMap<>();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SelfChassisDiagnosis() {
        /*
            r5 = this;
            int r0 = com.ciot.diagnosis.R.string.diagnosis_chassis_name
            java.lang.String r0 = getString(r0)
            NAME = r0
            r5.<init>(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r5.subModuleNameList = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5.diagnosisHashMap = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r5.unInitCodeMap = r0
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = CHASSIS_CONNECT
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = LASER_SENSOR
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = INTERNET
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = MOTOR_LEFT
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = MOTOR_RIGHT
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = ULTRASOUND
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = GYRO
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = BATTERY
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = SYSTEM_CURRENT
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = PERIPHERALS
            r0.add(r1)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.lang.String r1 = OTHER_ERROR
            r0.add(r1)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = CHASSIS_CONNECT
            r2 = 3900(0xf3c, float:5.465E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = LASER_SENSOR
            r2 = 3901(0xf3d, float:5.466E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = INTERNET
            r2 = 3902(0xf3e, float:5.468E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = MOTOR_LEFT
            r2 = 3903(0xf3f, float:5.469E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = MOTOR_RIGHT
            r2 = 3904(0xf40, float:5.47E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = ULTRASOUND
            r2 = 3905(0xf41, float:5.472E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = GYRO
            r2 = 3906(0xf42, float:5.473E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = BATTERY
            r2 = 3907(0xf43, float:5.475E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = SYSTEM_CURRENT
            r2 = 3908(0xf44, float:5.476E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = PERIPHERALS
            r2 = 3909(0xf45, float:5.478E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.unInitCodeMap
            java.lang.String r1 = OTHER_ERROR
            r2 = 3910(0xf46, float:5.479E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            java.util.List<java.lang.String> r0 = r5.subModuleNameList
            java.util.Iterator r0 = r0.iterator()
        L_0x0102:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0125
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.HashMap<java.lang.String, com.ciot.diagnosis.base.BaseDiagnosis> r2 = r5.diagnosisHashMap
            com.ciot.diagnosis.diagnosis.SelfChassisDiagnosis$SubChassisDiagnosis r3 = new com.ciot.diagnosis.diagnosis.SelfChassisDiagnosis$SubChassisDiagnosis
            java.util.HashMap<java.lang.String, java.lang.Integer> r4 = r5.unInitCodeMap
            java.lang.Object r4 = r4.get(r1)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3.<init>(r1, r4)
            r2.put(r1, r3)
            goto L_0x0102
        L_0x0125:
            java.util.HashMap<java.lang.String, com.ciot.diagnosis.base.BaseDiagnosis> r0 = r5.diagnosisHashMap
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x012f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0147
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.util.List r2 = r5.subDiagnosisList
            java.lang.Object r1 = r1.getValue()
            com.ciot.diagnosis.base.BaseDiagnosis r1 = (com.ciot.diagnosis.base.BaseDiagnosis) r1
            r2.add(r1)
            goto L_0x012f
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.diagnosis.diagnosis.SelfChassisDiagnosis.<init>():void");
    }

    public BaseDiagnosis getDiagnosis(String str) {
        return this.diagnosisHashMap.get(str);
    }

    public static class SubChassisDiagnosis extends BaseDiagnosis {
        public static final int CODE_ERROR_CONNECT_FAIL = 3002;
        public static final int CODE_INIT_FAIL = 3001;
        public static String NAME;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SubChassisDiagnosis(String str, int i) {
            super(str, i);
            NAME = str;
        }
    }
}
