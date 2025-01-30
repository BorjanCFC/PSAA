// Потребно е да се организира чување на информации за мобилна апликација. Мобилната
// апликација треба за секој корисник да чува податоци за корисничкото име, лозинка и листа од
// пријатели на корисникот (низа од други кориснички имиња). Дополнително, апликацијата треба
// да чува податоци за секое направено споделување, при што споделувањата може да бидат
// слика или цитат. За секое споделување се чува информација за тоа кога е направено
// споделувањето (dd/mm/yy hh:mm), кој корисник го има направено споделувањето и самата
// содржина. Притоа, цитатите се споделуваат во форма на слика до сите пријатели на корисникот,
// а сликата е дефинирана како матрица со димензии 256 x 256 x 3. Едно споделување е целосно
// определено од комбинацијата на времето кога е направено споделувањето и корисникот кој го
// прави истото, при што се претпоставува дека еден корисник може да сподели само еднаш во
// одреден временски момент.
// Во главната програма, по внесување на информациите, треба да се овозможи внесување на
// датум и на корисничко име. Програмата треба да провери дали внесените информации се
// валидни. Во случај информациите да не се валидни потребно е да се прикаже соодветна порака
// за грешка. Ако внесените информации се валидни програмата треба да ги прикаже сите
// споделувања кои се направени од корисникот на внесениот датум и дополнително да каже кои
// се корисниците кои може да ги видат направените споделувања.

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
        // Generate hash code based on key's string representation
        return Math.abs(key.toString().hashCode() % htable.length);
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
        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; pred = node, node = node.next) {
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

class User {
    String username;
    String password;
    String[] friends;
    
    public User(String username, String password, String[] friends) {
        this.username = username;
        this.password = password;
        this.friends = friends;
    }
}

class Sharing {
    String timestamp;
    String username;
    Object content;
    boolean isImage;
    
    public Sharing(String timestamp, String username, Object content, boolean isImage) {
        this.timestamp = timestamp;
        this.username = username;
        this.content = content;
        this.isImage = isImage;
    }
}

class Image {
    int[][][] pixels;
    
    public Image(int[][][] pixels) {
        this.pixels = pixels;
    }
}

public class zad12 {
    private static SLLHT<String, User> userTable;
    private static SLLHT<String, Sharing> sharingTable;
    
    private static boolean validateCredentials(String username, String password) {
        SLLNode<Map<String, User>> userNode = userTable.find(username);
        if (userNode == null) {
            System.out.println("Greshka: Korisnickoto ime ne postoi.");
            return false;
        }
        
        if (!userNode.info.value.password.equals(password)) {
            System.out.println("Greshka: Pogresna lozinka.");
            return false;
        }
        
        return true;
    }
    
    private static void findSharings(String date, String username) {
        for (int i = 0; i < sharingTable.getTableSize(); i++) {
            for (SLLNode<Map<String, Sharing>> node = sharingTable.getChain(i); 
                 node != null; node = node.next) {
                
                Sharing sharing = node.info.value;
                
                if (sharing.username.equals(username) && 
                    sharing.timestamp.startsWith(date)) {
                    
                    System.out.println("Spodeluvanje: " + sharing.timestamp);
                    
                    User user = userTable.find(username).info.value;
                    System.out.println("Korisnici koi mozat da go vidat spodeluvanjeto: ");
                    for (String friend : user.friends) {
                        System.out.println(" - " + friend);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        userTable = new SLLHT<>(100);
        sharingTable = new SLLHT<>(200);
        
        String[] friends = {"user2", "user3"};
        User newUser = new User("user1", "pass123", friends);
        userTable.insert("user1", newUser);
        
        int[][][] imageData = new int[256][256][3];
        Image image = new Image(imageData);
        Sharing sharing = new Sharing("23/01/24 14:30", "user1", image, true);
        String sharingKey = sharing.timestamp + sharing.username;
        sharingTable.insert(sharingKey, sharing);
        
        String testUsername = "user1";
        String testPassword = "pass123";
        String testDate = "23/01/24";
        
        if (validateCredentials(testUsername, testPassword)) {
            findSharings(testDate, testUsername);
        }
    }
}
