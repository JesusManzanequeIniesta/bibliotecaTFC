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
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.GoogleBooks;
import tools.Tools;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("/controllersocio")
public class ControllerSocio extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSocio() {
        super();
        // TODO Auto-generated constructor stub
    }
    // CONTROL DE ERRORES

    protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        String mensajeError = e.getMessage();
        String traza = Tools.printStackTrace_toString(e);
        request.setAttribute("error", "Error->mensaje=" + mensajeError);
        request.setAttribute("traza", traza);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
            throws ServletException, IOException {
        int codigoError = e.getErrorCode();
        String traza = Tools.printStackTrace_toString(e);
        String mensajeError;
        switch (codigoError) {
            // >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
            default:
                mensajeError = e.getMessage();
        }
        request.setAttribute("error", "ErrorSQL->mensaje=" + mensajeError + ",codigo=" + codigoError);
        request.setAttribute("traza", traza);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    protected void procesarErrorBiblioteca(HttpServletRequest request, HttpServletResponse response, BibliotecaException e)
            throws ServletException, IOException {
        int codigoError = e.getErrorCode();
        String traza = Tools.printStackTrace_toString(e);
        String mensajeError;
        switch (codigoError) {
            // >>>> Tenemos que ver los códigos de error y lo que vamos a hacer
            default:
                mensajeError = e.getMessage();
        }
        request.setAttribute("error", "ErrorBiBlioteca->mensaje=" + mensajeError + ",codigo=" + codigoError);
        request.setAttribute("traza", traza);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    // En cada operación creo un dao y al final lo cierro
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        Dao dao = null;
        DaoAutor daoautor;
        DaoLibro daolibro;
        DaoPeticion daopeticion;
        DaoSocio daosocio;
//		DaoEscribe daoescribe;
        DaoEjemplar daoejemplar;
        // >>>> modificar para que ante excepcion se cierre
        // dao con rollback
        switch (operacion) {
            case "listarAutores":
		try {
                    dao = new Dao();
                    daoautor = new DaoAutor(dao);
                    List<Autor> listadoautores = daoautor.listAllAuthors();
                    dao.close();
                    request.setAttribute("listadoautores", listadoautores);
                    request.getRequestDispatcher("/socios/listadoautores.jsp").forward(request, response);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }
            break;
            case "registropeticion":
                dao = new Dao();
                daopeticion = new DaoPeticion(dao);
                daolibro = new DaoLibro(dao);
                daosocio = new DaoSocio(dao);
                String titulo = request.getParameter("titulolibro");
                String emailsocio = request.getParameter("emailsocio");
                try {
                    Libro libro = daolibro.findByTitulo(titulo);

                    for(Ejemplar ejemplar:libro.getEjemplarList()){
                        if(ejemplar.getCalculadoPrestado() == false && ejemplar.getCalculadoPrestable() == true){

                                throw new BibliotecaException("Hay al menos un ejemplar disponible, no es necesario hacer una peticion", 7);

                        }
                    }
                    Peticion peticion = new Peticion();
                    peticion.setFechainicio(LocalDate.now());
                    peticion.setFechafin(LocalDate.now().plusDays(3));
                    peticion.setLibroId(libro);
                    peticion.setSocioId(daosocio.findByEmail(emailsocio));
                    peticion.setCalculadoActivo(true);
                    
                    daopeticion.create(peticion);
                    
                    request.setAttribute("peticion", peticion);
                    request.getRequestDispatcher("/socios/altapeticion.jsp").forward(request, response);
                } 
                catch (BibliotecaException be) {
                    procesarErrorBiblioteca(request, response, be);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }
                break;
            case "busquedaautor":
                dao = new Dao();
                String nombreAutor = request.getParameter("nombre");
                daoautor = new DaoAutor(dao);
                try{
                    List<Autor> listaautor = daoautor.findByNombreLike(nombreAutor);
                    request.setAttribute("listadoautores", listaautor);
                    request.getRequestDispatcher("/socios/busquedaautor.jsp").forward(request, response);
                }catch (Exception e){
                    procesarError(request, response, e);
                }
                break;
            case "busquedalibro":
                dao = new Dao();
                String tituloLibro = request.getParameter("titulo");
                daolibro = new DaoLibro(dao);
                try{
                    List<Autor> listalibros = daolibro.findByTituloLike(tituloLibro);
                    request.setAttribute("listadolibros", listalibros);
                    request.getRequestDispatcher("/socios/busquedalibro.jsp").forward(request, response);
                }catch (Exception e){
                    procesarError(request, response, e);
                }
                break;
            case "logout":
                HttpSession sesion = request.getSession();
                sesion.invalidate();
                response.sendRedirect("index.jsp");
                break;
            default:
                request.setAttribute("error", "No existe esa opción en controller");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
