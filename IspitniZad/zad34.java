// Given a Binary Search Tree (BST), the task is to modify it so that all greater and equal values in the given BST are added to every node.

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

public class zad34 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.root = tree.insert(50, tree.root);
        tree.root = tree.insert(30, tree.root);
        tree.root = tree.insert(70, tree.root);
        tree.root = tree.insert(20, tree.root);
        tree.root = tree.insert(40, tree.root);
        tree.root = tree.insert(60, tree.root);
        tree.root = tree.insert(80, tree.root);

        System.out.print("Original BST (Inorder): ");
        tree.inorder(tree.root);
        System.out.println();

        int[] sum = {0};
        modifyBST(tree.root, sum);

        System.out.print("Modified BST (Inorder): ");
        tree.inorder(tree.root);
    }

    private static void modifyBST(BNode<Integer> r, int[] sum) {
        if (r == null) {
            return;
        }

        modifyBST(r.right, sum);

        sum[0] += r.info;
        r.info = sum[0];

        modifyBST(r.left, sum);
    }
}

