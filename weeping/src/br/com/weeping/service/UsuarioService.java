package br.com.weeping.service;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import br.com.weeping.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Usuario> getUsuarios() {

		Collection<Usuario> resultList = em.createQuery("SELECT a from Usuario a", Usuario.class).getResultList();
		return resultList;
	}

	public Usuario consultar(String login, String senha) {
		Query q = em.createQuery("SELECT a from Usuario a where a.login = :logindigitado and a.senha = :Senhadigitada",
				Usuario.class);
		q.setParameter("logindigitado", login);
		q.setParameter("Senhadigitada", senha);

		return (Usuario) q.getSingleResult();

	}

	public void salvar(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			persist(usuario);
		} else {
			update(usuario);
		}
	}

	private void persist(Usuario usuario) {

		em.persist(usuario);
	}

	private void update(Usuario usuario) {

		em.merge(usuario);
	}

	public void remove(int id) {

		em.remove(em.find(Usuario.class, id));
	}

}
