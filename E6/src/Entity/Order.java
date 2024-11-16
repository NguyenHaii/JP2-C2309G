package Entity;

import java.util.List;

public class Order {
    private int id;
    private List<OrderDetail> orderDetails;
    private List<Product> products;

    public Order(){;}

    public Order(int id, List<OrderDetail> orderDetails, List<Product> products) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
