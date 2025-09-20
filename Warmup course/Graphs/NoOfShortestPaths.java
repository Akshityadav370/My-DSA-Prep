import java.util.*;

public class NoOfShortestPaths {

    static void findPaths(List<List<Integer>> graph, int source, int n) {
        int[] dist = new int[n+1];
        int[] ways = new int[n+1];

        Arrays.fill(dist, -1);
        Arrays.fill(ways, 0);

        dist[source] = 0; 
        ways[source] = 1; 

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while(!queue.isEmpty()) {
            int u = queue.poll();

            for (int v: graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1; 
                    ways[v] = ways[u];
                    queue.add(v);
                } else if (dist[v] == dist[u] + 1) {    
                    ways[v] += ways[u];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(ways[i] + " ");
        }
        System.out.println();
    }
    
    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void main(String[] args) {
        int n = 6;  // Number of nodes
        int m = 6;  // Number of edges

        // Create adjacency list (1-based indexing)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Example edges
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);
        addEdge(graph, 3, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 6);

        findPaths(graph, 1, n);
    }
}
