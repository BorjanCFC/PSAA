// Да се напише функција во класата бинарно пребарувачко дрво која проверува дали постои
// барем еден пар внатрешни јазли во дрвото кои се во врска родител-дете и за кои важи дека и
// двата немаат десно дете. Функцијата да врати 1, ако постои таков пар јазли, а во спротивно да
// врати 0. Да се напише главна програма во која ќе тестира работата на функцијата.


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

    public boolean noRightKid(BNode<E> node) {
        if (node == null) {
            return false; 
        }
    
        if (node.left != null) {
            
            if (node.right == null && node.left.right == null) {
                return true; 
            }

            if (noRightKid(node.left)) {
                return true;
            }
        }
    
        if (noRightKid(node.right)) {
            return true;
        }
    
        return false; 
    }
}

public class zad2 {
    public static void main(String[] args) {
        
        BSTree<Integer> tree = new BSTree<>();

        tree.root = tree.insert(10, tree.root);
        tree.root = tree.insert(5, tree.root);
        tree.root = tree.insert(15, tree.root);
        tree.root = tree.insert(3, tree.root);
        tree.root = tree.insert(7, tree.root);
        tree.root = tree.insert(6, tree.root); 

        tree.inorder(tree.root);
        System.out.println();

        boolean result = tree.noRightKid(tree.root);
        if (result) {
            System.out.println("Postoi par roditel-dete bez desno dete");
        } else {
            System.out.println("Ne postoi par roditel-dete bez desno dete");
        }
    }
}

