// Во класата бинарно пребарувачко дрво да се напише функција која за дадено дрво ќе го
// пронајде и врати хиерархиски најнискиот јазол кој е предок на јазлите со вредности n1 и n2, при
// што функцијата како аргументи прима корен на дрвото и вредностите на двата јазли.
// Да се напише главна програма во која ќе тестира работата на функцијата.

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;
    
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

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;
    
    public BSTree() {
        root = null;
    }
    
    public BSTree(E info) {
        root = new BNode<>(info);
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }
    
    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode<>(info);
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) { //left
            r.left = insert(info, r.left);
        } else if (comp > 0) { //right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
        }
        
        return r;
    }
    
    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }
    
    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // brishi go jazolot info
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }
        
        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }

    public BNode<E> najdiPredok(BNode<E> r, BNode<E> node1, BNode<E> node2) {
        if (r == null) {
            return null;
        }
        
        if (r.info.equals(node1.info) || r.info.equals(node2.info)) {
            return r;
        }
    
        BNode<E> lev = najdiPredok(r.left, node1, node2);
        BNode<E> desen = najdiPredok(r.right, node1, node2);
    
        if (lev != null && desen != null) {
            return r;
        } else if (lev != null && desen == null) {
            return lev;
        } else {
            return desen;
        }
        
    }
    

}

public class zad3 {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>(20);
        tree.root = tree.insert(10, tree.root);
        tree.root = tree.insert(30, tree.root);
        tree.root = tree.insert(5, tree.root);
        tree.root = tree.insert(15, tree.root);
        tree.root = tree.insert(25, tree.root);
        tree.root = tree.insert(35, tree.root);

        
        BNode<Integer> node1 = tree.root.left.left;  
        BNode<Integer> node2 = tree.root.left.right; 
        
        BNode<Integer> predok = tree.najdiPredok(tree.root, node1, node2);
        System.out.println("Najniskiot zaednicki predok na " + node1.info + " i " + node2.info + " e: " + predok.info);
    }
}
