package mc.csst.com.selfchassislibrary.utils.navigation;

public class PicText {
    private String imagedesc;
    private String imagename;

    public PicText(String str, String str2) {
        this.imagename = str;
        this.imagedesc = str2;
    }

    public String getImagename() {
        return this.imagename;
    }

    public void setImagename(String str) {
        this.imagename = str;
    }

    public String getImagedesc() {
        return this.imagedesc;
    }

    public void setImagedesc(String str) {
        this.imagedesc = str;
    }
}
