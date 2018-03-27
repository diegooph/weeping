package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.sql.Update;

import br.com.weeping.entity.Mensagem;

@Stateless
public class MensagemService {

	@PersistenceContext
	private EntityManager emm;

	public Collection<Mensagem> getMensagems() {

		Collection<Mensagem> resultList = emm.createQuery("SELECT m from mensagem m", Mensagem.class).getResultList();
		return resultList;
	}

	public void salvar(Mensagem mensagem) {
		if (mensagem.getIdMensagem() == null) {
			persist(mensagem);
		} else {
			update(mensagem);
		}
	}

	private void persist(Mensagem mensagem) {

		emm.persist(mensagem);
	}

	private void update(Mensagem mensagem) {

		emm.merge(mensagem);
	}

	public void remove(int id) {
		// ja esta programada para deletaor o post ou a resposta vinculada a ela e todos os seus filhos
		emm.remove(emm.find(Mensagem.class, id));
	}

}
