package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 */
public class TrappingRainWater {

    public int trap(final int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        // starting from left to right calculate how an element is restricted from left
        final int[] restrictions = new int[heights.length];
        // consider first element unrestricted, but it defines initial restriction to the right positions
        restrictions[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            restrictions[i] = Math.max(restrictions[i - 1], heights[i]);
        }

        // from right to left check right restriction and compare to left restrictions. The lowest wins.
        // consider last element unrestricted
        restrictions[restrictions.length - 1] = heights[heights.length - 1];
        for (int i = heights.length - 2; i > 0; i--) {
            restrictions[i] = Math.min(restrictions[i], restrictions[i + 1]);
            restrictions[i] = Math.max(restrictions[i], heights[i]);
        }

        int result = 0;
        for (int i = 0; i < restrictions.length; i++) {
            result += Math.abs(heights[i] - restrictions[i]);
        }

        return result;
    }
}
