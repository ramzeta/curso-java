package fr.eni.ep3jasp.web;

import fr.eni.ep3jasp.business.UsuarioServicio;

import fr.eni.ep3jasp.domain.Usuario;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Controller
@RequestMapping("/usuarios")
public class UsuarioMvcController {
  /**
   * DOCUMENT ME!
   */
  @Autowired
  UsuarioServicio usuarioServicio;

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET)
  public Collection<Usuario> select() {
    return usuarioServicio.select();
  }

  /**
   * DOCUMENT ME!
   *
   * @param id DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @ResponseBody
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Usuario get(@PathVariable
  Long id) {
    return usuarioServicio.get(id);
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   * @param response DOCUMENT ME!
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody
  Usuario usuario, HttpServletResponse response) {
    usuarioServicio.add(usuario);

    String location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                 .pathSegment("{id}")
                                                 .buildAndExpand(usuario.getId())
                                                 .toUriString();

    response.setHeader("Location", location);
  }

  /**
   * DOCUMENT ME!
   *
   * @param id DOCUMENT ME!
   * @param usuario DOCUMENT ME!
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable
  Long id, @RequestBody
  Usuario usuario) {
    usuario.setId(id);
    usuarioServicio.update(usuario);
  }
}
