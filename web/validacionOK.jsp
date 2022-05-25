<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validación OK</title>
</head>
<body>
	<div id="container">
			<c:if test="${token != null}">
				<div id="divconfirmacion">
					<p>
						<strong><c:out value="Mensaje" /></strong> <br>
						Email ${token.email} validado <br>
						Recibirá un SMS al teléfono ${token.telefono} con la clave de acceso <br>
						El origen del mensaje será AZARQUIEL<br>
					</p>
				</div>
				<div class="botones">
					<a href="index.jsp" title="inicio"> Pulsa para entrar</a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>