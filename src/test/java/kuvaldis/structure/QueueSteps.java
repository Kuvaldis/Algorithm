package kuvaldis.structure;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueueSteps {

    private Queue<Integer> queue;
    private List<Integer> dequeued;

    @Given("empty integer queue")
    public Queue<Integer> emptyQueue() {
        dequeued = new ArrayList<>();
        return queue = new Queue<>();
    }

    @When("enqueue $value")
    public void enqueue(final Integer value) {
        queue.enqueue(value);
    }

    @When("dequeue")
    public void dequeue() {
        Optional.ofNullable(queue.dequeue()).ifPresent(dequeued::add);
    }

    @Then("dequeued items are $items")
    public void checkDequeued(final List<Integer> items) {
        Assert.assertEquals(items, dequeued);
    }

    @Then("empty dequeued items")
    public void checkDequeuedEmpty() {
        Assert.assertTrue(dequeued.isEmpty());
    }

    @Then("queue size is $correctSize")
    public void checkSize(final Integer correctSize) {
        Assert.assertEquals(correctSize.intValue(), queue.size());
    }
}