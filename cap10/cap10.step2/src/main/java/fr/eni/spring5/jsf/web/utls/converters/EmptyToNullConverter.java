package fr.eni.spring5.jsf.web.utls.converters;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@FacesConverter(forClass = String.class)
public class EmptyToNullConverter implements Converter {
  /**
   * DOCUMENT ME!
   *
   * @param facesContext DOCUMENT ME!
   * @param component DOCUMENT ME!
   * @param value DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Object getAsObject(FacesContext facesContext, UIComponent component,
    String value) {
    if ((value == null) || (value.trim().length() == 0)) {
      if (component instanceof EditableValueHolder) {
        ((EditableValueHolder) component).setSubmittedValue(null);
      }

      return null;
    }

    return value;
  }

  /**
   * DOCUMENT ME!
   *
   * @param facesContext DOCUMENT ME!
   * @param component DOCUMENT ME!
   * @param value DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getAsString(FacesContext facesContext, UIComponent component,
    Object value) {
    return (value == null) ? null : value.toString();
  }
}
