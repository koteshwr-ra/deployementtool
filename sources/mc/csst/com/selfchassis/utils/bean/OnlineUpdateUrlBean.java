package mc.csst.com.selfchassis.utils.bean;

public class OnlineUpdateUrlBean {
    private boolean isDefault;
    private String url = "";
    private String urlAlias = "";
    private int urlDescType;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getUrlDescType() {
        return this.urlDescType;
    }

    public void setUrlDescType(int i) {
        this.urlDescType = i;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public void setDefault(boolean z) {
        this.isDefault = z;
    }

    public String getUrlAlias() {
        return this.urlAlias;
    }

    public void setUrlAlias(String str) {
        this.urlAlias = str;
    }

    public OnlineUpdateUrlBean(String str, int i, boolean z, String str2) {
        this.url = str;
        this.urlDescType = i;
        this.isDefault = z;
        this.urlAlias = str2;
    }

    public String toString() {
        return "OnlineUpdateUrlBean{url='" + this.url + '\'' + ", urlDescType=" + this.urlDescType + ", isDefault=" + this.isDefault + ", urlAlias='" + this.urlAlias + '\'' + '}';
    }
}
