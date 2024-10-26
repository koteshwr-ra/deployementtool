package com.example.sroslibrary.monitor;

import com.ciot.tcpclient.ClientIoSessionManager;
import com.example.sroslibrary.bean.ProtocolBean;
import com.example.sroslibrary.monitor.monitor.result.ControlPlaybackResultBean;
import com.example.sroslibrary.monitor.monitor.result.PlaybackQueryResultBean;
import com.example.sroslibrary.monitor.monitor.result.StartPhoneCallResultBean;
import com.example.sroslibrary.monitor.monitor.result.StartVideoResultBean;
import com.example.sroslibrary.monitor.monitor.result.StopPhoneCallResultBean;
import com.example.sroslibrary.monitor.monitor.result.StopVideoResultBean;
import com.example.sroslibrary.sros.SrosSendMsgUtil;
import java.util.List;

public class VideoSendMsgUtil {
    private static VideoSendMsgUtil instance;

    public static VideoSendMsgUtil getInstance() {
        if (instance == null) {
            synchronized (VideoSendMsgUtil.class) {
                if (instance == null) {
                    instance = new VideoSendMsgUtil();
                }
            }
        }
        return instance;
    }

    public void replyStartVideo(boolean z, String str, int i, String str2, int i2, int i3, int i4, int i5) {
        StartVideoResultBean startVideoResultBean = new StartVideoResultBean(i2, str2, i3, i4, i5);
        startVideoResultBean.setResult(z);
        if (!z) {
            startVideoResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30207);
        protocolBean.setBody(startVideoResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyStopLive(boolean z, String str, int i) {
        StopVideoResultBean stopVideoResultBean = new StopVideoResultBean();
        stopVideoResultBean.setResult(z);
        if (!z) {
            stopVideoResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30206);
        protocolBean.setBody(stopVideoResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyStartPlayback(boolean z, String str, int i, String str2, int i2, int i3, int i4, int i5) {
        StartVideoResultBean startVideoResultBean = new StartVideoResultBean(i2, str2, i3, i4, i5);
        startVideoResultBean.setResult(z);
        if (!z) {
            startVideoResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30204);
        protocolBean.setBody(startVideoResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyStopPlayback(boolean z, String str, int i) {
        StopVideoResultBean stopVideoResultBean = new StopVideoResultBean();
        stopVideoResultBean.setResult(z);
        if (!z) {
            stopVideoResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30203);
        protocolBean.setBody(stopVideoResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyControlPlayback(boolean z, String str, int i) {
        ControlPlaybackResultBean controlPlaybackResultBean = new ControlPlaybackResultBean();
        controlPlaybackResultBean.setResult(z);
        if (!z) {
            controlPlaybackResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30201);
        protocolBean.setBody(controlPlaybackResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyQueryPlayback(boolean z, String str, int i, List<PlaybackQueryResultBean.Record> list) {
        PlaybackQueryResultBean playbackQueryResultBean = new PlaybackQueryResultBean(list);
        playbackQueryResultBean.setResult(z);
        if (!z) {
            playbackQueryResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30205);
        protocolBean.setBody(playbackQueryResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyStartPhoneCall(boolean z, String str, int i, String str2, int i2) {
        StartPhoneCallResultBean startPhoneCallResultBean = new StartPhoneCallResultBean(i2, str2);
        startPhoneCallResultBean.setResult(z);
        if (!z) {
            startPhoneCallResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30200);
        protocolBean.setBody(startPhoneCallResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }

    public void replyStopPhoneCall(boolean z, String str, int i) {
        StopPhoneCallResultBean stopPhoneCallResultBean = new StopPhoneCallResultBean();
        stopPhoneCallResultBean.setResult(z);
        if (!z) {
            stopPhoneCallResultBean.setReason(str);
        }
        ProtocolBean protocolBean = SrosSendMsgUtil.getInstance().getProtocolBean();
        protocolBean.setSeq(i);
        protocolBean.setQa((byte) 1);
        protocolBean.setResult(z ^ true ? (short) 1 : 0);
        protocolBean.setCmd(-30199);
        protocolBean.setBody(stopPhoneCallResultBean);
        ClientIoSessionManager.getInstance().sendSrosMsg((Object) protocolBean);
    }
}
