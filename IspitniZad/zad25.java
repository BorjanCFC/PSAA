// Да се напише функција која единечно поврзана листа ќе ја промени на тој начин што сите
// елементи на парен индекс (парна позиција во листата) ќе се сместат на почетокот на листата, а сите
// елементи на непарен индекс ќе се најдат на крајот од листата. Се претпоставува дека head јазолот
// е со индекс нула.
// Пример:
// За листата: 1 -> 4 -> 6 -> 5 -> 7 -> 8, елементите 1, 6 и 7 се наоѓаат на парен индекс, додека елементите
// 4, 5 и 8 се наоѓаат на непарен индекс, па добиената листа треба да биде: 1 -> 6 -> 7 -> 4 -> 5 -> 8.

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
}

public class zad25 {
    public static void main(String[] args) {
    
        SLinkedList<Integer> list2 = new SLinkedList<>();

        list2.insertLast(1);
        list2.insertLast(4);
        list2.insertLast(6);
        list2.insertLast(5);
        list2.insertLast(7);
        list2.insertLast(8);

        list2.printList();
        System.out.println();

        izmeniLista(list2);
        list2.printList();
        
    }

    private static void izmeniLista(SLinkedList<Integer> list) {
        Node<Integer> even = list.getHead();
        Node<Integer> odd = even.next;
        Node<Integer> oddHead = odd;

        while(odd != null && odd.next != null) {
            even.next = odd.next;
            even = even.next;
            odd.next = even.next;
            odd = odd.next;
        }

        even.next = oddHead;
    }
}