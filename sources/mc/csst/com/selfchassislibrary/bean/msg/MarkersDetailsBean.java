package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class MarkersDetailsBean {
    private String id;
    private String info;
    private String op;
    private Boolean result;
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

    public Boolean isResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
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
        private List<FloorsBean> floors;
        private String info;
        private float result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public float getResult() {
            return this.result;
        }

        public void setResult(float f) {
            this.result = f;
        }

        public String getBuilding_name() {
            return this.building_name;
        }

        public void setBuilding_name(String str) {
            this.building_name = str;
        }

        public List<FloorsBean> getFloors() {
            return this.floors;
        }

        public void setFloors(List<FloorsBean> list) {
            this.floors = list;
        }

        public static class FloorsBean {
            private String floor_name;
            private List<MarkerBean> markers;

            public String getFloor_name() {
                return this.floor_name;
            }

            public void setFloor_name(String str) {
                this.floor_name = str;
            }

            public List<MarkerBean> getMarkers() {
                return this.markers;
            }

            public void setMarkers(List<MarkerBean> list) {
                this.markers = list;
            }
        }
    }
}
