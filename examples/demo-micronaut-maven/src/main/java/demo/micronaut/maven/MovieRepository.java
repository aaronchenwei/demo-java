package demo.micronaut.maven;

import java.util.stream.Stream;

public interface MovieRepository {
    Stream<Movie> findByName(String name);
}
