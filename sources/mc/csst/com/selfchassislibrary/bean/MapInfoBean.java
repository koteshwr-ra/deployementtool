package mc.csst.com.selfchassislibrary.bean;

public class MapInfoBean {
    private int h;
    private float r;
    private int w;
    private float x;
    private float y;

    public int getH() {
        return this.h;
    }

    public void setH(int i) {
        this.h = i;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int i) {
        this.w = i;
    }

    public float getR() {
        return this.r;
    }

    public void setR(float f) {
        this.r = f;
    }

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

    public String toString() {
        return "MapInfoBean{h=" + this.h + ", w=" + this.w + ", r=" + this.r + ", x=" + this.x + ", y=" + this.y + '}';
    }
}
