// Count the total number of ways or paths that exist between two vertices in a directed graph. 
// These paths don’t contain a cycle, the simple enough reason is that a cycle contains an infinite number of paths and hence they create a problem

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
    }
    
    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
    }

    GNode<E> getNode(E info) {
        for (int i = 0; i < n; i++) {
            if (graph[i].info == info) {
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
    
    int countPaths(int s, int d) {
        boolean[] visited = new boolean[n];
        return countPathsHelper(s, d, visited);
    }
    
    int countPathsHelper(int s, int d, boolean[] visited) {
        if (s == d) {
            return 1;
        }

        visited[s] = true;
        int pathCount = 0;

        GNode<E> currentNode = graph[s];
        for (GNode<E> neighbor : currentNode.list) {
            int neighborIndex = neighbor.num;
            if (!visited[neighborIndex]) {
                pathCount += countPathsHelper(neighborIndex, d, visited);
            }
        }

        visited[s] = false;
        return pathCount;
    }


}

public class zad45 {

    public static void main(String[] args) {
        String[] infos = {"A", "B", "C", "D"};
        Graph<String> graph = new Graph<>(4, infos);

        graph.addEdge(0, 1); // A -> B
        graph.addEdge(1, 2); // B -> C
        graph.addEdge(2, 3); // C -> D
        graph.addEdge(0, 2); // A -> C
        graph.addEdge(1, 3); // B -> D

        int pathCount = graph.countPaths(0, 3);
        System.out.println("Number of paths from A to D: " + pathCount);
    }
}