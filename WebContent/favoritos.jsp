<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Favoritos</title>
</head>
<style>
.boton {
	background-color: white;
	border-radius: 40px;
	border: 2px solid blue;
	width: 100px;
	height: 20px;
	text-align: center;
	margin: 15px;
	padding-bottom: 5px;
	padding-top: 5px;
	width: 100px;
	color: blue;
}

.boton:hover {
	background-color: blue;
	border-radius: 40px;
	border: 2px solid blue;
	width: 100px;
	height: 20px;
	text-align: center;
	margin: 15px;
	padding-bottom: 5px;
	padding-top: 5px;
	color: white;
}

a {
	text-decoration: none;
	color: blue;
}
</style>
<body>
	<%
		out.print("<a href=\"index.jsp\"><div class=\"boton\">Volver</div></a>");
		String tabla = (String) request.getAttribute("fav");
		if (!tabla.equals("null"))
			out.print(tabla);
	%>
</body>
</html>