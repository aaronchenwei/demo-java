package io.github.aaronchenwei.demorxjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoRxjavaApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(DemoRxjavaApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("EXECUTING : command line runner");

    for (int i = 0, len = args.length; i < len; i += 1) {
      log.info("args[{}]: {}", i, args[i]);
    }
  }
}
