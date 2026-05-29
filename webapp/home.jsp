<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.BankModel"%>
<%@ page import="java.util.ArrayList"%>


<%
@SuppressWarnings("unchecked")
ArrayList<BankModel> list =
    (ArrayList<BankModel>) request.getAttribute("banks");
%>



<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Home</title>
<script type="text/javascript" src="js/validation.js"></script>
<link rel="stylesheet" href="style/style.css">
</head>


<body>

	<main>

		<form name="frmLogin" action="login" method="POST" class="login-form" id="frmLogin">
		
			<h2>Login</h2>

			<div class="input-group">
				<input type="text" name="user" placeholder="User">
				 <span class="error-msg" id="errorUser"></span>
			</div>

			<div class="input-group">
				<input type="password" name="password" placeholder="Password">
				<span class="error-msg" id="errorPassword"></span>
			</div>

			<div class="input-group">
				<button type="button" onclick="login()">Logar</button>
			</div>

			<div id="errorContainer" style="color: red;">
				<% if(request.getAttribute("error") != null){ %>
				<%= request.getAttribute("error") %>
				<% } %>
			</div>
		</form>

	</main>




	


</body>
</html>