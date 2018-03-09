package br.com.exemploBanco.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemploBanco.entity.Post;
@Stateless
public class MensagenService {
@PersistenceContext
private EntityManager em;
public Collection<Post> getPostsUsuario() {
	return em.createQuery("SELECT a FROM Aluno a").getResultList();
}


}
