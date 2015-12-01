package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BTreeSetTest {

    @Test
    public void testBTree() throws Exception {
        final BTreeSet set = new BTreeSet();
        set.add(1);
        set.add(18);
        set.add(36);
        set.add(11);
        set.add(15);
        set.add(2);
        set.add(38);
        set.add(40);
        set.add(41);
        assertEquals(9, set.size());
    }
}