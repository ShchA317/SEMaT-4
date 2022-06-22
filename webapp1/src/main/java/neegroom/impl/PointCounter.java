package neegroom.impl;

import lombok.extern.slf4j.Slf4j;
import neegroom.monitoring.PointCounterMXBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Slf4j
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMXBean {
    private int sequenceNumber = 0;
    private int count = 0;
    private int unHitCount = 0;

    private static final PointCounter pointCounter = new PointCounter();

    public static PointCounter getInstance() {
        return pointCounter;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getUnHitCount() {
        return unHitCount;
    }

    @Override
    public void check(boolean result, double x, double y, double r) {
        count++;
        if (!result) unHitCount++;
        if (Math.abs(x) > r * 1.2 || Math.abs(y) > r * 1.2) {
            sendNotification(new Notification("multiple_of_10", this, sequenceNumber++, System.currentTimeMillis(), "The number of points is " + count));
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHitCount(int hitCount) {
        this.unHitCount = hitCount;
    }
}