package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class VirtualWallOperationGetWallsResponseBean {
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
        private String info;
        private List<ListInfoBean> list_info;
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

        public List<ListInfoBean> getList_info() {
            return this.list_info;
        }

        public void setList_info(List<ListInfoBean> list) {
            this.list_info = list;
        }

        public static class MarkersBean {
            private List<WaypointsBean> waypoints;

            public List<WaypointsBean> getWaypoints() {
                return this.waypoints;
            }

            public void setWaypoints(List<WaypointsBean> list) {
                this.waypoints = list;
            }

            public static class WaypointsBean {
                private int behavior_code;
                private String name;
                private PoseBean pose;
                private float rest_time;
                private float time_out;

                public int getBehavior_code() {
                    return this.behavior_code;
                }

                public void setBehavior_code(int i) {
                    this.behavior_code = i;
                }

                public float getTime_out() {
                    return this.time_out;
                }

                public void setTime_out(int i) {
                    this.time_out = (float) i;
                }

                public PoseBean getPose() {
                    return this.pose;
                }

                public void setPose(PoseBean poseBean) {
                    this.pose = poseBean;
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public float getRest_time() {
                    return this.rest_time;
                }

                public void setRest_time(int i) {
                    this.rest_time = (float) i;
                }

                public static class PoseBean {
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
                        private float z;

                        public float getY() {
                            return this.y;
                        }

                        public void setY(int i) {
                            this.y = (float) i;
                        }

                        public float getX() {
                            return this.x;
                        }

                        public void setX(int i) {
                            this.x = (float) i;
                        }

                        public float getZ() {
                            return this.z;
                        }

                        public void setZ(int i) {
                            this.z = (float) i;
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

                        public void setY(int i) {
                            this.y = (float) i;
                        }

                        public float getX() {
                            return this.x;
                        }

                        public void setX(int i) {
                            this.x = (float) i;
                        }

                        public float getZ() {
                            return this.z;
                        }

                        public void setZ(int i) {
                            this.z = (float) i;
                        }

                        public float getW() {
                            return this.w;
                        }

                        public void setW(int i) {
                            this.w = (float) i;
                        }
                    }
                }
            }
        }

        public static class VwallsBean {
            private HeaderBean header;
            private String map_name;
            private List<WallsBean> walls;

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

            public List<WallsBean> getWalls() {
                return this.walls;
            }

            public void setWalls(List<WallsBean> list) {
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

            public static class WallsBean {
                private float end_x;
                private float end_y;
                private float start_x;
                private float start_y;
                private String wall_id;

                public float getEnd_x() {
                    return this.end_x;
                }

                public void setEnd_x(float f) {
                    this.end_x = f;
                }

                public String getWall_id() {
                    return this.wall_id;
                }

                public void setWall_id(String str) {
                    this.wall_id = str;
                }

                public float getEnd_y() {
                    return this.end_y;
                }

                public void setEnd_y(float f) {
                    this.end_y = f;
                }

                public float getStart_x() {
                    return this.start_x;
                }

                public void setStart_x(float f) {
                    this.start_x = f;
                }

                public float getStart_y() {
                    return this.start_y;
                }

                public void setStart_y(float f) {
                    this.start_y = f;
                }
            }
        }

        public static class ListInfoBean {
            private String building_name;
            private List<FloorInfoBean> floor_info;

            public String getBuilding_name() {
                return this.building_name;
            }

            public void setBuilding_name(String str) {
                this.building_name = str;
            }

            public List<FloorInfoBean> getFloor_info() {
                return this.floor_info;
            }

            public void setFloor_info(List<FloorInfoBean> list) {
                this.floor_info = list;
            }

            public static class FloorInfoBean {
                private String floor_name;
                private boolean map;
                private boolean markers;
                private boolean virtual_walls;

                public boolean isMap() {
                    return this.map;
                }

                public void setMap(boolean z) {
                    this.map = z;
                }

                public String getFloor_name() {
                    return this.floor_name;
                }

                public void setFloor_name(String str) {
                    this.floor_name = str;
                }

                public boolean isMarkers() {
                    return this.markers;
                }

                public void setMarkers(boolean z) {
                    this.markers = z;
                }

                public boolean isVirtual_walls() {
                    return this.virtual_walls;
                }

                public void setVirtual_walls(boolean z) {
                    this.virtual_walls = z;
                }
            }
        }
    }
}
