<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CONSULTA</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
</head>
<body>
	<div class="container">
			<c:if test="${requestScope.MENSAJE!=null}">
				<div class="alert alert-warning alert-dismissible fade show" role="alert">
				  <strong>${requestScope.MENSAJE}</strong>
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
			</c:if>

		<h2 class="text-center">Consulta de Solicitudes x trabajador</h2>
		<form>
		   <div class="form-row mt-4">
			    <div class="col-auto">
			       <label for="exampleInputPassword1">Ingresar Solicitud</label>
			    </div>
			    <div class="col-auto">
			      	 <input type="text" class="form-control" id="idNumSucur">
			    </div>
			    <div class="col-auto">
			      <button type="button" class="btn btn-primary mb-2" id="btn-consultar">Consultar</button>
			    </div>
		  </div>
		</form>
		
		<div class="mt-4">
			<table id="tableSucursal" class="table table-striped table-bordered" style="width:100%">
		        <thead>
		            <tr>
		                <th>CÓDIGO</th>
		                <th>FECHA DE LA SOLICITUD</th>
		                <th>DESCRIPCION DE LA SOLICITUD</th>
		                <th>ESRADO SOLICITUD</th>
		                <th>TRABAJADOR REGISTRADO</th>
		                <th></th>
		            </tr>
		        </thead>
		        <tbody>
				</tbody>
			</table>	
		</div>
		
		<!-- INICIO - Modal EDITAR-->
		<div class="modal fade" id="modalSucursal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">SUPERVISOR</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formDocente" method="post" action="ServletSolicitudCrud">
				  <div class="form-group">
				    <label for="exampleInputEmail1">Código</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Nombres</label>
				    <input type="text" class="form-control" id="idnombre" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Apellido</label>
				    <input type="text" class="form-control" id="idapellido" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Sucursal</label>
				    <select class="form-control" name="condicion" id="idSucur">
				      <option value=" ">[Seleccione Sucursal]</option>
				    </select>
				  </div>				  
				  
				  <div class="modal-footer">
			      	<button type="submit" class="btn btn-primary">Eliminar</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn-cerrar">Cerrar</button>
			      </div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- FIN - Modal PARA EDITAR-->		
		
		
	</div>
	<!-- liberia completa de JQUERY -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	
	<script>
	Sucursal();
	
		//asignar evento click al botòn con ID "btn-consultar"
		$(document).on("click","#btn-consultar",function(){
			
			//leer caja idNumPisos
			var pisos;
			pisos=$("#idNumSucur").val();
			//limpiar filas dentro del tbody //data-toggle="modal" data-target="#modalMedicamento"
			$("#tableSucursal tbody").empty();
			$.getJSON("ServletSolicitud",{numeroPisos:pisos},function(response){
				$.each(response,function(index,item){
					$("#tableSucursal").append("<tr><td>"+item.codigo+"</td><td>"+item.fecha+"</td><td>"+
							item.descripcion+"</td><td>"+item.estado+"</td><td>"+item.trabajador+
												"</td><td><button type='button' class='btn btn-success btn-datos' data-target='#modalSucursal' data-toggle='modal'>Ver Datos</button></td></tr>");
				})
			})
			
		})
		
		function Sucursal(){
			$.getJSON("ServletTrabajadorJSON",{},function(response){
				//bucle para realizar recorrido sobre "response"
				$.each(response,function(index,item){
					$("#idSucur").append("<option value='"+item.cod_trabajador+"'>"+item.nom_trabajador+"</option>");
				})
			})
		} 
		
		//asignar evento click al botòn con CLASE "btn-datos"
		//asignar evento click al botòn con CLASE "btn-datos"
		$(document).on("click",".btn-datos",function(){
			//obtener valores de la fila seleccionada
			var cod,nom,ape,dni;
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			nom=$(this).parents("tr").find("td")[1].innerHTML;
			ape=$(this).parents("tr").find("td")[2].innerHTML;
			dni=$(this).parents("tr").find("td")[3].innerHTML;
			//mostrar en cajas
			$("#idCodigo").val(cod);
			$("#idnombre").val(nom);
			$("#idapellido").val(ape);
			$("#idSucur").val(dni);
	
		})
		
		
		
	</script>
</body>
</html>
























