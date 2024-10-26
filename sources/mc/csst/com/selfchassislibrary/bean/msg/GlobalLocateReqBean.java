package mc.csst.com.selfchassislibrary.bean.msg;

import java.io.Serializable;
import java.util.List;

public class GlobalLocateReqBean implements Serializable {
    private ArgsBean args;
    private String id;
    private String op;
    private String service;

    public ArgsBean getArgs() {
        return this.args;
    }

    public void setArgs(ArgsBean argsBean) {
        this.args = argsBean;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public static class ArgsBean implements Serializable {
        private int cmd;
        private SearchBoundaryBean search_boundary;
        private int search_step_angular;
        private double search_step_linear;

        public int getCmd() {
            return this.cmd;
        }

        public void setCmd(int i) {
            this.cmd = i;
        }

        public double getSearch_step_linear() {
            return this.search_step_linear;
        }

        public void setSearch_step_linear(double d) {
            this.search_step_linear = d;
        }

        public SearchBoundaryBean getSearch_boundary() {
            return this.search_boundary;
        }

        public void setSearch_boundary(SearchBoundaryBean searchBoundaryBean) {
            this.search_boundary = searchBoundaryBean;
        }

        public int getSearch_step_angular() {
            return this.search_step_angular;
        }

        public void setSearch_step_angular(int i) {
            this.search_step_angular = i;
        }

        public static class SearchBoundaryBean implements Serializable {
            private HeaderBean header;
            private PolygonBean polygon;

            public HeaderBean getHeader() {
                return this.header;
            }

            public void setHeader(HeaderBean headerBean) {
                this.header = headerBean;
            }

            public PolygonBean getPolygon() {
                return this.polygon;
            }

            public void setPolygon(PolygonBean polygonBean) {
                this.polygon = polygonBean;
            }

            public static class HeaderBean implements Serializable {
                private String frame_id;
                private int seq;
                private double stamp;

                public double getStamp() {
                    return this.stamp;
                }

                public void setStamp(double d) {
                    this.stamp = d;
                }

                public String getFrame_id() {
                    return this.frame_id;
                }

                public void setFrame_id(String str) {
                    this.frame_id = str;
                }

                public float getSeq() {
                    return (float) this.seq;
                }

                public void setSeq(int i) {
                    this.seq = i;
                }
            }

            public static class PolygonBean implements Serializable {
                private List<PointsBean> points;

                public List<PointsBean> getPoints() {
                    return this.points;
                }

                public void setPoints(List<PointsBean> list) {
                    this.points = list;
                }

                public static class PointsBean implements Serializable {
                    private double x;
                    private double y;
                    private double z;

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

                    public double getZ() {
                        return this.z;
                    }

                    public void setZ(double d) {
                        this.z = d;
                    }
                }
            }
        }
    }
}
