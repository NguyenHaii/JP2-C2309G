package test;

import thread.SharedBuffer;
import thread.ThreadOne;
import thread.ThreadTwo;

public class Test {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();
        ThreadOne threadOne = new ThreadOne(buffer);
        ThreadTwo threadTwo = new ThreadTwo(buffer);

        threadOne.start();
        threadTwo.start();
    }
}
