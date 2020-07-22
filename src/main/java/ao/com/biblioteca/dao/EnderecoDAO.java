package ao.com.biblioteca.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class EnderecoDAO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	//inserir Endereço
	public void salvar(Endereco end) {
		em.merge(end);
	}
	
	//buscar todas os endereços
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() {
		return em.createQuery("from Endereco order by nome").getResultList();
	}
	
	//Excluir endereço
	@Transactional
	public void excluir(Endereco end) throws NegocioException {
		end = buscarPeloCodigo(end.getCodigo());
		try {
			em.remove(end);
			em.flush();
		} catch (Exception e) {
			throw new NegocioException("Endereço não pode ser excluído.");
		}
		
	}
	//editar endereco
	public Endereco buscarPeloCodigo(Long codigo) {
		return em.find(Endereco.class, codigo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Endereco> filtrados(String nome) {

		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Endereco.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}
