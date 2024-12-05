import java.util.*;

class Dijkstra {
  private Map<String, List<Edge>> graph = new HashMap<>();

  static class Edge {
    String dest;
    int weight;

    Edge(String dest, int weight) {
      this.dest = dest;
      this.weight = weight;
    }
  }

  void addEdge(String u, String v, int weight) {
    graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
    graph.computeIfAbsent(v, k -> new ArrayList<>()); // Ensure all nodes are initialized
  }

  void shortestPath(String start, String end) {
    PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    Map<String, Integer> distances = new HashMap<>();
    Map<String, String> parents = new HashMap<>();

    // Initialize distances with a large value (infinity-like)
    for (String node : graph.keySet())
      distances.put(node, Integer.MAX_VALUE);
    distances.put(start, 0); // Distance to the start node is zero
    pq.add(new Edge(start, 0));

    while (!pq.isEmpty()) {
      Edge current = pq.poll();
      String currentNode = current.dest;

      // Traverse neighbors of the current node
      for (Edge edge : graph.getOrDefault(currentNode, new ArrayList<>())) {
        int newDist = distances.get(currentNode) + edge.weight;

        // Update the distance if a shorter path is found
        if (newDist < distances.get(edge.dest)) {
          distances.put(edge.dest, newDist);
          parents.put(edge.dest, currentNode);
          pq.add(new Edge(edge.dest, newDist));
        }
      }
    }

    // Construct the shortest path from start to end
    List<String> path = new ArrayList<>();
    for (String at = end; at != null; at = parents.get(at))
      path.add(0, at);

    if (distances.get(end) == Integer.MAX_VALUE) {
      System.out.println("No path exists from " + start + " to " + end);
    } else {
      System.out.println("Shortest path: " + path);
    }
  }

  public static void main(String[] args) {
    Dijkstra graph = new Dijkstra();
    graph.addEdge("A", "B", 1);
    graph.addEdge("B", "C", 2);
    graph.addEdge("A", "C", 4);
    graph.shortestPath("A", "C"); // Output: Shortest path: [A, B, C]
  }
}
