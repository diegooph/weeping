package br.com.weeping.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, initialValue = 1)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")

	private Integer idUsuario;

	private String nome;
	@Length(max = 10, min = 0)
	private String login;
	private String senha;

	private Date dataNacimento;

	@OneToMany(mappedBy = "id_usuario_remetente", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Mensagem> mensagens;

	@OneToMany(mappedBy = "id_usuario_destinatario", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Post> posts;

	public Usuario() {
		super();
	}

	public Usuario(String nome, Date dataNacimento) {
		super();
		this.nome = nome;
		this.dataNacimento = dataNacimento;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}
