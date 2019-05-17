package demo.micronaut.maven;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/movie")
public class MovieController {
    @Get("/echo/{text}")
    public Single<String> echo(String text) {
        return Single.just("> " + text);
    }
}
