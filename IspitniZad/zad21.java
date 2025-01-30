// Во библиотека се внесуваат податоци за N членови. За секој член се води информација за
// неговото презиме, неговото име, бројот на позајмени книги, број на членска карта и матичниот
// број. Корисник на системот има можност да пребарува член во библиотеката, по име и презиме.
// Да се направи проверка дали тој член постои, дали запишаниот е ист член кој се пребарува (и
// покрај тоа што името и презимето може да се исти) и доколку е најден бараниот член да се
// испише бројот на позајмени книги и членската карта. 


import java.util.Scanner;

class Map<K extends Comparable<K>, E> {

    public K key;
    public E value;

    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {

    public E info;
    public SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {

    private SLLNode<Map<K, E>>[] htable;

    @SuppressWarnings("unchecked")
    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }

    private int hash(K key) {
        return (Integer)key % htable.length;
    }

    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }
        return null;
    }

    public void insert(K key, E value) {
        Map<K, E> entry = new Map<>(key, value);

        int h = hash(key);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key) {
        int h = hash(key);

        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node
                != null; pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }

    }

    public int getTableSize() {
        return htable.length;
    }

    public SLLNode<Map<K, E>> getChain(int index) {
        return htable[index];
    }

}

class Member {
    String lastName;
    String firstName;
    int borrowedBooks;
    int membershipCard;
    String idNumber;

    public Member(String lastName, String firstName, int borrowedBooks, int membershipCard, String idNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.borrowedBooks = borrowedBooks;
        this.membershipCard = membershipCard;
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s, Borrowed Books: %d, Membership Card: %d", firstName, lastName, borrowedBooks, membershipCard);
    }
}

public class zad21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of members: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        SLLHT<String, Member> library = new SLLHT<>(10);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter the number of borrowed books: ");
            int borrowedBooks = scanner.nextInt();
            System.out.print("Enter membership card number: ");
            int membershipCard = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            System.out.print("Enter ID number: ");
            String idNumber = scanner.nextLine();

            Member member = new Member(lastName, firstName, borrowedBooks, membershipCard, idNumber);
            library.insert(lastName + firstName, member);
        }

        System.out.print("Enter last name to search: ");
        String searchLastName = scanner.nextLine();
        System.out.print("Enter first name to search: ");
        String searchFirstName = scanner.nextLine();
        System.out.print("Enter ID number for verification: ");
        String searchId = scanner.nextLine();

        String searchKey = searchLastName + searchFirstName;
        SLLNode<Map<String, Member>> resultNode = library.find(searchKey);

        if (resultNode != null) {
            Member foundMember = resultNode.info.value;

            if (foundMember.idNumber.equals(searchId)) {
                System.out.println("Member found:");
                System.out.println(foundMember);
            } else {
                System.out.println("Member found, but ID does not match.");
            }
        } else {
            System.out.println("Member not found.");
        }

        scanner.close();
    }
}

