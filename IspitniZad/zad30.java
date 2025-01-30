// You are given a binary tree and a given sum. 
// The task is to check if there exists a subtree whose sum of all nodes is equal to the given sum.

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

public class zad30 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(1);
        tree.addChild(tree.root, BNode.LEFT, 3);
        tree.addChild(tree.root, BNode.RIGHT, 6);
        tree.addChild(tree.root.left, BNode.LEFT, 5);
        tree.addChild(tree.root.left, BNode.RIGHT, 9);
        tree.addChild(tree.root.right, BNode.LEFT, 8);

        int targetSum = 17; 
        boolean result = subTreeTargetSum(tree.root, targetSum);
        System.out.println("Exists a subtree with sum " + targetSum + ": " + result);
    }


    public static boolean subTreeTargetSum(BNode<Integer> r, int target) {
        boolean[] foundSum = new boolean[1];

        sumSubtree(r, foundSum, target);
        
        return foundSum[0];
    }
        
    private static int sumSubtree(BNode<Integer> r, boolean[] foundSum, int target) {

        if(r == null) {
            return 0;
        }

        int sum = 0;

        sum += r.info + sumSubtree(r.left, foundSum, target) + sumSubtree(r.right, foundSum, target);

        if(sum == target) {
            foundSum[0] = true;
        }

        return sum;

    }

}