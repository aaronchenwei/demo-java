package io.github.aaronchenwei.demoguava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DemoGuavaApplicationTests {

  @BeforeAll
  public static void setup() {
    log.debug("Test");
  }

  @Test
  public void contextLoads() {}
}
