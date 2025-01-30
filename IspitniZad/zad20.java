// Да се напише функција која во насочен тежински граф ќе ја пронајде и врати патеката од еден
// до друг јазол. Да се напише главна програма во која ќе се внесат јазли почеток и дестинација.
// Да се тестира работата на функцијата и да се прикаже добиената патека.

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
    
    void dfs(int visited[], ArrayList<Integer> lista, int start, int end, int[] found) {
        visited[start] = 1;  
        lista.add(start);    
        GNode<E> pom = graph[start];  
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i); 
            
            if (visited[next.num] == 0) {
                if (next.num == end) {
                    lista.add(next.num);  
                    for (int j = 0; j < lista.size(); j++) {
                        System.out.print(lista.get(j) + ", ");
                    }
                    found[0] = 1;  
                    return;  
                }
                
                if (found[0] == 1) {
                    return;
                }

                dfs(visited, lista, next.num, end, found);
                
            }
        }
        
        lista.remove(lista.size() - 1);
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



public class zad20 {
    public static void main(String[] args) {
        Integer [] infos = {1, 2, 3, 4, 5, 6, 7};
        Graph<Integer> graph = new Graph<Integer>(7, infos);

        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(6, 5);
        graph.addEdge(1, 0);
        graph.addEdge(4, 5);

        graph.printGraph();

        int [] visited = new int[graph.n];
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for(int i = 0; i < graph.n; i++){
            visited[i] = 0;
        }
        int [] found = {0};
        graph.dfs(visited, lista, 1, 5, found);
    }
}
