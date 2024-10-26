package com.example.sroslibrary.bean;

import android.text.TextUtils;
import androidx.core.internal.view.SupportMenu;
import com.blankj.utilcode.util.NetworkUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.networklib.bean.phone.VisitByPhone;
import com.tencent.smtt.sdk.TbsListener;
import org.apache.log4j.spi.Configurator;

public class RobotStatusResponse {
    public String id = AppSpUtil.getInstance().getRobotNumber();
    public String lock = AppSpUtil.getInstance().getRobotLock();
    public String status = "activite";

    public RobotStatusResponse(String str) {
        this.status = str;
    }

    public RobotStatusResponse(String str, String str2) {
        this.lock = str;
        this.status = str2;
    }

    public int getColor() {
        boolean isLock = isLock();
        int i = SupportMenu.CATEGORY_MASK;
        if (!isLock && (TextUtils.equals("activite", this.status) || TextUtils.equals(Configurator.NULL, this.status) || TextUtils.isEmpty(this.status))) {
            i = -1;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public boolean isLock() {
        return TextUtils.equals(VisitByPhone.VERIFIED, this.lock) || TextUtils.equals("lock", this.lock);
    }

    public String getBottomTip() {
        if (VisitByPhone.VERIFIED.equalsIgnoreCase(this.lock) || "lock".equalsIgnoreCase(this.lock)) {
            return "机器功能已被锁定，解锁请联系管理员或致电400-128-9688";
        }
        if (TextUtils.isEmpty(this.status) && "unlock".equalsIgnoreCase(this.lock)) {
            return "机器已解锁,可正常使用";
        }
        if (TextUtils.isEmpty(this.status)) {
            return MySpUtils.getInstance().getString("SP_HOME_TITLE_NAME", "");
        }
        String str = this.status;
        char c = 65535;
        switch (str.hashCode()) {
            case -1655966981:
                if (str.equals("activite")) {
                    c = 3;
                    break;
                }
                break;
            case -1309235419:
                if (str.equals("expired")) {
                    c = 2;
                    break;
                }
                break;
            case -65094476:
                if (str.equals("unActivite")) {
                    c = 1;
                    break;
                }
                break;
            case 2089772257:
                if (str.equals("noInUse")) {
                    c = 0;
                    break;
                }
                break;
        }
        return (c == 0 || c == 1) ? NetworkUtils.isConnected() ? "机器尚未接入平台，请联系管理员或致电400-128-9688" : MySpUtils.getInstance().getString("SP_HOME_TITLE_NAME", "") : c != 2 ? MySpUtils.getInstance().getString("SP_HOME_TITLE_NAME", "") : "机器使用授权已到期，如需继续提供服务请致电400-128-9688";
    }

    public String toString() {
        return "RobotStatusResponse{id='" + this.id + '\'' + ", lock='" + this.lock + '\'' + ", status='" + this.status + '\'' + ", color=" + TbsListener.ErrorCode.DOWNLOAD_THROWABLE;
    }
}
