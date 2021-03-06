package br.com.weeping.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@SequenceGenerator(name = "Post_seq", sequenceName = "Post_seq", allocationSize = 1, initialValue = 1)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_seq")
	private Integer idPost;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "id_usuario_destinatario", referencedColumnName = "idUsuario")
	private Usuario id_usuario_destinatario;

	@JoinColumn(name = "id_mensagem_post", referencedColumnName = "idMensagem")
	@OneToOne(fetch= FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE )
	private Mensagem mensagem;

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	public Usuario getId_usuario_destinatario() {
		return id_usuario_destinatario;
	}

	public void setId_usuario_destinatario(Usuario id_usuario_destinatario) {
		this.id_usuario_destinatario = id_usuario_destinatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPost == null) ? 0 : idPost.hashCode());
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
		Post other = (Post) obj;
		if (idPost == null) {
			if (other.idPost != null)
				return false;
		} else if (!idPost.equals(other.idPost))
			return false;
		return true;
	}
}
