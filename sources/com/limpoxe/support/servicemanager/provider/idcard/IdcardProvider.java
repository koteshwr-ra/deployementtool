package com.limpoxe.support.servicemanager.provider.idcard;

import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IdcardProvider extends IProvider {
    void start(Context context, IdcardReceiveListener idcardReceiveListener);

    void stop();
}
