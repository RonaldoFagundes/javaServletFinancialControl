<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Login - Sistema de Gestão Financeira</title>

    <!-- CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- JavaScript -->
    <script
        src="${pageContext.request.contextPath}/assets/js/validation.js"
        defer>
    </script>

</head>

<body>

    <main>

        <form
            name="frmLogin"
            id="frmLogin"
            action="${pageContext.request.contextPath}/login"
            method="POST"
            class="login-form">

            <h2>Login</h2>

            <!-- Usuário -->
            <div class="input-group">

                <input
                    type="text"
                    name="user"
                    id="user"
                    placeholder="Usuário"
                    autocomplete="username"
                    required>

                <span
                    class="error-msg"
                    id="errorUser">
                </span>

            </div>

            <!-- Senha -->
            <div class="input-group">

                <input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Senha"
                    autocomplete="current-password"
                    required>

                <span
                    class="error-msg"
                    id="errorPassword">
                </span>

            </div>

            <!-- Botão -->
            <div class="input-group">

                <button type="submit">
                    Entrar
                </button>

            </div>

            <!-- Mensagem de erro enviada pelo Controller -->
            <div
                id="errorContainer"
                class="server-error">

                ${error}

            </div>

        </form>

    </main>

</body>

</html>
