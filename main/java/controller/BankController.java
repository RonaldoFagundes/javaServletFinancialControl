package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;

@WebServlet(urlPatterns = {
        "/selectBank",
        "/newBank",
        "/creatBank"
})
public class BankController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BankDao bankDao = new BankDao();

    /*
     * GET
     *
     * Responsável por:
     *
     * /selectBank → seleciona um banco
     * /newBank    → abre formulário de cadastro
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {

            case "/selectBank":
                selectBank(request, response);
                break;

            case "/newBank":
                newBank(request, response);
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/main"
                );
                break;
        }
    }

    /*
     * POST
     *
     * Responsável por:
     *
     * /creatBank → cadastra um novo banco
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        switch (action) {

            case "/creatBank":
                createBank(request, response);
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/main"
                );
                break;
        }
    }

    /*
     * Seleciona um banco.
     *
     * Recebe o ID do banco e encaminha para
     * a tela de contas correspondente.
     */
    private void selectBank(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String idBank = request.getParameter("idBnk");

        /*
         * Validação do ID.
         */
        if (idBank == null || idBank.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );

            return;
        }

        /*
         * Encaminha o ID do banco para a rota
         * responsável pelas contas.
         */
        response.sendRedirect(
                request.getContextPath()
                + "/selectAccount?idBnk="
                + idBank
        );
    }

    /*
     * Abre o formulário de cadastro de banco.
     */
    private void newBank(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/view/banks/cad_bank.jsp"
        ).forward(request, response);
    }

    /*
     * Cadastra um novo banco.
     *
     * O método do BankDao deverá ser utilizado
     * aqui quando o INSERT estiver implementado.
     */
    private void createBank(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        /*
         * Exemplo futuro:
         *
         * String name = request.getParameter("name");
         *
         * BankModel bank = new BankModel();
         * bank.setName(name);
         *
         * bankDao.createBank(bank);
         */

        /*
         * Após o cadastro, retorna ao dashboard.
         */
        response.sendRedirect(
                request.getContextPath() + "/main"
        );
    }
}