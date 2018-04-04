package br.com.weeping.entity;

import java.io.ByteArrayInputStream;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, initialValue = 1)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Integer idUsuario;
	private String nome;
	private String sobrenome;
	private String email;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Endereco endereco;

	@OneToMany(mappedBy = "id_usuario_remetente", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Mensagem> mensagens;

	@OneToMany(mappedBy = "id_usuario_destinatario", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Post> posts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "listaAmigo")
	private Collection<Usuario> listaAmigos;

	/* Representa a imagem em miniatura em base64 */
	@Column(length = 50000, columnDefinition = "text")
	private String fotoIconBase64;

	/* Salva a imagem em tamanho original */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "fotoIconBase64Original")
	private byte[] fotoIconBase64Original;
	private String extensao;

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Usuario() {
		super();
	}

	public Usuario(String nome) {
		super();
		this.nome = nome;

	}

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

	public Collection<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(Collection<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Collection<Post> getPosts() {
		return posts;
	}

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

	public Collection<Usuario> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(Collection<Usuario> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
