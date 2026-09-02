package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InvestmentsDao;
import model.InvestmentsModel;
import service.InvestmentsService;

@WebServlet(urlPatterns = {
        "/readInvest",
        "/newInvest",
        "/creatInvest",
        "/selectInvest"
})
public class InvestmentController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final InvestmentsDao investmentsDao =
            new InvestmentsDao();

    private final InvestmentsService investmentsService =
            new InvestmentsService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {

            case "/newInvest":
                newInvestment(request, response);
                break;

            case "/selectInvest":
                selectInvestment(request, response);
                break;

            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        switch (action) {

            case "/readInvest":
                listInvestments(request, response);
                break;

            case "/creatInvest":
                createInvestment(request, response);
                break;

            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    private void listInvestments(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("idAcc"));

            InvestmentsModel investment =
                    new InvestmentsModel();

            investment.setFkBka(idAccount);

            ArrayList<InvestmentsModel> list =
                    investmentsDao.listInvestments(
                            investment);

            request.setAttribute(
                    "investments",
                    list);

            request.getRequestDispatcher(
                    "investments/list_investments.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect("home.jsp");
        }
    }

    private void newInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("idAcc"));

            request.setAttribute(
                    "fkbka",
                    idAccount);

            request.getRequestDispatcher(
                    "investments/cad_investments.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect("home.jsp");
        }
    }

    private void selectInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idInvestment =
                    Integer.parseInt(
                            request.getParameter("idInv"));

            InvestmentsModel investment =
                    new InvestmentsModel();

            investment.setId(idInvestment);

            investmentsDao.selectInvestById(
                    investment);

            request.setAttribute(
                    "investment",
                    investment);

            request.getRequestDispatcher(
                    "investments/slctd_investments.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect("home.jsp");
        }
    }

    private void createInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            BigDecimal rate =
                    new BigDecimal(
                            request.getParameter("rate"));

            BigDecimal price =
                    new BigDecimal(
                            request.getParameter("price"));

            String date =
                    request.getParameter("open");

            String broker =
                    request.getParameter("broker");

            String description =
                    request.getParameter("desc");

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("fk"));

            /*
             * Aqui entra a gravação do investimento.
             *
             * Precisamos conhecer o InvestmentsDao completo
             * para fazer o INSERT corretamente.
             */

            InvestmentsModel investment =
                    new InvestmentsModel();

            investment.setFkBka(idAccount);

            ArrayList<InvestmentsModel> list =
                    investmentsDao.listInvestments(
                            investment);

            request.setAttribute(
                    "investments",
                    list);

            request.getRequestDispatcher(
                    "investments/list_investments.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Dados numéricos inválidos.");

            request.getRequestDispatcher(
                    "investments/cad_investments.jsp")
                    .forward(request, response);
        }
    }
}
