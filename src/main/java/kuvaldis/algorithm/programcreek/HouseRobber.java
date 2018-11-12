package kuvaldis.algorithm.programcreek;

/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 */
public class HouseRobber {

    public long maxSum(final int[] houses) {
        final long[] optimals = new long[2];
        // optimals[0] - for even
        // optimals[1] - for odd
        for (int i = 0; i < houses.length; i++) {
            final int index = i & 1;
            final long newOptimal = Math.max(optimals[(index + 1) % 2], houses[i] + optimals[index]);
            optimals[index] = newOptimal;
        }
        return Math.max(optimals[0], optimals[1]);
    }
}
