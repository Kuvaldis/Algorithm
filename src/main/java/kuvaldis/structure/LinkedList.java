package kuvaldis.structure;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LinkedList<E> {

    private Element first;
    private Element last;
    private int size = 0;

    public boolean add(final E item) {
        final Element element = new Element(item);
        if (first == null) {
            first = element;
            last = element;
        } else {
            last.next = element;
            element.prev = last;
            last = element;
        }
        size++;
        return true;
    }

    public int search(final E item) {
        return -1;
    }

    public int delete(final E item) {
        return -1;
    }

    public int size() {
        return size;
    }

    public E[] toArray() {
        //noinspection unchecked
        return (E[]) Stream.iterate(first, element -> element.next)
                .limit(size)
                .map(element -> element.item)
                .toArray();
    }

    @Data
    private class Element {
        private final E item;
        private Element next;
        private Element prev;
    }
}
