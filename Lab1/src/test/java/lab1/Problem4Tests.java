package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem4Tests {

    @Test
    void isGeometricProgression_test1() {
        // given
        String inputString = "1,2,4,8,16";

        // when True

        // then
        assertTrue(Problem4.isGeometricProgression(inputString));
    }

    @Test
    void isGeometricProgression_test2() {
        // given
        String inputString = "16,2,8,1,4";

        // when True

        // then
        assertTrue(Problem4.isGeometricProgression(inputString));
    }

    @Test
    void isGeometricProgression_test3() {
        // given
        String inputString = "2,3,5";

        // when False

        // then
        assertFalse(Problem4.isGeometricProgression(inputString));
    }

}