package kuvaldis.algorithm.programcreek;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

Given a singly linked list L: L0→L1→ ... →Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...

For example, given {1,2,3,4}, reorder it to {1,4,2,3}. You must do this in-place without altering the nodes' values.

 */
public class ReorderList {

    public static class ListNode {
        
        public int value;

        public ListNode next;

        @Override
        public String toString() {
            ListNode node = this;
            final Stream.Builder<String> builder = Stream.builder();
            while (node != null) {
                builder.accept(String.valueOf(node.value));
                node = node.next;
            }
            return builder.build().collect(Collectors.joining(", "));
        }
    }

    public ListNode reorderList(final ListNode list) {
        if (list == null || list.next == null) {
            return list;
        }

        // we need to find middle element
        ListNode secondList = list;
        ListNode tmp = list;
        while (tmp != null) {
            secondList = secondList.next;
            tmp = tmp.next;
            if (tmp != null) {
                tmp = tmp.next;
            }
        }

        // revert second list
        secondList = revertList(secondList);

        // merge lists
        ListNode firstList = list;
        // secondList is either of the same length or shorter
        while (secondList != null) {
            tmp = firstList;
            firstList = firstList.next;
            tmp.next = secondList;
            tmp = secondList;
            secondList = secondList.next;
            tmp.next = firstList;
            if (secondList == null) {
                firstList.next = null;
            }
        }
        return list;
    }

    private ListNode revertList(ListNode list) {
        ListNode newList = null;
        ListNode tmp;
        while (list != null) {
            tmp = newList;
            newList = list;
            list = list.next;
            newList.next = tmp;
        }
        return newList;
    }


}
