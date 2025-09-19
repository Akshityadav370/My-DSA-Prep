import java.util.*;

public class ShortestDistanceBFS {
    static void bfs(List<List<Integer>> graph, int n, int start) {
        int[] dist = new int[n + 1];  // distance array (1-based indexing)
        Arrays.fill(dist, -1);        // -1 means not visited

        Queue<Integer> queue = new LinkedList<>();
        dist[start] = 0;              // Distance to source = 0
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (dist[neighbor] == -1) {  // Not visited yet
                    dist[neighbor] = dist[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // Print distances for nodes 1 to N
        for (int i = 1; i <= n; i++) {
            System.out.print(dist[i] + " ");
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

        bfs(graph, n, 1);  // Source node is 1
    }
}
