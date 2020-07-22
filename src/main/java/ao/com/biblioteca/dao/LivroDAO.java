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

import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class LivroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Livro buscarPeloCodigo(Long codigo){
		return manager.find(Livro.class, codigo);
	}
	
	public void salvar(Livro livro){
		manager.merge(livro);
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> buscarTodos(){
		return manager.createQuery("from Livro order by titulo").getResultList();
	}
	@Transactional
	public void excluir(Livro livro) throws NegocioException{
		livro = buscarPeloCodigo(livro.getCodigo());
		try{
			manager.remove(livro);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("O Livro não pode ser excluido!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Livro> filtrados(String titulo) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Livro.class);

		if (StringUtils.isNotBlank(titulo)) {
			criteria.add(Restrictions.ilike("titulo", titulo, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("titulo")).list();
	}

}