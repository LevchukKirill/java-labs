package lab1;

public class Problem1 {

    public static boolean containsDigitAInHexadecimalRepresentation(int number) {
        while (number > 0) {
            int remainder = number % 16;
            if (remainder == 10) {
                return true;
            }
            number /= 16;
        }
        return false;
    }

}
