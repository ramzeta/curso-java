package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author $author$
 * @version $Revision$
 */
@Slf4j
public class Main {
    /**
     * Metodo main
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
 /*       ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        log.info("Inicio de losejemplos");

        Flux.just("a", "b", "c", "d", "e", "f", "g", "h")
                .log()
                .subscribe();

        log.info("Suscripción:");
        List<String> cadenas = new ArrayList<>();
        Flux.just("aaa", "bbb", "ccc")
                .log()
                .subscribe(cadenas::add);

        log.info("Suscripción con detalles:");

        Mono.just("a")
                .delaySubscription(Mono.delay(Duration.ofSeconds(5)))
                .log()
                .subscribe();

        Flux.just("a", "b", "c")
                .delayElements(Duration.ofSeconds(2))
                .log()
                .subscribe();

        Mono.just("d")
                .delaySubscription(Mono.delay(Duration.ofSeconds(5)))
                .log()
                .block();

        Flux.just("e", "f", "g")
                .delayElements(Duration.ofSeconds(2))
                .log()
                .blockLast();

        log.info("Suscripción con scheduler:");
        Scheduler scheduler = Schedulers.newElastic("es1");
        Mono.just("dos")
                .delayElement(Duration.ofSeconds(2), scheduler)
                .log()
                .block();

        List<String> elementos = new ArrayList<>();
        Flux.just("a1", "b1", "c1", "d1")
                .log()
                .map(i -> i + "z1")
                .subscribeOn(Schedulers.parallel())
                .subscribe(elementos::add);
        log.info("combinación:");
        List<String> elementos = new ArrayList<>();

        Mono<String> a = Mono.just("action A")
                .delaySubscription(Duration.ofMillis(550));
        Flux<String> b = Flux.just("Levantarse", "Comer",
                "Echar la siesta")
                .delayElements(Duration.ofMillis(550));

        Flux.first(a, b)
                .toIterable()
                .forEach(System.out::println);

        log.info("Lotes:");
        List<String> elementos2 = new ArrayList<>();
        Flux.just("a", "b", "c", "d", "e", "f")
                .log()
                .subscribe(new Subscriber<String>() {
                    private Subscription s;
                    int onNext;
                    // Pide 3 a la suscripción
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(3);
                    }
                    @Override
                    public void onNext(String st) {
                        elementos2.add(st);
                        onNext++;
                        //Vuelve a pedir 3 más
                        if (onNext % 3 == 0) {
                            s.request(3);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {}
                    @Override
                    public void onComplete() {}
                });
*/


        log.info("Fin de los ejemplos");

    }
}
