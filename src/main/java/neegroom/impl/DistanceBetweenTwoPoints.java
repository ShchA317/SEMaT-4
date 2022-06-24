package neegroom.impl;

import lombok.extern.slf4j.Slf4j;
import neegroom.monitoring.DistanceBetweenTwoPointsMXBean;

import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Slf4j
public class DistanceBetweenTwoPoints extends NotificationBroadcasterSupport implements DistanceBetweenTwoPointsMXBean, Serializable {

    private static final DistanceBetweenTwoPoints distanceBetweenTwoPoints = new DistanceBetweenTwoPoints();

    private double lastPointX;

    public double getLastPointX() {
        return lastPointX;
    }

    public double getLastPointY() {
        return lastPointY;
    }

    public double getPreLastPointX() {
        return preLastPointX;
    }

    public double getPreLastPointY() {
        return preLastPointY;
    }

    private double lastPointY;

    private double preLastPointX;
    private double preLastPointY;

    private double distance;

    public static DistanceBetweenTwoPoints getInstance(){
        return distanceBetweenTwoPoints;
    }

    @Override
    public void calculateDistanceBetweenTwoLastPoints() {
        distance = Math.sqrt((lastPointX - preLastPointX) * (lastPointX - preLastPointX) +
            (lastPointY - preLastPointY) * (lastPointY - preLastPointY));
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPreLastPointX(double preLastPointX) {
        this.preLastPointX = preLastPointX;
    }

    public void setPreLastPointY(double preLastPointY) {
        this.preLastPointY = preLastPointY;
    }

    public void setLastPointX(double lastPointX) {
        this.preLastPointX = this.lastPointX;
        this.lastPointX = lastPointX;
    }

    public void setLastPointY(double lastPointY) {
        this.preLastPointY = this.lastPointY;
        this.lastPointY = lastPointY;
    }
}
