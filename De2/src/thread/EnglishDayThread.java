package thread;

public class EnglishDayThread extends Thread {
    private final DayTranslator translator;

    public EnglishDayThread(DayTranslator translator) {
        this.translator = translator;
    }

    @Override
    public void run() {
        while (true) {
            translator.translateAndPrintEnglishDay();
        }
    }
}
