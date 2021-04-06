<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
<meta charset="UTF-8">
<title>Login</title>
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
                <li class="nav-item active">
                <a class="nav-link" href="#">Iniciar Sesión<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Registrate</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Login form -->
    <div class="container h-100 d-flex justify-content-center align-items-center">
        <div class="form-bg p-4 border rounded">
            <form action="ServletLogin" method="post">
            	<h1>
					${msg}
				</h1>
                <h4>Iniciar Sesión</h4>
                <hr/>
                <div class="form-group">
                    <label for="email">Correo Electronico</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Correo Electronico">
                    <small id="emailHelp" class="form-text text-muted">No compartiremos tu correo electronico con nadie.</small>
                </div>
                <div class="form-group">
                    <label for="psw">Contraseña</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="psw" name="psw" placeholder="Contraseña">
                        <div class="input-group-append">
                            <a href="#" class="input-group-text" id="revelaPassword" onclick=revelaPassword()><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="recuerda">
                    <label class="form-check-label" for="recuerda">Recuerdame</label>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Iniciar Sesión">
                <hr/>
                <p class="text-muted">No tienes una cuenta? <a href="#">Registrate aquí</a>.</p>
            </form>
        </div>
    </div>
    <h1>HOLA MUNDO</h1>
    <div class="footer">
        <div class="container h-100 d-flex justify-content-center align-items-center">
            <p class="text-muted">© Copyright 2020 - 2021 by Webflix. All Rights Reserved.</p>
        </div>
    </div>
    
    <!-- Incluimos tools.js -->
    <script src="js/tools.js"></script>
    
    <!-- Incluyendo librerias JS necesarias para Bootstrap -->
    <script src="js/jquery-3.5.1.slim.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>