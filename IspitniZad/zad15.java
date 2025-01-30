// Дадена е еднострана поврзана листа од броеви со дупликати. За дадена вредност на info поле во листата, да се обезбеди бројот 
// на повторувања на тој број да биде парен. Доколку за дадената вредност на инфо полето, бројот на елементи со таа вредност е непарен, 
// треба да се додаде истиот елемент пред првото појавување на елементот со таа вредност  во листата.


// Забелешка: 0 е парен број. 


// Влез:

// Во првиот ред се внесуваат бројот на елементи во листата, па потоа самите елементи, а на крај се внесува бројот (вредноста) кој ќе се осигураме дека се појавува парен број на пати.

// Излез:

// Листата со парен број на дупликати за дадената вредност


// Примери:

// Влез:
// 6
// 1 2 5 7 9 12
// 7

// Излез:
// 1 2 5 7 7 9 12

// Влез:
// 8
// 7 1 2 7 5 9 7 12
// 7

// Излез:
// 7 7 1 2 7 5 9 7 12

import java.util.Scanner;

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

public class zad15 {
    public static void main(String[] args) {
        
        System.out.print("Vnesi dolzina na listata: ");
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        int n;

        SLinkedList<Integer> list1 = new SLinkedList<Integer>();
        n = scan.nextInt();

        for(int i = 0; i<n; i++) {
            System.out.print("Vnesi element: ");
            list1.insertLast(scan.nextInt());
        }

        int br = scan.nextInt();

        change(list1,br);
        
        list1.printList();
        
    }
        
    private static void change(SLinkedList<Integer> list1, int br) {
        Node<Integer> tmp = list1.getHead();
        Node<Integer> prev = null;
        int counter = 0;

        while(tmp != null) {
            if(tmp.data == br) {
                counter++;
            }
            tmp = tmp.next;
        }

        tmp = list1.getHead();

        if(counter % 2 == 0) {
            System.out.println("Elementot go ima vo listata " + counter + " pati");
        } else {
            while(tmp != null) {
                if(list1.getHead().data == br) {
                    list1.insertFirst(br);
                    break;
                }

                if(tmp.data == br) {
                    Node<Integer> pom = new Node<>();
                    prev.next = pom;
                    pom.next = tmp;
                    pom.data = br;
                    break;
                }

                prev = tmp;
                tmp = tmp.next;

            }
        }
    }
}
