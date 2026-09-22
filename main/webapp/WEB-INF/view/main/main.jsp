<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.BankModel"%>

<%
@SuppressWarnings("unchecked")
ArrayList<BankModel> banks =
(ArrayList<BankModel>) request.getAttribute("banks");

String contextPath = request.getContextPath();


%>

<!DOCTYPE html> <html lang="pt-BR"> <head>
<meta charset="UTF-8">

<meta
    name="viewport"
    content="width=device-width, initial-scale=1.0">

<title>Meus Bancos</title>

<link
    rel="stylesheet"
    href="<%=contextPath%>/assets/css/style.css">

<script>
    window.contextPath = "<%=contextPath%>";
</script>

<script
    src="<%=contextPath%>/assets/js/script.js"
    defer>
</script>

</head>
 <body>
 
 <header>
 
   <div>
      <h1>Meus Bancos</h1>
  </div>

  <div>

    <div>
    
      <span>
        Olá, ${sessionScope.loggedUser}!
      </span>
    
      <form
         action="<%=contextPath%>/logout"
         method="POST">

         <button
            id="btn-default"
            type="submit">
            Sair
         </button>
      </form>
      
    </div>
    

</div>

</header>
 <main>
 
  <section>

     <h2>Bancos cadastrados</h2>

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
                    <th>Logo</th>
                    <th>Banco</th>
                    <th>Contato</th>
                </tr>
            </thead>

            <tbody>
            <%
                for (BankModel bank : banks) {

                    String id = String.valueOf(bank.getId());

                    String img =
                        bank.getImg() != null
                            ? bank.getImg().trim()
                            : "";

                    String name =
                        bank.getName() != null
                            ? bank.getName()
                            : "";

                    String contact =
                        bank.getContact() != null
                            ? bank.getContact()
                            : "";
            %>
                <tr>

                    <!-- ==============================
                         LOGO
                         ============================== -->

                    <td>
                        <a
                            href="<%=contextPath%>/selectBank?idBnk=<%=id%>"
                            class="bank-select"

                            data-id="<%=id%>"

                            data-img="<%=img%>"

                            data-name="<%=name%>"

                            data-contact="<%=contact%>"
                        >

                            <%
                                if (!img.isEmpty()) {
                            %>

                                <img
                                    class="bank-logo"
                                    src=""
                                    alt="Logo de <%=name%>"
                                    data-bank-image="<%=img%>"
                                    data-bank-name="<%=name%>"
                                >

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


                    <!-- ==============================
                         NOME
                         ============================== -->

                    <td>
                        <%=name%>
                    </td>


                    <!-- ==============================
                         CONTATO
                         ============================== -->

                    <td>

                        <%
                            if (!contact.trim().isEmpty()) {
                        %>

                            <a
                                href="<%=contact%>"
                                target="_blank"
                                rel="noopener noreferrer">

                                <%=contact%>

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
        href="<%=contextPath%>/newBank"
        id="btn-default">
        Cadastrar novo banco
    </a>

</section>

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