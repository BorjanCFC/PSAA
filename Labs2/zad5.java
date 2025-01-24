package labVezbi.labVezbiVtorKolok;

import java.util.ArrayList;
import java.util.List;

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

class Airline {
    String name;
    int id;
    String originCountry;
    double rating;

    public Airline(String name, int id, String originCountry, double rating) {
        this.name = name;
        this.id = id;
        this.originCountry = originCountry;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - %s [%.1f]", name, id, originCountry, rating);
    }
}

class Flight {
    String flightNumber;
    String destination;
    int duration;
    int passengers;
    int airlineId;

    public Flight(String flightNumber, String destination, int duration, int passengers, int airlineId) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.duration = duration;
        this.passengers = passengers;
        this.airlineId = airlineId;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d min %d passengers AirlineID: %d", flightNumber, destination, duration, passengers, airlineId);
    }
}

public class zad5 {
    public static void main(String[] args) {
        SLLHT<Integer, Airline> airlinesTable = new SLLHT<>(10);

        Airline airline1 = new Airline("AirlineA", 1, "USA", 4.5);
        Airline airline2 = new Airline("AirlineB", 2, "Germany", 3.2);
        airlinesTable.insert(airline1.id, airline1);
        airlinesTable.insert(airline2.id, airline2);

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("FL123", "Paris", 280, 150, 1));
        flights.add(new Flight("FL124", "Berlin", 320, 180, 2));
        flights.add(new Flight("FL125", "Rome", 240, 200, 1));
        flights.add(new Flight("FL126", "Madrid", 480, 100, 2));

        System.out.println("Letovi so vremetraenje pod 5 casa i rejting na aviokompanijata > 3.5:");
        for (Flight flight : flights) {
            SLLNode<Map<Integer, Airline>> node = airlinesTable.find(flight.airlineId);
            if (node != null && flight.duration < 300 && node.info.value.rating > 3.5) {
                System.out.println(flight);
            }
        }
    }
}
