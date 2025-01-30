// Да се напише функција која како аргумент добива насочен граф. Функцијата треба да ги
// најде и врати сите врски за кои важи дека графот станува неповрзан граф при нивно отстранување.
// Да се напише главна програма во која ќе се тестира работата на функцијата.

import java.util.LinkedList;
import java.util.ArrayList;

class Edge {
    int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + " - " + to;
    }

}

class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNode<E>> list;
    
    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList<>();
    }
    
    void addNeighbour(GNode<E> node) {
        if (!list.contains(node)) {
            list.add(node);
        }
    }
    
    void deleteNeighbour(GNode<E> node) {
        if (list.contains(node)) {
            list.remove(node);
        }
    }
    
    boolean hasNeighbour(GNode<E> node) {
        return list.contains(node);
    }
}

class Graph<E> {
    public int n;
    public GNode<E> graph[];
    
    @SuppressWarnings("unchecked")
    public Graph(int n, E[] infos) {
        this.n = n;
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode<>(i, infos[i]);
        }
    }
    
    boolean neighbours(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }
    
    void addEdge(int x, int y) {
        graph[x].addNeighbour(graph[y]);
        graph[y].addNeighbour(graph[x]); 
    }
    
    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
    }

    GNode<E> getNode(E info) {
        for (int i = 0; i < n; i++) {
            if (graph[i].info.equals(info)) {
                return graph[i];
            }
        }
        
        return null;
    }

    void addNode(E info) {
        ++n;
        
        @SuppressWarnings("unchecked")
        GNode<E> [] graphpom = new GNode[n];
        
        for (int i = 0; i < n; i++) {
            graphpom[i] = graph[i];
        }
        graphpom[n - 1] = new GNode<>(n - 1, info);
        
        graph = graphpom;
    }
    
    void deleteNode(E info) {
        GNode<E> node = getNode(info);
        
        for (int i = 0; i < n; i++) {
            if (graph[i].hasNeighbour(node)) {
                graph[i].deleteNeighbour(node);
            }
        }
        
        for (int i = node.num; i < n - 1; i++) {
            graph[i] = graph[i + 1];
            graph[i].num = i;
        }
        
        n--;
    }
    
    void dfs(int visited[], int start) {
        visited[start] = 1;
        
        GNode<E> pom = graph[start];
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i);
            
            if (visited[next.num] == 0) {
                dfs(visited, next.num);
            }
        }
    }

    ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (GNode<E> neighbor : graph[i].list) {
                if (i != neighbor.num) { 
                    edges.add(new Edge(i, neighbor.num));
                }
            }
        }
        return edges;
    }

    boolean isConnected() {
        int[] visited = new int[n];
        dfs(visited, 0); 

        for (int v : visited) {
            if (v == 0) {
                return false;
            } 
        }
        return true;
    }

    ArrayList<Edge> findCriticalEdges() {
        ArrayList<Edge> edges = getAllEdges();
        ArrayList<Edge> criticalEdges = new ArrayList<>();

        for (Edge edge : edges) {
            deleteEdge(edge.from, edge.to);

            if (!isConnected()) {
                criticalEdges.add(edge);
            }

            addEdge(edge.from, edge.to);
        }

        return criticalEdges;
    }

    void printGraph() {
        for (int i = 0; i < n; i++) {
            System.out.print("Jazol " + graph[i].info + " e povrzazn so ");
            for (GNode<E> neighbour : graph[i].list) {
                System.out.print(neighbour.info + " ");
            }
            System.out.println();
        }
    }
    
}

public class zad28 {
    public static void main(String[] args) {
        Integer[] infos = {0, 1, 2, 3, 4, 5}; 
        Graph<Integer> graph = new Graph<>(6, infos);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        System.out.println("Graf pred proverka:");
        graph.printGraph();

        ArrayList<Edge> criticalEdges = graph.findCriticalEdges();
        System.out.println("\nKriticni vrski: ");
        System.out.println(criticalEdges);
        
    }
}

