package io.github.aaronchenwei.demoguava.DAG;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Edge {
    @NonNull
    private final Path path;
    @NonNull
    private final Node from;
    @NonNull
    private final Node to;
}
