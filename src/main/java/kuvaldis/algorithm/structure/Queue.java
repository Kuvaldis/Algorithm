package kuvaldis.algorithm.structure;

import java.util.Optional;

public class Queue<E> {

    private Element first;
    private Element last;
    private int size;

    public void enqueue(final E newItem) {
        final Element newElement = new Element(newItem);
        if (first == null) {
            first = newElement;
        }
        if (last != null) {
            last.setNext(newElement);
        }
        last = newElement;
        size++;
    }

    public E dequeue() {
        final Element oldFirst = first;
        Optional<E> optional = Optional.ofNullable(oldFirst).map(Element::getItem);
        if (optional.isPresent()) {
            first = first.getNext();
            size--;
        }
        if (first == null) {
            last = null;
        }
        return optional.orElse(null);
    }

    public int size() {
        return size;
    }

    public class Element {
        private final E item;
        private Element next;

        public Element(final E item) {
            this.item = item;
        }

        public E getItem() {
            return item;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(final Element next) {
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Element{");
            sb.append("item=").append(item);
            sb.append('}');
            return sb.toString();
        }
    }
}
