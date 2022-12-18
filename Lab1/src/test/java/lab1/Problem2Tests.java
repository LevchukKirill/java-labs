package lab1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem2Tests {

    @Test
    void segregateEvenAndOddNumbers_test1() {
        // given
        int[] inputArray = { 2, 1, 5, 6, 8 };

        // when
        int[] expectedOutputArray = { 2, 6, 8, 1, 5 };

        // then
        assertTrue(Arrays.equals(
                Problem2.segregateEvenAndOddNumbers(inputArray),
                expectedOutputArray
        ));
    }

    @Test
    void segregateEvenAndOddNumbers_test2() {
        // given
        int[] inputArray = { 1, 2, 3, 4, 5, 6 };

        // when
        int[] expectedOutputArray = { 2, 4, 6, 1, 3, 5 };

        // then
        assertTrue(Arrays.equals(
                Problem2.segregateEvenAndOddNumbers(inputArray),
                expectedOutputArray
        ));
    }
}
