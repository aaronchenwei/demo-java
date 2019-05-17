package io.github.aaronchenwei.demoguava.DAG;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Node {
    private final String nodeName;
    private int level = 0;
}
