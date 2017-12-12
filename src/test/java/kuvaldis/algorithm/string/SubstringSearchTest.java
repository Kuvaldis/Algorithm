package kuvaldis.algorithm.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SubstringSearchTest {

    @Parameters(name = "[{index}] \"{1}\" in \"{0}\"")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"abc", "a", 0},
                {"abc", "b", 1},
                {"abc", "c", 2},
                {"abc", "d", -1},
                {"abc", "ab", 0},
                {"abc", "ab", 0},
                {"abc", "bc", 1},
                {"abc", "cd", -1},
                {"abc", "abc", 0},
                {"abc", "abcd", -1},
        });
    }

    @Parameter(0)
    public String s;

    @Parameter(1)
    public String ss;

    @Parameter(2)
    public int expectedIndex;

    @Test
    public void testSimpleSearch() throws Exception {
        // when
        final int result = new SimpleSubstringSearch().search(s, ss);

        // then
        assertEquals(expectedIndex, result);
    }
}
