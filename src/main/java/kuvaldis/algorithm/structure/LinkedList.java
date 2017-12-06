package kuvaldis.algorithm.structure;

import java.util.Optional;
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

    public int index(final E item) {
        return Stream.iterate(new IterationElement(0, first),
                iteration -> new IterationElement(iteration.i + 1, iteration.element.next))
                .limit(size)
                .filter(iteration -> iteration.element.item.equals(item))
                .mapToInt(IterationElement::getI)
                .findFirst()
                .orElse(-1);
    }

    public boolean delete(final E item) {
        Optional<Element> found = Stream.iterate(first, element -> element.next)
                .limit(size)
                .filter(element -> element.item.equals(item))
                .findFirst();
        found.ifPresent(element -> {
            element.prev.next = element.next;
            element.next.prev = element.prev;
            element.next = element.prev = null;
        });
        return found.isPresent();
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

    private class IterationElement {
        private final int i;
        private final Element element;

        private IterationElement(final int i, final Element element) {
            this.i = i;
            this.element = element;
        }

        public int getI() {
            return i;
        }

        public Element getElement() {
            return element;
        }
    }

    private class Element {
        private final E item;
        private Element next;
        private Element prev;

        private Element(final E item) {
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

        public Element getPrev() {
            return prev;
        }

        public void setPrev(final Element prev) {
            this.prev = prev;
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
