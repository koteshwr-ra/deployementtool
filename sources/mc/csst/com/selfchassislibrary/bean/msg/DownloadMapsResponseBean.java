package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class DownloadMapsResponseBean {
    private String id;
    private String info;
    private String op;
    private boolean result;
    private String service;
    private ValuesBean values;

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean z) {
        this.result = z;
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

    public static class ValuesBean {
        private String building_name;
        private List<FloorBean> floor;
        private String info;
        private String robot_id;
        private int status;

        public String getRobot_id() {
            return this.robot_id;
        }

        public void setRobot_id(String str) {
            this.robot_id = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public List<FloorBean> getFloor() {
            return this.floor;
        }

        public void setFloor(List<FloorBean> list) {
            this.floor = list;
        }

        public String getBuilding_name() {
            return this.building_name;
        }

        public void setBuilding_name(String str) {
            this.building_name = str;
        }

        public static class FloorBean {
            private String annex;
            private int height;
            private String num;
            private String path;
            private List<?> place;
            private int width;

            public String getAnnex() {
                return this.annex;
            }

            public void setAnnex(String str) {
                this.annex = str;
            }

            public int getHeight() {
                return this.height;
            }

            public void setHeight(int i) {
                this.height = i;
            }

            public int getWidth() {
                return this.width;
            }

            public void setWidth(int i) {
                this.width = i;
            }

            public String getNum() {
                return this.num;
            }

            public void setNum(String str) {
                this.num = str;
            }

            public List<?> getPlace() {
                return this.place;
            }

            public void setPlace(List<?> list) {
                this.place = list;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String str) {
                this.path = str;
            }
        }
    }
}
