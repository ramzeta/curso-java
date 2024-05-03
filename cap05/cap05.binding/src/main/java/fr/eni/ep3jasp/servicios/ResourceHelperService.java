package fr.eni.ep3jasp.services;

import lombok.extern.java.Log;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Locale;


/**
 * El caso de soporte
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class ResourceHelperService implements MessageSourceAware {
  /**
   * messageSource
   */
  private MessageSource messageSource;

  /**
   * Setter del messageSource
   *
   * @param messageSource el messageSource
   */
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Visualización de los mensajes
   */
  public void VerMessages() {
    String name = messageSource.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("User name (English) : " + name);

    name = messageSource.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("Nombre del usuario (Español) : " + name);
  }
}
