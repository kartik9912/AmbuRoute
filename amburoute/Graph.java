package amburoute;

import java.util.*;

public class Graph {
    private Map<String, List<Node>> adjList = new HashMap<>();

    public void addEdge(String from, String to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Node(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Node(from, weight));
    }

    public double getShortestDistance(String start, String end) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));

        for (String key : adjList.keySet()) {
            dist.put(key, Integer.MAX_VALUE);
        }

        dist.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.id.equals(end)) return dist.get(end);

            for (Node neighbor : adjList.getOrDefault(current.id, new ArrayList<>())) {
                int newDist = dist.get(current.id) + neighbor.weight;
                if (newDist < dist.get(neighbor.id)) {
                    dist.put(neighbor.id, newDist);
                    pq.add(new Node(neighbor.id, newDist));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static class Node {
        String id;
        int weight;

        Node(String id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}
