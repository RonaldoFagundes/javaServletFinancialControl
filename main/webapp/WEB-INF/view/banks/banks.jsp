<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.BankModel"%>
<%@ page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<BankModel> banks =
        (ArrayList<BankModel>) request.getAttribute("banks");

    if (banks == null) {
        banks = new ArrayList<BankModel>();
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Meus Bancos - Sistema de Gestão Financeira</title>

    <!-- CSS -->
    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- Contexto da aplicação -->
    <script>
        var contextPath =
            '${pageContext.request.contextPath}';
    </script>

    <!-- JavaScript -->
    <script
        src="${pageContext.request.contextPath}/assets/js/script.js"
        defer>
    </script>

</head>


<body>

    <!-- =====================================================
         CABEÇALHO
         ===================================================== -->

    <header>

        <div>

            <h1>
                Meus Bancos
            </h1>

        </div>


        <div id="container-btn">

            <a
                href="${pageContext.request.contextPath}/main"
                id="btn-default">

                Início

            </a>


            <a
                href="${pageContext.request.contextPath}/logout"
                id="btn-default">

                Sair

            </a>

        </div>

    </header>


    <!-- =====================================================
         CONTEÚDO
         ===================================================== -->

    <main>

        <div id="container-bancs">

            <table id="table">

                <thead>

                    <tr>

                        <th>
                            Banco
                        </th>

                        <th>
                            Nome
                        </th>

                        <th>
                            Site
                        </th>

                    </tr>

                </thead>


                <tbody>

                    <%
                        for (BankModel bank : banks) {
                    %>

                    <tr>

                        <!-- =================================
                             LOGO / SELEÇÃO DO BANCO
                             ================================= -->

                        <td>

                            <a
                                href="${pageContext.request.contextPath}/selectBank?idBnk=<%= bank.getId() %>"
                                class="bank-select"
                                data-id="<%= bank.getId() %>"
                                data-img="<%= bank.getImg() %>"
                                data-name="<%= bank.getName() %>"
                                data-contact="<%= bank.getContact() %>"
                                title="Selecionar banco">

                                <img
                                    src="data:image/png;base64,<%= bank.getImg() %>"
                                    alt="Logo do banco <%= bank.getName() %>"
                                    class="bank-img">

                                <span class="select-overlay">
                                    Selecionar
                                </span>

                            </a>

                        </td>


                        <!-- =================================
                             NOME
                             ================================= -->

                        <td>

                            <span>
                                <%= bank.getName() %>
                            </span>

                        </td>


                        <!-- =================================
                             SITE / CONTATO
                             ================================= -->

                        <td>

                            <%
                                String contact = bank.getContact();

                                if (contact != null &&
                                    !contact.trim().isEmpty()) {
                            %>

                                <a
                                    href="<%= contact %>"
                                    target="_blank"
                                    rel="noopener noreferrer">

                                    <%= contact %>

                                </a>

                            <%
                                } else {
                            %>

                                <span>
                                    Não informado
                                </span>

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


            <!-- =============================================
                 MENSAGEM QUANDO NÃO HÁ BANCOS
                 ============================================= -->

            <%
                if (banks.isEmpty()) {
            %>

                <div class="server-error">

                    Nenhum banco cadastrado.

                </div>

            <%
                }
            %>

        </div>


        <!-- =================================================
             NOVO BANCO
             ================================================= -->

        <div id="container-btn">

            <a
                href="${pageContext.request.contextPath}/newBank"
                id="btn-default">

                Cadastrar novo Banco

            </a>

        </div>

    </main>


    <!-- =====================================================
         RODAPÉ
         ===================================================== -->

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
