// Во дадена бинарна матрица со димензии m x n да се одреди најголемата квадратна подматрица
// која е составена само од единици и нејзината локација во главната матрица. Димензиите на
// бинарната матрица и нејзината содржина се внесуваат од тастатура.

import java.util.Scanner;

public class zad3 {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        
        System.out.print("Vnesete broj na redici: ");
        int N = input.nextInt();

        System.out.println();
        System.out.print("Vnesete broj na koloni: ");
        int M = input.nextInt();
        
        int[][] mat = new int[N][M];

        System.out.println("Vnesete gi elementite (0, 1): ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mat[i][j] = input.nextInt();
            }
        }

        System.out.println("Originalnata matrica: ");

        printMat(mat, N, M);
        System.out.println();

        int[][] dp = new int[N][M];
        int maxSize = 0;
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = mat[i][j];
                } else if (mat[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }

                if (dp[i][j] > maxSize) {
                    maxSize = dp[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Golemina: " + maxSize);
        System.out.println("Pocetna lokacija: (" + (maxRow - maxSize + 1) + ", " + (maxCol - maxSize + 1) + ")");
    }

    public static void printMat(int[][] matrix, int N, int M) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
