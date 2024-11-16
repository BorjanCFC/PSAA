// N гости во еден ресторан, во ист временски момент, му порачуваат на готвачот N различни јадења. 
// За секое од јадењата се знае колку време му е потребно на готвачот да го приготви. 
// Секој од гостите, од моментот на нарачка, почнува да му брои казнени поени на готвачот. 
// Му дава онолку казнени поени колку што време чека да му стигне јадењето. 
// Кој е минималниот број на казнени поени кои може да ги добие готвачот.

package labVezbi;

import java.util.Arrays;
import java.util.Scanner;

public class zad7 {
    
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vnesete go brojot na jadenja: ");
        int n = scanner.nextInt();

        int[] preparationTimes = new int[n];
        System.out.println("Vnesete go vremeto za prigotvuvanje za sekoe jadenje:");
        for (int i = 0; i < n; i++) {
            preparationTimes[i] = scanner.nextInt();
        }

        Arrays.sort(preparationTimes);

        int totalPenalty = 0;
        int currentTime = 0;

        for (int i = 0; i < preparationTimes.length; i++) {
            currentTime += preparationTimes[i]; 
            totalPenalty += currentTime;      
        }

        System.out.println("Minimalen broj na kazneni poeni: " + totalPenalty);
    }
}
