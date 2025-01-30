// Given a binary tree, the task is to check for every node, its value is equal to the sum of values of its immediate left and right child. 
// For NULL values, consider the value to be 0. Also, leaves are considered to follow the property.

import java.util.Stack;

class BNode<E> {
    public E info;
    public BNode<E> left, right;
    static int LEFT = 1, RIGHT = 2;
    
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

class BTree<E> {
    public BNode<E> root;
    
    public BTree() {
        root = null;
    }
    
    public BTree(E info) {
        root = new BNode<E>(info);
    }
    
    public BNode<E> addChild(BNode<E> node, int where, E info) {
        BNode<E> tmp = new BNode<E>(info);
        
        if (where == BNode.LEFT) {
            if (node.left != null) {
                return null;
            }
            node.left = tmp;
        } else {
            if (node.right != null) {
                return null;
            }
            node.right = tmp;
        }
        
        return tmp;
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.println(r.info);
            inorder(r.right);
        }
    }
    
    public void preorder(BNode<E> r) {
        if (r != null) {
            System.out.println(r.info);
            preorder(r.left);
            preorder(r.right);
        }
    }
    
    public void postorder(BNode<E> r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.println(r.info);
        }
    }
    
    public void inorderUsingStack(BNode<E> r) {
        Stack<BNode<E>> s = new Stack<>();
        
        BNode<E> p = root;
        
        while (true) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }
            
            if (s.isEmpty()) {
                break;
            }
            
            p = s.pop();
            
            System.out.println(p.info.toString());
            
            p = p.right;
        }
    }

}

public class zad47 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(35);

        BNode<Integer> root = tree.root;

        // Create the tree structure
        BNode<Integer> leftChild = tree.addChild(root, BNode.LEFT, 20);
        BNode<Integer> rightChild = tree.addChild(root, BNode.RIGHT, 15);

        tree.addChild(leftChild, BNode.LEFT, 15);
        tree.addChild(leftChild, BNode.RIGHT, 5);

        tree.addChild(rightChild, BNode.LEFT, 10);
        tree.addChild(rightChild, BNode.RIGHT, 5);

        System.out.println("Does the tree satisfy the sum property? " + checkSum(root));
    }

    public static boolean checkSum(BNode<Integer> r) {

        if(r == null || (r.left == null && r.right == null)) {
            return true;
        }

        int sum = 0;

        if(r.left != null && r.right != null) {
            sum += r.left.info + r.right.info;

            if(sum != r.info) {
                return false;
            }

        }

        return checkSum(r.left) && checkSum(r.right);

    }
}
