<jsp:include page="/menu.jsp"></jsp:include>
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
		<!-- INICIO - Modal PARA REGISTRAR Y ACTUALIZAR DOCENTE-->
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">Solicitud</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formDocente"  action="ServletSolicitudCrud?ACCION=REGISTRAR" method="post">
		         <div class="form-group"  style="display: none;">
				    <label for="exampleInputEmail1">Código de Solicitud</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" value="0" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Nombre Trabajador</label>
				    <input type="text" class="form-control" id="idnombre" name="nombre" value="${sessionScope.DATOS.getNom_trabajador()} ${sessionScope.DATOS.getApe_pat_trabajador()}" readonly>
				    <input type="text" class="form-control" id="idTrabajador" name="codTrabajador" value="0" readonly  style="display: none;">
				  </div>	
				  <div class="form-group">
				    <label for="exampleInputEmail1">Fecha</label>
				    <input type="text" class="form-control" id="idNombres" name="fecha" placeholder="Ingresar nombres">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Descripcion</label>
				    <input type="text" class="form-control" id="idPaterno" name="des" placeholder="Descripcion de Solicitud">
				  </div>
				  <div class="form-group" >
				    <select class="form-control" id="idtrabajador" name="estado" readonly style="display: none;">
				      <option value="Pendiente">Pendiente</option>
				    </select>
				  </div>			  
				  <div class="modal-footer">
			        <button type="submit" class="btn btn-primary">Guardar</button>		        
			      </div>
				</form>
		      </div>
		      
		    </div>
		  </div>
		</div>
  		<!-- FIN - Modal PARA REGISTRAR Y ACTUALIZAR DOCENTE-->
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
		    
		    $('#idTrabajador').val("${sessionScope.DATOS.getCod_trabajador()}")
		} );
	
		//asignar evento click a los botones con clase "btn-editar"
		$(document).on("click",".btn-editar",function(){
			//variables
			var cod,nom,pat,mat;
			//obtener valores de la fila actual según donde se haga clic al botón editar
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			nom=$(this).parents("tr").find("td")[1].innerHTML;
			pat=$(this).parents("tr").find("td")[2].innerHTML;
			mat=$(this).parents("tr").find("td")[3].innerHTML;
			
			//mostrar los valores de las variables en los controles(cajas y select)
			$("#idCodigo").val(cod);
			$("#idNombres").val(nom);
			$("#idPaterno").val(pat);
			$("#idMaterno").val(mat);
			
		
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
					$("#idtrabajador").append("<option value='"+item.cod_trabajador+"'>"+item.nom_trabajador+"</option>");
				})
				
			})
			
		}
		
	</script>
	
	
	  
</script>
	
</body>
</html>





