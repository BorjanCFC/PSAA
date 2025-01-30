// Given a linked list and a value x, partition it such that all nodes less than x come first, 
// then all nodes with a value equal to x, and finally nodes with a value greater than x. 
// The original relative order of the nodes in each of the three partitions should be preserved.

// Examples: 

// Input : 1 -> 4 -> 3 -> 2 -> 5 -> 2 -> 3, x = 3
// Output: 1 -> 2 -> 2 -> 3 -> 3 -> 4 -> 5

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

public class zad41 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {1, 4, 3, 2, 5, 2, 3};
            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();

            int x = 3;
            partition(list, x);
            list.printList();
    }
            
    private static void partition(SLinkedList<Integer> list, int x) {
        Node<Integer> lessHead = new Node<>();
        Node<Integer> equalHead = new Node<>();
        Node<Integer> greaterHead = new Node<>();

        Node<Integer> less = lessHead;
        Node<Integer> equal = equalHead;
        Node<Integer> greater = greaterHead;

        Node<Integer> tmp = list.getHead();

        while(tmp != null) {
            if(tmp.data < x) {
                less.next = tmp;
                less = less.next;
            } else if (tmp.data == x){
                equal.next = tmp;
                equal = equal.next;
            } else {
                greater.next = tmp;
                greater = greater.next;
            }
            tmp = tmp.next;
        }

        greater.next = null;
        equal.next = greaterHead.next;
        less.next = equalHead.next;
    }
}
