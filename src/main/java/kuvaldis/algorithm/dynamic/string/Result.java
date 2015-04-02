package kuvaldis.algorithm.dynamic.string;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.*;

@Data
public class Result implements Iterable<ActionData> {

    @Getter(AccessLevel.PRIVATE)
    private final Deque<ActionData> actions = new LinkedList<>();

    public void push(final ActionData actionData) {
        actions.push(actionData);
    }

    @Override
    public Iterator<ActionData> iterator() {
        return actions.iterator();
    }

    public ActionData last() {
        return actions.getLast();
    }
}
