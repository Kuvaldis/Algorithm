package kuvaldis.algorithm.interview;

public class Task85 {

    public void print(final int n) {
        print("", n, n);
    }

    private void print(String tmp, final int ol, final int cl) {
        if (ol == 0 && cl == 0) {
            System.out.println(tmp);
            return;
        }
        if (ol > 0) {
            print(tmp + "(", ol - 1, cl);
        }
        if (cl > 0 && cl > ol) {
            print(tmp + ")", ol, cl - 1);
        }
    }

}
