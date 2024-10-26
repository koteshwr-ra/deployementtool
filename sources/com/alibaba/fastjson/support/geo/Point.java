package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import io.realm.com_ciot_realm_db_patrol_PointRealmProxy;

@JSONType(orders = {"type", "bbox", "coordinates"}, typeName = "Point")
public class Point extends Geometry {
    private double[] coordinates;

    public Point() {
        super(com_ciot_realm_db_patrol_PointRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(double[] dArr) {
        this.coordinates = dArr;
    }
}
