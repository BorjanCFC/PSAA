// Given a Binary Search Tree (BST) and a positive integer k, the task is to find the kth largest element in the Binary Search Tree.

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

public class zad49 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>(20);
        tree.root = tree.insert(8, tree.root);
        tree.root = tree.insert(22, tree.root);
        tree.root = tree.insert(4, tree.root);
        tree.root = tree.insert(12, tree.root);
        tree.root = tree.insert(10, tree.root);
        tree.root = tree.insert(14, tree.root);

        int k = 3;
        int result = kthLargest(tree.root, k);
        System.out.println("The " + k + "th largest element is: " + result);
    }

    public static int kthLargest(BNode<Integer> r, int k) {
        ArrayList<Integer> elements = new ArrayList<>();
        addElements(r, elements);
        
        Collections.sort(elements, Collections.reverseOrder());
        return elements.get(k - 1);
    }

    public static void addElements(BNode<Integer> r, ArrayList<Integer> elements) {
        if (r != null) {
            elements.add(r.info);
            addElements(r.left, elements); 
            addElements(r.right, elements);
        }
    }
}
