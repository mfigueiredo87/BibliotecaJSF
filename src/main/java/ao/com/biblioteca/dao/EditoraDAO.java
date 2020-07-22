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

import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jpa.Transactional;

public class EditoraDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Editora buscarPeloCodigo(Long codigo){
		return manager.find(Editora.class, codigo);
	}
	
	public void salvar(Editora editora){
		manager.merge(editora);
	}
	
	@SuppressWarnings("unchecked")
	public List<Editora> buscarTodos(){
		return manager.createQuery("from Editora order by nome").getResultList();
	}
	@Transactional
	public void excluir(Editora editora) throws NegocioException{
		editora = buscarPeloCodigo(editora.getCodigo());
		try{
			manager.remove(editora);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Editora não pode ser excluida!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Editora> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Editora.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}