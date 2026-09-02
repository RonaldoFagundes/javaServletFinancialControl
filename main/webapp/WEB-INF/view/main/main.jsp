<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.BankModel"%>

<%
    ArrayList<BankModel> banks =
        (ArrayList<BankModel>) request.getAttribute("banks");
%>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        Meus Bancos
    </title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

<header>

    <div>

        <h1>
            Meus Bancos
        </h1>

    </div>

    <div>

        <span>
            Olá, ${sessionScope.loggedUser}!
        </span>

        <form
            action="${pageContext.request.contextPath}/logout"
            method="POST">

            <button type="submit">
                Sair
            </button>

        </form>

    </div>

</header>


<main>

    <section>

        <h2>
            Bancos cadastrados
        </h2>

    </section>


    <section id="container-bancs">

        <%
            if (banks == null || banks.isEmpty()) {
        %>

            <p>
                Nenhum banco cadastrado.
            </p>

        <%
            } else {
        %>

            <table id="table">

                <thead>

                    <tr>

                        <th>
                            Logo
                        </th>

                        <th>
                            Banco
                        </th>

                        <th>
                            Contato
                        </th>

                    </tr>

                </thead>


                <tbody>

                <%
                    for (BankModel bank : banks) {
                %>

                    <tr>

                        <td>

                            <a
                                href="${pageContext.request.contextPath}/selectBank?idBnk=<%= bank.getId() %>"
                                class="bank-select">

                                <%
                                    if (bank.getImg() != null &&
                                        !bank.getImg().isEmpty()) {
                                %>

                                    <img
                                        src="data:image/png;base64,<%= bank.getImg() %>"
                                        alt="Logo do banco"
                                        class="bank-img">

                                <%
                                    } else {
                                %>

                                    <span>
                                        Sem imagem
                                    </span>

                                <%
                                    }
                                %>

                            </a>

                        </td>


                        <td>

                            <%= bank.getName() %>

                        </td>


                        <td>

                            <%
                                if (bank.getContact() != null &&
                                    !bank.getContact().isEmpty()) {
                            %>

                                <a
                                    href="<%= bank.getContact() %>"
                                    target="_blank"
                                    rel="noopener noreferrer">

                                    <%= bank.getContact() %>

                                </a>

                            <%
                                } else {
                            %>

                                Não informado

                            <%
                                }
                            %>

                        </td>

                    </tr>

                <%
                    }
                %>

                </tbody>

            </table>

        <%
            }
        %>

    </section>


    <section>

        <a
            href="${pageContext.request.contextPath}/newBank"
            id="btn-default">

            Cadastrar novo banco

        </a>

    </section>

</main>


<footer>

    <div>

        <span>
            Sistema de Gestão Financeira
        </span>

    </div>

    <div>

        <span class="version">
            v1.5.26
        </span>

    </div>

</footer>

</body>

</html>
