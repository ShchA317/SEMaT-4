package monitoring;

public interface PointCounterMXBean {
    void check(boolean result);

    int getHitCount();

    int getCount();
}
