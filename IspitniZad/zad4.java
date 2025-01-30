// Да се имплементира структура множество од редови. Множеството редови прима креирање на
// ред со одреден капацитет. Откако ќе се надмине капацитетот на последниот ред, треба да се
// креира нов ред.
// Класата треба да содржи метод pushElement(int el) со кој во множеството редови ќе се додава
// нова вредност, метод pushQueueAt(int index, queue s) кој ќе додава нов ред во множеството,
// само ако големината на редот соодветствува со дозволениот капацитет, и метод popQueueAt(int
// index) кој ќе го одзема редот на позиција index во множеството.
// Да се напише главна програма во која ќе се покаже функционалноста на методите.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class QueueSet {
    public int capacity;
    ArrayList<Queue<Integer>> queues = new ArrayList<>();

    public QueueSet(int capacity) {
        this.capacity = capacity;
    }

    public Queue<Integer> getLastQueue() {
        if (queues.isEmpty()) {
            return null;
        }

        return queues.get(queues.size() - 1);
    }

    public void pushElement(int el) {
        Queue<Integer> last = getLastQueue();

        if (last != null && last.size() < capacity) {
            last.add(el);
        } else {
            Queue<Integer> newQueue = new LinkedList<>();
            newQueue.add(el);
            queues.add(newQueue);
        }

    }

    public void pushQueueAt(int index, Queue<Integer> q) {
        if(q.size() == capacity) {
            queues.add(index, q);
        }
    }

    public Queue<Integer> popQueueAt(int index) {
        if (index >= 0 && index < queues.size()) {
            Queue<Integer> q = queues.get(index);
            queues.remove(index);

            return q;
        }

        return null;
    }

}

public class zad4 {
    public static void main(String[] args) {
        QueueSet ss = new QueueSet(3);
        
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
        for (int i = 0; i < ss.queues.size(); i++) {
            for (Integer item : ss.queues.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        
        Queue<Integer> q = ss.popQueueAt(2);
        
    
        System.out.println("\nAfter popping stack at index 2:");
        for (int i = 0; i < ss.queues.size(); i++) {
            for (Integer item : ss.queues.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        ss.pushQueueAt(1, q);

        System.out.println("\nAfter pushing the stack back at index 1:");
        for (int i = 0; i < ss.queues.size(); i++) {
            for (Integer item : ss.queues.get(i)) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
