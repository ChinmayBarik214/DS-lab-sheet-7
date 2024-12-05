import java.util.*;

public class BFS {
  private Map<String, List<String>> adjList = new HashMap<>();

  void addEdge(String u, String v) {
    adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
  }

  void bfs(String start) {
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();

    visited.add(start);
    queue.add(start);

    while (!queue.isEmpty()) {
      String node = queue.poll();
      System.out.print(node + " ");
      for (String neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
  }

  public static void main(String[] args) {
    BFS graph = new BFS();
    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("B", "D");
    graph.bfs("A"); // Output: A B C D
  }
}
