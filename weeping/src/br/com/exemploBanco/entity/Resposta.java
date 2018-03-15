package br.com.exemploBanco.entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="R")
public class Resposta extends Mensagem {
	@JoinColumn(name="id_mensagem_abordada"  )
	@ManyToOne
	private Mensagem mensagemAbordada;


	public Mensagem getMensagemVinculada() {
		return mensagemAbordada;
	}

	public void setMensagemVinculada(Mensagem mensagemVinculada) {
		this.mensagemAbordada = mensagemVinculada;
	}





}
