// Given a binary tree, the task is to check if it is a Sum Tree. 
// A Sum Tree is a Binary Tree where the value of a node is equal to the sum of the nodes present in its left subtree and right subtree. 
// An empty tree is Sum Tree and the sum of an empty tree can be considered as 0. A leaf node is also considered a Sum Tree.

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

public class zad31 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(26);
        tree.addChild(tree.root, BNode.LEFT, 10);
        tree.addChild(tree.root, BNode.RIGHT, 3);

        tree.addChild(tree.root.left, BNode.LEFT, 2);
        tree.addChild(tree.root.left, BNode.RIGHT, 6);

        tree.addChild(tree.root.right, BNode.RIGHT, 3);

        boolean result = isSumTree(tree.root);

        System.out.println("Is the tree a Sum Tree? " + result);
    }

    public static int sum(BNode<Integer> r) {
        if(r == null) {
            return 0;
        }

        return r.info + sum(r.left) + sum(r.right);
    }

    public static boolean isSumTree(BNode<Integer> r) {
        if(r == null || (r.left == null & r.right == null)) {
            return true;
        }

        int leftSum, rightSum;

        leftSum = sum(r.left);
        rightSum = sum(r.right);

        return (r.info == leftSum + rightSum) && isSumTree(r.left) && isSumTree(r.right);
    }
}
