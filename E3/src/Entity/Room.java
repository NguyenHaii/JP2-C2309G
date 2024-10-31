package Entity;

public class Room {
    private String id;
    private RoomType roomType;
    private double pricePerHour;

    public Room() {;}

    public Room(String id, RoomType roomType, double pricePerHour) {
        this.id = id;
        this.roomType = roomType;
        this.pricePerHour = pricePerHour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomType=" + roomType +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
