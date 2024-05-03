package fr.eni.ep3jasp.cap04.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import fr.eni.ep3jasp.cap04.services.MyService;

@Slf4j
@Service("myServiceByAnnotation")
public class MyServiceImpl implements MyService {
  @Override
  public void myMethod(String msg) {
    log.info(msg);
  }
}
