package org.example.question1;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainQuest1 {
    public static void main(String[] args) {
        int graphSize=8;
        List <List<Integer>> graph=new ArrayList<>();
        for (int i = 0; i < graphSize; i++) {
            graph.add(new ArrayList<>());
        }
        //מקרא הגרף
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
    }
}