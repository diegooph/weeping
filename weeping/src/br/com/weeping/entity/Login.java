package br.com.weeping.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@SequenceGenerator(name = "login_seq", sequenceName = "login_seq", allocationSize = 1, initialValue = 1)
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
	private Integer idLogin;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST.REFRESH )
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;
	private String login;
	private String senha;
	
	private String perfilUser;

	public Login() {
		super();
		
	}

	public Login(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public void setPerfilUser(String perfilUser) {
		this.perfilUser = perfilUser;
	}
	
	public String getPerfilUser() {
		return perfilUser;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
