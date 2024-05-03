package fr.eni.ep3jasp;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import reactor.core.publisher.Flux;

@Data
public class HashChecker {
    private final Flux<String> source;
    String hash = "22D22A24E777A441B2258DD8FF7F3321";
    String password = "Reactor";

    static String getMd5Hex(String val) {
        return DigestUtils.md5Hex(val).toUpperCase();
    }

    public HashChecker(Flux<String> source) {
        this.source = source;
    }

    public Flux<String> getMd5Hex() {
        return source.map(HashChecker::getMd5Hex);
    }
}