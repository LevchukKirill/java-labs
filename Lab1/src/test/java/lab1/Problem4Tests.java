package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem4Tests {

    @Test
    void isGeometricProgression_input124816_expectedOutputTrue() {
        // given
        String inputString = "1,2,4,8,16";

        // expected True

        // result
        assertTrue(Problem4.isGeometricProgression(inputString));
    }

    @Test
    void isGeometricProgression_input162814_expectedOutputTrue() {
        // given
        String inputString = "16,2,8,1,4";

        // expected True

        // result
        assertTrue(Problem4.isGeometricProgression(inputString));
    }

    @Test
    void isGeometricProgression_input235_expectedOutputFalse() {
        // given
        String inputString = "2,3,5";

        // expected False

        // result
        assertFalse(Problem4.isGeometricProgression(inputString));
    }

}
