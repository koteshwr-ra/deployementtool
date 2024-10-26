package mc.csst.com.selfchassis.utils.bean;

public class TopMenuBean {
    private int iconBg;
    private boolean isHide;
    private int labelId;
    private String labelText = "";

    public interface TopMenuType {
        public static final int TYPE_CONTINUE_SCAN_MAP = 1;
        public static final int TYPE_CREATE_MAP = 0;
    }

    public TopMenuBean(int i, String str, int i2, boolean z) {
        this.labelId = i;
        this.labelText = str;
        this.iconBg = i2;
        this.isHide = z;
    }

    public TopMenuBean(int i, String str, int i2) {
        this.labelId = i;
        this.labelText = str;
        this.iconBg = i2;
        this.isHide = false;
    }

    public int getLabelId() {
        return this.labelId;
    }

    public void setLabelId(int i) {
        this.labelId = i;
    }

    public String getLabelText() {
        return this.labelText;
    }

    public void setLabelText(String str) {
        this.labelText = str;
    }

    public int getIconBg() {
        return this.iconBg;
    }

    public void setIconBg(int i) {
        this.iconBg = i;
    }

    public boolean isHide() {
        return this.isHide;
    }

    public void setHide(boolean z) {
        this.isHide = z;
    }

    public String toString() {
        return "TopMenuBean{labelId=" + this.labelId + ", labelText='" + this.labelText + '\'' + ", iconBg=" + this.iconBg + ", isHide=" + this.isHide + '}';
    }
}
