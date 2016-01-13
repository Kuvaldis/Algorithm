package kuvaldis.algorithm.codility;

public class Task122 {
    public int solution(int[] a, int[] b) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 && b[i] == 1) {
                c++;
                continue;
            }
            int g = gcm(a[i], b[i]);
            int m = (a[i] / g) * (b[i] / g);
            while (true) {
                if (g % m == 0) {
                    c++;
                    break;
                } else {
                    int newG = gcm(g, m);
                    if (newG == 1) {
                        break;
                    }
                    m = m / newG;
                    g = newG;
                }
            }
        }
        return c;
    }

    private int gcm(final int a, final int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcm(b, a % b);
        }
    }
}
