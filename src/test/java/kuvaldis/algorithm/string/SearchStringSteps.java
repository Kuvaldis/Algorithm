package kuvaldis.algorithm.string;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;

public class SearchStringSteps {

    private char[] string;
    private int index;

    @Given("string $string")
    public void string(final String string) {
        this.string = string.toCharArray();
    }

    @When("search substring $substring")
    public void searchSubstring(final String substring) {
        index = new SearchString().search(string, substring.toCharArray());
    }

    @Then("index is $correctIndex")
    public void checkIndex(final int correctIndex) {
        assertEquals(correctIndex, index);
    }
}
