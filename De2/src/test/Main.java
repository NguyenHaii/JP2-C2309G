package test;

import thread.DayTranslator;
import thread.VietnameseDayThread;
import thread.EnglishDayThread;

public class Main {
    public static void main(String[] args) {
        DayTranslator translator = new DayTranslator();

        VietnameseDayThread vietnameseThread = new VietnameseDayThread(translator);
        EnglishDayThread englishThread = new EnglishDayThread(translator);

        vietnameseThread.start();
        englishThread.start();
    }
}
