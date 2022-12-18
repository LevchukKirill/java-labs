package lab1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem3Tests {

    @Test
    void flattenMatrix_inputShape3x3_expectedOutputShape9() {
        // given
        int[][] inputArray = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // expected
        int[] expectedOutputArray = { 1, 4, 7, 2, 5, 8, 3, 6, 9 };

        // result
        assertTrue(Arrays.equals(
                Problem3.flattenMatrix(inputArray),
                expectedOutputArray
        ));
    }

    @Test
    void flattenMatrix_inputShape2x5_expectedOutputShape10() {
        // given
        int[][] inputArray = {
                { 0, 1, 2, 3, 4 },
                { 5, 6, 7, 8, 9 }
        };

        // expected
        int[] expectedOutputArray = { 0, 5, 1, 6, 2, 7, 3, 8, 4, 9 };

        // result
        assertTrue(Arrays.equals(
                Problem3.flattenMatrix(inputArray),
                expectedOutputArray
        ));
    }

}
