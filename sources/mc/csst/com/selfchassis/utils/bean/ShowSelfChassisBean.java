package mc.csst.com.selfchassis.utils.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.ciot.sentrymove.R;

public class ShowSelfChassisBean extends BaseObservable {
    private static volatile ShowSelfChassisBean mInstance;
    private int battery;
    private int batteryIconRes = R.mipmap.ic_battery_10;
    private String batteryPercent = "";
    private boolean bottomLock = false;
    private String bottomTips = "";
    private int bottomTipsColor = R.color.black;
    private String build = "";
    private String deviceState = "";
    private boolean exitBtnCanClick = true;
    private String floor = "";
    private float matching;
    private String matchingPercent = "";
    private int onDock;
    private int onDockIconRes = R.mipmap.icon_off_dock;
    private int showBottomTips = 8;
    private boolean softStop = false;
    private String sportState = "";
    private String t = "";
    private String x = "";
    private String y = "";

    private ShowSelfChassisBean() {
    }

    public static ShowSelfChassisBean getInstance() {
        if (mInstance == null) {
            synchronized (ShowSelfChassisBean.class) {
                if (mInstance == null) {
                    mInstance = new ShowSelfChassisBean();
                }
            }
        }
        return mInstance;
    }

    @Bindable
    public String getBuild() {
        return this.build;
    }

    public void setBuild(String str) {
        this.build = str;
        notifyPropertyChanged(8);
    }

    @Bindable
    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String str) {
        this.floor = str;
        notifyPropertyChanged(14);
    }

    @Bindable
    public String getX() {
        return this.x;
    }

    public void setX(String str) {
        this.x = str;
        notifyPropertyChanged(26);
    }

    @Bindable
    public String getY() {
        return this.y;
    }

    public void setY(String str) {
        this.y = str;
        notifyPropertyChanged(27);
    }

    @Bindable
    public String getT() {
        return this.t;
    }

    public void setT(String str) {
        this.t = str;
        notifyPropertyChanged(24);
    }

    @Bindable
    public String getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceState(String str) {
        this.deviceState = str;
        notifyPropertyChanged(12);
    }

    @Bindable
    public String getSportState() {
        return this.sportState;
    }

    public void setSportState(String str) {
        this.sportState = str;
        notifyPropertyChanged(23);
    }

    @Bindable
    public String getMatchingPercent() {
        return this.matchingPercent;
    }

    public void setMatchingPercent(String str) {
        this.matchingPercent = str;
        notifyPropertyChanged(16);
    }

    @Bindable
    public String getBatteryPercent() {
        return this.batteryPercent;
    }

    public void setBatteryPercent(String str) {
        this.batteryPercent = str;
        notifyPropertyChanged(4);
    }

    @Bindable
    public float getMatching() {
        return this.matching;
    }

    public void setMatching(float f) {
        this.matching = f;
        notifyPropertyChanged(15);
    }

    @Bindable
    public int getBattery() {
        return this.battery;
    }

    public void setBattery(int i) {
        this.battery = i;
        notifyPropertyChanged(2);
    }

    @Bindable
    public boolean isSoftStop() {
        return this.softStop;
    }

    public void setSoftStop(boolean z) {
        this.softStop = z;
        notifyPropertyChanged(22);
    }

    @Bindable
    public int getBatteryIconRes() {
        return this.batteryIconRes;
    }

    public void setBatteryIconRes(int i) {
        this.batteryIconRes = i;
        notifyPropertyChanged(3);
    }

    @Bindable
    public int getOnDockIconRes() {
        return this.onDockIconRes;
    }

    public void setOnDockIconRes(int i) {
        this.onDockIconRes = i;
        notifyPropertyChanged(18);
    }

    @Bindable
    public boolean isBottomLock() {
        return this.bottomLock;
    }

    public void setBottomLock(boolean z) {
        this.bottomLock = z;
        notifyPropertyChanged(5);
    }

    @Bindable
    public String getBottomTips() {
        return this.bottomTips;
    }

    public void setBottomTips(String str) {
        this.bottomTips = str;
        notifyPropertyChanged(6);
    }

    @Bindable
    public int getOnDock() {
        return this.onDock;
    }

    public void setOnDock(int i) {
        this.onDock = i;
        notifyPropertyChanged(17);
    }

    @Bindable
    public int getBottomTipsColor() {
        return this.bottomTipsColor;
    }

    public void setBottomTipsColor(int i) {
        this.bottomTipsColor = i;
        notifyPropertyChanged(7);
    }

    @Bindable
    public int getShowBottomTips() {
        return this.showBottomTips;
    }

    public void setShowBottomTips(int i) {
        this.showBottomTips = i;
        notifyPropertyChanged(20);
    }

    @Bindable
    public boolean isExitBtnCanClick() {
        return this.exitBtnCanClick;
    }

    public void setExitBtnCanClick(boolean z) {
        this.exitBtnCanClick = z;
        notifyPropertyChanged(13);
    }

    public String toString() {
        return "ShowSelfChassisBean{build='" + this.build + '\'' + ", floor='" + this.floor + '\'' + ", x='" + this.x + '\'' + ", y='" + this.y + '\'' + ", t='" + this.t + '\'' + ", deviceState='" + this.deviceState + '\'' + ", sportState='" + this.sportState + '\'' + ", matching=" + this.matching + ", matchingPercent='" + this.matchingPercent + '\'' + ", battery=" + this.battery + ", batteryPercent='" + this.batteryPercent + '\'' + ", softStop=" + this.softStop + ", batteryIconRes=" + this.batteryIconRes + ", onDock=" + this.onDock + ", onDockIconRes=" + this.onDockIconRes + ", bottomLock=" + this.bottomLock + ", bottomTips='" + this.bottomTips + '\'' + ", bottomTipsColor=" + this.bottomTipsColor + ", showBottomTips=" + this.showBottomTips + ", exitBtnCanClick=" + this.exitBtnCanClick + '}';
    }
}
