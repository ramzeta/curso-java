import fr.eni.ep3jasp.HashChecker;
import fr.eni.ep3jasp.MiLibreriaReactiva;
import fr.eni.ep3jasp.Main;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Slf4j
public class Test1 {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        log.info("test1:");
        List<String> cadenas = new ArrayList<>();
        Flux.just("aaa", "bbb", "ccc")
                .log()
                .subscribe(cadenas::add);
        assertThat(cadenas).containsExactly("aaa", "bbb", "ccc");

    }

    @Test
    public void test2() {
        StepVerifier.create(Flux.just("foo", "bar"))
                .expectNext("foo")
                .expectNext("bar")
                .expectComplete()
                .verify();

    }

    @Test
    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world"))
                                .delaySubscription(Duration.ofMillis(500));

        helloPauseWorld.subscribe(System.out::println);
    }

    @Test
    public void test3() {
        MiLibreriaReactiva libreria = new MiLibreriaReactiva();
        StepVerifier.create(libreria.numero10(3))
                .expectNext("33", "44", "55")
                .expectComplete()
                .verify();
    }
    @Test
    public void testWithDelay() {
        MiLibreriaReactiva libreria = new MiLibreriaReactiva();
        Duration testDuration =
                StepVerifier.withVirtualTime(() -> libreria.conDelay("20", 30))
                        .expectSubscription()
                        .thenAwait(Duration.ofSeconds(10))
                        .expectNoEvent(Duration.ofSeconds(10))
                        .thenAwait(Duration.ofSeconds(10))
                        .expectNext("20")
                        .expectComplete()
                        .verify();
        System.out.println(testDuration.toMillis() + "ms");
    }

    @Test
    public void testTestPublisher() {
        TestPublisher.<String>create()
                .next("aaa", "bbb", "ccc")
                .error(new RuntimeException("Mi mensaje"));
    }

    @Test
    public void testHashChecker() {
        final TestPublisher<String> testPublisher =
                TestPublisher.create();
        HashChecker hashChecker = new HashChecker(testPublisher.flux());
        StepVerifier.create(hashChecker.getMd5Hex())
                .then(() -> testPublisher.emit("password", "Reactor",
                        "invalide"))
                .expectNext("5F4DCC3B5AA765D61D8327DEB882CF99", "1436185F7342E0210BE86E7E17963250", "E220F0FFF51A14C886A8836124B795CB")
                .verifyComplete();
    }


}