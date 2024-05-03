package fr.eni.spring5.jsf.services;

import fr.eni.spring5.jsf.web.model.Tarea;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * El DAO para Tarea
 *
 * @author $author$
 * @version $Revision$
  */
@Component
public class TareaDao {
  /**
   * La entity manager
   */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * copia de seguridad
   *
   * @param tarea la tarea
   */
  @Transactional
  public void save(Tarea tarea) {
    entityManager.persist(tarea);
  }

  /**
   * Listar las tareas
   *
   * @return La lista de las tareas
   */
  @SuppressWarnings("unchecked")
  public List<Tarea> list() {
    return entityManager.createQuery("select t from Tarea t").getResultList();
  }
}
