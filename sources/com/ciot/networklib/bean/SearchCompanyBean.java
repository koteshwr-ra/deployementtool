package com.ciot.networklib.bean;

import java.util.List;

public class SearchCompanyBean {
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

        public static class DataListBean {
            private String action;
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
        }
    }
}
