package br.com.weeping.bens;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.weeping.entity.Usuario;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private UsuarioService usuarioDao = new UsuarioService();

	public String salvar() {
		usuarioDao.persist(usuario);
		carregarUsuarios();
		return "";
	}

	public String novo() {
		usuario = new Usuario();
		return "";
	}

	public String remove() {
		usuarioDao.remove(usuario.getIdUsuario());
		usuario = new Usuario();
		carregarUsuarios();
		return "";
	}

	@PostConstruct
	public void carregarUsuarios() {
		usuarioDao.getUsuarios();
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

	public String logar() {

		Usuario usuarioUser = usuarioDao.consultar(usuario.getLogin(), usuario.getSenha());

		if (usuarioUser != null) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuarioUser);

			return "principal.xhtml";
		}

		return "";
	}

	/*public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioUser = (Usuario) externalContext.getSessionMap().get("usuarioLogado");

		return usuarioUser.getPerfilUser().equals(acesso);
		
	}*/

}
