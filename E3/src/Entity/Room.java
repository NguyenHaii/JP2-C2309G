
package Entity;
import E3.Entity.RoomType;


public class Room {
    private int id;
    private String codeRoom;
    private RoomType roomType;
    private double pricePerHour;

    public Room() {
    }

    public Room(int id, String codeRoom,RoomType roomType, double pricePerHour) {
        this.id = id;
        this.codeRoom = codeRoom;
        this.roomType = roomType;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeRoom() {
        return codeRoom;
    }

    public void setCodeRoom(String codeRoom) {
        this.codeRoom = codeRoom;
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
                "id=" + id +
                ", codeRoom='" + codeRoom + '\'' +
                ", roomType=" + roomType +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
