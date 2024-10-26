package mc.csst.com.selfchassislibrary.navigation;

public class PositionTag {
    private String content = "";
    private String id;
    private String name;
    private float theta;
    private int type;
    private float x;
    private float y;
    private int z;

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int i) {
        this.z = i;
    }

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

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public float getTheta() {
        return this.theta;
    }

    public void setTheta(float f) {
        this.theta = f;
    }

    public String toString() {
        return "PositionTag{id='" + this.id + '\'' + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + ", z=" + this.z + ", name='" + this.name + '\'' + ", content='" + this.content + '\'' + '}';
    }
}
