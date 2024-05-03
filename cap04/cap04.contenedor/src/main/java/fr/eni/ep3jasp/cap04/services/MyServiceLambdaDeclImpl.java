package fr.eni.ep3jasp.cap04.services;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServiceLambdaDeclImpl implements MyService {
    public void myMethod(String msg) {
        log.info(msg);
    }
}
