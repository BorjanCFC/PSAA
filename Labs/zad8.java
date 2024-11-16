// За цел број N (внесен од тастатура) да се генерираат првите N редици од Паскаловиот триаголник

package labVezbi;

import java.util.Scanner;

public class zad8 {
    
    public static void PascalTriangle(int n) {
        
        int[][] triangle = new int[n][];

        
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1]; 

            triangle[i][0] = 1;
            triangle[i][i] = 1;

            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println(); 
        }
    }

    public static void main(String[] args) {
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vnesete go brojot na redici: ");
        int N = scanner.nextInt();
        
        PascalTriangle(N); 
    }
}