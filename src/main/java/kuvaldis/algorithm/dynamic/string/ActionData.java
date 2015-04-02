package kuvaldis.algorithm.dynamic.string;

import lombok.Data;

@Data
public class ActionData {
    private final Cost cost;
    private final Action action;
    private final Character letterFrom;
}
