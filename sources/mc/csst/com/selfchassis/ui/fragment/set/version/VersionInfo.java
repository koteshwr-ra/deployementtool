package mc.csst.com.selfchassis.ui.fragment.set.version;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;

public class VersionInfo extends BaseObservable {
    @Bindable
    String algorithmVersion = ContextUtil.getContext().getString(R.string.unknown);
    @Bindable
    String chassisDriveFirmwareVersion = ContextUtil.getContext().getString(R.string.unknown);
    @Bindable
    String chassisHardwareVersion = ContextUtil.getContext().getString(R.string.unknown);
    String chassisType = ContextUtil.getContext().getString(R.string.self_chassis);
    @Bindable
    String curTime = ContextUtil.getContext().getString(R.string.unknown);
    String curVersion = ContextUtil.getContext().getString(R.string.unknown);
    @Bindable
    String robotChassisNumber = ContextUtil.getContext().getString(R.string.unknown);

    public String getCurVersion() {
        return this.curVersion;
    }

    public void setCurVersion(String str) {
        this.curVersion = str;
    }

    public String getAlgorithmVersion() {
        return this.algorithmVersion;
    }

    public void setAlgorithmVersion(String str) {
        this.algorithmVersion = str;
        notifyPropertyChanged(1);
    }

    public String getChassisDriveFirmwareVersion() {
        return this.chassisDriveFirmwareVersion;
    }

    public void setChassisDriveFirmwareVersion(String str) {
        this.chassisDriveFirmwareVersion = str;
        notifyPropertyChanged(9);
    }

    public String getCurTime() {
        return this.curTime;
    }

    public void setCurTime(String str) {
        this.curTime = str;
        notifyPropertyChanged(11);
    }

    public String getChassisHardwareVersion() {
        return this.chassisHardwareVersion;
    }

    public void setChassisHardwareVersion(String str) {
        this.chassisHardwareVersion = str;
        notifyPropertyChanged(10);
    }

    public String getRobotChassisNumber() {
        return this.robotChassisNumber;
    }

    public void setRobotChassisNumber(String str) {
        this.robotChassisNumber = str;
        notifyPropertyChanged(19);
    }

    public String getChassisType() {
        return this.chassisType;
    }

    public void setChassisType(String str) {
        this.chassisType = str;
    }
}
