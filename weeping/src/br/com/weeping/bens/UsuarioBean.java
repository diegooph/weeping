package br.com.weeping.bens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.weeping.entity.Login;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "usuariobean")

// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class UsuarioBean {
	private Login login;
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioDao = new UsuarioService();

	public String acessarUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object usuarioExterno = null;
		externalContext.getSessionMap().put("usuarioExterno", usuarioExterno);
		return "";
	}

	public String adicionarAmizade() {
		usuarioDao.adicionarOuRemoverAmigo(usuario, login);

		return "";
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

	public Login getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		return (Login) externalContext.getSessionMap().get("usuarioLogado");

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public UsuarioBean() {
		super();
		this.login = getUsuarioLogado();

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

}
