// Да се напише функција која како аргумент добива магацин. Функцијата треба да ја сортира содржината на магацинот така што најмалиот елемент 
// ќе биде сместен на врвот од магацинот, а најголемиот елемент ќе биде сместен на дното. 
// Притоа, за реализација на решението функцијата може да користи само магацини како помошна 
// структура (дополнително дефинирање на променливи – обични целобројни или структурни не е дозволено).

package labVezbi;

import java.util.Stack;

public class zad5 {
    public static void main(String[] args) {
         Stack<Integer> stack = new Stack<>();
        
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(5);
        
        sortStack(stack);
                
                while (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
            }
        
            public static void sortStack(Stack<Integer> stack) {

                Stack<Integer> tmpStack = new Stack<>();
                Stack<Integer> pom = new Stack<>();
                
                while (!stack.isEmpty()) {
                    pom.push(stack.pop());
                    
                    while (!tmpStack.isEmpty() && tmpStack.peek() > pom.peek()) {
                        stack.push(tmpStack.pop());
                    }
                    
                    tmpStack.push(pom.pop());
                }
                
                while (!tmpStack.isEmpty()) {
                    stack.push(tmpStack.pop());
                }
            }
}
