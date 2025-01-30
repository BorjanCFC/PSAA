// Да се напише функција која како аргументи добива бинарно пребарувачко дрво и единечно поврзана листа. 
// Функцијата треба да ги одреди и врати сите елементи од бинарното пребарувачко дрво кои се лево дете на својот родител и кои во единечно поврзаната листа имаат непарен претходник.
// Да се напише главна програма во која ќе се тестира работата на функцијата. Во главната програма да се испечати резултатот добиен од функцијата.

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

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;
    
    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
    
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;
    
    public BSTree() {
        root = null;
    }
    
    public BSTree(E info) {
        root = new BNode<>(info);
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }
    
    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode<>(info);
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = insert(info, r.left);
        } else if (comp > 0) {
            r.right = insert(info, r.right);
        }
        
        return r;
    }
    
    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }
    
    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else {
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }
        
        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }
}

public class zad57 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.root = tree.insert(20, tree.root);
        tree.root = tree.insert(10, tree.root);
        tree.root = tree.insert(30, tree.root);
        tree.root = tree.insert(5, tree.root);
        tree.root = tree.insert(15, tree.root);
        tree.root = tree.insert(25, tree.root);
        tree.root = tree.insert(35, tree.root);
        tree.root = tree.insert(3, tree.root);
        tree.root = tree.insert(7, tree.root);

        System.out.println("Drvoto vo inorder:");
        tree.inorder(tree.root);
        System.out.println();

        SLinkedList<Integer> list = new SLinkedList<>();
        list.insertLast(1);
        list.insertLast(5);
        list.insertLast(3);
        list.insertLast(7);
        list.insertLast(2);
        list.insertLast(15);
        list.insertLast(10);

        System.out.println("Listata:");
        list.printList();

        SLinkedList<Integer> result = findElements(tree, list);

        System.out.print("Elementite se: ");
        result.printList();
    }
        
    private static SLinkedList<Integer> findElements(BSTree<Integer> tree, SLinkedList<Integer> list) {
        if (tree == null || tree.root == null) {
            System.out.println("Nevalidno drvo");
        }
    
        if (list == null || list.getHead() == null) {
            System.out.println("Nevalidna lista");
        }
    
        SLinkedList<Integer> resultList = new SLinkedList<>();
    
        SLinkedList<Integer> leftChildren = new SLinkedList<>();
        findLeftChildren(tree.root, leftChildren);
    
        Node<Integer> tmp = list.getHead();
        while (tmp != null && tmp.next != null) {
            if (tmp.data % 2 == 1 && contains(leftChildren, tmp.next.data)) {
                resultList.insertLast(tmp.next.data);
            }
            tmp = tmp.next;
        }
    
        return resultList;
    }
    
    private static boolean contains(SLinkedList<Integer> list, int value) {
        Node<Integer> tmp = list.getHead();
        while (tmp != null) {
            if (tmp.data == value) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
    
    private static void findLeftChildren(BNode<Integer> node, SLinkedList<Integer> leftChildren) {
        if (node == null) {
            return;
        }
    
        if (node.left != null) {
            leftChildren.insertLast(node.left.info);
        }
    
        findLeftChildren(node.left, leftChildren);
        findLeftChildren(node.right, leftChildren);
    }
}