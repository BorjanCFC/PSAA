// Given a singly linked list, swap kth node from beginning with kth node from end. 
// Swapping of data is not allowed, only pointers should be changed. 

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

    public void deleteLast() {
        if (head == null) {
            System.out.println("Error.");
            return;
        }
        
        if (head.next == null) { 
            head = null;
            return;
        }
        
        Node<E> tmp = head;
        while (tmp.next.next != null) { 
            tmp = tmp.next;
        }
        
        tmp.next = null; 
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

public class zad39 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {1, 2, 3, 4, 5};
            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();

            int k = 4;
            swapKth(list, k);
            list.printList();
    }
            
    private static void swapKth(SLinkedList<Integer> list, int k) {

        if(k > list.size()) {
            return;
        }

        if(2 * k - 1 == list.size()) {
            return;
        }
        
        if(k == 1 || k == list.size()) {
            Node<Integer> first = list.getHead();
            Node<Integer> last = list.getHead();
            while(last.next != null) {
                last = last.next;
            }
            list.deleteFirst();
            list.deleteLast();

            list.insertFirst(last.data);
            list.insertLast(first.data);
            return;
        }

        Node<Integer> first = list.getHead();
        Node<Integer> firstPrev = null;
        Node<Integer> last = list.getHead();
        Node<Integer> lastPrev = null;
        Node<Integer> tmp = list.getHead();
        
        int k1 = k;
        int k2 = list.size() - k;

        while(k1 > 1) {
            first = first.next;
            k1--;
        }

        Node<Integer> firstNext = first.next;

        while(k2 > 0) {
            last = last.next;
            k2--;
        }

        Node<Integer> lastNext = last.next;

        while(tmp != first) {
            firstPrev = tmp;
            tmp = tmp.next;
        }

        tmp = list.getHead();

        while(tmp != last) {
            lastPrev = tmp;
            tmp = tmp.next;
        }

        firstPrev.next = last;
        last.next = firstNext;
        lastPrev.next = first;
        first.next = lastNext;
    }
}
