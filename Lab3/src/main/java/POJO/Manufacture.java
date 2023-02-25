package POJO;


import jakarta.persistence.*;



@Entity
@Table(name = "Manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_manu;
    private String name, location;
    private int employee;

    public Manufacture(){
        super();
    }
    public Manufacture(int id_manu, String name, String location, int employee) {
        this.id_manu = id_manu;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public int getId() {
        return id_manu;
    }

    public int getEmployee() {
        return employee;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id_manu) {
        this.id_manu = id_manu;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String toString(){
        return "Manufacture{" + getId() + ", " + getName() + ", " + getLocation() + ", " + getEmployee() + "}";
    }

}
