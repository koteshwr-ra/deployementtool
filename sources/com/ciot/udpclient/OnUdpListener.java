package com.ciot.udpclient;

public interface OnUdpListener {
    void onConnectUdp();

    void onDisConnectUdp();

    void onReceiveMsg(byte[] bArr);
}
