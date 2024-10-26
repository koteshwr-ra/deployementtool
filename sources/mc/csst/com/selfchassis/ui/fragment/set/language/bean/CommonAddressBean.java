package mc.csst.com.selfchassis.ui.fragment.set.language.bean;

public class CommonAddressBean {
    private boolean isWuhan;
    private boolean selected;
    private String url;

    public CommonAddressBean(String str, boolean z) {
        this.url = str;
        this.isWuhan = z;
    }

    public boolean isWuhan() {
        return this.isWuhan;
    }

    public void setWuhan(boolean z) {
        this.isWuhan = z;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
