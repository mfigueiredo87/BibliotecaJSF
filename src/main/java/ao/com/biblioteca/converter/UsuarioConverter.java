package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.UsuariosDAO;
import ao.com.biblioteca.modelo.Usuario;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private UsuariosDAO usuarios;

	public UsuarioConverter() {
		this.usuarios = CDIServiceLocator.getBean(UsuariosDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String str) {
		Usuario retorno = null;
		if (str != null) {
			Long id = new Long(str);
			retorno = this.usuarios.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			return ((Usuario) obj).getId() == null ? "" : ((Usuario) obj).getId().toString();
		}
		return "";
	}

}
