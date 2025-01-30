// Да се напише функција која како аргумент добива единечно поврзана листа и цел број n.
// Функцијата треба да ги измине елементите на позиции n, 2*n,..., од листата и да провери дали
// тие формираат растечка низа. Ако целиот број е поголем од димензијата на листата, функцијата
// да врати -1. Ако од листата може да се земе само еден елемент или ако земените елементи
// формираат растечка низа, функцијата да врати 1. Ако земените елементи не формираат
// растечка низа, функцијата да врати 0.
// Дополнително, да се напише и главна програма во која ќе се тестира работата на функцијата. 

import java.util.ArrayList;


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

public class zad9 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
            int[] values = {1, 2, 3, 4, 20, 6, 7, 8, 9};

            for (int v : values) list.insertLast(v);
    
            System.out.println("Original list:");
            list.printList();
    
            int n = 2;
            System.out.println("Funkcijata vrati " + checkList(list, n));
    }
            
    private static int checkList(SLinkedList<Integer> list, int n) {
        
        if(n > list.size()) {
            return -1;
        }

        if(list.size() == 1) {
            return 1;
        }

        ArrayList<Integer> elements = new ArrayList<>();

        Node<Integer> tmp = list.getHead();

        int i = n;
        while(tmp != null) {
            tmp = tmp.next;
            i--;
            if (i == 0) {
                elements.add(tmp.data);
                i = n;
            }
        }
    
        System.out.println(elements);
        boolean result = checkElements(elements);

        if (result) {
            return 1;
        } else {
            return 0;
        }

    }
        
    private static boolean checkElements(ArrayList<Integer> elements) {
        int element1, element2;

        for(int i = 0; i < elements.size() - 1; i++) {
            element1 = elements.get(i);
            element2 = elements.get(i + 1);

            if (element1 > element2) {
                return false;
            }

        }

        return true;
    }
}
