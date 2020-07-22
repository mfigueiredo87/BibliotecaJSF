package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Endereco.class, value="enderecoConverter")
public class EnderecoConverter implements Converter {

	private EnderecoDAO enderecoDAO;
	
	public EnderecoConverter() {
		this.enderecoDAO = CDIServiceLocator.getBean(EnderecoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;

		if (value != null) {
			retorno = this.enderecoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Endereco) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}
}