package fr.eni.ep3jasp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@RestController
@RequestMapping("/service/greeting")
public class SpringServiceController {
  /**
   * DOCUMENT ME!
   *
   * @param name DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String getGreeting(@PathVariable
  String name) {
    String result = "Hello " + name;

    return result;
  }
}
