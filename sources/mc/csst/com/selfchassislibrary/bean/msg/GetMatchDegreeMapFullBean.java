package mc.csst.com.selfchassislibrary.bean.msg;

public class GetMatchDegreeMapFullBean {
    private MsgBean msg;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public MsgBean getMsg() {
        return this.msg;
    }

    public void setMsg(MsgBean msgBean) {
        this.msg = msgBean;
    }

    public static class MsgBean {
        private String data;
        private InfoBean info;
        private String op;

        public InfoBean getInfo() {
            return this.info;
        }

        public void setInfo(InfoBean infoBean) {
            this.info = infoBean;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String str) {
            this.data = str;
        }

        public String getOp() {
            return this.op;
        }

        public void setOp(String str) {
            this.op = str;
        }

        public static class InfoBean {
            private Integer height;
            private OriginBean origin;
            private Float resolution;
            private Integer width;

            public OriginBean getOrigin() {
                return this.origin;
            }

            public void setOrigin(OriginBean originBean) {
                this.origin = originBean;
            }

            public Integer getWidth() {
                return this.width;
            }

            public void setWidth(Integer num) {
                this.width = num;
            }

            public Float getResolution() {
                return this.resolution;
            }

            public void setResolution(Float f) {
                this.resolution = f;
            }

            public Integer getHeight() {
                return this.height;
            }

            public void setHeight(Integer num) {
                this.height = num;
            }

            public static class OriginBean {
                private OrientationBean orientation;
                private PositionBean position;

                public PositionBean getPosition() {
                    return this.position;
                }

                public void setPosition(PositionBean positionBean) {
                    this.position = positionBean;
                }

                public OrientationBean getOrientation() {
                    return this.orientation;
                }

                public void setOrientation(OrientationBean orientationBean) {
                    this.orientation = orientationBean;
                }

                public static class PositionBean {
                    private Float x;
                    private Float y;
                    private Float z;

                    public Float getX() {
                        return this.x;
                    }

                    public void setX(Float f) {
                        this.x = f;
                    }

                    public Float getY() {
                        return this.y;
                    }

                    public void setY(Float f) {
                        this.y = f;
                    }

                    public Float getZ() {
                        return this.z;
                    }

                    public void setZ(Float f) {
                        this.z = f;
                    }
                }

                public static class OrientationBean {
                    private Float w;
                    private Float x;
                    private Float y;
                    private Float z;

                    public Float getY() {
                        return this.y;
                    }

                    public void setY(Float f) {
                        this.y = f;
                    }

                    public Float getX() {
                        return this.x;
                    }

                    public void setX(Float f) {
                        this.x = f;
                    }

                    public Float getZ() {
                        return this.z;
                    }

                    public void setZ(Float f) {
                        this.z = f;
                    }

                    public Float getW() {
                        return this.w;
                    }

                    public void setW(Float f) {
                        this.w = f;
                    }
                }
            }
        }
    }
}
