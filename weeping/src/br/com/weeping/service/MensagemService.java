package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.weeping.entity.Mensagem;

@Stateless
public class MensagemService {

	@PersistenceContext
	private EntityManager emm;

	public Collection<Mensagem> getMensagems() {

		Collection<Mensagem> resultList = emm.createQuery("SELECT m from mensagem m", Mensagem.class).getResultList();
		return resultList;
	}

	public void persist(Mensagem mensagem) {
		
		emm.persist(mensagem);
	}

	public void remove(int id) {

		emm.remove(emm.find(Mensagem.class, id));
	}

}
