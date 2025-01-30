// Во класата за бинарно пребарувачко дрво да се напише функција со дефиниција BNode<E> findLeft (BNode<E> root, E value, int level). 
// Функцијата во бинарното пребарувачко дрво треба да го одреди левиот сосед (ако лев сосед постои) на јазол со вредност value кој се 
// наоѓа на длабочина level. Лев сосед на јазол е оној јазол кој се наоѓа на иста длабочина во дрвото и кој во inorder итерацијата се наоѓа пред јазолот со вредност value.
// Доколку во бинарното пребарувачко дрво не постои јазол со вредност value или истиот не се наоѓа на длабочина level, 
// тогаш функцијата враќа null. Доколку во бинарното пребарувачко дрво постои бараниот јазол, но истиот нема лев сосед, тогаш функцијата враќа null.
// Да се напише главна програма во која ќе се креира бинарно пребарувачко дрво и ќе се тестира работата на функцијата.
// Напомена: Форматот со кој е зададена функцијата е фиксно барање кое треба да се исполни.

import java.util.Stack;

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
        if (comp < 0) { //left
            r.left = insert(info, r.left);
        } else if (comp > 0) { //right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
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
        } else { // brishi go jazolot info
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

    public BNode<E> findLeft(BNode<E> root, E value, int level) {
        if (root == null) {
            return null;
        }
    
        Stack<BNode<E>> stack = new Stack<>(); 
        Stack<Integer> levels = new Stack<>();
        BNode<E> leftNeighbor = null; 
        int currentLevel = 0; 
    
        stack.push(root);
        levels.push(currentLevel); 
    
        while (!stack.isEmpty()) { 
            BNode<E> current = stack.pop(); 
            currentLevel = levels.pop(); 
    
            if (currentLevel == level) {
                if (current.info.compareTo(value) == 0) {
                    return leftNeighbor;
                }
                leftNeighbor = current; 
            }
    
            if (current.right != null) {
                stack.push(current.right);
                levels.push(currentLevel + 1);
            }
            if (current.left != null) {
                stack.push(current.left);
                levels.push(currentLevel + 1);
            }
        }
    
        return null; 
    }
    
}

public class zad56 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        
        tree.root = tree.insert(10, tree.root);
        tree.root = tree.insert(6, tree.root);
        tree.root = tree.insert(12, tree.root);
        tree.root = tree.insert(5, tree.root);
        tree.root = tree.insert(7, tree.root);
        tree.root = tree.insert(11, tree.root);
        tree.root = tree.insert(3, tree.root);
        tree.root = tree.insert(9, tree.root);

        // Proverka za jazol 12 na nivo 1
        BNode<Integer> result = tree.findLeft(tree.root, 12, 1);
        if (result != null) {
            System.out.println("Jazolot 12 ima lev sosed: " + result.info);
        } else {
            System.out.println("Jazolot 12 nema lev sosed.");
        }

        // Proverka za jazol 7 na nivo 2
        result = tree.findLeft(tree.root, 7, 2);
        if (result != null) {
            System.out.println("Jazolot 7 ima lev sosed: " + result.info);
        } else {
            System.out.println("Jazolot 7 nema lev sosed.");
        }

        // Proverka za jazol 3 na nivo 3
        result = tree.findLeft(tree.root, 3, 3);
        if (result != null) {
            System.out.println("Jazolot 3 ima lev sosed: " + result.info);
        } else {
            System.out.println("Jazolot 3 nema lev sosed.");
        }

        result = tree.findLeft(tree.root, 11, 2);
        if (result != null) {
            System.out.println("Jazolot 11 ima lev sosed: " + result.info);
        } else {
            System.out.println("Jazolot 11 nema lev sosed.");
        }

        result = tree.findLeft(tree.root, 5, 2);
        if (result != null) {
            System.out.println("Jazolot 5 ima lev sosed: " + result.info);
        } else {
            System.out.println("Jazolot 5 nema lev sosed.");
        }
    }
}


