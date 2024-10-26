package mc.csst.com.selfchassis.utils.view.map.area;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import mc.csst.com.selfchassis.utils.view.map.area.bean.LineSegment;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;

public class AreaUtils {
    public static void main(String[] strArr) {
        int i = 0;
        while (i < 3) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < 3; i3++) {
                PrintStream printStream = System.out;
                printStream.println("i:" + i + " j:" + i3);
            }
            i = i2;
        }
    }

    public static boolean intersectionJudgment(List<AreaItemBean.PolygonBean.PointsBean> list, List<AreaItemBean.PolygonBean.PointsBean> list2) {
        if (isExistNullOrEmpty(list, list2) || !fastExclude(list, list2)) {
            return false;
        }
        List<LineSegment> lineSegment = getLineSegment(list);
        List<LineSegment> lineSegment2 = getLineSegment(list2);
        if (isExistNullOrEmpty(lineSegment, lineSegment2)) {
            return false;
        }
        if (!crossJudgment(lineSegment, lineSegment2) && !includeRelation(list, lineSegment2)) {
            return includeRelation(list2, lineSegment);
        }
        return true;
    }

    private static boolean fastExclude(List<AreaItemBean.PolygonBean.PointsBean> list, List<AreaItemBean.PolygonBean.PointsBean> list2) {
        List<AreaItemBean.PolygonBean.PointsBean> list3 = list;
        List<AreaItemBean.PolygonBean.PointsBean> list4 = list2;
        double x = list3.get(0).getX();
        double x2 = list3.get(0).getX();
        double y = list3.get(0).getY();
        double y2 = list3.get(0).getY();
        for (AreaItemBean.PolygonBean.PointsBean next : list) {
            x = Math.max(x, next.getX());
            x2 = Math.min(x2, next.getX());
            y = Math.max(y, next.getY());
            y2 = Math.min(y2, next.getY());
        }
        double x3 = list4.get(0).getX();
        double x4 = list4.get(0).getX();
        double y3 = list4.get(0).getY();
        double y4 = list4.get(0).getY();
        Iterator<AreaItemBean.PolygonBean.PointsBean> it = list2.iterator();
        double d = y4;
        double d2 = y3;
        double d3 = x;
        while (it.hasNext()) {
            AreaItemBean.PolygonBean.PointsBean next2 = it.next();
            x3 = Math.max(x3, next2.getX());
            x4 = Math.min(x4, next2.getX());
            d2 = Math.max(d2, next2.getY());
            d = Math.min(d, next2.getY());
            it = it;
            y2 = y2;
        }
        return d3 >= x4 && x2 <= x3 && y >= d && y2 <= d2;
    }

    public static List<LineSegment> getLineSegment(List<AreaItemBean.PolygonBean.PointsBean> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size() - 1) {
            i++;
            arrayList.add(new LineSegment(list.get(i), list.get(i)));
        }
        if (arrayList.size() > 0) {
            arrayList.add(new LineSegment(list.get(list.size() - 1), list.get(0)));
        }
        return arrayList;
    }

    public static List<LineSegment> getLineSegmentOther(List<AreaItemBean.PolygonBean.PointsBean> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size() - 1) {
            i++;
            arrayList.add(new LineSegment(list.get(i), list.get(i)));
        }
        return arrayList;
    }

    private static boolean crossJudgment(List<LineSegment> list, List<LineSegment> list2) {
        for (LineSegment next : list) {
            Iterator<LineSegment> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (calculationLineSegmentCrossing(next, it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean crossJudgment(List<LineSegment> list) {
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (!isSamplePoint(list.get(i).getPrePoint(), list.get(i3).getPrePoint()) && !isSamplePoint(list.get(i).getPrePoint(), list.get(i3).getLastPoint()) && !isSamplePoint(list.get(i).getLastPoint(), list.get(i3).getPrePoint()) && !isSamplePoint(list.get(i).getLastPoint(), list.get(i3).getLastPoint()) && calculationLineSegmentCrossing(list.get(i), list.get(i3))) {
                    return true;
                }
            }
            i = i2;
        }
        return false;
    }

    private static boolean calculationLineSegmentCrossing(LineSegment lineSegment, LineSegment lineSegment2) {
        if (isPointOnline(lineSegment, lineSegment2)) {
            return true;
        }
        AreaItemBean.PolygonBean.PointsBean prePoint = lineSegment.getPrePoint();
        AreaItemBean.PolygonBean.PointsBean lastPoint = lineSegment.getLastPoint();
        AreaItemBean.PolygonBean.PointsBean prePoint2 = lineSegment2.getPrePoint();
        AreaItemBean.PolygonBean.PointsBean lastPoint2 = lineSegment2.getLastPoint();
        double crossProduct = crossProduct(prePoint, lastPoint, prePoint, prePoint2);
        double crossProduct2 = crossProduct(prePoint, lastPoint, prePoint, lastPoint2);
        double crossProduct3 = crossProduct(prePoint2, lastPoint2, prePoint2, prePoint);
        double crossProduct4 = crossProduct(prePoint2, lastPoint2, prePoint2, lastPoint);
        if (crossProduct * crossProduct2 >= 0.0d || crossProduct3 * crossProduct4 >= 0.0d) {
            return false;
        }
        return true;
    }

    private static boolean isPointOnline(LineSegment lineSegment, LineSegment lineSegment2) {
        return isExistTrue(new boolean[]{isCollinearIntersection(lineSegment.getPrePoint(), lineSegment2), isCollinearIntersection(lineSegment.getLastPoint(), lineSegment2), isCollinearIntersection(lineSegment2.getPrePoint(), lineSegment), isCollinearIntersection(lineSegment2.getLastPoint(), lineSegment)});
    }

    private static boolean isCollinearIntersection(AreaItemBean.PolygonBean.PointsBean pointsBean, AreaItemBean.PolygonBean.PointsBean pointsBean2, AreaItemBean.PolygonBean.PointsBean pointsBean3) {
        if (pointsBean.getX() < Math.min(pointsBean2.getX(), pointsBean3.getX()) || pointsBean.getX() > Math.max(pointsBean2.getX(), pointsBean3.getX()) || pointsBean.getY() < Math.min(pointsBean2.getY(), pointsBean3.getY()) || pointsBean.getY() > Math.max(pointsBean2.getY(), pointsBean3.getY()) || crossProduct(pointsBean, pointsBean2, pointsBean, pointsBean3) != 0.0d) {
            return false;
        }
        return true;
    }

    private static boolean isSamplePoint(AreaItemBean.PolygonBean.PointsBean pointsBean, AreaItemBean.PolygonBean.PointsBean pointsBean2) {
        return pointsBean.getX() == pointsBean2.getX() && pointsBean.getY() == pointsBean2.getY();
    }

    private static boolean isCollinearIntersection(AreaItemBean.PolygonBean.PointsBean pointsBean, LineSegment lineSegment) {
        return isCollinearIntersection(pointsBean, lineSegment.getPrePoint(), lineSegment.getLastPoint());
    }

    private static boolean includeRelation(List<AreaItemBean.PolygonBean.PointsBean> list, List<LineSegment> list2) {
        for (AreaItemBean.PolygonBean.PointsBean pointInPolygon : list) {
            if (!pointInPolygon(pointInPolygon, list2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean pointInPolygon(AreaItemBean.PolygonBean.PointsBean pointsBean, List<LineSegment> list) {
        double x = pointsBean.getX();
        double y = pointsBean.getY();
        int i = 0;
        for (LineSegment next : list) {
            if (isCollinearIntersection(pointsBean, next.getPrePoint(), next.getLastPoint())) {
                return true;
            }
            double max = Math.max(next.getPrePoint().getY(), next.getLastPoint().getY());
            if (y >= Math.min(next.getPrePoint().getY(), next.getLastPoint().getY()) && y < max && x > (((y - next.getPrePoint().getY()) * (next.getLastPoint().getX() - next.getPrePoint().getX())) / (next.getLastPoint().getY() - next.getPrePoint().getY())) + next.getPrePoint().getX()) {
                i++;
            }
        }
        if (i % 2 != 0) {
            return true;
        }
        return false;
    }

    private static double crossProduct(AreaItemBean.PolygonBean.PointsBean pointsBean, AreaItemBean.PolygonBean.PointsBean pointsBean2, AreaItemBean.PolygonBean.PointsBean pointsBean3, AreaItemBean.PolygonBean.PointsBean pointsBean4) {
        double x = pointsBean2.getX() - pointsBean.getX();
        double y = pointsBean2.getY() - pointsBean.getY();
        return (x * (pointsBean4.getY() - pointsBean3.getY())) - ((pointsBean4.getX() - pointsBean3.getX()) * y);
    }

    private static boolean isExistNull(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    private static boolean isExistNullOrEmpty(Collection<?>... collectionArr) {
        for (Collection<?> collection : collectionArr) {
            if (collection == null || collection.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isExistTrue(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCoordinatePoint(AreaItemBean.PolygonBean.PointsBean pointsBean, List<AreaItemBean.PolygonBean.PointsBean> list) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            AreaItemBean.PolygonBean.PointsBean pointsBean2 = list.get(i);
            i++;
            AreaItemBean.PolygonBean.PointsBean pointsBean3 = list.get(i % list.size());
            if (pointsBean2.getY() != pointsBean3.getY() && pointsBean.getY() >= Math.min(pointsBean2.getY(), pointsBean3.getY()) && pointsBean.getY() < Math.max(pointsBean2.getY(), pointsBean3.getY()) && (((pointsBean.getY() - pointsBean2.getY()) * (pointsBean3.getX() - pointsBean2.getX())) / (pointsBean3.getY() - pointsBean2.getY())) + pointsBean2.getX() > pointsBean.getX()) {
                i2++;
            }
        }
        if (i2 % 2 == 1) {
            return true;
        }
        return false;
    }

    public static boolean isOnAreaOriginPoint(List<AreaItemBean.PolygonBean.PointsBean> list, float f) {
        if (isExistNullOrEmpty(list)) {
            return false;
        }
        double x = list.get(0).getX();
        double y = list.get(0).getY();
        double x2 = list.get(list.size() - 1).getX();
        double y2 = list.get(list.size() - 1).getY();
        double d = (double) f;
        boolean z = x2 <= x + d || x2 >= x - d;
        boolean z2 = y2 <= y + d || y2 >= y - d;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public static double getPointExtremum(List<AreaItemBean.PolygonBean.PointsBean> list, boolean z, boolean z2) {
        double d = Double.MIN_VALUE;
        double d2 = Double.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            double x = list.get(i).getX();
            double y = list.get(i).getY();
            if (z) {
                if (x > d) {
                    d = x;
                }
                if (x < d2) {
                    d2 = x;
                }
            } else {
                if (y > d) {
                    d = y;
                }
                if (y < d2) {
                    d2 = y;
                }
            }
        }
        return z2 ? d : d2;
    }
}
