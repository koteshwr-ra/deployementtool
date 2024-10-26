package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class TagManagerControlResponseBean {
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
        private String info;
        private List<?> map_list;
        private MarkersBean markers;
        private int result;
        private VwallsBean vwalls;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public int getResult() {
            return this.result;
        }

        public void setResult(int i) {
            this.result = i;
        }

        public MarkersBean getMarkers() {
            return this.markers;
        }

        public void setMarkers(MarkersBean markersBean) {
            this.markers = markersBean;
        }

        public VwallsBean getVwalls() {
            return this.vwalls;
        }

        public void setVwalls(VwallsBean vwallsBean) {
            this.vwalls = vwallsBean;
        }

        public List<?> getMap_list() {
            return this.map_list;
        }

        public void setMap_list(List<?> list) {
            this.map_list = list;
        }

        public static class MarkersBean {
            private List<?> waypoints;

            public List<?> getWaypoints() {
                return this.waypoints;
            }

            public void setWaypoints(List<?> list) {
                this.waypoints = list;
            }
        }

        public static class VwallsBean {
            private HeaderBean header;
            private String map_name;
            private List<?> walls;

            public String getMap_name() {
                return this.map_name;
            }

            public void setMap_name(String str) {
                this.map_name = str;
            }

            public HeaderBean getHeader() {
                return this.header;
            }

            public void setHeader(HeaderBean headerBean) {
                this.header = headerBean;
            }

            public List<?> getWalls() {
                return this.walls;
            }

            public void setWalls(List<?> list) {
                this.walls = list;
            }

            public static class HeaderBean {
                private String frame_id;
                private int seq;
                private StampBean stamp;

                public StampBean getStamp() {
                    return this.stamp;
                }

                public void setStamp(StampBean stampBean) {
                    this.stamp = stampBean;
                }

                public String getFrame_id() {
                    return this.frame_id;
                }

                public void setFrame_id(String str) {
                    this.frame_id = str;
                }

                public int getSeq() {
                    return this.seq;
                }

                public void setSeq(int i) {
                    this.seq = i;
                }

                public static class StampBean {
                    private int nsecs;
                    private int secs;

                    public int getSecs() {
                        return this.secs;
                    }

                    public void setSecs(int i) {
                        this.secs = i;
                    }

                    public int getNsecs() {
                        return this.nsecs;
                    }

                    public void setNsecs(int i) {
                        this.nsecs = i;
                    }
                }
            }
        }
    }
}
