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

import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class AutorDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Autor buscarPeloCodigo(Long codigo){
		return manager.find(Autor.class, codigo);
	}
	
	public void salvar(Autor autor){
		manager.merge(autor);
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> buscarTodos(){
		return manager.createQuery("from Autor order by nome").getResultList();
	}
	@Transactional
	public void excluir(Autor autor) throws NegocioException{
		autor = buscarPeloCodigo(autor.getCodigo());
		try{
			manager.remove(autor);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Autor não pode ser excluido!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Autor> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Autor.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}