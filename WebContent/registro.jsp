<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Registro</title>
</head>
<body>
	<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            Proyecto LP2
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                <a class="nav-link" href="#">Iniciar Sesión<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                <a class="nav-link" href="#">Registrate</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Login form -->
    <div class="container h-100 d-flex justify-content-center align-items-center">
        <div class="mw-50 form-bg p-4 border rounded">
            <form>
                <h4>Ingresa tus datos</h4>
                <hr/>
                <div class="form-row">
                    <div class="col-6">
                        <label for="nombre">Nombre(s)</label>
                        <input type="text" class="form-control" id="nombre" placeholder="Pepito">
                    </div>
                    <div class="col-6">
                        <label for="apellido">Apellido(s)</label>
                        <input type="text" class="form-control" id="apellido" placeholder="Perez">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Correo Electronico</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Correo Electronico">
                    <small id="emailHelp" class="form-text text-muted">No compartiremos tu correo electronico con nadie.</small>
                </div>
                <div class="form-group">
                    <label for="psw">Contraseña</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="psw" placeholder="Contraseña">
                        <div class="input-group-append">
                            <a href="#" class="input-group-text" id="revelaPassword" onclick=revelaPassword()><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
                <label for="celular">Número de Teléfono</label>
                <div class="form-row">
                    <div class="form-group col-md-4 col-2">
                        <input type="text" class="form-control text-center" id="codigopais" value='+51'>
                    </div>
                    <div class="form-group col-md-8 col-10">
                        <input type="text" class="form-control" id="celular" placeholder="912345678">
                    </div>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="recuerda">
                    <label class="form-check-label" for="recuerda">Acepto los Términos y Condiciones</label>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Registrame">
                <hr/>
                <p class="text-muted">Ya tienes una cuenta? <a href="#">Inicia Sesión aquí</a>.</p>
            </form>
        </div>
    </div>
    <div class="footer">
        <div class="container h-100 d-flex justify-content-center align-items-center">
            <p class="text-muted">© Copyright 2020 - 2021 by Webflix. All Rights Reserved.</p>
        </div>
    </div>
    <!-- Script para mostrar/ocultar la contraseña -->
    <script src="js/tools.js"></script>
    <!-- Incluyendo librerias necesarias para JS -->
    <script src="js/jquery-3.5.1.slim.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>