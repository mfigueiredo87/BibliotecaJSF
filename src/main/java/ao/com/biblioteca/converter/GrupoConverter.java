package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.GrupoDAO;
import ao.com.biblioteca.modelo.Grupo;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	private GrupoDAO grupos;

	public GrupoConverter() {
		this.grupos = CDIServiceLocator.getBean(GrupoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String str) {
		Grupo retorno = null;
		if (str != null) {
			Long id = new Long(str);
			retorno = this.grupos.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			return ((Grupo) obj).getId() == null ? "" : ((Grupo) obj).getId().toString();
		}
		return "";
	}

}
