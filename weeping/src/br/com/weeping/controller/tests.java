package br.com.weeping.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.weeping.bens.LoginBens;
import br.com.weeping.entity.Login;
import br.com.weeping.entity.Mensagem;
import br.com.weeping.entity.Post;
import br.com.weeping.entity.Resposta;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.LoginService;
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
	private LoginService servlogin;

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
		
		Login login = servlogin.consultar("f", "f");
	Collection<Usuario> lista= login.getUsuario().getListaAmigos();
	int i=0;
	for (Usuario post : lista) {
		i++;
		response.getWriter().append(i+" "+post.getNome() + " \n").append(request.getContextPath());
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
