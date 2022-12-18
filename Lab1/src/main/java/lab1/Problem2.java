package lab1;

public class Problem2 {

    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int[] evenNumbersAndAns = new int[array.length];
        int[] oddNumbers = new int[array.length];
        int evenIndex = 0, oddIndex = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] % 2 == 0) {
                evenNumbersAndAns[evenIndex] = array[i];
                ++evenIndex;
            } else {
                oddNumbers[oddIndex] = array[i];
                ++oddIndex;
            }
        }
        for (int i = 0; i < oddIndex; ++i) {
            evenNumbersAndAns[evenIndex + i] = oddNumbers[i];
        }
        return evenNumbersAndAns;
    }

}
