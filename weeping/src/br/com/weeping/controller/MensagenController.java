package br.com.weeping.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.handlers.message_rfc822;

import br.com.weeping.entity.Mensagem;
import br.com.weeping.entity.Post;
import br.com.weeping.entity.Resposta;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.MensagemService;
import br.com.weeping.service.PostService;
import br.com.weeping.service.RespostaService;
import br.com.weeping.service.UsuarioService;

/**
 * Servlet implementation class MensagenController
 */
@WebServlet("/")
public class MensagenController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService servuser;

	@Inject
	private PostService servPost;

	@Inject
	private RespostaService servres;

	@Inject
	private MensagemService servMens;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MensagenController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// cria um usuarioo e salva no banco de dados
		Usuario usuario = new Usuario();
		usuario.setDataNacimento(null);
		usuario.setNome("diego");
		servuser.persist(usuario);

		//pesquisa todos os usuarios
		Collection<Usuario> lista = servuser.getUsuarios();

		//cria uma nova mensagem
		Mensagem mens = new Mensagem();
		mens.setLikes(6);
		mens.setMensagem("Ola mundo");
		mens.setUsuarioRemetente(usuario);
	//salva essa mensagem ja com o relacionamento do usuario
		servMens.persist(mens);

		//cria um post e salva com as 2 chaves estrangeiras
		Post post = new Post();
		post.setMensagem(mens);
		post.setUsuarioDestinatario(usuario);
		servPost.persist(post);
		
		//cria uma resposta ao post
		
		Resposta res = new Resposta();
		Mensagem mensagemresposta = new Mensagem();
		mensagemresposta.setMensagem("ola mundo o k7");
		mensagemresposta.setUsuarioRemetente(usuario);
		servMens.persist(mensagemresposta);
		res.setId_mensagem_abordada(mens);
		res.setId_mensagem_resposta(mensagemresposta);
		servres.persist(res);

		//lista os usuarios 
		for (Usuario usuario2 : lista) {
			response.getWriter().append("   " + usuario2.getNome()).append(request.getContextPath());

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
