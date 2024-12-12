package thread;

import java.util.Random;

public class VietnameseDayThread extends Thread {
    private final DayTranslator translator;

    public VietnameseDayThread(DayTranslator translator) {
        this.translator = translator;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int dayIndex = random.nextInt(7); // Generate random index for days
            translator.setVietnameseDay(dayIndex);
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
