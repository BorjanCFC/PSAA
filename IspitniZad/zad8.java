// Локална мрежа се состои од компјутери меѓусебно поврзани со мрежни кабли. Поради
// големината на мрежата има потреба од функција која ќе прави проверка дали во мрежата има
// врски чиј прекин би ја поделил мрежата на две или повеќе неповрзани делови. Функцијата како
// аргумент прима матрица на врски во мрежата, а треба да го врати бројот на кабли кои го имаат
// горе опишаното својство.
// Да се напише и главна програма која ја тестира работата за мрежа внесена од корисникот.
// Пример1:
// 5 // број на компјутери
// 1 2 3 4 5 // нумерираност на компјутерите
// 4 // број на врски
// 0 1 // јазли меѓу кои постои врска
// 1 2
// 2 3
// 3 4
// 3 // почетен јазол за разгледување
// 4 // број на такви врски

// Пример2:
// 5 // број на компјутери
// A B C D E // нумерираност на компјутерите
// 5 // број на врски
// 0 1 // јазли меѓу кои постои врска
// 1 2
// 2 3
// 1 3
// 3 4
// 2 // почетен јазол
// 2 // број на такви врски


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

    int countCriticalEdges() {
        ArrayList<Edge> edges = getAllEdges();
        ArrayList<Edge> criticalEdges = new ArrayList<>();

        for (Edge edge : edges) {
            deleteEdge(edge.from, edge.to);

            if (!isConnected()) {
                criticalEdges.add(edge);
            }

            addEdge(edge.from, edge.to);
        }

        return criticalEdges.size();
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

public class zad8 {
    public static void main(String[] args) {
        Integer[] nodes1 = {1, 2, 3, 4, 5};
        Graph<Integer> graph1 = new Graph<>(5, nodes1);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 4);
        
        System.out.println("Broj na kriticni vrski: " + graph1.countCriticalEdges());
        
        String[] nodes2 = {"A", "B", "C", "D", "E"};
        Graph<String> graph2 = new Graph<>(5, nodes2);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(1, 3);
        graph2.addEdge(3, 4);
        
        System.out.println("Broj na kriticni vrski: " + graph2.countCriticalEdges());
    }
}
