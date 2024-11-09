package Entity;

public class Customer {
    private int id;
    private String code;
    private String name;

    public Customer(){;}

    public Customer(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(this.getId())
                .append(separator)
                .append(this.getCode())
                .append(separator)
                .append(this.getName())
                .toString();
    }
}
