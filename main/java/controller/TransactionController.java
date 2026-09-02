package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountsDao;
import dao.TransactionsDao;
import model.AccountsModel;
import model.TransactionsModel;

@WebServlet(urlPatterns = {
        "/trf",
        "/pay",
        "/sqe",
        "/creatTrs",
        "/ext"
})
public class TransactionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final AccountsDao accountsDao =
            new AccountsDao();

    private final TransactionsDao transactionsDao =
            new TransactionsDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        if ("/ext".equals(action)) {

            report(request, response);

        } else {

            response.sendRedirect("home.jsp");
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

            case "/trf":
            case "/pay":
            case "/sqe":

                newTransaction(request, response);
                break;

            case "/creatTrs":

                createTransaction(request, response);
                break;

            default:

                response.sendRedirect("home.jsp");
                break;
        }
    }

    private void newTransaction(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String action =
                    request.getServletPath();

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("idAcc"));

            BigDecimal amount =
                    new BigDecimal(
                            request.getParameter("amount"));

            String number =
                    request.getParameter("number");

            String accountType =
                    request.getParameter("type");

            String bank =
                    request.getParameter("bank");

            int idBank =
                    Integer.parseInt(
                            request.getParameter("fkBnk"));

            request.setAttribute(
                    "trsType",
                    action);

            request.setAttribute(
                    "idAcc",
                    idAccount);

            request.setAttribute(
                    "bank",
                    bank);

            request.setAttribute(
                    "numberAcc",
                    number);

            request.setAttribute(
                    "typeAcc",
                    accountType);

            request.setAttribute(
                    "amountAcc",
                    amount);

            ArrayList<AccountsModel> accounts =
                    new ArrayList<>();

            if ("/trf".equals(action)) {

                if ("Investimentos".equals(accountType)) {

                    AccountsModel account =
                            new AccountsModel();

                    account.setId(idAccount);
                    account.setFkbnk(idBank);

                    accountsDao.uniqueAccount(account);

                    request.setAttribute(
                            "idAcForward",
                            account.getId());

                    request.setAttribute(
                            "numberForward",
                            account.getNumber());

                    request.setAttribute(
                            "typeForward",
                            account.getType());

                    request.setAttribute(
                            "typeTitle",
                            "Transferência");

                } else {

                    accounts =
                            accountsDao.accountsAll(
                                    createAccount(idAccount));

                    request.setAttribute(
                            "accounts",
                            accounts);

                    request.setAttribute(
                            "typeTitle",
                            "Transferências");
                }

            } else if ("/pay".equals(action)) {

                request.setAttribute(
                        "typeTitle",
                        "Pagamentos");
            }

            request.getRequestDispatcher(
                    "transactions/cad_trs.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect("home.jsp");
        }
    }

    private AccountsModel createAccount(int id) {

        AccountsModel account =
                new AccountsModel();

        account.setId(id);

        return account;
    }

    private void createTransaction(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String transactionType =
                    request.getServletPath();

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("idAcc"));

            BigDecimal value =
                    new BigDecimal(
                            request.getParameter("trsValue"));

            BigDecimal accountAmount =
                    new BigDecimal(
                            request.getParameter("amountAcc"));

            if (value.compareTo(BigDecimal.ZERO) <= 0) {

                showError(
                        request,
                        response,
                        "Valor deve ser maior que zero.");

                return;
            }

            if ("/trf".equals(transactionType)
                    && value.compareTo(accountAmount) > 0) {

                showError(
                        request,
                        response,
                        "Saldo insuficiente.");

                return;
            }

            /*
             * Aqui deve entrar o TransactionsDao.
             *
             * Exemplo:
             *
             * transactionsDao.insert(...)
             *
             * Precisamos do seu TransactionsDao
             * para montar o INSERT corretamente.
             */

            response.sendRedirect(
                    "selectBank");

        } catch (NumberFormatException e) {

            showError(
                    request,
                    response,
                    "Valor numérico inválido.");
        }
    }

    private void report(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("idAcc"));

            TransactionsModel transaction =
                    new TransactionsModel();

            transaction.setFkbka(idAccount);

            ArrayList<TransactionsModel> report =
                    transactionsDao.report(transaction);

            request.setAttribute(
                    "report",
                    report);

            /*
             * Definir aqui a JSP do relatório.
             */

        } catch (NumberFormatException e) {

            response.sendRedirect("home.jsp");
        }
    }

    private void showError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message)
            throws ServletException, IOException {

        request.setAttribute(
                "error",
                message);

        request.getRequestDispatcher(
                "transactions/cad_trs.jsp")
                .forward(request, response);
    }
}
