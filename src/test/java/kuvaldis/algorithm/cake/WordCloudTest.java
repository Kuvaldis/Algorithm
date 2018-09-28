package kuvaldis.algorithm.cake;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WordCloudTest {

    @Test
    public void testNull() {
        // given
        final String s = null;

        // when
        final WordCloud wordCloud = new WordCloud();
        final Map<String, Integer> result = wordCloud.count(s);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmpty() {
        // given
        final String s = "";

        // when
        final WordCloud wordCloud = new WordCloud();
        final Map<String, Integer> result = wordCloud.count(s);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testExample1() {
        // given
        final String s = "\"After beating the eggs, Dana read the next step:\"\n" +
                "\"Add milk and eggs, then add flour and sugar.\"";

        // when
        final WordCloud wordCloud = new WordCloud();
        final Map<String, Integer> result = wordCloud.count(s);

        // then
        assertEquals(14, result.size());
        final Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("after", 1);
        expectedMap.put("beating", 1);
        expectedMap.put("the", 2);
        expectedMap.put("eggs", 2);
        expectedMap.put("dana", 1);
        expectedMap.put("read", 1);
        expectedMap.put("next", 1);
        expectedMap.put("step", 1);
        expectedMap.put("add", 2);
        expectedMap.put("milk", 1);
        expectedMap.put("and", 2);
        expectedMap.put("then", 1);
        expectedMap.put("flour", 1);
        expectedMap.put("sugar", 1);
        assertEquals(expectedMap, result);
    }

    @Test
    public void testExample2() {
        // given
        final String s = "  \"We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake.\"\n" +
                "\"The bill came to five dollars.\"";

        // when
        final WordCloud wordCloud = new WordCloud();
        final Map<String, Integer> result = wordCloud.count(s);

        // then
        assertEquals(13, result.size());
        final Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("we", 4);
        expectedMap.put("came", 2);
        expectedMap.put("saw", 1);
        expectedMap.put("conquered", 1);
        expectedMap.put("then", 1);
        expectedMap.put("ate", 1);
        expectedMap.put("bill", 2);
        expectedMap.put("mille-feuille", 1);
        expectedMap.put("cake", 1);
        expectedMap.put("the", 1);
        expectedMap.put("to", 1);
        expectedMap.put("five", 1);
        expectedMap.put("dollars", 1);
        assertEquals(expectedMap, result);
    }
}