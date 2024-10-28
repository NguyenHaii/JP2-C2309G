package Entity;

public class Department {
    private int id_Dep ;
    private  String name_Dep;

    public Department (){;}

    public Department(int id_Dep, String name_Dep) {
        this.id_Dep = id_Dep;
        this.name_Dep = name_Dep;
    }

    public int getId_Dep() {
        return id_Dep;
    }

    public void setId_Dep(int id_Dep) {
        this.id_Dep = id_Dep;
    }

    public String getName_Dep() {
        return name_Dep;
    }

    public void setName_Dep(String name_Dep) {
        this.name_Dep = name_Dep;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id_Dep=" + id_Dep +
                ", name_Dep='" + name_Dep + '\'' +
                '}';
    }
}
