package mc.csst.com.selfchassis.utils.bean;

public class LayerBean {
    private String id;
    private String name;
    private String rootId;
    private int selectStatus;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getSelectStatus() {
        return this.selectStatus;
    }

    public void setSelectStatus(int i) {
        this.selectStatus = i;
    }

    public String getRootId() {
        return this.rootId;
    }

    public void setRootId(String str) {
        this.rootId = str;
    }
}
