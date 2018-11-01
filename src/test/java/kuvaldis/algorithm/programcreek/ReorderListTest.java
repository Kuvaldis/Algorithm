package kuvaldis.algorithm.programcreek;

import org.junit.Test;

public class ReorderListTest {

    @Test
    public void simpleTest() {
        final ReorderList.ListNode list = createList(4);
        final ReorderList.ListNode result = new ReorderList().reorderList(list);
        System.out.println(result);
    }

    @Test
    public void simpleTest2() {
        final ReorderList.ListNode list = createList(7);
        final ReorderList.ListNode result = new ReorderList().reorderList(list);
        System.out.println(result);
    }

    private static ReorderList.ListNode createList(final int nodeNumber) {
        ReorderList.ListNode result = null;
        for (int i = 0; i < nodeNumber; i++) {
            final ReorderList.ListNode node = new ReorderList.ListNode();
            node.value = nodeNumber - i;
            node.next = result;
            result = node;
        }
        return result;
    }
}