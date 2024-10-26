package com.ciot.networklib.bean.patrol;

public class AlarmDataBean {
    private BatteryAlarmBean battery;
    private BlackListAlarmBean blackList;
    private EquipmentAlarmBean equipment;
    private PointAlarmBean point;

    public BlackListAlarmBean getBlackList() {
        return this.blackList;
    }

    public void setBlackList(BlackListAlarmBean blackListAlarmBean) {
        this.blackList = blackListAlarmBean;
    }

    public EquipmentAlarmBean getEquipment() {
        return this.equipment;
    }

    public void setEquipment(EquipmentAlarmBean equipmentAlarmBean) {
        this.equipment = equipmentAlarmBean;
    }

    public BatteryAlarmBean getBattery() {
        return this.battery;
    }

    public void setBattery(BatteryAlarmBean batteryAlarmBean) {
        this.battery = batteryAlarmBean;
    }

    public PointAlarmBean getPoint() {
        return this.point;
    }

    public void setPoint(PointAlarmBean pointAlarmBean) {
        this.point = pointAlarmBean;
    }
}
