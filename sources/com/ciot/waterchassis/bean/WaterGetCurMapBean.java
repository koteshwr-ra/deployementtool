package com.ciot.waterchassis.bean;

public class WaterGetCurMapBean {
    private String command;
    private String error_message;
    private ResultsBean results;
    private String status;
    private String type;
    private String uuid;

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String str) {
        this.error_message = str;
    }

    public ResultsBean getResults() {
        return this.results;
    }

    public void setResults(ResultsBean resultsBean) {
        this.results = resultsBean;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public static class ResultsBean {
        private int floor;
        private InfoBean info;
        private String map_name;

        public int getFloor() {
            return this.floor;
        }

        public void setFloor(int i) {
            this.floor = i;
        }

        public InfoBean getInfo() {
            return this.info;
        }

        public void setInfo(InfoBean infoBean) {
            this.info = infoBean;
        }

        public String getMap_name() {
            return this.map_name;
        }

        public void setMap_name(String str) {
            this.map_name = str;
        }

        public static class InfoBean {
            private int height;
            private double origin_x;
            private double origin_y;
            private double resolution;
            private int width;

            public int getHeight() {
                return this.height;
            }

            public void setHeight(int i) {
                this.height = i;
            }

            public double getOrigin_x() {
                return this.origin_x;
            }

            public void setOrigin_x(double d) {
                this.origin_x = d;
            }

            public double getOrigin_y() {
                return this.origin_y;
            }

            public void setOrigin_y(double d) {
                this.origin_y = d;
            }

            public double getResolution() {
                return this.resolution;
            }

            public void setResolution(double d) {
                this.resolution = d;
            }

            public int getWidth() {
                return this.width;
            }

            public void setWidth(int i) {
                this.width = i;
            }

            public String toString() {
                return "InfoBean{height=" + this.height + ", origin_x=" + this.origin_x + ", origin_y=" + this.origin_y + ", resolution=" + this.resolution + ", width=" + this.width + '}';
            }
        }

        public String toString() {
            return "ResultsBean{floor=" + this.floor + ", info=" + this.info + ", map_name='" + this.map_name + '\'' + '}';
        }
    }

    public String toString() {
        return "WaterGetCurMapBean{command='" + this.command + '\'' + ", error_message='" + this.error_message + '\'' + ", results=" + this.results + ", status='" + this.status + '\'' + ", type='" + this.type + '\'' + ", uuid='" + this.uuid + '\'' + '}';
    }
}
