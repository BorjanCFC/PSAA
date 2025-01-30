// Given a Binary Tree and a key, the task is to find the level of key in the Binary Tree.

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

public class zad44 {

    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(1);
        BNode<Integer> root = tree.root;
        BNode<Integer> node2 = tree.addChild(root, BNode.LEFT, 2);
        BNode<Integer> node3 = tree.addChild(root, BNode.RIGHT, 3);
        tree.addChild(node2, BNode.LEFT, 4);
        tree.addChild(node2, BNode.RIGHT, 5);
        tree.addChild(node3, BNode.LEFT, 6);
        tree.addChild(node3, BNode.RIGHT, 7);

        int target = 5;
        int level = getLevel(root, target, 0);
        System.out.println("Level of " + target + " is: " + level);
    }

    public static int getLevel(BNode<Integer> r, int target, int level) {
        if(r == null) {
            return -1;
        }

        if(r.info == target) {
            return level;
        }

        int leftLevel = getLevel(r.left, target, level + 1);
        if (leftLevel != -1) {
            return leftLevel;
        }

        return getLevel(r.right, target, level + 1);
    }
}