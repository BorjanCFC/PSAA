// Да се напише функција која во ненасочен граф ќе провери постоење на циклус.
// Да се напише главна програма во која ќе се креира граф, а потоа и ќе се тестира работата на
// функцијата.

import java.util.Arrays;

class GraphInfo<E extends Comparable<E>> {
    E info;
    
    public GraphInfo(E info) {
        this.info = info;
    }
}

class Graph<E extends Comparable<E>> {
    private GraphInfo<E> infos[];
    public int n;
    private int mtx[][];
    
    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
            }
        }
    }
    
    void addEdge(int x, int y) {
        mtx[x][y] = 1;
        mtx[y][x] = 1;
    }
    
    void deleteEdge(int x, int y) {
        mtx[x][y] = 0;
        mtx[y][x] = 0;
    }
    
    void setInfo(int pos, E info) {
        infos[pos] = new GraphInfo<>(info);
    }
    
    E getInfo(int pos) {
        return infos[pos].info;
    }
    
    int getIndex(E info) {
        for (int i = 0; i < n; i++) {
            if (infos[i].info == info) {
                return i;
            }
        }
        
        return -1;
    }
    
    boolean neighbours(int x, int y) {
        if (mtx[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    void addNode(E info) {
        ++n;
        
        @SuppressWarnings("rawtypes")
        GraphInfo[] infospom = new GraphInfo[n];
        for (int i = 0; i < n - 1; i++) {
            infospom[i] = infos[i];
        }
        infospom[n - 1] = new GraphInfo<>(info);
        
        int[][] mtxpom = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mtxpom[i][j] = mtx[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            mtxpom[n - 1][i] = 0;
            mtxpom[i][n - 1] = 0;
        }
        
        infos = infospom;
        mtx = mtxpom;
    }
    
    void deleteNode(E info) {
        int ind = getIndex(info);
        
        if (ind != n - 1) {
            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = mtx[i + 1][j];
                }
            }
            for (int j = ind; j < n - 1; j++) {
                for (int i = 0; i < n; i++) {
                    mtx[i][j] = mtx[i][j + 1];
                }
            }
            
            for (int i = ind; i < n - 1; i++) {
                infos[i] = infos[i + 1];
            }
        }
        
        n--;
    }
    
    public void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mtx[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + infos[start].info);
        
        for (int i = 0; i < n; i++) {
            if (i != start) {
                if (mtx[start][i] > 0 && visited[i] == 0) {
                    dfs(visited, i);
                }
            }
        }
    }

    public boolean detectIfCycleExists(int[] visited, int start, int parent) {
        visited[start] = 1;
        
        for (int i = 0; i < n; i++) {
            if (mtx[start][i] > 0) {
                if (visited[i] == 0) {
                    if (detectIfCycleExists(visited, i, start)) {
                        return true;
                    }
                } else if(visited[i] == 1 && i != parent) {
                    return true;
                } 
            }
        }
        
        return false;
    }
}

public class zad11 {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(5);

        graph.setInfo(0, "A");
        graph.setInfo(1, "B");
        graph.setInfo(2, "C");
        graph.setInfo(3, "D");
        graph.setInfo(4, "E");

        graph.addEdge(0, 1); // A - B
        graph.addEdge(1, 0); // B - C
        graph.addEdge(2, 0); // C - D
        graph.addEdge(3, 0); // D - A 
        graph.addEdge(3, 4); // D - E

        System.out.println("Matrica na sosedstvo:");
        graph.printMatrix();

        int[] visited = new int[graph.n];
        Arrays.fill(visited, 0);

        boolean hasCycle = graph.detectIfCycleExists(visited, 0, -1);

        if (hasCycle) {
            System.out.println("Grafot sodrzi ciklus.");
        } else {
            System.out.println("Grafot ne sodrzi ciklus.");
        }
    }
}
