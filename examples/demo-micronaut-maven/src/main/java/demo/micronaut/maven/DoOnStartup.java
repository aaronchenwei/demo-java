package demo.micronaut.maven;

import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Singleton
@Slf4j
public class DoOnStartup {
    @EventListener
    void onStartup(ServiceStartedEvent event) {
        log.info("onStartup - {}", event);
    }
}
