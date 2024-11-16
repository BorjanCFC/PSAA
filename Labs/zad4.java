// Да се напише функција која како аргумент добива единечно поврзана листа. Функцијата треба да провери дали елементите 
// на листата формираат палиндром, т.е. дали последниот и првиот, претпоследниот и вториот елемент, итн. се еднакви. 
// Задачата да се реши и со класата SLinkedList и со класата LinkedList.

package labVezbi;

import java.util.LinkedList;

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

public class zad4 {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();
        
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(4);
        list.insertLast(3);
        list.insertLast(2);
        list.insertLast(1);
        
        if (isPalindrom(list)) {
            System.out.println("Listata e palindrom");
            list.printList();
        } else {
            System.out.println("Listata ne e palindrom");
            list.printList();
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(1);
        
        if (isPalindromLinkedList(linkedList)) {
            System.out.println("LinkedList e palindrom");
            System.out.println(linkedList);
        } else {
            System.out.println("LinkedList ne e palindrom");
            System.out.println(linkedList);
        }

    }
    
    private static boolean isPalindrom(SLinkedList<Integer> list) {
        if (list.getHead() != null) {
            Node<Integer> tmp = list.getHead();
            SLinkedList<Integer> tmplist = new SLinkedList<>();
            int counter = 0;
            boolean check = false;
            
            while (tmp != null) {
                tmp = tmp.next;
                counter++;
            }
            
            if (counter % 2 == 0) {
                return false;
            }
            
            tmp = list.getHead();
            
            while (tmp != null) {
                tmplist.insertFirst(tmp.data);
                tmp = tmp.next;
            }
            
            Node<Integer> original = list.getHead();
            Node<Integer> reversed = tmplist.getHead();
            
            while (original != null && reversed != null) {
                if (original.data.equals(reversed.data)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                original = original.next;
                reversed = reversed.next;
            }
            
            return check;
        }
        
        return false;
    }

    private static boolean isPalindromLinkedList(LinkedList<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

}
