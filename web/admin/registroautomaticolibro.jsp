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
		<c:if test="${libro != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Libro dada de alta" /></strong> <br>
					<c:out value="${libro.titulo}" />
				</p>
			</div>
		</c:if>
		<c:if test="${ejemplar != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Le corresponde el ejemplar" /></strong> 
					<c:out value="${ejemplar.codbarras}" />
				</p>
				<embed src="generados/Ejemplar_${ejemplar.codbarras}.pdf#zoom=300" type="application/pdf" width="640px" height="300px" />
			</div>
		</c:if>
		<div id="formRegistroAutomaticoLibro" class="formulariogeneral">
			<form name="formRegistroAutomaticoLibro" method="get"
				action="${pageContext.request.contextPath}/controlleradmin">
				<fieldset id="datosLibro">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Alta Libro</legend>
					<div class="etiquetas">
						<label for="isbn">ISBN:</label>
					</div>
					<div class="campos">
						<%-- autofocus pone ahí el enfoque en cuento se carga --%>
						<input type="text" id="isbn" name="isbn" autofocus> 
						<input name="operacion" type="hidden" id="operacion"
							value="registroautomaticolibro">
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