package com.limpoxe.support.servicemanager.provider.idcard;

public interface IdcardReceiveListener {
    void onReceiveCardException(String str);

    void onReceiveIdcardInfo(String str, String str2);
}
