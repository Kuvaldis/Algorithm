package kuvaldis.test;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.*;

public class TestSteps {

    private Test test;
    private boolean value;

    @Given("test")
    public void test() {
        test = new Test();
    }

    @When("call return true")
    public void call() {
        value = test.returnTrue();
    }

    @Then("value is $value")
    public void value(boolean value) {
        assertEquals(value, this.value);
    }
}