<!DOCTYPE html>
<html lang="en">
<head>
    <%@page pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Login and Register SocialSport</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signup.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="js/signup.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body background="../res/athletics.jpg">
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="inner cover">
					<h1 class="cover-heading">Social Sport</h1>
					<p>El mundo del deporte</p>
				</div>
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Iniciar Sesión</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Registrarse</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="/Servidor/usuarios" method="GET" role="form" style="display: block;">
									<div class="form-group">
										<input type="email" name="emailL" id="email" tabindex="1" class="form-control" placeholder="Email" value="">
									</div>
									<div class="form-group">
										<input type="password" name="contrasenaL" id="contrasena" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember" id="remember">
										<label for="remember"> Recordarme</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Iniciar Sesión">
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="/Servidor/usuarios" method="POST" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username">
									</div>
									<div class="form-group">
										<input type="email" name="emailR" id="email" tabindex="1" class="form-control" placeholder="Email Address">
									</div>
									<div class="form-group">
										<input type="password" name="contrasenaR" id="contrasena" tabindex="1" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="text" name="nombre" id="nombre" tabindex="1" class="form-control" placeholder="Nombre">
									</div>
									<div class="form-group">
										<input type="text" name="apellidos" id="apellidos" tabindex="1" class="form-control" placeholder="Apellidos">
									</div>
									<div class="form-group">
										<input type="date" name="fecha_nacimiento" id="fecha_nacimiento" tabindex="1" class="form-control" placeholder="Fecha de nacimiento">
										<input type="hidden" name="tipo" id="tipo" tabindex="1" class="form-control" value="registro">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Registrarse Ahora">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
