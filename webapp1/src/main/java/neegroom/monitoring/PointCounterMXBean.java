package neegroom.monitoring;

public interface PointCounterMXBean {
    void check(boolean result, double x, double y, double r);

    int getUnHitCount();

    int getCount();
}
