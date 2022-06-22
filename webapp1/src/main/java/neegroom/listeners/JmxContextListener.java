package neegroom.listeners;

import lombok.extern.slf4j.Slf4j;
import neegroom.impl.DistanceBetweenTwoPoints;
import neegroom.impl.PointCounter;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

@Slf4j
public class JmxContextListener implements ServletContextListener {
    private static final String pointCounterName = "neegroom.monitoring.impl:type=mbean,name=PointCounter";
    private static final String distanceBetweenTwoPointsName = "neegroom.monitoring.impl:type=mbean,name=DistanceBetweenTwoPoints";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCounterObjectName = new ObjectName(pointCounterName);
            PointCounter pointCounter = PointCounter.getInstance();
            mBeanServer.registerMBean(pointCounter, pointCounterObjectName);

            ObjectName distanceBetweenTwoPointsObjectName = new ObjectName(distanceBetweenTwoPointsName);
            DistanceBetweenTwoPoints distanceBetweenTwoPoints = DistanceBetweenTwoPoints.getInstance();
            mBeanServer.registerMBean(distanceBetweenTwoPoints, distanceBetweenTwoPointsObjectName);

            MXBeanNotificationListener pointCounterListener = new MXBeanNotificationListener("multiplicity");
            mBeanServer.addNotificationListener(pointCounterObjectName, pointCounterListener, pointCounterListener.getNotificationFilter(), null);
        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCounterObjectName = new ObjectName(pointCounterName);
            mBeanServer.unregisterMBean(pointCounterObjectName);

            ObjectName missPercentageObjectName = new ObjectName(distanceBetweenTwoPointsName);
            mBeanServer.unregisterMBean(missPercentageObjectName);

        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }
}
