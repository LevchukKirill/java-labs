package lab1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem2Tests {

    @Test
    void segregateEvenAndOddNumbers_input21568_expectedOutput26815() {
        // given
        int[] inputArray = { 2, 1, 5, 6, 8 };

        // expected
        int[] expectedOutputArray = { 2, 6, 8, 1, 5 };

        // result
        assertTrue(Arrays.equals(
                Problem2.segregateEvenAndOddNumbers(inputArray),
                expectedOutputArray
        ));
    }

    @Test
    void segregateEvenAndOddNumbers_input84602_expectedOutput84602() {
        // given
        int[] inputArray = { 8, 4, 6, 0, 2 };

        // expected
        int[] expectedOutputArray = { 8, 4, 6, 0, 2 };

        // result
        assertTrue(Arrays.equals(
                Problem2.segregateEvenAndOddNumbers(inputArray),
                expectedOutputArray
        ));
    }

    @Test
    void segregateEvenAndOddNumbers_input97351_expectedOutput97351() {
        // given
        int[] inputArray = { 9, 7, 3, 5, 1 };

        // expected
        int[] expectedOutputArray = { 9, 7, 3, 5, 1 };

        // result
        assertTrue(Arrays.equals(
                Problem2.segregateEvenAndOddNumbers(inputArray),
                expectedOutputArray
        ));
    }

}
