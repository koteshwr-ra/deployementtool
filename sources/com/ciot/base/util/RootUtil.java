package com.ciot.base.util;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class RootUtil {
    public static void initVoiceAndCamera() {
        execShell("chmod 777 /dev/snd/pcm*");
        execShell("chmod 666 /dev/i2c-9");
    }

    public static void execShell(String str) {
        try {
            OutputStream outputStream = Runtime.getRuntime().exec("su").getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(str);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
            ToastUtil.showText2(ContextUtil.getContext(), "修改语音系统权限成功");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                ToastUtil.showText2(ContextUtil.getContext(), "修改语音系统权限失败");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
