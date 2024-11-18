/*
Со додавање на чинии една врз друга се формира магацин. 
Ако магацинот стане превисок, истиот може да се преврти. 
За таа цел откако ќе се достигне одреден број на чинии поставени 
една врз друга, се започнува со структирирање на нов столб чинии. 
Овој реален пример да се имплементира како структура множество од 
магацини. Множеството магацини прима креирање на магацини со одреден 
капацитет. Откако ќе се надмине капацитетот на последниот магацин, 
се креира нов магацин.
Класата треба да содржи метод pushElement(int el) со кој во множеството 
магацини ќе се додава нова вредност, метод pushStackAt(int index, stack s) 
кој ќе додава нов магацин во множеството, само ако големината на магацинот 
соодветствува со дозволениот капацитет, и метод popStackAt(int index) кој 
ќе го одзема магацинот на позиција index во множеството.
Да се напише главна програма во која ќе се покаже функционалноста на методите.

*/

import java.util.Stack;
import java.util.ArrayList;

class StacksSet {
    public int capacity;
    ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
    
    public StacksSet(int capacity) {
        this.capacity = capacity;
    }
    
    public Stack<Integer> getLastStack() {
        if (stacks.isEmpty()) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }
    
    public void pushElement(int value) {
        Stack<Integer> last = getLastStack();
        
        if (last != null && last.size() < capacity) {
            last.push(value);
        } else {
            Stack<Integer> stack = new Stack<>();
            stack.push(value);
            stacks.add(stack);
        }
    }
    
    public void pushStackAt(int index, Stack<Integer> s) {
        if (s.size() == capacity) {
            stacks.add(index, s);
        }
    }
    
    public Stack<Integer> popStackAt(int index) {
        if (index >= 0 && index < stacks.size()) {
            Stack<Integer> stack = stacks.get(index);
            stacks.remove(index);
            return stack;
        }
        return null; 
    }
}

public class zad9 {
    public static void main(String[] args) {
        StacksSet ss = new StacksSet(3);
        
        ss.pushElement(4);
        ss.pushElement(1);
        ss.pushElement(2);
        ss.pushElement(4);
        ss.pushElement(5);
        ss.pushElement(7);
        ss.pushElement(6);
        ss.pushElement(2);
        ss.pushElement(3);
        ss.pushElement(1);
        ss.pushElement(4);
        
        System.out.println("After pushing elements:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        
        Stack<Integer> s = ss.popStackAt(2);
        
    
        System.out.println("\nAfter popping stack at index 2:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        ss.pushStackAt(1, s);

        System.out.println("\nAfter pushing the stack back at index 1:");
        for (int i = 0; i < ss.stacks.size(); i++) {
            for (Integer item : ss.stacks.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        
    }    
}