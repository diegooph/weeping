package br.com.weeping.service;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import br.com.weeping.entity.Endereco;
import br.com.weeping.entity.Usuario;

@Stateless
public class EnderecoService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Endereco> getUsuarios() {

		Collection<Endereco> resultList = em.createQuery("SELECT a from Endereco a", Endereco.class).getResultList();
		return resultList;
	}

	public void salvar(Endereco endereco) {
		if (endereco.getIdEndereco() == null) {
			persist(endereco);
		} else {
			update(endereco);
		}
	}

	private void persist(Endereco endereco) {

		em.persist(endereco);
	}

	private void update(Endereco endereco) {

		em.merge(endereco);
	}

	public void remove(int id) {
		// apaga somente o endereço usar com sabedoria se necessario tudo
		// referente ao usuario
		// em.remove(em.find(Endereco.class, id));
	}

}
