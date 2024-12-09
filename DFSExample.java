import java.util.*;

public class DFSExample {
    public static boolean dfsCycleDetection(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited, int parent) {
        visited.add(node);
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            //neighbor is already visited and it is not a parent then its a cycle
            if (!visited.contains(neighbor)) {
                if (dfsCycleDetection(graph, neighbor, visited, node)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        // Example graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 2));
        graph.put(2, Arrays.asList(0, 1, 3));
        graph.put(3, Arrays.asList(2));

        // Detect cycle
        Set<Integer> visited = new HashSet<>();
        boolean hasCycle = false;
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfsCycleDetection(graph, node, visited, -1)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        System.out.println("Graph has a cycle: " + hasCycle); // Output: Graph has a cycle: true
    }
}
