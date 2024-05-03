package fr.eni.spring5.jsf.web.controllers;

import fr.eni.spring5.jsf.services.TareaDao;
import fr.eni.spring5.jsf.web.model.Tarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Component("tareaBackingBean")
@Scope("request")
public class TareaBackingBean {
  /**
   * DOCUMENT ME!
   */
  private static final Logger logger = LoggerFactory.getLogger(TareaBackingBean.class);

  /**
   * DOCUMENT ME!
   */
  private Tarea tarea = new Tarea();

  /**
   * DOCUMENT ME!
   */
  private List<Tarea> tareas;

  /**
   * DOCUMENT ME!
   */
  @Autowired
  private TareaDao TareaDao;

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getMessage() {
    logger.debug("Returning message from tarea home bean");

    return "Hello from Spring";
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Tarea getTarea() {
    return tarea;
  }

  /**
   * DOCUMENT ME!
   */
  public void saveTarea() {
    TareaDao.save(tarea);
    tarea = new Tarea();
    invalidateTareas();
  }

  /**
   * DOCUMENT ME!
   */
  private void invalidateTareas() {
    tareas = null;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public List<Tarea> getTareas() {
    if (tareas == null) {
      tareas = TareaDao.list();
    }

    return tareas;
  }
}
