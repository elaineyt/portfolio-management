<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel = "stylesheet" type = "text/css" href="StyleSheet.css"/>
    <link rel="shortcut icon" href="">
	<title>USC CS310 Stock Portfolio Management HomePage</title>
	</head>	
<body>

	
	<div class="topnav" style="padding-top: 2%; padding-bottom: 2.5%;">
		<h1 style="color: #white; font-family:Lato; font-size:250%; padding-left: 7%;">USC CS310 Stock Portfolio Management </h1>
	</div>
	
	
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Create Account</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" role="form" style="display: block;">
									<div id= "loginError" class="errorMessage"></div>
									<div class="form-group">
										<input type="text" name="username" id="login-username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="login-password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="button" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" role="form" style="display: none;">
									<div id="registerError" class="errorMessage"></div>
									<div class="form-group">
										<input type="text" name="username" id="register-username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="register-password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" name="confirm" id="register-confirm" tabindex="2" class="form-control" placeholder="Confirm Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="button" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Create Account">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type = "submit" name="cancel-submit" id="cancel-submit" class="form-control btn btn-cancel  " value= "Cancel">
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
	
   	  	
 	   	<script type="text/javascript">
 	   	
		   	 $(function() {
		
		   	    $('#login-form-link').click(function(e) {
		   			$("#login-form").delay(100).fadeIn(100);
		   	 		$("#register-form").fadeOut(100);
		   			$('#register-form-link').removeClass('active');
		   			$(this).addClass('active');
		   			e.preventDefault();
		   		});
		   		$('#register-form-link').click(function(e) {
		   			$("#register-form").delay(100).fadeIn(100);
		   	 		$("#login-form").fadeOut(100);
		   			$('#login-form-link').removeClass('active');
		   			$(this).addClass('active');
		   			e.preventDefault();
		   		});
		   		
		   		$(document).ready(
					function(){
			   		 	$('#register-submit').click(
				   			function(e){
				   				var username = $('#register-username').val();
				   			   	var password = $('#register-password').val();
				   			   	var confirm = $('#register-confirm').val();
				   			 	
				   	       	 	createUser(username, password, confirm);
				   			} 
				   		);
			   		 	
						$('#login-submit').click(
							function(e){
				   				var username = $('#login-username').val();
				   			   	var password = $('#login-password').val();
				   			 	
				   	       	 	logIn(username, password);
				   			} 
			   		 	);
						
						$('#cancel-submit').click(
							function(e){
					   			$('#register-username').val() = "";
					   			$('#register-password').val() = "";
					   			$('#register-confirm').val() = "";
					   	       	 	
				       			window.location.href = 'http://localhost:8080/index.jsp';

					   		} 
				   		);
						
					}	
			   	);
		
		   	});
		   	 
		   
		   	
		   	//Create user
		   	function createUser(username, password, confirm){
		   		
		   		
		   		const HTTP = new XMLHttpRequest();
		   		const url = "http://localhost:8080/user?username=" + username.toString() + "&password=" + password.toString() + "&confirm=" + confirm.toString();
		   		HTTP.open("POST", url);
		        HTTP.send();
		        
		        HTTP.onreadystatechange = (e) => {
		       		if(HTTP.readyState == 4 && HTTP.status == 200){
		       			var response = HTTP.responseText.toString();
		       			if(response == "{\"Success\": \"Successfully created user.\"}"){
		       				window.location.href = 'http://localhost:8080/index.jsp';
		       			}
		       			else{
		       				if(response == "{\"Error\": \"Creating User. Invalid username.\"}"){
		       					$('#registerError').html("Please provide a username.");
		       				}
		       				else if(response == "{\"Error\": \"Creating User. Invalid password.\"}"){
		       					$('#registerError').html("Please provide a password.");
		       				}
		       				else if(response == "{\"Error\": \"Creating User. Invalid confirm password.\"}"){
		       					$('#registerError').html("Please confirm your password.");
		       				}
		       				else if(response == "{\"Error\": \"Creating User. Passwords don't match.\"}"){
		       					$('#registerError').html("Your passwords did not match.");
		       				}
		       				else if(response == "{\"Error\": \"User already exists.\"}"){
		       					$('#registerError').html("This user already exists.");
		       				}    				
		       				
		       			}
		       			
		       		}
		   		}
		   		  		
		   	}
		   	
			//Login Validation
		   	function logIn(username, password){
				
				
		   		const HTTP = new XMLHttpRequest();
		   		const url = "http://localhost:8080/login?username=" + username.toString() + "&password=" + password.toString();
		        HTTP.onreadystatechange = (e) => {
		        	
		       		if(HTTP.readyState == 4 && HTTP.status == 200){
		       			var response = HTTP.responseText.toString();
		       			
		       			//if login is successful redirect to Main Page, otherwise stay on index.jsp
		       			if(response == "{\"Success\": \"Successfully logged in.\"}"){
		       				window.location.href = 'http://localhost:8080/MainPage.jsp';
		       			}
		       			else{	
		       				if(response == "{\"Error\": \"Failed to log in user. No username provided.\"}"){
		       					$('#loginError').html("Please provide a username.");
		       				}
		       				else if(response == "{\"Error\": \"Failed to log in user. No password provided.\"}"){
		       					$('#loginError').html("Please provide a password.");
		       				}
		       				else if(response == "{\"Error\": \"Failed to log in user. Incorrect password.\"}"){
		       					$('#loginError').html("Incorrect password.");
		       				}
		       				else if(response == "{\"Error\": \"Failed to log in user. User doesn't exist.\"}"){
		       					$('#loginError').html("Username doesn't exist.");
		       				}
		       				else if(response ==  "{\"Error\": \"Exceeded number of login attempts.\"}") {
		       					$('#loginError').html("Exceeded login attempts wait 1 minute.");
		       				}
		       				else if(response == "{\"Error\": \"Have not waited full minute since lockout.\"}") {
		       					$('#loginError').html("Please wait full minute.");
		       				}
		       			}
		       		}
		   		}	
		        HTTP.open("POST", url);
		        HTTP.send();
		   	}
		   		
		   		
   	   	</script>	    
</body>
</html>