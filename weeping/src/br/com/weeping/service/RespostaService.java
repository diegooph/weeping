package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.weeping.entity.Post;
import br.com.weeping.entity.Resposta;

@Stateless
public class RespostaService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Resposta> getAlunos() {

		Collection<Resposta> resultList = em.createQuery("SELECT r from resposta r", Resposta.class).getResultList();
		return resultList;
	}

	public void salvar(Resposta resposta) {
		if (resposta.getIdResposta() == null) {
			persist(resposta);
		} else {
			update(resposta);
		}
	}

	private void persist(Resposta resposta) {

		em.persist(resposta);
	}

	private void update(Resposta resposta) {

		em.merge(resposta);
	}

	public void remove(int id) {

		em.remove(em.find(Resposta.class, id));
	}

}
