<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import="model.InvestmentsModel"%>

<%
    InvestmentsModel invm = (InvestmentsModel) request.getAttribute("investment");

  if (invm == null) {
    response.sendRedirect(request.getContextPath() + "/main");
   return;
  }
 %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Investment</title>

<link rel="stylesheet"  href="${pageContext.request.contextPath}/assets/css/style.css">
<script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>
<script src="${pageContext.request.contextPath}/assets/js/validation.js" defer></script>

</head>
<body>

<header>

    <div class="bank-header">
       <div>
       
       <!--  
        <img
           id="transfer-bank-img"
           class="bank-img"
           alt="Banco">
          <span id="transfer-bank-name"></span>
       -->
       
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
      <a href="${pageContext.request.contextPath}/readInvest?idAcc=<%=invm.getFkBka()%>"
          id="btn-default">
         Voltar
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
<input
    type="hidden"
    id="bank-source"
    name="bank-source">


<!-- =====================================================
     CARD DETALHES DA APLICAÇÃO
     ===================================================== -->

<section class="investment-details-card">


    <div class="investment-card-header">

        <h2>
            Detalhes da Aplicação
        </h2>

    </div>


    <div class="investment-info">


        <div class="investment-row">

            <span class="investment-label">
                ID
            </span>

            <span class="investment-value">
                <%=invm.getId()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Corretora
            </span>

            <span class="investment-value">
                <%=invm.getBroker()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Tipo
            </span>

            <span class="investment-value">
                <%=invm.getType()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Data de abertura
            </span>

            <span class="investment-value">
                <%=invm.getOpen()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Vencimento
            </span>

            <span class="investment-value">
                <%=invm.getExpery()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Tipo de taxa
            </span>

            <span class="investment-value">
                <%=invm.getRateType()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Taxa
            </span>

            <span class="investment-value">
                <%=invm.getRate()%>%
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Valor aplicado
            </span>

            <span class="investment-value">
                R$ <%=invm.getValue()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Rentabilidade
            </span>

            <span class="investment-value">
                R$ <%=invm.getProfitability()%>
            </span>

        </div>


        <div class="investment-row">

            <span class="investment-label">
                Total resgatado
            </span>

            <span class="investment-value">
                R$ <%=invm.getRescue()%>
            </span>

        </div>


        <div class="investment-row investment-total">

            <span class="investment-label">
                Saldo disponível
            </span>

            <span class="investment-value">
                R$ <%=invm.getAmount()%>
            </span>

        </div>


    </div>


    <!-- =================================================
         BOTÃO RESGATE
         ================================================= -->

    <div class="investment-actions">

        <button
            type="button"
            id="btn-open-rescue"
            class="btn-rescue">

            Resgatar aplicação

        </button>

    </div>


</section>

</main> 




<div id="rescueModal" class="modal">
<div class="modal-content">


    <div class="modal-header">

        <h2>
            Resgate da Aplicação
        </h2>


        <button
            type="button"
            id="btn-close-modal"
            class="modal-close">

            &times;

        </button>

    </div>


    <div class="modal-body">


        <div class="rescue-balance">

            <span>
                Saldo disponível
            </span>

            <strong>
                R$ <%=invm.getAmount()%>
            </strong>

        </div>


        <form
            name="frmRescue"
            id="frmRescue"
            class="rescue-form"
            method="POST"
            action="${pageContext.request.contextPath}/creatRescue">


            <!-- ID DA APLICAÇÃO -->

            <input
                type="hidden"
                name="idInv"
                value="<%=invm.getId()%>">


            <!-- ID DA CONTA -->

            <input
                type="hidden"
                name="fkBka"
                value="<%=invm.getFkBka()%>">


            <!-- CORRETORA -->

            <input
                type="hidden"
                name="broker"
                value="<%=invm.getBroker()%>">


            <!-- DATA ABERTURA -->

            <input
                type="hidden"
                name="open"
                value="<%=invm.getOpen()%>">


            <!-- TIPO -->

            <input
                type="hidden"
                name="type"
                value="<%=invm.getType()%>">


            <div class="input-group">

                <label for="rescue-date">
                    Data do resgate
                </label>

                <input
                    type="date"
                    name="rescue-date"
                    id="rescue-date"
                    required>

                <span
                    class="error-msg"
                    id="errorDate">
                </span>

            </div>


            <div class="input-group">

                <label for="rescue">
                    Valor do resgate
                </label>

                <input
                    type="text"
                    name="rescue"
                    id="rescue"
                    placeholder="0,00"
                    autocomplete="off"
                    required>

                <span
                    class="error-msg"
                    id="erroValue">
                </span>

            </div>


            <div class="input-group">

                <label for="desc">
                    Descrição
                </label>

                <input
                    type="text"
                    name="desc"
                    id="desc"
                    placeholder="Descrição do resgate"
                    maxlength="255">

                <span
                    class="error-msg"
                    id="erroDesc">
                </span>

            </div>


            <div
                id="errorContainer"
                class="error-container">

                <% if(request.getAttribute("error") != null) { %>

                    <%=request.getAttribute("error")%>

                <% } %>

            </div>


            <div class="modal-actions">

                <button
                    type="button"
                    id="btn-cancel-rescue"
                    class="btn-cancel">

                    Cancelar

                </button>


                <button
                    type="button"
                    onclick="validateRescue()"
                    class="btn-confirm">

                    Confirmar resgate

                </button>

            </div>


        </form>

    </div>

</div>

</div> 


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

    <span class="version">

        v1.5.26

    </span>

</div>

</footer>


</body>
</html>