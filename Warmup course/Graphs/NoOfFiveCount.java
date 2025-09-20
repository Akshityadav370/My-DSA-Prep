import java.util.*;

public class NoOfFiveCount {

    static void FiveCount(List<List<Integer>> graph, int n, int start, int[] b) {
        int[] dist = new int[n + 1];        
        int[] fiveCount = new int[n + 1];   

        Arrays.fill(dist, -1); 

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        dist[start] = 0;
        fiveCount[start] = (b[start] == 5 ? 1 : 0); 

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph.get(u)) {
                // First time visiting v → shortest path found
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    fiveCount[v] = fiveCount[u] + (b[v] == 5 ? 1 : 0);
                    queue.add(v);
                }
                // Another shortest path found → update max count of 5's
                else if (dist[v] == dist[u] + 1) {
                    fiveCount[v] = Math.max(fiveCount[v], fiveCount[u] + (b[v] == 5 ? 1 : 0));
                }
            }
        }

        System.out.println("Max 5's on shortest paths:");
        for (int i = 1; i <= n; i++) {
            System.out.print(fiveCount[i] + " ");
        }
        System.out.println();
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void main(String[] args) {
        int n = 6;
        int m = 6;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);
        addEdge(graph, 3, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 6);

        int[] b = {0, 0, 5, 0, 5, 0, 5}; 

        FiveCount(graph, n, 1, b);
    }
}
