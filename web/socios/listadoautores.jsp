<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de Autores</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
	<c:if test="${not empty listadoautores}">
		<div id="tabla">
			<table class="table tablaconborde tablacebra">
				<caption>Listado de Autores</caption>
				<tr>
					<th scope="col">CODIGO</th>
					<th scope="col">NOMBRE</th>
				</tr>
				<c:forEach items="${listadoautores}" var="autor">
						<tr>
						<td class="txtderecha">${autor.id}</td>
						<td>${autor.nombre}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>		
   </div>
</body>
</html>