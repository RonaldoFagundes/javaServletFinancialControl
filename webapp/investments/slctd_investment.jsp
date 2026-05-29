<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="model.InvestmentsModel"%>

<%
    InvestmentsModel invm = (InvestmentsModel) request.getAttribute("investment");
  %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Investment</title>
<script src="./js/script.js"></script>
<script src="./js/validation.js"></script>
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

		<div id="container-slctd-invest">


			<h2>Details</h2>

			<p>
				Id :
				<%=invm.getId()%></p>

			<p>
				Broker :
				<%= invm.getBroker()%></p>

			<p>
				Type :
				<%= invm.getType()%></p>

			<p>
				Open :<%= invm.getOpen()%></p>

			<p>
				Expery :
				<%= invm.getExpery()%></p>

			<p>
				RateType :
				<%= invm.getRateType()%>
			</p>

			<p>
				Rate :<%= invm.getRate()%></p>

			<p>
				Value:
				<%= invm.getValue()%></p>

			<p>
				Profitability:
				<%= invm.getProfitability()%></p>

			<p>
				Rescue:
				<%= invm.getRescue()%></p>

			<p>
				Amount:
				<%= invm.getAmount()%></p>


			<div>
				<a href="" id="btn-default">Abrir Modal form</a>
			</div>

		</div>






		<div>
			<form name="frmRescue"
				action="creatRescue?idInv=<%=invm.getId()%>&fkBka=<%=invm.getFkBka()%>&broker=<%=invm.getBroker()%>&open=<%=invm.getOpen()%>&type=<%=invm.getType()%>"
				method="POST" class="rescue-form" id="frmRescue">

				<h2>Rescue</h2>


				<div class="input-group">
					<span>Date</span><input type="date" name="rescue-date"> <span
						class="error-msg" id="errorDate"></span>
				</div>

				<div class="input-group">
					<input type="text" name="rescue" placeholder="Value"> <span
						class="error-msg" id="erroValue"></span>
				</div>



				<div class="input-group">
					<input type="text" name="desc" placeholder="Desc"> <span
						class="error-msg" id="erroDesc"></span>
				</div>


				<div class="input-group">
					<button type="button" onclick="validateRescue()">Resgatar</button>
				</div>


				<div id="errorContainer" style="color: red;">
					<% if(request.getAttribute("error") != null){ %>
					<%= request.getAttribute("error") %>
					<% } %>
				</div>


			</form>

		</div>



	</main>


	<footer>
	 <div><a href="https://github.com/RonaldoFagundes" target="blank">developed by RFagundes</a></div>  	  
     <div><span style="color:blue; font-size:1.2rem;">v=>{1.5.26}</span></div>
	</footer>



	<script>
		window.onload = onloadData();		
	</script>


</body>
</html>