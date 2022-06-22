package neegroom.monitoring;

public interface PointCounterMXBean {
    void check(boolean result);

    int getUnHitCount();

    int getCount();
}
