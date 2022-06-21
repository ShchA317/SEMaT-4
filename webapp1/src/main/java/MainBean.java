    import domain.Point;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ManagedBean(name = "mainBean", eager = true)
@SessionScoped
public class MainBean implements Serializable {
    private static final long serialVersionUID = 4L;
    private EntityManagerFactory managerFactory;

    @ManagedProperty(value="#{countBean}")
    private CountBean countBean;

    private EntityManager manager;
    private List<Point> points = new ArrayList<>();

  //  @ManagedProperty(value = "#{validate}")
    public void validate() {
        System.out.println("пришли данные на валидацию");
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            managerFactory = Persistence.createEntityManagerFactory("db_con");
            manager = managerFactory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
            Point point;

            if((params.get("X-field") != null && params.get("Y-field") != null && params.get("R-field") != null)) {
                point = new Point(facesContext.getExternalContext().getSessionId(true), Double.parseDouble(params.get("X-field")),
                        Double.parseDouble(params.get("Y-field")), Double.parseDouble(params.get("R-field")));
                System.out.println("Пришла точка: " + point);
                transaction.begin();
                manager.persist(point);
                points.add(point);
                transaction.commit();

                if(countBean != null){
                    countBean.add_point();
                    if(!point.isCoordsStatus()){
                        countBean.add_red_point();
                    }
                    System.out.println("всего точек в этой сессии: " + countBean.getNumber_of_points());
                    System.out.println("красных точек в этой сессии: " + countBean.getNumber_of_red_points());
                } else {
                    System.out.println("почему он null?");
                }

                transaction.begin();
                TypedQuery<Point> query = manager.createQuery("SELECT p FROM Point p WHERE p.owner = :owner", Point.class);
                points = query.setParameter("owner", facesContext.getExternalContext().getSessionId(true)).getResultList();
                transaction.commit();
            } else {
                System.out.println("Пришел null");
                if(params.get("X-field") == null) System.out.println("X-field = null");
                if(params.get("Y-field") == null) System.out.println("Y-field = null");
                if(params.get("R-field") == null) System.out.println("R-field = null");
            }

        } finally {
            if(manager != null)
                manager.close();
            if(managerFactory != null)
                managerFactory.close();
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainBean)) return false;
        MainBean that = (MainBean) o;
        return Objects.equals(managerFactory, that.managerFactory) &&
                Objects.equals(manager, that.manager) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerFactory, manager, points);
    }

    @Override
    public String toString() {
        return "mainBean{" +
                "managerFactory=" + managerFactory +
                ", manager=" + manager +
                ", points=" + points +
                '}';
    }

    public CountBean getCountBean() {
        return countBean;
    }

    public void setCountBean(CountBean countBean) {
        this.countBean = countBean;
    }
}
