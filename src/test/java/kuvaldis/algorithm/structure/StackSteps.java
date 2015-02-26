package kuvaldis.algorithm.structure;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StackSteps {

    private Stack<Integer> stack;
    private List<Integer> popped;

    @Given("empty integer stack")
    public Stack<Integer> emptyStack() {
        popped = new ArrayList<>();
        return stack = new Stack<>();
    }

    @When("push $value")
    public void push(final Integer value) {
        stack.push(value);
    }

    @When("pop")
    public void pop() {
        Optional.ofNullable(stack.pop()).ifPresent(popped::add);
    }

    @Then("popped items are $items")
    public void checkPopped(final List<Integer> items) {
        Assert.assertEquals(items, popped);
    }

    @Then("empty popped items")
    public void checkPoppedEmpty() {
        Assert.assertTrue(popped.isEmpty());
    }

    @Then("stack size is $correctSize")
    public void checkSize(final Integer correctSize) {
        Assert.assertEquals(correctSize.intValue(), stack.size());
    }
}