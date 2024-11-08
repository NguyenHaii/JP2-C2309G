package E3.Entity;

public enum RoomType {
    SINGLE("Single Room"),
    DOUBLE("Double Room"),
    QUEEN("Queen Room"),
    TRIPLE("Triple Room"),
    QUAD("Quad Room");

    private final String label;

    RoomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}