package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /*
     * JSP do formulário de login.
     *
     * A JSP está dentro de WEB-INF,
     * portanto não pode ser acessada diretamente
     * pelo navegador.
     */
    private static final String LOGIN_PAGE =
            "/WEB-INF/view/login/home.jsp";


    /*
     * =====================================================
     * GET /login
     * =====================================================
     *
     * Abre a tela de login.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	 response.setHeader(
    	            "Cache-Control",
    	            "no-cache, no-store, must-revalidate"
    	    );

    	    response.setHeader(
    	            "Pragma",
    	            "no-cache"
    	    );

    	    response.setDateHeader(
    	            "Expires",
    	            0
    	    );

        HttpSession session = request.getSession(false);


        /*
         * Se já existe usuário autenticado,
         * não mostra novamente o login.
         */
        if (session != null
                && session.getAttribute("loggedUser") != null) {

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );

            return;
        }


        /*
         * Usuário não autenticado.
         *
         * Mostra:
         * /WEB-INF/view/login/home.jsp
         */
        request.getRequestDispatcher(
                LOGIN_PAGE
        ).forward(
                request,
                response
        );
    }


    /*
     * =====================================================
     * POST /login
     * =====================================================
     *
     * Processa o formulário de login.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        /*
         * Recebe os dados do formulário.
         */
        String user =
                request.getParameter("user");

        String password =
                request.getParameter("password");


        /*
         * Remove espaços do usuário.
         */
        if (user != null) {
            user = user.trim();
        }


        /*
         * Validação dos campos.
         */
        if (user == null
                || user.isEmpty()
                || password == null
                || password.isEmpty()) {

            request.setAttribute(
                    "error",
                    "Usuário e senha são obrigatórios."
            );

            request.getRequestDispatcher(
                    LOGIN_PAGE
            ).forward(
                    request,
                    response
            );

            return;
        }


        /*
         * =================================================
         * LOGIN TEMPORÁRIO
         * =================================================
         *
         * Usuário: admin
         * Senha:   123
         *
         * Depois podemos substituir por LoginDao.
         */
        boolean loginValido =
                "admin".equals(user)
                && "123".equals(password);


        /*
         * =================================================
         * LOGIN CORRETO
         * =================================================
         */
        if (loginValido) {

            /*
             * Cria a sessão.
             */
            HttpSession session =
                    request.getSession(true);


            /*
             * Guarda o usuário autenticado.
             *
             * O LoginFilter verifica exatamente
             * este atributo.
             */
            session.setAttribute(
                    "loggedUser",
                    user
            );


            /*
             * Redireciona para /main.
             *
             * NÃO coloque aqui:
             *
             * /WEB-INF/view/login/home.jsp
             *
             * A tela de login já foi utilizada.
             */
            response.sendRedirect(
                    request.getContextPath() + "/main"
            );

            return;
        }


        /*
         * =================================================
         * LOGIN INCORRETO
         * =================================================
         */
        request.setAttribute(
                "error",
                "Usuário ou senha inválidos."
        );


        /*
         * Volta para a tela de login.
         */
        request.getRequestDispatcher(
                LOGIN_PAGE
        ).forward(
                request,
                response
        );
    }
}
