package kuvaldis.algorithm.cci;

public class SortedSearchNoSize {

    public static class Listy {
        private final int[] arr;

        public Listy(final int[] arr) { // assume arr is sorted already
            this.arr = arr;
        }
        
        public int elementAt(final int i) {
            if (arr.length <= i) {
                return -1;
            }
            return arr[i];
        }
    }

    public int getIndex(final Listy listy, final int value) {
        // figure out right border
        int right = 1;
        while (listy.elementAt(right - 1) != -1) {
            right *= 2;
        }
        right--; // it's guaranteed now that there are no values to the right of it including right itself

        // binary search
        int left = 0;
        while (left <= right) {
            final int mid = (left + right) / 2;
            final int valueAtMid = listy.elementAt(mid);
            if (value == valueAtMid) {
                return mid;
            } else if (valueAtMid == -1 || value < valueAtMid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
