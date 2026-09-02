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
<title>Transactions</title>
<script src="./js/script.js"></script>
<script type="text/javascript" src="./js/validation.js" defer></script>
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
	
	
	
	
	
	<main id="main-trs">	
	  
	   <div>
	     <h1><%= request.getAttribute("typeTitle")%></h1>     
	   </div>
	   
	   
	      
	     <form name="frmTrs" action="creatTrs" method="POST">     
	     	        
	        <div id="container-account">	        
	          <div id="content-account">	          	        	         	         
	             <div class="input-group">
	                <span>action</span>	         
	                <input type="text" name="trsType" readonly="readonly" value="<%= request.getAttribute("trsType")%>"						
			    	placeholder="<%=request.getAttribute("trsType")%>">				
	            </div>	            
	            <div class="input-group">
	                <span>idAcc</span>	         
	                <input type="text" name="idAcc" readonly="readonly" value="<%= request.getAttribute("idAcc")%>"						
			    	placeholder="<%=request.getAttribute("idAcc")%>">				
	            </div>	                   
	            <div class="input-group">
	                <span>bank</span>	         
	                <input type="text" name="bank" readonly="readonly" value="<%= request.getAttribute("bank")%>"						
			    	placeholder="<%=request.getAttribute("bank")%>">				
	            </div>
	         </div>		         
	            
	        <div id="content-account">	        	                 
	             <div class="input-group">	
	               <span>typeAcc</span>         
	                <input type="text" name="typeAcc" readonly="readonly" value="<%= request.getAttribute("typeAcc")%>"						
			    	placeholder="<%=request.getAttribute("typeAcc")%>">				
	            </div>	                       
	            <div class="input-group">	
	               <span>numberAcc</span>         
	                <input type="text" name="numberAcc" readonly="readonly" value="<%= request.getAttribute("numberAcc")%>"						
			    	placeholder="<%=request.getAttribute("numberAcc")%>">				
	           </div>	           	           
	           <div class="input-group">	
	                <span>amountAcc</span>         
	                <input type="text" name="amountAcc" readonly="readonly" value="<%= request.getAttribute("amountAcc")%>"						
			    	placeholder="<%=request.getAttribute("amountAcc")%>">				
	           </div>	            	              
	        </div>	        
	      </div>  
	              	
	        		
		 <div id="container-trs">
		   <div id="content-option">		   	        
	            <div><span>Transação:</span></div>	            
	            <div>               
                 <label>
                     <input type="radio" name="type" value="Pix" checked>
                     Pix
                 </label>
                </div>                 	
                <div> 	                 
                 <label>
                     <input type="radio" name="type" value="Ted" >
                     Ted
                 </label>
                </div>                          
	        </div>
	        <div id="content-option">	        	        
	            <div><span>Tipo:</span></div>
	            <div>                
                 <label>
                     <input type="radio" name="trf" value="in" checked>
                     in
                 </label>
                </div>                
                <div>	                 
                 <label>
                     <input type="radio" name="trf" value="out" >
                     out
                 </label>
                </div>  
	        </div>
	      </div>
	         
	       <div id="content-source-text">	                	
	           <div class="input-group">
	        	 <input type="text" name="sourceIn" placeholder="Origem">
				 <span class="error-msg" id="errorDesc"></span>
			   </div> 	         
	        </div>	 
	          
	        
	      <div id="container-source-trs">
		   <div id="content-option">		   	        
	            <div><span>Origem:</span></div>	            
	            <div>               
                 <label>
                     <input type="radio" name="source" value="outros" checked>
                     Outros
                 </label>
                </div>                 	
                <div> 	                 
                 <label>
                    <input type="radio" name="source" value="pessoal">
                     Pessoal
                 </label>
                </div>                          
	        </div>  
	      </div>
	      	           
	       <div id="container-forward">	
	              	        	  
	         <div id="content-forward-text">	
	           <div class="input-group">	           
				 <input type="text" name="who" placeholder="Destino">
				 <span class="error-msg" id="errorDesc"></span>				 
			   </div>			  		      
	        </div> 
	                	
	         
	        <div id="content-accounts">
	            <div><span>Escolha uma Conta:</span></div> 
	           <!-- <label for="accountSelect"></label> -->
	            <select
				 id="accountSelect" name="accountSelect">
				 <option value="">-- Selecionar --</option>
				
				 <%
				  for (AccountsModel acc : list) {
				  %>
				 <option value="<%=acc.getId()%>" 
				    data-bank="<%=acc.getBank()%>"
					data-type="<%=acc.getType()%>" 					
					data-number="<%=acc.getNumber()%>"
					data-amount="<%=acc.getAmount()%>">

                    <%=acc.getBank() %>  -
                    <%=acc.getType()%>   -
					<%=acc.getNumber()%> 					

			     </option>
				 <%
				 }
				 %>
			  </select> 
			   <input type="hidden" name="selected-bank" id="selected-bank">
			   <input type="hidden" name="selected-type" id="selected-type">
			   <input type="hidden" name="selected-number" id="selected-number">			   
			   <input type="hidden" name="selected-amount" id="selected-amount">
			   <input type="hidden" name="type-move" id="type-move">
			   
			   <input type="hidden" name="bank-source" id="bank-source">			    
	        </div>	                  
	      </div> 	
	        
	      <div id="container-way"> 	      	      
	          <div id="content-option">	        
	            <div><span>Canal:</span></div> 
	            <div>                
                 <label>
                     <input type="radio" name="form" value="Digital" checked>
                     Digital
                 </label>
                 </div>
                 <div>	                 
                 <label>
                     <input type="radio" name="form" value="Atm" >
                     Atm
                 </label>
                 </div>
	          </div> 	      
	      </div>
	               
	      
	       <div id="container-pay"> 	      	      
	          <div id="content-option">	         
	            <div><span>Via:</span></div>
	            <div>              
                 <label>
                     <input type="radio" name="pay" value="Pix" checked>
                     Pix
                 </label>
                 </div>
                <div>                
                 <label>
                     <input type="radio" name="pay" value="CBarra" >
                     Codigo Barra
                 </label>
                 </div>
	        </div>	      
	     </div>
	         	            
	            
	     <div id="container-account-forward"> 	         	         
	           <div id="content-account">	           
	            <div class="input-group">	            
	                <span>idAcForward</span>	         
	                <input type="text" name="idAcForward" readonly="readonly" value="<%= request.getAttribute("idAcForward")%>"						
			    	placeholder="<%=request.getAttribute("idAcForward")%>">				
	             </div>	           
	             <div class="input-group">	            
	                <span>typeForward</span>	         
	                <input type="text" name="typeForward" readonly="readonly" value="<%= request.getAttribute("typeForward")%>"						
			    	placeholder="<%=request.getAttribute("typeForward")%>">				
	             </div>	         	         
	              <div class="input-group">	             
	                <span>numberForward</span>	         
	                <input type="text" name="numberForward" readonly="readonly" value="<%= request.getAttribute("numberForward")%>"						
			    	placeholder="<%=request.getAttribute("numberForward")%>">				
	              </div>       	             
	          </div> 
	       </div>
	           	              
	      
	      <div id="container-form-trs">
	       
	        <div id="content-form-trs">
	           <div class="input-group">				
			     <input type="date" name="trsDate">						
				 <span  class="error-msg" id="errorTrsDate"></span>
		       </div>	
			   <div class="input-group">
				 <input type="text" name="trsValue" placeholder="Valor">
			     <span	class="error-msg" id="errorTrsValue"></span>
			   </div>
			 </div>			 	
			 
			 <div id="content-form-trs-bg">	
	           <div class="input-group">
				 <input type="text" name="desc" placeholder="Desc">
				 <span class="error-msg" id="errorDesc"></span>
			   </div>   
	        </div>      
	    </div>
	          
	          
	      <div id="container-btn-trs">  
	         <div class="btn-group">
			  <button type="button" onclick="validateTrs()">Salvar</button>
		   </div>  
		  </div> 
	            	
	  </form>
	      
	  
	   
	    
	      
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