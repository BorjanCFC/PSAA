// Корисникот од тастатура внесува квадратна матрица со димензија N која преставува слика (т.е. е пополнета со вредности од 0 до 255). 
// Програмата треба да ја ротира сликата за 90 степени во лево.
// Бонус: Задачата да се реши без употреба на нова матрица.

package labVezbi;

import java.util.Scanner;

public class zad3 {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        
        System.out.print("Vnesete golemina na matricata: ");
        int N = input.nextInt();
        
        int[][] mat = new int[N][N];

        System.out.println("Vnesete gi elementite (vrednosti od 0 do 255): ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = input.nextInt();
            }
        }

        System.out.println("Originalnata matrica: ");

        printMat(mat, N);
        System.out.println();

        rotateMat(mat, N);
        
        System.out.println("Matricata po rotacijata za 90 stepeni vo levo: ");
        printMat(mat, N);

        
    }

    public static void rotateMat(int[][] mat, int N) {
    
        int[] pom = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pom[idx++] = mat[i][j];
            }
        }

        idx = 0;
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                mat[i][j] = pom[idx++];
            }
        }
    }

    public static void printMat(int[][] matrix, int N) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
