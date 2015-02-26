package kuvaldis.algorithm.structure;

import lombok.Data;
import lombok.ToString;

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

    @Data
    @ToString(of = "item")
    public class Element {
        private final E item;
        private Element next;
    }
}
