package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.AutorDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Autor.class)
public class AutorConverter implements Converter {

	private AutorDAO autorDAO;
	
	public AutorConverter() {
		this.autorDAO = CDIServiceLocator.getBean(AutorDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Autor retorno = null;

		if (value != null) {
			retorno = this.autorDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Autor) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}