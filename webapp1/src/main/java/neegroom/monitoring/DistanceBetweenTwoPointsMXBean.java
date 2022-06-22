package neegroom.monitoring;

import neegroom.domain.Point;

public interface DistanceBetweenTwoPointsMXBean {
    double calculateDistanceBetweenTwoLastPoints();
    void setLastPoint(Point point);
}
