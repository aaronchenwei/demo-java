package io.github.aaronchenwei.demoguava;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import io.github.aaronchenwei.demoguava.DAG.Node;
import io.github.aaronchenwei.demoguava.DAG.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class GuavaDAGTests {
  private static MutableGraph<Node> DAG;

  private static Node[] nodes;
  private static Path[] paths;

  @BeforeAll
  public static void beforeAll() {
    DAG =
        GraphBuilder.directed().allowsSelfLoops(false).nodeOrder(ElementOrder.insertion()).build();

    nodes =
        new Node[] {
          new Node("0"), new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"),
          //          new Node("6"),
          //          new Node("7"),
          //          new Node("8"),
          //          new Node("9"),
        };

    for (Node node : nodes) {
      DAG.addNode(node);
    }

    paths =
        new Path[] {
          Path.builder().pathName("P1").node(nodes[0]).node(nodes[1]).node(nodes[2]).build(),
          Path.builder().pathName("P2").node(nodes[0]).node(nodes[1]).node(nodes[3]).build(),
          Path.builder().pathName("P3").node(nodes[0]).node(nodes[1]).node(nodes[4]).build(),
          Path.builder().pathName("P4").node(nodes[0]).node(nodes[5]).node(nodes[4]).build(),
        };

    for (Path path : paths) {
      Node previous = null;
      for (Node node : path.getNodes()) {
        if (previous != null && DAG.hasEdgeConnecting(previous, node) == false) {
          DAG.putEdge(previous, node);
        }
        previous = node;
      }
    }
  }

  @DisplayName("Test Graph")
  @Test
  public void test1() {
    log.debug("{}", DAG);

    for (Node node : DAG.nodes()) {
      log.debug("Node {}: in = {}, out = {}", node, DAG.inDegree(node), DAG.outDegree(node));
    }
  }

  @DisplayName("Test Copy Graph")
  @Test
  public void test2() {
    MutableGraph<Node> copied = GraphBuilder.from(DAG).build();
    for (Node node : DAG.nodes()) {
      copied.addNode(node);
    }
    for (EndpointPair<Node> edge : DAG.edges()) {
      copied.putEdge(edge);
    }

    log.debug("{}", copied);

    for (Node node : copied.nodes()) {
      log.debug("Node {}: in = {}, out = {}", node, copied.inDegree(node), copied.outDegree(node));
    }
  }

  @DisplayName("Test Topological Sort")
  @Test
  public void test3() {
    Objects.requireNonNull(DAG);

    List<Node> sorted = new ArrayList<>(DAG.nodes().size());

    Queue<Node> roots =
        DAG.nodes().stream()
            .filter(node -> DAG.inDegree(node) == 0)
            .collect(toCollection(ArrayDeque::new));
    Map<Node, Integer> nonRootsToInDegree =
        DAG.nodes().stream()
            .filter(node -> DAG.inDegree(node) > 0)
            .collect(toMap(node -> node, DAG::inDegree, (a, b) -> a, HashMap::new));
    while (!roots.isEmpty()) {
      Node next = roots.remove();
      sorted.add(next);
      for (Node successor : DAG.successors(next)) {
        int newInDegree = nonRootsToInDegree.get(successor) - 1;
        nonRootsToInDegree.put(successor, newInDegree);
        if (newInDegree == 0) {
          nonRootsToInDegree.remove(successor);
          roots.add(successor);
          successor.setLevel(next.getLevel() + 1);
        }
      }
    }

    for (Node node : sorted) {
      log.debug("{}", node);
    }
  }
}
