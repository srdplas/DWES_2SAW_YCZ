
<%@page import="java.util.List"%>
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
	List<String> nombres = (List<String>) request.getAttribute("nombres");
	List<String> dnis = (List<String>) request.getAttribute("dnis");
	List<String> sexos = (List<String>) request.getAttribute("sexos");
	List<String> categorias = (List<String>) request.getAttribute("categorias");
	List<String> anyosTrabajados = (List<String>) request.getAttribute("anyosTrabajados");
	%>


	<div class="container mt-2">
		<div
			class="row py-3 w-75 bg-secondary rounded bg-opacity-50 text-light">
			<div class="col">
				<h3>Informacion de todos los Empleados</h3>

				<form action="infoEmpleados" method="POST" class="text-center">
					<div class="form-row">
						<button type="submit" class="btn btn-success mt-3">
							Consultar Empleados</button>


						<button class="btn btn-danger mt-3">
							<a href="/DWES_Tarea2_YCZ/infoEmpleado.jsp"
								style="text-decoration: none; color: white;">Cancelar</a>
						</button>
				</form>
				<c:if test="${nombres!=null}">
					<table class="table table-secondary rounded mt-4">

					<tbody>
						<tr>
							<th scope="row">Nombre</th>
							<c:forEach begin="0" var="nombre" items="${nombres}">
								<td>${nombre}</td>
							</c:forEach>
						</tr>
						<tr>
							<th scope="row">DNI</th>
							<c:forEach begin="0" var="dni" items="${dnis}">

								<td>${dni}</td>

							</c:forEach>
						</tr>
						<tr>
							<th scope="row">Sexo</th>
							<c:forEach begin="0" var="sexo" items="${sexos}">

								<td>${sexo}</td>

							</c:forEach>
						</tr>
						<tr>
							<th scope="row">Categoria</th>
							<c:forEach begin="0" var="categoria" items="${categorias}">
								<td>${categoria}</td>
							</c:forEach>
						</tr>
						<tr>
							<th scope="row">Anyos. Trab</th>
							<c:forEach begin="0" var="anyosT" items="${anyosTrabajados}">
								<td>${anyosT}</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				
				
				</c:if>
			</div>



			<div class="form-group py-3 d-grid gap-2">
				<button class="btn btn-primary mt-3">
					<a href="/DWES_Tarea2_YCZ"
						style="text-decoration: none; color: white;">Volver Inicio</a>
				</button>
			</div>


		</div>
	</div>

</body>

</html>

