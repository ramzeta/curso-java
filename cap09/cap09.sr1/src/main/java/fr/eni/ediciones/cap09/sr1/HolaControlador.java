package fr.eni.editions.cap09.sr1;

import org.springframework.ui.Model;
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
@RequestMapping("")
public class HolaController {
  /**
   * DOCUMENT ME!
   *
   * @param nombre DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @RequestMapping(value = "/hola/{nombre}", method = RequestMethod.GET)
  public String getGreeting(@PathVariable String nombre) {
    String resultado = "Hola " + nombre;

    return resultado;
  }
}

