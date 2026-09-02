package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RescueDao;
import service.InvestmentsService;

@WebServlet("/creatRescue")
public class RescueController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final RescueDao rescueDao =
            new RescueDao();

    private final InvestmentsService investmentsService =
            new InvestmentsService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int idInvestment =
                    Integer.parseInt(
                            request.getParameter("idInv"));

            int idAccount =
                    Integer.parseInt(
                            request.getParameter("fkBka"));

            String openDate =
                    request.getParameter("open");

            String investmentType =
                    request.getParameter("type");

            String broker =
                    request.getParameter("broker");

            String rescueDate =
                    request.getParameter("rescue-date");

            String description =
                    request.getParameter("desc");

            BigDecimal rescueValue =
                    new BigDecimal(
                            request.getParameter("rescue"));

            boolean exempt =
                    "Lci".equals(investmentType)
                    || "Lca".equals(investmentType);

            BigDecimal irValue =
                    BigDecimal.ZERO;

            BigDecimal liquidValue =
                    rescueValue;

            if (!exempt) {

                BigDecimal irTx =
                        investmentsService.getIrTx(
                                openDate,
                                rescueDate);

                irValue =
                        investmentsService.getIrValue(
                                irTx,
                                rescueValue);

                liquidValue =
                        rescueValue.subtract(irValue);
            }

            /*
             * O INSERT definitivo deve ser implementado
             * conforme o RescueDao.
             */

            response.sendRedirect(
                    "selectInvest?idInv=" + idInvestment);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Valor ou identificador inválido.");

            request.getRequestDispatcher(
                    "home.jsp").forward(
                            request,
                            response);
        }
    }
}
