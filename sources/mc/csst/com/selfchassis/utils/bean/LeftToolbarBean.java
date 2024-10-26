package mc.csst.com.selfchassis.utils.bean;

public class LeftToolbarBean {
    private int iconBg;
    private int iconSelectBg;
    private boolean isHide;
    private String labelId;
    private String labelText = "";

    public LeftToolbarBean(String str, String str2, int i, int i2, boolean z) {
        this.labelId = str;
        this.labelText = str2;
        this.iconBg = i;
        this.iconSelectBg = i2;
        this.isHide = z;
    }

    public LeftToolbarBean(String str, String str2, int i, int i2) {
        this.labelId = str;
        this.labelText = str2;
        this.iconBg = i;
        this.iconSelectBg = i2;
        this.isHide = false;
    }

    public String getLabelId() {
        return this.labelId;
    }

    public void setLabelId(String str) {
        this.labelId = str;
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

    public int getIconSelectBg() {
        return this.iconSelectBg;
    }

    public void setIconSelectBg(int i) {
        this.iconSelectBg = i;
    }

    public boolean isHide() {
        return this.isHide;
    }

    public void setHide(boolean z) {
        this.isHide = z;
    }

    public String toString() {
        return "LeftToolbarBean{labelId=" + this.labelId + ", labelText='" + this.labelText + '\'' + ", iconBg=" + this.iconBg + ", iconSelectBg=" + this.iconSelectBg + ", isHide=" + this.isHide + '}';
    }
}
