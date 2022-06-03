package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.*;
import entidades.*;
import excepciones.BibliotecaException;
import java.security.MessageDigest;
import java.util.List;
import tools.GoogleBooks;
import tools.Tools;
/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Este valor debe coincidir con lo almacenado en la tabla Grupos
	// para el grupo que tiene el role socios
	private static final String ROLESOCIOS="sociosbiblioteca";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}
	// CONTROL DE ERRORES
	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		String mensajeError = e.getMessage();
		String traza=Tools.printStackTrace_toString(e);
		request.setAttribute("error", "Error->mensaje="+mensajeError);
		if (Tools.valorTRAZA()) request.setAttribute("traza", traza);
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
			throws ServletException, IOException {
		int codigoError = e.getErrorCode();
		String traza=Tools.printStackTrace_toString(e);
		String mensajeError;
		switch (codigoError) {
		// >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
		default:
			mensajeError = e.getMessage();
		}
    	request.setAttribute("error", "ErrorSQL->mensaje="+mensajeError+",codigo="+codigoError);
    	if (Tools.valorTRAZA()) request.setAttribute("traza", traza);
		request.getRequestDispatcher("/error.jsp").forward(request,response);
	}   
	
	protected void procesarErrorBiblioteca(HttpServletRequest request, HttpServletResponse response, BibliotecaException e)
			throws ServletException, IOException {
		int codigoError = e.getErrorCode();
		String traza=Tools.printStackTrace_toString(e);
		String mensajeError;
		switch (codigoError) {
		// >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
		default:
			mensajeError = e.getMessage();
		}
    	request.setAttribute("error", "ErrorBiBlioteca->mensaje="+mensajeError+",codigo="+codigoError);
    	if (Tools.valorTRAZA()) request.setAttribute("traza", traza);
		request.getRequestDispatcher("/error.jsp").forward(request,response);
	}   
	
	// En cada operación creo un dao y al final lo cierro
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		Dao dao=null;
		DaoSocio daosocio;
		DaoUsuarios daousuarios;
		DaoAutor daoautor;
		DaoGrupos daogrupos;
		DaoToken daotoken;
		// >>>> modificar para que ante excepcion se cierre
		// dao con rollback
		switch (operacion) {
		case "listarAutores":
			try {
				dao=new Dao();
				daoautor=new DaoAutor(dao);
                                List<Autor> listadoautores = daoautor.listAllAuthors();
				dao.close();
				request.setAttribute("listadoautores", listadoautores);
				request.getRequestDispatcher("/socios/listadoautores.jsp").forward(request, response);
			} catch (Exception e) {	procesarError(request, response, e);}
		break;
		case "registrarse":
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			// El email se pasa a mayúsculas para no tener problemas en login
			// pero se avisa al usuario que debe ponerlo en minusculas
			email=email.toLowerCase();
			//String clave = request.getParameter("password");
			String telefono = request.getParameter("telefono");
			String grecaptcharesponse = request.getParameter("g-recaptcha-response");
			if (!Tools.validarCaptcha(grecaptcharesponse)) {
				String mensajeError = "Verifique que no es un robot";
				request.setAttribute("error", mensajeError);
				request.getRequestDispatcher("altasocio.jsp").forward(request, response);
			} else  
				try {
					dao=new Dao();
					daosocio = new DaoSocio(dao);
					Socio socio=daosocio.findByEmail(email);
					// Si el socio ya existe lanzamos un error
					// no podemos tener dos socios con mismo email
					if (socio!=null) throw new BibliotecaException("Email de socio usado",3);
					socio = new Socio();
					socio.setNombre(nombre);
					socio.setEmail(email);
					socio.setTelefono(telefono);
					daosocio.create(socio);
					// Creo un token de validación de email y telefono
					Token token=new Token();
					token.setEmail(email);
					token.setTelefono(telefono);
//					// Genero un token aleatorio
					token.setValue(Tools.generaToken());
					daotoken = new DaoToken(dao);
					daotoken.inserta(token);
					// Envío correo de validación
					String asunto="Validación en correo registro aplicación biblioteca";
					String cuerpo=Tools.creaCuerpoCorreo(token.getValue());
					Tools.enviarConGMail(email,asunto,cuerpo);
					dao.close();
					request.setAttribute("socio", socio);
					request.getRequestDispatcher("/socioregistrado.jsp").forward(request, response);
				} catch (BibliotecaException be) {procesarErrorBiblioteca(request, response, be);}
			  	  catch (Exception e) {	procesarError(request, response, e);}
				  
			break;
		case "validacion":
			// Recibo el correo de validación con un token
			String value = request.getParameter("token");
			try {
				dao=new Dao();
				daotoken = new DaoToken(dao);
				Token token = daotoken.findByValue(value);
				if (token==null) throw new BibliotecaException("token no encontrado",8);
				// >> Tendría que mirar si está caducado
				daousuarios = new DaoUsuarios(dao);
				Usuarios usuario=new Usuarios();
				usuario.setUsuario(token.getEmail());
				String clave=Tools.generaClave();
                                //Pasamos la clave a MD5 antes de insertarla. Código de howtodoinjava.com

                                // Create MessageDigest instance for MD5
                                MessageDigest md = MessageDigest.getInstance("MD5");

                                // Add password bytes to digest
                                md.update(clave.getBytes());

                                // Get the hash's bytes
                                byte[] bytes = md.digest();

                                // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < bytes.length; i++) {
                                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                                }
                                        
//                              Fin paso a MD5
				usuario.setClave(sb.toString());
				Tools.enviarSMS(token.getTelefono(),"La contraseña de acceso es"+clave);
//				// Borro el token después de usarlo
				daotoken.borra(token);
                                daousuarios.create(usuario);
				daogrupos = new DaoGrupos(dao);
				Grupos grupo = new Grupos();
//				El nombre del role 
                                GruposPK grupoPK = new GruposPK();
                                grupoPK.setIdgrupo(ROLESOCIOS);
                                grupoPK.setIdusuario(usuario.getUsuario());
                                grupo.setGruposPK(grupoPK);
                                daogrupos.create(grupo);
                                usuario.getGruposList().add(grupo);
                                dao.close();
				request.setAttribute("token", token);
				request.getRequestDispatcher("/validacionOK.jsp").forward(request, response);
			}
			catch (SQLException e) {procesarErrorSQL(request, response, e);} 
		  	catch (BibliotecaException be) {procesarErrorBiblioteca(request, response, be);}
		  	catch (Exception e) {	procesarError(request, response, e);}
			
		case "logout":
			HttpSession sesion = request.getSession(false);
			sesion.invalidate();
			response.sendRedirect("index.jsp");
			break;
		default:
			request.setAttribute("error", "No existe esa opción en controller");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
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
