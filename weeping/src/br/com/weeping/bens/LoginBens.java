package br.com.weeping.bens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.model.UploadedFile;

import br.com.weeping.entity.Endereco;
import br.com.weeping.entity.Login;
import br.com.weeping.entity.Usuario;
import br.com.weeping.service.LoginService;
import br.com.weeping.service.UsuarioService;

@ViewScoped
@ManagedBean(name = "loginbean")

// a cada metodo que fomos utilizando , adicione um comentario encima do campo
// com o nome da paggina que foi utilizado
public class LoginBens {
	@Inject
	private LoginService daologin = new LoginService();
	private Login login = new Login();
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();

	@Inject
	private UsuarioService usuarioDao = new UsuarioService();
	private boolean aceitarTermos;
	private UploadedFile arquivo;

	public String SalvarLogin() throws IOException {
		setFotousuario();

		usuario.setEndereco(endereco);
		login.setUsuario(usuario);

		if (validarCamposCadastro()) {
			daologin.salvar(login);
			return "login.xhtml";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "há campos não validados."));

		return "";

	}

	public String novaInstancia() {
		usuario = new Usuario();
		login = new Login();
		aceitarTermos = false;
		return "";
	}

	public String logar() {

		Login loginUser = daologin.consultar(login.getLogin(), login.getSenha());

		if (loginUser != null) {// achou o usuÃ¡rio

			// adicionar o usuÃ¡rio na sessÃ£o usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", loginUser);
			// adicionar a pagina de entrada do usuario
			return "principal.xhml?faces-redirect=true";
		}

		return "";
	}

	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Login loginUser = (Login) externalContext.getSessionMap().get("usuarioLogado");

		return loginUser.getPerfilUser().equals(acesso);

	}

	public byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	public static boolean validar(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	public boolean validarCamposCadastro() {

		if (!validar(usuario.getEmail()) || usuario.getEmail() == null || usuario.getEmail().equals("")) {
			return false;
		}

		if (usuario.getNome() == null || usuario.getNome().length() <= 0 || usuario.getNome().equals("")
				|| usuario.getNome().length() > 15) {
			return false;
		}

		if (usuario.getSobrenome() == null || usuario.getSobrenome().length() <= 0 || usuario.getSobrenome().equals("")
				|| usuario.getSobrenome().length() > 30) {
			return false;
		}

		if (login.getLogin() == null || login.getLogin().equals("") || login.getLogin().length() <= 0
				|| login.getLogin().length() > 12) {
			return false;
		}

		if (login.getSenha() == null || login.getSenha().equals("") || login.getSenha().length() <= 0
				|| login.getSenha().length() > 20) {
			return false;

		}

		return true;

	}

	private void setFotousuario() throws IOException {
		try {
			
	
			System.out.println(arquivo+"soghósdfhhjnjapjdgpbojg ojpbjgpboijpzonjmkpozkbf´zkokhhpokvbókd pobkvzsv---------------------------------------------------------------");
			String miniImgBase64 = DatatypeConverter.printBase64Binary(getBytes(arquivo.getInputstream()));

			// Convertendo para byte[] usando lib apache
			byte[] imageBytes = Base64.getDecoder().decode(miniImgBase64);
			usuario.setFotoIconBase64Original(imageBytes);
			System.out.println(imageBytes+" s  ogh   ós  fhhjnjapjdgpbojg ojpbjgpboijpzonjmkpozkbf´zkokhhpokvbókd pobkvzsv---------------------------------------------------------------");
			// Transformando em BufferedImage
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

			// Pega o tipo da imagem
			int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

			// largura e a altura
			int largura = Integer.parseInt("300");
			int altura = Integer.parseInt("180");

			// Cria a imagem em minitura
			BufferedImage resizedImage = new BufferedImage(largura, altura, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(bufferedImage, 0, 0, largura, altura, null);
			g.dispose();

			// Escrevendo novamente a imagem em tamanho menor
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String extensao = arquivo.getContentType().split("\\/")[1];
			ImageIO.write(resizedImage, extensao, baos);

			miniImgBase64 = "data:" + arquivo.getContentType() + ";base64,"
					+ DatatypeConverter.printBase64Binary(baos.toByteArray());

			usuario.setFotoIconBase64(miniImgBase64);
			usuario.setExtensao(extensao);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "adicione uma foto de valida."));
		}
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LoginService getDaologin() {
		return daologin;
	}

	public void setDaologin(LoginService daologin) {
		this.daologin = daologin;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioService usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public boolean isAceitarTermos() {
		return aceitarTermos;
	}

	public void setAceitarTermos(boolean aceitarTermos) {
		this.aceitarTermos = aceitarTermos;
	}

}
