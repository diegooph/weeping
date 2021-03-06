package br.com.weeping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.weeping.entity.Login;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Login usuarioLogado = (Login) session.getAttribute("usuarioLogado");

		String url = req.getServletPath();

		if ((!url.contains("index.xhtml") &&  usuarioLogado == null)
				&& (!url.contains("/img/"))
				&& (!url.contains("/css/"))
				&& (!url.contains("/js/"))
				&& (!url.contains("javax.faces"))
				&& (!url.contains("cadastro.xhtml"))
				&& (!url.contains("login.xhtml"))
				&& (!url.contains("facelets"))
				&& (!url.contains("tests"))

		// para cada linha ex:&& (!url.contains("/img/")) � adicionada um
		// diretorio para o filtro nao bloquear
		) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.xhtml");
			dispatcher.forward(request, response);

		} else {
			
			if (!url.contains("principal.xhtml")
					&& (!url.contains("/img/"))
					&& (!url.contains("/css/"))
					&& (!url.contains("/js/"))
					&& (!url.contains("facelets"))
					&& (!url.contains("cadastro.xhtml"))
					&& (!url.contains("index.xhtml"))
					&& (!url.contains("painel.xhtml"))
					&& (!url.contains("user.xhtml"))
					&& (!url.contains("javax.faces"))
					&& (!url.contains("login.xhtml"))
					&& (!url.contains("tests"))) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/404.jsp");
				dispatcher.forward(request, response);

			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
