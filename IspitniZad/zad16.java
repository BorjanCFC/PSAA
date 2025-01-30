// Дадена е еднострано поврзана листа од цели броеви. Истата треба да се трансформира за да ги задоволува условите да биде цик-цак секвенца. 
// Односно, после секој позитивен број да следува негативен и обратно, и не треба да содржи нули. 
// Ова треба да се направи со тоа што ќе се измине листата и за секој пар од последователни елементи ќе се направи следното:

// Доколку и двата елементи се позитивни, се брише вториот.
// Доколку и двата елементи се негативни, меѓу нив се вметнува нов елемент со вредност што ќе биде апсолутната вредност на првиот.
// Доколку било кој од елементите е 0, се брише истиот (доколку 2та се 0, треба да се избришат и 2та елементи).

// Влез:
// 4 -> -3 -> -6 -> 0 -> 7 -> 7 -> -2 -> 5

// Излез:
// 4 -> -3 -> 3 -> -6 -> 7 -> -2 -> 5

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

public class zad16 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();

        list.insertLast(4);
        list.insertLast(-3);
        list.insertLast(-6);
        list.insertLast(0);
        list.insertLast(0);
        list.insertLast(7);
        list.insertLast(7);
        list.insertLast(-2);
        list.insertLast(5);


        list.printList();
        System.out.println();

        makeZigZag(list);
        
        list.printList();
    }
        
    private static void makeZigZag(SLinkedList<Integer> list) {
        Node<Integer> tmp = list.getHead();
        Node<Integer> prev = null;
        Node<Integer> nextEl = tmp.next;

        while(nextEl != null) {
            if(tmp.data > 0 && nextEl.data > 0) {
                tmp.next = nextEl.next;
                nextEl = nextEl.next;
            } else if(tmp.data > 0 && nextEl.data < 0) {
                prev = tmp;
                tmp = tmp.next;
                nextEl = nextEl.next;
            } else if(tmp.data < 0 && nextEl.data < 0) {
                Node<Integer> novEl = new Node<>();
                novEl.data = tmp.data * -1;
                tmp. next = novEl;
                novEl.next = nextEl;
                prev = tmp;
                tmp = novEl;
            } else if(tmp.data < 0 && nextEl.data > 0) {
                prev = tmp;
                tmp = tmp.next;
                nextEl = nextEl.next;
            } else if(tmp.data == 0 && nextEl.data != 0) {
                prev.next = nextEl;
                tmp = nextEl;
                nextEl = nextEl.next;
            } else if(tmp.data != 0 && nextEl.data == 0) {
                tmp.next = nextEl.next;
                nextEl = nextEl.next;
            } else {
                prev = nextEl.next;
                tmp = nextEl.next;
                nextEl = nextEl.next.next;
            }
        }
    }
}
