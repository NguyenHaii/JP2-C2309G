package thread;

import data.Data;

public class ThreadOne extends Thread {
    private final SharedBuffer buffer;

    public ThreadOne(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (String str : Data.DATA) {
            synchronized (buffer) {
                buffer.setData(str.toUpperCase());
                buffer.notify();
                try {
                    buffer.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
