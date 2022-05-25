<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error de Aplicación</title>
<jsp:directive.include file="includes/includefile.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="WEB-INF/menu.jspf" />
		</div>
		<c:if test="${error != null}">
			<div id="diverror">
				<p>
					<strong><c:out value="Error" /></strong> <br>
					<c:out value="${error}" />
				</p>
			</div>
		</c:if>
		<c:if test="${traza != null}">
			<div id="diverror">
				<p>
					<strong><c:out value="Traza" /></strong> <br>
					<c:out value="${traza}" />
				</p>
			</div>
		</c:if>
		</div>
</body>
</html>