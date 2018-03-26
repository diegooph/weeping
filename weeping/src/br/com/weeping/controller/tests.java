package br.com.weeping.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.weeping.entity.Mensagem;
import br.com.weeping.entity.Post;
import br.com.weeping.entity.Resposta;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.MensagemService;
import br.com.weeping.service.PostService;
import br.com.weeping.service.RespostaService;
import br.com.weeping.service.UsuarioService;

/**
 * Servlet implementation class tests
 */
@WebServlet("/tests")

public class tests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Inject
	private UsuarioService servuser;
	@Inject
	MensagemService servmen;
	@Inject
	PostService servpos;
	@Inject
	RespostaService servres;

	public tests() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Mensagem meen2;
		Resposta res = new Resposta();
		Post post = new Post();
		Mensagem men = new Mensagem();
		Usuario usuario = new Usuario();

		usuario.setLogin("diego");
		usuario.setSenha("abc");
		servuser.salvar(usuario);

		men.setMensagem("asdasdasdasfasfdasfi");
		servmen.salvar(men);
		post.setMensagem(men);
		post.setUsuarioDestinatario(usuario);
		servpos.salvar(post);

		usuario = new Usuario();
		usuario.setLogin("carlos");
		servuser.salvar(usuario);
		men.setMensagem("asdasdasdasfasfdasfi");
		servmen.salvar(men);
		 meen2 = men;
		post.setMensagem(men);
		post.setUsuarioDestinatario(usuario);
		servpos.salvar(post);

		usuario = new Usuario();
		usuario.setLogin("eueumesmo");
		servuser.salvar(usuario);
		men.setMensagem("asdasdasdasfasfdasfi");
		servmen.salvar(men);
res.setId_mensagem_abordada(men);

res.setId_mensagem_resposta(meen2);
servres.salvar(res);
		response.getWriter().append(usuario.getNome()).append(request.getContextPath());

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
