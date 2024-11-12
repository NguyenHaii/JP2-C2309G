package Entity;

import java.time.LocalDate;


public class ProductData {
    public int productId;
    public int clicks;
    public int addToCart;
    public int checkOut;
    public LocalDate dateTime;

    public ProductData(){;}

    public ProductData(int productId, int clicks, int addToCart, int checkOut, LocalDate dateTime) {
        this.productId = productId;
        this.clicks = clicks;
        this.addToCart = addToCart;
        this.checkOut = checkOut;
        this.dateTime = dateTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(int addToCart) {
        this.addToCart = addToCart;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "productId=" + productId +
                ", clicks=" + clicks +
                ", addToCart=" + addToCart +
                ", checkOut=" + checkOut +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

}
