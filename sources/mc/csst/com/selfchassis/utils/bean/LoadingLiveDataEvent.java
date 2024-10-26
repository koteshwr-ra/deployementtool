package mc.csst.com.selfchassis.utils.bean;

public class LoadingLiveDataEvent {
    private boolean isCloseDialog = false;
    private String loadingTxt;
    private int timeOut = -1;

    public String getLoadingTxt() {
        return this.loadingTxt;
    }

    public LoadingLiveDataEvent(boolean z) {
        this.isCloseDialog = z;
    }

    public LoadingLiveDataEvent(String str) {
        this.loadingTxt = str;
    }

    public LoadingLiveDataEvent(String str, int i) {
        this.loadingTxt = str;
        this.timeOut = i;
    }

    public LoadingLiveDataEvent(String str, int i, boolean z) {
        this.loadingTxt = str;
        this.timeOut = i;
        this.isCloseDialog = z;
    }

    public void setLoadingTxt(String str) {
        this.loadingTxt = str;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public boolean isCloseDialog() {
        return this.isCloseDialog;
    }

    public void setCloseDialog(boolean z) {
        this.isCloseDialog = z;
    }
}
