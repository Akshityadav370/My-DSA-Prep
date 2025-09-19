import java.util.*;

public class BFS {
    static void bfs(List<List<Integer>> graph, int n, int start) {
        boolean[] visited = new boolean[n];
        visited[start] = true; 
        Queue<Integer> queue = new LinkedList<>(); 

        queue.add(start); 

        System.out.println("BFS Traversal: ");
        Map<Integer, Integer> levels = new HashMap<>();
        levels.put(start, 0); // Start node is at level 0

        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at this level
            // process all nodes at the current level
            for (int i = 0; i < size; i++) {
                int popped = queue.poll(); 
                int level = levels.get(popped);

                System.out.println("Level " + level + ": " + popped);

                for (int node : graph.get(popped)) {
                    if (!visited[node]) {
                        queue.add(node); 
                        visited[node] = true; 
                        levels.put(node, level + 1); // child's level = parent level + 1
                    }
                }
            }
        }
    }

    static void addEdge(List<List<Integer>> graph, int v, int u) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void main(String[] args) {
        int n = 6; 
        List<List<Integer>> graph = new ArrayList<>(); 
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);
        addEdge(graph, 3, 5);
        addEdge(graph, 4, 5);

        bfs(graph, n, 0);
    }
}
