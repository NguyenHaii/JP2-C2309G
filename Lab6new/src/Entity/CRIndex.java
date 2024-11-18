package Entity;

public class CRIndex {
    private int id;
    private int month;
    private int year;
    private double crViews;
    private double crAddToCart;
    private double crCheckOut;

    public CRIndex() {
    }

    public CRIndex(int id, int month, int year, double crViews, double crAddToCart, double crCheckOut) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.crViews = crViews;
        this.crAddToCart = crAddToCart;
        this.crCheckOut = crCheckOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCrViews() {
        return crViews;
    }

    public void setCrViews(double crViews) {
        this.crViews = crViews;
    }

    public double getCrAddToCart() {
        return crAddToCart;
    }

    public void setCrAddToCart(double crAddToCart) {
        this.crAddToCart = crAddToCart;
    }

    public double getCrCheckOut() {
        return crCheckOut;
    }

    public void setCrCheckOut(double crCheckOut) {
        this.crCheckOut = crCheckOut;
    }

    @Override
    public String toString() {
        return "CRIndex{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                ", crViews=" + crViews +
                ", crAddToCart=" + crAddToCart +
                ", crCheckOut=" + crCheckOut +
                '}';
    }
}
