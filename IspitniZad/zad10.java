// Во класата BSTree да се напише функција која ќе добива корен на дрвото како аргумент.
// Функцијата треба да ја пресмета и врати височината на дрвото.
// Да се напише и главна програма во која ќе се тестира работата на функцијата. 

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

    public int findHeight(BNode<E> r) {

        int counterLeft = 1;
        int counterRight = 1;

        if (r.left == null && r.right == null) {
            return 1;
        } else if (r.left != null && r.right == null) {
            counterLeft += findHeight(r.left);
        } else if (r.left == null && r.right != null) {
            counterRight += findHeight(r.right);
        } else {
            counterLeft += findHeight(r.left);
            counterRight += findHeight(r.right);
        }
    
        if (counterLeft > counterRight) {
            return counterLeft;
        } else {
            return  counterRight;
        }

    }

}

public class zad10 {
    public static void main(String[] args) {
        BSTree<Integer> drvo1 = new BSTree<>();
        drvo1.root = drvo1.insert(10, drvo1.root);
        drvo1.root = drvo1.insert(6, drvo1.root);
        drvo1.root = drvo1.insert(15, drvo1.root);
        drvo1.root = drvo1.insert(2, drvo1.root);
        drvo1.root = drvo1.insert(8, drvo1.root);
        drvo1.root = drvo1.insert(13, drvo1.root);
        drvo1.root = drvo1.insert(18, drvo1.root);
        drvo1.root = drvo1.insert(20, drvo1.root);
        drvo1.root = drvo1.insert(21, drvo1.root);

        System.out.println("Visocinata na drvoto e " + drvo1.findHeight(drvo1.root));

    }
}
