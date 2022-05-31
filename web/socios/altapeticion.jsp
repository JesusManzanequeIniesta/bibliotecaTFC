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
		<c:if test="${peticion != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Peticion dado de alta" /></strong> 
					<c:out value="Numero de peticion: ${peticion.id}" /> <br/>
                                        <strong><c:out value="Fecha de caducidad de la peticion" /></strong>
                                        <c:out value="${peticion.fechafin} , pasada esa fecha, la peticion será desactivada." />
				</p>
			</div>
		</c:if>
		<div id="formRegistroAutomaticoLibro" class="formulariogeneral">
			<form name="formRegistroAutomaticoLibro" method="get"
				action="${pageContext.request.contextPath}/controllersocio">
				<fieldset id="datosPrestamo">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Alta Préstamo</legend>
					<div class="etiquetas">
						<label for="emailsocio">Email Socio:</label>
					</div>
					<div class="campos">
						<input type="text" id="emailsocio" name="emailsocio"> 
					</div>
					<div class="etiquetas">
						<label for="titulolibro">Titulo Libro:</label>
					</div>
					<div class="campos">
						<input type="text" id="titulolibro" name="titulolibro"> 
						<input name="operacion" type="hidden" id="operacion"
							value="registropeticion">
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