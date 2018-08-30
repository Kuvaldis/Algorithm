package kuvaldis.algorithm.cake;

/*

Find a duplicate, Space Edition™.

We have a list of integers, where:

The integers are in the range 1..n
The list has a length of n+1
It follows that our list has at least one integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than twice.

Write a function which finds an integer that appears more than once in our list. (If there are multiple duplicates, you only need to find one of them.)

We're going to run this function on our new, super-hip MacBook Pro With Retina Display™. Thing is, the damn thing came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!

 */
public class FindDuplicateSpaceEdition {

    public int find(final int[] ints) {
        // integers in the array have values from 1 to n = ints.length - 1.
        // we can split them on two categories, before middle element (inclusive) and after.
        // lets say n = 4, so possible values are 1, 2, 3, 4.
        // first category consists of 1 or 2, second is 3 or 4.
        // for n = 5: 1, 2, 3, and 4, 5 respectively.
        // now, lets get back to n = 4 case. We spread array elements between categories.
        // In each category there might be only two non-duplicate elements.
        // We can use element buckets, sets for instance. But our task now is to find out which category has duplicates.
        // For that we can use two counters for each category. We initialize the counters with maximum number of non-duplicates in category.
        // If an array element belongs to a category, we decrease corresponding counter.
        // If a category counter becomes less than zero, it means one of the numbers from this category has a duplicate.
        // We repeat the procedure for this category.
        // min and max bound a duplicate.
        int min = 1;
        int max = ints.length - 1;
        while (true) {
            if (min == max) {
                return min;
            }
            int mid = (min + max) / 2;
            // max number of non-duplicates in each category
            int c1 = mid - min + 1;
            int c2 = max - mid;
            for (int i : ints) {
                if (min <= i && i <= mid) {
                    c1--;
                } else if (mid < i && i <= max) {
                    c2--;
                }
            }
            if (c1 < 0) {
                max = mid;
            } else if (c2 < 0) {
                min = mid + 1;
            } else {
                throw new IllegalArgumentException("Wrong input array");
            }
        }
    }
}
