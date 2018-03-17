package br.com.exemploBanco.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
// @DiscriminatorValue(value="P")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPost;

	@JoinColumn(name = "id_usuario_destinatario", referencedColumnName = "idUsuario")
	@ManyToOne
	private Usuario usuarioDestinatario;

	@JoinColumn(name = "id_mensagem_post", referencedColumnName = "idMensagem")
	@ManyToOne
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

	public Usuario getUsuarioDestinatario() {
		return usuarioDestinatario;
	}

	public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
	}

}
