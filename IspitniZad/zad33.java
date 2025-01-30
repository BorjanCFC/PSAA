// Given two values n1 and n2 where n1 < n2 and a root pointer to a Binary Search Tree. 
// The task is to find all the keys of the tree in the range n1 to n2 in increasing order.

import java.util.ArrayList;

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

}

public class zad33 {
    public static void main(String[] args) {
        BSTree<Integer> drvo1 = new BSTree<>();
        drvo1.root = drvo1.insert(22, drvo1.root);
        drvo1.root = drvo1.insert(12, drvo1.root);
        drvo1.root = drvo1.insert(30, drvo1.root);
        drvo1.root = drvo1.insert(8, drvo1.root);
        drvo1.root = drvo1.insert(20, drvo1.root);

        int n1 = 10;
        int n2 = 22;

        ArrayList<Integer> list = findRange(drvo1.root, n1, n2);
        list.sort(null);
        System.out.println(list);
    }
        
    private static ArrayList<Integer> findRange(BNode<Integer> r, int n1, int n2) {
        ArrayList<Integer> list = new ArrayList<>();
        fillList(r, n1, n2, list);
        return list;
    }
    
    private static void fillList(BNode<Integer> node, int n1, int n2, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        
        if (node.info > n1) {
            fillList(node.left, n1, n2, list);
        }
        
        if (node.info < n2) {
            fillList(node.right, n1, n2, list);
        }

        if (node.info >= n1 && node.info <= n2) {
            list.add(node.info);
        }
        
    }

}
