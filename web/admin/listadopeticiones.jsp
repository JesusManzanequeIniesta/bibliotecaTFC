<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de Peticiones</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
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
   </div>
</body>
</html>