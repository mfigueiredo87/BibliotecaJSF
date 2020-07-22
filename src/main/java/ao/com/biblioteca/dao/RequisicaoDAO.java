package ao.com.biblioteca.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ao.com.biblioteca.modelo.Requisicao;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class RequisicaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Requisicao buscarPeloCodigo(Long codigo){
		return manager.find(Requisicao.class, codigo);
	}
	
	public void salvar(Requisicao requisicao){
		manager.merge(requisicao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> buscarTodos(){
		return manager.createQuery("from Requisicao order by livro").getResultList();
	}
	
	//metodo para buscar os requisitados
	@SuppressWarnings("unchecked")
	public List<Requisicao> buscarRequisitados(){
		return manager.createNativeQuery("SELECT * FROM Requisicao WHERE estado = 0", Requisicao.class).getResultList();
	}
	
	//metodo para buscar os requisitados
		@SuppressWarnings("unchecked")
		public List<Requisicao> buscarDevolvidos(){
			return manager.createNativeQuery("SELECT * FROM Requisicao WHERE estado = 1", Requisicao.class).getResultList();
		}
	
	@Transactional
	public void excluir(Requisicao requisicao) throws NegocioException{
		requisicao = buscarPeloCodigo(requisicao.getCodigo());
		try{
			manager.remove(requisicao);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("A Requisição não pode ser excluido!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Requisicao> filtrados(String estado) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Requisicao.class);

		if (StringUtils.isNotBlank(estado)) {
			criteria.add(Restrictions.ilike("estado", estado, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("estado")).list();
	}

}