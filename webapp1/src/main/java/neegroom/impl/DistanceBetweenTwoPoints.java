package neegroom.impl;

import lombok.extern.slf4j.Slf4j;
import neegroom.domain.Point;
import neegroom.monitoring.DistanceBetweenTwoPointsMXBean;

@Slf4j
public class DistanceBetweenTwoPoints implements DistanceBetweenTwoPointsMXBean {

    private static final DistanceBetweenTwoPoints distanceBetweenTwoPoints = new DistanceBetweenTwoPoints();

    private Point lastPoint;
    private Point preLastPoint;

    private double distance;

    public static DistanceBetweenTwoPoints getInstance(){
        return distanceBetweenTwoPoints;
    }

    @Override
    public double calculateDistanceBetweenTwoLastPoints() {
        if(lastPoint != null && preLastPoint != null){
            distance = (lastPoint.getX() - preLastPoint.getX()) * (lastPoint.getX() - preLastPoint.getX()) -
                    (lastPoint.getY() - preLastPoint.getY()) * (lastPoint.getY() - preLastPoint.getY());
        }
        return distance;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point point) {
        this.preLastPoint = this.lastPoint;
        this.lastPoint = point;
        System.out.println("Точенька: " + point);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPreLastPoint(Point preLastPoint) {
        this.preLastPoint = preLastPoint;
    }

    public Point getPreLastPoint() {
        return preLastPoint;
    }
}
