// Given a linked list, The task is to reverse alternate k nodes. 
// If the number of nodes left at the end of the list is fewer than k, reverse these remaining nodes.

// Example: 

// Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 , k = 2
// Output: 2 -> 1 -> 3 -> 4 -> 6 -> 5 . 

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

public class zad40 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {1, 2, 3, 4, 5};
            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();

            int k = 3;
            reverseKnodes(list, k);
            list.printList();
    }
            
    public static void reverseKnodes(SLinkedList<Integer> list, int k) {
        Node<Integer> tmp = list.getHead();
        Node<Integer> prevGroup = null;
        boolean reverse = true; 
    
        while (tmp != null) {
            Node<Integer> groupEnd = tmp;
            for (int i = 1; i < k && groupEnd.next != null; i++) {
                groupEnd = groupEnd.next;
            }
    
            Node<Integer> nextGroup = groupEnd.next;
    
            if (reverse) {
                Node<Integer> prev = null;
                Node<Integer> curr = tmp;
                Node<Integer> next = null;
                for (int i = 0; i < k && curr != null; i++) {
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
    
                if (prevGroup == null) {
                    list.setHead(groupEnd);
                } else {
                    prevGroup.next = groupEnd;
                }
    
                tmp.next = nextGroup;
                prevGroup = tmp; 
                tmp = nextGroup;
            } else {
                prevGroup = groupEnd;
                tmp = nextGroup;
            }
    
            reverse = !reverse;
        }
    }
}
