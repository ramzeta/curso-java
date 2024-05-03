package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
class MiSubscriber implements Subscriber<String> {

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("onSubscribe() -> {}", Long.MAX_VALUE);
        subscription.request(Long.MAX_VALUE);
    }
    @Override
    public void onNext(String item) {
        log.info("onNext() -> {}", item);
    }
    @Override
    public void onError(Throwable throwable) {
        log.error("onError()", throwable);
    }
    @Override
    public void onComplete() {
        log.info("onComplete -> Terminado.");
    }
}