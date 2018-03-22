package br.com.weeping;

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
	private UsuarioService daoGeneric = new DaoGeneric<Usuario>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private IDaoUsuario iDaoUsuario = new IDaoUsuarioImpl();

	public String salvar() {
		usuario = daoGeneric.merge(usuario);
		carregarUsuarios();
		return "";
	}
	
	public String novo(){
		usuario = new Usuario();
		return "";
	}
	
	public String remove(){
		daoGeneric.deletePorId(usuario);
		usuario = new Usuario();
		carregarUsuarios();
		return "";
	}
	
	@PostConstruct
	public void carregarUsuarios(){
		usuarios = daoGeneric.getListEntity(Usuario.class);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DaoGeneric<Usuario> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Usuario> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public String logar(){
		
		Usuario usuarioUser = iDaoUsuario.consultarUsuario(usuario.getLogin(), usuario.getSenha());
		
		if (usuarioUser != null){// achou o usuário
			
			//adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuarioUser);

			return "primeirapagina.jsf";
		}
		
		return "index.jsf";
	}
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario usuarioUser = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		
		return usuarioUser.getPerfilUser().equals(acesso);
	}

}
