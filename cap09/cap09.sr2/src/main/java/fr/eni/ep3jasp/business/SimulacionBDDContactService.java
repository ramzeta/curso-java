/**
 *
 */
package fr.eni.ep3jasp.business;

import fr.eni.ep3jasp.domain.Usuario;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Service
public class SimulacionBDDContactService implements UsuarioServicio {
  /**
   * DOCUMENT ME!
   */
  private Map<Long, Usuario> usuarios = new LinkedHashMap<Long, Usuario>();

  /**
   * Creates a new SimulacionBDDContactService object.
   */
  public SimulacionBDDContactService() {
    Usuario usuario = new Usuario(1, "Sarah", "CONNOR");
    usuarios.put(usuario.getId(), usuario);
    usuario = new Usuario(2, "John", "CONNOR");
    usuarios.put(usuario.getId(), usuario);
    usuario = new Usuario(3, "Cameron", "PHILLIPS");
    usuarios.put(usuario.getId(), usuario);
    usuario = new Usuario(4, "Derek", "REESE");
    usuarios.put(usuario.getId(), usuario);
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @Override
  public Collection<Usuario> select() {
    return usuarios.values();
  }

  /**
   * DOCUMENT ME!
   *
   * @param id DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @Override
  public Usuario get(Long id) {
    return usuarios.get(id);
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @Override
  public Usuario add(Usuario usuario) {
    //		usuario.setId(idCounter.incrementAndGet());
    usuarios.put(usuario.getId(), usuario);

    return usuario;
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @Override
  public Usuario update(Usuario usuario) {
    usuarios.put(usuario.getId(), usuario);

    return usuario;
  }
}
