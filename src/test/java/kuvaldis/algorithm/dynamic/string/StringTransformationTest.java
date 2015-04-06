package kuvaldis.algorithm.dynamic.string;

import org.junit.Test;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static kuvaldis.algorithm.dynamic.string.Action.*;
import static org.junit.Assert.assertEquals;

public class StringTransformationTest {

    @Test
    public void testCompare() throws Exception {
        final StringTransformation stringTransformation = new StringTransformation();
        final Result result = stringTransformation.compare("thou shalt not", "you should not");
        assertEquals(Cost.of(5), result.last().getCost());
        assertEquals(asList(DELETE, SUBSTITUTE, NONE, NONE, NONE, NONE, NONE, INSERT,
                SUBSTITUTE, NONE, SUBSTITUTE, NONE, NONE, NONE, NONE),
                stream(result.spliterator(), false)
                        .map(ActionData::getAction)
                        .collect(Collectors.toList()));
    }
}