package thread;

public class SharedBuffer {
    private String data;

    public synchronized String getData() {
        return data;
    }

    public synchronized void setData(String data) {
        this.data = data;
    }
}
