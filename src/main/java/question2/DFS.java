package question2;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFS {

    private boolean[] visited;
    private int[] parent;
    private int[] discoveryTime;
    private int[] finishTime;
    private Stack<Integer> topoStack = new Stack<>();

    private int time;
    public DFS(List <List<Integer>> graph){
        int n=graph.size();
        visited =new boolean[n];
        parent=new int[n];
        discoveryTime=new int[n];
        finishTime=new int[n];
        Arrays.fill(parent,-1);
        time=0;
        for (int u=0; u<n; u++){
            if (!visited[u]){
                dfsVisit(graph,u);
            }
        }
        System.out.println("Topological Order:");
        for (int i = topoStack.size() - 1; i >= 0; i--) {
            System.out.print(topoStack.get(i) + " ");
        }

//        System.out.println("Node\tParent\tDiscovery\tFinish");
//        for (int i = 0; i < n; i++) {
//            System.out.println(i + "\t"+"\t" + parent[i] + "\t\t\t" + discoveryTime[i] + "\t\t" + finishTime[i]);
//        }

    }

    public  void dfsVisit(List <List<Integer>> graph,int u){
        visited[u]=true;
        discoveryTime[u]=time++;
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                System.out.println("Tree Edge: " + u + " → " + v);
                dfsVisit(graph, v);
            } else {
                if (finishTime[v] == 0) {
                    System.out.println("Back Edge: " + u + " → " + v);
                } else if (discoveryTime[u] < discoveryTime[v]) {
                    System.out.println("Forward Edge: " + u + " → " + v);
                } else {
                    System.out.println("Cross Edge: " + u + " → " + v);
                }
            }
        }
        finishTime[u] = time++;
        topoStack.push(u);

    }
    public Stack<Integer> getFinishStack() {
        return topoStack;
    }
}
