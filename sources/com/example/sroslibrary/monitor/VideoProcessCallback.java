package com.example.sroslibrary.monitor;

import com.example.sroslibrary.bean.ProtocolBean;

public interface VideoProcessCallback {
    void processControlPlayback(ProtocolBean protocolBean);

    void processLocalStopPhoneCall(ProtocolBean protocolBean);

    void processQueryPlayback(ProtocolBean protocolBean);

    void processStartLive(ProtocolBean protocolBean);

    void processStartPhoneCall(ProtocolBean protocolBean);

    void processStartPlayback(ProtocolBean protocolBean);

    void processStopPhoneCall(ProtocolBean protocolBean);

    void processStopPlayback(ProtocolBean protocolBean);

    void processStopVideo(ProtocolBean protocolBean);
}
