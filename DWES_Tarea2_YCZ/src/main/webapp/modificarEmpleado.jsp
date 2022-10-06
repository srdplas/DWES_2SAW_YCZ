<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.time.LocalDate, java.time.DayOfWeek"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar datos Empleado</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
</head>

<body class="bg-danger bg-opacity-25">

<%
	String nombre = " ";
	nombre = (String) request.getAttribute("nombre");

	String dni = " ";
	dni = (String) request.getAttribute("dni");
	
	String sexo = " ";
	sexo = (String) request.getAttribute("sexo");
	
	String categoria = "";
	categoria =(String) request.getAttribute("categoria");
	
	String anyos = "";
	anyos = (String) request.getAttribute("anyosTrabajados");
	%>



	<div class="container mt-2">
		<div
			class="row py-3 w-50 bg-secondary rounded bg-opacity-50 text-light">
			<form method="POS" action="modificarDatosEmpleado" class="text-center">
				<h3>Modificar Empleado</h3>
				<div class="form-row">
					<label for="dni">DNI</label><c:choose>
							<c:when test="${dni==null}">
								<input type="text" class="form-control"
									placeholder="Dni del empleado" name="dni" value="">
							</c:when>
							<c:when test="${dni!=null}">
								<input type="text" class="form-control"
									placeholder="Dni del empleado" name="dni" value="<%=dni%>">
							</c:when>

						</c:choose>
					<button type="submit" class="btn btn-success mt-3">
							Consultar Salario</button>
						<button class="btn btn-danger mt-3">
							<a href="/DWES_Tarea2_YCZ/modificarEmpleado.jsp"
								style="text-decoration: none; color: white;">Cancelar</a>
						</button>
				</div>
			</form>
			<c:if test="${nombre!=null&&dni!=null&&sexo!=null&&categoria>0 }">
				<form>
					<div class="form-group py-3">
					<label for="nombreEmpleado">Nombre Empleado:</label> <input
						type="text" class="form-control" id="cambiaNombreEmpleado" value="<%=nombre%>">
				</div>
				<div class="form-group py-3">
					<label for="dniEmpleado">Dni Empleado:</label> <input type="text"
						class="form-control" id="cambiaDniEmpleado" value="<%=dni%>">
				</div>
				<div class="form-group py-3">
					<label for="sexoEmpleado">Sexo Empleado:</label> <input type="text"
						class="form-control" id="cambiaSexoEmpleado" value="<%=sexo%>">
				</div>
				<div class="form-group py-3">
					<label for="categoriaEmpleado">Categoria Empleado:</label> <input
						type="text" class="form-control" id="cambiaCategoriaEmpleado" value="<%=categoria%>">
				</div>
				<div class="form-group py-3">
					<label for="anyosTrabajados">Años Trabajados Empleado:</label> <input
						type="text" class="form-control" id="cambiaAnyosTrabajados" value="<%=anyos%>">
				</div>
				<div class="form-group d-grid gap-2">
					<button type="submit"  class="btn btn-warning text-dark">Actualizar Empleado</button>
				</div>
			</form>
			</c:if>
				<div class="form-group py-3 d-grid gap-2">
					<button  class="btn btn-primary">
						<a href="/DWES_Tarea2_YCZ" style="text-decoration: none; color: white;">Volver Inicio</a>
					</button>
				</div>
			
		</div>
	</div>
</body>

</html>

