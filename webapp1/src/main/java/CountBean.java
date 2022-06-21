import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "countBean", eager = true)
@SessionScoped
public class CountBean implements Serializable {
    private int number_of_points;
    private int number_of_red_points;

    public CountBean(){
        number_of_points = 0;
        number_of_red_points = 0;
    }

    public void add_point(){
        number_of_points++;
    }

    public void add_red_point(){
        number_of_red_points++;
    }

    public int getNumber_of_points() {
        return number_of_points;
    }

    public void setNumber_of_points(int number_of_points) {
        this.number_of_points = number_of_points;
    }

    public int getNumber_of_red_points() {
        return number_of_red_points;
    }

    public void setNumber_of_red_points(int number_of_red_points) {
        this.number_of_red_points = number_of_red_points;
    }
}
