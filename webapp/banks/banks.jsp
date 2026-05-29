<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.BankModel"%>
<%@ page import="java.util.ArrayList"%>


<%
@SuppressWarnings("unchecked")
ArrayList<BankModel> list = (ArrayList<BankModel>) request.getAttribute("banks");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Banks</title>
<script src="./js/script.js"></script>
<link rel="stylesheet" href="./style/style.css">
</head>
<body>

	<header>
		<div><h1>Meus Bancos</h1></div>
		<div id="container-btn">
			<a href="" id="btn-default">LogOut</a>
		</div>
	</header>



	<main>


		<div id="container-bancs">

			<table id="table">

				<tbody>
					<%
				for (int i = 0; i < list.size(); i++) {
				%>

					<tr>

						<td><a href="selectBank?idBnk=<%=list.get(i).getId()%>"
							class="bank-select" data-id="<%=list.get(i).getId()%>"
							data-img="<%=list.get(i).getImg()%>"
							data-name="<%=list.get(i).getName()%>"
							onclick="storeBankData(<%=list.get(i).getId()%>, '<%=list.get(i).getImg()%>', '<%=list.get(i).getName()%>', '<%=list.get(i).getContact()%>')">
								<img src="data:image/png;base64,<%=list.get(i).getImg()%>"
								alt="Bank Logo" class="bank-img" /> <span
								class="select-overlay">Select</span>
						</a></td>

						<td><%=list.get(i).getName()%></td>

						<td><a target="blank" href="<%=list.get(i).getContact()%>"><%=list.get(i).getContact()%></a>
						</td>

					</tr>

					<%
				}
				%>

				</tbody>

			</table>

		</div>




		<div>
			<a href="newBank" id="btn-default">Cadastrar novo Banco</a>
		</div>
		

	</main>


	<footer>
	  <div><a href="https://github.com/RonaldoFagundes" target="blank">developed by RFagundes</a></div>  	  
	  <div><span style="color:blue; font-size:1.2rem;">v=>{1.5.26}</span></div>
	</footer>

</body>
</html>