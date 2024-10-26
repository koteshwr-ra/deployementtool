package mc.csst.com.selfchassislibrary.bean.msg;

public class GetMapInfoResponseBean {
    private String id;
    private String op;
    private boolean result;
    private String service;
    private ValuesBean values;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getService() {
        return this.service;
    }

    public void setService(String str) {
        this.service = str;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class ValuesBean {
        private String building_name;
        private String floor_name;
        private double free_space = 0.0d;
        private int height;
        private double occ_space = 0.0d;
        private float origin_x;
        private float origin_y;
        private float resolution;
        private double unknown_space = 0.0d;
        private int width;

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

        public float getOrigin_y() {
            return this.origin_y;
        }

        public void setOrigin_y(float f) {
            this.origin_y = f;
        }

        public float getOrigin_x() {
            return this.origin_x;
        }

        public void setOrigin_x(float f) {
            this.origin_x = f;
        }

        public String getFloor_name() {
            return this.floor_name;
        }

        public void setFloor_name(String str) {
            this.floor_name = str;
        }

        public float getResolution() {
            return this.resolution;
        }

        public void setResolution(float f) {
            this.resolution = f;
        }

        public String getBuilding_name() {
            return this.building_name;
        }

        public void setBuilding_name(String str) {
            this.building_name = str;
        }

        public double getFree_space() {
            return this.free_space;
        }

        public void setFree_space(double d) {
            this.free_space = d;
        }

        public double getOcc_space() {
            return this.occ_space;
        }

        public void setOcc_space(double d) {
            this.occ_space = d;
        }

        public double getUnknown_space() {
            return this.unknown_space;
        }

        public void setUnknown_space(double d) {
            this.unknown_space = d;
        }
    }
}
