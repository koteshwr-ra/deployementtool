package mc.csst.com.selfchassis.utils.view.map.area.bean;

import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;

public class LineSegment {
    private AreaItemBean.PolygonBean.PointsBean lastPoint;
    private AreaItemBean.PolygonBean.PointsBean prePoint;

    public LineSegment(AreaItemBean.PolygonBean.PointsBean pointsBean, AreaItemBean.PolygonBean.PointsBean pointsBean2) {
        this.prePoint = pointsBean;
        this.lastPoint = pointsBean2;
    }

    public AreaItemBean.PolygonBean.PointsBean getPrePoint() {
        return this.prePoint;
    }

    public void setPrePoint(AreaItemBean.PolygonBean.PointsBean pointsBean) {
        this.prePoint = pointsBean;
    }

    public AreaItemBean.PolygonBean.PointsBean getLastPoint() {
        return this.lastPoint;
    }

    public void setLastPoint(AreaItemBean.PolygonBean.PointsBean pointsBean) {
        this.lastPoint = pointsBean;
    }
}
