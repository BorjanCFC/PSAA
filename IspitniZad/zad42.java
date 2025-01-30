// Given a Binary Tree, the task is to check if the given binary tree is a Binary Search Tree or not. 
// If found to be true, then print “YES”. Otherwise, print “NO”.

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

public class zad42 {

    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(9); 
        BNode<Integer> root = tree.root;

        BNode<Integer> node6 = tree.addChild(root, BNode.LEFT, 6); 
        BNode<Integer> node4 = tree.addChild(node6, BNode.LEFT, 4); 
        tree.addChild(node4, BNode.LEFT, 3); 
        tree.addChild(node4, BNode.RIGHT, 5); 
        BNode<Integer> node7 = tree.addChild(node6, BNode.RIGHT, 7);
        tree.addChild(node7, BNode.RIGHT, 8); 

        BNode<Integer> node10 = tree.addChild(root, BNode.RIGHT, 10);
        tree.addChild(node10, BNode.RIGHT, 11); 

        if (isBST(tree.root)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean isBST(BNode<Integer> r) {
        if (r == null) {
            return true;
        }

        Stack<BNode<Integer>> s = new Stack<>();
        BNode<Integer> curr = r;
        BNode<Integer> prev = null;

        while (!s.isEmpty() || curr != null) {
        
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }

        
            curr = s.pop();

            if (prev != null && curr.info <= prev.info) {
                return false;
            }

            prev = curr;

            
            curr = curr.right;
        }

        return true; 
    }

}