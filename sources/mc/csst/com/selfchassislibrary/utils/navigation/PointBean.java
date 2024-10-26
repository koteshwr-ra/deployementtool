package mc.csst.com.selfchassislibrary.utils.navigation;

import java.util.ArrayList;
import java.util.List;

public class PointBean {
    private List<DataBean> data = new ArrayList();

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public String toString() {
        return "PointBean{data=" + this.data + '}';
    }

    public static class DataBean {
        private float angle;
        private String bindpoint;
        private String endbobao;
        private String goingbobao;
        private List<PicText> imagedescribe;
        private int isauto;
        private int isendbobao;
        private int isgoingbobao;
        private int isimageshow;
        private int isshow;
        private int isstartbobao;
        private String pointname;
        private String pointnum;
        private int setoften;
        private String startbobao;
        private int waittime;
        private float x;
        private float y;
        private int z;

        public String getPointnum() {
            return this.pointnum;
        }

        public void setPointnum(String str) {
            this.pointnum = str;
        }

        public String getPointname() {
            return this.pointname;
        }

        public void setPointname(String str) {
            this.pointname = str;
        }

        public float getX() {
            return this.x;
        }

        public void setX(float f) {
            this.x = f;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float f) {
            this.y = f;
        }

        public int getZ() {
            return this.z;
        }

        public void setZ(int i) {
            this.z = i;
        }

        public int getWaittime() {
            return this.waittime;
        }

        public void setWaittime(int i) {
            this.waittime = i;
        }

        public float getAngle() {
            return this.angle;
        }

        public void setAngle(float f) {
            this.angle = f;
        }

        public String getBindpoint() {
            return this.bindpoint;
        }

        public void setBindpoint(String str) {
            this.bindpoint = str;
        }

        public int getIsshow() {
            return this.isshow;
        }

        public void setIsshow(int i) {
            this.isshow = i;
        }

        public int getIsstartbobao() {
            return this.isstartbobao;
        }

        public void setIsstartbobao(int i) {
            this.isstartbobao = i;
        }

        public String getStartbobao() {
            return this.startbobao;
        }

        public void setStartbobao(String str) {
            this.startbobao = str;
        }

        public int getIsendbobao() {
            return this.isendbobao;
        }

        public void setIsendbobao(int i) {
            this.isendbobao = i;
        }

        public String getEndbobao() {
            return this.endbobao;
        }

        public void setEndbobao(String str) {
            this.endbobao = str;
        }

        public int getIsauto() {
            return this.isauto;
        }

        public void setIsauto(int i) {
            this.isauto = i;
        }

        public int getSetoften() {
            return this.setoften;
        }

        public void setSetoften(int i) {
            this.setoften = i;
        }

        public int getIsgoingbobao() {
            return this.isgoingbobao;
        }

        public void setIsgoingbobao(int i) {
            this.isgoingbobao = i;
        }

        public String getGoingbobao() {
            return this.goingbobao;
        }

        public void setGoingbobao(String str) {
            this.goingbobao = str;
        }

        public int getIsimageshow() {
            return this.isimageshow;
        }

        public void setIsimageshow(int i) {
            this.isimageshow = i;
        }

        public List<PicText> getImagedescribe() {
            return this.imagedescribe;
        }

        public void setImagedescribe(List<PicText> list) {
            this.imagedescribe = list;
        }

        public String toString() {
            return "DataBean{pointnum='" + this.pointnum + '\'' + ", pointname='" + this.pointname + '\'' + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", waittime=" + this.waittime + ", angle=" + this.angle + ", bindpoint='" + this.bindpoint + '\'' + ", isshow=" + this.isshow + ", isstartbobao=" + this.isstartbobao + ", startbobao='" + this.startbobao + '\'' + ", isendbobao=" + this.isendbobao + ", endbobao='" + this.endbobao + '\'' + ", isauto=" + this.isauto + ", setoften=" + this.setoften + ", isgoingbobao=" + this.isgoingbobao + ", goingbobao='" + this.goingbobao + '\'' + ", isimageshow=" + this.isimageshow + ", imagedescribe=" + this.imagedescribe + '}';
        }
    }
}
