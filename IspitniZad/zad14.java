// Да се напише функција која како аргумент добива две единечно поврзани листи и од нив
// формира нова листа на тој начин што: Елементите од првата листа се земаат од почетокот на
// листата, а елементите од втората листа се земаат од крајот. Притоа, за секој пар земени
// елементи (еден од првата, еден од втората листа) се прави проверка дали разликата помеѓу
// двата елементи е прост број и доколку тоа е случајот помалиот од двата елементи се сместува
// во крајната листа. Доколку разликата меѓу елементите е сложен број во новата листа не се
// додава ништо. Листите се изминуваат се додека не се измине помалата од двете листи.
// Да се напише главна програма во која од тастатура ќе се внесат две единечно поврзани листи и
// ќе се повика функцијата. Во главната програма да се отпечати добиената листа.


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

public class zad14 {
    public static void main(String[] args) {
        SLinkedList<Integer> list1 = new SLinkedList<>();
        SLinkedList<Integer> list2 = new SLinkedList<>();

        list1.insertLast(10);
        list1.insertLast(15);
        list1.insertLast(20);
        list1.insertLast(25);
        list1.insertLast(30);
        list1.insertLast(35);
        list1.insertLast(40);
        list1.insertLast(45);
        list1.insertLast(50);
        list1.insertLast(55);

        list2.insertLast(5);
        list2.insertLast(12);
        list2.insertLast(18);
        list2.insertLast(30);
        list2.insertLast(7);
        list2.insertLast(14);
        list2.insertLast(22);
        list2.insertLast(19);

        System.out.println("Prva lista");
        list1.printList();

        System.out.println("Vtora lista");
        list2.printList();

        SLinkedList<Integer> list3 = combineLists(list1, list2);
        
        System.out.println("Rezultantna lista");
        list3.printList();
                
    }
        
    private static SLinkedList<Integer> combineLists(SLinkedList<Integer> list1, SLinkedList<Integer> list2) {
        
        SLinkedList<Integer> resultList = new SLinkedList<>();

        list2.reverse();

        Node<Integer> tmp = list1.getHead();
        Node<Integer> tmp1 = list2.getHead();
        int razlika;

        while(tmp != null && tmp1 != null) {
            if(tmp.data > tmp1.data) {
                razlika = (tmp.data - tmp1.data);
                if(prostBroj(razlika)) {
                    resultList.insertLast(tmp1.data);
                } 
            } else if (tmp.data < tmp1.data) {
                razlika = (tmp1.data - tmp.data);
                if(prostBroj(razlika)) {
                    resultList.insertLast(tmp.data);
                } 
            }
            
            tmp = tmp.next;
            tmp1 = tmp1.next;
        }

        return resultList;
    }

    private static boolean prostBroj(int broj) {
        int counter = 0;

        if (broj <= 1) {
            return false;
        }

        for (int i = 2; i < (broj / 2); i++) {
            if(broj % i == 0) {
                counter++;
            }
        }

        
        return counter == 0;
    }
}
