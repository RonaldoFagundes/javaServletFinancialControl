package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
    "/main",
    "/selectBank",
    "/newBank",
    "/creatBank",
    "/selectAccount",
    "/readInvest",
    "/newInvest",
    "/creatInvest",
    "/selectInvest",
    "/creatRescue",
    "/ext",
    "/trf",
    "/pay",
    "/sqe",
    "/crc",
    "/creatTrs"
})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            javax.servlet.ServletRequest request,
            javax.servlet.ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse resp = (HttpServletResponse) response;


        /*
         * =================================================
         * IMPEDIR CACHE DO NAVEGADOR
         * =================================================
         *
         * Isso é importante para impedir que o usuário
         * veja páginas protegidas usando o botão VOLTAR.
         */

        resp.setHeader(
                "Cache-Control",
                "no-cache, no-store, must-revalidate"
        );

        resp.setHeader(
                "Pragma",
                "no-cache"
        );

        resp.setDateHeader(
                "Expires",
                0
        );


        /*
         * =================================================
         * VERIFICAÇÃO DA SESSÃO
         * =================================================
         */

        HttpSession session =  req.getSession(false);


        boolean loggedIn =
                session != null
                && session.getAttribute("loggedUser") != null;


        /*
         * =================================================
         * USUÁRIO AUTENTICADO
         * =================================================
         */

        if (loggedIn) {

            chain.doFilter(
                    request,
                    response
            );

            return;
        }


        /*
         * =================================================
         * USUÁRIO NÃO AUTENTICADO
         * =================================================
         */

        resp.sendRedirect(
                req.getContextPath() + "/login"
        );
    }


    @Override
    public void init(
            javax.servlet.FilterConfig filterConfig)
            throws ServletException {
    }


    @Override
    public void destroy() {
    }
}
