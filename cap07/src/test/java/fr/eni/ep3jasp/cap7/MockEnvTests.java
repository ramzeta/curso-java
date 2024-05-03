package fr.eni.ep3jasp.cap7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Pruebas unitarias con mocks
 *
 * @author $author$
 * @version $Revision$
  */

@ExtendWith(MockitoExtension.class)
public class MockEnvTests {
  /**
   * el entorno
   */
  @Mock
  Environment env;

  /**
   * el servicio
   */
  @Mock
  MiServicio miServicio;

  /**
   * Inicialización del TU
   */
  @BeforeEach
  public void init() {
    // Inicialización de los mocks 
    initMocks(this);

    // Definición del mock
    //Es necesaria une variable de entorno para que funcione.
    when(this.env.getProperty("M4_HOME")).thenReturn("c:\\M4");

    // Inicialización del service
    miServicio = new MiServicioImpl();

    // Garantizar que el servicio utiliza el entorno mockeado
    miServicio.setEnvironment(this.env);
  }

  /**
   * Acción sobre el entorno 
   */
  @Test
  public void shouldDoSomething() {
    env = miServicio.getEnvironment();
  }
}
