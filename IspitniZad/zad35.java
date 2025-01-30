// Given two Binary Search Trees consisting of unique positive elements, the task is to check whether the two BSTs contain the same set of elements or not. 
// The structure of the two given BSTs can be different. 

import java.util.ArrayList;
import java.util.Collections;

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

public class zad35 {
    public static void main(String[] args) {
        BSTree<Integer> tree1 = new BSTree<>();
        tree1.root = tree1.insert(50, tree1.root);
        tree1.root = tree1.insert(30, tree1.root);
        tree1.root = tree1.insert(70, tree1.root);
        tree1.root = tree1.insert(20, tree1.root);
        tree1.root = tree1.insert(40, tree1.root);
        tree1.root = tree1.insert(60, tree1.root);
        tree1.root = tree1.insert(80, tree1.root);

        BSTree<Integer> tree2 = new BSTree<>();
        tree2.root = tree2.insert(40, tree2.root);
        tree2.root = tree2.insert(20, tree2.root);
        tree2.root = tree2.insert(30, tree2.root);
        tree2.root = tree2.insert(70, tree2.root);
        tree2.root = tree2.insert(50, tree2.root);
        tree2.root = tree2.insert(60, tree2.root);
        tree2.root = tree2.insert(80, tree2.root);

        System.out.print("BST 1 (Inorder): ");
        tree1.inorder(tree1.root);
        System.out.println();

        System.out.print("BST 2 (Inorder): ");
        tree2.inorder(tree2.root);
        System.out.println();

        boolean result = sameElements(tree1.root, tree2.root);
        System.out.println("Do the two BSTs contain the same elements? " + result);
    }

    public static boolean sameElements(BNode<Integer> r1, BNode<Integer> r2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        fillList(r1, list1);
        fillList(r2, list2);

        Collections.sort(list1);
        Collections.sort(list2);

        return list1.equals(list2);
    }

    private static void fillList(BNode<Integer> r, ArrayList<Integer> list) {
        if (r == null) {
            return;
        }

        list.add(r.info); 
        fillList(r.left, list);
        fillList(r.right, list);
    }
}
