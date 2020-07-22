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

import ao.com.biblioteca.modelo.Cliente;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cliente buscarPeloCodigo(Long codigo){
		return manager.find(Cliente.class, codigo);
	}
	
	public void salvar(Cliente cliente){
		manager.merge(cliente);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos(){
		return manager.createQuery("from Cliente order by nome").getResultList();
	}
	@Transactional
	public void excluir(Cliente cliente) throws NegocioException{
		cliente = buscarPeloCodigo(cliente.getCodigo());
		try{
			manager.remove(cliente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Cliente não pode ser excluido!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}