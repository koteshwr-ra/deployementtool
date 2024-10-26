package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class AreaItemBean {
    private String name;
    private float param1;
    private double param2;
    private double param3;
    private double param4;
    private PolygonBean polygon;
    private int type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public double getParam2() {
        return this.param2;
    }

    public void setParam2(double d) {
        this.param2 = d;
    }

    public double getParam3() {
        return this.param3;
    }

    public void setParam3(double d) {
        this.param3 = d;
    }

    public double getParam4() {
        return this.param4;
    }

    public void setParam4(double d) {
        this.param4 = d;
    }

    public float getParam1() {
        return this.param1;
    }

    public void setParam1(float f) {
        this.param1 = f;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public PolygonBean getPolygon() {
        return this.polygon;
    }

    public void setPolygon(PolygonBean polygonBean) {
        this.polygon = polygonBean;
    }

    public static class PolygonBean {
        private List<PointsBean> points;

        public List<PointsBean> getPoints() {
            return this.points;
        }

        public void setPoints(List<PointsBean> list) {
            this.points = list;
        }

        public static class PointsBean {
            private double x;
            private double y;

            public double getY() {
                return this.y;
            }

            public void setY(double d) {
                this.y = d;
            }

            public double getX() {
                return this.x;
            }

            public void setX(double d) {
                this.x = d;
            }

            public String toString() {
                return "PointsBean{y=" + this.y + ", x=" + this.x + '}';
            }
        }

        public String toString() {
            return "PolygonBean{points=" + this.points + '}';
        }
    }

    public String toString() {
        return "AreaItemBean{name='" + this.name + '\'' + ", param1=" + this.param1 + ", param2=" + this.param2 + ", param3=" + this.param3 + ", param4=" + this.param4 + ", type=" + this.type + ", polygon=" + this.polygon + '}';
    }
}
