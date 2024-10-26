package com.example.sroslibrary.monitor;

import com.ciot.base.config.CommonConfig;
import com.ciot.base.util.MyLogUtils;
import com.example.sroslibrary.bean.ProtocolBean;

public class VideoProcessHandler {
    private VideoProcessCallback videoProcessCallback;

    public void setVideoProcessCallback(VideoProcessCallback videoProcessCallback2) {
        this.videoProcessCallback = videoProcessCallback2;
    }

    public static VideoProcessHandler getInstance() {
        return VideoProcessHandlerHolder.instance;
    }

    private VideoProcessHandler() {
    }

    private static class VideoProcessHandlerHolder {
        public static VideoProcessHandler instance = new VideoProcessHandler();

        private VideoProcessHandlerHolder() {
        }
    }

    public void requestVideo(ProtocolBean protocolBean) {
        short cmd = protocolBean.getCmd();
        if (!(cmd == 2064 || cmd == 2065)) {
            switch (cmd) {
                case -30207:
                case -30206:
                case -30205:
                case -30204:
                case -30203:
                    break;
                default:
                    switch (cmd) {
                        case -30201:
                        case -30200:
                        case -30199:
                            break;
                    }
            }
        }
        MyLogUtils.Logd(CommonConfig.VIDEO_MONITOR_TAG, "SrosHandlerCallback NotificationBean:" + protocolBean.toString());
        if (this.videoProcessCallback != null) {
            short cmd2 = protocolBean.getCmd();
            if (cmd2 != 2065) {
                switch (cmd2) {
                    case -30207:
                        this.videoProcessCallback.processStartLive(protocolBean);
                        return;
                    case -30206:
                        this.videoProcessCallback.processStopVideo(protocolBean);
                        return;
                    case -30205:
                        this.videoProcessCallback.processQueryPlayback(protocolBean);
                        return;
                    case -30204:
                        this.videoProcessCallback.processStartPlayback(protocolBean);
                        return;
                    case -30203:
                        this.videoProcessCallback.processStopPlayback(protocolBean);
                        return;
                    default:
                        switch (cmd2) {
                            case -30201:
                                this.videoProcessCallback.processControlPlayback(protocolBean);
                                return;
                            case -30200:
                                this.videoProcessCallback.processStartPhoneCall(protocolBean);
                                return;
                            case -30199:
                                this.videoProcessCallback.processStopPhoneCall(protocolBean);
                                return;
                            default:
                                return;
                        }
                }
            } else {
                this.videoProcessCallback.processLocalStopPhoneCall(protocolBean);
            }
        }
    }
}
