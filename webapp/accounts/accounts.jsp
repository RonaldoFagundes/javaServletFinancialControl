<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.AccountsModel"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<AccountsModel> list = (ArrayList<AccountsModel>) request.getAttribute("accounts");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accounts</title>
<script src="./js/script.js"></script>
<link rel="stylesheet" href="./style/style.css">
</head>
<body>






	<header>
		<div>
			<img id="bank-img" src="" alt="Bank Logo" /> <span id="bank-name"></span>
		</div>
		<div id="container-btn">
			<a href="" id="btn-default">LogOut</a>
		</div>		
	</header>




	<main>
	
	   <input type="hidden" name="bank-source" id="bank-source">	

		<div class="account-select-container">

			<label for="accountSelect">Escolha uma Conta:</label> <select
				id="accountSelect">
				<option value="">-- Selecionar --</option>

				<%
				for (AccountsModel acc : list) {
				%>
				<option value="<%=acc.getId()%>" 
				    data-bank="<%=acc.getBank()%>"
				    data-number="<%=acc.getNumber()%>"
					data-type="<%=acc.getType()%>" 					
					data-amount="<%=acc.getBalance()%>"
					data-fk="<%=acc.getFkbnk()%>">

					<%=acc.getNumber()%> -
					<%=acc.getType()%>

				</option>
				<%
				}
				%>


			</select>
		</div>



		<div id="accountDetails"></div>


		<div id="container-sevice"></div>

	</main>


	<footer>
	  <div><a href="https://github.com/RonaldoFagundes" target="blank">developed by RFagundes</a></div>  	  
	  <div><span style="color:blue; font-size:1.2rem;">v=>{1.5.26}</span></div>
	</footer>



	<script>
		window.onload = onloadData();
		/*
		 window.onload = function() {
		     const bank = getStoredBankData();
		     
		     if (bank) {
		         document.getElementById("bank-name").innerText = bank.name;
		         document.getElementById("bank-img").src = "data:image/png;base64," + bank.img;                
		     }
		 };
		 */
	</script>


</body>
</html>