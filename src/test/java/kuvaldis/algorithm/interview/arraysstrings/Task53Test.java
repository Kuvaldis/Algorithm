package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

public class Task53Test {

    @Test
    public void testPrintNexts() throws Exception {
        final Task53 t = new Task53();
        final byte val = 7;
        printBinary(val);
        printBinary(t.nextLargest(val));
        printBinary(t.nextSmallest(val));
    }

    private void printBinary(final int i) {
        System.out.println(String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0'));
    }
}