package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemploBanco.entity.Mensagem;

@Stateless
public class MensagemService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Mensagem> getMensagems() {

		Collection<Mensagem> resultList = em.createQuery("SELECT m from mensagem m", Mensagem.class).getResultList();
		return resultList;
	}

	public void persist(Mensagem mensagem) {
		
		em.persist(mensagem);
	}

	public void remove(int id) {

		em.remove(em.find(Mensagem.class, id));
	}

}
