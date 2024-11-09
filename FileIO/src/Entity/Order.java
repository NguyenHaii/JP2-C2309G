package Entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerid;
    private LocalDateTime dateTime;

    public Order(){;}

    public Order(int id, int customerid, LocalDateTime dateTime) {
        this.id = id;
        this.customerid = customerid;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
