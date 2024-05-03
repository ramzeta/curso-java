package fr.eni.ep3jasp;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.Locale;


/**
 * Clase de soporte para los recursos
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
@Service("resourceHelperService")
public class ResourceHelperService implements MessageSourceAware {
  /**
   * La fuente de los mensajes
   */
  private MessageSource messageSource;

  /**
   * El setter
   *
   * @param messageSource La fuente de los mensajes
   */
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Ver los mensajes
   */
  public void VerMessages() {
    String name = messageSource.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("User name (English) : " + name);

    name = messageSource.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("Nombre usaurio (España): " + name);
  }
}
