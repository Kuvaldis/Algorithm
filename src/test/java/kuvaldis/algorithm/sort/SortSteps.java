package kuvaldis.algorithm.sort;

import org.apache.commons.lang.ArrayUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortSteps {

    private static String getSortName(final Class cls) {
        return cls.getSimpleName().replaceAll("Sort", "").toLowerCase();
    }

    private static final Map<String, Class<? extends Sort>> ALGORITHMS =
            new Reflections("kuvaldis.algorithm.sort").getSubTypesOf(Sort.class).stream()
                    .collect(Collectors.toMap(SortSteps::getSortName, Function.identity()));

    private Sort sort;
    private int[] sorted;

    @Given("algorithm $name sort")
    public void algorithm(final String name) throws IllegalAccessException, InstantiationException {
        sort = ALGORITHMS.get(name).newInstance();
    }

    @When("sort $input")
    public void sort(final List<Integer> input) {
        sorted = sort.sort(convertToArray(input));
    }

    @When("sort empty array")
    public void sortEmpty() {
        sorted = sort.sort(new int[0]);
    }

    @Then("output equals $correctOutput")
    public void check(final List<Integer> correctOutput) {
        Assert.assertArrayEquals(String.format("Incorrect sort algorithm: %s", sort.getClass().getName()),
                convertToArray(correctOutput), sorted);
    }

    @Then("output equals empty array")
    public void checkEmptyArray() {
        Assert.assertEquals(String.format("Incorrect sort algorithm: %s", sort.getClass().getName()),
                0, sorted.length);
    }

    private int[] convertToArray(List<Integer> input) {
        final int[] result = new int[input.size()];
        IntStream.range(0, input.size()).forEach(i -> result[i] = input.get(i));
        return result;
    }
}