// Given a binary tree, find the largest value in each level.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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

public class zad48 {
    public static void main(String[] args) {
        //      4
        //     / \
        //    9   2
        //   / \   \
        //  3   5   7
        
        BTree<Integer> tree = new BTree<>(4);
        BNode<Integer> node9 = tree.addChild(tree.root, BNode.LEFT, 9);
        BNode<Integer> node2 = tree.addChild(tree.root, BNode.RIGHT, 2);
        tree.addChild(node9, BNode.LEFT, 3);
        tree.addChild(node9, BNode.RIGHT, 5);
        tree.addChild(node2, BNode.RIGHT, 7);
        
        ArrayList<Integer> result = largestValue(tree.root);
        
        for (int value : result) {
            System.out.println(value);
        }
    }

    public static ArrayList<Integer> largestValue(BNode<Integer> root) {
        ArrayList<Integer> largestValues = new ArrayList<>();
        
        if (root == null) {
            return largestValues;
        }
        
        Queue<BNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int max = 0;
            
            for (int i = 0; i < levelSize; i++) {
                BNode<Integer> tmp = queue.remove();
                
                if (tmp.info > max) {
                    max = tmp.info;
                }
                
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            
            largestValues.add(max);
        }
        
        return largestValues;
    }
}
