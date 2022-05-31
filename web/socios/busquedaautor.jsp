<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html>
<html>
<head>
<meta charset="UTF-8">
<title> Busqueda Autor </title>
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
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Nuevo Autor</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
					</div>
					<div class="campos">
						<input type="text" id="nombre" name="nombre"> 
						<input name="operacion" type="hidden" id="operacion"
							value="busquedaautor">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Guardar">
					</div>
				</fieldset>
			</form>
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
		<div id="separacion">
			<br>
		</div>
	</div>
</body>
</html>