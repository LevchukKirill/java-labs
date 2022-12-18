package lab1;

import java.util.Arrays;

public class Problem4 {

    public static boolean isGeometricProgression(String commaSeparatedStringOfNumbers) {
        int[] numbers = extractIntegersFromString(commaSeparatedStringOfNumbers);
        Arrays.sort(numbers);
        for (int i = 1; i < numbers.length-1; ++i) {
            if (numbers[i] * numbers[i] != numbers[i-1] * numbers[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] extractIntegersFromString(String commaSeparatedStringOfNumbers) {
        String[] numbersAsStrings = commaSeparatedStringOfNumbers.split(",");
        int[] ans = new int[numbersAsStrings.length];
        for (int i = 0; i < numbersAsStrings.length; ++i) {
            ans[i] = Integer.parseInt(numbersAsStrings[i]);
        }
        return ans;
    }

}
