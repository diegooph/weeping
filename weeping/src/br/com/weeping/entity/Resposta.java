package br.com.weeping.entity;

import javax.persistence.Entity;
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
@SequenceGenerator(name = "res_seq", sequenceName = "res_seq", allocationSize = 1, initialValue = 1)
// @DiscriminatorValue(value="R")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_seq")
	private Integer idResposta;

	@ManyToOne()
	@JoinColumn(name = "id_mensagem_abordada", referencedColumnName = "idMensagem")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Mensagem id_mensagem_abordada;

	@OneToOne()
	@JoinColumn(name = "id_mensagem_resposta", referencedColumnName = "idMensagem")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Mensagem id_mensagem_resposta;

	public Integer getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Integer idResposta) {
		this.idResposta = idResposta;
	}

	public Mensagem getId_mensagem_abordada() {
		return id_mensagem_abordada;
	}

	public void setId_mensagem_abordada(Mensagem id_mensagem_abordada) {
		this.id_mensagem_abordada = id_mensagem_abordada;
	}

	public Mensagem getId_mensagem_resposta() {
		return id_mensagem_resposta;
	}

	public void setId_mensagem_resposta(Mensagem id_mensagem_resposta) {
		this.id_mensagem_resposta = id_mensagem_resposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idResposta == null) ? 0 : idResposta.hashCode());
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
		Resposta other = (Resposta) obj;
		if (idResposta == null) {
			if (other.idResposta != null)
				return false;
		} else if (!idResposta.equals(other.idResposta))
			return false;
		return true;
	}
}
