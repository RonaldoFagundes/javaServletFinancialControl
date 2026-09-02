<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.AccountsModel"%>
<%@ page import="model.BankModel"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<AccountsModel> list =
        (ArrayList<AccountsModel>) request.getAttribute("accounts");

BankModel bank =
        (BankModel) request.getAttribute("bank");

if (list == null) {
    list = new ArrayList<>();
}
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

    <div class="bank-header">

        <% if (bank != null) { %>

            <% if (bank.getImg() != null &&
                   !bank.getImg().trim().isEmpty()) { %>

                <img
                    id="bank-img"
                    src="data:image/png;base64,<%=bank.getImg()%>"
                    alt="<%=bank.getName()%>"
                    class="bank-img">

            <% } %>

            <span id="bank-name">
                <%=bank.getName()%>
            </span>

        <% } else { %>

            <span id="bank-name">
                Banco não encontrado
            </span>

        <% } %>

    </div>


    <div id="container-btn">

        <a
            href="${pageContext.request.contextPath}/main"
            id="btn-default">

            Início

        </a>

        <form
            action="${pageContext.request.contextPath}/logout"
            method="POST"
            style="display:inline;">

            <button
                type="submit"
                id="btn-default">

                Sair

            </button>

        </form>

    </div>

</header>


<main>

    <input
        type="hidden"
        name="bank-source"
        id="bank-source"
        value="<%=bank != null ? bank.getName() : ""%>">


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
                    data-bank="<%=bank != null ? bank.getName() : ""%>"
                    data-number="<%=acc.getNumber()%>"
                    data-type="<%=acc.getType()%>"
                    data-amount="<%=acc.getBalance()%>"
                    data-fk="<%=acc.getFkbnk()%>">

                    <%=acc.getNumber()%>
                    -
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

    <div>

        <a
            href="https://github.com/RonaldoFagundes"
            target="_blank"
            rel="noopener noreferrer">

            Desenvolvido por RFagundes

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
