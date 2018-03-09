package br.com.exemploBanco.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resposta extends Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Integer idResposta;
	private Mensagem mensagemAbortada;

	public Integer getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Integer idResposta) {
		this.idResposta = idResposta;
	}

	public Mensagem getMensagemVinculada() {
		return mensagemAbortada;
	}

	public void setMensagemVinculada(Mensagem mensagemVinculada) {
		this.mensagemAbortada = mensagemVinculada;
	}





}
