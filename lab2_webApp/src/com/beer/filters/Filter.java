package com.beer.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "Filter", urlPatterns = {"/beerForm.html", "/beer_result.jsp"})
public class Filter implements javax.servlet.Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session.getAttribute("age") == null || ((int) session.getAttribute("age")) < 18) {
            response.setStatus(403);
            PrintWriter writer = response.getWriter();
            writer.println("You need to be over 18 to go further");
            writer.println("<br><hr><br>\n" +
                    "<a href=\"index.jsp\">Back to index</a>");
            writer.close();
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config){
    }
    public void destroy() {
    }
}
