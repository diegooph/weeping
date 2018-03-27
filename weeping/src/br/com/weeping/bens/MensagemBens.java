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
@ManagedBean(name = "mensagenBean")


// a cada metodo que fomos utilizando , adicione um comentario encima do campo com o nome da paggina que foi utilizado
public class MensagemBens {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private UsuarioService usuarioDao = new UsuarioService();
	
	public String novoPost() {
		
		
		return "";
	}

	public String novaInstancia() {
		usuario = new Usuario();
		return "";
	}

	public String removerUsuario() {
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



	/*public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioUser = (Usuario) externalContext.getSessionMap().get("usuarioLogado");

		return usuarioUser.getPerfilUser().equals(acesso);
		
	}*/

}
