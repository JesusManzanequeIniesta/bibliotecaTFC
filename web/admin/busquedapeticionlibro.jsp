<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html>
<html>
<head>
<meta charset="UTF-8">
<title> Busqueda Peticion por Libro </title>
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
		<div id="formAutor" class="formulariogeneral">
			<form name="frmAutor" method="get"
				action="${pageContext.request.contextPath}/controlleradmin">
				<fieldset id="datosAutor">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Busqueda Petición</legend>
					<div class="etiquetas">
						<label for="titulolibro">Libro:</label>
					</div>
					<div class="campos">
						<input type="text" id="titulolibro" name="titulolibro"> 
						<input name="operacion" type="hidden" id="operacion"
							value="busquedapeticionlibro">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Guardar">
					</div>
				</fieldset>
			</form>
		</div>
                <c:if test="${not empty listadopeticiones}">
                    <div id="tabla">
                            <table class="table tablaconborde tablacebra">
                                    <caption>Listado de Peticiones</caption>
                                    <tr>
                                            <th scope="col">CODIGO</th>
                                            <th scope="col">FECHA FIN</th>
                                            <th scope="col">LIBRO</th>
                                            <th scope="col">SOCIO</th>
                                            <th scope="col">ESTADO</th>
                                    </tr>
                                    <c:forEach items="${listadopeticiones}" var="peticion">
                                                    <tr>
                                                    <td class="txtderecha">${peticion.id}</td>
                                                    <td>${peticion.fechafin}</td>
                                                    <td>${peticion.libroId.titulo}</td>
                                                    <td>${peticion.socioId.nombre}</td>
                                                    <c:choose>
                                                        <c:when test="${peticion.calculadoActivo != true}">
                                                            <td><strong>CADUCADA</strong></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>ACTIVA</td>
                                                        </c:otherwise>
                                                    </c:choose>

                                            </tr>
                                    </c:forEach>
                            </table>
                    </div>
                </c:if>		
		<div id="separacion">
			<br>
		</div>
	</div>
</body>
</html>