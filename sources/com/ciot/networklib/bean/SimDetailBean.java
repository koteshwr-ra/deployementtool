package com.ciot.networklib.bean;

public class SimDetailBean {
    private int activeDuration;
    private int cardPoolId;
    private String carrier;
    private double dataUsage;
    private String deviceStatus;
    private String expireDate;
    private String iccid;
    private String imeiStatus;
    private String imsi;
    private String iratePlanName;
    private String lastSyncDate;
    private String msisdn;
    private boolean nbCard;
    private String openDate;
    private String orgName;
    private String ratePlanEffetiveDate;
    private String ratePlanExpirationDate;
    private int ratePlanId;
    private String realNameCertifystatus;
    private boolean realnameRequired;
    private int speedLimit;
    private String startDate;
    private String status;
    private String testingExpireDate;
    private int totalDataVolume;
    private String type;
    private boolean useCountAsVolume;
    private double usedDataVolume;

    public String getRatePlanExpirationDate() {
        return this.ratePlanExpirationDate;
    }

    public void setRatePlanExpirationDate(String str) {
        this.ratePlanExpirationDate = str;
    }

    public int getActiveDuration() {
        return this.activeDuration;
    }

    public void setActiveDuration(int i) {
        this.activeDuration = i;
    }

    public int getRatePlanId() {
        return this.ratePlanId;
    }

    public void setRatePlanId(int i) {
        this.ratePlanId = i;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getTotalDataVolume() {
        return this.totalDataVolume;
    }

    public void setTotalDataVolume(int i) {
        this.totalDataVolume = i;
    }

    public String getDeviceStatus() {
        return this.deviceStatus;
    }

    public void setDeviceStatus(String str) {
        this.deviceStatus = str;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public boolean isUseCountAsVolume() {
        return this.useCountAsVolume;
    }

    public void setUseCountAsVolume(boolean z) {
        this.useCountAsVolume = z;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(String str) {
        this.expireDate = str;
    }

    public int getCardPoolId() {
        return this.cardPoolId;
    }

    public void setCardPoolId(int i) {
        this.cardPoolId = i;
    }

    public String getRatePlanEffetiveDate() {
        return this.ratePlanEffetiveDate;
    }

    public void setRatePlanEffetiveDate(String str) {
        this.ratePlanEffetiveDate = str;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String str) {
        this.msisdn = str;
    }

    public double getDataUsage() {
        return this.dataUsage;
    }

    public void setDataUsage(double d) {
        this.dataUsage = d;
    }

    public boolean isNbCard() {
        return this.nbCard;
    }

    public void setNbCard(boolean z) {
        this.nbCard = z;
    }

    public String getImeiStatus() {
        return this.imeiStatus;
    }

    public void setImeiStatus(String str) {
        this.imeiStatus = str;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String str) {
        this.orgName = str;
    }

    public String getTestingExpireDate() {
        return this.testingExpireDate;
    }

    public void setTestingExpireDate(String str) {
        this.testingExpireDate = str;
    }

    public double getUsedDataVolume() {
        return this.usedDataVolume;
    }

    public void setUsedDataVolume(double d) {
        this.usedDataVolume = d;
    }

    public String getLastSyncDate() {
        return this.lastSyncDate;
    }

    public void setLastSyncDate(String str) {
        this.lastSyncDate = str;
    }

    public boolean isRealnameRequired() {
        return this.realnameRequired;
    }

    public void setRealnameRequired(boolean z) {
        this.realnameRequired = z;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public void setSpeedLimit(int i) {
        this.speedLimit = i;
    }

    public String getRealNameCertifystatus() {
        return this.realNameCertifystatus;
    }

    public void setRealNameCertifystatus(String str) {
        this.realNameCertifystatus = str;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public void setCarrier(String str) {
        this.carrier = str;
    }

    public String getOpenDate() {
        return this.openDate;
    }

    public void setOpenDate(String str) {
        this.openDate = str;
    }

    public String getIratePlanName() {
        return this.iratePlanName;
    }

    public void setIratePlanName(String str) {
        this.iratePlanName = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
