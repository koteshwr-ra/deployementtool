package com.limpoxe.support.servicemanager;

import android.os.Binder;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class ProcessBinder extends Binder {
    public static final int FIRST_CODE = 1;
    private final String DESCRIPTOR;

    public ProcessBinder(String str) {
        this.DESCRIPTOR = str;
        attachInterface((IInterface) null, str);
    }

    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface(this.DESCRIPTOR);
            Bundle routerToInstance = MethodRouter.routerToInstance(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            if (routerToInstance != null) {
                parcel2.writeInt(1);
                routerToInstance.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString(this.DESCRIPTOR);
            return true;
        }
    }
}
