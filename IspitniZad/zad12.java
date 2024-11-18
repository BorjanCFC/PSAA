/*
Оперативниот систем чува структура за опис на процесите кои се извршуваат на 
него, PCB (Process Control Block). За секој процес познато е неговото име, 
ID бројот, приоритетот, како и ID бројот на следниот процес со помал приоритет 
(ова поле при декларирање на процесот е празно). За управување со процесите, 
системот одржува редица на приоритет (priority queue), во која се наоѓаат сите 
процеси спремни за извршување, сортирани по опаѓачки приоритет. На почеток на 
редицата се наоѓа процесот кој тековно се извршува на системот. Ако ниту еден 
процес не е активен, редицата содржи специјален idle процес (со ID = 0 и 
приоритет = 0). 
Да се напише функција void insert_into_readyQueue (int ID), која ќе го вметне 
процесот со даден ID број во редицата на приоритет. Ако повеќе процеси имаат 
ист приоритет, тогаш овој процес да се вметне после последниот процес со ист 
приоритет. Ако процесот веќе се наоѓа во редицата, не се случува ништо. Да се 
променат и потребните ID броеви на следбеникот. Да се напише и главна програма 
во која ќе се дефинираат неколку процеси како во примерот и со секој од нив ќе 
се повика функцијата insert_into_readyQueue (int ID). На крај да се измине 
редицата и да се отпечатат имињата на процесите во неа. 
Забелешка: структурата која ќе ја опишува редицата на приоритет изберете ја сами. 
Пример: Ако се дефинирани следниве процеси: PCB p1(P1, 1, 120), 
PCB p2(P2, 2, 160), PCB p3(P3, 3, 140), PCB p4(P4, 4, 120), после повик на 
функцијата со секој од нив, редицата го има следниов редослед: 
PriorityQueue: P2 -> P3 -> P1 -> P4 -> Idle.
*/

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

class PCB {
    String name;
    int ID;
    int priority;
    int nextLowerPriorityID;
    
    public PCB(String name, int ID, int priority) {
        this.name = name;
        this.ID = ID;
        this.priority = priority;
    }
}

public class zad12 {
    public static void main(String[] args) {
        DLinkedList<PCB> readyQueue = new DLinkedList<PCB>();
        
        PCB idleProcess = new PCB("Idle", 0, 0);
        insert_into_readyQueue(readyQueue, idleProcess);
        
        PCB p1 = new PCB("P1", 1, 120);
        PCB p2 = new PCB("P2", 2, 160);
        PCB p3 = new PCB("P3", 3, 140);
        PCB p4 = new PCB("P4", 4, 120);
        
        insert_into_readyQueue(readyQueue, p1);
        insert_into_readyQueue(readyQueue, p2);
        insert_into_readyQueue(readyQueue, p3);
        insert_into_readyQueue(readyQueue, p4);
        
        Node<PCB> tmp = readyQueue.getHead();
        while (tmp != null) {
            System.out.print(tmp.data.name + " <-> ");
            tmp = tmp.next;
        }
    }

    private static void insert_into_readyQueue(DLinkedList<PCB> readyQueue, PCB pcb) {
        if (readyQueue.getHead() == null) {
            readyQueue.insertFirst(pcb);
            return;
        }
    
        Node<PCB> currentNode = readyQueue.getHead();
        Node<PCB> prevNode = null;
    
        while (currentNode != null && currentNode.data.priority >= pcb.priority) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    
        if (prevNode == null) {
            readyQueue.insertFirst(pcb);
        } else if (currentNode == null) {
            readyQueue.insertLast(pcb);
        } else {
            Node<PCB> newNode = new Node<>(pcb, prevNode, currentNode);
            prevNode.next = newNode;
            if (currentNode != null) {
                currentNode.prev = newNode;
            }
        }
    }
    
    
}
