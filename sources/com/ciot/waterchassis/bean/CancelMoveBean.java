package com.ciot.waterchassis.bean;

public class CancelMoveBean {
    private boolean cancelMove;

    public boolean isCancelMove() {
        return this.cancelMove;
    }

    public void setCancelMove(boolean z) {
        this.cancelMove = z;
    }

    public String toString() {
        return "CancelMoveBean{cancelMove=" + this.cancelMove + '}';
    }
}
