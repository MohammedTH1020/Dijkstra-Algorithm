import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
    List<List<Edge>> graph;
    boolean visited[];
    int distance[];
    int nodes;

    Graph(int nodes) {
        this.nodes = nodes;
        graph = new ArrayList<>();
        visited = new boolean[nodes];
        distance = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            graph.add(i, new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public void addEdge(int sourceNode, int targetNode, int distance) {
        graph.get(sourceNode).add(new Edge(targetNode, distance));
        graph.get(targetNode).add(new Edge(sourceNode, distance));
    }

    public ArrayList<ArrayList<Integer>> minimumDistanceBetweenTwoNodes(int source, int destination) {
        ArrayList<ArrayList<Integer>> minPaths = new ArrayList<>();
        for (int i = 0; i <nodes; i++)
            minPaths.add(new ArrayList<>());

        if (source == destination) {
            ArrayList<Integer> singleSourceList = new ArrayList<>();
            singleSourceList.add(source);
            minPaths.add(singleSourceList);
            return minPaths;
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.distanceFromNode - e2.distanceFromNode);

        distance[source] = 0;
        minHeap.add(new Edge(source, 0));
        minPaths.get(source).add(source);

        while (!minHeap.isEmpty()) {
            int v = minHeap.poll().targetNode;
            if (visited[v])
                continue;

            visited[v] = true;

            List<Edge> childList = graph.get(v);

            for (Edge child : childList) {
                int dist = child.distanceFromNode;
                int childNode = child.targetNode;

                if (!visited[childNode] && (distance[v] + dist < distance[childNode])) {
                    distance[childNode] = distance[v] + dist;
                    child.distanceFromNode = distance[v] + dist;
                    minHeap.add(child);

                    ArrayList<Integer> minPathAtV = new ArrayList<>(minPaths.get(v));
                    minPathAtV.add(childNode);
                    minPaths.set(childNode, minPathAtV);
                }
            }
        }

        return minPaths;
    }

}