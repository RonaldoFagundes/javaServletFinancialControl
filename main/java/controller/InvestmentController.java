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

    private final InvestmentsDao investmentsDao =  new InvestmentsDao();
    private final InvestmentsService investmentsService = new InvestmentsService();

    
    
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getServletPath();

        switch (action) {

            case "/readInvest":
                listInvestments(request, response);
                break;

            case "/newInvest":
                newInvestment(request, response);
                break;

            case "/selectInvest":
                selectInvestment(request, response);
                break;

            default:

                response.sendRedirect(
                        request.getContextPath() + "/main"
                );

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
                response.sendRedirect(
                        request.getContextPath() + "/main"
                );
                break;
        }
    }


    /*
     * =====================================================
     * LISTAR INVESTIMENTOS
     * =====================================================
     *
     * Recebe o ID da conta através de:
     *
     * idAcc
     *
     * Busca as aplicações pertencentes à conta
     * e encaminha para list_investments.jsp.
     *
     */

    private void listInvestments(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idAccount = Integer.parseInt(
                    request.getParameter("idAcc")
            );


            InvestmentsModel investment =
                    new InvestmentsModel();

            investment.setFkBka(idAccount);


            ArrayList<InvestmentsModel> list =
                    investmentsDao.listInvestments(
                            investment
                    );


            if (list == null) {
                list = new ArrayList<>();
            }


            request.setAttribute(
                    "investments",
                    list
            );


            request.setAttribute(
                    "idAcc",
                    idAccount
            );


            request.getRequestDispatcher(
                    "/WEB-INF/view/investments/list_investments.jsp"
            ).forward(
                    request,
                    response
            );


        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );
        }
    }

    
    
    
    
    
    
    
    

    /*
     * =====================================================
     * NOVA APLICAÇÃO
     * =====================================================
     */

    private void newInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String parameter =
                    request.getParameter("idAcc");


            if (parameter == null ||
                parameter.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath() + "/main"
                );

                return;
            }


            int idAccount =
                    Integer.parseInt(parameter);


            request.setAttribute(
                    "fkbka",
                    idAccount
            );


            request.getRequestDispatcher(
                    "/WEB-INF/view/investments/cad_investments.jsp"
            ).forward(
                    request,
                    response
            );


        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );
        }
    }


    /*
     * =====================================================
     * SELECIONAR INVESTIMENTO
     * =====================================================
     */
    
    
    private void selectInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idInvestment = Integer.parseInt(
                    request.getParameter("idInv")
            );


            InvestmentsModel investment =
                    new InvestmentsModel();

            investment.setId(idInvestment);


            /*
             * Busca a aplicação.
             */
            investmentsDao.selectInvestById(
                    investment
            );


            /*
             * Verifica se encontrou.
             */
            if (investment.getId() <= 0) {

                response.sendRedirect(
                        request.getContextPath() + "/main"
                );

                return;
            }


            /*
             * Envia aplicação para JSP.
             */
            request.setAttribute(
                    "investment",
                    investment
            );


            /*
             * Envia também o ID da conta.
             */
            request.setAttribute(
                    "idAcc",
                    investment.getFkBka()
            );


            request.getRequestDispatcher(
                    "/WEB-INF/view/investments/slctd_investment.jsp"
            ).forward(
                    request,
                    response
            );


        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );
        }
    }

    
    
    
    
    
    
    
    
    

    /*
     * =====================================================
     * CRIAR INVESTIMENTO
     * =====================================================
     */

    private void createInvestment(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            BigDecimal rate =
                    new BigDecimal(
                            request.getParameter("rate")
                    );


            BigDecimal price =
                    new BigDecimal(
                            request.getParameter("price")
                    );


            String date =
                    request.getParameter("open");


            String broker =
                    request.getParameter("broker");


            String description =
                    request.getParameter("desc");


            int idAccount =
                    Integer.parseInt(
                            request.getParameter("fk")
                    );


            /*
             * =================================================
             * CRIA O MODEL
             * =================================================
             */

            InvestmentsModel investment =
                    new InvestmentsModel();


            investment.setFkBka(
                    idAccount
            );


            /*
             * =================================================
             * GRAVAÇÃO DO INVESTIMENTO
             * =================================================
             *
             * A gravação ainda não foi implementada aqui,
             * pois depende da implementação do InvestmentsDao.
             *
             */


            /*
             * =================================================
             * ATUALIZA A LISTA
             * =================================================
             */

            ArrayList<InvestmentsModel> list =
                    investmentsDao.listInvestments(
                            investment
                    );


            if (list == null) {

                list =
                        new ArrayList<InvestmentsModel>();
            }


            request.setAttribute(
                    "investments",
                    list
            );


            request.getRequestDispatcher(
                    "/WEB-INF/view/investments/list_investments.jsp"
            ).forward(
                    request,
                    response
            );


        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Dados numéricos inválidos."
            );


            request.getRequestDispatcher(
                    "/WEB-INF/view/investments/cad_investments.jsp"
            ).forward(
                    request,
                    response
            );
        }
    }
    
    
    
}
