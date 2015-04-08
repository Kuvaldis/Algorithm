package kuvaldis.algorithm.dynamic;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class NonPermutationMinimumSumDifferencePartitionTest {

    @Test
    public void partition() throws Exception {
        final NonPermutationMinimumSumDifferencePartition nonPermutationMinimumSumDifferencePartition =
                new NonPermutationMinimumSumDifferencePartition();
        final List<NonPermutationMinimumSumDifferencePartition.Partition> result =
                nonPermutationMinimumSumDifferencePartition.partitions(asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
        assertEquals(3, result.size());
        final NonPermutationMinimumSumDifferencePartition.Partition firstPartition = result.get(0);
        assertEquals(0, firstPartition.getStartInclusive());
        assertEquals(4, firstPartition.getEndInclusive());
        assertEquals(asList(1, 2, 3, 4, 5), firstPartition.getValues());
        final NonPermutationMinimumSumDifferencePartition.Partition secondPartition = result.get(1);
        assertEquals(5, secondPartition.getStartInclusive());
        assertEquals(6, secondPartition.getEndInclusive());
        assertEquals(asList(6, 7), secondPartition.getValues());
        final NonPermutationMinimumSumDifferencePartition.Partition thirdPartition = result.get(2);
        assertEquals(7, thirdPartition.getStartInclusive());
        assertEquals(8, thirdPartition.getEndInclusive());
        assertEquals(asList(8, 9), thirdPartition.getValues());
    }
}