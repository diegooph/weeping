package br.com.exemploBanco.entity;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;



@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="fl_tipo_mensagem", discriminatorType=DiscriminatorType.CHAR)
//@DiscriminatorValue(value="M")
public class Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idMensagem;
	
	
	@ManyToOne
	@JoinColumn(name="id_usuario_remetente" )
	private Usuario usuarioRemetente;
	
	@OneToMany(mappedBy= "idResposta" ,  fetch= FetchType.LAZY)
	private Collection<Resposta> resposta;
	
	
	private String mensagem;
	private Integer likes; // Sera criado uma tabela para conter os registros do likes , e assim implementar casos de uso 

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Usuario getUsuarioRemetente() {
		return usuarioRemetente;
	}

	public void setUsuarioRemetente(Usuario usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
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

}
