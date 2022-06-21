import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(name = "countBean", eager = true)
@SessionScoped
public class CountBean implements Serializable {
    private int number_of_points;
    private int number_of_red_points;

    public CountBean(){
        number_of_points = 0;
        number_of_red_points = 0;
    }

    public void count(){
        System.out.println("посчитаем");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();

        if(params.get("R-field") != null){
            System.out.println(params.get("R-field"));
            double r = Double.parseDouble(params.get("R-field"));
        }
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
