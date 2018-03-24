package br.com.weeping.service;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.weeping.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Usuario> getUsuarios() {
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
	    criteria.from(Usuario.class);
	    return  em.createQuery(criteria).getResultList();
//		Collection<Usuario> resultList = em.createQuery("SELECT a from Usuario a",Usuario.class).getResultList();
//		return resultList;
	}
	
	public Usuario consultar(String login, String senha) {
		
		 Usuario usuario = new Usuario();
			    CriteriaBuilder builder = em.getCriteriaBuilder();
			    CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
			    criteria.from(Usuario.class);
			    criteria.where(usuario.getSenha() == senha);
			    return  em.createQuery(criteria).getSingleResult();
			  
	}

	public void persist(Usuario usuario) {

		em.persist(usuario);
	}

	public void remove(int id) {

		em.remove(em.find(Usuario.class, id));
	}

}
