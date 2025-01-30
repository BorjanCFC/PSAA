// Given a singly linked list, the task is to remove all the nodes with any node on their right whose value is greater and return the head of the modified linked list.

// Examples: 

// Input: head: 12->15->10->11->5->6->2->3
// Output: 15->11->6->3
// Explanation: Node with value 12 , 10, 5, and 2 will be deleted as the greater value is present on right side of nodes.

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
        
}

public class zad37 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {12, 15, 10, 11, 5, 6, 2, 3};
            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();
    
            deleteRightNodes(list);
            list.printList();
    }
            
    private static void deleteRightNodes(SLinkedList<Integer> list) {
        Node<Integer> tmp = list.getHead();
        Node<Integer> next = tmp.next;
        Node<Integer> prev = null;

        while(next != null) {
            if(prev == null && tmp.data < next.data) {
                tmp = next;
                next = next.next;
                list.deleteFirst();
            } else if (tmp.data < next.data) {
                prev.next = next;
                tmp = next;
                next = next.next;
            } else {
                prev = tmp;
                tmp = tmp.next;
                next = next.next;
            }
        }
    }
}
