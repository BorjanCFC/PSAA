// Даден е шпил од 51 карта. Треба да се внесе карта и да се одреди по колку мешања таа
// карта ќе се најде на почеток од редот. Едно мешање на шпилот се состои од вадење на
// седум карти од почетокот на шпилот, кои се превртуваат, пред да бидат вратени на крај од
// шпилот на тој начин што на крај од редот се става една од извадените карти, па првата карта
// од редот, и постапката се повторува се додека не се потрошат сите 7 извлечени карти. 

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class zad10 {
    public static void main(String[] args) {
        int karta = 51; 

        
        int broj = brojMeshanja(karta);

        
        System.out.println("Broj na meshanje na kartata " + karta + " e " + broj);
    }

    private static int brojMeshanja(int karta) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        
        
        for (int i = 1; i <= 51; i++) {
            queue.add(i);
        }

        int counter = 0;

        
        while (queue.peek() != karta) {
            
            for (int i = 0; i < 7; i++) {
                s.push(queue.remove());
            }
            
            while (!s.isEmpty()) {
                queue.add(s.pop());
                queue.add(queue.remove());
            }

            counter++;
        }

        return counter;
    }
}

