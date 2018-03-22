package br.com.weeping.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.weeping.entity.Post;

@Stateless
public class PostService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Post> getPosts() {

		Collection<Post> resultList = em.createQuery("SELECT p from post p", Post.class).getResultList();
		return resultList;
	}

	public void persist(Post post) {
	
		em.persist(post);
	}

	public void remove(int id) {

		em.remove(em.find(Post.class, id));
	}

}
