<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de Prestamos</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
	<c:if test="${not empty listadoprestamos}">
		<div id="tabla">
			<table class="table tablaconborde tablacebra">
				<caption>Listado de Prestamos</caption>
				<tr>
					<th scope="col">CODIGO</th>
					<th scope="col">SOCIO</th>
                                        <th scope="col">EJEMPLAR</th>
                                        <th scope="col">FECHA LIMITE</th>
                                        <th scope="col">FECHA DEVOLUCION</th>
                                        <th scope="col">ACTIVO</th>
				</tr>
				<c:forEach items="${listadoprestamos}" var="prestamo">
						<tr>
						<td class="txtderecha">${prestamo.id}</td>
						<td>${prestamo.socioId.nombre}</td>
                                                <td>${prestamo.ejemplarId.codbarras}</td>
                                                <td>${prestamo.fechalimite}</td>
                                                <td>${prestamo.fechadevolucion}</td>
                                                <c:choose>
                                                    <c:when test="${prestamo.calculadoActivo != true}">
                                                        <td><strong>DEVUELTO</strong></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>ACTIVO</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>		
   </div>
</body>
</html>