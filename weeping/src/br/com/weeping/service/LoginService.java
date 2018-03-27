package br.com.weeping.service;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import br.com.weeping.entity.Login;
import br.com.weeping.entity.Usuario;

@Stateless
public class LoginService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Login> getUsuarios() {

		Collection<Login> resultList = em.createQuery("SELECT a from Login a", Login.class).getResultList();
		return resultList;
	}

	public Login consultar(String login, String senha) {
		Query q = em.createQuery("SELECT a from Login a where a.login = :logindigitado and a.senha = :Senhadigitada",
				Login.class);
		q.setParameter("logindigitado", login);
		q.setParameter("Senhadigitada", senha);

		return (Login) q.getSingleResult();

	}

	public void salvar(Login login) {
		if (login.getIdLogin() == null) {
			persist(login);
		} else {
			update(login);
		}
	}

	private void persist(Login login) {

		em.persist(login);
	}

	private void update(Login login) {

		em.merge(login);
	}

	public void remove(int id) {
// apaga tudo referente ao usuario e login
		em.remove(em.find(Login.class, id));
	}

}
