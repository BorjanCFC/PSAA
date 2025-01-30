// Given a Binary Tree, the task is to find the difference between the sum of nodes at the odd level and the sum of nodes at the even level.

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

public class zad53 {

    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(10);
        tree.addChild(tree.root, BNode.LEFT, 20);
        tree.addChild(tree.root, BNode.RIGHT, 30);
        tree.addChild(tree.root.left, BNode.LEFT, 40);
        tree.addChild(tree.root.left, BNode.RIGHT, 60);

        int result = getLevelDiff(tree.root);
        System.out.println("Difference between sum of nodes at odd level and even level: " + result);
    }

    private static int getLevelDiff(BNode<Integer> r) {
        if(r == null) {
            return 0;
        }
        int [] oddSum = {0};
        int [] evenSum = {0};

        calculateSum(r, 1, oddSum, evenSum);

        return oddSum[0] - evenSum[0];
    }

    private static void calculateSum(BNode<Integer> r, int level, int[] oddSum, int[] evenSum) {
        if(r == null) {
            return;
        }

        if(level % 2 == 1) {
            oddSum[0] += r.info;
        } else {
            evenSum[0] += r.info;
        }

        calculateSum(r.left, level + 1, oddSum, evenSum);
        calculateSum(r.right, level + 1, oddSum, evenSum);
    }
}