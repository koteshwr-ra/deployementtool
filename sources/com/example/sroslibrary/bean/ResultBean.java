package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResultBean implements Serializable {
    private static final long serialVersionUID = 792308382125084003L;
    @SerializedName("enctype")
    @Expose(deserialize = true, serialize = true)
    private int Enctype;
    @SerializedName("key")
    @Expose(deserialize = true, serialize = true)
    private int Key;
    @SerializedName("newversion")
    @Expose(deserialize = true, serialize = true)
    private int NewVerion;
    @SerializedName("reason")
    @Expose(deserialize = true, serialize = true)
    private String Reason;
    @SerializedName("result")
    @Expose(deserialize = true, serialize = true)
    private boolean Result;

    public boolean getResult() {
        return this.Result;
    }

    public void setResult(boolean z) {
        this.Result = z;
    }

    public String getReason() {
        return this.Reason;
    }

    public void setReason(String str) {
        this.Reason = str;
    }

    public int getEnctype() {
        return this.Enctype;
    }

    public void setEnctype(int i) {
        this.Enctype = i;
    }

    public int getKey() {
        return this.Key;
    }

    public void setKey(int i) {
        this.Key = i;
    }

    public int getNewVerion() {
        return this.NewVerion;
    }

    public void setNewVerion(int i) {
        this.NewVerion = i;
    }

    public String toString() {
        return "ResultBean{Result='" + this.Result + '\'' + ", Reason='" + this.Reason + '\'' + ", Enctype=" + this.Enctype + ", Key=" + this.Key + ", NewVerion=" + this.NewVerion + '}';
    }
}
