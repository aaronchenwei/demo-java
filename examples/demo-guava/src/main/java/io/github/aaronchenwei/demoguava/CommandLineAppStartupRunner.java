package io.github.aaronchenwei.demoguava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

// @Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {
  @Override
  public void run(String... args) {
    log.info("CommandLineAppStartupRunner");

    MutableNetwork<Node, Edge> network =
        NetworkBuilder.directed()
            .allowsParallelEdges(true)
            .nodeOrder(ElementOrder.insertion())
            .edgeOrder(ElementOrder.insertion())
            .expectedNodeCount(100000)
            .expectedEdgeCount(1000000)
            .build();

    Path[] paths =
        new Path[] {
          new Path("P0"), new Path("P1"), new Path("P2"), new Path("P3"), new Path("P4"),
        };

    Node[] nodes =
        new Node[] {
          new Node("A"), new Node("B"), new Node("C"), new Node("D"), new Node("E"), new Node("F"),
          //                new Node("G"),
          //                new Node("H"),
          //                new Node("I"),
          //                new Node("J"),
        };

    for (Node node : nodes) {
      network.addNode(node);
    }

    network.addEdge(nodes[0], nodes[1], new Edge(paths[0]));
    network.addEdge(nodes[1], nodes[2], new Edge(paths[0]));
    network.addEdge(nodes[0], nodes[1], new Edge(paths[1]));
    network.addEdge(nodes[1], nodes[4], new Edge(paths[1]));
    network.addEdge(nodes[0], nodes[1], new Edge(paths[2]));
    network.addEdge(nodes[1], nodes[5], new Edge(paths[2]));
    network.addEdge(nodes[0], nodes[1], new Edge(paths[3]));
    network.addEdge(nodes[0], nodes[3], new Edge(paths[4]));
    network.addEdge(nodes[3], nodes[2], new Edge(paths[4]));

    log.debug("{}", network.edges());

    log.debug("{}", network.edgesConnecting(nodes[0], nodes[1]));

    for (Node node : network.nodes()) {
      log.debug("node: {}", node);
      //            log.debug("predecessors: {}", network.predecessors(node));
      log.debug("successors: {}", network.successors(node));
      log.debug("==========");
    }
  }

  public static List<Node> topologicalSort(final MutableNetwork<Node, Edge> network) {
    return null;
  }

  @Getter
  @Setter
  private static class Path {
    private final String pathName;

    public Path(String pathName) {
      this.pathName = pathName;
    }

    @Override
    public String toString() {
      return this.getPathName();
    }
  }

  @Getter
  @Setter
  private static class Node {
    private final String nodeName;
    private Node trueNode;
    private Node falseNode;

    public Node(String nodeName) {
      this.nodeName = nodeName;
      this.trueNode = null;
      this.falseNode = null;
    }

    @Override
    public String toString() {
      return this.getNodeName();
    }
  }

  @Getter
  @Setter
  @ToString
  private static class Edge {
    private final Path path;

    public Edge(Path path) {
      this.path = path;
    }
  }
}
