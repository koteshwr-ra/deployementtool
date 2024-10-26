package com.ciot.networklib.bean.patrol;

public class PatrolPathBean {
    private PatrolPointBean end;
    private PatrolRouteBean path;
    private PatrolPointBean start;

    public PatrolPointBean getStart() {
        return this.start;
    }

    public void setStart(PatrolPointBean patrolPointBean) {
        this.start = patrolPointBean;
    }

    public PatrolPointBean getEnd() {
        return this.end;
    }

    public void setEnd(PatrolPointBean patrolPointBean) {
        this.end = patrolPointBean;
    }

    public PatrolRouteBean getPath() {
        return this.path;
    }

    public void setPath(PatrolRouteBean patrolRouteBean) {
        this.path = patrolRouteBean;
    }
}
