package br.com.weeping.bens;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.weeping.entity.Login;
import br.com.weeping.entity.Mensagem;
import br.com.weeping.entity.Post;
import br.com.weeping.entity.Resposta;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.MensagemService;
import br.com.weeping.service.PostService;
import br.com.weeping.service.RespostaService;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "mensagenbean")

// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class MensagemBens {
	private Login login;
	// usuario dono da pagina null = principal ... ou seja vai pro proprio
	// usuario
	private Usuario usuario;
	private Post post;
	private Mensagem mensagem;
	private Resposta resposta;
	private Mensagem mensagemAbordada;

	private List<Post> posts = new ArrayList<Post>();

	@Inject
	private UsuarioService usuarioDao = new UsuarioService();
	@Inject
	private MensagemService mensagemDao = new MensagemService();
	@Inject
	private PostService postDao = new PostService();
	@Inject
	private RespostaService resDao = new RespostaService();

	public MensagemBens() {
		super();
		this.post = new Post();
		this.mensagem = new Mensagem();
		this.mensagemAbordada = new Mensagem();
		this.resposta = new Resposta();
		this.posts = (List<Post>) postDao.getPosts(); 
	}

	public void getUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		login = (Login) externalContext.getSessionMap().get("usuarioLogado");

	}

	public String novoPost() {
		if (usuario == null) {
			post.setId_usuario_destinatario(login.getUsuario());
		} else {
			post.setId_usuario_destinatario(usuario);
		}
		mensagem.setId_usuario_remetente(login.getUsuario());
		mensagemDao.salvar(mensagem);
		post.setMensagem(mensagem);
		postDao.salvar(post);
		return "";
	}

	public String novaResposta() {

		mensagemDao.salvar(mensagem);
		resposta.setId_mensagem_abordada(mensagemAbordada);
		resposta.setId_mensagem_resposta(mensagem);
		resDao.salvar(resposta);
		return "";
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Mensagem getMensagemAbordada() {
		return mensagemAbordada;
	}

	public void setMensagemAbordada(Mensagem mensagemAbordada) {
		this.mensagemAbordada = mensagemAbordada;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
