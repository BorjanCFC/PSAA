// Дадена е двострано поврзана листа чии што јазли содржат по еден знак (буква). Листата треба да се подели на 
// две резултантни листи, т.ш. во првата резултантна листа ќе бидат бидат сместени самогласките од влезната листа, а во втората – согласките. 
// Јазлите во резултантните листи се додаваат наизменично почнувајќи од почетокот и крајот на 
// влезната листа (т.е. прво се разгледува првиот елемент од листата и се додава во соодветната резултантна листа, па последниот, па вториот итн...)
// Во првиот ред од влезот се дадени буквите од кои се составени јазлите по редослед од влезната листа. 
// Во првиот ред од излезот треба да се испечатат јазлите по редослед од првата резултантна листа (т.е. самогласките), во вториот ред од втората (т.е. согласките)
// Име на класа (за Java): PodeliSamoglaski
// Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.
// Забелешка: При реализација на задачите МОРА да се користат дадените структури, а не да користат помошни структури како низи или сл.
 
// Input:
// a b c d e f g h i j
// Output:
// a i e 
// j b c h d g f

class Node<E> {
    protected E data;
    protected Node<E> prev, next;

    public Node() {
        data = null;
        prev = next = null;
    }

    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class DLinkedList<E> {
    private Node<E> head, tail;

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public DLinkedList() {
        head = null;
        tail = null;
    }

    public void insertFirst(E e) {
        Node<E> first = new Node<>(e, null, head);

        if (head != null) {
            head.prev = first;
        }

        if (tail == null) {
            tail = first;
        }

        head = first;
    }

    public void insertLast(E e) {
        if (head != null) {
            Node<E> last = new Node<>(e, tail, null); 
            tail.next = last;
            tail = last;
        } else {
            this.insertFirst(e); 
        }
    }

    public void printList() {
        Node<E> tmp = head;
    
        if (tmp == null) {
            System.out.println("Listata e prazna.");
            return;
        }
    
        System.out.print("Lista: ");
        while (tmp != null) {
            System.out.print(tmp.data);
            
            if (tmp.next != null) {
                System.out.print(" <-> ");
            }
    
            tmp = tmp.next;
        }
        System.out.println();
    }
}


public class zad6 {
    public static void main(String[] args) {
        DLinkedList<Character> list = new DLinkedList<>();

        list.insertLast('a');
        list.insertLast('b');
        list.insertLast('c');
        list.insertLast('d');
        list.insertLast('e');
        list.insertLast('f');
        list.insertLast('g');
        list.insertLast('h');
        list.insertLast('i');
        list.insertLast('j');

        func(list);
    }
        
    private static void func(DLinkedList<Character> list) {
        DLinkedList<Character> samoglaski = new DLinkedList<>();
        DLinkedList<Character> soglaski = new DLinkedList<>();

        Node<Character> tmp = list.getHead();
        Node<Character> pom = list.getTail();

        while(tmp != pom && tmp != pom.next) {
            if(tmp.data == 'a' || tmp.data == 'e' || tmp.data == 'i' || tmp.data == 'o' || tmp.data == 'u') {
                samoglaski.insertLast(tmp.data);
            } else {
                soglaski.insertLast(tmp.data);
            }

            if(pom.data == 'a' || pom.data == 'e' || pom.data == 'i' || pom.data == 'o' || pom.data == 'u') {
                samoglaski.insertLast(pom.data);
            } else {
                soglaski.insertLast(pom.data);
            }

            tmp = tmp.next;
            pom = pom.prev;
        }

        if(tmp.data == 'a' || tmp.data == 'e' || tmp.data == 'i' || tmp.data == 'o' || tmp.data == 'u') {
            samoglaski.insertLast(tmp.data);
        } else {
            soglaski.insertLast(tmp.data);
        }

        samoglaski.printList();
        soglaski.printList();

    }
}
