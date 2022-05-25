<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Alta de Libro </title>
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
		<c:if test="${prestamo != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Préstamo dado de alta" /></strong> <br>
					<c:out value="${prestamo.id}" />
                                        <strong><c:out value="Fecha de devolución del préstamo" /></strong>
                                        <c:out value="${prestamo.fechalimite}" />
				</p>
			</div>
		</c:if>
		<div id="formRegistroAutomaticoLibro" class="formulariogeneral">
			<form name="formRegistroAutomaticoLibro" method="get"
				action="${pageContext.request.contextPath}/controlleradmin">
				<fieldset id="datosPrestamo">
					<legend><img src="resources/img/azarquiel.gif">&nbsp;Alta Préstamo</legend>
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
							value="registroprestamo">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Alta">
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