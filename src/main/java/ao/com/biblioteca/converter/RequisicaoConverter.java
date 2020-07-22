package ao.com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ao.com.biblioteca.dao.RequisicaoDAO;
import ao.com.biblioteca.modelo.Requisicao;
import ao.com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Requisicao.class)
public class RequisicaoConverter implements Converter {

	private RequisicaoDAO requisicaoDAO;
	
	public RequisicaoConverter() {
		this.requisicaoDAO = CDIServiceLocator.getBean(RequisicaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Requisicao retorno = null;

		if (value != null) {
			retorno = this.requisicaoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Requisicao) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}