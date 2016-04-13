<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="description" content="Supermarket Manegement System">
    <title>Login</title>
    <link href="https://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/examples/signin/signin.css" rel="stylesheet">
  </head>

  <body>
	<div class="container">
	  <form class="form-signin" action="Login" method="post">
	    <h2 class="form-signin-heading"> Please sign in</h2>
	    <label for="inputUsername" class="sr-only">Username</label>
	    <input type="username" id="inputUsername" class="form-control" placeholder="username" required="" autofocus="" name="username">
	    <label for="inputPassword" class="sr-only">Password</label>
	    <input type="password" id="inputPassword" class="form-control" placeholder="password" required="" name="password">
	    <button class="btn btn-large btn-primary" type="submit">Sign in</button>
	  </form>
	</div>
  </body>
</html>