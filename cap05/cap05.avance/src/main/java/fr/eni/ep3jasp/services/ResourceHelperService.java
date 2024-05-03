package fr.eni.ep3jasp.services;

import org.springframework.context.ResourceLoaderAware;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


/*
Since bean does not have the application context access, how can a bean access a resources?
The workaround is implement the ResourceLoaderAware interface and create setter method for
ResourceLoader object. Spring will DI the resource loader into your bean.
 */
/**
 * Servicio de ayuda para los recursos
 *
 * @author $author$
 * @version $Revision$
  */
public class ResourceHelperService implements ResourceLoaderAware {
  /**
   * La carga del recurso
   */
  private ResourceLoader resourceLoader;

  /**
   * Setter del cargador de recursos
   *
   * @param resourceLoader El cargador de recursos
   */
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  /**
   * Getter del cargador de recursos
   *
   * @param location La ubicación
   *
   * @return el cargador de recursos
   */
  public Resource getResource(String location) {
    return resourceLoader.getResource(location);
  }
}
