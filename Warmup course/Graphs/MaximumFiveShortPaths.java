import java.util.*;

class MaximumFiveShortPaths {

    static void FiveCountPaths(List<List<Integer>> graph, int n, int start, int[] b) {
        int[] dist = new int[n + 1];
        int[] max5 = new int[n + 1];
        int[] cnt = new int[n + 1];

        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        dist[start] = 0;
        max5[start] = (b[start] == 5 ? 1 : 0);
        cnt[start] = 1;  // one path to start

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : graph.get(u)) {
                int new5 = max5[u] + (b[v] == 5 ? 1 : 0);

                if (dist[v] == -1) {
                    // first time visit
                    dist[v] = dist[u] + 1;
                    max5[v] = new5;
                    cnt[v] = cnt[u];
                    q.add(v);
                }
                else if (dist[v] == dist[u] + 1) {
                    // another shortest path
                    if (new5 > max5[v]) {
                        max5[v] = new5;
                        cnt[v] = cnt[u];  // reset count for new max
                    } else if (new5 == max5[v]) {
                        cnt[v] += cnt[u]; // add more paths with same max
                    }
                }
            }
        }

        System.out.println("Max 5's on shortest paths:");
        for (int i = 1; i <= n; i++) System.out.print(max5[i] + " ");
        System.out.println();

        System.out.println("Number of paths with max 5's:");
        for (int i = 1; i <= n; i++) System.out.print(cnt[i] + " ");
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
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);
        addEdge(graph, 3, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 6);

        int[] b = {0, 0, 5, 0, 5, 0, 5};

        FiveCountPaths(graph, n, 1, b);
    }
}
