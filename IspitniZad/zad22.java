// Да се напише функција која за дадено бинарно дрво ќе ја одреди и врати патеката која
// почнувајќи од коренот и завршувајќи во некој од листовите на дрвото има најголема тежина.
// Под тежина на патеката се подразбира сумата од вредностите на јазлите кои ја формираат
// патеката.
// Да се напише главна програма во која ќе се внесе бинарно дрво и ќе се тестира работата на
// функцијата.

import java.util.ArrayList;
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

public class zad22 {
    public static void main(String[] args) {
        BTree<Integer> drvo = new BTree<>(1);

        drvo.addChild(drvo.root, BNode.LEFT, 2);
        drvo.addChild(drvo.root, BNode.RIGHT, 3);
        drvo.addChild(drvo.root.left, BNode.LEFT, 4);
        drvo.addChild(drvo.root.left, BNode.RIGHT, 5);
        drvo.addChild(drvo.root.left.left, BNode.LEFT, 8);
        drvo.addChild(drvo.root.left.right, BNode.LEFT, 6);
        drvo.addChild(drvo.root.left.right.left, BNode.RIGHT, 7);

        ArrayList<Integer> path = new ArrayList<>();
        int maxWeight = najdiPateka(drvo.root, path);

        System.out.println("Najgolema tezina: " + maxWeight);
        System.out.println("Pateka so najgolema tezina: " + path);
    }

    private static int najdiPateka(BNode<Integer> node, ArrayList<Integer> path) {
        if (node == null) {
            return 0;
        }

        ArrayList<Integer> leftPath = new ArrayList<>();
        ArrayList<Integer> rightPath = new ArrayList<>();

        int leftWeight = najdiPateka(node.left, leftPath);
        int rightWeight = najdiPateka(node.right, rightPath);

        if (leftWeight > rightWeight) {
            path.addAll(leftPath);
        } else {
            path.addAll(rightPath);
        }

        path.add(node.info); 
        return node.info + Math.max(leftWeight, rightWeight);
    }
}
