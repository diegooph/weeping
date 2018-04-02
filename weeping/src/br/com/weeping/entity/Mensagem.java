package br.com.weeping.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="fl_tipo_mensagem", discriminatorType=DiscriminatorType.CHAR)
//@DiscriminatorValue(value="M")
@Entity
@SequenceGenerator(name = "mensagem_seq", sequenceName = "mensagem_seq", allocationSize = 1, initialValue = 1)
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_seq")
	private Integer idMensagem;

	@ManyToOne()
	@JoinColumn(name = "id_usuario_remetente", referencedColumnName = "idUsuario")
	private Usuario id_usuario_remetente;

	@OneToMany(mappedBy = "id_mensagem_abordada", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Resposta> resposta;

	private String mensagem;
	private Integer likes; // Sera criado uma tabela para conter os registros do
							// likes , e assim implementar casos de uso

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Usuario getId_usuario_remetente() {
		return id_usuario_remetente;
	}

	public void setId_usuario_remetente(Usuario id_usuario_remetente) {
		this.id_usuario_remetente = id_usuario_remetente;
	}

	public Collection<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(Collection<Resposta> resposta) {
		this.resposta = resposta;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Mensagem() {
		super();
		mensagem = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMensagem == null) ? 0 : idMensagem.hashCode());
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
		Mensagem other = (Mensagem) obj;
		if (idMensagem == null) {
			if (other.idMensagem != null)
				return false;
		} else if (!idMensagem.equals(other.idMensagem))
			return false;
		return true;
	}
}
