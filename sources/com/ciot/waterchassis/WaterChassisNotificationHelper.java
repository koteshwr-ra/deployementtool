package com.ciot.waterchassis;

public class WaterChassisNotificationHelper {
    private static final String TAG = WaterChassisNotificationHelper.class.getSimpleName();
    private static WaterChassisNotificationHelper sWaterChassisNotificationHelper;

    public static WaterChassisNotificationHelper getInstance() {
        if (sWaterChassisNotificationHelper == null) {
            synchronized (WaterChassisNotificationHelper.class) {
                if (sWaterChassisNotificationHelper == null) {
                    sWaterChassisNotificationHelper = new WaterChassisNotificationHelper();
                }
            }
        }
        return sWaterChassisNotificationHelper;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int notificationUser(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1
            switch(r0) {
                case 45836434: goto L_0x0126;
                case 45836437: goto L_0x011c;
                case 45836494: goto L_0x0111;
                case 45836495: goto L_0x0107;
                case 45837395: goto L_0x00fd;
                case 45925804: goto L_0x00f2;
                case 45925805: goto L_0x00e7;
                case 45925806: goto L_0x00dd;
                case 45925835: goto L_0x00d2;
                case 45925836: goto L_0x00c7;
                case 45925838: goto L_0x00bc;
                case 45925867: goto L_0x00b0;
                case 45925869: goto L_0x00a5;
                case 45925897: goto L_0x009a;
                case 45925898: goto L_0x008e;
                case 45925899: goto L_0x0082;
                case 45925900: goto L_0x0076;
                case 45925928: goto L_0x006a;
                case 45925959: goto L_0x005e;
                case 45925960: goto L_0x0052;
                case 45925961: goto L_0x0046;
                case 45925990: goto L_0x003a;
                case 45925991: goto L_0x002e;
                case 45925992: goto L_0x0022;
                case 45926021: goto L_0x0016;
                case 45926022: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0130
        L_0x000a:
            java.lang.String r0 = "04071"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 25
            goto L_0x0131
        L_0x0016:
            java.lang.String r0 = "04070"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 24
            goto L_0x0131
        L_0x0022:
            java.lang.String r0 = "04062"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 22
            goto L_0x0131
        L_0x002e:
            java.lang.String r0 = "04061"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 21
            goto L_0x0131
        L_0x003a:
            java.lang.String r0 = "04060"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 20
            goto L_0x0131
        L_0x0046:
            java.lang.String r0 = "04052"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 11
            goto L_0x0131
        L_0x0052:
            java.lang.String r0 = "04051"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 19
            goto L_0x0131
        L_0x005e:
            java.lang.String r0 = "04050"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 18
            goto L_0x0131
        L_0x006a:
            java.lang.String r0 = "04040"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 10
            goto L_0x0131
        L_0x0076:
            java.lang.String r0 = "04033"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 9
            goto L_0x0131
        L_0x0082:
            java.lang.String r0 = "04032"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 8
            goto L_0x0131
        L_0x008e:
            java.lang.String r0 = "04031"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 17
            goto L_0x0131
        L_0x009a:
            java.lang.String r0 = "04030"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 7
            goto L_0x0131
        L_0x00a5:
            java.lang.String r0 = "04023"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 6
            goto L_0x0131
        L_0x00b0:
            java.lang.String r0 = "04021"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 16
            goto L_0x0131
        L_0x00bc:
            java.lang.String r0 = "04013"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 5
            goto L_0x0131
        L_0x00c7:
            java.lang.String r0 = "04011"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 15
            goto L_0x0131
        L_0x00d2:
            java.lang.String r0 = "04010"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 14
            goto L_0x0131
        L_0x00dd:
            java.lang.String r0 = "04002"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 4
            goto L_0x0131
        L_0x00e7:
            java.lang.String r0 = "04001"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 13
            goto L_0x0131
        L_0x00f2:
            java.lang.String r0 = "04000"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 12
            goto L_0x0131
        L_0x00fd:
            java.lang.String r0 = "01103"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 3
            goto L_0x0131
        L_0x0107:
            java.lang.String r0 = "01022"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 2
            goto L_0x0131
        L_0x0111:
            java.lang.String r0 = "01021"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 23
            goto L_0x0131
        L_0x011c:
            java.lang.String r0 = "01006"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 1
            goto L_0x0131
        L_0x0126:
            java.lang.String r0 = "01003"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0130
            r0 = 0
            goto L_0x0131
        L_0x0130:
            r0 = -1
        L_0x0131:
            switch(r0) {
                case 0: goto L_0x0184;
                case 1: goto L_0x0181;
                case 2: goto L_0x017e;
                case 3: goto L_0x017b;
                case 4: goto L_0x0178;
                case 5: goto L_0x0175;
                case 6: goto L_0x0172;
                case 7: goto L_0x0187;
                case 8: goto L_0x016f;
                case 9: goto L_0x016c;
                case 10: goto L_0x0169;
                case 11: goto L_0x0166;
                case 12: goto L_0x0187;
                case 13: goto L_0x0187;
                case 14: goto L_0x0163;
                case 15: goto L_0x0160;
                case 16: goto L_0x0187;
                case 17: goto L_0x0187;
                case 18: goto L_0x015d;
                case 19: goto L_0x0187;
                case 20: goto L_0x015a;
                case 21: goto L_0x0157;
                case 22: goto L_0x0154;
                case 23: goto L_0x0151;
                case 24: goto L_0x014e;
                case 25: goto L_0x014b;
                default: goto L_0x0134;
            }
        L_0x0134:
            java.lang.String r0 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "notification: "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            android.util.Log.w(r0, r5)
            goto L_0x0187
        L_0x014b:
            int r5 = com.ciot.waterchassis.R.string.water_code_end_waiting_for_the_lift_to_unlock
            return r5
        L_0x014e:
            int r5 = com.ciot.waterchassis.R.string.water_code_start_waiting_for_the_lift_to_unlock
            return r5
        L_0x0151:
            int r5 = com.ciot.waterchassis.R.string.water_code_dock_charging_pile_success
            return r5
        L_0x0154:
            int r5 = com.ciot.waterchassis.R.string.water_code_back_lift_fail
            return r5
        L_0x0157:
            int r5 = com.ciot.waterchassis.R.string.water_code_back_lift_success
            return r5
        L_0x015a:
            int r5 = com.ciot.waterchassis.R.string.water_code_start_back_lift
            return r5
        L_0x015d:
            int r5 = com.ciot.waterchassis.R.string.water_code_start_exit_lift
            return r5
        L_0x0160:
            int r5 = com.ciot.waterchassis.R.string.water_code_call_lift_success
            return r5
        L_0x0163:
            int r5 = com.ciot.waterchassis.R.string.water_code_start_call_lift
            return r5
        L_0x0166:
            int r5 = com.ciot.waterchassis.R.string.water_code_exit_lift_fail
            return r5
        L_0x0169:
            int r5 = com.ciot.waterchassis.R.string.water_code_avoid_lift
            return r5
        L_0x016c:
            int r5 = com.ciot.waterchassis.R.string.water_code_not_enough_space
            return r5
        L_0x016f:
            int r5 = com.ciot.waterchassis.R.string.water_code_fail_enter_lift
            return r5
        L_0x0172:
            int r5 = com.ciot.waterchassis.R.string.water_code_task_lift_more_than_tree_minutes
            return r5
        L_0x0175:
            int r5 = com.ciot.waterchassis.R.string.water_code_call_lift_more_than_tree_minutes
            return r5
        L_0x0178:
            int r5 = com.ciot.waterchassis.R.string.water_code_go_to_lift_fail
            return r5
        L_0x017b:
            int r5 = com.ciot.waterchassis.R.string.water_code_cruise_fail
            return r5
        L_0x017e:
            int r5 = com.ciot.waterchassis.R.string.water_code_auto_dock_charging_pile_fail
            return r5
        L_0x0181:
            int r5 = com.ciot.waterchassis.R.string.water_code_move_task_trap
            return r5
        L_0x0184:
            int r5 = com.ciot.waterchassis.R.string.water_code_move_task_fail
            return r5
        L_0x0187:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.waterchassis.WaterChassisNotificationHelper.notificationUser(java.lang.String):int");
    }
}
