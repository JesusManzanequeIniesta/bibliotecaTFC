<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nuevo Socio</title>
   	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
   	<script>
       function onSubmit(token) {
         document.getElementById("frmSocio").submit();
       }
     </script>
	<jsp:directive.include file="includes/includefile.jspf" />
</head>
<body>
	<br>
	<div id="container">
		<div id="header"></div>
		<c:if test="${requestScope.error != null}">
			<div id="diverror">
				<p>
					<strong><c:out value="Error" /></strong> <br>
					<c:out value="${requestScope.error}" />
					<c:remove var="error" scope="session" />
				</p>
			</div>
		</c:if>
		<div id="divAltaSocio" class="formulariogeneral">
			<form name="frmSocio" method="post" id="frmSocio"
				action="${pageContext.request.contextPath}/controller_sinvalidacioncorreo">
				<fieldset id="datosSocio">
					<legend>
						<img src="resources/img/azarquiel.gif">&nbsp;Nuevo Socio
					</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
					</div>
					<div class="campos">
						<input type="text" id="nombre" name="nombre" required/>
					</div>
					<div class="etiquetas">
						<label for="email">Email:</label>
					</div>
					<div class="campos">
						<input type="email" id="email" name="email" required/>
					</div>
                                        <div class="etiquetas">
						<label for="clave">Clave:</label>
					</div>
					<div class="campos">
						<input type="text" id="password" name="password" required/>
					</div>
					<div class="etiquetas">
						<label for="direccion">Teléfono móvil español:</label>
					</div>
					<div class="campos">
						<input type="tel" id="telefono" name="telefono" 
							pattern="[6-7][0-9]{8}" placeholder="647628264" required/> 
						<input name="operacion" type="hidden" id="operacion" value="registrarse">
					</div>
					<div class="cb"></div>
					<div class="botones">
						<button class="g-recaptcha" data-sitekey="6LdMoW8dAAAAABGkv1R5SdopL17FMcmsfQ2VqVdw" 
						data-callback="onSubmit">Alta</button>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="separacion">
			<br>
		</div>
	</div>
</html>