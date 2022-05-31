<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html>
<html>
<head>
<meta charset="UTF-8">
<title> Alta de Autor </title>
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
		<c:if test="${confirmaroperacion != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Mensaje" /></strong> <br>
					<c:out value="${confirmaroperacion}" />
				</p>
			</div>
		</c:if>
		<div id="formAutor" class="formulariogeneral">
			<form name="frmAutor" method="get"
				action="${pageContext.request.contextPath}/controller">
				<fieldset id="datosAutor">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Modifica Autor</legend>
					<div class="etiquetas">
						<label for="nombre">Nuevo nombre:</label>
					</div>
					<div class="campos">
						<input type="text" id="nombre" name="nombre"> 
					</div>
					<div class="etiquetas">
						<label for="Id">Identificador:</label>
					</div>
					<div class="campos">
						<input type="number" min="1" id="id" name="id"> 
						<input name="operacion" type="hidden" id="operacion"
							value="modificaautor">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Modificar">
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