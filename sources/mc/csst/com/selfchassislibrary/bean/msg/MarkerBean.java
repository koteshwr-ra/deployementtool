package mc.csst.com.selfchassislibrary.bean.msg;

public class MarkerBean {
    private float behavior_code;
    private String name;
    private MarkerPoseBean pose;
    private float rest_time;
    private float time_out;

    public float getBehavior_code() {
        return this.behavior_code;
    }

    public void setBehavior_code(float f) {
        this.behavior_code = f;
    }

    public float getTime_out() {
        return this.time_out;
    }

    public void setTime_out(float f) {
        this.time_out = f;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public float getRest_time() {
        return this.rest_time;
    }

    public void setRest_time(float f) {
        this.rest_time = f;
    }

    public MarkerPoseBean getPose() {
        return this.pose;
    }

    public void setPose(MarkerPoseBean markerPoseBean) {
        this.pose = markerPoseBean;
    }
}
