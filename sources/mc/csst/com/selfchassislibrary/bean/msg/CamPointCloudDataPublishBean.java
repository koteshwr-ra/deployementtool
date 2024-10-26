package mc.csst.com.selfchassislibrary.bean.msg;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class CamPointCloudDataPublishBean implements Serializable {
    @SerializedName("msg")
    private MsgDTO msg;
    @SerializedName("op")
    private String op;
    @SerializedName("topic")
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public MsgDTO getMsg() {
        return this.msg;
    }

    public void setMsg(MsgDTO msgDTO) {
        this.msg = msgDTO;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class MsgDTO implements Serializable {
        @SerializedName("pt")
        private List<Float> pt;
        @SerializedName("px")
        private List<Float> px;
        @SerializedName("py")
        private List<Float> py;

        public List<Float> getPx() {
            return this.px;
        }

        public void setPx(List<Float> list) {
            this.px = list;
        }

        public List<Float> getPy() {
            return this.py;
        }

        public void setPy(List<Float> list) {
            this.py = list;
        }

        public List<Float> getPt() {
            return this.pt;
        }

        public void setPt(List<Float> list) {
            this.pt = list;
        }
    }
}
