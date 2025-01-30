// Given a Binary Tree, the task is to check whether the given Binary Tree is a perfect Binary Tree or not.
// Note:

// A Binary tree is a Perfect Binary Tree in which all internal nodes have two children and all leaves are at the same level.

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

public class zad32 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(10);

        tree.addChild(tree.root, BNode.LEFT, 20);
        tree.addChild(tree.root, BNode.RIGHT, 30);
        tree.addChild(tree.root.left, BNode.LEFT, 40);
        tree.addChild(tree.root.left, BNode.RIGHT, 50);
        tree.addChild(tree.root.right, BNode.LEFT, 60);
        tree.addChild(tree.root.right, BNode.RIGHT, 70);   
        
        int depth = maxDepth(tree.root);
        
        boolean isPerfectTree = isPerfect(tree.root, depth);
        
        if (isPerfectTree) {
            System.out.println("The binary tree is a perfect binary tree.");
        } else {
            System.out.println("The binary tree is NOT a perfect binary tree.");
        }
    }
    private static int maxDepth(BNode<Integer> r) {
        if (r == null) {
            return -1;
        }
        return 1 + Math.max(maxDepth(r.left), maxDepth(r.right));
    }

    public static boolean isPerfect(BNode<Integer> r, int d) {
        if(r == null) {
            return true;
        }

        if(r.left == null && r.right == null) {
            return d == 0;
        }

        if(r.left == null || r.right == null) {
            return false;
        }

        return isPerfect(r.left, d - 1) && isPerfect(r.right, d - 1);
    }
}
