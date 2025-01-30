// Да се напише функција која како аргумент добива магацин. Во магацинот се сместени топчиња
// со различни четири тежини, нумерирани од 1 до 4. Топчиња со поголема тежина можат да ги
// поништат сите топчиња со помала тежина кои се наоѓаат под нив во магацинот. Дополнително,
// две топчиња со иста тежина кои се наоѓаат едно до друго можат да се здружат во едно топче
// со тежина 5. Две топчиња со тежина 5 не можат да се здружат во едно топче. Топче со тежина
// 5 може да поништи само две топчиња со строго помала тежина кои се директно под него во
// магацинот. Функцијата треба да го врати променетиот магацин.
// Да се напише главна програма во која ќе се внесе еден магацин и во која ќе се повика и тестира
// работата на функцијата.
// Пример:
// За магацин: bottom [3, 2, 1, 1, 2, 3, 3, 4, 2, 3, 1] top, ќе се добие чекор по чекор:
// - двете топчиња со вредност 1 се здружуваат во тежина 5: bottom [3, 2, 5, 2, 3, 3, 4, 2, 3, 1] top
// - топчето 5 ги поништува двете топчиња под него: bottom [5, 2, 3, 3, 4, 2, 3, 1] top
// - двете топчиња 3 се здружуваат во тежина 5: bottom [5, 2, 5, 4, 2, 3, 1] top
// - топчето 5 го поништува само едното топче под него бидејќи не може да поништи или да се
// здружи со друго топче со тежина 5: bottom [5, 5, 4, 2, 3, 1] top
// - топчето 3 го поништува топчето 2: bottom [5, 5, 4, 3, 1] top и ова е финалниот изглед на
// магацинот.

import java.util.Stack;

public class zad13 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        int [] sequence = {3, 2, 1, 1, 2, 3, 3, 4, 2, 3, 1};
        for (int i : sequence) {
            stack.push(i);
        }

        System.out.println("Magacinot e: " + stack);

        System.out.println();


        System.out.println("Magacinot po promenata e: " + changeStack(stack));
                        
    }
        
    private static Stack<Integer> changeStack(Stack<Integer> s) {

        Stack<Integer> tmp = new Stack<>();
        Stack<Integer> pom = new Stack<>();
        int element;

        while(!s.isEmpty()) {
            pom.push(s.pop());
        }

        while(!pom.isEmpty()){

            element = pom.pop();
            
            if(tmp.isEmpty()) {
                tmp.push(element);

            } else {

                if(element > tmp.peek()) {

                    while (!tmp.isEmpty() && element > tmp.peek()) {
                        tmp.pop();
                    }

                    tmp.push(element);

                } else if (element == tmp.peek()) {

                    tmp.pop();
                    if(tmp.size() >= 2) {

                        int br1 = tmp.pop();
                        int br2 = tmp.pop();

                        if(br1 == 5) {

                            tmp.push(br1);
                            tmp.push(br2);
                            tmp.push(5);

                        } else if (br2 == 5){

                            tmp.push(br2);
                            tmp.push(5);
                            
                        } else {
                            tmp.push(5);
                        }

                    } else if (tmp.size() == 1) {

                        if(tmp.peek() == 5) {
                            tmp.push(5);

                        } else {

                            tmp.pop();
                            tmp.push(5);

                        }
                        
                    } else {
                        tmp.push(5);
                    }
                } else {
                    tmp.push(element);
                }
            }
        }

        return tmp;

    }

}
