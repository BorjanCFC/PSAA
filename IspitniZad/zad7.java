// Во класата BSTree да се напише функција која како аргумент прима корен на дрво и дрво.
// Притоа, треба да ги измине елементите од дрвото за кое е даден коренот (значи првото дрво)
// и доколку најде родител за кој разлика од вредностите на двете деца ќе биде вредноста на
// родителот, треба во второто дрво да се додаде родителот. Притоа, ако не постои едно од двете
// деца, тој јазол не го исполнува условот.
// Да се напише главна програма во која ќе се креираат две дрва преку кои ќе се тестира работата
// на функцијата.

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

    public void func (BNode<Integer> r, BSTree<Integer> drvo) {
        if (r == null) {
            return;
        }

        if (r.left != null && r.right != null) {
            int desen = r.right.info;
            int lev = r.left.info;
            if ( (desen - lev) == r.info) {
                drvo.root = drvo.insert(r.info, drvo.root);
            } 
            
        }

        func(r.left, drvo);
        func(r.right, drvo);
    }

}


public class zad7 {
    public static void main(String[] args) {
        BSTree<Integer> drvo1 = new BSTree<>();
        drvo1.root = drvo1.insert(10, drvo1.root);
        drvo1.root = drvo1.insert(6, drvo1.root);
        drvo1.root = drvo1.insert(15, drvo1.root);
        drvo1.root = drvo1.insert(2, drvo1.root);
        drvo1.root = drvo1.insert(8, drvo1.root);
        drvo1.root = drvo1.insert(13, drvo1.root);
        drvo1.root = drvo1.insert(18, drvo1.root);

        BSTree<Integer> drvo2 = new BSTree<>();

        drvo1.func(drvo1.root, drvo2);

        System.out.println("Elementi vo vtoroto drvo:");
        drvo2.inorder(drvo2.root);
    }
}
