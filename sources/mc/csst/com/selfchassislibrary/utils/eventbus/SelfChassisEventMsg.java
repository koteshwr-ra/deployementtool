package mc.csst.com.selfchassislibrary.utils.eventbus;

public class SelfChassisEventMsg<T> {
    public static final String CODE_CONNECT_STATE = "connectstate";
    private String code;
    private T data;

    public SelfChassisEventMsg(String str) {
        this.code = str;
    }

    public SelfChassisEventMsg(String str, T t) {
        this.code = str;
        this.data = t;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "SelfChassisEventMsg{code='" + this.code + '\'' + ", data=" + this.data + '}';
    }
}
