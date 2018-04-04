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

		Collection<Post> resultList = em.createQuery("SELECT p from Post p ORDER BY p.idPost DESC" , Post.class).getResultList();
		return resultList;
	}

	public void salvar(Post post) {
		if (post.getIdPost() == null) {
			persist(post);
		} else {
			update(post);
		}
	}

	private void persist(Post post) {

		em.persist(post);
	}

	private void update(Post post) {

		em.merge(post);
	}

	public void remove(int id) {

		// nao usar esse metodo apagar diretamente a mensagem
		//em.remove(em.find(Post.class, id));
	}

}
