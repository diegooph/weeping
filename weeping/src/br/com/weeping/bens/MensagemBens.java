package br.com.weeping.bens;

import java.util.Collection;
import javax.annotation.PostConstruct;
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
// @ApplicationScoped
//
// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class MensagemBens {
	private Login login;
	private Usuario usuario;
	private Post post;
	private Mensagem mensagemprincipal;
	private Resposta resposta;
	private Mensagem mensagemAbordada;
	private Post postSelecionado;

	@Inject
	private PostService postDao;

	@Inject
	private UsuarioService usuarioDao = new UsuarioService();

	@Inject
	private MensagemService mensagemDao = new MensagemService();

	@Inject
	private RespostaService resDao = new RespostaService();

	private Collection<Post> posts;

	@PostConstruct
	public void novaInstancia() {
		login = getUsuarioLogado();
		usuario = login.getUsuario();
		post = new Post();
		resposta = new Resposta();
		mensagemAbordada = new Mensagem();
		mensagemprincipal = new Mensagem();
		posts = postDao.getPosts();
	}

	public Login getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		return (Login) externalContext.getSessionMap().get("usuarioLogado");

	}

	public String novoPost() {

		login = getUsuarioLogado();
		post.setId_usuario_destinatario(getUsuarioLogado().getUsuario());
		mensagemprincipal.setId_usuario_remetente(getUsuarioLogado().getUsuario());
		mensagemDao.salvar(mensagemprincipal);
		post.setMensagem(mensagemprincipal);
		postDao.salvar(post);
		mensagemprincipal = new Mensagem();
		post = new Post();
		posts = postDao.getPosts();
		return "";
	}

	public String novaResposta() {

		mensagemDao.salvar(mensagemprincipal);
		resposta.setId_mensagem_abordada(mensagemAbordada);
		resposta.setId_mensagem_resposta(mensagemprincipal);
		resDao.salvar(resposta);
		return "";
	}

	public String apagarMensagem()  {
		if (validarPermicaoUsuarioMensagem()) {
			mensagemDao.remove(	postSelecionado.getMensagem().getIdMensagem());
		}
		return "";
	}

	public boolean validarPermicaoUsuarioMensagem() {

		if (postSelecionado.getId_usuario_destinatario() == login.getUsuario()
				|| (postSelecionado.getMensagem().getId_usuario_remetente() == login.getUsuario())) {
			return true;
		}
		return false;

	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Post getPostSelecionado() {
		return postSelecionado;
	}

	public void setPostSelecionado(Post postSelecionado) {
		this.postSelecionado = postSelecionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Mensagem getMensagemprincipal() {
		return mensagemprincipal;
	}

	public void setMensagemprincipal(Mensagem mensagemprincipal) {
		this.mensagemprincipal = mensagemprincipal;
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

	public PostService getPostDao() {
		return postDao;
	}

	public void setPostDao(PostService postDao) {
		this.postDao = postDao;
	}

	public UsuarioService getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioService usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public MensagemService getMensagemDao() {
		return mensagemDao;
	}

	public void setMensagemDao(MensagemService mensagemDao) {
		this.mensagemDao = mensagemDao;
	}

	public RespostaService getResDao() {
		return resDao;
	}

	public void setResDao(RespostaService resDao) {
		this.resDao = resDao;
	}

	public Collection<Post> getPosts() {
		return posts;
	}

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

}
