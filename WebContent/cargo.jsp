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
	
	<title>Cargos - CRUD</title>
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
  		
  		<h2 class="text-center">Listado de Cargos</h2>
  		
  		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCargo">
		  Nuevo Cargo
		</button>
		
		<table id="tableCargos" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>CÓDIGO</th>
                <th>DESCRIPCION</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        	<!-- en el atributo items permite recuperar el atributo "cargos" que viene del ServletCargo -->
        	<c:forEach items="${requestScope.cargos}" var="row">
	            <tr>
	                <td>${row.cod_cargo}</td>
	                <td>${row.desc_cargo}</td>
	                <td><button type="button" class="btn btn-info btn-editar" data-toggle="modal" data-target="#modalCargo">Editar</button></td>
	                <td><button type="button" class="btn btn-danger btn-eliminar" data-toggle="modal" data-target="#modalEliminar">Eliminar</button></td>
	            </tr>
            </c:forEach>
 		</tbody>
    </table>
		
		
		<!-- INICIO - Modal PARA REGISTRAR Y ACTUALIZAR CARGO-->
		<div class="modal fade" id="modalCargo" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">CARGO</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formCargo"  action="ServletCargo?ACCION=GUARDAR" method="post">
		         <div class="form-group">
				    <label for="exampleInputEmail1">Código</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" value="0" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Descripcion</label>
				    <input type="text" class="form-control" id="idDescripcion" name="descripcion" placeholder="Ingresar descripcion">
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
  		<!-- FIN - Modal PARA REGISTRAR Y ACTUALIZAR CARGO-->
  		
		<!-- INICIO - Modal PARA ELIMINAR CARGO-->
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
		        <form id="formTrabajador"  action="ServletCargo?ACCION=ELIMINAR" method="post">
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
  		<!-- FIN - Modal PARA ELIMINAR CARGO-->  		
  		
	</div>
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>


	<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
	
	<script>
		//window.onload = cargarTrabajadores();
		$(document).ready(function() {
		    $('#tableCargos').DataTable();
		} );
		//asignar evento click a los botones con clase "btn-editar"
		$(document).on("click",".btn-editar",function(){
			//variables
			var cod,desc;
			//obtener valores de la fila actual según donde se haga clic al botón editar
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			desc=$(this).parents("tr").find("td")[1].innerHTML;
			//mostrar los valores de las variables en los controles(cajas y select)
			$("#idCodigo").val(cod);
			$("#idDescripcion").val(desc);
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
			 $('#formCargo').data("bootstrapValidator").resetForm(true);
			 //limpiar cajas
			 $('#formCargo').trigger("reset");
			 //asignar "0" a la caja con ID "idCodigo" 
			 $("#idCodigo").val(0);
		})
	</script>
	
	
	<script type="text/javascript">    
    $(document).ready(function(){     
        $('#formDocente').bootstrapValidator({      
        	 fields:{
        		 	nombres:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo nombre es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,15}$/,
        		 				message:'Campo nombre solo letras mínimo 3 y máximo 15'
        		 			}
        		 		}
        		 	},
        		 	paterno:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo apellido paterno es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,15}$/,
        		 				message:'Campo apellido paterno solo letras mínimo 3 y máximo 15'
        		 			}
        		 		}
        		 	},
        		 	materno:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo apellido materno es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,15}$/,
        		 				message:'Campo apellido materno solo letras mínimo 3 y máximo 15'
        		 			}
        		 		}
        		 	},
        		 	hijos:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo número de hijos es obligatorio'
        		 			},
        		 			digits:{
        		 				message:'Campo número de hijos solo digitos<br>'
        		 			},
        		 			between:{
        		 				min:0,
        		 				max:12,
        		 				message:'Campo número de hijos solo digitos[0-12]'
        		 			}
        		 	
        		 		}
        		 	},
        		 	sueldo:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo sueldo es obligatorio'
        		 			}
        		 		}
        		 	},
        		 	sexo:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo sexo es obligatorio'
        		 			}
        		 		}
        		 	},
        		 	
        		 	
        		 	
        	 }
        });   
			
    });    
</script>
	
</body>
</html>





