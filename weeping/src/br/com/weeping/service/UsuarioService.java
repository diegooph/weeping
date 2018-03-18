package br.com.weeping.service;


import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.exemploBanco.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager em;

	 public Collection<Usuario> getAlunos() {
	 return em.createQuery("SELECT idusuario, datanacimento, nome FROM public.usuario;").getResultList();
	 }
	
	public void persist(Usuario usuario) {
		System.out.println(usuario.getNome()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		em.persist(usuario);
	}

	public void remove(int id) {

		em.remove(em.find(Usuario.class, id));
	}

}
