<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:directive.include file="includes/includefile.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="tabla">
			<c:if test="${socio != null}">
				<table class="table tablaconborde">
					<caption>Sus datos han sido registrados <br>
							 Recibirá un correo para activar la cuenta</caption>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">NOMBRE</th>
						<th scope="col">EMAIL</th>
						<th scope="col">TELEFONO</th>
					</tr>
					<tr>
						<td>${socio.id}</td>
						<td>${socio.nombre}</td>
						<td>${socio.email}</td>
						<td>${socio.telefono}</td>
					</tr>
				</table>
				<div class="botones">
					<a href="index.jsp" title="inicio">Login</a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>