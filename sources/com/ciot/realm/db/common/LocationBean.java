package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_common_LocationBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class LocationBean extends RealmObject implements com_ciot_realm_db_common_LocationBeanRealmProxyInterface {
    private String latitude;
    private String longitude;

    public String realmGet$latitude() {
        return this.latitude;
    }

    public String realmGet$longitude() {
        return this.longitude;
    }

    public void realmSet$latitude(String str) {
        this.latitude = str;
    }

    public void realmSet$longitude(String str) {
        this.longitude = str;
    }

    public LocationBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getLongitude() {
        return realmGet$longitude();
    }

    public void setLongitude(String str) {
        realmSet$longitude(str);
    }

    public String getLatitude() {
        return realmGet$latitude();
    }

    public void setLatitude(String str) {
        realmSet$latitude(str);
    }
}
