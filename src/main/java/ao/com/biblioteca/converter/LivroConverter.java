package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.LivroDAO;
import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Livro.class)
public class LivroConverter implements Converter {

	private LivroDAO livroDAO;
	
	public LivroConverter() {
		this.livroDAO = CDIServiceLocator.getBean(LivroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Livro retorno = null;

		if (value != null) {
			retorno = this.livroDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Livro) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}