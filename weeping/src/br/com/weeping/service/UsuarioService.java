package br.com.weeping.service;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import br.com.weeping.entity.Login;
import br.com.weeping.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager em;

	public Collection<Usuario> getUsuarios() {

		Collection<Usuario> resultList = em.createQuery("SELECT a from Usuario a", Usuario.class).getResultList();
		return resultList;
	}

	public void adicionarOuRemoverAmigo(Usuario usuario , Login login){
		Query q = em.createQuery("SELECT a FROM listaamigo a where a.usuario_idusuario = :usuario and a.listaamigos_idusuario = :login") ; 
		q.setParameter("login", login.getUsuario().getIdUsuario());
		q.setParameter("usuario", usuario.getIdUsuario());
		
		if (q.getSingleResult()==null) {
			usuario.getListaAmigos().add(login.getUsuario());			
			em.merge(usuario);
		} else {
			usuario.getListaAmigos().remove(login.getUsuario());			
			em.merge(usuario);
		}
		
	}

	public void salvar(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			persist(usuario);
		} else {
			update(usuario);
		}
	}

	private void persist(Usuario usuario) {

		em.persist(usuario);
	}

	private void update(Usuario usuario) {

		em.merge(usuario);
	}

	public void remove(int id) {
		// apaga tudo referente ao usuario nao usar pois nao apagara o login
		// em.remove(em.find(Usuario.class, id));
	}

}
