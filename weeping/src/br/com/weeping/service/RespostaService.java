package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemploBanco.entity.Resposta;

@Stateless
public class RespostaService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Resposta> getAlunos() {

		Collection<Resposta> resultList = em.createQuery("SELECT r from resposta r",Resposta.class).getResultList();
		return resultList;
	}

	public void persist(Resposta resposta) {

		em.persist(resposta);
	}

	public void remove(int id) {

		em.remove(em.find(Resposta.class, id));
	}

}
