package mc.csst.com.selfchassislibrary.bean.msg;

import java.util.List;

public class SendFollowPathResponseBean {
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

    public ValuesBean getValues() {
        return this.values;
    }

    public void setValues(ValuesBean valuesBean) {
        this.values = valuesBean;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public static class ValuesBean {
        private AdjacentListBean adjacent_list;
        private String info;
        private Integer result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public AdjacentListBean getAdjacent_list() {
            return this.adjacent_list;
        }

        public void setAdjacent_list(AdjacentListBean adjacentListBean) {
            this.adjacent_list = adjacentListBean;
        }

        public Integer getResult() {
            return this.result;
        }

        public void setResult(Integer num) {
            this.result = num;
        }

        public static class AdjacentListBean {
            private String building_name;
            private String floor_name;
            private String goal_node_name;
            private List<NodesBean> nodes;
            private String start_node_name;

            public String getGoal_node_name() {
                return this.goal_node_name;
            }

            public void setGoal_node_name(String str) {
                this.goal_node_name = str;
            }

            public String getFloor_name() {
                return this.floor_name;
            }

            public void setFloor_name(String str) {
                this.floor_name = str;
            }

            public String getStart_node_name() {
                return this.start_node_name;
            }

            public void setStart_node_name(String str) {
                this.start_node_name = str;
            }

            public List<NodesBean> getNodes() {
                return this.nodes;
            }

            public void setNodes(List<NodesBean> list) {
                this.nodes = list;
            }

            public String getBuilding_name() {
                return this.building_name;
            }

            public void setBuilding_name(String str) {
                this.building_name = str;
            }

            public static class NodesBean {
                private String goal_node_name;
                private Float pass_capacity;
                private String path_name;
                private List<Float> path_t;
                private Float path_width;
                private List<Float> path_x;
                private List<Float> path_y;
                private String start_node_name;
                private Float velocity;

                public String getPath_name() {
                    return this.path_name;
                }

                public void setPath_name(String str) {
                    this.path_name = str;
                }

                public List<Float> getPath_x() {
                    return this.path_x;
                }

                public void setPath_x(List<Float> list) {
                    this.path_x = list;
                }

                public Float getPath_width() {
                    return this.path_width;
                }

                public void setPath_width(Float f) {
                    this.path_width = f;
                }

                public List<Float> getPath_y() {
                    return this.path_y;
                }

                public void setPath_y(List<Float> list) {
                    this.path_y = list;
                }

                public List<Float> getPath_t() {
                    return this.path_t;
                }

                public void setPath_t(List<Float> list) {
                    this.path_t = list;
                }

                public Float getVelocity() {
                    return this.velocity;
                }

                public void setVelocity(Float f) {
                    this.velocity = f;
                }

                public String getGoal_node_name() {
                    return this.goal_node_name;
                }

                public void setGoal_node_name(String str) {
                    this.goal_node_name = str;
                }

                public Float getPass_capacity() {
                    return this.pass_capacity;
                }

                public void setPass_capacity(Float f) {
                    this.pass_capacity = f;
                }

                public String getStart_node_name() {
                    return this.start_node_name;
                }

                public void setStart_node_name(String str) {
                    this.start_node_name = str;
                }
            }
        }
    }
}
