package kuvaldis.algorithm.careercup;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SerializeMultiTreeTest {

    @Test
    public void testSerialize() throws Exception {
        final SerializeMultiTree.Node tree = tree("1", tree("2", tree("7"), tree("8:muhaha"), tree("9")), tree("3", tree("10")),
                tree("4", tree("11"), tree("12")), tree("5 spacehere"), tree("6", tree("13"), tree("14"), tree("15")));
        final String serializedTree = new SerializeMultiTree().serialize(tree);
        assertEquals("1:1 5 1:2 1:3 1:4 11:5 spacehere 1:6 3 1:7 8:8:muhaha 1:9 1 2:10 2 2:11 2:12 0 3 2:13 2:14 2:15 0 0 0 0 0 0 0 0 0 ",
                serializedTree);
        final SerializeMultiTree.Node deserializedTree = new SerializeMultiTree().deserialize(serializedTree);
        assertEquals("1:1 5 1:2 1:3 1:4 11:5 spacehere 1:6 3 1:7 8:8:muhaha 1:9 1 2:10 2 2:11 2:12 0 3 2:13 2:14 2:15 0 0 0 0 0 0 0 0 0 ",
                new SerializeMultiTree().serialize(deserializedTree));
    }

    private static SerializeMultiTree.Node tree(final String value, final SerializeMultiTree.Node... children) {
        return new SerializeMultiTree.Node(value, Arrays.asList(children));
    }
}