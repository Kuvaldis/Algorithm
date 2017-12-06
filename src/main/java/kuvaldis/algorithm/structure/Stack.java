package kuvaldis.algorithm.structure;

import java.util.Optional;

public class Stack<E> {

    private Element top;
    private int size;

    public void push(final E newItem) {
        final Element newElement = new Element(newItem);
        if (top != null) {
            newElement.setNext(top);
        }
        top = newElement;
        size++;
    }

    public E pop() {
        final Element oldTop = top;
        Optional<E> optional = Optional.ofNullable(oldTop).map(Element::getItem);
        if (optional.isPresent()) {
            top = top.getNext();
            size--;
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
