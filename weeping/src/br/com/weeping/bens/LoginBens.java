package br.com.weeping.bens;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.weeping.entity.Endereco;
import br.com.weeping.entity.Login;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.LoginService;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "loginbean")

// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class LoginBens {
	@Inject
	private LoginService daologin = new LoginService();
	private Login login = new Login();
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	@Inject
	private UsuarioService usuarioDao = new UsuarioService();
	private boolean aceitarTermos;

	public String SalvarLogin() {
		usuario.setEndereco(endereco);
		login.setUsuario(usuario);
		daologin.salvar(login);

		return "";
	}

	public String novaInstancia() {
		usuario = new Usuario();
		login = new Login();
		aceitarTermos = false;
		return "";
	}

	public String logar() {

		Login loginUser = daologin.consultar(login.getLogin(), login.getSenha());

		if (loginUser != null) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", loginUser);
			// adicionar a pagina de entrada do usuario
			return "";
		}

		return "index.jsf";
	}

	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Login loginUser = (Login) externalContext.getSessionMap().get("usuarioLogado");

		return loginUser.getPerfilUser().equals(acesso);

	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LoginService getDaologin() {
		return daologin;
	}

	public void setDaologin(LoginService daologin) {
		this.daologin = daologin;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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

	public boolean isAceitarTermos() {
		return aceitarTermos;
	}

	public void setAceitarTermos(boolean aceitarTermos) {
		this.aceitarTermos = aceitarTermos;
	}

}
