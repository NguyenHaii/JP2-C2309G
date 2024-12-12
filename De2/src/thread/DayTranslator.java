package thread;

public class DayTranslator {
    private String vietnameseDay;
    private boolean newDayAvailable = false;

    private static final String[] VIETNAMESE_DAYS = {
            "Chu nhat", "Thu hai", "Thu ba", "Thu tu", "Thu nam", "Thu sau", "Thu bay"
    };
    private static final String[] ENGLISH_DAYS = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    public synchronized void setVietnameseDay(int dayIndex) {
        while (newDayAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        vietnameseDay = VIETNAMESE_DAYS[dayIndex];
        System.out.println("Vietnamese: " + vietnameseDay);
        newDayAvailable = true;
        notify();
    }

    public synchronized void translateAndPrintEnglishDay() {
        while (!newDayAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int dayIndex = java.util.Arrays.asList(VIETNAMESE_DAYS).indexOf(vietnameseDay);
        System.out.println("English: " + ENGLISH_DAYS[dayIndex]);
        newDayAvailable = false;
        notify();
    }
}
