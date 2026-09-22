<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.InvestmentsModel" %>
<%@ page import="java.util.ArrayList" %>

<%
@SuppressWarnings("unchecked")
ArrayList<InvestmentsModel> list = (ArrayList<InvestmentsModel>) request.getAttribute("investments");

if (list == null) {
   list = new ArrayList<InvestmentsModel>();
}
%>


<!DOCTYPE html>
 <html lang="pt-BR">
 <head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Aplicações</title>
   <link  rel="stylesheet"  href="${pageContext.request.contextPath}/assets/css/style.css">
   <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>
  </head>
    
 <body>
 
 
 <header>

    <div class="bank-header">
       <div>
         <img
            id="bank-img"
            src=""
            alt="Logo do banco"
            class="bank-img">
         <span id="bank-name"></span>
     </div>
  </div>

  <div id="container-btn">
     <div>
        <a
            href="${pageContext.request.contextPath}/main"
            id="btn-default">
            Início
        </a>
     </div>

    <div>
        <form
            action="${pageContext.request.contextPath}/logout"
            method="POST"
            style="display:inline;">

            <button type="submit" id="btn-default">Sair</button>
        </form>
    </div>

 </div>

</header> 







	<main>
	
	  <input type="hidden" name="bank-source" id="bank-source">
	  
	  <h2>Aplicações disponíveis para resgate</h2>

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
			   <%
                  if (list.isEmpty()) {
               %>
			
			    <tr>
                   <td colspan="6">
                      Nenhuma aplicação disponível para resgate.
                  </td>
               </tr>
               
                <%
                  } else {
                    for (InvestmentsModel investment : list) {
                 %>
                                    
                  <tr>
                    <td>
                       <%= investment.getBroker() %>
                    </td>
                    <td>
                       <%= investment.getType() %>
                    </td>
                    <td>
                       <%= investment.getOpen() %>
                   </td>
                    <td>
                      <%= investment.getRateType() %>
                   </td>
                   <td>
                      <%= investment.getRate() %>%
                   </td>
                    <td>                      
                                        
                    <a href="${pageContext.request.contextPath}/selectInvest?idInv=<%=investment.getId()%>">
                      <span>Selecionar</span>
                    </a>
                    </td>
                </tr>                
               <%
                }
              }
             %>
        </tbody>
    </table>            
            	
				
</main>


	<footer>
	    <div>
          <a
             href="https://github.com/RonaldoFagundes"
             target="_blank"
             rel="noopener noreferrer">
            Developed by RFagundes
         </a>
      </div>
      <div>
        <span class="version">v1.5.26</span>
       </div>
	</footer>

</body>

</html>