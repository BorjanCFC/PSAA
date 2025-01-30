// Една телевизиска станица има потреба да води евиденција за документарни филмови
// прикажувани низ годините. За секој филм се знае насловот, името на продукциската куќа, годината
// на издавање, жанрот и IMDB рејтингот. Да се претпостави дека не постојат два документарни
// филмови со ист наслов. Еден филм може да биде прикажан повеќе пати од неговото издавање, при
// што за секое одделно прикажување се чува датумот на прикажување и информација за бројот на
// гледачи кои го следеле истиот. Креирајте ги потребните хеш табели за реализација на проблемот,
// на тој начин што ќе обезбедите оптимална мемориска комплексност (не треба да се чуваат дупликат
// информации).
// Дополнително, да се напише функција која ќе ги прикаже сите прикажувања на даден
// документарен филм во одреден временски период и вкупниот број на гледачи кои ги следеле тие
// прикажувања. Притоа, функцијата како аргументи ќе ги добие името на документарниот филм и
// временскиот период (две променливи кои претставуваат година на почеток и крај на прикажување
// на филмот). Функцијата треба да провери дали филмот е валиден податок (т.е. дали постои филм

// со даденото име) и ако тоа не е случај да испише соодветна порака за грешка. Доколку
// документарниот филм постои функцијата треба да ги прикаже сите информации што се чуваат за
// истиот. Следно, треба да се проверат сите прикажувања и бројот на гледачи на филмот во внесениот
// опсег. Притоа, доколку опсегот не е валиден (т.е. филмот бил снимен по внесениот временски
// период) да се прикаже соодветна порака за грешка. Во спротивно, функцијата да ги отпечати
// соодветните информации на екран.

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
        htable[h] = new SLLNode<>(entry, htable[h]);
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

// Класата за чување на податоци за документарен филм
class Documentary {
    String title;
    String productionHouse;
    int releaseYear;
    String genre;
    double imdbRating;
    SLLNode<Screening> screenings;

    Documentary(String title, String productionHouse, int releaseYear, String genre, double imdbRating) {
        this.title = title;
        this.productionHouse = productionHouse;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.screenings = null;
    }

    // Метод за додавање на прикажување на филмот
    void addScreening(Screening screening) {
        screenings = new SLLNode<>(screening, screenings);
    }
}

// Класата за чување на податоци за прикажување на документарен филм
class Screening {
    int year;
    int viewers;

    Screening(int year, int viewers) {
        this.year = year;
        this.viewers = viewers;
    }
}

public class zad27 {

    public static void main(String[] args) {
        SLLHT<String, Documentary> filmsHT = new SLLHT<>(10);

        Documentary film1 = new Documentary("Documentary 1", "Production House 1", 2010, "Nature", 8.7);
        film1.addScreening(new Screening(2011, 500));
        film1.addScreening(new Screening(2012, 600));
        film1.addScreening(new Screening(2015, 800));
        filmsHT.insert(film1.title, film1);

        Documentary film2 = new Documentary("Documentary 2", "Production House 2", 2015, "History", 9.2);
        film2.addScreening(new Screening(2016, 1000));
        film2.addScreening(new Screening(2018, 1200));
        filmsHT.insert(film2.title, film2);

        
        displayScreeningsInPeriod(filmsHT, "Documentary 1", 2010, 2015);
    }
            
    public static void displayScreeningsInPeriod(SLLHT<String, Documentary> filmsHT, String filmTitle, int startYear, int endYear) {
        SLLNode<Map<String, Documentary>> filmNode = filmsHT.find(filmTitle);

        if (filmNode == null) {
            System.out.println("Film not found.");
            return;
        }

        Documentary film = filmNode.info.value;

        if (film.releaseYear > endYear) {
            System.out.println("Invalid period: the film was released after the specified period.");
            return;
        }

        System.out.println("Film: " + film.title);
        System.out.println("Production House: " + film.productionHouse);
        System.out.println("Release Year: " + film.releaseYear);
        System.out.println("Genre: " + film.genre);
        System.out.println("IMDB Rating: " + film.imdbRating);

        int totalViewers = 0;
        boolean foundValidScreening = false;

        for (SLLNode<Screening> screeningNode = film.screenings; screeningNode != null; screeningNode = screeningNode.next) {
            Screening screening = screeningNode.info;
            if (screening.year >= startYear && screening.year <= endYear) {
                System.out.println("Screening Year: " + screening.year + " | Viewers: " + screening.viewers);
                totalViewers += screening.viewers;
                foundValidScreening = true;
            }
        }

        if (!foundValidScreening) {
            System.out.println("No screenings found in the given period.");
        } else {
            System.out.println("Total viewers in the period: " + totalViewers);
        }
    }
}
