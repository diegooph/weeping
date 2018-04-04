package br.com.weeping.bens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.weeping.entity.Usuario;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "usuariobean")

// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Inject
	private UsuarioService usuarioDao = new UsuarioService();
	

	public String salvarNovoUsuario() throws IOException {

	

		usuarioDao.salvar(usuario);
		// salva e faz update na tabela usuario nao afetando login.

		return "../";
	}

	public String novaInstancia() {
		usuario = new Usuario();
		return "";
	}

	public String removerUsuario() {
		usuarioDao.remove(usuario.getIdUsuario());
		usuario = new Usuario();
		// carregarUsuarios();
		return "";
	}




	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioService usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}
