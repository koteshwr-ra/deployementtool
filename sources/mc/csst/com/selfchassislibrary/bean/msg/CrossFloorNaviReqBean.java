package mc.csst.com.selfchassislibrary.bean.msg;

public class CrossFloorNaviReqBean {
    private String id;
    private MsgBean msg;
    private String op;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public MsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(MsgBean msgBean) {
        this.msg = msgBean;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class MsgBean {
        private String goal_floor;
        private String marker_name;
        private MarkerPoseBean marker_pose;

        public MarkerPoseBean getMarker_pose() {
            return this.marker_pose;
        }

        public void setMarker_pose(MarkerPoseBean markerPoseBean) {
            this.marker_pose = markerPoseBean;
        }

        public String getGoal_floor() {
            return this.goal_floor;
        }

        public void setGoal_floor(String str) {
            this.goal_floor = str;
        }

        public String getMarker_name() {
            return this.marker_name;
        }

        public void setMarker_name(String str) {
            this.marker_name = str;
        }
    }
}
