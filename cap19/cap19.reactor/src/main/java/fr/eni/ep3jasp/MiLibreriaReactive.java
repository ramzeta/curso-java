package fr.eni.ep3jasp;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class MiLibreriaReactiva {
    public Flux<String> numero10(int from) {
        return Flux.range((int) from, 3)
                .map(i -> i +""+i);
    }

    public Mono<String> conDelay(String valor, int delaySeconds) {
        return Mono.just(valor)
                .delaySubscription(Duration.ofSeconds(delaySeconds));
    }
}