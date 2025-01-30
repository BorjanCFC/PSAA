/*
Да се напише функција која како аргументи добива единечно поврзана листа и низа 
од индекси. Функцијата треба од единечно поврзаната листа да ги отстрани оние 
елементи кои во листата се наоѓаат на позиции дадени со низата индекси. 
Функцијата треба да ја врати променетата единечно поврзана листа. 
Потоа, да се напише функција која како аргумент добива единечно поврзана листа. 
Функцијата треба од листата да креира ред во кој првин ќе бидат запишани парните 
вредности од листата сортирани во растечки редослед, па ќе бидат запишани 
непарните вредности од листата исто така во растечки редослед.
Да се напише главна програма во која ќе се тестира работата на двете функции. 
Во главната програма да се отпечатат резултатите добиени од секоја од функциите.
Пример: Во продолжение е даден еден пример за функционалноста на двете функции:
sll: 10  5  6  9  11  4  2  7
	niza_indeksi: [0, 3, 6] 
Првата функција треба од дадениве аргументи да ги избрише елементите од листата 
sll кои се наоѓаат на индекси 0, 3 и 6. Гледано од почеток на листата, тоа се 
елементите 10, 9, 2. Првата функција треба да врати променета единечно поврзана 
листа во која се избришани овие елементи:
sll_nova: 5  6  11  4  7
Втората функција треба од новата листа sll_nova да добие ред queue во кој во 
растечки редослед ќе бидат сортирани првин парните, па непарните броеви. Во 
новата листа парни броеви се 4 и 6, а непарни се 5, 11 и 7. Во редот ќе треба 
да се прикажат сортирани 4 и 6, па сортирани вредностите 5, 11 и 7. Значи, 
редот што ќе се добие треба да биде:
queue: 4 – 6 – 5 – 7 – 11

*/

import java.util. *;

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

    public void setHead(Node<E> n) {
        head = n;
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
}

public class zad18 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<Integer>();
        
        for (int i = 0; i < 10; i++) {
            list.insertFirst(i + 1);
        }
        
        int[] array = {0, 3, 6};
        
        removeElementsAtIndex(list, array);
        
        list.printList();
        
        Queue<Integer> queue = formQueue(list);
        
        for(int element : queue) {
            System.out.print(element + ", ");
        }
    }

    private static void removeElementsAtIndex(SLinkedList<Integer> list, int[] array) {
        Node<Integer> tmp = list.getHead();
        Node<Integer> prev = null;
        
        int index = 0, i = 0;
        
        while (tmp != null && i < array.length) {

            if (index == array[i]) {

                if (prev != null) {
                    prev.next = tmp.next;
                } else {
                    list.setHead(tmp.next);
                }

                i++;

            } else {
                prev = tmp;
            }

            tmp = tmp.next;
            index++;
        }
    }

    private static Queue<Integer> formQueue(SLinkedList<Integer> list) {
        ArrayList<Integer> evenArray = new ArrayList<Integer>();
        ArrayList<Integer> oddArray = new ArrayList<Integer>();
        
        Node<Integer> tmp = list.getHead();
        
        while (tmp != null) {
            if (tmp.data % 2 == 0) {
                evenArray.add(tmp.data);
            } else {
                oddArray.add(tmp.data);
            }
            tmp = tmp.next;
        }
        
        Collections.sort(evenArray);
        Collections.sort(oddArray);
        
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for (int element : evenArray) {
            queue.add(element);
        }
        
        for (int element : oddArray) {
            queue.add(element);
        }
        
        return queue;
    }
    
}