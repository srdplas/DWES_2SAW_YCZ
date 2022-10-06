<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="java.time.LocalDate, java.time.DayOfWeek"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Informacion Empleados</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
</head>

<body class="bg-danger bg-opacity-25">
	<%
	String sueldo = " ";
	sueldo = (String) request.getAttribute("sueldo");
	String dni = " ";
	dni = (String) request.getAttribute("dni");
	%>
	<div class="container mt-2">
		<div
			class="row py-3 w-50 bg-secondary rounded bg-opacity-50 text-light">
			<div class="col">
				<form action="salarioEmpleado" method="POST" class="text-center">
					<h3>Informacion de todos los Empleados</h3>
					
		<div class="form-group py-3 d-grid gap-2">
			<button class="btn btn-primary mt-3">
				<a href="/DWES_Tarea2_YCZ"
					style="text-decoration: none; color: white;">Volver Inicio</a></button>
		</div>

	
	</div>
	</div>

</body>

</html>

