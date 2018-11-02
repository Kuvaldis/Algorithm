package kuvaldis.algorithm.cci;

public class StackMin {

    private final int[] stack;
    private final int[] mins;
    private int currentStack = -1;
    private int currentMins = -1;

    public StackMin(final int size) {
        stack = new int[size];
        mins = new int[size];
    }

    public void push(final int value) {
        stack[++currentStack] = value;
        if (currentMins < 0 || mins[currentMins] >= value) {
            mins[++currentMins] = value;
        }
    }

    public int pop() {
        final int val = stack[currentStack--];
        if (mins[currentMins] == val) {
            currentMins--;
        }
        return val;
    }

    public int min() {
        if (currentMins >= 0) {
            return mins[currentMins];
        }
        throw new RuntimeException("No min");
    }
}
