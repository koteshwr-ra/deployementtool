package com.noober.background.common;

public enum MultiSelector {
    State_Checkable("state_checkable", 16842911),
    State_Checked("state_checked", 16842912),
    State_Enabled("state_enabled", 16842910),
    State_Selected("state_selected", 16842913),
    State_Pressed("state_pressed", 16842919),
    State_Focused("state_focused", 16842908),
    State_Hovered("state_hovered", 16843623),
    State_Activated("state_activated", 16843518);
    
    public int id;
    public String value;

    private MultiSelector(String str, int i) {
        this.value = str;
        this.id = i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.noober.background.common.MultiSelector getMultiAttr(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1722420599: goto L_0x004e;
                case -1616325175: goto L_0x0044;
                case -1243548044: goto L_0x003a;
                case -182969863: goto L_0x0030;
                case 64931747: goto L_0x0026;
                case 175751469: goto L_0x001c;
                case 259503156: goto L_0x0012;
                case 1760089491: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0058
        L_0x0008:
            java.lang.String r0 = "state_enabled"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 2
            goto L_0x0059
        L_0x0012:
            java.lang.String r0 = "state_checkable"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 0
            goto L_0x0059
        L_0x001c:
            java.lang.String r0 = "state_hovered"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 6
            goto L_0x0059
        L_0x0026:
            java.lang.String r0 = "state_activated"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 7
            goto L_0x0059
        L_0x0030:
            java.lang.String r0 = "state_checked"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 1
            goto L_0x0059
        L_0x003a:
            java.lang.String r0 = "state_pressed"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 4
            goto L_0x0059
        L_0x0044:
            java.lang.String r0 = "state_focused"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 5
            goto L_0x0059
        L_0x004e:
            java.lang.String r0 = "state_selected"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0058
            r1 = 3
            goto L_0x0059
        L_0x0058:
            r1 = -1
        L_0x0059:
            switch(r1) {
                case 0: goto L_0x0073;
                case 1: goto L_0x0070;
                case 2: goto L_0x006d;
                case 3: goto L_0x006a;
                case 4: goto L_0x0067;
                case 5: goto L_0x0064;
                case 6: goto L_0x0061;
                case 7: goto L_0x005e;
                default: goto L_0x005c;
            }
        L_0x005c:
            r1 = 0
            return r1
        L_0x005e:
            com.noober.background.common.MultiSelector r1 = State_Activated
            return r1
        L_0x0061:
            com.noober.background.common.MultiSelector r1 = State_Hovered
            return r1
        L_0x0064:
            com.noober.background.common.MultiSelector r1 = State_Focused
            return r1
        L_0x0067:
            com.noober.background.common.MultiSelector r1 = State_Pressed
            return r1
        L_0x006a:
            com.noober.background.common.MultiSelector r1 = State_Selected
            return r1
        L_0x006d:
            com.noober.background.common.MultiSelector r1 = State_Enabled
            return r1
        L_0x0070:
            com.noober.background.common.MultiSelector r1 = State_Checked
            return r1
        L_0x0073:
            com.noober.background.common.MultiSelector r1 = State_Checkable
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.common.MultiSelector.getMultiAttr(java.lang.String):com.noober.background.common.MultiSelector");
    }
}
