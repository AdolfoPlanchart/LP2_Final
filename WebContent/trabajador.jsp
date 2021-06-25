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
	
	<title>Trabajadores - CRUD</title>
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
	<script>
		let parametros = new URLSearchParams(window.location.search);
		if(!parametros.has('ACCION')) {
			window.location.href='ServletTrabajador?ACCION=LISTAR';
		}
	</script>
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
  		
  		<h2 class="text-center">Listado de Trabajadores</h2>
  		<hr/>
  		<div class="form-inline">
	  		<!-- Form filtrar -->
	  		<form class="form-inline" action="ServletTrabajador?ACCION=LISTARxCARGO" method="POST" id="frmFiltrarxCargo">
			  <div class="form-group sm-1">
			    <input type="text" readonly class="form-control-plaintext" value="Seleccionar cargo: ">
			  </div>
			  <div class="form-group mx-sm-4 mb-2">
			    <select class="form-control comboCargo" id="filtrarPorCargo" name="filtroCargo">
				    <option value=" ">[Seleccione Cargo]</option>
				</select>
			  </div>
			  <button type="submit" class="btn btn-success mb-2 mx-sm-3">Consultar por Cargo</button>
			  <!-- Button trigger modal -->
			  <button type="button" class="btn btn-primary mx-sm-3 mb-2" data-toggle="modal" data-target="#modalTrabajador">
					Nuevo Trabajador
			  </button>
			</form>
			<c:if test="${requestScope.filtrando}">
	  			<form class="form-inline mx-sm-3 mb-2" action="ServletTrabajador?ACCION=LISTAR" method="POST">
	  		  		<button type="submit" class="btn btn-warning">Listar todos los trabajadores</button>
			  	</form>
			</c:if>
	  		<!-- Finish form filtrar -->  
	  	</div>
	  	
		<table id="tableTrabajadores" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>CÓDIGO</th>
                <th>NOMBRE</th>
                <th>PATERNO</th>
                <th>MATERNO</th>
                <th>DIRECCION</th>
                <th>DNI</th>
                <th>CARGO</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        	<!-- en el atributo items permite recuperar el atributo "trabajadores" que viene del ServletTrabajador -->
        	<c:forEach items="${requestScope.trabajadores}" var="row">
	            <tr>
	                <td>${row.cod_trabajador}</td>
	                <td>${row.nom_trabajador}</td>
	                <td>${row.ape_pat_trabajador}</td>
	                <td>${row.ape_mat_trabajador}</td>
	                <td>${row.dir_trabajador}</td>
	                <td>${row.dni_trabajador}</td>
	                <td data-codigo="${row.cod_cargo}">${row.cargo}</td>
	                <td><button type="button" class="btn btn-info btn-editar" data-toggle="modal" data-target="#modalTrabajador">Editar</button></td>
	                <td><button type="button" class="btn btn-danger btn-eliminar" data-toggle="modal" data-target="#modalEliminar">Eliminar</button></td>
	            </tr>
            </c:forEach>
 		</tbody>
    </table>

		
		<!-- INICIO - Modal PARA REGISTRAR Y ACTUALIZAR TRABAJADORES-->
		<div class="modal fade" id="modalTrabajador" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">TRABAJADOR</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="formTrabajador"  action="ServletTrabajador?ACCION=GUARDAR" method="post">
		         <div class="form-group">
				    <label for="exampleInputEmail1">Código</label>
				    <input type="text" class="form-control" id="idCodigo" name="codigo" value="0" readonly>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Nombre</label>
				    <input type="text" class="form-control" id="idNombre" name="nombre" placeholder="Ingresar nombre">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Paterno</label>
				    <input type="text" class="form-control" id="idPaterno" name="paterno" placeholder="Ingresar apellido paterno">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Materno</label>
				    <input type="text" class="form-control" id="idMaterno" name="materno" placeholder="Ingresar apellido materno">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Direccion</label>
				    <input type="text" class="form-control" id="idDireccion" name="direccion" placeholder="Ingresar Direccion">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">DNI</label>
				    <input type="text" class="form-control" id="idDNI" name="dni" placeholder="Ingresar DNI">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Cargo</label>
				    <select class="form-control comboCargo" name="cargo">
				      <option value=" ">[Seleccione Cargo]</option>
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
  		<!-- FIN - Modal PARA REGISTRAR Y ACTUALIZAR TRABAJADORES-->
  		
		<!-- INICIO - Modal PARA ELIMINAR TRABAJADORES-->
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
		        <form id="formTrabajadorCerrar"  action="ServletTrabajador?ACCION=ELIMINAR" method="post">
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
  		<!-- FIN - Modal PARA ELIMINAR TRABAJADORES-->  		
  		
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
		    $('#tableTrabajadores').DataTable({
		    	searching: false,
		    	info: false,
		    	dom: "<bottom<'row'<'col-sm-6 col-md-5'l><'col-sm-6 col-md-7'p>>>",
		    	language:{
		    		lengthMenu: 'Mostrar _MENU_ registros por pagina.',
		    		zeroRecords: 'No hay registros',
		    		paginate:{
		    			first: 'Primero',
		    			last: 'Ultimo',
		    			next: 'Siguiente',
		    			previous: 'Anterior'
		    		}
		    	}
		    });
		    llenarCargo();
		} );
		//asignar evento click a los botones con clase "btn-editar"
		$(document).on("click",".btn-editar",function(){
			//variables
			var cod,nom,pat,mat,dir,dni,cargo;
			//obtener valores de la fila actual según donde se haga clic al botón editar
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			nom=$(this).parents("tr").find("td")[1].innerHTML;
			pat=$(this).parents("tr").find("td")[2].innerHTML;
			mat=$(this).parents("tr").find("td")[3].innerHTML;
			dir=$(this).parents("tr").find("td")[4].innerHTML;
			dni=$(this).parents("tr").find("td")[5].innerHTML;
			cargo=$(this).parents("tr").find("td")[6].getAttribute("data-codigo");
			console.log(cargo);
			//mostrar los valores de las variables en los controles(cajas y select)
			$("#idCodigo").val(cod);
			$("#idNombre").val(nom);
			$("#idPaterno").val(pat);
			$("#idMaterno").val(mat);
			$("#idDireccion").val(dir);
			$("#idDNI").val(dni);
			$("#idCargo").val(cargo);
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
			 $('#formTrabajador').data("bootstrapValidator").resetForm(true);
			 //limpiar cajas
			 $('#formTrabajador').trigger("reset");
			 //asignar "0" a la caja con ID "idCodigo" 
			 $("#idCodigo").val(0);
		})
		function llenarCargo(){
			$.getJSON("ServletCargoJSON",{},function(response){
				//bucle para realizar recorrido sobre "response"
				$.each(response,function(index,item){
					$(".comboCargo").append("<option value='"+item.cod_cargo+"'>"+item.desc_cargo+"</option>");
					console.log(item);
				})
			})	
		}
	</script>
	
	
	<script type="text/javascript">    
    $(document).ready(function(){
    	$('#frmFiltrarxCargo').bootstrapValidator({
    		fields:{
    			filtroCargo:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:' '
    		 			}
    		 		}
    		 	}	 
    		}
    	});
        $('#formTrabajador').bootstrapValidator({      
        	 fields:{
        		 	nombre:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo nombre es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,40}$/,
        		 				message:'Campo nombre solo letras mínimo 3 y máximo 40'
        		 			}
        		 		}
        		 	},
        		 	paterno:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo apellido paterno es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,40}$/,
        		 				message:'Campo apellido paterno solo letras mínimo 3 y máximo 40'
        		 			}
        		 		}
        		 	},
        		 	materno:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo apellido materno es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\O\Ú]{3,40}$/,
        		 				message:'Campo apellido materno solo letras mínimo 3 y máximo 40'
        		 			}
        		 		}
        		 	},
        		 	direccion:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo direccion es obligatorio'
        		 			},
        		 			regexp:{
        		 				regexp:/^[a-zA-Z0-9\s\ñ\Ñ\.\,]{5,60}$/,
        		 				message:'Campo direccion minimo 5 caracteres maximo 60'
        		 			}
        		 		}
        		 	},
        		 	dni:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo DNI es obligatorio<br/>'
        		 			},
        		 			digits:{
        		 				message:'Campo DNI solo numeros.<br/>'
        		 			},
        		 			regexp:{
        		 				regexp:/^[0-9]{8}$/,
        		 				message:'Campo DNI debe contener 8 numeros exactamente.'
        		 			}
        		 		}
        		 	},
        		 	cargo:{
        		 		validators:{
        		 			notEmpty:{
        		 				message:'Campo Cargo es obligatorio'
        		 			}
        		 		}
        		 	}	 	
        	 }
        });   
			
    });    
</script>
	
</body>
</html>





