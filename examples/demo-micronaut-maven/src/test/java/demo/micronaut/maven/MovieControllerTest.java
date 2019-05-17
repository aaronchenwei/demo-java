package demo.micronaut.maven;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieControllerTest {
    @Test
    public void testIndex() throws Exception {
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

        RxHttpClient client = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL());

        Assertions.assertEquals(client.toBlocking().exchange("/movie/echo/test").status(), HttpStatus.OK);
        server.stop();
    }
}
