package com.ciot.networklib.bean;

import java.util.List;
import java.util.Objects;

public class CommonResponseBean {
    private int code;
    private DataBean data;
    private String message;
    private int ttl;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getTtl() {
        return this.ttl;
    }

    public void setTtl(int i) {
        this.ttl = i;
    }

    public static class DataBean {
        private List<DataListBean> dataList;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public List<DataListBean> getDataList() {
            return this.dataList;
        }

        public void setDataList(List<DataListBean> list) {
            this.dataList = list;
        }

        public String toString() {
            return "DataBean{type='" + this.type + '\'' + ", dataList=" + this.dataList + '}';
        }

        public static class DataListBean {
            private String action;
            public String backgroundurl;
            private String name;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getAction() {
                return this.action;
            }

            public void setAction(String str) {
                this.action = str;
            }

            public String getBackgroundurl() {
                return this.backgroundurl;
            }

            public void setBackgroundurl(String str) {
                this.backgroundurl = str;
            }

            public String toString() {
                return "DataListBean{name='" + this.name + '\'' + ", action='" + this.action + '\'' + ", backgroundurl='" + this.backgroundurl + '\'' + '}';
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                DataListBean dataListBean = (DataListBean) obj;
                if (!Objects.equals(this.name, dataListBean.name) || !Objects.equals(this.action, dataListBean.action) || !Objects.equals(this.backgroundurl, dataListBean.backgroundurl)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return Objects.hash(new Object[]{this.name, this.action, this.backgroundurl});
            }
        }
    }

    public String toString() {
        return "CommonResponseBean{code=" + this.code + ", data=" + this.data + ", message='" + this.message + '\'' + ", ttl=" + this.ttl + '}';
    }
}
