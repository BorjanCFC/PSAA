// Да се напише функција reverse која ќе се додаде на SLinkedList класата. Функцијата како
// аргумент прима почетен јазол на листа и вредност f. Функцијата треба елементите во листата да
// ги групира во сегменти со должина f, и да направи превртување на елементите во секој сегмент.
// Да се напише главна програма во која корисникот ќе внесе листа и ќе се тестира работата на
// функцијата.
// Пример: Дадената листа е следнава: 9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1. За внесено f = 3, треба
// да се добие следнава листа: 7 -> 8 -> 9 -> 4 -> 5 -> 6 -> 1 -> 2 -> 3.


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
    
    public void insertFirst(E e) {
        Node<E> first = new Node<>(e, head);
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
            
            while(tmp.next != n && tmp.next != null) {
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
    
    public int size() {
        Node<E> tmp = head;
        int size = 0;
        
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        
        return size;
    }
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
    
    public void reverse(Node<E> h, int f) {
    
        Node<E> tmp = h;
        Node<E> prevGroup = null;
    
        while (tmp != null) {
    
            Node<E> groupEnd = tmp;
            for (int i = 1; i < f && groupEnd.next != null; i++) {
                groupEnd = groupEnd.next;
            }
    
            Node<E> nextGroup = groupEnd.next;
    
            Node<E> prev = null;
            Node<E> curr = tmp;
            Node<E> next = null;
            for (int i = 0; i < f && curr != null; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
    
            if (prevGroup == null) {
                head = groupEnd;
            } else {
                prevGroup.next = groupEnd;
            }
    
            tmp.next = nextGroup;
            prevGroup = tmp;
            tmp = nextGroup;
        }
    }
        
    }
    
    public class zad6 {
        public static void main(String[] args) {
            SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {9, 8, 7, 6, 5, 4, 3, 2, 1};
            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();
    
            int f = 3;
            list.reverse(list.getHead(),f);
            System.out.println("Reversed list in groups of " + f + ":");
            list.printList();
    }
}
