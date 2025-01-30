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

public class zad50 {
    public static void main(String[] args) {
        BTree<Integer> tree1 = new BTree<>(26);
        BNode<Integer> root1 = tree1.root;
        tree1.addChild(root1, BNode.LEFT, 10);
        tree1.addChild(root1, BNode.RIGHT, 3);
        
        BNode<Integer> node10 = root1.left;
        BNode<Integer> node3 = root1.right;
        tree1.addChild(node10, BNode.LEFT, 4);
        tree1.addChild(node10, BNode.RIGHT, 6);
        tree1.addChild(node3, BNode.RIGHT, 3);
        
        BNode<Integer> node4 = node10.left;
        tree1.addChild(node4, BNode.RIGHT, 30);
        
        BTree<Integer> tree2 = new BTree<>(10);
        BNode<Integer> root2 = tree2.root;
        tree2.addChild(root2, BNode.LEFT, 4);
        tree2.addChild(root2, BNode.RIGHT, 6);
        
        BNode<Integer> node4InTree2 = root2.left;
        tree2.addChild(node4InTree2, BNode.RIGHT, 30);

        System.out.println("Is tree2 a subtree of tree1? " + isSubtree(tree1.root, tree2.root));
    }

    public static boolean areIdentical(BNode<Integer> root1, BNode<Integer> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return (root1.info.equals(root2.info) && areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right));
    }

    public static boolean isSubtree(BNode<Integer> root1, BNode<Integer> root2) {
        if (root2 == null) {
            return true;
        } 
        if (root1 == null) {
            return false; 
        }
        
        if (areIdentical(root1, root2)) {
            return true;
        }

        return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }
}
