// Даден е коренот на бинарно дрво и цел број targetSum. 
// Да се напише функција која што враќа true ако дрвото има пат од коренот до листот така што збирот на сите вредности по патот е еднаков на targetSum.

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

public class zad29 {
    public static void main(String[] args) {
        BTree<Integer> tree1 = new BTree<>(10);

        tree1.addChild(tree1.root, BNode.LEFT, 6);
        tree1.addChild(tree1.root, BNode.RIGHT, 5);
        tree1.addChild(tree1.root.left, BNode.LEFT, 2);
        tree1.addChild(tree1.root.left, BNode.RIGHT, 4);
        tree1.addChild(tree1.root.left.left, BNode.LEFT, 11);
        tree1.addChild(tree1.root.right, BNode.LEFT, 15);
        tree1.addChild(tree1.root.right, BNode.RIGHT, 7);
        tree1.addChild(tree1.root.right.left, BNode.LEFT, 8);
        tree1.addChild(tree1.root.right.left, BNode.RIGHT, 3);
        tree1.addChild(tree1.root.right.right, BNode.RIGHT, 1);

        int targetSum = 20; 

        boolean result = pathTargetSum(tree1.root, targetSum);
        System.out.println("Postoi pat so zbir " + targetSum + ": " + result);
        
    }
        
        
    public static boolean pathTargetSum(BNode<Integer> r, int targetSum) {
        if (r == null) {
            return false;
        }
        
        if (r.left == null && r.right == null) {
            return targetSum == r.info;
        }
        
        return pathTargetSum(r.left, targetSum - r.info) || pathTargetSum(r.right, targetSum - r.info);
    }
}
