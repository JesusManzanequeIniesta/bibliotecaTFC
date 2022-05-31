<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Login</title>
</head>
<body>
	<div id="container">
	<div id="header"></div>
     <div id="formLogin" class="formulariogeneral">		
		<form action="j_security_check" method="POST">
			<fieldset id="datosAutor">
                   <legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Introduzca sus datos de usuario</legend>
                   	<div class="etiquetas">
						<label for="j_username">Usuario:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="j_username" 
						       name="j_username"/>
					</div>	
					<div class="etiquetas">
						<label for="j_password">Clave:</label>
					</div>	
				    <div class="campos">
						<input type="password" 
						       id="j_password" 
						       name="j_password"/>
					</div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Acceder">
							<a href="${pageContext.request.contextPath}/altasocio.jsp">Registrese</a>
					</div>	
				</fieldset>				
		</form>
		</div>
	</div>
</body>
</html>