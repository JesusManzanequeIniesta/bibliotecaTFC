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
import static java.time.temporal.ChronoUnit.DAYS;
import tools.GoogleBooks;
import tools.Tools;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("/controlleradmin")
public class ControllerAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
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
        DaoEstado daoestado;
        DaoEjemplar daoejemplar;
        DaoSocio daosocio;
        DaoPrestamo daoprestamo;
        DaoTipoSancion daotiposancion;
        // >>>> modificar para que ante excepcion se cierre
        // dao con rollback
        switch (operacion) {
            // Se trata del proceso de registro de cada libro que llega a la Biblioteca
            // pero realizado sin captura adicional de información
            // Si no encuentra el libro debe proceder de forma manual
            case "registroautomaticolibro":
                // El proceso empieza con la lectura del ISBN del libro
                String isbn = request.getParameter("isbn");
                // Primero miramos si ya está registrado
                try {
                    dao = new Dao();
                    Libro libro;
                    daolibro = new DaoLibro(dao);
                    libro = daolibro.findByISBN(isbn);
                    if (libro == null) {
                        // No ha sido dado de alta anteriormente, lo busco en Google
                        JSONObject jsLibro = GoogleBooks.buscaISBN(isbn);
                        if (jsLibro == null) {
                            request.setAttribute("error", "Libro no localizado en BD Gooogle");
                            request.getRequestDispatcher("/admin/registroautomaticolibro.jsp").forward(request, response);
                        } else {
                            // Leo datos JSON para proceder al alta
                            libro = new Libro();
                            libro.setIsbn(isbn);
                            libro.setTitulo(jsLibro.getString("titulo"));
                            // Ahora mismo paso del tejuelo para no hacer ésto mas largo
                            //libro.Tejuelo();
                            daolibro.create(libro);
                            // Ahora proceso los autores, si están dados de alta no los vuelvo a dar
                            Autor autor;
                            daoautor = new DaoAutor(dao);
//						daoescribe=new DaoEscribe(dao);
                            JSONArray jsAutores = jsLibro.getJSONArray("autores");
                            for (int i = 0; i < jsAutores.length(); i++) {
                                String nombre = jsAutores.getString(i);
                                // Nombre de autor vacio
                                // >>> Quizás mejor que no aparezca en json
                                if (!nombre.equals("")) {
                                    autor = daoautor.findByNombre(nombre);
                                    // No he encontrado el autor asi que lo doy de alta
                                    if (autor == null) {
                                        autor = new Autor();
                                        autor.setNombre(nombre);
                                        daoautor.create(autor);
                                    }
                                    // Tengo que decir que ha escrito ese libro
                                    libro.getAutorList().add(autor);
                                    autor.getLibroList().add(libro);
                                }
                            } // fin for de autores
                        } // fin jsLibro
                    } // fin Libro
                    // Ahora inserto un ejemplar de ese libro
                    Ejemplar ejemplar = new Ejemplar();
                    ejemplar.setLibroId(libro);
                    ejemplar.setFechaalta(LocalDate.now());
                    daoestado = new DaoEstado(dao);
                    ejemplar.setEstadoId(daoestado.findById(1));
                    ejemplar.setLibroId(libro);
                    daoejemplar = new DaoEjemplar(dao);
                    daoejemplar.create(ejemplar);
                    // Cierro dao y valido toda la operación
                    dao.close();
                    dao = null;
                    // Paso como atributos el libro y el ejemplar
                    request.setAttribute("libro", libro);
                    request.setAttribute("ejemplar", ejemplar);
                    String ruta = getServletConfig().getServletContext().getRealPath("/generados");
                    // Genero el pdf en generados
                    String file = Tools.creaFicheroPDF(ruta + "/", "Ejemplar", ejemplar.getCodbarras());
//				request.setAttribute("error",file);
                    request.getRequestDispatcher("/admin/registroautomaticolibro.jsp").forward(request, response);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }

                break;
            case "insertaautor":
                Autor autor = new Autor();
                String nombreAutor = request.getParameter("nombre");
                autor.setNombre(nombreAutor);
                try {
                    dao = new Dao();
                    daoautor = new DaoAutor(dao);
                    daoautor.create(autor);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }
                request.setAttribute("confirmaroperacion", "Autor creado satisfactoriamente");
                request.getRequestDispatcher("/admin/altaautor.jsp").forward(request, response);
                break;
            case "modificaautor":
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                try {
                    dao = new Dao();
                    daoautor = new DaoAutor(dao);
                    autor = daoautor.findById(id);
                    // Si devuelve null no se ha encontrado lanzamos excepción
                    // >>> El if con new Autor es para probar excepciones
                    // eliminarlo y descomentar el if con el throw
                    if (autor == null) {
                        throw new BibliotecaException("El autor no existe", 1);
                    }
                    /*if (autor==null) {
					autor=new Autor();
					autor.setId(id);
				}*/
                    autor.setNombre(nombre);
                    daoautor.edit(autor);
                    dao.close();
                    dao = null;
                } catch (BibliotecaException be) {
                    procesarErrorBiblioteca(request, response, be);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }
                request.setAttribute("confirmaroperacion", "Autor modificado satisfactoriamente");
                request.getRequestDispatcher("/modificaautor.jsp").forward(request, response);
                break;
            case "registroprestamo":
                String codBarrasSocio = request.getParameter("codBarrasSocio");
                String codBarrasEjemplar = request.getParameter("codBarrasEjemplar");
                try {
                    dao = new Dao();
                    daoejemplar = new DaoEjemplar(dao);
                    daosocio = new DaoSocio(dao);
                    daoprestamo = new DaoPrestamo(dao);
                    Prestamo prestamo = new Prestamo();
                    
                    Socio socio = new Socio();
                    Ejemplar ejemplar = new Ejemplar();
                    
                    socio = daosocio.findByCodBarras(codBarrasSocio);
                    
                    if(socio == null){
                        throw new BibliotecaException("El socio no existe en la base de datos.", 2);
                    }
                    ejemplar = daoejemplar.findByCodBarras(codBarrasEjemplar);
                    if(ejemplar == null){
                        throw new BibliotecaException("El ejemplar no existe", 3);
                    }
                    
                    prestamo.setSocioId(socio);
                    prestamo.setEjemplarId(ejemplar);
                    prestamo.setFechainicio(LocalDate.now());
                    prestamo.setFechalimite(LocalDate.now().plusDays(7));
                    prestamo.setCalculadoActivo(true);
                    daoprestamo.create(prestamo);
                    
                    socio.getPrestamoList().add(prestamo);
                    ejemplar.getPrestamoList().add(prestamo);
                    ejemplar.setCalculadoPrestado(true);
                    
                    dao.close();
                    dao = null;
                    
                    request.setAttribute("prestamo", prestamo);
                    request.setAttribute("confirmaroperacion", "Prestmoa añadido satisfactoriamente");
                    request.getRequestDispatcher("/admin/altaprestamo.jsp").forward(request, response);
                } catch (BibliotecaException be) {
                    procesarErrorBiblioteca(request, response, be);
                } catch (Exception e) {
                    procesarError(request, response, e);
                }
                break;
            case "devolucionEjemplar":
                String codBarrasSocioDevolucion = request.getParameter("codBarrasSocio");
                String codBarrasEjemplarDevolucion = request.getParameter("codBarrasEjemplar");
                String estadoEjemplar = request.getParameter("estado");
                try {
                    dao = new Dao();
                    Tiposancion ts = new Tiposancion();
                    daosocio = new DaoSocio(dao);
                    daotiposancion = new DaoTipoSancion(dao);                    
                    Socio socio = daosocio.findByCodBarras(codBarrasSocioDevolucion);
                                        
                    if(socio == null){
                        throw new BibliotecaException("El socio no existe en la base de datos.", 2);
                    }
                    
                    List<Prestamo> listaPrestamo = socio.getPrestamoList();
                    
                    for(Prestamo p:listaPrestamo){
                        if(p.getCalculadoActivo() == true && p.getEjemplarId().getCodbarras().equals(codBarrasEjemplarDevolucion)){
                            switch (estadoEjemplar) {
                                case "CORRECTO":
                                    if (p.getFechalimite().isAfter(LocalDate.now())) {

                                        p.setFechadevolucion(LocalDate.now());
                                        p.getEjemplarId().setCalculadoPrestado(true);
                                        p.setCalculadoActivo(false);
                                        request.setAttribute("devolucioncorrecta", true);
                                    } else {
                                        ts.setDescripcion("Sanción por devolución tardía");
                                        int diasSancionado = 3 * ((int)DAYS.between(p.getFechalimite(), LocalDate.now()));
                                        ts.setDiassancion(diasSancionado);
                                        daotiposancion.create(ts);
                                        
                                        socio.setCalculadoSancionado(true);
                                        socio.setCalculadoFechafinsancion(LocalDate.now().plusDays(diasSancionado));

                                        ts.getPrestamoList().add(p);
                                        p.setTiposancionId(ts);
                                        p.setFechadevolucion(LocalDate.now());
                                        p.getEjemplarId().setCalculadoPrestado(false);
                                        p.setCalculadoActivo(false);
                                        p.setTiposancionId(ts);
                                        
                                        request.setAttribute("devolucioncorrecta", false);
                                        request.setAttribute("socio", socio);
                                        request.setAttribute("tiposancion", ts);
                                    }
                                    break;
                                case "DAÑADO":
                                    int diasSancionRoto = 14;
                                    if(p.getFechalimite().isAfter(LocalDate.now())){

                                        ts.setDescripcion("Sanción por daños al libro.");
                                        
                                        ts.setDiassancion(diasSancionRoto);
                                        daotiposancion.create(ts);
                                        socio.setCalculadoSancionado(true);
                                        socio.setCalculadoFechafinsancion(LocalDate.now().plusDays(diasSancionRoto));

                                        ts.getPrestamoList().add(p);
                                        p.setTiposancionId(ts);
                                        p.setFechadevolucion(LocalDate.now());
                                        p.getEjemplarId().setCalculadoPrestado(false);
                                        p.setCalculadoActivo(false);
                                        p.setTiposancionId(ts);
                                        
                                        daoestado = new DaoEstado(dao);
                                        Estado estado = daoestado.findByDescripcion(estadoEjemplar);
                                        p.getEjemplarId().setEstadoId(estado);
                                        
                                        request.setAttribute("devolucioncorrecta", false);
                                        request.setAttribute("socio", socio);
                                        request.setAttribute("tiposancion", ts);
                                    }
                                    else{
                                        ts.setDescripcion("Sanción por devolución tardía. Se añaden 2 semanas más por daños al libro.");
                                        int diasSancionado = diasSancionRoto+(3 * ((int)DAYS.between(p.getFechalimite(), LocalDate.now())));
                                        ts.setDiassancion(diasSancionado);
                                        daotiposancion.create(ts);
                                        
                                        socio.setCalculadoSancionado(true);
                                        socio.setCalculadoFechafinsancion(LocalDate.now().plusDays(diasSancionado));

                                        ts.getPrestamoList().add(p);
                                        p.setTiposancionId(ts);
                                        p.setFechadevolucion(LocalDate.now());
                                        p.getEjemplarId().setCalculadoPrestado(false);
                                        p.setCalculadoActivo(false);
                                        p.setTiposancionId(ts);
                                                                                
                                        daoestado = new DaoEstado(dao);
                                        Estado estado = daoestado.findByDescripcion(estadoEjemplar);
                                        p.getEjemplarId().setEstadoId(estado);
                                        
                                        request.setAttribute("devolucioncorrecta", false);
                                        request.setAttribute("socio", socio);
                                        request.setAttribute("tiposancion", ts);
                                    }
                                    break;
                                case "DESTRUIDO":
                                    int diasSancionDest = 30;
                                    if(p.getFechalimite().isAfter(LocalDate.now())){
                                        ts.setDescripcion("Sanción por destrucción del libro. La Dirección del centro le obligará a pagar la totalidad del Ejemplar.");
                                        
                                        ts.setDiassancion(diasSancionDest);
                                        daotiposancion.create(ts);
                                        
                                        socio.setCalculadoSancionado(true);
                                        socio.setCalculadoFechafinsancion(LocalDate.now().plusDays(diasSancionDest));

                                        ts.getPrestamoList().add(p);
                                        p.setTiposancionId(ts);
                                        p.setFechadevolucion(LocalDate.now());
                                        p.getEjemplarId().setCalculadoPrestado(false);
                                        p.setCalculadoActivo(false);
                                        p.setTiposancionId(ts);
                                        
                                        
                                        daoestado = new DaoEstado(dao);
                                        Estado estado = daoestado.findByDescripcion(estadoEjemplar);
                                        p.getEjemplarId().setEstadoId(estado);
                                        
                                        request.setAttribute("devolucioncorrecta", false);
                                        request.setAttribute("socio", socio);
                                        request.setAttribute("tiposancion", ts);
                                    }
                                    else{
                                        ts.setDescripcion("Sanción por devolución tardía y destruccion del libro. La Dirección del centro le obligará a pagar la totalidad del Ejemplar.");
                                        int diasSancionado = diasSancionDest + (3 * ((int)DAYS.between(p.getFechalimite(), LocalDate.now())));
                                        ts.setDiassancion(diasSancionado);
                                        daotiposancion.create(ts);
                                        
                                        socio.setCalculadoSancionado(true);
                                        socio.setCalculadoFechafinsancion(LocalDate.now().plusDays(diasSancionado));
                                        
                                        ts.getPrestamoList().add(p);
                                        p.setTiposancionId(ts);
                                        p.getEjemplarId().setCalculadoPrestado(false);
                                        p.getEjemplarId().setCalculadoPrestable(false);
                                        p.setCalculadoActivo(false);
                                        
                                        daoestado = new DaoEstado(dao);
                                        Estado estado = daoestado.findByDescripcion(estadoEjemplar);
                                        p.getEjemplarId().setEstadoId(estado);
                                        
                                        request.setAttribute("devolucioncorrecta", false);
                                        request.setAttribute("socio", socio);
                                        request.setAttribute("tiposancion", ts);
                                    }
                                    break;
                            }
                            
                        }
                        
                    }
                    
                    
                    dao.close();
                    dao = null;
                    
                    request.getRequestDispatcher("/admin/devolucionejemplar.jsp").forward(request, response);
                } catch (BibliotecaException be) {
                    procesarErrorBiblioteca(request, response, be);
                } catch (Exception e) {
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
