// Со помош на единечно поврзана листа да се имплементира распоред на патници во воз.
// Притоа, еден јазол во единечната листа претставува еден вагон во возот. За возот треба да се
// знае колку вагони има и колку од нив се вагони од прва класа. Во еден вагон можат да се качат
// не повеќе од 20 патници. За секој патник се знае името и презимето, бројот на купениот билет
// и дали билетот е или не е за во прва класа. Корисникот треба да ги внесува патниците еден по
// еден се додека не ги внесе сите патници кои имаат купено билет. Патниците треба да се
// распоредат така што ќе се задржи рамномерна дистрибуција на патници по вагони – имено, во
// сите вагони треба да има што е можно поеднаков број на патници. При распределбата треба
// да се внимава патниците да се сместат во вагон чија класа одговара на класата на купениот
// билет.
// Логиката да се реализира со функција. Да се напише и главна програма која ќе ја тестира
// работата на функцијата.

import java.util.ArrayList;

class patnik {
    String ime;
    String prezime;
    int brBilet;
    boolean prvaKlasa;

    public patnik(String ime, String prezime, int brBilet, boolean prvaKlasa) {
        this.ime = ime;
        this.prezime = prezime;
        this.brBilet = brBilet;
        this.prvaKlasa = prvaKlasa;
    }
}

class vagon {
    ArrayList<patnik> patnici;
    boolean prvaKlasa;

    public vagon(boolean prvaKlasa) {
        this.patnici = new ArrayList<>();
        this.prvaKlasa = prvaKlasa;
    }

    public boolean dodadiPatnik(patnik Patnik) {
        if (patnici.size() < 20) {
            patnici.add(Patnik);
            return true;
        }
        return false;
    }
}

class voz {
    int vagoni;
    int prvKlasaKolku;

    public voz(int vagoni, int prvaKlasaKolku) {
        this.vagoni = vagoni;
        this.prvKlasaKolku = prvaKlasaKolku;
    }

}

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

public class zad1 {

    public static void main(String[] args) {
        
    }
}