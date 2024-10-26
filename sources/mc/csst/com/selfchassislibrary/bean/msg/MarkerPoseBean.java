package mc.csst.com.selfchassislibrary.bean.msg;

public class MarkerPoseBean {
    private MarkerOrientationBean orientation;
    private MarkerPositionBean position;

    public MarkerPositionBean getPosition() {
        return this.position;
    }

    public void setPosition(MarkerPositionBean markerPositionBean) {
        this.position = markerPositionBean;
    }

    public MarkerOrientationBean getOrientation() {
        return this.orientation;
    }

    public void setOrientation(MarkerOrientationBean markerOrientationBean) {
        this.orientation = markerOrientationBean;
    }
}
