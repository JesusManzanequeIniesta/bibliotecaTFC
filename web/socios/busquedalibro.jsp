<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html>
<html>
<head>
<meta charset="UTF-8">
<title> Busqueda Libro </title>
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
				action="${pageContext.request.contextPath}/controllersocio">
				<fieldset id="datosAutor">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Busqueda Libro</legend>
					<div class="etiquetas">
						<label for="nombre">Titulo:</label>
					</div>
					<div class="campos">
						<input type="text" id="titulo" name="titulo"> 
						<input name="operacion" type="hidden" id="operacion"
							value="busquedalibro">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Guardar">
					</div>
				</fieldset>
			</form>
		</div>
                <c:if test="${not empty listadolibros}">
                    <div id="tabla">
                            <table class="table tablaconborde tablacebra">
                                    <caption>Listado de Libros</caption>
                                    <tr>
                                            <th scope="col">TITULO</th>
                                            <th scope="col">EJEMPLARES</th>
                                    </tr>
                                    <c:forEach items="${listadolibros}" var="libro">
                                                    <tr>
                                                    <td class="txtderecha">${libro.titulo}</td>
                                                    <td>${libro.getEjemplarList().size()}</td>
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