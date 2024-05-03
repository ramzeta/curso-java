package fr.eni.dao;

import fr.eni.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
  /**
   * DOCUMENT ME!
   *
   * @param nombre DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  List<Usuario> findByNombreLike(String nombre);
}
