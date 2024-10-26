package mc.csst.com.selfchassis.utils.bean;

public class ToolbarBean {
    private int iconBg;
    private int iconSelectBg;
    private boolean isHide;
    private int labelId;
    private String labelText = "";

    public ToolbarBean(int i, String str, int i2, int i3, boolean z) {
        this.labelId = i;
        this.labelText = str;
        this.iconBg = i2;
        this.iconSelectBg = i3;
        this.isHide = z;
    }

    public ToolbarBean(int i, String str, int i2, int i3) {
        this.labelId = i;
        this.labelText = str;
        this.iconBg = i2;
        this.iconSelectBg = i3;
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
