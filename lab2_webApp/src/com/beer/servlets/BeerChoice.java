package com.beer.servlets;

import com.beer.model.BeerExpert;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "beerChoice")
public class BeerChoice extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String type = request.getParameter("colour");
        if(type == null) return;
        BeerExpert.setBeerBrand(type);
        response.sendRedirect("beer_result.jsp");
    }
}
