package lab1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem3Tests {

    @Test
    void flattenMatrix_test1() {
        // given
        int[][] inputArray = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // when
        int[] expectedOutputArray = { 1, 4, 7, 2, 5, 8, 3, 6, 9 };

        // then
        assertTrue(Arrays.equals(
                Problem3.flattenMatrix(inputArray),
                expectedOutputArray
        ));
    }

    @Test
    void flattenMatrix_test2() {
        // given
        int[][] inputArray = {
                { 2, 4, 5, 4 },
                { 6, 9, 8, 3 },
                { 6, 7, 1, 0 },
                { 1, 6, 5, 1 }
        };

        // when
        int[] expectedOutputArray = { 2, 6, 6, 1, 4, 9, 7, 6, 5, 8, 1, 5, 4, 3, 0, 1 };

        // then
        assertTrue(Arrays.equals(
                Problem3.flattenMatrix(inputArray),
                expectedOutputArray
        ));
    }

}