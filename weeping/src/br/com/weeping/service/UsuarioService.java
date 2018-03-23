package br.com.weeping.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.weeping.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Usuario> getUsuarios() {

		Collection<Usuario> resultList = em.createQuery("SELECT a from Usuario a",Usuario.class).getResultList();
		return resultList;
	}
	
	public Usuario consultar(String login, String senha) {
		
		Usuario usuario = new Usuario();
		
		return usuario;
	}

	public void persist(Usuario usuario) {

		em.persist(usuario);
	}

	public void remove(int id) {

		em.remove(em.find(Usuario.class, id));
	}

}
