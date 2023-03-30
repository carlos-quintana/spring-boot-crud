package com.carlosquintana.imageboard.services.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionServiceUnitTest {

    private SubmissionService service = new SubmissionService();

    @Test
    public void parseInputTags() {
        // The second tag has two words separated by one space
        String expectedResult = "one,two three,four";

        String inputTags = "one, two three, four";
        assertEquals(expectedResult, service.parseInputTags(inputTags));

        inputTags = "  ONE, two      THREE    , Four ";
        assertEquals(expectedResult, service.parseInputTags(inputTags));

        inputTags = "ONE";
        expectedResult = "one";
        assertEquals(expectedResult, service.parseInputTags(inputTags));

        inputTags = "";
        expectedResult = "";
        assertEquals(expectedResult, service.parseInputTags(inputTags));
    }

    @Test
    public void parseOutputTags() {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("one");
        expectedResult.add("two three");
        expectedResult.add("four");

        String inputTags = "one,two three,four";
        List<String> result = service.parseOutputTags(inputTags);
        assertEquals(expectedResult.getClass(), result.getClass());
        assertFalse(result.isEmpty());
        assertEquals(expectedResult, result);

        // An empty list when there are no tags
        inputTags = "";
        result = service.parseOutputTags(inputTags);
        assertTrue(result.isEmpty());
    }
}