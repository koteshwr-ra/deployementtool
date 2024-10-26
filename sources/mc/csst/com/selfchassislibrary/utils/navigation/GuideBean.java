package mc.csst.com.selfchassislibrary.utils.navigation;

import java.util.List;

public class GuideBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public String toString() {
        return "GuideBean{data=" + this.data + '}';
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public static class DataBean {
        private String broadcast;
        private int isuser;
        private int isvisits;
        private String linename;
        private int linenum;
        private String linepoint;
        private List<PicText> showmap;
        private VisitstimeBean visitstime;

        public String toString() {
            return "DataBean{linenum=" + this.linenum + ", linename='" + this.linename + '\'' + ", isuser=" + this.isuser + ", isvisits=" + this.isvisits + ", broadcast='" + this.broadcast + '\'' + ", visitstime=" + this.visitstime + ", linepoint='" + this.linepoint + '\'' + ", showmap=" + this.showmap + '}';
        }

        public int getLinenum() {
            return this.linenum;
        }

        public void setLinenum(int i) {
            this.linenum = i;
        }

        public String getLinename() {
            return this.linename;
        }

        public void setLinename(String str) {
            this.linename = str;
        }

        public int getIsuser() {
            return this.isuser;
        }

        public void setIsuser(int i) {
            this.isuser = i;
        }

        public int getIsvisits() {
            return this.isvisits;
        }

        public void setIsvisits(int i) {
            this.isvisits = i;
        }

        public String getBroadcast() {
            return this.broadcast;
        }

        public void setBroadcast(String str) {
            this.broadcast = str;
        }

        public VisitstimeBean getVisitstime() {
            return this.visitstime;
        }

        public String getLinepoint() {
            return this.linepoint;
        }

        public void setLinepoint(String str) {
            this.linepoint = str;
        }

        public List<PicText> getShowmap() {
            return this.showmap;
        }

        public void setShowmap(List<PicText> list) {
            this.showmap = list;
        }

        public static class VisitstimeBean {
            private String endguidetime;
            private String freestarttime;
            private String guidedate;
            private String startguidetime;
            private String stoptime;

            public String getGuidedate() {
                return this.guidedate;
            }

            public void setGuidedate(String str) {
                this.guidedate = str;
            }

            public String getStartguidetime() {
                return this.startguidetime;
            }

            public void setStartguidetime(String str) {
                this.startguidetime = str;
            }

            public String getEndguidetime() {
                return this.endguidetime;
            }

            public void setEndguidetime(String str) {
                this.endguidetime = str;
            }

            public String getStoptime() {
                return this.stoptime;
            }

            public void setStoptime(String str) {
                this.stoptime = str;
            }

            public String getFreestarttime() {
                return this.freestarttime;
            }

            public void setFreestarttime(String str) {
                this.freestarttime = str;
            }

            public String toString() {
                return "VisitstimeBean{guidedate='" + this.guidedate + '\'' + ", startguidetime='" + this.startguidetime + '\'' + ", endguidetime='" + this.endguidetime + '\'' + ", stoptime='" + this.stoptime + '\'' + ", freestarttime='" + this.freestarttime + '\'' + '}';
            }
        }
    }
}
