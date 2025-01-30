// Во класата за единечно поврзана листа да се напише метода isSortedBy (int ns) која како
// аргумент добива целоброен податок ns. Методата ќе направи распределба на елементите од
// листата во ns на број магацини на следниот начин. Секој магацин ќе се состои од елементи од
// оригиналната листа на расчекор ns. Првиот магацин ќе почне од нултиот елемент од листата,
// проследен со елементите на позиција ns, 2*ns итн., додека вториот магацин ќе ги содржи
// елементите од следните позиции од оригиналната листа: 1, ns+1, 2*ns+1,…
// После распределбата на елементите во ns магацини, методата isSortedBy треба да провери дали
// секој магацин е сортиран во растечки редослед и да врати true ако тоа е исполнето. Притоа,
// празни магацини и магацини кои содржат само еден елемент се сметаат за сортирани. Исто
// така, за дупликатите се смета дека не ја нарушуваат сортираноста. Доколку ns е помало или
// еднакво на 0, методата треба да јави IllegalArgumentException. Да се напише главна програма
// во која ќе се тестира работата на методата.
// Пример:
// За дадена листа list: <1, 3, 2, 5, 8, 6, 12, 7, 20>, доколку функцијата се повика како:
// • list.isSortedBy(1), функцијата треба да врати false, бидејќи самата листа не е сортирана
// во растечки редослед.
// • list.isSortedBy(2), функцијата треба да врати true, бидејќи двата магацина: [1, 2, 8, 12, 20]
// и [3, 5, 6, 7] се сортирани во растечки редослед.
// • list.isSortedBy(3), функцијата треба да врати false, бидејќи од трите магацина: [1, 5, 12],
// [3, 8, 7], [2, 6, 20] вториот не е сортиран во растечки редослед.

import java.util.ArrayList;
import java.util.Stack;

class Node<E> {
    protected E data;
    protected Node<E> next;
    
    public Node() {
        data = null;
        next = null;
    }
    
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E data) {
        Node<E> first = new Node<>(data, head);
        head = first;
    }
    
    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node<>(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                return;
            }
            
            while (tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }
            
            if (tmp.next == n) {
                Node<E> node = new Node<>(e, n);
                tmp.next = node;
            }
        }
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            
            Node<E> last = new Node<>(e, null);
            tmp.next = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
    
    public void reverse() {
        if (head != null) {
            Node<E> curr = head;
            Node<E> prev = null, next = null;
            
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            
            head = prev;
        }
    }

    public boolean isSortedBy(int ns) {

        if (ns <= 0) {
            throw new IllegalArgumentException("Greshka.");
        }

        ArrayList<Stack<E>> list = new ArrayList<>();

        for (int i = 0; i < ns; i++) {
            list.add(new Stack<E>());
        }

        Node<E> tmp = head;

        int index = 0;

        while (tmp != null) {
            list.get(index).push(tmp.data);

            index++;
            if (index == ns) {
                index = 0;
            }

            tmp = tmp.next;
        }

        for (int i = 0; i < ns; i++) {
            System.out.println("Stack " + (i + 1) + list.get(i));
        }

        for (int i = 0; i < ns; i++) {

            int element1 = (int) list.get(i).pop();
            int element2 = (int) list.get(i).pop();

            if(element1 < element2) {
                return false;
            }

            while (element1 >=  element2 && !list.get(i).isEmpty()) {

                element1 = element2;
                element2 = (int) list.get(i).pop();
                
                if(element1 < element2) {
                    return false;
                }

            }
        }

        return true;
    }

}
public class zad1 {

    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();

        list.insertLast(1);
        list.insertLast(3);
        list.insertLast(2);
        list.insertLast(5);
        list.insertLast(8);
        list.insertLast(6);
        list.insertLast(12);
        list.insertLast(7);
        list.insertLast(20);

        System.out.print("Lista: ");
        list.printList();

        int ns = 4;
        boolean result = list.isSortedBy(ns);

        if (result) {
            System.out.println("Listata e sortirana vo rastecki redosled za ns = " + ns);
        } else {
            System.out.println("Listata ne e sortirana vo rastecki redosled za ns = " + ns);
        }
    }
}