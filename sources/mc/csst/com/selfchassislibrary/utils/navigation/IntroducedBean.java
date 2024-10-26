package mc.csst.com.selfchassislibrary.utils.navigation;

import java.util.ArrayList;
import java.util.List;

public class IntroducedBean {
    private List<DataBean> data = new ArrayList();

    public List<DataBean> getData() {
        return this.data;
    }

    public String toString() {
        return "IntroducedBean{data=" + this.data + '}';
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public static class DataBean {
        private List<PicText> imagedescribe;
        private String intropoint;
        private String introtitle;
        private int isshow;
        private int number;

        public DataBean() {
        }

        public DataBean(int i, String str, String str2, int i2, List<PicText> list) {
            this.number = i;
            this.introtitle = str;
            this.intropoint = str2;
            this.isshow = i2;
            this.imagedescribe = list;
        }

        public int getNumber() {
            return this.number;
        }

        public void setNumber(int i) {
            this.number = i;
        }

        public String getIntrotitle() {
            return this.introtitle;
        }

        public void setIntrotitle(String str) {
            this.introtitle = str;
        }

        public String getIntropoint() {
            return this.intropoint;
        }

        public void setIntropoint(String str) {
            this.intropoint = str;
        }

        public int getIsshow() {
            return this.isshow;
        }

        public void setIsshow(int i) {
            this.isshow = i;
        }

        public List<PicText> getImagedescribe() {
            return this.imagedescribe;
        }

        public void setImagedescribe(List<PicText> list) {
            this.imagedescribe = list;
        }

        public String toString() {
            return "DataBean{number=" + this.number + ", introtitle='" + this.introtitle + '\'' + ", intropoint='" + this.intropoint + '\'' + ", isshow=" + this.isshow + ", imagedescribe=" + this.imagedescribe + '}';
        }
    }
}
