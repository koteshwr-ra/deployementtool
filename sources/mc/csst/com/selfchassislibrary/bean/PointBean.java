package mc.csst.com.selfchassislibrary.bean;

public class PointBean {
    private String name;
    private float theta;
    private int type;
    private float x;
    private float y;

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

    public float getTheta() {
        return this.theta;
    }

    public void setTheta(float f) {
        this.theta = f;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "PointBean{x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + ", name='" + this.name + '\'' + ", type=" + this.type + '}';
    }
}
