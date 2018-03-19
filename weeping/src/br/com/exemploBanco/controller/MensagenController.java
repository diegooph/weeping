package br.com.exemploBanco.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.handlers.message_rfc822;

import br.com.exemploBanco.entity.Mensagem;
import br.com.exemploBanco.entity.Post;
import br.com.exemploBanco.entity.Resposta;
import br.com.exemploBanco.entity.Usuario;
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
	private PostService servPost;
	private RespostaService servres;
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

		Usuario usuario = new Usuario();
		usuario.setDataNacimento(null);
		usuario.setNome("diego");
		servuser.persist(usuario);
		
		Collection<Usuario> lista = servuser.getUsuarios();
		
		Mensagem mens = new Mensagem();

		mens.setLikes(6);
		mens.setMensagem("Ola mundo");
		mens.setUsuarioRemetente(usuario);
		System.out.println(usuario.getIdUsuario());
		servMens = new MensagemService();
		servMens.persist(mens);
		
		Post post = new Post();
		
		post.setMensagem(mens);
		post.setUsuarioDestinatario(usuario);
		
		servPost.persist(post);
		
		for (Usuario usuario2 : lista) {
			response.getWriter().append("pppp" + usuario2.getNome()).append(request.getContextPath());
		
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
