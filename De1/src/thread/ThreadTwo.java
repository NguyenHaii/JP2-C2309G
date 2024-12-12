package thread;

public class ThreadTwo extends Thread {
    private final SharedBuffer buffer;

    public ThreadTwo(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        synchronized (buffer) {
            while (true) {
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(buffer.getData());
                buffer.notify();
            }
        }
    }
}
