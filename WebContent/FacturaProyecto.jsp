<jsp:include page="/menu.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FacturarProyecto</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css">
<style type="text/css">
	.card-header{
		color:#fff;
		background: #428bca;
		display: flex;
  		/*justify-content: center;*/
  		
	}
	.modal-header{
		color:#fff;
		background: #428bca;
		display: flex;
  		justify-content: center;
  		
	}
	.modal-lg, .modal-xl {
		max-width: 1200px;
		max-height: 600px;
	}
	.dataTables_length,.dataTables_info{
	display: none;
	}   
}
</style>
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
	
		<h3 class="text-center mt-4">Lista de Proyectos Facturados</h3>
		<div class="mt-4">
			<div class="card">
			  <div class="card-header">
			    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" id="btnNuevo">Nueva Factura</button>
			  </div>
			  <div class="card-body">
			    	<table id="tableRequerimientos" class="table table-striped table-bordered">
					     <thead>
					        <tr>
							<th width="20%">Código de Proyecto</th>
							<th width="20%">Fecha de Facturacion</th>
							<th width="30%">Descripcion de proyecto</th>
							<th width="50%">Documentos Adjuntados</th>
							<th width="30%">Estado</th>
							
							</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
			  </div>
			</div>
		</div>
		
		<!-- begin #modal-dialog : MODAL GENERAR NUEVO REQUERIMIENTO-->
		<div class="modal fade" id="exampleModal" data-backdrop="static" 
							data-keyboard="false" tabindex="-1" 
							aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg" >					
							<div class="modal-content">
								<div class="modal-header bg-blue-darker">
									<h4 class="modal-title text-white">Nueva Proyecto</h4>
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								</div>
								<div class="modal-body">
									<form action="ServletGenerarProyecto?accion=GENERAR" method="POST">	
										<div class="container">
											<div class="row">																											
												<div class="col-md-4">
													<div class="form-group">
														<label for="exampleInputEmail1"><b>Codigo de Proyecto</b></label>
														<input type="text" class="form-control" name="CodigoPro" placeholder="" readonly value="#00234">
													</div>
													<div class="form-group">
														<label><b>Trabajador</b></label>					
														<select class="form-control"   id="idTrabajador" name="trabajador")>					
															<option>Seleccionar Trabajador</option>						
														</select>				
													</div>																												
												</div>
												<div class="col-md-4">
													<div class="form-group" >
														<label for="exampleInputEmail1"><b>Descripcion de Proyecto</b></label>
														<input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Ejemplo:Expediente de Computo">			
													</div>													
													<div class="form-group">
														<label><b>Cargo</b></label>					
														<input type="text" class="form-control" name="cargo" placeholder="Gerente" readonly name="">			
													</div>																												
												</div>
												<div class="col-md-4">
													<div class="form-group ">
														<label><b>Fecha de creacion</b></label>
														<input type="text" class="form-control"  value = ""  readonly name="fecha">													
													</div>		
													<div class="form-group">
														<label><b>Moneda</b></label>					
														<input type="text" class="form-control" name="moneda" placeholder="Sol Peruano" readonly name="">			
													</div>	
												</div>
												<div class="col-md-6">	
													<label for="exampleInputEmail1" class="text-center"><b>Archivos Adjuntados</b></label>
													<input type="text" class="form-control" id="idArchivos" name="Archivos" placeholder="Ejemplo: Adjunto PDF, Word,Excel">
													<label for="exampleInputEmail1" class="mt-3"><b>Listado de Expedientes</b></label>
													<table id="tableExpedientes" class="table table-striped table-bordered mt-3">
												        <thead>
												            <tr>
												                <th width="5%">Código</th>
												                <th width="95%">Descripcion expediente</th>
												                <th></th>
												            </tr>
												        </thead>
												        <tbody>
		
												        </tbody>
												    </table>
											    </div>	
											    <div class="col-md-6">	
													<label for="exampleInputEmail1"><b>Detalle del Proyecto</b></label>
													<tr>3
													<table id="tableDetalle" class="table table-striped table-bordered mt-1">
												        <thead>
												            <tr>
												                <th width="5%">Codigo</th>
												                <th width="80%">Descripcion</th>
												            <th></th>
												            </tr>
												        </thead>
												        <tbody>
												        </tbody>
												   </table>
											    </div>	
											</div>
										</div>								
										<div class="col-md-12 text-center mt-4"> 										
											<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" id="btnNuevo">Generar</button>
											<button type="button" id="Cancelar" class="btn btn-danger" data-dismiss="modal"><i class="fas fa-undo-alt"></i> Cancelar</button>
										</div> 																								
									</form>
								</div>
							</div>
						</div>
		</div>
		<!-- FIN Modal -->			
	</div>
	
<!-- JS de Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<!-- JS de Bootstrapvalidator -->
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
<!-- JS de la tabla -->
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
<!-- JS ICONOS -->
<script src="https://kit.fontawesome.com/08aaa156fb.js" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
	llenarTrabajador();
	cargarExpedientes();
});

function cargarExpedientes(){
	$.getJSON("ServletExpedienteJSON",{},function(response){
		$.each(response,function(index,item){
			console.log(item.codigo);
			$("#tableExpedientes").append("<tr><td>"+item.codigo+"</td><td>"+item.descripcion+"</td><td>"+
					"<button type='button' class='btn btn-success btn-agregar'>Agregar</button>");

		})
		$('#tableExpedientes').DataTable();
	})
}
//asignar evento click a los botones con clase btn-AGREGAR
$(document).on("click",".btn-agregar",function(){
	//variables
	var cod,des;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	des=$(this).parents("tr").find("td")[1].innerHTML;
	$("#tableDetalle tbody").empty();
	$.getJSON("ServletGenerarProyecto",{accion:"AGREGAR",codigo:cod,descripcion:des},function(response){
		$.each(response,function(index,item){
			console.log(response);
			$("#tableDetalle").append("<tr><td>"+item.cod_resolu+"</td><td>"+item.desc_expediente+"</td><td>"+
					"<button type='button' class='btn btn-danger btn-eliminar'>Eliminar</button>");
		})
	})
	
})
//asignar evento click a los botones con clase btn-AGREGAR
$(document).on("click",".btn-eliminar",function(){
	//variables
	var cod;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	$("#tableDetalle tbody").empty();
	$.getJSON("ServletGenerarProyecto",{accion:"ELIMINAR",cod_resolu:cod},function(response){
		$.each(response,function(index,item){
			console.log(response);
			$("#tableDetalle").append("<tr><td>"+item.cod_resolu+"</td><td>"+item.cod_expedient+"</td><td>"+
					"<button type='button' class='btn btn-danger btn-eliminar'>Eliminar</button>");
		})
	})
	
})












function llenarTrabajador(){
	$.getJSON("ServletTrabajadorJSON",{},function(response){
		$.each(response,function(index,item){
			$("#idTrabajador").append("<option value='"+item.cod_trabajador+"'>"+item.nom_trabajador+" "+item.ape_pat_trabajador+"</option>");
			console.log(item.nom_trabajador);
		})
	})
}







/*
*/





</script>
</body>
</html>










