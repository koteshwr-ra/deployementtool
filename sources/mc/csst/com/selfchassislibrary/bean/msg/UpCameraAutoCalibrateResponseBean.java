package mc.csst.com.selfchassislibrary.bean.msg;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class UpCameraAutoCalibrateResponseBean implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("info")
    private String info;
    @SerializedName("op")
    private String op;
    @SerializedName("result")
    private Boolean result;
    @SerializedName("service")
    private String service;
    @SerializedName("values")
    private ValuesDTO values;

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

    public ValuesDTO getValues() {
        return this.values;
    }

    public void setValues(ValuesDTO valuesDTO) {
        this.values = valuesDTO;
    }

    public Boolean isResult() {
        return this.result;
    }

    public void setResult(Boolean bool) {
        this.result = bool;
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

    public static class ValuesDTO implements Serializable {
        @SerializedName("features_resp")
        private List<FeaturesRespDTO> featuresResp;
        @SerializedName("info")
        private String info;
        @SerializedName("result")
        private Integer result;

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public Integer getResult() {
            return this.result;
        }

        public void setResult(Integer num) {
            this.result = num;
        }

        public List<FeaturesRespDTO> getFeaturesResp() {
            return this.featuresResp;
        }

        public void setFeaturesResp(List<FeaturesRespDTO> list) {
            this.featuresResp = list;
        }

        public static class FeaturesRespDTO implements Serializable {
            @SerializedName("enable")
            private Boolean enable;
            @SerializedName("info")
            private String info;
            @SerializedName("name")
            private String name;
            @SerializedName("value")
            private Integer value;
            @SerializedName("value_ext")
            private Double valueExt;

            public String getInfo() {
                return this.info;
            }

            public void setInfo(String str) {
                this.info = str;
            }

            public Double getValueExt() {
                return this.valueExt;
            }

            public void setValueExt(Double d) {
                this.valueExt = d;
            }

            public Boolean isEnable() {
                return this.enable;
            }

            public void setEnable(Boolean bool) {
                this.enable = bool;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public Integer getValue() {
                return this.value;
            }

            public void setValue(Integer num) {
                this.value = num;
            }
        }

        public String toString() {
            return "ValuesDTO{info='" + this.info + '\'' + ", result=" + this.result + ", featuresResp=" + this.featuresResp + '}';
        }
    }
}
