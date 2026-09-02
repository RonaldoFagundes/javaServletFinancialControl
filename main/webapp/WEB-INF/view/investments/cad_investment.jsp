<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Investment</title>
<script src="./js/script.js"></script>
<script type="text/javascript" src="./js/validation.js"></script>
<link rel="stylesheet" href="./style/style.css">
</head>
<body>
	<header>
		<div>
			<img id="bank-img" src="" alt="Bank Logo" /> <span id="bank-name"></span>
		</div>
	</header>


	<main>

       <input type="hidden" name="bank-source" id="bank-source">

		<div id="container-form">
			<!--   <form name="frmInvestment" action="cadInvest"> -->
			<div>
				<h2>Aplicar</h2>
			</div>

			<form name="frmInvestment" action="creatInvest" method="POST"
				class="invest-form" id="frmInvestment">

				<div id="content-form">

					<div class="input-group">
						<input type="text" name="broker" placeholder="Broker Name">
						<span class="error-msg" id="errorBroker"></span>
					</div>

					<div class="input-group">
						<input type="text" name="cat" placeholder="Categoria">
						
						 <span	class="error-msg" id="errorCat"></span>
					</div>

					<div class="input-group">
						<input type="text" name="type" placeholder="Type">
						
						 <span class="error-msg" id="errorType"></span>
					</div>

				</div>


				<div id="content-form">

					<div class="input-group">
						<span>Open Date</span><input type="date" name="open">
						
						 <span	class="error-msg" id="errorOpen"></span>
					</div>


					<div class="input-group">
						<span>Expery Date</span><input type="date" name="expery">
						<span class="error-msg" id="errorExpery"></span>
					</div>

				</div>


				<div id="content-form">

					<div class="input-group">
						<input type="text" name="rate_type" placeholder="Tipo taxa">
						<span class="error-msg" id="errorRateType"></span>
					</div>

					<div class="input-group">
						<input type="text" name="rate" placeholder="Taxa">						
						<span	class="error-msg" id="errorRate"></span>
					</div>

				</div>

				<div id="content-form">

					<div class="input-group">
						<input type="text" name="price" placeholder="Valor">
						
						 <span	class="error-msg" id="errorPrice"></span>
					</div>

					<div class="input-group">
						<input type="text" name="desc" placeholder="Desc">
						
						 <span class="error-msg" id="errorDesc"></span>
					</div>

					<div class="input-group">
						<input type="text" name="fk" readonly="readonly" value="<%= request.getAttribute("fkbka")%>"
						
						 placeholder="Idbka <%= request.getAttribute("fkbka") %>">						 
						 
					</div>

				</div>


				<div class="input-group">
					<button type="button" onclick="validateInvestment()">Aplicar</button>
				</div>


				<div id="errorContainer" style="color: red;">
					<%
					if (request.getAttribute("error") != null) {
					%>
					<%=request.getAttribute("error")%>
					<%
					}
					%>
				</div>


			</form>






			<!-- 
		<form name="frmInvestment" action="cadInvest">

			<table>

				<tr>
					<td><input type="text" name="broker" placeholder="Broker Name"></td>
				</tr>

				<tr>
					<td><input type="text" name="cat" placeholder="Categoria"></td>
				</tr>

				<tr>
					<td><input type="text" name="type" placeholder="Type"></td>
				</tr>

				<tr>
					<td><span>Open Date</span><input type="date" name="open"></td>
				</tr>

				<tr>
					<td><span>Expery Date</span><input type="date" name="expery"></td>
				</tr>

				<tr>
					<td><input type="text" name="rate_type"	placeholder="Tipo taxa"></td>
				</tr>

				<tr>
					<td><input type="text" name="rate" placeholder="Taxa"></td>
				</tr>

				<tr>
					<td><input type="text" name="price" placeholder="Valor"></td>
				</tr>

				<tr>
					<td><input type="text" name="desc" placeholder="Desc"></td>
				</tr>

				<tr>
					<td><input type="text" name="fk" placeholder="fk"></td>
				</tr>

			</table>

			<input type="button" value="Add" onclick="validation()">

		</form>
		
	-->


		</div>


	</main>


	<footer></footer>



	<script>
		window.onload = onloadData();
	</script>

</body>
</html>