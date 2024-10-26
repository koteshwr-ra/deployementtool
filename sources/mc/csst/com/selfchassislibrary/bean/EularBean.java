package mc.csst.com.selfchassislibrary.bean;

public class EularBean {
    private float p;
    private float r;
    private float y;

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getP() {
        return this.p;
    }

    public void setP(float f) {
        this.p = f;
    }

    public float getR() {
        return this.r;
    }

    public void setR(float f) {
        this.r = f;
    }

    public String toString() {
        return "EularBean{y=" + this.y + ", p=" + this.p + ", r=" + this.r + '}';
    }
}
