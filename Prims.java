import java.util.*;

class Prims {
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
    graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight)); // Ensure bidirectional edges
  }

  void minimumSpanningTree(String start) {
    PriorityQueue<EdgeWithSource> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    Set<String> visited = new HashSet<>();
    List<String> mst = new ArrayList<>();

    // Add all edges of the start node to the priority queue
    visited.add(start);
    for (Edge edge : graph.getOrDefault(start, new ArrayList<>())) {
      pq.add(new EdgeWithSource(start, edge.dest, edge.weight));
    }

    // Process the priority queue until all nodes are visited
    while (!pq.isEmpty()) {
      EdgeWithSource currentEdge = pq.poll();
      String from = currentEdge.source;
      String to = currentEdge.dest;

      if (!visited.contains(to)) {
        visited.add(to);
        mst.add(from + "-" + to); // Add edge to the MST
        for (Edge edge : graph.getOrDefault(to, new ArrayList<>())) {
          if (!visited.contains(edge.dest)) {
            pq.add(new EdgeWithSource(to, edge.dest, edge.weight));
          }
        }
      }
    }

    System.out.println("Minimum Spanning Tree: " + mst);
  }

  static class EdgeWithSource {
    String source;
    String dest;
    int weight;

    EdgeWithSource(String source, String dest, int weight) {
      this.source = source;
      this.dest = dest;
      this.weight = weight;
    }
  }

  public static void main(String[] args) {
    Prims graph = new Prims();
    graph.addEdge("A", "B", 1);
    graph.addEdge("B", "C", 2);
    graph.addEdge("A", "C", 3);
    graph.minimumSpanningTree("A"); // Output: Minimum Spanning Tree: [A-B, B-C]
  }
}
