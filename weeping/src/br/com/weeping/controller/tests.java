package br.com.weeping.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.weeping.entity.Usuario;
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

		 for (Usuario usuario : servuser.getUsuarios()) {
//		Usuario usuario = new Usuario();
//		usuario.setNome("diego");
//		usuario.setSenha("abc");
//		usuario = servuser.consultar(usuario.getNome(), usuario.getSenha());
		response.getWriter().append(usuario.getNome()).append(request.getContextPath());
		
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
