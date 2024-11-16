// За внесена редица од цели броеви треба да се направи проверка дали за три последователни елементи важи дека сумата на 
// првите два ја дава вредноста во третиот елемент и доколку условот е исполнет треба да се избрише вториот елемент. 
// Доколку не е исполнет условот, редицата не претрпува промена.

package labVezbi;

import java.util.LinkedList;
import java.util.Queue;

public class zad6 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(4);
        queue.add(3);
        queue.add(5);
        queue.add(8);
        queue.add(13);

        System.out.println("Original Queue: " + queue);

        Queue<Integer> modifiedQueue = modifyQueue(queue);

        System.out.println("Modified Queue: " + modifiedQueue);
    }

    private static Queue<Integer> modifyQueue(Queue<Integer> queue) {
        int tmp1;
        int tmp2;
        int tmp3;

        Queue<Integer> tmpQueue = new LinkedList<>();
        Queue<Integer> pom = new LinkedList<>();

        while (!queue.isEmpty()) {
            if (queue.size() >= 3) {
                tmp1 = queue.remove();
                tmp2 = queue.remove();
                tmp3 = queue.remove();

                if (tmp1 + tmp2 == tmp3) {
                    tmpQueue.add(tmp1);
                    tmpQueue.add(tmp3);

                    while (!queue.isEmpty()) {
                        tmpQueue.add(queue.remove());
                    }

                } else {
                    pom.add(tmp1);
                    tmpQueue.add(tmp2);
                    tmpQueue.add(tmp3);

                    while (!queue.isEmpty()) {
                        tmpQueue.add(queue.remove());
                    }
                }

                while (!tmpQueue.isEmpty()) {
                    queue.add(tmpQueue.remove());
                }

            } else {
                while (!queue.isEmpty()) {
                    pom.add(queue.remove());
                }
            }
        }

        return pom;
    }
}