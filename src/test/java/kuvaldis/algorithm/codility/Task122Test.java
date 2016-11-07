package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task122Test {

    @Test
    public void testSolution() throws Exception {
        int[] a = new int[4900];
        int[] b = new int[4900];
        for (int i = 0; i < 70; i++) {
            for (int j = 0; j < 70; j++) {
                a[i + 70 * j] = i + 1;
                b[i + 70 * j] = j + 1;
            }
        }
//        System.out.println(new Task122().solution(a, b));
        a = new int[] {6059, 551};
        b = new int[] {442307, 303601};
        System.out.println(new Task122().solution(a, b));
    }
}