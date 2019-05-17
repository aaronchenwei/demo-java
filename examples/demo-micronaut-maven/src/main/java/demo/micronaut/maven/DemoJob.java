package demo.micronaut.maven;

import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.inject.*;

@Slf4j
@Singleton
public class DemoJob {
    @Scheduled(fixedRate = "1m")
    public void process() {
        log.info("process...");
    }
}
