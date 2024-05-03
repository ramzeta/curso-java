package fr.eni.ep3jasp.business;

import fr.eni.ep3jasp.domain.Usuario;

import java.util.Collection;


/**
 * @author acogoluegnes
 *
 */
public interface UsuarioServicio {
  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  Collection<Usuario> select();

  /**
   * DOCUMENT ME!
   *
   * @param id DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  Usuario get(Long id);

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  Usuario add(Usuario usuario);

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  Usuario update(Usuario usuario);
}
