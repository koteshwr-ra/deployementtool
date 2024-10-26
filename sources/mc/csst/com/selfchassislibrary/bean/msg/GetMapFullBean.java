package mc.csst.com.selfchassislibrary.bean.msg;

public class GetMapFullBean {
    private MsgBean msg;
    private String op;
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

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class MsgBean {
        private String data;
        private InfoBean info;

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

        public static class InfoBean {
            private int height;
            private OriginBean origin;
            private float resolution;
            private int width;

            public OriginBean getOrigin() {
                return this.origin;
            }

            public void setOrigin(OriginBean originBean) {
                this.origin = originBean;
            }

            public int getWidth() {
                return this.width;
            }

            public void setWidth(int i) {
                this.width = i;
            }

            public float getResolution() {
                return this.resolution;
            }

            public void setResolution(float f) {
                this.resolution = f;
            }

            public int getHeight() {
                return this.height;
            }

            public void setHeight(int i) {
                this.height = i;
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
                    private float x;
                    private float y;

                    public float getY() {
                        return this.y;
                    }

                    public void setY(float f) {
                        this.y = f;
                    }

                    public float getX() {
                        return this.x;
                    }

                    public void setX(float f) {
                        this.x = f;
                    }
                }

                public static class OrientationBean {
                    private float w;
                    private float x;
                    private float y;
                    private float z;

                    public float getY() {
                        return this.y;
                    }

                    public void setY(float f) {
                        this.y = f;
                    }

                    public float getX() {
                        return this.x;
                    }

                    public void setX(float f) {
                        this.x = f;
                    }

                    public float getZ() {
                        return this.z;
                    }

                    public void setZ(float f) {
                        this.z = f;
                    }

                    public float getW() {
                        return this.w;
                    }

                    public void setW(float f) {
                        this.w = f;
                    }
                }
            }

            public String toString() {
                return "InfoBean{origin=" + this.origin + ", width=" + this.width + ", resolution=" + this.resolution + ", height=" + this.height + '}';
            }
        }

        public String toString() {
            return "MsgBean{info=" + this.info + ", data='" + this.data + '\'' + '}';
        }
    }

    public String toString() {
        return "GetMapFullBean{topic='" + this.topic + '\'' + ", msg=" + this.msg + ", op='" + this.op + '\'' + '}';
    }
}
