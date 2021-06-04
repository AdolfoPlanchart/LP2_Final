
<!-- referenciar libreria JSTL pata trabajar con la sub-libreria CORE-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
	
	<title>Solicitud</title>
	<style>
		.modal-header{
			color:#fff;
			background: #428bca;
			display: flex;
	  		justify-content: center;
	  		
		}
		.help-block {
		  		color: red;
		}
		.form-group.has-error .form-control-label {
		  color: red;
		}
		.form-group.has-error .form-control {
		  border: 1px solid red;
		  box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
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
  		
  		<h2 class="text-center">Mantenimiento Solicitud</h2>
  		
  		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalDocente">
		  Nuevo Solicitud
		</button>
		
		<table id="tableDocentes" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>CÓDIGO</th>
                <th>NOMBRE</th>
                <th>PATERNO</th>
                <th>MATERNO</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        	<!-- en el atributo items permite recuperar el atributo "docentes" que viene del ServletDocente -->
        	<c:forEach items="${requestScope.docentes}" var="row">
	            <tr>
	                <td>${row.codigo}</td>
	                <td>${row.nombres}</td>
	                <td>${row.paterno}</td>   
	                <td>${row.codigoCondicion}</td>
	                <td><button type="button" class="btn btn-info btn-editar" data-toggle="modal" data-target="#modalDocente">Editar</button></td>
	                <td><button type="button" class="btn btn-danger btn-eliminar" data-toggle="modal" data-target="#modalEliminar">Eliminar</button></td>
	            </tr>
            </c:forEach>
 		</tbody>
    </table>
		
		
		<!-- INICIO - Modal PARA REGISTRAR Y ACTUALIZAR DOCENTE-->
		<div class="modal fade" id="modalDocente" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">DOCENTE</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formDocente"  action="ServletSolicitudCrud?ACCION=REGISTRAR" method="post">
		         <div class="form-group">
				    <label for="exampleInputEmail1">Código</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" value="0" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Fecha</label>
				    <input type="text" class="form-control" id="idNombres" name="nombres" placeholder="Ingresar nombres">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Descripcion</label>
				    <input type="text" class="form-control" id="idPaterno" name="paterno" placeholder="Ingresar apellido paterno">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Condición</label>
				    <select class="form-control" id="idCondicion" name="condicion">
				      <option value=" ">[Seleccione Condición]</option>
				    </select>
				  </div>				  
				  <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">Guardar</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn-cerrar">Cerrar</button>			        
			      </div>
				</form>
		      </div>
		      
		    </div>
		  </div>
		</div>
  		<!-- FIN - Modal PARA REGISTRAR Y ACTUALIZAR DOCENTE-->
  		
		<!-- INICIO - Modal PARA ELIMINAR DOCENTE-->
		<div class="modal fade" id="modalEliminar" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">SISTEMA</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formDocente"  action="ServletSolicitudCrud?ACCION=ELIMINAR" method="post">
		         	    <input type="hidden" class="form-control" id="idCodigoEliminar" name="codigoEliminar">
				  SEGURO DE ELIMINAR ?
				  <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">SI</button>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>			        
			      </div>
				</form>
		      </div>
		      
		    </div>
		  </div>
		</div>
  		<!-- FIN - Modal PARA ELIMINAR DOCENTE-->  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
	</div>
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>


	<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
	
	<script>
		$(document).ready(function() {
		    $('#tableDocentes').DataTable();
		    llenarCondicion();
		} );
	
		//asignar evento click a los botones con clase "btn-editar"
		$(document).on("click",".btn-editar",function(){
			//variables
			var cod,nom,pat,mat,sexo,hijos,sue,codCondi;
			//obtener valores de la fila actual según donde se haga clic al botón editar
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			nom=$(this).parents("tr").find("td")[1].innerHTML;
			pat=$(this).parents("tr").find("td")[2].innerHTML;
			mat=$(this).parents("tr").find("td")[3].innerHTML;
			sexo=$(this).parents("tr").find("td")[4].innerHTML;
			hijos=$(this).parents("tr").find("td")[5].innerHTML;
			sue=$(this).parents("tr").find("td")[6].innerHTML;
			codCondi=$(this).parents("tr").find("td")[7].innerHTML;
			//mostrar los valores de las variables en los controles(cajas y select)
			$("#idCodigo").val(cod);
			$("#idNombres").val(nom);
			$("#idPaterno").val(pat);
			$("#idMaterno").val(mat);
			$("#idSexo").val(sexo);
			$("#idHijos").val(hijos);
			$("#idSueldo").val(sue);
			$("#idCondicion").val(codCondi);
		})
		
		//asignar evento click a los botones con clase "btn-eliminar"
		$(document).on("click",".btn-eliminar",function(){
			//variables
			var cod;
			//obtener código de la fila actual según donde se haga clic al botón editar
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			//
			$("#idCodigoEliminar").val(cod);
		
		})
		//asignar evento click al botón con ID "btn-cerrar"
		$(document).on("click","#btn-cerrar",function(){
			 //resetear validación 
			 $('#formDocente'). data("bootstrapValidator").resetForm(true);
			 //limpiar cajas
			 $('#formDocente').trigger("reset");
			 //asignar "0" a la caja con ID "idCodigo" 
			 $("#idCodigo").val(0);
		})
		
		//implementar función para llenar el select con ID "idCondicion"
		function llenarCondicion(){
			//función en JQUERY que permite leer un JSON
			/*
				la función getJSON tiene 3 parámetros:
				1. llamar al servlet "ServletCondicionJSON"	
				2. parámetro o parámetros que necesita el "ServletCondicionJSON", en este
					caso no esiten parámetros debido a que no existe "request.getParameter"
				3. respuesta del "ServletCondicionJSON"	
			*/
			$.getJSON("ServletTrabajadorJSON",{},function(response){
				//bucle para realizar recorrido sobre "response"
				$.each(response,function(index,item){
					$("#idCondicion").append("<option value='"+item.cod_trabajador+"'>"+item.nom_trabajador+"</option>");
				})
				
			})
			
		}
		
	</script>
	
	
	  
</script>
	
</body>
</html>





