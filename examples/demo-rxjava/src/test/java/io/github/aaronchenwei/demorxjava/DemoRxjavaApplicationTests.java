package io.github.aaronchenwei.demorxjava;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.reactivex.*;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoRxjavaApplicationTests {

  @Test
  public void simple() {
    Flowable.just("Hello world").subscribe(log::debug);
  }

  @Test
  public void simple2() {
    String[] result = {""};
//    String result;
    String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
    Observable<String> observable = Observable.fromArray(letters);
    observable.subscribe(
        i -> result[0] += i,  //OnNext
        Throwable::printStackTrace, //OnError
        () -> result[0] += "_Completed" //OnCompleted
    );
    log.debug(result[0]);
    assertTrue(result[0].equals("abcdefg_Completed"));
  }

}
