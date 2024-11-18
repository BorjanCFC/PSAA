/*
Дадени се две двојно поврзани листи чии јазли содржат парен број цели броеви 
(бројот на елементи во двете лисити не мора да е еднаков). Да се напише 
функција која како аргумент прима две листи и враќа нова листа, на следниов 
начин: се земаат по два броеви од двете листи наизменично (два броја од почеток 
на првата, па два броја од крај на втората, итн.) и се проверува дали збирот на 
броевите од првата листа е ист со збирот на броевите од втората листа. Доколку 
овој услов е исполнет, тој збир треба да се смести во нова двојно поврзана 
листа. Доколку условот не е исполнет во новата листа да се смести нула. 
Доколку една од листите се измине пред другата, преостанатите елементи само се 
додаваат во крајната листа. 
Да се напише главна програма во која ќе се тестира работата на функцијата. 
Пример: Листа 1: |2|<->|5|<->|7|<->|9|<->|3|<->|3|<->|7|<->|1| 
Листа 2: |5|<->|3|<->|4|<->|1|<->|5|<->|1|<->|1|<->|5|<->|3|<->|4|<->|4|<->|3| 
Крајна листа: |7|<->|0|<->|6|<->|0|<->|1|<->|4|<->|3|<->|5|

*/

class Node<E> {
    protected E data;
    protected Node<E> prev, next;

    public Node() {
        data = null;
        prev = next = null;
    }

    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class DLinkedList<E> {
    private Node<E> head, tail;

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public DLinkedList() {
        head = null;
        tail = null;
    }

    public void insertFirst(E e) {
        Node<E> first = new Node<>(e, null, head);

        if (head != null) {
            head.prev = first;
        }

        if (tail == null) {
            tail = first;
        }

        head = first;
    }

    public void insertLast(E e) {
        if (head != null) {
            Node<E> last = new Node<>(e, tail, null); 
            tail.next = last;
            tail = last;
        } else {
            this.insertFirst(e); 
        }
    }

    public void printList() {
        Node<E> tmp = head;
    
        if (tmp == null) {
            System.out.println("Listata e prazna.");
            return;
        }
    
        System.out.print("Lista: ");
        while (tmp != null) {
            System.out.print(tmp.data);
            
            if (tmp.next != null) {
                System.out.print(" <-> ");
            }
    
            tmp = tmp.next;
        }
        System.out.println();
    }
}

public class zad13 {
    public static void main(String[] args) {
        DLinkedList<Integer> list1 = new DLinkedList<Integer>();
        DLinkedList<Integer> list2 = new DLinkedList<Integer>();
        DLinkedList<Integer> list3;
        
        list1.insertLast(2);
        list1.insertLast(5);
        list1.insertLast(7);
        list1.insertLast(9);
        list1.insertLast(3);
        list1.insertLast(3);
        list1.insertLast(7);
        list1.insertLast(1);
        
        list2.insertLast(5);
        list2.insertLast(3);
        list2.insertLast(4);
        list2.insertLast(1);
        list2.insertLast(5);
        list2.insertLast(1);
        list2.insertLast(1);
        list2.insertLast(5);
        list2.insertLast(3);
        list2.insertLast(4);
        list2.insertLast(4);
        list2.insertLast(3);
        
        list3 = combineLists(list1, list2);
        
        list3.printList();        
    }

    private static DLinkedList<Integer> combineLists(DLinkedList<Integer> list1, DLinkedList<Integer> list2) {
        DLinkedList<Integer> list3 = new DLinkedList<>();
        
        Node<Integer> tmp = list1.getHead(); 
        Node<Integer> pom = list2.getTail(); 
        
        int sum1, sum2;
        
        
        while (tmp != null && pom != null) {
            
            sum1 = tmp.data + (tmp.next != null ? tmp.next.data : 0);
            sum2 = pom.data + (pom.prev != null ? pom.prev.data : 0);
            
            
            if (sum1 == sum2) {
                list3.insertLast(sum2);
            } else {
                list3.insertLast(0);
            }
            
            
            tmp = tmp.next.next;
            pom = pom.prev.prev;
        }
        
        
        while (tmp != null) {
            list3.insertLast(tmp.data);
            tmp = tmp.next;
        }
        
        while (pom != null) {
            list3.insertLast(pom.data);
            pom = pom.prev;
        }
        
        return list3;
    }
    
}