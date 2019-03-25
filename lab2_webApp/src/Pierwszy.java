import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "pierwszy")
public class Pierwszy extends HttpServlet {

        protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("imie");
        int age = Integer.parseInt(request.getParameter("wiek"));
        out.println("<html>");
        out.println("<head><title>Pierwszy Servlet</title></head>");
        out.println("<body>");
        out.println("<p>Witaj, " + name + ", masz " + age + " lat</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
