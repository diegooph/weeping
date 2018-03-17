package br.com.exemploBanco.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
// @DiscriminatorValue(value="R")
public class Resposta  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResposta;
	
	
	@JoinColumn(name = "id_mensagem_abordada")
	@ManyToOne
	private Mensagem mensagemAbordada;
	
	@JoinColumn(name = "id_mensagem_resposta", referencedColumnName = "idMensagem")
	@ManyToOne
	private Mensagem mensagem;

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Integer idResposta) {
		this.idResposta = idResposta;
	}

	public Mensagem getMensagemAbordada() {
		return mensagemAbordada;
	}

	public void setMensagemAbordada(Mensagem mensagemAbordada) {
		this.mensagemAbordada = mensagemAbordada;
	}

}
