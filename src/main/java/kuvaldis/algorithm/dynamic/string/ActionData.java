package kuvaldis.algorithm.dynamic.string;

public class ActionData {

    private final Cost cost;
    private final Action action;
    private final Character letterFrom;

    public ActionData(final Cost cost, final Action action, final Character letterFrom) {
        this.cost = cost;
        this.action = action;
        this.letterFrom = letterFrom;
    }

    public Cost getCost() {
        return cost;
    }

    public Action getAction() {
        return action;
    }

    public Character getLetterFrom() {
        return letterFrom;
    }
}
