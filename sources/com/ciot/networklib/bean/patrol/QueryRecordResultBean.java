package com.ciot.networklib.bean.patrol;

import java.io.Serializable;
import java.util.List;

public class QueryRecordResultBean implements Serializable {
    private List<DatasBean> datas;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<DatasBean> getDatas() {
        return this.datas;
    }

    public void setDatas(List<DatasBean> list) {
        this.datas = list;
    }

    public static class DatasBean implements Serializable {
        private long begin;
        private int createtime;
        private long end;
        private String id;
        private String line;
        private String map;
        private List<PathsBean> paths;
        private String robot;
        private String state;
        private String task;

        public static class PathsBean implements Serializable {
            private EndBean end;
            private PathBean path;
            private StartBean start;

            public static class EndBean implements Serializable {
                private List<ActionsBean> actions;
                private List<AlarmsBean> alarms;
                private long begin;
                private long end;
                private List<Double> loc;
                private String name;

                public static class ActionsBean implements Serializable {
                    private String path;
                    private String resId;
                    private long time;
                    private int type;
                }

                public static class AlarmsBean implements Serializable {
                    private String id;
                    private String name;
                    private long time;
                    private String type;
                }
            }

            public static class PathBean implements Serializable {
                private List<?> actions;
                private List<?> alarms;
                private long begin;
                private long end;
                private List<Double> loc_end;
                private List<Double> loc_start;
                private double speed;
            }

            public static class StartBean implements Serializable {
                private List<ActionsBeanX> actions;
                private List<AlarmsBeanX> alarms;
                private long begin;
                private long end;
                private List<Double> loc;
                private String name;

                public static class ActionsBeanX implements Serializable {
                    private String path;
                    private String resId;
                    private long time;
                    private int type;
                }

                public static class AlarmsBeanX implements Serializable {
                    private String id;
                    private String name;
                    private long time;
                    private String type;
                }
            }
        }

        public long getBegin() {
            return this.begin;
        }

        public void setBegin(long j) {
            this.begin = j;
        }

        public long getEnd() {
            return this.end;
        }

        public void setEnd(long j) {
            this.end = j;
        }

        public String getLine() {
            return this.line;
        }

        public void setLine(String str) {
            this.line = str;
        }

        public String getMap() {
            return this.map;
        }

        public void setMap(String str) {
            this.map = str;
        }

        public String getRobot() {
            return this.robot;
        }

        public void setRobot(String str) {
            this.robot = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getTask() {
            return this.task;
        }

        public void setTask(String str) {
            this.task = str;
        }

        public int getCreatetime() {
            return this.createtime;
        }

        public void setCreatetime(int i) {
            this.createtime = i;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public List<PathsBean> getPaths() {
            return this.paths;
        }

        public void setPaths(List<PathsBean> list) {
            this.paths = list;
        }
    }
}
