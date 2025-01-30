// Given two numbers represented as two lists, the task is to return the sum of two lists.

// Note: There can be leading zeros in the input lists, but there should not be any leading zeros in the output list.

// Examples:

// Input: num1 = 4 -> 5, num2 = 3 -> 4 -> 5
// Output: 3 -> 9 -> 0 
// Explanation: Sum of 45 and 345 is 390.

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

public class zad38 {
    public static void main(String[] args) {
        SLinkedList<Integer> list1 = new SLinkedList<>();
        int[] values1 = {1, 2, 3};
        for (int v : values1) list1.insertLast(v);
        

        SLinkedList<Integer> list2 = new SLinkedList<>();
        int[] values2 = {9, 9, 9};
        for (int v : values2) list2.insertLast(v);


        System.out.println("List1:");
        list1.printList();
        System.out.println();
        System.out.println("List2:");
        list2.printList();
        
        System.out.println();
        System.out.println("List3:");
        SLinkedList<Integer> list3 = addTwoLists(list1, list2);
        list3.printList();
    }
        
    private static SLinkedList<Integer> addTwoLists(SLinkedList<Integer> list1, SLinkedList<Integer> list2) {
        SLinkedList<Integer> result = new SLinkedList<>();
    
        trimZeros(list1);
        trimZeros(list2);
    
        list1.reverse();
        list2.reverse();
    
        Node<Integer> tmp1 = list1.getHead();
        Node<Integer> tmp2 = list2.getHead();
        int carry = 0;
    
        while (tmp1 != null || tmp2 != null) {
            int sum = carry;
    
            if (tmp1 != null) {
                sum += tmp1.data;
                tmp1 = tmp1.next;
            }
            if (tmp2 != null) {
                sum += tmp2.data;
                tmp2 = tmp2.next;
            }
    
            result.insertFirst(sum % 10);
            carry = sum / 10;
        }

        if(carry > 0) {
            result.insertFirst(carry);
        }
    
        return result;
    }
    
        
    private static void trimZeros(SLinkedList<Integer> list) {
        Node<Integer> tmp = list.getHead();

        while(tmp != null && tmp.data == 0) {
            tmp = tmp.next;
            list.deleteFirst();
        }

    }

    
}
