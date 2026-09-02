<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Cadastrar Banco</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

    <!-- =====================================================
         CABEÇALHO
         ===================================================== -->

    <header>

        <div>

            <h1>
                Cadastrar novo Banco
            </h1>

        </div>

        <div>

            <a
                href="${pageContext.request.contextPath}/main"
                id="btn-default">

                Voltar

            </a>

        </div>

    </header>


    <!-- =====================================================
         CONTEÚDO PRINCIPAL
         ===================================================== -->

    <main>

        <section class="login-form">

            <h2>
                Dados do Banco
            </h2>


            <!--
                Formulário de cadastro

                Controller esperado:
                /creatBank
            -->

            <form
                action="${pageContext.request.contextPath}/creatBank"
                method="POST"
                enctype="multipart/form-data">


                <!-- =================================================
                     NÚMERO DO BANCO
                     ================================================= -->

                <div class="input-group">

                    <label for="number">
                        Número do Banco
                    </label>

                    <input
                        type="text"
                        id="number"
                        name="number"
                        placeholder="Ex.: 001"
                        maxlength="20"
                        required>

                </div>


                <!-- =================================================
                     NOME
                     ================================================= -->

                <div class="input-group">

                    <label for="name">
                        Nome do Banco
                    </label>

                    <input
                        type="text"
                        id="name"
                        name="name"
                        placeholder="Ex.: Banco do Brasil"
                        maxlength="100"
                        required>

                </div>


                <!-- =================================================
                     CNPJ / E.I.N.
                     ================================================= -->

                <div class="input-group">

                    <label for="ein">
                        CNPJ
                    </label>

                    <input
                        type="text"
                        id="ein"
                        name="ein"
                        placeholder="00.000.000/0000-00"
                        maxlength="18">

                </div>


                <!-- =================================================
                     CONTATO
                     ================================================= -->

                <div class="input-group">

                    <label for="contact">
                        Site / Contato
                    </label>

                    <input
                        type="url"
                        id="contact"
                        name="contact"
                        placeholder="https://www.exemplo.com.br"
                        maxlength="255">

                </div>


                <!-- =================================================
                     DESCRIÇÃO
                     ================================================= -->

                <div class="input-group">

                    <label for="desc">
                        Descrição
                    </label>

                    <textarea
                        id="desc"
                        name="desc"
                        rows="4"
                        placeholder="Descrição do banco..."
                        maxlength="500"></textarea>

                </div>


                <!-- =================================================
                     IMAGEM
                     ================================================= -->

                <div class="input-group">

                    <label for="img">
                        Logo do Banco
                    </label>

                    <input
                        type="file"
                        id="img"
                        name="img"
                        accept="image/png,image/jpeg,image/jpg">

                </div>


                <!-- =================================================
                     BOTÃO
                     ================================================= -->

                <div class="btn-group">

                    <button
                        type="submit">

                        Cadastrar Banco

                    </button>

                </div>

            </form>


            <!-- =================================================
                 MENSAGEM DE ERRO
                 ================================================= -->

            <%
                String error =
                    (String) request.getAttribute("error");

                if (error != null && !error.isEmpty()) {
            %>

                <div class="server-error">
                    <%= error %>
                </div>

            <%
                }
            %>


            <!-- =================================================
                 MENSAGEM DE SUCESSO
                 ================================================= -->

            <%
                String success =
                    (String) request.getAttribute("success");

                if (success != null && !success.isEmpty()) {
            %>

                <div style="color: green; margin-top: 10px;">
                    <%= success %>
                </div>

            <%
                }
            %>

        </section>

    </main>


    <!-- =====================================================
         RODAPÉ
         ===================================================== -->

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
