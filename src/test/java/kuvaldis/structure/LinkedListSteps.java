package kuvaldis.structure;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.List;
import java.util.stream.IntStream;

public class LinkedListSteps {

    private LinkedList<Integer> list;
    private Integer index;
    private boolean deleted;

    @Given("empty integer linked list")
    public LinkedList<Integer> emptyList() {
        return list = new LinkedList<>();
    }

    @Given("integer list of $integers")
    public void list(final List<Integer> integers) {
        integers.stream().forEach(emptyList()::add);
    }

    @When("add $value")
    public void add(final Integer value) {
        list.add(value);
    }

    @When("index $value")
    public void index(final Integer value) {
        index = list.index(value);
    }

    @When("delete $value")
    public void delete(final Integer value) {
        deleted = list.delete(value);
    }

    @Then("items are $items")
    public void checkItems(final List<Integer> items) {
        Assert.assertArrayEquals(convertToArray(items), list.toArray());
    }

    @Then("found index is $correctIndex")
    public void checkIndex(final Integer correctIndex) {
        Assert.assertEquals(correctIndex, index);
    }

    @Then("element deleted: $correctDeleted")
    public void checkDeleted(final Boolean correctDeleted) {
        Assert.assertEquals(correctDeleted, deleted);
    }

    private Integer[] convertToArray(List<Integer> input) {
        final Integer[] result = new Integer[input.size()];
        IntStream.range(0, input.size()).forEach(i -> result[i] = input.get(i));
        return result;
    }
}