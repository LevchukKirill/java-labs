package lab1;

public class Problem3 {

    public static int[] flattenMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] ans = new int[n*m];
        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                ans[j*n + i] = matrix[i][j];
            }
        }
        return ans;
    }

}
