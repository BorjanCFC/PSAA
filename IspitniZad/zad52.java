// Given a binary tree in which each node element contains a number. Find the maximum possible sum from one leaf node to another. 

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

public class zad52 {
    public static void main(String[] args) {
        //
        //         1
        //       /   \
        //     -2     3
        //     / \   / \
        //    8  -1  4  -5
        BTree<Integer> tree = new BTree<>(1);
        BNode<Integer> root = tree.root;
        
        BNode<Integer> left = tree.addChild(root, BNode.LEFT, -2);
        BNode<Integer> right = tree.addChild(root, BNode.RIGHT, 3);
        
        tree.addChild(left, BNode.LEFT, 8);
        tree.addChild(left, BNode.RIGHT, -1);
        
        tree.addChild(right, BNode.LEFT, -10);
        tree.addChild(right, BNode.RIGHT, -6);

        System.out.println("Maximum Leaf-to-Leaf Sum: " + findMaxSum(tree.root));
    }

    public static int findMaxSum(BNode<Integer> r) {
        if (r == null) {
            return 0;
        }

        int[] maxSum = {Integer.MIN_VALUE}; 
        maxPathSum(r, maxSum);


        return maxSum[0];
    }

    private static int maxPathSum(BNode<Integer> r, int[] maxSum) {
        if (r == null) {
            return 0;
        }

        if (r.left == null && r.right == null) {
            return r.info;
        }

        int leftSum = maxPathSum(r.left, maxSum);
        int rightSum = maxPathSum(r.right, maxSum);

        if (r.left != null && r.right != null) {
            maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + r.info);
            return Math.max(leftSum, rightSum) + r.info;
        }

        if (r.left != null) {
            return leftSum + r.info;
        } else {
            return rightSum + r.info;
        }
    }
}
