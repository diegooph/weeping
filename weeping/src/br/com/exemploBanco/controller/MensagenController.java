package br.com.exemploBanco.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.exemploBanco.entity.Usuario;

import br.com.weeping.service.UsuarioService;

/**
 * Servlet implementation class MensagenController
 */
@WebServlet("/")
public class MensagenController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService serv;

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
		
		serv.persist(usuario);

		response.getWriter().append("pppp" + usuario.getNome()).append(request.getContextPath());

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
