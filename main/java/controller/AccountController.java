package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountsDao;
import dao.BankDao;
import model.AccountsModel;
import model.BankModel;

@WebServlet("/selectAccount")
public class AccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final AccountsDao accountsDao = new AccountsDao();
    private final BankDao bankDao = new BankDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String parameter = request.getParameter("idBnk");

        if (parameter == null || parameter.trim().isEmpty()) {
            parameter = request.getParameter("fkBnk");
        }

        if (parameter == null || parameter.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/main"
            );
            return;
        }

        try {

            int idBank = Integer.parseInt(parameter);

            /*
             * Busca as contas do banco.
             */
            AccountsModel account = new AccountsModel();
            account.setFkbnk(idBank);

            ArrayList<AccountsModel> accounts =
                    accountsDao.listAccounts(account);

            /*
             * Busca os dados do banco.
             */
            BankModel bank =
                    bankDao.findById(idBank);

            /*
             * Envia os dados para accounts.jsp.
             */
            request.setAttribute("accounts", accounts);
            request.setAttribute("bank", bank);
            request.setAttribute("idBnk", idBank);

            request.getRequestDispatcher(
                    "/WEB-INF/view/accounts/accounts.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/main"
            );
        }
    }
}
