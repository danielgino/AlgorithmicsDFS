# Question 1
DFS Edge Classification in a Directed Graph
This program performs a Depth-First Search (DFS) on a directed graph and classifies all edges into:

Tree Edges: edges that are part of the DFS tree

Back Edges: edges pointing to an ancestor in the DFS tree

Forward Edges: edges pointing to a descendant (non-tree)

Cross Edges: edges between unrelated subtrees

It also prints for each node:

π[u]: parent of node u

d[u]: discovery time

f[u]: finish time

🕒 Time Complexity: O(V + E)

## Input Graph
![image](https://github.com/user-attachments/assets/94c18727-0945-4e6a-a149-deaedd139c6c)

```
        //מקרא הגרף
0 → s
1 → z
2 → w
3 → y
4 → x
5 → v
6 → u
7 → t
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
```

## Output

```
Node: 0 Connected to: [1, 2]
Node: 1 Connected to: [2, 3]
Node: 2 Connected to: [4]
Node: 3 Connected to: [4]
Node: 4 Connected to: [1]
Node: 5 Connected to: [2, 0]
Node: 6 Connected to: [5, 7]
Node: 7 Connected to: [6, 5]
Tree Edge: 0 → 1
Tree Edge: 1 → 2
Tree Edge: 2 → 4
Back Edge: 4 → 1
Tree Edge: 1 → 3
Cross Edge: 3 → 4
Forward Edge: 0 → 2
Cross Edge: 5 → 2
Cross Edge: 5 → 0
Cross Edge: 6 → 5
Tree Edge: 6 → 7
Back Edge: 7 → 6
Cross Edge: 7 → 5
Node	     Parent	        Discovery	       Finish
0		-1			0		9
1		0			1		8
2		1			2		5
3		1			6		7
4		2			3		4
5		-1			10		11
6		-1			12		15
7		6			13		14

```
# Question 2

## Semi-Connected Graph
This program checks whether a directed graph is semi-connected, meaning:

For every pair of vertices u and v, either u can reach v, or v can reach u.

## How It Works:
DFS + Topological Sort on the original graph

Graph Reversal

Second DFS on the reversed graph to find Strongly Connected Components (SCCs)

Build DAG of SCCs (each SCC is a node)

Topological Sort of SCCs

Check if there's an edge from every SCC to the next in the topological order

## Input Graph
![image](https://github.com/user-attachments/assets/94c18727-0945-4e6a-a149-deaedd139c6c)

```
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
```

## Output
```
Node: 0 Connected to: [1, 2]
Node: 1 Connected to: [2, 3]
Node: 2 Connected to: [4]
Node: 3 Connected to: [4]
Node: 4 Connected to: [1]
Node: 5 Connected to: [2, 0]
Node: 6 Connected to: [5, 7]
Node: 7 Connected to: [6, 5]
Tree Edge: 0 → 1
Tree Edge: 1 → 2
Tree Edge: 2 → 4
Back Edge: 4 → 1
Tree Edge: 1 → 3
Cross Edge: 3 → 4
Forward Edge: 0 → 2
Cross Edge: 5 → 2
Cross Edge: 5 → 0
Cross Edge: 6 → 5
Tree Edge: 6 → 7
Back Edge: 7 → 6
Cross Edge: 7 → 5
Topological Order:
6 7 5 0 1 3 2 4 
SCCs:
SCC 0: [6, 7]
SCC 1: [5]
SCC 2: [0]
SCC 3: [1, 4, 2, 3]

Topological order of SCCs: [0, 1, 2, 3]
Is the graph semi-connected? true
```
