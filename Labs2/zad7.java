import java.util.ArrayList;
import java.util.LinkedList;


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
        System.out.println("Node: " + graph[start].info);
        
        GNode<E> pom = graph[start];
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i);
            
            if (visited[next.num] == 0) {
                dfs(visited, next.num);
            }
        }
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
    
    ArrayList<E> findReachableCities(int[] visited, int start) {
        ArrayList<E> reachable = new ArrayList<>();
        visited[start] = 1;
        reachable.add(graph[start].info);
    
        GNode<E> pom = graph[start];
        for (int i = 0; i < pom.list.size(); i++) {
            GNode<E> next = pom.list.get(i); 
            if (visited[next.num] == 0) { 
                reachable.addAll(findReachableCities(visited, next.num)); 
            }
        }
    
        return reachable;
    }
    

}

public class zad7 {
    public static void main(String[] args) {
        String[] cityNames = {"Skopje", "Berlin", "Oslo", "London", "Birmingham"};
        int n = cityNames.length;

        Graph<String> graph = new Graph<>(n, cityNames);

        graph.addEdge(0, 1); // Skopje - Berlin
        graph.addEdge(1, 2); // Berlin - Oslo
        graph.addEdge(0, 2); // Skopje - Oslo
        graph.addEdge(3, 4); // London - Birmingham

        
        String startCity = "Oslo";


        GNode<String> startNode = graph.getNode(startCity);
    
        int[] visited = new int[n];
        ArrayList<String> reachableCities = graph.findReachableCities(visited, startNode.num);

        reachableCities.remove(startCity);
        if (reachableCities.isEmpty()) {
            System.out.println("Ne postojat drugi gradovi do koj sto moze da se stigne od " + startCity + ".");
        } else {
            System.out.println("Od " + startCity + " moze da se stigne do slednite gradovi: " + reachableCities);
        }
        

    }
}
