// За дадено бинарно дрво важат следните правила:

// коренот е со вредност 100,
// ако јазол има вредност x и има лево дете, тогаш вредност на левото дете е x/2 – 1 (ако оваа пресметка е позитивен број) или 0 (ако x/2 – 1 е негативен број),
// ако јазол има вредност x и има десно дете, тогаш вредноста на десното дете е x/2 – 2 (ако оваа пресметка е позитивен број) или 0 (ако x/2 – 2 е негативен број).

// По одреден процес дрвото и неговата содржина се оштетени, така што сите вредности во дрвото се заменети со вредност -1. 
// Следејќи ги правилата кои важат за бинарното дрво имплементирајте функција за корегирање на дрвото, која како аргумент ќе добие оштетено дрво и истото ќе го реконструира. 
// Дополнително, напишете и функција за пребарување на одредена вредност во реконструираното дрво која враќа информација за тоа дали вредноста се наоѓа во дрвото или не.
// Да се напише главна програма во која ќе се тестира работата на двете функции.

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

    public void fixTree(BTree<Integer> tree) {
        if (tree == null || tree.root == null) {
            System.out.println("Nevalidno drvo");
        }

        fixNodes(tree.root);
    }

    private void fixNodes(BNode<Integer> node) {
        if (node == null) {
            System.out.println("Nevalidno vnesen element vo drvo");
        }
        
        
        if (node.left != null) {

            int leftValue = node.info / 2 - 1;

            if (leftValue > 0) {
                node.left.info = leftValue;
            } else {
                node.left.info = 0;
            }

            fixNodes(node.left);
        }
        
        if (node.right != null) {
            int rightValue = node.info / 2 - 2;

            if (rightValue > 0) {
                node.right.info = rightValue;
            } else {
                node.right.info = 0;
            }

            fixNodes(node.right);
        }
    }

    public boolean contains(int info, BNode<Integer> node) {
        if (node == null) {
            return false;
        }

        if (node.info == info) {
            return true;
        }

        return contains(info, node.left) || contains(info, node.right);
    }
                            
}

public class zad55 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(100);
        
        BNode<Integer> root = tree.root;
        BNode<Integer> leftChild = tree.addChild(root, BNode.LEFT, -1);
        BNode<Integer> rightChild = tree.addChild(root, BNode.RIGHT, -1);
        
        tree.addChild(leftChild, BNode.LEFT, -1);
        tree.addChild(leftChild, BNode.RIGHT, -1);
        tree.addChild(rightChild, BNode.LEFT, -1);
        tree.addChild(rightChild, BNode.RIGHT, -1);
        
        tree.fixTree(tree);
        
        tree.inorder(root);

        int vrednost = 25;
        boolean found = tree.contains(vrednost, root);
        
        if (found) {
            System.out.println("Vrednosta " + vrednost + " e pronajdena vo drvoto");
        } else {
            System.out.println("Vrednosta " + vrednost + " ne e pronajdena vo drvoto");
        }
    }
}
