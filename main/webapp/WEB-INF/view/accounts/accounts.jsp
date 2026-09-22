<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.AccountsModel"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<AccountsModel> list =
    (ArrayList<AccountsModel>) request.getAttribute("accounts");

if (list == null) {
    list = new ArrayList<AccountsModel>();
}

String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Contas</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/style.css">

    <script
        src="${pageContext.request.contextPath}/assets/js/script.js"
        defer>
    </script>

    <script
        src="${pageContext.request.contextPath}/assets/js/validation.js"
        defer>
    </script>

</head>

<body>

<header>

    <!-- =====================================================
         BANCO - CARREGADO PELO LOCALSTORAGE
         ===================================================== -->

    <div
        class="bank-header"
        id="bank-header">

        <div class="bank-header-content">

            <img
                id="bank-img"
                src=""
                alt="Logo do banco"
                class="bank-img"
                style="display:none;">

            <span id="bank-name">
                Banco
            </span>

        </div>

    </div>


    <!-- =====================================================
         BOTÕES
         ===================================================== -->

    <div id="container-btn">

        <div>

            <a
                href="<%=contextPath%>/main"
                id="btn-default">

                Início

            </a>

        </div>


        <div>

            <form
                action="<%=contextPath%>/logout"
                method="POST"
                style="display:inline;">

                <button
                    type="submit"
                    id="btn-default">

                    Sair

                </button>

            </form>

        </div>

    </div>

</header>


<main>


    <!-- =====================================================
         BANCO SELECIONADO
         ===================================================== -->

    <input
        type="hidden"
        name="bank-source"
        id="bank-source"
        value="">


    <!-- =====================================================
         SELEÇÃO DE CONTA
         ===================================================== -->

    <div class="account-select-container">

        <label for="accountSelect">

            Escolha uma Conta:

        </label>


        <select id="accountSelect">

            <option value="">

                -- Selecionar --

            </option>


            <%

            for (AccountsModel acc : list) {

            %>

                <option

                    value="<%=acc.getId()%>"

                    data-number="<%=acc.getNumber()%>"

                    data-type="<%=acc.getType()%>"

                    data-amount="<%=acc.getBalance()%>"

                    data-fk="<%=acc.getFkbnk()%>"
                >

                    <%=acc.getNumber()%>
                    -
                    <%=acc.getType()%>

                </option>


            <%

            }

            %>

        </select>

    </div>


    <!-- =====================================================
         DETALHES DA CONTA
         ===================================================== -->

    <div id="accountDetails"></div>


    <!-- =====================================================
         SERVIÇOS
         ===================================================== -->

    <div id="container-service"></div>


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

        <span class="version">

            v1.5.26

        </span>

    </div>

</footer>


</body>

</html>
