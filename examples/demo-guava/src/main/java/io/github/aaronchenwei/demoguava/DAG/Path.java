package io.github.aaronchenwei.demoguava.DAG;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class Path {
    private final String pathName;
    @Singular
    private final List<Node> nodes;

    @Override
    public String toString() {
        return this.getPathName();
    }
}
