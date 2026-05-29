<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.InvestmentsModel"%>
<%@ page import="java.util.ArrayList"%>



<%
@SuppressWarnings("unchecked")
ArrayList<InvestmentsModel> list =(ArrayList<InvestmentsModel>) request.getAttribute("investments");
%>





<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Investiments</title>
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

		<table id="table">
		
		
			<thead>			
				<tr>
					<th>BrokerName</th>
					<th>Type</th>
					<th>Open</th>
					<th>RateType</th>
					<th>Rate</th>
					<th>Action</th>						
				</tr>				
			</thead>
			
			
			
			<tbody>
			
				<% for(int i =0; i < list.size(); i++){ %>

				<tr>
				
					<td><%= list.get(i).getBroker()%></td>
					<td><%= list.get(i).getType() %></td>
					<td><%= list.get(i).getOpen()%></td>
					<td><%= list.get(i).getRateType()%></td>
					<td><%= list.get(i).getRate() %>%</td>
					
					<td>
					  <a href="selectInvest?idInv=<%=list.get(i).getId()%>">
					    <span>Select</span>
					  </a>
					</td>
					
				</tr>

				<% } %>

			</tbody>
			
		</table>		

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