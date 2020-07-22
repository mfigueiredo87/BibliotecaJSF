package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.EditoraDAO;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Editora.class)
public class EditoraConverter implements Converter {

	private EditoraDAO editoraDAO;
	
	public EditoraConverter() {
		this.editoraDAO = CDIServiceLocator.getBean(EditoraDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Editora retorno = null;

		if (value != null) {
			retorno = this.editoraDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Editora) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}