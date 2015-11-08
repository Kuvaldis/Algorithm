package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static kuvaldis.algorithm.interview.arraysstrings.Node.fromList;
import static kuvaldis.algorithm.interview.arraysstrings.Node.toList;
import static org.junit.Assert.*;

public class Task21Test {

    @Test
    public void testRemoveDuplicates() throws Exception {
        final Task21 t = new Task21();
        assertEquals(singletonList(1), toList(t.removeDuplicates(fromList(singletonList(1)))));
        assertEquals(asList(1, 2), toList(t.removeDuplicates(fromList(asList(1, 2)))));
        assertEquals(asList(1, 2), toList(t.removeDuplicates(fromList(asList(1, 2, 1)))));
        assertEquals(asList(1, 2), toList(t.removeDuplicates(fromList(asList(1, 2, 1, 2, 1)))));
        assertEquals(asList(1, 2), toList(t.removeDuplicates(fromList(asList(1, 1, 1, 2, 2)))));
    }
}