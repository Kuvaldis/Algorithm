package kuvaldis.structure;

import kuvaldis.algorithm.sort.BubbleSort;
import kuvaldis.algorithm.sort.InsertionSort;
import kuvaldis.algorithm.sort.SelectionSort;
import kuvaldis.algorithm.sort.Sort;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LinkedListSteps {

    private LinkedList<Integer> list;

    @Given("empty integer linked list")
    public void emptyList() throws IllegalAccessException, InstantiationException {
        list = new LinkedList<Integer>();
    }

    @When("add $value")
    public void add(final Integer value) {
        list.add(value);
    }

    @Then("items are $elements")
    public void check(final List<Integer> elements) {
        Assert.assertArrayEquals("Incorrect list implementation", convertToArray(elements), list.toArray()); }

    private Integer[] convertToArray(List<Integer> input) {
        final Integer[] result = new Integer[input.size()];
        IntStream.range(0, input.size()).forEach(i -> result[i] = input.get(i));
        return result;
    }
}