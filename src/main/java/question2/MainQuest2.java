package question2;



import java.util.*;

public class MainQuest2 {
    public static void main(String[] args) {
        int graphSize=8;
        List <List<Integer>> graph=new ArrayList<>();
        for (int i = 0; i < graphSize; i++) {
            graph.add(new ArrayList<>());
        }
        //מקרא הגרף לקוח משאלה 1
//        0 → s
//        1 → z
//        2 → w
//        3 → y
//        4 → x
//        5 → v
//        6 → u
//        7 → t
        graph.get(0).add(1);///S TO Z
        graph.get(0).add(2);//S TO W

        graph.get(1).add(2);//Z TO W
        graph.get(1).add(3);//Z TO Y

        graph.get(3).add(4);// Y to X
        graph.get(4).add(1);//X TO Z

        graph.get(2).add(4);//W TO X
        graph.get(5).add(2);//V TO W
        graph.get(5).add(0);//V TO S
        graph.get(6).add(5);//U TO V
        graph.get(6).add(7);//U TO T
        graph.get(7).add(6);//T TO U
        graph.get(7).add(5);//T TO V

        for (int i = 0; i < graph.size(); i++) {
            System.out.println("Node: " + i + " Connected to: " + graph.get(i));
        }

      DFS dfs=new DFS(graph);
        Stack<Integer> finishStack = dfs.getFinishStack();
        List<List<Integer>> reversed = reverseGraph(graph);
        List<List<Integer>> sccs = getSCCs(reversed, finishStack);
        System.out.println("\nSCCs:");
        for (int i = 0; i < sccs.size(); i++) {
            System.out.println("SCC " + i + ": " + sccs.get(i));
        }

        int[] nodeToSCC = mapNodesToSCCs(sccs, graphSize);
        List<List<Integer>> sccGraph = buildSCCDAG(graph, nodeToSCC, sccs.size());
        List<Integer> topoOrder = topologicalSort(sccGraph);

        System.out.println("\nTopological order of SCCs: " + topoOrder);
        boolean semiConnected = isSemiConnected(sccGraph, topoOrder);
        System.out.println("Is the graph semi-connected? " + semiConnected);
    }


    public static List<List<Integer>> getSCCs(List<List<Integer>> reversedGraph, Stack<Integer> finishStack) {
        boolean[] visited = new boolean[reversedGraph.size()];
        List<List<Integer>> sccs = new ArrayList<>();

        while (!finishStack.isEmpty()) {
            int node = finishStack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfsCollect(reversedGraph, node, visited, component);
                sccs.add(component);
            }
        }
        return sccs;
    }

    public static void dfsCollect(List<List<Integer>> graph, int u, boolean[] visited, List<Integer> component) {
        visited[u] = true;
        component.add(u);
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                dfsCollect(graph, v, visited, component);
            }
        }
    }

    public static List<List<Integer>> reverseGraph(List<List<Integer>> graph) {
        int n = graph.size();
        List<List<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < n; i++) reversed.add(new ArrayList<>());
        for (int u = 0; u < n; u++) {
            for (int v : graph.get(u)) {
                reversed.get(v).add(u);
            }
        }
        return reversed;
    }
    public static int[] mapNodesToSCCs(List<List<Integer>> sccs, int totalNodes) {
        int[] nodeToSCC = new int[totalNodes];
        for (int i = 0; i < sccs.size(); i++) {
            for (int node : sccs.get(i)) {
                nodeToSCC[node] = i;
            }
        }
        return nodeToSCC;
    }

    public static List<List<Integer>> buildSCCDAG(List<List<Integer>> graph, int[] nodeToSCC, int sccCount) {
        List<List<Integer>> sccGraph = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) sccGraph.add(new ArrayList<>());

        for (int u = 0; u < graph.size(); u++) {
            for (int v : graph.get(u)) {
                int cu = nodeToSCC[u];
                int cv = nodeToSCC[v];
                if (cu != cv && !sccGraph.get(cu).contains(cv)) {
                    sccGraph.get(cu).add(cv);
                }
            }
        }
        return sccGraph;
    }
    public static List<Integer> topologicalSort(List<List<Integer>> graph) {
        int n = graph.size();
        int[] indegree = new int[n];
        for (List<Integer> neighbors : graph) {
            for (int v : neighbors) indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);
            for (int v : graph.get(u)) {
                if (--indegree[v] == 0) queue.add(v);
            }
        }
        return result;
    }
    public static boolean isSemiConnected(List<List<Integer>> sccGraph, List<Integer> topoOrder) {
        for (int i = 0; i < topoOrder.size() - 1; i++) {
            int from = topoOrder.get(i);
            int to = topoOrder.get(i + 1);
            if (!sccGraph.get(from).contains(to)) return false;
        }
        return true;
    }

}
