package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BankDao;
import model.BankModel;

@WebServlet("/main")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BankDao bankDao = new BankDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * Verifica se o usuário está logado.
         */
        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("loggedUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }


        /*
         * Busca os bancos cadastrados.
         */
        ArrayList<BankModel> banks =
                bankDao.listBanks();


        /*
         * Evita enviar null para o JSP.
         */
        if (banks == null) {
            banks = new ArrayList<BankModel>();
        }


        /*
         * Envia os bancos para o JSP.
         */
        request.setAttribute(
                "banks",
                banks
        );


        /*
         * IMPORTANTE:
         *
         * Coloque aqui o caminho REAL
         * do seu main.jsp.
         */
        request.getRequestDispatcher(
                "/WEB-INF/view/main/main.jsp"
        ).forward(
                request,
                response
        );
    }
}
