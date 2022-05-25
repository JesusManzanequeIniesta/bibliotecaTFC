<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
    language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title> Devolución Ejemplar </title>
            <jsp:directive.include file="../includes/includefile.jspf" />
        </head>
        <body>
            <div id="container">
                <div id="header"></div>
                <div id="menu">
                    <jsp:directive.include file="../WEB-INF/menu.jspf" />
                </div>
                <c:if test="${error != null}">
                    <div id="diverror">
                        <p>
                            <strong><c:out value="Error" /></strong> <br>
                            <c:out value="${error}" />
                        </p>
                    </div>
                </c:if>
                <c:if test="${socio != null && tiposancion != null}">
                    <div id="diverror">
                        <p>
                            <strong><c:out value="Usuario recibe sancion. Motivo: ${tiposancion.descripcion}" /></strong> <br/>
                            <c:out value="Recibe sanción hasta el ${socio.calculadoFechafinsancion}, aplicada desde el día de hoy" />
                        </p>
                    </div>
                </c:if>
                <c:if test="${devolucioncorrecta == true}">
                    <div id="divconfirmacion">
                        <p>
                            <strong><c:out value="Ejemplar devuelto antes de fecha limite" /></strong> <br>
                        </p>
                    </div>
                </c:if>
                <div id="formRegistroAutomaticoLibro" class="formulariogeneral">
                    <form name="formRegistroAutomaticoLibro" method="get"
                          action="${pageContext.request.contextPath}/controlleradmin">
                        <fieldset id="datosPrestamo">
                            <legend>
                                <img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">
                                &nbsp;Devolución de Ejemplar
                            </legend>
                            <div class="etiquetas">
                                <label for="estado">Estado:</label>
                            </div>
                            <div class="campos">
                                <select name="estado" id="estado">
                                    <option value="">---</option>
                                    <option value="CORRECTO">CORRECTO</option>
                                    <option value="DAÑADO">DAÑADO</option>
                                    <option value="DESTRUIDO">DESTRUIDO</option>
                                </select>
                            </div>
                            <div class="etiquetas">
                                <label for="codBarrasSocio">CodBarras Socio:</label>
                            </div>
                            <div class="campos">
                                <input type="text" id="codBarrasSocio" name="codBarrasSocio"> 
                            </div>
                            <div class="etiquetas">
                                <label for="codBarrasEjemplar">CodBarras Ejemplar:</label>
                            </div>
                            <div class="campos">
                                <input type="text" id="codBarrasEjemplar" name="codBarrasEjemplar"> 
                                <input name="operacion" type="hidden" id="operacion"
                                       value="devolucionEjemplar">
                            </div>
                            <div class="cb"></div>
                            <div class="cb"></div>
                            <div class="botones">	
                                <input type="submit" name="Submit" value="Devolución">
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div id="separacion">
                    <br>
                </div>
            </div>
        </body>
    </html>