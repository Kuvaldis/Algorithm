package kuvaldis.algorithm.cci;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BuildOrderTest {

    @Test
    public void simpleTest() {
        final String[] projects = {"a", "b", "c", "d", "e", "f"};
        final String[][] dependencies = {
                {"a", "d"},
                {"f", "b"},
                {"b", "d"},
                {"f", "a"},
                {"d", "c"}};
        final List<String> order = new BuildOrder().buildOrder(projects, dependencies);
        System.out.println(order);
    }
}