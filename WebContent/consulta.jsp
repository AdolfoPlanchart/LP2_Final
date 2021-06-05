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
			
		<h2 class="text-center">Consulta de Trabajador por Cargo</h2>
		<form>
		   <div class="form-row mt-4">
			    <div class="col-auto">
			       <label for="exampleInputPassword1">Ingresar Cargo</label>
			    </div>
			    <div class="col-auto">
			      	 <input type="text" class="form-control" id="idCargoTrabajador">
			    </div>
			    <div class="col-auto">
			      <button type="button" class="btn btn-primary mb-2" id="btn-consultar">Consultar</button>
			    </div>
		  </div>
		</form>
		
		<div class="mt-4">
			<table id="tableSupervisor" class="table table-striped table-bordered" style="width:100%">
		        <thead>
		            <tr>
		                <th>CÓDIGO</th>
		                <th>NOMBRE</th>
		                <th>APE.PATERNO</th>
		                <th>APE.MATERNO</th>
		                <th>DIRECCION</th>
		                <th>DNI</th>
		                <th>CARGO</th>
		               <th></th>
		            </tr>
		        </thead>
		        <tbody>
				</tbody>
			</table>	
		</div>
		
		<!-- INICIO - Modal EDITAR-->
		<div class="modal fade" id="modalSupervisor" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">TRABAJADOR</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formDocente" method="post" action="ServletTrabajador">
				  <div class="form-group">
				    <label for="exampleInputEmail1">Código</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Nombre</label>
				    <input type="text" class="form-control" id="idNombre" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Ape.Paterno</label>
				    <input type="text" class="form-control" id="idApe.Paterno" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Ape.Materno</label>
				    <input type="text" class="form-control" id="idApe.Materno" readonly>
				  </div>
				   <div class="form-group">
				    <label for="exampleInputPassword1">Direccion</label>
				    <input type="text" class="form-control" id="idDireccion" readonly>
				  </div>  			 
				  <div class="form-group">
				    <label for="exampleInputPassword1">Dni</label>
				    <input type="text" class="form-control" id="idDni" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Cargo</label>
				    <select class="form-control" name="condicion" id="idCargo">
				      <option value=" ">[Seleccione Cargo]</option>
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
	sucursal();
	
	//asignar evento click al boton con ID "btn-consultar"
	$(document).on("click","#btn-consultar",function(){
		//leer caja idSueldoSupervisor
		var cargos;
		cargos=$("#idCargoTrabajador").val();//variable de la caja
		//limpiar filas dentro del tbody
		$("#tableSupervisor tbody").empty();	
		$.getJSON("ServletTrabajadorPorCargo",{cargoTrabajador:cargos},function(response){
			$.each(response,function(index,item){
				$("#tableSupervisor").append("<tr><td>"+item.cod_trabajador+"</td><td>"+item.nom_trabajador+"</td><td>"+item.ape_pat_trabajador
						+"</td><td>"+item.dni+"</td><td>"+item.ape_mat_trabajador+"</td><td>"+item.dir_trabajador+"</td><td>"+item.dni_trabajador+"</td><td>"+item.cargo+
						"</td><td><button type='button' class='btn btn-success btn-datos' data-target='#modalSupervisor' data-toggle='modal'>Ver Datos</button></td></tr>");
				
			})
		})
	})
	
	function sucursal(){
		$.getJSON("ServletSucursalJSON",{},function(response){
			$.each(response,function(index,item){
				$("#idSucursal").append("<option value='"+item.codigo+"'>"+item.nombre+"</option>");
				
			})
		})
		
	}
	
	//asignar evento click al boton con clase "btn-datos"
	$(document).on("click","#btn-datos",function(){
		//obtener valores de la fila seleccionada
		var cod,nom,ape,dni,hijos,sueldo,sucursal;
		cod=$(this).parents("tr").find("td")[0].innerHTML;
		nom=$(this).parents("tr").find("td")[1].innerHTML;
		ape=$(this).parents("tr").find("td")[2].innerHTML;
		dni=$(this).parents("tr").find("td")[3].innerHTML;
		hijos=$(this).parents("tr").find("td")[4].innerHTML;
		sueldo=$(this).parents("tr").find("td")[5].innerHTML;
		sucursal=$(this).parents("tr").find("td")[6].innerHTML;
		//mostrar en cajas
		$("#idCodigo").val(cod);
		$("#idNombre").val(nom);
		$("#idApellido").val(ape);
		$("#idDni").val(dni);
		$("#idHijos").val(hijos);
		$("#idSueldo").val(sueldo);
		$("#idSucursal").val(sucursal);
		
	}
	
	
	
	
	
	
	
	
	</script>
</body>
</html>



