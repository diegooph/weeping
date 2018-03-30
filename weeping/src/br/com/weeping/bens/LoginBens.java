package br.com.weeping.bens;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.weeping.entity.Login;
import br.com.weeping.service.LoginService;


@ViewScoped
@ManagedBean(name = "loginbean")


// a cada metodo que fomos utilizando , adicione um comentario encima do campo com o nome da paggina que foi utilizado
public class LoginBens {
	@Inject
	private LoginService daologin = new LoginService();
	private Login login = new Login();
	
	
	public String logar(){
		
		Login loginUser = daologin.consultar(login.getLogin(), login.getSenha());
		
		if (loginUser != null){// achou o usuário
			
			//adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", loginUser);

			return "primeirapagina.jsf";
		}
		
		return "index.jsf";
	}
	
	
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Login loginUser = (Login) externalContext.getSessionMap().get("usuarioLogado");

		return loginUser.getPerfilUser().equals(acesso);
		
	}

}
